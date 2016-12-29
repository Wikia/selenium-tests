package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DeleteCategoryModal extends WikiBasePageObject {

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
   * Categories are indexed from 0, where 0 is "General"
   *
   * @param position - category position
   * @return this object
   */
  public DeleteCategoryModal clickPill(final int position) {
    categories.get(position).click();
    return this;
  }

  public CategoriesFieldset clickConfirmButton() {
    confirmButton.click();
    return categoriesFieldset;
  }
}
