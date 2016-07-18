package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ReplyCreatorMobile extends BasePageObject {

  @FindBy(css = ".discussion-editor-entry-point-container .discussion-editor-entry-point-content")
  private WebElement replyCreator;

  @FindBy(css = ".modal-dialog-wrapper:not(.discussion-editor-dialog) .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy(css = ".modal-dialog-content + footer > :first-child")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-content + footer > :last-child")
  private WebElement signInButtonInSignInDialog;

  public ReplyCreatorMobile clickReplyCreator() {
    replyCreator.click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return dialogSignIn.isDisplayed();
  }

  public ReplyCreatorMobile clickOkButtonInSignInDialog() {
    okButtonInSignInDialog.click();
    return this;
  }

  public ReplyCreatorMobile clickSignInButtonInSignInDialog() {
    signInButtonInSignInDialog.click();
    return this;
  }

}
