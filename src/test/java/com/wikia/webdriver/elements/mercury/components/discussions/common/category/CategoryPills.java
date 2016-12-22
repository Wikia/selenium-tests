package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.CheckForNull;
import java.util.List;

public class CategoryPills extends BasePageObject {

  @FindBy(css = ".pop-over-compass")
  private WebElement categoryPillsPopover;

  /**
   *
   * @param categoryPosition - category position in pop over, counted from 0
   * @return category pill or null if category pill was not found
   */
  @CheckForNull
  public CategoryPill findCategoryOnPosition(int categoryPosition) {
    CategoryPill result = null;

    final List<WebElement> categoryPills = categoryPillsPopover.findElements(By.tagName("a"));
    if (categoryPosition < categoryPills.size()) {
      result = new CategoryPill(categoryPills.get(categoryPosition), categoryPosition);
    }

    return result;
  }
}
