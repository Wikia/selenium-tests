package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.common.BasePostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostsCreator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorMobile extends BasePostsCreator {

  @Getter
  @FindBy(css = ".new-post")
  private WebElement postsCreator;

  @Getter
  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement signInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

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
  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-textarea:not([disabled])")
  private WebElement descriptionTextarea;

  public PostsCreatorMobile() {
    super();
  }

  @Override
  protected String getBaseCssClassName() {
    return "discussion-standalone-editor";
  }

  @Override
  public PostsCreator waitForSpinnerToAppearAndDisappear() {
    final By success = By.cssSelector("." + getBaseCssClassName() + " svg.success");

    wait.forElementNotPresent(success);

    return this;
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
