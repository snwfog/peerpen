package com.peerpen.model;

/**
 * @deprecated This class is not used anymore, use an appropriate
 * Active Record model
 */
@Deprecated
public class User
{
  public boolean valid;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String gender;
  private String personalURL;
  private String email;
  private String points;

  // public User(User user) {
  // this.username = user.getUserName();
  // this.password = user.getPassword();
  // }

  public User()
  {
    // TODO Auto-generated constructor stub
  }

  public User(String user, String password)
  {
    this.username = user;
    this.password = password;
  }

  public User(String first_name2, String last_name2, String sex,
      String website, String user_name2, String email2, String password2)
  {
    this.firstName = first_name2;
    this.lastName = last_name2;
    this.setGender(sex);
    this.setPersonalWebsite(website);
    this.username = user_name2;
    this.setEmail(email2);
    this.password = password2;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String newfirst_name)
  {
    firstName = newfirst_name;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String newlast_name)
  {
    lastName = newlast_name;
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
    return this.username;
  }

  public void setUserName(String username)
  {
    this.username = username;
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
    return personalURL;
  }

  public void setPersonalWebsite(String personal_website)
  {
    this.personalURL = personal_website;
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
