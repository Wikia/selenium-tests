package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPills;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;

public abstract class ContributionEditor extends BasePageObject implements Editor {

  private final CategoryPills categoryPills;

  @Getter
  private By errorNotification = By.className("error");

  @Getter
  @FindBy(css = ".modal-dialog-posting-not-allowed.is-visible .modal-dialog")
  private WebElement signInDialog;

  @Getter
  @FindBy(css = ".discussion-image-upload__button input[type=file]")
  private WebElement uploadButton;

  private By confirmButton = By.className("confirm-button");

  private By signInButton = By.className("signin-button");

  protected WebElement getOkButtonInSignInDialog() {
    return getSignInDialog().findElement(confirmButton);
  }

  protected WebElement getSignInButtonInSignInDialog() {
    return getSignInDialog().findElement(signInButton);
  }

  public ContributionEditor clickOkButtonInSignInDialog() {
    waitAndClick(getOkButtonInSignInDialog());
    return this;
  }

  public ContributionEditor clickSignInButtonInSignInDialog() {
    waitAndClick(getSignInButtonInSignInDialog());
    return this;
  }

  private By imagePreview = By.className("post-image-inner-image");

  /**
   * Mobile and desktop editor have different class names, we need to find an image preview
   * inside of editor, and ignore images in existing posts
   * @return WebElement corresponding to image preview inside editor
   */
  protected WebElement getImagePreview() {
    return getEditor().findElement(imagePreview);
  }

  @Getter
  @FindBy(css = ".delete-image")
  private WebElement imageDeleteButton;

  @Getter
  @FindBy(css = ".editor-overlay-message .message-close")
  private WebElement guidelinesMessageCloseButton;

  public ContributionEditor() {
    this.categoryPills = new CategoryPills();
    categoryPills.setEmpty(false);
  }

  protected abstract WebElement getPostsCreator();
  protected abstract WebElement getEditor();
  protected abstract WebElement getTitleTextarea();

  public ContributionEditor click() {
    waitAndClick(getPostsCreator());
    return this;
  }

  @Override
  public ContributionEditor clickCancelButton() {
    waitAndClick(getCancelButton());
    return this;
  }

  @Override
  public ContributionEditor addTextWith(final String text) {
    getTextArea().sendKeys(text);
    return this;
  }

  public boolean isSignInDialogVisible() {
    return getSignInDialog().isDisplayed();
  }

  @Override
  public boolean isSubmitButtonActive() {
    return getSubmitButton().isEnabled();
  }

  @Override
  public WebElement getTextArea() {
    return getEditor().findElement(By.className("//TODO: fixme"));
  }

  @Getter
  private By openGraphContainer = By.className("og-container");

  @Getter
  private By openGraphText = By.className("og-texts");

  public boolean hasOpenGraph() {
    boolean result = false;
    final WebElement openGraphContainer = getEditor().findElement(getOpenGraphContainer());
    if (null != openGraphContainer) {
      result = null != openGraphContainer.findElement(getOpenGraphText());
    }
    return result;
  }

  public ContributionEditor closeGuidelinesMessage() {
    if (getGuidelinesMessageCloseButton().isDisplayed()) {
      getGuidelinesMessageCloseButton().click();
    }
    return this;
  }

  private By popover = By.className("pop-over-compass");

  private WebElement getCategoriesPopover() {
    return getEditor().findElement(popover);
  }

  private By addCategoryButton = By.className("discussion-category-picker-button");

  private WebElement getAddCategoryButton() {
    return getEditor().findElement(addCategoryButton);
  }
  public CategoryPills clickAddCategoryButton() {
    try {
      getAddCategoryButton().click();
      wait.forElementVisible(getCategoriesPopover());
      categoryPills.setEmpty(false);
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo("Category picker not found", e);
      categoryPills.setEmpty(true);
    }
    return categoryPills;
  }

  public ContributionEditor addTitleWith(final String text) {
    getTitleTextarea().sendKeys(text);
    return this;
  }

  public ContributionEditor clearTitle() {
    getTitleTextarea().clear();
    return this;
  }



  public ContributionEditor addDescriptionWithLink(final URL url) {
    getTextArea().sendKeys(String.format(" %s ", url.toString()));
    return this;
  }

  @Override
  public ContributionEditor clearText() {
    getTextArea().clear();
    return this;
  }

  @Override
  public ContributionEditor clickSubmitButton() {
    getSubmitButton().click();
    return this;
  }

  public ContributionEditor uploadImage() {
    getUploadButton().sendKeys(ContentLoader.getImage());
    wait.forElementVisible(getImagePreview());
    return this;
  }

  public String uploadUnsupportedImage() {
    getUploadButton().sendKeys(ContentLoader.getUnsupportedImage());
    return getEditor().findElement(getErrorNotification()).getText();
  }

  public ContributionEditor removeImage() {
    waitAndClick(getImageDeleteButton());
    wait.forElementNotVisible(getImagePreview());
    return this;
  }

  public ContributionEditor startPostCreation() {
    return startPostCreationWith(TextGenerator.defaultText());
  }

  public ContributionEditor startPostCreationWith(String description) {
    click()
      .closeGuidelinesMessage()
      .addTitleWith(TextGenerator.defaultText())
      .addTextWith(description)
      .clickAddCategoryButton()
      .selectFirstCategory();
    return this;
  }

  public ContributionEditor startPostCreationWithLink(URL link) {
    return startPostCreationWith(String.format(" %s ", link.toString()));
  }

}
