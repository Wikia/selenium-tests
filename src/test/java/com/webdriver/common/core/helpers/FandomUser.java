package com.webdriver.common.core.helpers;

import com.webdriver.common.core.XMLReader;
import lombok.Getter;

public enum FandomUser {
  EDITOR("fandom.users.admin.username", "fandom.users.admin.password");

  @Getter
  private String username;

  @Getter
  private String password;

  FandomUser(String userNameKey, String passwordKey) {
    this.username = XMLReader.getValue(userNameKey);
    this.password = XMLReader.getValue(passwordKey);
  }
}
