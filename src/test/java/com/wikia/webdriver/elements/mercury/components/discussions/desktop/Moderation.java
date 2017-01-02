package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Moderation extends BasePageObject {

  @Getter(lazy = true)
  private final CategoriesFieldset categoriesFieldset = new CategoriesFieldset();

  @FindBy(className = "moderation-checkbox-label")
  private WebElement showOnlyReportedContentCheckbox;

  public Moderation checkShowOnlyReportedContentCheckbox() {
    showOnlyReportedContentCheckbox.click();
    return this;
  }
}
