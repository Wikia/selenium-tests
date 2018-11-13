package com.wikia.webdriver.elements.communities.desktop.components.categories;

import com.wikia.webdriver.elements.communities.desktop.pages.categories.CategoryExhibitionPage;
import com.wikia.webdriver.elements.communities.desktop.pages.categories.DynamicCategoryPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This component lets user choose category page layout, it's available only for logged in users
 */
public class CategoryLayoutSelector extends BasePageObject {

  @FindBy(css = "li[data-category-layout=mediawiki]")
  private WebElement classicViewButton;
  @FindBy(css = "li[data-category-layout=category-exhibition]")
  private WebElement categoryExhibitionButton;
  @FindBy(css = "li[data-category-layout=category-page3]")
  private WebElement dynamicViewButton;
  @FindBy(css = ".category-layout-selector")
  private WebElement ViewContainer;

  public static CategoryLayoutSelector getComponent() {
    //TODO: Chack if user is logged in, if not throw exception

    return new CategoryLayoutSelector();
  }

  private CategoryLayoutSelector() {

  }

  public boolean isCategoryExhibitionActive() {
    return categoryExhibitionButton.getAttribute("class").contains("is-active");
  }

  public boolean isDynamicViewActive() {
    return dynamicViewButton.getAttribute("class").contains("is-active");
  }

  public void switchToClassicView() {
    classicViewButton.click();
  }

  public CategoryExhibitionPage switchToCategoryExhibitionView() {
    categoryExhibitionButton.click();
    return new CategoryExhibitionPage();
  }

  public DynamicCategoryPage switchToDynamicCategoriesView() {
    dynamicViewButton.click();
    return new DynamicCategoryPage();
  }

  public boolean isVisible() {
    return isVisible(ViewContainer);
  }
}
