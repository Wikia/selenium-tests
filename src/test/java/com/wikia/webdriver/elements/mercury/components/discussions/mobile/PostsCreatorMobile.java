package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.common.BasePostsCreator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorMobile extends BasePostsCreator {

  @Getter
  @FindBy(className = "new-post")
  private WebElement postsCreator;

  @Getter
  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement signInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @Getter
  @FindBy (className = "discussion-standalone-editor")
  private WebElement editor;

  @Getter
  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-save-button")
  private WebElement submitButton;

  @Getter
  @FindBy(css = ".editor-overlay-message .message-close")
  private WebElement guidelinesMessageCloseButton;

  @Getter
  @FindBy(css = "#categoryPickerButtonMobile")
  private WebElement addCategoryButton;

  @Getter
  @FindBy (css = ".discussion-standalone-editor .discussion-textarea-with-counter")
  private WebElement titleTextarea;

  @Getter
  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-textarea:not([disabled])")
  private WebElement descriptionTextarea;

  @Getter
  @FindBy(css = ".discussion-image-upload__button input[type=file]")
  private WebElement uploadButton;

  @Getter
  @FindBy(css = ".discussion-standalone-editor .post-image-inner-image")
  private WebElement imagePreview;

  @Getter
  @FindBy(css = ".alert-notification")
  private WebElement alertNotification;

  @Getter
  @FindBy(css = ".delete-image")
  private WebElement imageDeleteButton;

  public PostsCreatorMobile() {
    super();
  }

  @Override
  protected String getBaseCssClassName() {
    return "discussion-standalone-editor";
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
