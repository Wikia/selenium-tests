package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.GoogleSignupModalComponent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleAuthContext extends BasePageObject {

  @FindBy(css = ".signup-provider-google")
  private WebElement connectWithGoogleButton;

  protected GoogleSignupModalComponent clickGoogleSignUp() {
    wait.forElementClickable(connectWithGoogleButton).click();
    Log.log("clickGoogleSignUp", "clicked on sign up with google button", true);
    return new GoogleSignupModalComponent();
  }

  protected boolean isConnectWithGoogleButtonVisible() {
    return wait.forElementVisible(connectWithGoogleButton).isDisplayed();
  }
}
