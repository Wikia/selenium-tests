package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.BasePostsCreator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorDesktop extends BasePostsCreator {

  @Getter
  @FindBy(css = ".inline-editor .editor-textarea")
  private WebElement postsCreator;

  @Getter
  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement signInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @Getter
  @FindBy(css = ".inline-editor")
  private WebElement editor;

  @Getter
  @FindBy(css = ".inline-editor .inline-editor-submit")
  private WebElement submitButton;

  @Getter
  @FindBy(css = ".editor-overlay-message .message-close")
  private WebElement guidelinesMessageCloseButton;

  @Getter
  @FindBy(css = "#categoryPickerButtonDesktop")
  private WebElement addCategoryButton;

  @Getter
  @FindBy(css = ".inline-editor .discussion-textarea-with-counter")
  private WebElement titleTextarea;

  @Getter
  @FindBy(css = ".inline-editor textarea[required]")
  private WebElement descriptionTextarea;

  @Getter
  @FindBy(css = ".discussion-image-upload__button input[type=file]")
  private WebElement uploadButton;

  @Getter
  @FindBy(css = ".inline-editor .post-image-inner-image")
  private WebElement imagePreview;

  @Getter
  @FindBy(css = ".delete-image")
  private WebElement imageDeleteButton;

  @Getter
  @FindBy(css = ".og-close")
  private WebElement openGraphDeleteButton;

  @Getter
  private By openGraphContainer = By.className("og-container");

  @Getter
  private By openGraphText = By.className("og-texts");

  @Override
  protected String getBaseCssClassName() {
    return "inline-editor";
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
