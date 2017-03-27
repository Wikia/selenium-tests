package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.BaseAuthPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedSignInPage extends BaseAuthPage {

  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;

  @FindBy(css = "#loginPassword")
  private WebElement passwordField;

  public AttachedForgotPasswordPage clickForgotPasswordLink() {
    wait.forElementClickable(forgottenPasswordLink);
    forgottenPasswordLink.click();

    return new AttachedForgotPasswordPage();
  }

  public AttachedSignInPage typePassword(String password) {
    wait.forElementVisible(passwordField).sendKeys(password);
    return this;
  }


}


