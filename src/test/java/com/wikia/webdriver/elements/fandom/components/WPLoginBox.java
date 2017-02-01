package com.wikia.webdriver.elements.fandom.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.helpers.FandomUser;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class WPLoginBox extends BasePageObject {

  @FindBy(css = "#user_login")
  private WebElement username;

  @FindBy(css = "#user_pass")
  private WebElement password;

  @FindBy(css = "#wp-submit")
  private WebElement submit;

  public WPLoginBox login(FandomUser user) {
    username.sendKeys(user.getUsername());
    password.sendKeys(user.getPassword());
    submit.click();

    return this;
  }
}
