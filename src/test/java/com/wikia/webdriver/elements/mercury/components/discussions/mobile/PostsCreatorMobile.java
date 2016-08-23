package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorMobile extends BasePageObject {

  @FindBy(css = ".new-post")
  private WebElement postCreator;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  public PostsCreatorMobile clickPostCreator() {
    wait.forElementClickable(postCreator);
    postCreator.click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return dialogSignIn.isDisplayed();
  }

  public PostsCreatorMobile clickOkButtonInSignInDialog() {
    wait.forElementClickable(okButtonInSignInDialog);
    okButtonInSignInDialog.click();
    return this;
  }

  public PostsCreatorMobile clickSignInButtonInSignInDialog() {
    wait.forElementClickable(signInButtonInSignInDialog);
    signInButtonInSignInDialog.click();
    return this;
  }
}
