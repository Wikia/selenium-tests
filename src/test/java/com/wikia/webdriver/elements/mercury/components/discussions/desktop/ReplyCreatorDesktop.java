package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.BaseReplyCreator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ReplyCreatorDesktop extends BaseReplyCreator {

  @FindBy(css = ".discussion-inline-editor-floating-container .discussion-inline-editor-textarea")
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

  @FindBy(css = ".discussion-inline-reply-editor .discussion-inline-editor-submit")
  @Getter
  private WebElement submitButton;

  @FindBy(css = ".discussion-inline-reply-editor .discussion-inline-editor-textarea")
  @Getter
  private WebElement textarea;

  @FindBy(css = ".discussion-inline-reply-editor")
  private WebElement editor;

  @Override
  public boolean isPresent() {
    return !driver.findElements(By.cssSelector(".replies-list label:first-of-type")).isEmpty();
  }

  @Override
  public int getEditorHeight() {
    return wait.forElementVisible(editor).getSize().getHeight();
  }
}
