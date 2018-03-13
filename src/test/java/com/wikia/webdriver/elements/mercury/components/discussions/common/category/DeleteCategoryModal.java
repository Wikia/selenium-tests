package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import com.wikia.webdriver.elements.mercury.pages.discussions.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteCategoryModal extends BasePage {

  private final CategoriesFieldset categoriesFieldset;

  @FindBy(css = ".discussion-categories-dialog .category-pill")
  private List<WebElement> categories;

  @FindBy(css = ".discussion-categories-dialog .confirm-button")
  private WebElement confirmButton;

  public DeleteCategoryModal(CategoriesFieldset categoriesFieldset) {
    super();
    this.categoriesFieldset = categoriesFieldset;
  }

  /**
   *
   * @param categoryName name of destination category
   * @return this object
   */
  public DeleteCategoryModal clickPill(final String categoryName) {
      categories.stream()
        .filter(element -> element.getText().equalsIgnoreCase(categoryName))
        .collect(Collectors.toList())
        .get(0)
        .click();

    return this;
  }

  public CategoriesFieldset clickConfirmButton() {
    confirmButton.click();
    return categoriesFieldset;
  }
}
