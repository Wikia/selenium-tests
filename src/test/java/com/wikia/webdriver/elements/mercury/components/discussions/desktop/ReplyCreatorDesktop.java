package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.mobile.ReplyCreatorMobile;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ReplyCreatorDesktop extends BasePageObject {

  @FindBy(css = ".discussion-inline-editor-floating-container .discussion-inline-editor-textarea")
  private WebElement replyCreator;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @FindBy(css = ".editor-overlay-message .message-button")
  private WebElement guidelinesReadButton;

  @FindBy(css = ".discussion-inline-reply-editor .discussion-inline-editor-submit")
  private WebElement submitButton;

  @FindBy(css = ".discussion-inline-reply-editor .discussion-inline-editor-textarea")
  private WebElement textarea;

  public boolean isPresent() {
    return !driver.findElements(By.cssSelector(".replies-list label:first-of-type")).isEmpty();
  }

  public ReplyCreatorDesktop click() {
    replyCreator.click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return dialogSignIn.isDisplayed();
  }

  public ReplyCreatorDesktop clickOkButtonInSignInDialog() {
    okButtonInSignInDialog.click();
    return this;
  }


  public ReplyCreatorDesktop clickSignInButtonInSignInDialog() {
    signInButtonInSignInDialog.click();
    return this;
  }


  public ReplyCreatorDesktop clickGuidelinesReadButton() {
    guidelinesReadButton.click();
    return this;
  }

  public ReplyCreatorDesktop add(final String text) {
    textarea.sendKeys(text);
    return this;
  }

  public ReplyCreatorDesktop clickSubmitButton() {
    submitButton.click();
    return this;
  }
}
