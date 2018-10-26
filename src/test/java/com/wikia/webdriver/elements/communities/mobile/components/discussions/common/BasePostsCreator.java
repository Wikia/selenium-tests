package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.category.CategoryPills;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.net.URL;

public abstract class BasePostsCreator extends BasePageObject implements PostsCreator {

  private final CategoryPills categoryPills;

  @Getter
  private By errorNotification = By.className("error");

  public BasePostsCreator() {
    this.categoryPills = new CategoryPills();
    categoryPills.setEmpty(false);
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

  protected abstract WebElement getPollPreview();

  protected abstract WebElement getUploadButton();

  protected abstract WebElement getAddPollButton();

  protected abstract WebElement getImageDeleteButton();

  protected abstract WebElement getOpenGraphDeleteButton();

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
      wait.forElementVisible(By.cssSelector(".discussion-category-picker .wds-dropdown__content"));
      categoryPills.setEmpty(false);
    } catch (NoSuchElementException e) {
      Log.info("Category picker not found", e);
      categoryPills.setEmpty(true);
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
  public PostsCreator addDescriptionWithLink(final String url) {
    getDescriptionTextarea().sendKeys(String.format(" %s ", url));
    return this;
  }

  @Override
  public PostsCreator clearDescription() {
    getDescriptionTextarea().clear();
    return this;
  }

  @Override
  public PostsCreator clearOpenGraph() {
    getOpenGraphDeleteButton().click();
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

  public Poll addPoll() {
    getAddPollButton().click();
    wait.forElementVisible(getPollPreview());
    return new Poll();
  }

  public String uploadUnsupportedImage() {
    getUploadButton().sendKeys(ContentLoader.getUnsupportedImage());
    return getEditor().findElement(getErrorNotification()).getText();
  }

  public BasePostsCreator removeImage() {
    waitAndClick(getImageDeleteButton());
    wait.forElementNotVisible(getImagePreview());
    return this;
  }

  public BasePostsCreator startPostCreation() {
    return startPostCreationWith(TextGenerator.defaultText());
  }

  public BasePostsCreator startPostCreationWithoutDescription() {
    click().closeGuidelinesMessage()
        .addTitleWith(TextGenerator.defaultText())
        .clickAddCategoryButton()
        .selectFirstCategory();
    return this;
  }

  public BasePostsCreator startPostCreationWith(String description) {
    startPostCreationWithoutDescription().addDescriptionWith(description);
    return this;
  }

  public BasePostsCreator startPostCreationWithLink(URL link) {
    return startPostCreationWith(String.format(" %s ", link.toString()));
  }

  public BasePostsCreator waitForErrorMessageNotVisible() {
    wait.forElementNotVisible(By.className("error"));

    return this;
  }
}
