package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.desktop.CategoryPills;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostsCreatorMobile extends BasePageObject {

  @FindBy(css = ".new-post")
  private WebElement postCreator;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .confirm-button")
  private WebElement okButtonInSignInDialog;

  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .signin-button")
  private WebElement signInButtonInSignInDialog;

  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-save-button")
  private WebElement submitButton;

  @FindBy(css = ".editor-overlay-message .message-close")
  private WebElement guidelinesMessageCloseButton;

  @FindBy(css = "#categoryPickerButtonMobile")
  private WebElement addCategoryButton;

  @FindBy(css = ".discussion-standalone-editor .discussion-standalone-editor-textarea:not([disabled])")
  private WebElement descriptionTextarea;

  private final CategoryPills categoryPills;

  public PostsCreatorMobile() {
    this.categoryPills = new CategoryPills();
  }

  public PostsCreatorMobile clickPostCreator() {
    wait.forElementClickable(postCreator);
    postCreator.click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return dialogSignIn.isDisplayed();
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

  public PostsCreatorMobile closeGuidelinesMessage() {
    if (guidelinesMessageCloseButton.isDisplayed()) {
      guidelinesMessageCloseButton.click();
    }
    return this;
  }

  public CategoryPills clickAddCategoryButton() {
    addCategoryButton.click();
    wait.forElementVisible(By.cssSelector(".discussion-standalone-editor .pop-over-compass"));

    return categoryPills;
  }

  public PostsCreatorMobile fillDescriptionWith(String text) {
    descriptionTextarea.sendKeys(text);
    return this;
  }

  public PostsCreatorMobile clickSubmitButton() {
    submitButton.click();
    return this;
  }

  public PostsCreatorMobile waitForSpinnerToAppearAndDisappear() {
    final By spinner = By.cssSelector(".discussion-standalone-editor svg.spinner");

    wait.forElementVisible(spinner);
    wait.forElementNotPresent(spinner);

    return this;
  }
}
