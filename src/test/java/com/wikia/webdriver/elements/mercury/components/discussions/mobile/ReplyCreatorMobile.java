package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.common.BaseReplyCreator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ReplyCreatorMobile extends BaseReplyCreator {

  @FindBy(css = ".discussion-editor-entry-point-container .discussion-editor-entry-point-content")
  @Getter
  private WebElement replyCreator;

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

  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-save-button")
  @Getter
  private WebElement submitButton;

  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-textarea:not([disabled])")
  @Getter
  private WebElement textarea;

  @Override
  public boolean isPresent() {
    return !driver.findElements(By.className("discussion-editor-entry-point-container")).isEmpty();
  }
}
