package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorDesktop extends BasePageObject {

  @FindBy (css = ".discussion-inline-editor-textarea-wrapper .discussion-textarea-wrapper")
  private WebElement postCreator;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy (css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @FindBy (css = ".discussion-inline-editor")
  private WebElement discussionEditor;

  @FindBy (css = ".discussion-inline-editor .discussion-inline-editor-submit")
  private WebElement submitButton;

  @FindBy (css = ".editor-overlay-message .message-close")
  private WebElement guidelinesMessageCloseButton;

  @FindBy (css = "#categoryPickerButtonDesktop")
  private WebElement addCategoryButton;

  @FindBy (css = ".discussion-inline-editor .discussion-textarea-with-counter")
  private WebElement titleTextarea;

  @FindBy (css = ".discussion-inline-editor textarea[required]")
  private WebElement descriptionTextarea;

  private final CategoryPills categoryPills;

  public PostsCreatorDesktop() {
    this.categoryPills = new CategoryPills();
  }

  public PostsCreatorDesktop clickPostCreator() {
    postCreator.click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return dialogSignIn.isDisplayed();
  }

  public boolean isExpanded() {
    return discussionEditor.getAttribute("class").contains("is-active");
  }

  public boolean isPostButtonActive() {
    return submitButton.isEnabled();
  }

  public PostsCreatorDesktop clickSubmitButton() {
    submitButton.click();
    return this;
  }

  public PostsCreatorDesktop waitForSpinnerToAppearAndDisappear() {
    final By spinner = By.cssSelector(".discussion-inline-editor svg.spinner");

    wait.forElementVisible(spinner);
    wait.forElementNotPresent(spinner);

    return this;
  }

  public PostsCreatorDesktop closeGuidelinesMessage() {
    if (guidelinesMessageCloseButton.isDisplayed()) {
      guidelinesMessageCloseButton.click();
    }
    return this;
  }

  public PostsCreatorDesktop fillTitleWith(String text) {
    titleTextarea.sendKeys(text);
    return this;
  }

  public PostsCreatorDesktop clearTitle() {
    titleTextarea.clear();
    return this;
  }

  public PostsCreatorDesktop fillDescriptionWith(String text) {
    descriptionTextarea.sendKeys(text);
    return this;
  }

  public PostsCreatorDesktop clearDescription() {
    descriptionTextarea.clear();
    return this;
  }

  public PostsCreatorDesktop clickOkButtonInSignInDialog() {
    okButtonInSignInDialog.click();
    return this;
  }

  public CategoryPills clickAddCategoryButton() {
    addCategoryButton.click();
    wait.forElementVisible(By.cssSelector(".discussion-inline-editor .pop-over-compass"));

    return categoryPills;
  }

  public PostsCreatorDesktop clickSignInButtonInSignInDialog() {
    signInButtonInSignInDialog.click();
    return this;
  }

}
