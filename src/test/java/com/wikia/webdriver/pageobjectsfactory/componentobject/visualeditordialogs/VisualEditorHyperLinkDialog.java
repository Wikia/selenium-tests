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

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorHyperLinkDialog extends VisualEditorDialog {

  @FindBy(css = ".oo-ui-icon-previous")
  private WebElement previousButton;
  @FindBy(css = ".ve-ui-mwLinkTargetInputWidget input")
  private WebElement linkInput;
  @FindBy(css = ".oo-ui-pendingElement-pending")
  private WebElement inputPending;
  @FindBy(css = ".oo-ui-optionWidget-selected")
  private WebElement selectedResult;
  @FindBy(css = ".ve-ui-desktopContext")
  private WebElement desktopContext;
  @FindBy(css = ".oo-ui-processDialog-title.oo-ui-labelElement")
  private WebElement title;
  @FindBy(css = ".oo-ui-window.ve-ui-inspector")
  private WebElement inspector;

  private By linkResultMenuBy = By.cssSelector(".ve-ui-mwLinkTargetInputWidget-menu");
  private By selectedResultBy = By.cssSelector(".oo-ui-optionWidget-selected span");
  private By linkCategoryBy =
      By.cssSelector(".oo-ui-menuSectionOptionWidget .oo-ui-labelElement-label");
  private By highlightedResultsBy = By.cssSelector(".oo-ui-optionWidget-highlighted");
  private By doneButtonBy = By.cssSelector(".oo-ui-window-foot .oo-ui-buttonElement-button");

  private String menuSectionItemText = "oo-ui-menuSectionItemWidget";

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
    return new VisualEditorPageObject(driver);
  }

  public void typeInLinkInput(String text) {
    waitForDialogVisible();
    waitForElementClickableByElement(linkInput);
    linkInput.sendKeys(text);
    waitForElementNotVisibleByElement(inputPending);
    waitForValueToBePresentInElementsAttributeByElement(linkInput, "value", text);
  }

  private void indexLinkCategories() {
    waitForElementNotVisibleByElement(inputPending);
    List<WebElement> linkCategories = driver.findElement(linkResultMenuBy).findElements(linkCategoryBy);
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

  public void isNewPage() {
    Assertion.assertTrue(isCategoryResult(NEW_PAGE_INDEX), "New page index not found");
  }

  public void isMatchingPage() {
    Assertion.assertTrue(isCategoryResult(MATCHING_PAGE_INDEX), "Matching page index not found");
  }

  public void isExternalLink() {
    Assertion.assertTrue(isCategoryResult(EXTERNAL_LINK_INDEX), "External link index not found");
  }

  public void isRedirectPage() {
    Assertion.assertTrue(isCategoryResult(REDIRECT_PAGE_INDEX), "Redirect page index not found");
  }

  public void verifyNewPageIsTop() {
    indexLinkCategories();
    Assertion.assertNumber(0, pageCategoryIndex[NEW_PAGE_INDEX],
                           "Checking New Page is on the top of the results.");
  }

  public void verifyMatchingPageIsTop() {
    indexLinkCategories();
    Assertion.assertNumber(0, pageCategoryIndex[MATCHING_PAGE_INDEX],
                           "Checking Matching Page is on the top of the results.");
  }

  public void verifyExternalLinkIsTop() {
    indexLinkCategories();
    Assertion.assertNumber(0, pageCategoryIndex[EXTERNAL_LINK_INDEX],
                           "Checking External Link is on the top of the results.");
  }

  public void verifyRedirectPageIsTop() {
    indexLinkCategories();
    Assertion.assertNumber(0, pageCategoryIndex[REDIRECT_PAGE_INDEX],
                           "Checking Redirect Page is on the top of the results.");
  }

  public void clickLinkResult() {
    waitForElementNotVisibleByElement(inputPending);
    waitForElementVisibleByElement(selectedResult);
    WebElement matchingResult = driver.findElement(selectedResultBy);
    waitForElementClickableByElement(matchingResult);
    matchingResult.click();
  }
}
