package com.wikia.webdriver.elements.communities.mobile.components.discussions.mobile;

import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.SortOption;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.BasePage;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FiltersPopOver extends BasePage {

  @Getter(lazy = true)
  private final CategoriesFieldset categoriesFieldset = new CategoriesFieldset();

  @FindBy(css = ".discussion-dropdown > .wds-dropdown")
  private WebElement filterButton;

  @Getter
  @FindBy(css = ".discussion-filters .discussion-sort")
  private WebElement sortingFilter;

  @FindBy(css = ".sort-options .sort-trending .sort-option-label")
  private List<WebElement>  trendingOptionInSortMenu;

  @FindBy(css = ".sort-options .sort-latest .sort-option-label")
  private List<WebElement> latestOptionInSortMenu;

  @FindBy(css = ".filters-apply")
  private WebElement applyButtonInSortMenu;

  public FiltersPopOver click() {
    waitAndClick(filterButton);
    return this;
  }

  public FiltersPopOver chooseSortingOption(SortOption option) {
    if (option == SortOption.LATEST ) {
      scrollAndClick(latestOptionInSortMenu.get(1));
    } else if (option == SortOption.TRENDING) {
      scrollAndClick(trendingOptionInSortMenu.get(1));
    } else {
      throw new IllegalArgumentException(String.format(
          "Option %s not supported by sorting",
          option
      ));
    }
    return this;
  }

  public FiltersPopOver clickApplyButton() {
    scrollAndClick(applyButtonInSortMenu);
    return this;
  }

  public boolean isSortingFilterEnabled() {
    return isElementEnabled(getSortingFilter());
  }
}
