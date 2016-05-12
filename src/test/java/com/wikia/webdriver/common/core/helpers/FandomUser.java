package com.wikia.webdriver.common.core.helpers;

import lombok.Getter;

import com.wikia.webdriver.common.core.XMLReader;

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
