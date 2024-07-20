package com.fish.user.bridge.function;

import cn.hutool.http.HttpUtil;
import com.fish.user.bridge.abst.factory.RegisterLoginComponentFactory;
import com.fish.user.config.LoginGiteeConfig;
import com.fish.user.entity.UserInfo;
import com.fish.user.mapper.UserInfoMapper;
import com.fish.user.util.YangUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Component
public class RegisterLoginByGitee extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	private final Gson gson = YangUtil.getGson();

	@Resource
	private UserInfoMapper userInfoMapper;

	@Resource
	private LoginGiteeConfig loginGiteeConfig;

	@PostConstruct
	private void initFuncMap() {
		RegisterLoginComponentFactory.funcMap.put("Gitee", this);
	}

	@Override
	public String login(String account, String password) {
		return super.commonLogin(account, password, userInfoMapper);
	}

	@Override
	public String register(UserInfo userInfo) {
		return super.commonRegister(userInfo, userInfoMapper);
	}

	@Override
	public boolean checkUserExists(String userName) {
		return super.commonCheckUserExists(userName, userInfoMapper);
	}

	@Override
	public String login3rd(HttpServletRequest request) {
		// todo 临时测试赋值
		String state = "GITEE";
		String code = request.getParameter("code");

		// 进行 state 判断，值是前后端商定好的，前端将 state 传给 Gitee 平台，Gitee平台回传到回调接口
		if (!loginGiteeConfig.getState().equals(state)) {
			throw new UnsupportedOperationException("Invalid state!");
		}

		// 请求用户信息，并携带 Token
		String tokenUrl = loginGiteeConfig.getTokenUrl().concat(code);

		String giteeTokenResp = HttpUtil.post(tokenUrl, Collections.emptyMap());
		JsonElement giteeRespTree = gson.fromJson(giteeTokenResp, JsonElement.class);
		String accessToken = giteeRespTree.getAsJsonObject().get("access_token").getAsString();

		String userUrl = loginGiteeConfig.getUserUrl().concat(accessToken);
		String giteeUserInfoResp = HttpUtil.get(userUrl);
		JsonElement userInfoTree = gson.fromJson(giteeUserInfoResp, JsonElement.class);
		String giteeLogin = userInfoTree.getAsJsonObject().get("login").getAsString();
		String userName = loginGiteeConfig.getGiteeUserPrefix().concat(giteeLogin);
		String password = userName;

		return this.autoRegister3rdAndLogin(userName, password);
	}

	private void clearUserInfo(UserInfo user) {
		user.setId(null);
		user.setUserStatus(0);
		user.setCreateTime(null);
		user.setUpdateTime(null);
		user.setDeleteFlag(Boolean.FALSE);
	}

	private String autoRegister3rdAndLogin(String userName, String password) {
		// 如果第三方账户已经登录过，则直接登录
		if (this.checkUserExists(userName)) {
			return this.login(userName, password);
		}

		// 组装用户信息
		UserInfo userInfo = new UserInfo();
		userInfo.setId("");
		userInfo.setNickName("ceshi");
		userInfo.setUserAccount(userName);
		userInfo.setUserPassword(password);
		userInfo.setAvatarUrl("");
		userInfo.setGender(0);
		userInfo.setAge(18);
		userInfo.setPhone("18899998888");
		userInfo.setEmail("123@qq.com");
		userInfo.setAdminFlag(0);
		userInfo.setUserStatus(0);
		// 如果第三方账户是第一次登录，先进行自动注册
		this.register(userInfo);

		// 自动注册完成后，进行登录
		return this.login(userName, password);
	}

}
