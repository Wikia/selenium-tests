package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.CategoryResultType;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class VisualEditorOptionsDialog extends VisualEditorDialog {

  private static final int PAGESETTINGS = 0;
  private static final int CATEGORIES = 1;
  private static final int LANGUAGES = 2;

  @FindBy(css = ".oo-ui-outlineWidget li")
  private List<WebElement> outlineMenuItems;
  @FindBy(css = ".oo-ui-window-foot .oo-ui-labelElement-label")
  private WebElement applyChangesButton;
  @FindBy(css = ".ve-ui-mwCategoryWidget input")
  private WebElement categoriesInput;
  @FindBy(css = ".ve-ui-mwCategoryInputWidget-menu .oo-ui-optionWidget-selected")
  private WebElement selectedResult;
  @FindBy(css = ".ve-ui-mwCategoryItemWidget-button")
  private WebElement categoryItem;
  @FindBy(css = ".ve-ui-mwCategoryItemWidget-button")
  private List<WebElement> categoryItems;
  @FindBy(css = ".ve-ui-mwCategoryPopupWidget-content")
  private WebElement categoryPopUp;
  @FindBy(css = ".oo-ui-indicator-down")
  private WebElement categoryDownIndicator;
  @FindBy(css = ".ve-ui-mwCategoryPopupWidget-content .oo-ui-icon-remove")
  private WebElement categoryRemoveButton;
  @FindBy(css = ".ve-ui-mwCategoryInputWidget-menu div")
  private List<WebElement> categorySuggestions;

  private By labelElementBy = By.cssSelector(".oo-ui-labelElement-label");
  private By downIndicatorBy = By.cssSelector(".oo-ui-indicator-down");

  private String menuSectionItemText = "oo-ui-menuSectionOptionWidget";

  public VisualEditorOptionsDialog(WebDriver driver) {
    super(driver);
  }

  public void selectPageSettings() {
    waitForDialogVisible();
    selectFromOutlineMenu(PAGESETTINGS);
    PageObjectLogging.log("selectPageSettings", "Page settings is selected", true);
  }

  public void selectCategories() {
    waitForDialogVisible();
    selectFromOutlineMenu(CATEGORIES);
    PageObjectLogging.log("selectCategories", "Categories is selected", true);
  }

  public void selectLanguages() {
    waitForDialogVisible();
    selectFromOutlineMenu(LANGUAGES);
    PageObjectLogging.log("selectLanguages", "Languages is selected", true);
  }

  private void selectFromOutlineMenu(int index) {
    WebElement menuItem = outlineMenuItems.get(index).findElement(labelElementBy);
    waitForElementClickableByElement(menuItem);
    menuItem.click();
  }

  public VisualEditorPageObject clickApplyChangesButton() {
    waitForDialogVisible();
    waitForElementVisibleByElement(applyChangesButton);
    waitForElementClickableByElement(applyChangesButton);
    applyChangesButton.click();
    return new VisualEditorPageObject(driver);
  }

  public void addCategory(String cat) {
    waitForDialogVisible();
    typeCategory(cat);
    clickLinkResult();
    waitForElementByElement(categoryItem);
    PageObjectLogging.log("addCategory", "Category: " + cat + " is added", true, driver);
  }

  private void typeCategory(String cat) {
    waitForElementVisibleByElement(categoriesInput);
    categoriesInput.clear();
    categoriesInput.sendKeys(cat);
    PageObjectLogging.log("typeCategory", "Typed " + cat + " in the field", true, driver);
  }

  public void clickLinkResult() {
    waitForElementByElement(selectedResult);
    WebElement matchingResult = selectedResult.findElement(labelElementBy);
    waitForElementByElement(matchingResult);
    waitForElementClickableByElement(matchingResult);
    matchingResult.click();
  }

  public void removeCategory(String searchStr) {
    waitForDialogVisible();
    waitForElementByElement(categoryItem);
    WebElement elementToRemove = getElementByText(categoryItems, searchStr);
    WebElement categoryDownIndicator = elementToRemove.findElement(downIndicatorBy);
    categoryDownIndicator.click();
    waitForElementVisibleByElement(categoryPopUp);
    categoryRemoveButton.click();
    PageObjectLogging.log("removeCategory", "Category: " + searchStr + " is removed", true, driver);
  }

  public void addSortKeyToCategory(String cat, String key) {
    waitForDialogVisible();
    waitForElementByElement(categoryItem);
    WebElement elementFound = getElementByText(categoryItems, cat);
    WebElement categoryDownIndicator = elementFound.findElement(downIndicatorBy);
    categoryDownIndicator.click();
    waitForElementVisibleByElement(categoryPopUp);
    WebElement sortKeyInput = categoryPopUp.findElement(By.cssSelector("input"));
    sortKeyInput.sendKeys(key);
  }

  public List<WebElement> getLinkResults(String searchStr, CategoryResultType resultType) {
    String matchCategoryStr = null;
    List<WebElement> foundResults = new ArrayList<>();
    boolean isMatchingCategory = false;

    waitForDialogVisible();
    typeCategory(searchStr);

    switch (resultType) {
      case NEW:
        matchCategoryStr = "New category";
        break;
      case MATCHING:
        matchCategoryStr = "Matching categories";
        break;
      default:
        throw new NoSuchElementException("Non-existing result type selected");
    }

    for (int i = 0; i < categorySuggestions.size(); i++) {
      WebElement linkResult = categorySuggestions.get(i);
      String elementClassName = linkResult.getAttribute("class");
      if (elementClassName.contains(menuSectionItemText)) {
        String linkCategory = linkResult.findElement(labelElementBy).getText();
        if (linkCategory.equals(matchCategoryStr)) {
          isMatchingCategory = true;
        } else {
          isMatchingCategory = false;
        }
      } else {
        if (isMatchingCategory) {
          foundResults.add(linkResult);
        }
      }
    }

    if (foundResults.isEmpty()) {
      throw new NoSuchElementException(
          "No '" + matchCategoryStr + "' found with the search word: " + searchStr
      );
    }

    PageObjectLogging.log(
        "getLinkResults",
        "Found " + foundResults.size() + " result(s) under '" + matchCategoryStr + "'",
        true
    );
    return foundResults;
  }

  public void verifyLinkSuggestions(String searchStr, CategoryResultType resultType) {
    List<WebElement> results = getLinkResults(searchStr, resultType);
    waitForDialogVisible();
    for (WebElement result : results) {
      verifyLinkSuggestion(result, searchStr);
    }
  }

  private void verifyLinkSuggestion(WebElement linkResult, String searchStr) {
    String categoryStr = linkResult.findElement(labelElementBy).getAttribute("title");
    if (categoryStr.toLowerCase().contains(searchStr.toLowerCase())) {
      PageObjectLogging.log("getLinkResults", "Found type ahead suggestion: " + categoryStr, true);
    } else {
      throw new NoSuchElementException(
          searchStr + " is NOT found in type ahead suggestion: " + categoryStr);
    }
  }
}
