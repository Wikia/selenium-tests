package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VisualEditorHyperLinkDialog extends VisualEditorDialog {

  @FindBy(css = ".ve-ui-mwLinkTargetInputWidget input")
  private WebElement linkInput;
  @FindBy(css = ".oo-ui-pendingElement-pending")
  private WebElement inputPending;
  @FindBy(css = ".oo-ui-optionWidget-selected")
  private WebElement selectedResult;

  private By linkResultMenuBy = By.cssSelector(".ve-ui-mwLinkTargetInputWidget-menu");
  private By selectedResultBy = By.cssSelector(".oo-ui-optionWidget-selected span");
  private By linkCategoryBy = By
      .cssSelector(".oo-ui-menuSectionOptionWidget .oo-ui-labelElement-label");
  private By doneButtonBy = By.cssSelector(".oo-ui-window-foot .oo-ui-buttonElement-button");

  private int[] pageCategoryIndex = new int[4];
  private static final int NEW_PAGE_INDEX = 0;
  private static final int MATCHING_PAGE_INDEX = 1;
  private static final int EXTERNAL_LINK_INDEX = 2;
  private static final int REDIRECT_PAGE_INDEX = 3;

  public VisualEditorHyperLinkDialog(WebDriver driver) {
    super(driver);
    pageCategoryIndex[NEW_PAGE_INDEX] = -1;
    pageCategoryIndex[MATCHING_PAGE_INDEX] = -1;
    pageCategoryIndex[EXTERNAL_LINK_INDEX] = -1;
    pageCategoryIndex[REDIRECT_PAGE_INDEX] = -1;
  }

  public VisualEditorPageObject clickDoneButton() {
    waitForDialogVisible();
    WebElement doneButton = dialog.findElement(doneButtonBy);
    doneButton.click();
    waitForDialogNotVisible();
    return new VisualEditorPageObject();
  }

  public void typeInLinkInput(String text) {
    waitForDialogVisible();
    wait.forElementClickable(linkInput);
    linkInput.sendKeys(text);
    waitForElementNotVisibleByElement(inputPending);
    waitForValueToBePresentInElementsAttributeByElement(linkInput, "value", text);
  }

  private void indexLinkCategories() {
    waitForElementNotVisibleByElement(inputPending);
    List<WebElement> linkCategories =
        driver.findElement(linkResultMenuBy).findElements(linkCategoryBy);
    for (int i = 0; i < linkCategories.size(); i++) {
      String categoryName = linkCategories.get(i).getAttribute("title");
      switch (categoryName) {
        case "New page":
          pageCategoryIndex[NEW_PAGE_INDEX] = i;
          break;
        case "Matching page":
          pageCategoryIndex[MATCHING_PAGE_INDEX] = i;
          break;
        case "Matching pages":
          pageCategoryIndex[MATCHING_PAGE_INDEX] = i;
          break;
        case "Redirect page":
          pageCategoryIndex[REDIRECT_PAGE_INDEX] = i;
          break;
        case "External link":
          pageCategoryIndex[EXTERNAL_LINK_INDEX] = i;
          break;
        default:
          throw new NoSuchElementException("Non-existing link category selected");
      }
    }
    PageObjectLogging.log("viewResults", "Category indexes sorted", true);
  }

  private boolean isCategoryResult(int category) {
    indexLinkCategories();
    return (pageCategoryIndex[category] == -1);
  }

  public void verifyNewPageIsTop() {
    indexLinkCategories();
    Assertion.assertNumber(pageCategoryIndex[NEW_PAGE_INDEX], 0,
        "Checking New Page is on the top of the results.");
  }

  public void verifyMatchingPageIsTop() {
    indexLinkCategories();
    Assertion.assertNumber(pageCategoryIndex[MATCHING_PAGE_INDEX], 0,
        "Checking Matching Page is on the top of the results.");
  }

  public void verifyExternalLinkIsTop() {
    indexLinkCategories();
    Assertion.assertNumber(pageCategoryIndex[EXTERNAL_LINK_INDEX], 0,
        "Checking External Link is on the top of the results.");
  }

  public void clickLinkResult() {
    waitForElementNotVisibleByElement(inputPending);
    wait.forElementVisible(selectedResult);
    WebElement matchingResult = driver.findElement(selectedResultBy);
    wait.forElementClickable(matchingResult);
    matchingResult.click();
  }
}
