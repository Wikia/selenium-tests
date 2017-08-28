package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookMainPageObject extends BasePageObject {

  @FindBy(css = "#email")
  private WebElement emailField;
  @FindBy(css = "#pass")
  private WebElement passwordField;
  @FindBy(css = "#loginbutton")
  private WebElement loginButton;

  public void login(String facebookEmail, String facebookPassword) {
    getUrl(URLsContent.FACEBOOK_MAINPAGE);
    performLogin(facebookEmail, facebookPassword);
  }

  private void performLogin(String email, String password) {
    typeEmail(email);
    typePassword(password);
    clickLoginButton();
  }

  private void clickLoginButton() {
    waitAndClick(loginButton);
  }

  private void typePassword(String password) {
    fillInput(passwordField, password);
  }

  private void typeEmail(String email) {
    fillInput(emailField, email);
  }

}
