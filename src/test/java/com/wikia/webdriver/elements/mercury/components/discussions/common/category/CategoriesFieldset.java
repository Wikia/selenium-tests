package com.wikia.webdriver.elements.mercury.components.discussions.common.category;

import com.google.common.collect.Iterables;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.CheckForNull;
import java.util.List;
import java.util.function.Function;

public class CategoriesFieldset extends WikiBasePageObject {

  private static final String GENERAL_CATEGORY_NAME = "General";

  @FindBy(className = "discussion-categories")
  private WebElement fieldset;

  @FindBy(css = ".discussion-categories > legend.mobile-hidden")
  private WebElement label;

  @FindBy(className = "discussion-categories-edit-link")
  private WebElement editLink;

  @FindBy(css = ".discussion-categories .discussion-categories-list > li")
  private List<WebElement> categories;

  @FindBy(className = "discussion-categories-edit")
  private WebElement categoriesEdit;

  @FindBy(className = "discussion-category-all")
  private WebElement editableCategoryAll;

  @FindBy(css = ".discussion-categories-list .sortable-item")
  private List<WebElement> editableCategories;

  @FindBy(className = "discussion-categories-edit-add-link")
  private WebElement addCategoryLink;

  @FindBy(css = ".discussion-categories-edit button.submit")
  private WebElement approveChangesButton;

  @FindBy(className = "categories-edit-info-message")
  private WebElement infoMessage;

  public boolean canEdit() {
    WebElement svg = fieldset.findElement(By.tagName("use"));
    return svg.getAttribute("xlink:href").equals("#pencil");
  }

  @CheckForNull
  public CategoryPill findCategoryWith(final String categoryName) {
    CategoryPill result = null;

    for (int i = 0; i < categories.size() && result == null; i++) {
      WebElement category = categories.get(i);
      if (category.getText().equals(categoryName)) {
        result = new CategoryPill(category, i);
      }
    }

    return result;
  }

  /**
   * @param position - category position, first category is 0 - All
   * @return category pill
   * @throws IllegalArgumentException when category is not found
   */
  public CategoryPill clickCategoryAt(final int position) {
    return withBoundaryCheck(categories, position, webElement -> {
      final CategoryPill result = new CategoryPill(webElement, position);
      webElement.click();
      return result;
    });
  }

  private <T> T withBoundaryCheck(final List<WebElement> elements, final int position, Function<WebElement, T> transform) {
    final int size = elements.size();

    if (size > position) {
      return transform.apply(elements.get(position));
    } else {
      throw new IllegalArgumentException("You wanted to click category on position " + position + " but there is only " + size + " categories.");
    }
  }

  public CategoriesFieldset clickEdit() {
    editLink.click();
    return this;
  }

  /**
   * For mobile "All" category is not visible, that is why it's always false - can not be edited.
   *
   * @return true if "All" category can be edited
   */
  public boolean canEditAllCategory() {
    return isMobile() ? false
        // please remember to change this line when SOC-3793 is done - "isCategoryEditable(editableCategoryAll)"
        : !editableCategoryAll.findElement(By.className("fancy-checkbox-span")).getAttribute("class").contains("disabled");
  }

  private boolean isMobile() {
    return !label.isDisplayed();
  }

  public boolean canEditGeneralCategory() {
    return isCategoryEditable(findEditableCategoryWith(GENERAL_CATEGORY_NAME));
  }

  private WebElement findEditableCategoryWith(final String categoryName) {
    WebElement result = null;

    for (WebElement element : editableCategories) {
      final String value = element.findElement(By.cssSelector("input[type='text']")).getAttribute("value");
      if (value.equals(categoryName)) {
        result = element;
      }
    }

    return result;
  }

  private boolean isCategoryEditable(WebElement webElement) {
    return webElement.findElement(By.tagName("input")).isEnabled();
  }

  public boolean canAddCategory() {
    return !this.addCategoryLink.getAttribute("class").contains("active-element-disabled-theme-color");
  }

  public CategoriesFieldset addCategory(final String categoryName) {
    this.addCategoryLink.click();
    WebElement lastCategory = Iterables.getLast(this.editableCategories);
    if (!lastCategory.getText().trim().isEmpty()) {
      throw new IllegalStateException("Just created category should not contain text.");
    }
    lastCategory.findElement(By.cssSelector("input[type='text']")).sendKeys(categoryName);
    return this;
  }

  public CategoriesFieldset clickApproveButton() {
    approveChangesButton.click();
    return this;
  }

  public boolean hasCategory(final String categoryName) {
    return categories.stream()
        .map(WebElement::getText)
        .anyMatch(name -> name.equals(categoryName));
  }

  public CategoriesFieldset rename(final int position, final String newCategoryName) {
    withBoundaryCheck(editableCategories, position, webElement -> {
      webElement.click();
      WebElement input = webElement.findElement(By.cssSelector("input[type='text']"));
      input.clear();
      input.sendKeys(newCategoryName);
      return null;
    });

    return this;
  }

  public String getInfoMessageText() {
    return infoMessage.getText();
  }

  public CategoriesFieldset removeTemporaryCategory(final String categoryName) {
    return removeCategory(categoryName, "action-local-delete");
  }

  private CategoriesFieldset removeCategory(final String categoryName, final String actionName) {
    final WebElement element = findEditableCategoryWith(categoryName);
    if (null != element) {
      element.click();
      element.findElement(By.className(actionName)).click();
    }

    return this;
  }

  public DeleteCategoryModal removeCategory(final String categoryName) {
    removeCategory(categoryName, "action-delete");
    return new DeleteCategoryModal(this);
  }
}
