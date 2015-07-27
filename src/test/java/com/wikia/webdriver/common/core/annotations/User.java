package com.wikia.webdriver.common.core.annotations;

import java.io.File;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;

/**
 * Created by Ludwik on 2015-02-19.
 * Added LANGUAGE2 Japanese. By P. Archbold on 2015-07-23
 */
public enum User {
  USER("ci.user.regular.username", "ci.user.regular.password"), USER_2("ci.user.regular2.username",
      "ci.user.regular2.password"), USER_5("ci.user.regular5.username", "ci.user.regular5.password")
  , USER_9("ci.user.regular9.username", "ci.user.regular9.password"), USER_12(
      "ci.user.regular12.username", "ci.user.regular12.password"), STAFF(
      "ci.user.wikiastaff.username", "ci.user.wikiastaff.password"), ANONYMOUS("anonymous",
      "anonymous"),REGULAR_USER_JAPAN("ci.user.language2.username", "ci.user.language2.password");

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
