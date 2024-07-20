package com.fish.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fish.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dayang
 * @since 2023-07-13 13:38:54
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
