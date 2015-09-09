package com.wikia.webdriver.common.users;

import org.joda.time.DateTime;

public class TestUser {

  private String userName;
  private String password;
  private String email;
  private DateTime birthdate;

  public TestUser(String userName, String password, String email, DateTime birthdate) {
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.birthdate = birthdate;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public DateTime getBirthdate() {
    return birthdate;
  }
}
