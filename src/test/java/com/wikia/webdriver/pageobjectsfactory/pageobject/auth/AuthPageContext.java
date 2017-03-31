package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPageContext extends WikiBasePageObject implements FacebookAuthContext {

  @FindBy(css = ".signup-provider-facebook")
  private WebElement facebookSignUpButton;
  @FindBy(css = ".signup-providers li a")
  private WebElement connectWithFacebookButton;
  @FindBy(css = ".register-page .header-callout-link")
  private WebElement linkToSignInForm;
  @FindBy(css = ".signin-page .header-callout-link")
  private WebElement linkToSignUpForm;
  @FindBy(css = ".auth-header")
  private WebElement authHeader;
  @FindBy(css = ".second-card .auth-header")
  private WebElement secondCardHeader;

  @Override public FacebookSignupModalComponentObject clickFacebookSignUp() {
    wait.forElementClickable(facebookSignUpButton).click();
    PageObjectLogging.log("clickFacebookSignUp", "clicked on sign up with facebok button", true);
    return new FacebookSignupModalComponentObject();
  }

  @Override public boolean isConnetctWithFacebookButtonVisible() {
    return wait.forElementVisible(connectWithFacebookButton).isDisplayed();
  }

  public SignInPage navigateToSignIn() {
    waitAndClick(linkToSignInForm);
    return new AttachedSignInPage();
  }

  public RegisterPage navigateToSignUp() {
    waitAndClick(linkToSignUpForm);
    return new AttachedRegisterPage();
  }

  public boolean isHeaderDisplayed() {
    return wait.forElementVisible(authHeader).isDisplayed();
  }

  public boolean confirmationDisplayed(String text) {
    return wait.forElementVisible(secondCardHeader).getText().contains(text);
  }

}
