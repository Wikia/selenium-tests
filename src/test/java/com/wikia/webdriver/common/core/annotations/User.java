package com.wikia.webdriver.common.core.annotations;

import java.io.File;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;

/**
 * Created by Ludwik on 2015-02-19.
 */
public enum User {
  USER("ci.user.regular.username", "ci.user.regular.password"), USER_2("ci.user.regular2.username",
      "ci.user.regular2.password"), USER_9("ci.user.regular2.username", "ci.user.regular2.password"), STAFF(
      "ci.user.wikiastaff.username", "ci.user.wikiastaff.password"), ANONYMOUS("anonymous",
      "anonymous");

  private final String userName;

  private final String password;

  private final String filePath = Configuration.getCredentialsFilePath();

  User(String userNameKey, String passwordKey) {
    this.userName = XMLReader.getValue(new File(filePath), userNameKey);
    this.password = XMLReader.getValue(new File(filePath), passwordKey);
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }
}
