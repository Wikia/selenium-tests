package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPills;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.net.URL;

public abstract class ContributionEditor extends BasePageObject implements Editor {

  private final CategoryPills categoryPills;

  @Getter
  private By errorNotification = By.className("error");

  public ContributionEditor() {
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
  protected abstract WebElement getUploadButton();
  protected abstract WebElement getImageDeleteButton();
  protected abstract By getOpenGraphContainer();
  protected abstract By getOpenGraphText();

  public ContributionEditor click() {
    wait.forElementClickable(getPostsCreator());
    getPostsCreator().click();
    return this;
  }

  // TODO: implement me
  @Override
  public ContributionEditor clickCancelButton() {
    return this;
  }

  public boolean isSignInDialogVisible() {
    return getSignInDialog().isDisplayed();
  }

  @Override
  public boolean isSubmitButtonActive() {
    return getSubmitButton().isEnabled();
  }

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

  public CategoryPills clickAddCategoryButton() {
    try {
      getAddCategoryButton().click();
      wait.forElementVisible(By.cssSelector("." + getBaseCssClassName() + " .pop-over-compass"));
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

  @Override
  public ContributionEditor addTextWith(final String text) {
    getDescriptionTextarea().sendKeys(text);
    return this;
  }

  public ContributionEditor addDescriptionWithLink(final URL url) {
    getDescriptionTextarea().sendKeys(String.format(" %s ", url.toString()));
    return this;
  }

  @Override
  public ContributionEditor clearText() {
    getDescriptionTextarea().clear();
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
