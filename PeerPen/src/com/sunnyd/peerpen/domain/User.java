package com.sunnyd.peerpen.domain;

public class User {
	String user_name;
	String password;

	public User() {
		user_name = "john smith";
		password = "qwerty";
	}

	public User(String user, String password) {
		this.user_name = user;
		this.password = password;
	}

	public User(User user) {
		this.user_name = user.getUserName();
		this.password = user.getPassword();
	}

	public String getPassword() {
		return this.password;
	}

	public String getUserName() {
		return this.user_name;
	}

	public static User findUser(String name, String password) {
		return new User(name, password);
	}

}
