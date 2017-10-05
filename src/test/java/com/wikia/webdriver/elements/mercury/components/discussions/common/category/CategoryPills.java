package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.CheckForNull;
import java.util.List;
import java.util.Optional;

public class CategoryPills extends BasePageObject {

  @Setter
  private boolean isEmpty;

  @FindBy(css = ".pop-over-compass")
  private WebElement categoryPillsPopover;

  /**
   *
   * @param position - category position in pop over, counted from 0
   * @return category pill or null if category pill was not found
   */
  @CheckForNull
  public CategoryPill findCategoryOn(int position) {
    final List<WebElement> categoryPills = findCategoryPills();
    if (position < categoryPills.size()) {
      return new CategoryPill(categoryPills.get(position));
    } else {
      return null;
    }
  }

  public void selectFirstCategory() {
    if (!isEmpty) {
      findCategoryOn(0).click();
    }
  }

  private List<WebElement> findCategoryPills() {
    return categoryPillsPopover.findElements(By.tagName("a"));
  }

  public boolean hasCategory(final String categoryName) {
    return findCategoryPills().stream()
        .map(WebElement::getText)
        .anyMatch(name -> name.equals(categoryName));
  }
}
