package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JoinTodayPage extends BasePageObject {

  @FindBy(css = ".join-page")
  private WebElement overlay;
  @FindBy(css = ".signup-provider-facebook")
  private WebElement facebookConnectButton;
  @FindBy(css = ".signup-provider-email")
  private WebElement registerButton;
  @FindBy(css = ".sign-in.footer-callout-link")
  private WebElement loginButton;
  @FindBy(css = "a.close")
  private WebElement closeButton;

  public SignInPage navigateToSignIn() {
    waitAndClick(loginButton);
    return new AttachedSignInPage();
  }

  public RegisterPage navigateToSignUp() {
    waitAndClick(registerButton);
    return new AttachedRegisterPage();
  }

  public void close() {
    waitAndClick(closeButton);
  }

  public boolean isDisplayed() {
    return wait.forElementNotVisible(overlay);
  }

}
