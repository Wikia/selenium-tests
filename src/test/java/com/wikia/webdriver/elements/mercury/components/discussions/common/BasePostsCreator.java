package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPills;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.net.URL;

public abstract class BasePostsCreator extends BasePageObject implements PostsCreator {

  private final CategoryPills categoryPills;

  public BasePostsCreator() {
    this.categoryPills = new CategoryPills();
  }

  protected abstract String getBaseCssClassName();
  protected abstract WebElement getPostsCreator();
  protected abstract WebElement getEditor();
  protected abstract WebElement getSignInDialog();
  protected abstract WebElement getGuidelinesMessageCloseButton();
  protected abstract WebElement getTitleTextarea();
  protected abstract WebElement getDescriptionTextarea();
  protected abstract WebElement getAddCategoryButton();
  protected abstract WebElement getSubmitButton();
  protected abstract WebElement getImagePreview();
  protected abstract WebElement getUploadButton();
  protected abstract WebElement getAlertNotification();
  protected abstract WebElement getImageDeleteButton();
  protected abstract By getOpenGraphContainer();
  protected abstract By getOpenGraphText();

  @Override
  public PostsCreator click() {
    wait.forElementClickable(getPostsCreator());
    getPostsCreator().click();
    return this;
  }

  @Override
  public boolean isSignInDialogVisible() {
    return getSignInDialog().isDisplayed();
  }


  @Override
  public boolean isPostButtonActive() {
    return getSubmitButton().isEnabled();
  }

  @Override
  public boolean hasOpenGraph() {
    boolean result = false;
    final WebElement openGraphContainer = getEditor().findElement(getOpenGraphContainer());
    if (null != openGraphContainer) {
      result = null != openGraphContainer.findElement(getOpenGraphText());
    }
    return result;
  }

  @Override
  public PostsCreator closeGuidelinesMessage() {
    if (getGuidelinesMessageCloseButton().isDisplayed()) {
      getGuidelinesMessageCloseButton().click();
    }
    return this;
  }

  @Override
  public CategoryPills clickAddCategoryButton() {
    try {
      getAddCategoryButton().click();
      wait.forElementVisible(By.cssSelector("." + getBaseCssClassName() + " .pop-over-compass"));
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo("Category picker not found", e.toString());
    }
    return categoryPills;
  }

  @Override
  public PostsCreator addTitleWith(final String text) {
    getTitleTextarea().sendKeys(text);
    return this;
  }

  @Override
  public PostsCreator clearTitle() {
    getTitleTextarea().clear();
    return this;
  }

  @Override
  public PostsCreator addDescriptionWith(final String text) {
    getDescriptionTextarea().sendKeys(text);
    return this;
  }

  @Override
  public PostsCreator addDescriptionWithLink(final URL url) {
    getDescriptionTextarea().sendKeys(String.format(" %s ", url.toString()));
    return this;
  }

  @Override
  public PostsCreator clearDescription() {
    getDescriptionTextarea().clear();
    return this;
  }

  @Override
  public PostsCreator clickSubmitButton() {
    getSubmitButton().click();
    return this;
  }

  public BasePostsCreator uploadImage() {
    getUploadButton().sendKeys(ContentLoader.getImage());
    wait.forElementVisible(getImagePreview());
    return this;
  }

  public String uploadUnsupportedImage() {
    getUploadButton().sendKeys(ContentLoader.getUnsupportedImage());
    wait.forElementVisible(getAlertNotification());
    return getAlertNotification().getText();
  }

  public BasePostsCreator removeImage() {
    waitAndClick(getImageDeleteButton());
    wait.forElementNotVisible(getImagePreview());
    return this;
  }

  public BasePostsCreator startPostCreation() {
    return startPostCreationWith(TextGenerator.defaultText());
  }

  public BasePostsCreator startPostCreationWith(String description) {
    click()
      .closeGuidelinesMessage()
      .addTitleWith(TextGenerator.defaultText())
      .addDescriptionWith(description)
      .clickAddCategoryButton()
      .selectFirstCategory();
    return this;
  }

  public BasePostsCreator startPostCreationWithLink(URL link) {
    return startPostCreationWith(String.format(" %s ", link.toString()));
  }

}
