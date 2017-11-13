package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DiscussionsHeader extends BasePageObject {

  @FindBy(css = ".header-dropdown-button")
  private WebElement filterMenuButton;

  @Getter(lazy = true)
  private final FiltersPopOver filtersPopOver = new FiltersPopOver();

  public FiltersPopOver openFilterMenu() {
    waitAndClick(filterMenuButton);
    return getFiltersPopOver();
  }
}
