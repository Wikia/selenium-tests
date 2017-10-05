package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.BaseReplyCreator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ReplyCreatorDesktop extends BaseReplyCreator {

  @FindBy(css = ".discussion-inline-editor-floating-container .discussion-inline-editor-textarea")
  @Getter
  private WebElement replyCreatorTextArea;

  @FindBy(css = ".discussion-inline-editor-content-wrapper")
  @Getter
  private WebElement replyCreatorWrapper;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  @Getter
  private WebElement dialogSignIn;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  @Getter
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  @Getter
  private WebElement signInButtonInSignInDialog;

  @FindBy(css = ".editor-overlay-message .message-button")
  @Getter
  private WebElement guidelinesReadButton;

  @FindBy(css = ".discussion-inline-reply-editor .discussion-inline-editor-submit")
  @Getter
  private WebElement submitButton;

  @FindBy(css = ".discussion-inline-reply-editor .discussion-inline-editor-textarea")
  @Getter
  private WebElement textarea;

  @FindBy(css = ".wds-spinner__overlay .success")
  @Getter
  private WebElement loadingSuccess;

  @FindBy(css = ".discussion-inline-reply-editor")
  private WebElement editor;

  @Getter
  @FindBy(css = ".discussion-image-upload__button input[type=file]")
  private WebElement uploadButton;

  @Getter
  @FindBy(css = ".discussion-inline-editor .post-image-inner-image")
  private WebElement imagePreview;

  @Getter
  @FindBy(css = ".alert-notification")
  private WebElement alertNotification;

  @Getter
  @FindBy(css = ".delete-image")
  private WebElement imageDeleteButton;

  @Getter
  private By openGraphContainer = By.className("og-container");

  @Getter
  private By openGraphText = By.className("og-texts");

  @Override
  public boolean isPresent() {
    return !driver.findElements(By.cssSelector(".replies-list label:first-of-type")).isEmpty();
  }

}
