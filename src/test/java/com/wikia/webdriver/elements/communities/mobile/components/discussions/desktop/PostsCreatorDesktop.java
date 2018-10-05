package com.wikia.webdriver.elements.communities.mobile.components.discussions.desktop;

import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.BasePostsCreator;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.PostsCreator;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostsCreatorDesktop extends BasePostsCreator {

  @Getter
  @FindBy(css = ".post-entry-point__items > li:nth-child(1)")
  private WebElement textPostType;

  /*
    PostCreator as editor header is a nasty hack. It's for pointless click, because Desktop and Mobile post creation
    are implemented by one interface. It should be split into two, since these flows are now completely different.
  */
  @Getter
  @FindBy(css = ".post-entry-point__header")
  private WebElement postsCreator;

  @Getter
  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement signInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @Getter
  @FindBy(css = ".inline-wrapper")
  private WebElement editor;

  @Getter
  @FindBy(css = ".inline-editor button[type='submit']")
  private WebElement submitButton;

  @Getter
  @FindBy(css = ".editor-overlay-message .message-close")
  private WebElement guidelinesMessageCloseButton;

  @Getter
  @FindBy(css = "#categoryPickerButtonDesktop")
  private WebElement addCategoryButton;

  @Getter
  @FindBy(css = ".editor-textarea-with-counter__wrapper textarea")
  private WebElement titleTextarea;

  @Getter
  @FindBy(css = ".inline-editor textarea[required], .ql-editor")
  private WebElement descriptionTextarea;

  @Getter
  @FindBy(css = ".discussion-image-upload__button input[type=file]")
  private WebElement uploadButton;

  @Getter
  @FindBy(css = ".poll-add-button")
  private WebElement addPollButton;

  @Getter
  @FindBy(css = ".poll-wrapper")
  private WebElement pollPreview;

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

  @Override
  public PostsCreator click() {
    wait.forElementClickable(getPostsCreator());
    getPostsCreator().click();
    wait.forElementClickable(getTextPostType());
    getTextPostType().click();

    return this;
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
