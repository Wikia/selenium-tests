package com.wikia.webdriver.elements.fandom.pages;

import lombok.Getter;

import com.wikia.webdriver.common.core.helpers.FandomUser;
import com.wikia.webdriver.elements.fandom.FandomPage;
import com.wikia.webdriver.elements.fandom.components.LoginBox;

public class AdminLoginPage extends FandomPage<AdminLoginPage> {
  private final String URL = "http://qa.fandom.wikia.com/wp-admin";

  @Getter(lazy = true)
  private final LoginBox loginBox = new LoginBox();

  @Override
  public AdminLoginPage open() {
    getUrl(URL);

    return this;
  }

  public AdminLoginPage Login(FandomUser user) {
    getLoginBox().login(user);

    return this;
  }
}
