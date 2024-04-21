package com.fish.user.adapter;

public interface Login3rdTarget {

	String loginByGitee(String code, String state);

	String loginByGithub(String... params);

	String loginByQQ(String... params);

	String loginByWechat(String... params);

}
