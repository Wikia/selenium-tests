package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthModal extends WikiBasePageObject {

  @FindBy(css = ".auth.desktop.signin-page")
  private WebElement authModal;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;
  @FindBy(css = "#loginPassword")
  private WebElement passwordField;
  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;
  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;

  private final String mainWindowHandle;

  public AuthModal() {
    super();
    waitForNewWindow();
    this.mainWindowHandle = driver.getWindowHandle();
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
    boolean isOpenedResult = authModal.isDisplayed();
    switchToMainWindowHandle();
    return isOpenedResult;
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

  public void clickForgotPasswordLink() {
    switchToAuthModalHandle();
    forgottenPasswordLink.click();
    switchToMainWindowHandle();
  }

}
