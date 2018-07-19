package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookAuthContext extends BasePageObject {

  @FindBy(css = ".signup-provider-facebook")
  private WebElement connectWithFacebookButton;

  protected FacebookSignupModalComponentObject clickFacebookSignUp() {
    wait.forElementClickable(connectWithFacebookButton).click();
    Log.log("clickFacebookSignUp", "clicked on sign up with facebok button", true);
    return new FacebookSignupModalComponentObject();
  }

  protected boolean isConnectWithFacebookButtonVisible() {
    return wait.forElementVisible(connectWithFacebookButton).isDisplayed();
  }
}
