package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPageContext extends WikiBasePageObject {

  @FindBy(css = ".register-page .header-callout-link")
  private WebElement linkToSignInForm;
  @FindBy(css = ".signin-page .header-callout-link")
  private WebElement linkToSignUpForm;
  @FindBy(css = ".auth-header")
  private WebElement authHeader;
  @FindBy(css = ".second-card .auth-header")
  private WebElement secondCardHeader;

  private FacebookAuthContext fbAuthContext;

  public AuthPageContext() {
    fbAuthContext = new FacebookAuthContext();
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return fbAuthContext.clickFacebookSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    return fbAuthContext.isConnectWithFacebookButtonVisible();
  }

  public AttachedSignInPage navigateToSignIn() {
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
