package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPageContext extends WikiBasePageObject {

  @FindBy(css = ".signup-provider-facebook")
  private WebElement facebookSignUpButton;
  @FindBy(css = ".signup-providers li a")
  private WebElement connectWithFacebookButton;
  @FindBy(css = "#loginSubmit:disabled")
  private WebElement disabledSignInButton;
  @FindBy(css = ".error")
  private WebElement errorMessage;
  @FindBy(className = "auth-header")
  private WebElement header;
  @FindBy(css = ".auth.desktop.signin-page")
  private WebElement signInAuthModal;
  @FindBy(css = ".auth.desktop.register-page")
  private WebElement registerAuthModal;
  @FindBy(css = ".register-page .header-callout-link")
  private WebElement linkToSignInForm;
  @FindBy(css = " header.auth-header")
  private WebElement registerHeader;


  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    wait.forElementClickable(facebookSignUpButton).click();
    PageObjectLogging.log("clickFacebookSignUp", "clicked on sign up with facebok button", true);
    return new FacebookSignupModalComponentObject();
  }

  public boolean isConnetctWithFacebookButtonVisible() {
    return wait.forElementVisible(connectWithFacebookButton).isDisplayed();
  }



  private final String mainWindowHandle;

  public AuthPageContext() {
    super();
    this.mainWindowHandle = driver.getWindowHandle();
    switchToAuthModalHandle();
  }


  private void switchToAuthModalHandle() {
    for (String winHandle : driver.getWindowHandles()) {
      if (!winHandle.equals(mainWindowHandle)) {
        driver.switchTo().window(winHandle);
      }
    }
  }

  public SignInPage navigateToSignIn() {
    wait.forElementVisible(linkToSignInForm).click();
    return new AttachedSignInPage();
  }

}
