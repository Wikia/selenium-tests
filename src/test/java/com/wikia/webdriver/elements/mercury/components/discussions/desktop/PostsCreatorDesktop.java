package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorDesktop extends BasePageObject {

  @FindBy (css = ".discussion-inline-editor-textarea-wrapper .discussion-textarea-wrapper")
  private WebElement postCreator;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @FindBy (css = ".discussion-inline-editor")
  private WebElement discussionEditor;

  @FindBy (css = ".discussion-inline-editor .discussion-inline-editor-submit")
  private WebElement editorSubmit;

  public PostsCreatorDesktop clickPostCreator() {
    postCreator.click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return dialogSignIn.isDisplayed();
  }

  public boolean isExpanded() {
    return discussionEditor.getAttribute("class").contains("is-active");
  }

  public boolean isPostButtonActive() {
    return editorSubmit.isEnabled();
  }

  public PostsCreatorDesktop clickOkButtonInSignInDialog() {
    okButtonInSignInDialog.click();
    return this;
  }

  public PostsCreatorDesktop clickSignInButtonInSignInDialog() {
    signInButtonInSignInDialog.click();
    return this;
  }

}
