package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FiltersPopOver extends WikiBasePageObject {

  @Getter(lazy = true)
  private final CategoriesFieldset categoriesFieldset = new CategoriesFieldset();

  @FindBy(css = ".discussion-header .icon.filter-default")
  private WebElement filterButton;

  @FindBy(css = "label[for='sort-button-main.sort-by-trending']")
  private WebElement trendingOptionInSortMenu;

  @FindBy(css = "label[for='sort-button-main.sort-by-latest']")
  private WebElement latestOptionInSortMenu;

  @FindBy(css = ".filters-apply")
  private WebElement applyButtonInSortMenu;

  @FindBy(css = ".pop-over-container")
  private WebElement sortOptionsMobile;

  public FiltersPopOver click() {
    filterButton.click();
    return this;
  }

  public boolean isSortListVisibleMobile() {
    wait.forElementVisible(sortOptionsMobile);
    return sortOptionsMobile.isDisplayed();
  }

  public FiltersPopOver clickLatestLinkOnMobile() {
    wait.forElementClickable(latestOptionInSortMenu);
    latestOptionInSortMenu.click();
    return this;
  }

  public FiltersPopOver clickTrendingOptionInSortMenu() {
    wait.forElementClickable(trendingOptionInSortMenu);
    trendingOptionInSortMenu.click();
    return this;
  }

  public FiltersPopOver clickApplyButton() {
    wait.forElementClickable(applyButtonInSortMenu);
    applyButtonInSortMenu.click();
    return this;
  }
}
