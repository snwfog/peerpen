package com.peerpen.model;

public class User
{
  public boolean valid;
  private String user_name;
  private String password;
  private String first_name;
  private String last_name;
  private String gender;
  private String personal_website;
  private String email;
  private String points;

  // public User(User user) {
  // this.user_name = user.getUserName();
  // this.password = user.getPassword();
  // }

  public User()
  {
    // TODO Auto-generated constructor stub
  }

  public User(String user, String password)
  {
    this.user_name = user;
    this.password = password;
  }

  public User(String first_name2, String last_name2, String sex,
      String website, String user_name2, String email2, String password2)
  {
    this.first_name = first_name2;
    this.last_name = last_name2;
    this.setGender(sex);
    this.setPersonalWebsite(website);
    this.user_name = user_name2;
    this.setEmail(email2);
    this.password = password2;
  }

  public String getFirstName()
  {
    return first_name;
  }

  public void setFirstName(String newfirst_name)
  {
    first_name = newfirst_name;
  }

  public String getLastName()
  {
    return last_name;
  }

  public void setLastName(String newlast_name)
  {
    last_name = newlast_name;
  }

  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getUserName()
  {
    return this.user_name;
  }

  public void setUserName(String username)
  {
    this.user_name = username;
  }

  // public static User findUser(String name, String password) {
  // return new User(name, password);
  // }

  public boolean isValid()
  {
    return valid;
  }

  public void setValid(boolean newValid)
  {
    valid = newValid;
  }

  public String getGender()
  {
    return gender;
  }

  public void setGender(String gender)
  {
    this.gender = gender;
  }

  public String getPersonalWebsite()
  {
    return personal_website;
  }

  public void setPersonalWebsite(String personal_website)
  {
    this.personal_website = personal_website;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPoints()
  {
    return points;
  }

  public void setPoints(String points)
  {
    this.points = points;
  }

}
