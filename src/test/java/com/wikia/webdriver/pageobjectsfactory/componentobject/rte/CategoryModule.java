package com.wikia.webdriver.pageobjectsfactory.componentobject.rte;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory.EditCategoryComponentObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Situated in the right rail of the edit page, the Category module wraps category related features.
 * It provides autocompletion for new categories and allows to edit or remove existing ones.
 */
public class CategoryModule {

  @FindBy(css = ".module_categories h3")
  private WebElement categoryModuleHeader;
  @FindBy(id = "CategorySelect")
  private WebElement categorySelectModule;
  @FindBy(id = "CategorySelectInput")
  private WebElement categorySelectInput;
  @FindBy(className = "category")
  private List<WebElement> categoryList;

  private final By autoCompleteSuggestion = By.className("ui-menu-item");
  private final By editCategoryIcon = By.className("edit");
  private final By removeCategoryIcon = By.className("delete");
  private final By categoryNamesBy = By.cssSelector(".category .name");

  private final WebDriver webDriver;
  private final WebDriverWait webDriverWait;
  private final Actions actions;

  public CategoryModule(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
    this.webDriver = webDriver;
    webDriverWait = new WebDriverWait(webDriver, 5);
    actions = new Actions(webDriver);
  }

  /**
   * Type the given category name into the new category input field
   * @param categoryName the category name to input
   */
  public void typeCategoryName(String categoryName) {
    actions.click(categoryModuleHeader).perform();
    actions.moveToElement(categorySelectInput).perform();

    categorySelectInput.sendKeys(categoryName);
    PageObjectLogging.log("typeCategoryName", categoryName + " typed", true);
  }

  /**
   * Activate category suggestions based on the current content of the new category input field.
   * The first item will be selected and added as a new category.
   *
   * @return the name of the newly added category from suggestions
   */
  public String selectFirstSuggestedCategory() {
    WebElement suggestion = categorySelectModule.findElement(autoCompleteSuggestion);
    String categoryName = suggestion.getText().trim();

    suggestion.click();

    PageObjectLogging.log("selectCategorySuggestions", "category selected from suggestions", true);

    return categoryName;
  }

  /**
   * Add the category that is currently specified in the new category input field to the page.
   */
  public void saveCategory() {
    categorySelectInput.sendKeys(Keys.ENTER);
  }

  /**
   * Get the current list of categories this page belongs to
   * @return a list of category names
   */
  public List<String> getCategoryList() {
    return categoryList.stream()
        .map(WebElement::getText)
        .map(String::trim)
        .collect(Collectors.toList());
  }

  /**
   * Launch a modal window to edit an existing category based on its name.
   *
   * @param categoryName category name to search for among the categories this page belongs to
   * @return wrapper for category editing modal
   */
  public EditCategoryComponentObject editExistingCategory(String categoryName) {
    clickOnIconForCategory(categoryName, editCategoryIcon);

    return new EditCategoryComponentObject(webDriver);
  }

  /**
   * Remove a category this page belongs to
   * @param categoryName name of the category to remove
   */
  public void removeExistingCategory(String categoryName) {
    clickOnIconForCategory(categoryName, removeCategoryIcon);

    PageObjectLogging.log("removeCategory", "remove category button clicked on category "
                                            + categoryName, true);

    // due to animation on category removal, we need to wait here to avoid false negatives
    webDriverWait
        .until(ExpectedConditions.invisibilityOfElementWithText(categoryNamesBy, categoryName));
  }

  private void clickOnIconForCategory(String categoryName, By iconSelector) {
    WebElement resultCategory = categoryList.stream()
        .filter(category -> category.getText().equals(categoryName))
        .findFirst()
        .orElseThrow(NoSuchElementException::new);

    actions.moveToElement(resultCategory).perform();

    resultCategory.findElement(iconSelector).click();
  }
}
