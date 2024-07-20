package com.fish.user.adapter;

import cn.hutool.http.HttpUtil;
import com.fish.user.config.LoginGiteeConfig;
import com.fish.user.entity.UserInfo;
import com.fish.user.service.impl.UserInfoServiceImpl;
import com.fish.user.util.YangUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

@Slf4j
@Component
public class Login3rdAdapter extends UserInfoServiceImpl implements Login3rdTarget {

	private final Gson gson = YangUtil.getGson();

	@Resource
	private LoginGiteeConfig loginGiteeConfig;

	@Override
	public String loginByGitee(String code, String state) {

		// todo 临时测试赋值
		state = "GITEE";

		// 进行 state 判断，值是前后端商定好的，前端将 state 传给 Gitee 平台，Gitee平台回传到回调接口
		if (!loginGiteeConfig.getState().equals(state)) {
			throw new UnsupportedOperationException("Invalid state!");
		}

		// 请求用户信息，并携带 Token
		String tokenUrl = loginGiteeConfig.getTokenUrl().concat(code);

		String giteeTokenResp = HttpUtil.post(tokenUrl, Collections.emptyMap());
		JsonElement giteeRespTree = gson.fromJson(giteeTokenResp, JsonElement.class);
		log.debug("请求Gitee携带 code 响应数据 ==> {}", gson.toJson(giteeRespTree));
		String accessToken = giteeRespTree.getAsJsonObject().get("access_token").getAsString();

		String userUrl = loginGiteeConfig.getUserUrl().concat(accessToken);
		String giteeUserInfoResp = HttpUtil.get(userUrl);
		JsonElement userInfoTree = gson.fromJson(giteeUserInfoResp, JsonElement.class);
		log.debug("请求Gitee携带 Token 响应数据 ==> {}", gson.toJson(userInfoTree));
		String giteeLogin = userInfoTree.getAsJsonObject().get("login").getAsString();
		String userName = loginGiteeConfig.getGiteeUserPrefix().concat(giteeLogin);
		String password = userName;

		return this.autoRegister3rdAndLogin(userName, password);
	}

	@Override
	public String loginByGithub(String... params) {
		return "";
	}

	@Override
	public String loginByQQ(String... params) {
		return "";
	}

	@Override
	public String loginByWechat(String... params) {
		return "";
	}

	private String autoRegister3rdAndLogin(String userName, String password) {
		// 如果第三方账户已经登录过，则直接登录
		if (super.checkUserExist(userName)) {
			return super.login(userName, password);
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
		super.register(userInfo);

		// 自动注册完成后，进行登录
		return super.login(userName, password);
	}

}
