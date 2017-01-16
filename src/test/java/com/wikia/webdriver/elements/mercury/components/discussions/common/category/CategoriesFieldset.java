package com.wikia.webdriver.elements.mercury.components.discussions.common.category;
import com.google.common.collect.Iterables;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.CheckForNull;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesFieldset extends WikiBasePageObject {

  private static final String GENERAL_CATEGORY_NAME = "General";
  private static final String INPUT_TYPE_TEXT_SELECTOR = "input[type='text']";
  private static final String LOCAL_DELETE_COMMAND = "action-local-delete";
  private static final String DELETE_COMMAND = "action-delete";

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
    return "#pencil".equals(svg.getAttribute("xlink:href"));
  }

  @CheckForNull
  public CategoryPill findCategoryWith(final String categoryName) {
    return new CategoryPill(getCategoryWith(categoryName));
  }

  /**
   *
   * @param categoryName to match
   * @return first WebElement category that matches the name
   */
  private WebElement getCategoryWith(final String categoryName) {
    return categories.stream()
      .filter(element -> element.getText().equalsIgnoreCase(categoryName))
      .collect(Collectors.toList())
      .get(0);
  }

  public CategoriesFieldset clickCategoryWith(final String categoryName) {
    getCategoryWith(categoryName).click();
    return this;
  }

  public CategoriesFieldset clickEdit() {
    editLink.click();
    return this;
  }

  /**
   * For mobile "All" category is not visible, that is why it's always false - can not be edited.
   * TODO: please remember to change this line when SOC-3793 is done:
   * TODO: "isCategoryEditable(editableCategoryAll)"
   * @return true if "All" category can be edited
   */
  public boolean canEditAllCategory() {
    return !isMobile() && !editableCategoryAll
      .findElement(By.className("fancy-checkbox-span"))
      .getAttribute("class")
      .contains("disabled");
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
      final String value = element.findElement(By.cssSelector(INPUT_TYPE_TEXT_SELECTOR)).getAttribute("value");
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
    lastCategory.findElement(By.cssSelector(INPUT_TYPE_TEXT_SELECTOR)).sendKeys(categoryName);
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

  public CategoriesFieldset rename(final String oldCategoryName, final String newCategoryName) {
    WebElement category = getCategoryWith(oldCategoryName);
    WebElement input = category.findElement(By.cssSelector(INPUT_TYPE_TEXT_SELECTOR));
    input.clear();
    input.sendKeys(newCategoryName);
    return this;
  }

  public String getInfoMessageText() {
    return infoMessage.getText();
  }

  public CategoriesFieldset removeTemporaryCategory(final String categoryName) {
    return removeCategory(categoryName, LOCAL_DELETE_COMMAND);
  }

  public DeleteCategoryModal removeCategory(final String categoryName) {
    removeCategory(categoryName, DELETE_COMMAND);
    return new DeleteCategoryModal(this);
  }

  private CategoriesFieldset removeCategory(final String categoryName, final String actionName) {
    final WebElement element = findEditableCategoryWith(categoryName);
    if (null != element) {
      element.click();
      element.findElement(By.className(actionName)).click();
    }

    return this;
  }

}
