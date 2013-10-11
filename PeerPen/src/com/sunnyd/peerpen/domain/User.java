package com.sunnyd.peerpen.domain;

public class User {
	private String user_name;
	private String password;
	private String first_name;
    private String last_name;
    private String gender;
    private String personal_website;
    private String email;
    private String points;
    public boolean valid;

	public String getFirst_name() {
		return first_name;
	}

    public void setFirst_name(String newfirst_name) {
        first_name = newfirst_name;
	}

	
    public String getLast_name() {
         return last_name;
	}

    public void setLast_name(String newlast_name) {
         last_name = newlast_name;
	}
	public User(String user, String password) {
		this.user_name = user;
		this.password = password;
	}

//	public User(User user) {
//		this.user_name = user.getUserName();
//		this.password = user.getPassword();
//	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password; 
	}

	
	public String getUserName() {
		return this.user_name;
	}

	public void setUserName(String username){
		this.user_name = username;
	}
	
//	public static User findUser(String name, String password) {
//		return new User(name, password);
//	}
	
	public boolean isValid() {
        return valid;
	}

    public void setValid(boolean newValid) {
        valid = newValid;
	}	

}
