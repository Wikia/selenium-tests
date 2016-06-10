package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorMobile extends BasePageObject {

  @FindBy(css = ".new-post")
  private WebElement postCreator;

  @FindBy (css = ".modal-dialog-wrapper:not(.discussion-editor-dialog) .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy (css = ".modal-dialog-content + footer > :first-child")
  private WebElement okButtonInSignInDialog;

  @FindBy (css = ".modal-dialog-content + footer > :last-child")
  private WebElement signInButtonInSignInDialog;

  public PostsCreatorMobile clickPostCreator() {
    postCreator.click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return dialogSignIn.isDisplayed();
  }

  public PostsCreatorMobile clickOkButtonInSignInDialog() {
    okButtonInSignInDialog.click();
    return this;
  }

  public PostsCreatorMobile clickSignInButtonInSignInDialog() {
    signInButtonInSignInDialog.click();
    return this;
  }
}
