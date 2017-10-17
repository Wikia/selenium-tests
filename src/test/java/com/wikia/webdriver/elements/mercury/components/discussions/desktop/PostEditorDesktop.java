package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.ContributionEditor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostEditorDesktop extends ContributionEditor {

  @Getter
  @FindBy(css = ".discussion-inline-editor-textarea-wrapper .discussion-textarea-wrapper")
  private WebElement postsCreator;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @Getter
  @FindBy(css = ".discussion-inline-editor")
  private WebElement editor;

  @Getter
  @FindBy(css = ".discussion-inline-editor .discussion-inline-editor-submit")
  private WebElement submitButton;

  @Getter
  @FindBy(css = ".editor-overlay-message .message-close")
  private WebElement guidelinesMessageCloseButton;

  @Getter
  @FindBy(css = "#categoryPickerButtonDesktop")
  private WebElement addCategoryButton;

  @Getter
  @FindBy(css = ".discussion-inline-editor textarea[required]")
  private WebElement descriptionTextarea;

  @Getter
  private By openGraphContainer = By.className("og-container");

  @Getter
  private By openGraphText = By.className("og-texts");

  public boolean isExpanded() {
    return editor.getAttribute("class").contains("is-active");
  }

  public boolean isSticky() {
    return editor.getAttribute("class").contains("is-sticky");
  }

  public PostEditorDesktop clickOkButtonInSignInDialog() {
    okButtonInSignInDialog.click();
    return this;
  }

  public PostEditorDesktop clickSignInButtonInSignInDialog() {
    signInButtonInSignInDialog.click();
    return this;
  }

  @Override
  public WebElement getCancelButton() {
    throw new UnsupportedOperationException("Desktop editors don't have cancel buttons");
  }
}
