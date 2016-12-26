package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.BasePostsCreator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorDesktop extends BasePostsCreator {

  @Getter
  @FindBy (css = ".discussion-inline-editor-textarea-wrapper .discussion-textarea-wrapper")
  private WebElement postsCreator;

  @Getter
  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement signInDialog;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @Getter
  @FindBy (css = ".discussion-inline-editor")
  private WebElement editor;

  @Getter
  @FindBy (css = ".discussion-inline-editor .discussion-inline-editor-submit")
  private WebElement submitButton;

  @Getter
  @FindBy (css = ".editor-overlay-message .message-close")
  private WebElement guidelinesMessageCloseButton;

  @Getter
  @FindBy (css = "#categoryPickerButtonDesktop")
  private WebElement addCategoryButton;

  @Getter
  @FindBy (css = ".discussion-inline-editor .discussion-textarea-with-counter")
  private WebElement titleTextarea;

  @Getter
  @FindBy (css = ".discussion-inline-editor textarea[required]")
  private WebElement descriptionTextarea;

  public PostsCreatorDesktop() {
    super();
  }

  @Override
  protected String getBaseCssClassName() {
    return "discussion-inline-editor";
  }

  public boolean isExpanded() {
    return editor.getAttribute("class").contains("is-active");
  }

  public boolean isSticky() {
    return editor.getAttribute("class").contains("is-sticky");
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
