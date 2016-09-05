package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.elements.mercury.components.discussions.desktop.CategoryPills;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BasePostsCreator extends BasePageObject implements PostsCreator {

  private final CategoryPills categoryPills;

  protected abstract String getBaseCssClassName();

  protected abstract WebElement getPostsCreator();

  protected abstract WebElement getSignInDialog();

  protected abstract WebElement getGuidelinesMessageCloseButton();

  protected abstract WebElement getDescriptionTextarea();

  protected abstract WebElement getAddCategoryButton();

  protected abstract WebElement getSubmitButton();

  public BasePostsCreator() {
    this.categoryPills = new CategoryPills();
  }

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
  public PostsCreator closeGuidelinesMessage() {
    if (getGuidelinesMessageCloseButton().isDisplayed()) {
      getGuidelinesMessageCloseButton().click();
    }
    return this;
  }

  @Override
  public CategoryPills clickAddCategoryButton() {
    getAddCategoryButton().click();
    wait.forElementVisible(By.cssSelector("." + getBaseCssClassName() + " .pop-over-compass"));

    return categoryPills;
  }

  @Override
  public PostsCreator fillDescriptionWith(String text) {
    getDescriptionTextarea().sendKeys(text);
    return this;
  }

  @Override
  public PostsCreator clickSubmitButton() {
    getSubmitButton().click();
    return this;
  }
}
