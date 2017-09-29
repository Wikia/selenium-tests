package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.common.BaseReplyCreator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ReplyCreatorMobile extends BaseReplyCreator {

  @FindBy(css = ".discussion-editor-entry-point-container .discussion-editor-entry-point-content")
  private WebElement replyCreatorTextArea;

  @FindBy(css = ".discussion-standalone-content-wrapper")
  @Getter
  private WebElement replyCreatorWrapper;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @FindBy(css = ".editor-overlay-message .message-button")
  private WebElement guidelinesReadButton;

  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-save-button")
  private WebElement submitButton;

  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-textarea:not([disabled])")
  private WebElement textarea;

  @FindBy(css = ".wds-spinner__stroke")
  @Getter
  private WebElement loadingSuccess;

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

  @Override
  public boolean isPresent() {
    return !driver.findElements(By.className("discussion-editor-entry-point-container")).isEmpty();
  }

}
