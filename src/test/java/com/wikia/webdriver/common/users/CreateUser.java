package com.wikia.webdriver.common.users;

import org.joda.time.DateTime;

public class CreateUser {
  private String userName = "User" + DateTime.now().getMillis();
  private String password = "Pass" + DateTime.now().getMillis();
  private String email = "qaart001+" + DateTime.now().getMillis() + "@gmail.com";
  private DateTime birthday = new DateTime(1952, 12, 12, 12, 0, 0);

  public CreateUser() {

  }

  public CreateUser withName(String userName) {
    this.userName = userName;

    return this;
  }

  public CreateUser withPass(String password) {
    this.password = password;

    return this;
  }

  public CreateUser withEmail(String email) {
    this.email = email;

    return this;
  }

  public CreateUser withBirthday(DateTime birthday) {
    this.birthday = birthday;

    return this;
  }

  public TestUser create() {
    return new TestUser(userName, password, email, birthday);
  }
}
