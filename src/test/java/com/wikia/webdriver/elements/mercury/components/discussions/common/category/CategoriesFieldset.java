package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.CheckForNull;
import java.util.List;

public class CategoriesFieldset extends WikiBasePageObject {

  @FindBy(css = ".discussion-filters-fields-wrapper .discussion-categories")
  private WebElement fieldset;

  @FindBy(css = ".discussion-categories .discussion-categories-list > li")
  private List<WebElement> categories;


  public boolean canEdit() {
    WebElement svg = fieldset.findElement(By.cssSelector("use:first-of-type"));
    return svg.getAttribute("xlink:href").equals("#pencil");
  }

  @CheckForNull
  public CategoryPill clickCategory(final int position) {
    CategoryPill result = null;
    final int size = categories.size();

    if (size > position) {
      WebElement webElement = categories.get(position);
      result = new CategoryPill(webElement, position);
      webElement.click();
    } else {
      throw new IllegalArgumentException("You wanted to click category on position " + position + " but there is only " + size + " categories.");
    }

    return result;
  }

}
