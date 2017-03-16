package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;


import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseAuthPage extends WikiBasePageObject {

  @FindBy(css = ".signup-provider-facebook")
  private WebElement facebookSignUpButton;
  @FindBy(css = ".signup-providers li a")
  private WebElement connectWithFacebookButton;
  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;
  @FindBy(css = "#loginSubmit:disabled")
  private WebElement disabledSignInButton;
  @FindBy(css = ".error")
  private WebElement errorMessage;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;
  @FindBy(css = "#signupPassword")
  private WebElement passwordField;
  @FindBy(className = "auth-header")
  private WebElement header;


  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    wait.forElementClickable(facebookSignUpButton);
    facebookSignUpButton.click();
    PageObjectLogging.log("clickFacebookSignUp", "clicked on sign up with facebok button", true);
    return new FacebookSignupModalComponentObject();
  }

  public boolean isConnetctWithFacebookButtonVisible() {
    return wait.forElementVisible(connectWithFacebookButton).isDisplayed();
  }

  public BaseAuthPage clickSignInButton() {
    wait.forElementClickable(signInButton).click();
    return this;
  }

  private String getErrorMessage() {
    return wait.forElementVisible(errorMessage).getText();
  }

  public BaseAuthPage verifyErrorMessage(String errorMessage) {
    Assertion.assertEquals(getErrorMessage(), errorMessage);
    return this;
  }

  public BaseAuthPage typeUsername(String username) {
    fillInput(usernameField, username);
    return this;
  }

  public BaseAuthPage typePassword(String password) {
    fillInput(passwordField, password);
    return this;
  }

  public void verifySignInButtonNotClickable() {
    wait.forElementVisible(disabledSignInButton);
  }

  @FindBy(css = ".auth.desktop.signin-page")
  private WebElement signInAuthModal;
  @FindBy(css = ".auth.desktop.register-page")
  private WebElement registerAuthModal;
  @FindBy(css = ".register-page .header-callout-link")
  private WebElement linkToSignInForm;

  private final String mainWindowHandle;

  public BaseAuthPage() {
    super();
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

  public void login(String username, String password) {
    typeUsername(username);
    typePassword(password);
    clickSignInButton();
  }

  public void login(User user) {
    login(user.getUserName(), user.getPassword());
  }

  public boolean isModalOpen() {
    switchToAuthModalHandle();
    boolean isOpen = isAuthHeaderDisplayed();
    switchToMainWindowHandle();
    return isOpen;
  }

  private boolean isAuthHeaderDisplayed() {
    return wait.forElementVisible(header).isDisplayed();
  }

  public BaseAuthPage navigateToSignIn() {
    wait.forElementVisible(linkToSignInForm).click();
    return new SignInPage();
  }

  public void looseFocus() {
    switchToAuthModalHandle();
  }
}
