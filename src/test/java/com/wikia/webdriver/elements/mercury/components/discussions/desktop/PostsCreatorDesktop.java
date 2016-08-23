package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


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

  @FindBy (css = ".editor-overlay-message .message-close")
  private WebElement editorGuidelinesMessageCLoseButton;

  @FindBy (css = ".discussion-inline-editor textarea[required]")
  private WebElement editorContentTextarea;

  @FindBy (css = ".discussion-inline-editor #categoryPickerButtonDesktop")
  private WebElement addCategoryButton;

  @FindBy (css = ".discussion-inline-editor .pop-over-compass")
  private WebElement categoryPillsPopover;

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

  public PostsCreatorDesktop clickSubmitButton() {
    editorSubmit.click();
  }

  public PostsCreatorDesktop closeGuidelinesMessage() {
    if (editorGuidelinesMessageCLoseButton.isDisplayed()) {
      editorGuidelinesMessageCLoseButton.click();
    }
    return this;
  }

  public PostsCreatorDesktop fillPostContent(String text) {
    editorContentTextarea.sendKeys(text);
    return this;
  }

  public PostsCreatorDesktop clickOkButtonInSignInDialog() {
    okButtonInSignInDialog.click();
    return this;
  }

  public PostsCreatorDesktop selectCategory(int categoryNumber) {
    addCategoryButton.click();

    wait.forElementVisible(categoryPillsPopover);

    List<WebElement> categories = categoryPillsPopover.findElements(By.tagName("a"));

    if (categoryNumber < categories.size()) {
      categories.get(categoryNumber).click();
    }

    return this;
  }

  public PostsCreatorDesktop clickSignInButtonInSignInDialog() {
    signInButtonInSignInDialog.click();
    return this;
  }

}
