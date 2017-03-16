package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BaseAuthPage {

  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;

  @FindBy(css = "#loginPassword")
  private WebElement passwordField;

  public ForgotPasswordPage clickForgotPasswordLink() {
    wait.forElementClickable(forgottenPasswordLink);
    forgottenPasswordLink.click();

    return new ForgotPasswordPage();
  }

  public SignInPage typePassword(String password) {
    wait.forElementVisible(passwordField).sendKeys(password);
    return this;
  }


}


