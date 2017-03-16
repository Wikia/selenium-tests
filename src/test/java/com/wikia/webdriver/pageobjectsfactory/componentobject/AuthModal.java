package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.ForgotPasswordPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthModal extends WikiBasePageObject {

  @FindBy(css = ".auth.desktop.signin-page")
  private WebElement signInAuthModal;
  @FindBy(css = ".auth.desktop.register-page")
  private WebElement registerAuthModal;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;
  @FindBy(css = "#loginPassword")
  private WebElement passwordField;
  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;
  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;
  @FindBy(css = ".register-page .header-callout-link")
  private WebElement linkToSignInForm;
  @FindBy(css = ".signup-providers li a")
  private WebElement connectWithFacebookButton;

  private final String mainWindowHandle;

  public AuthModal() {
    super();
    waitForNewWindow();
    this.mainWindowHandle = driver.getWindowHandle();
    switchToAuthModalHandle();
  }


  private void switchToAuthModalHandle() {
    for (String winHandle : driver.getWindowHandles()) {
      driver.switchTo().window(winHandle);
    }
  }

  private void switchToMainWindowHandle() {
    driver.switchTo().window(this.mainWindowHandle);
  }

  public boolean isOpened() {
    switchToAuthModalHandle();
    try {
      wait.forElementVisible(registerAuthModal);
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Register Auth Modal is not displayed");
      return false;
    }
    switchToMainWindowHandle();
    return true;
  }

  public boolean isSignInOpened() {
    switchToAuthModalHandle();
    try {
      wait.forElementVisible(signInAuthModal);
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Sign In Auth Modal is not displayed");
      return false;
    }
    switchToMainWindowHandle();
    return true;
  }

  public boolean isConnetctWithFacebookButtonVisible() {
    switchToAuthModalHandle();
    boolean isConnetctWithFacebookButtonVisible = registerAuthModal.isDisplayed();
    switchToMainWindowHandle();

    return isConnetctWithFacebookButtonVisible;
  }

  public void login(String username, String password) {
    switchToAuthModalHandle();
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    signInButton.click();
    switchToMainWindowHandle();
  }

  public void login(User user) {
    login(user.getUserName(), user.getPassword());
  }

  public ForgotPasswordPage clickForgotPasswordLink() {
    switchToAuthModalHandle();
    forgottenPasswordLink.click();
    switchToMainWindowHandle();
    return new ForgotPasswordPage();
  }

  public void clickToSignInForm(){
    switchToAuthModalHandle();
    wait.forElementClickable(linkToSignInForm);
    linkToSignInForm.click();
  }

}
