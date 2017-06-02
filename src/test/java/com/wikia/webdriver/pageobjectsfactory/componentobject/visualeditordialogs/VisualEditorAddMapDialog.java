package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VisualEditorAddMapDialog extends VisualEditorDialog {

  @FindBy(css = ".ve-ui-wikiaMapInsertDialog-results-headline a .oo-ui-labeledElement-label")
  private WebElement createAMapButton;
  @FindBy(css = ".oo-ui-window-body")
  private WebElement mediaDialogBody;
  @FindBy(css = ".ve-ui-wikiaMapInsertDialog-empty-headline")
  private WebElement emptyStateDialogHeadline;
  @FindBy(css = ".ve-ui-wikiaMapInsertDialog-empty-text")
  private WebElement emptyStateDialogText;
  @FindBy(css = ".ve-ui-wikiaMapInsertDialog-results-headline-button .oo-ui-labelElement-label")
  private WebElement emptyStateCreateAMapButton;

  private By mediaResultsWidgetBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget");
  private By mediaResultsBy = By.cssSelector(".ve-ui-mwMediaResultWidget.map");

  public VisualEditorAddMapDialog(WebDriver driver) {
    super(driver);
  }

  public void clickLearnMoreLink() {
    //TODO return the correct page object
    //Goes to http://maps.wikia.com/wiki/Maps_Wiki
  }

  public CreateAMapComponentObject clickCreateAMapButton() {
    waitForDialogVisible();
    if (isElementOnPage(emptyStateCreateAMapButton)) {
      wait.forElementClickable(emptyStateCreateAMapButton);
      emptyStateCreateAMapButton.click();
      PageObjectLogging
          .log("clickCreateAMapButton", "Empty State: Create A Map button is clicked", true);
    } else {
      wait.forElementClickable(createAMapButton);
      createAMapButton.click();
      PageObjectLogging.log("clickCreateAMapButton", "Create A Map button is clicked", true);
    }
    switchToNewBrowserTab();
    return new CreateAMapComponentObject(driver);
  }

  public VisualEditorPageObject addExistingMap(int number) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = mediaDialogBody.findElement(mediaResultsWidgetBy);
    if (isElementOnPage(mediaResultsWidget)) {
      wait.forElementVisible(mediaResultsWidget);
      List<WebElement> maps = mediaResultsWidget.findElements(mediaResultsBy);
      WebElement map = maps.get(number);
      map.click();
    } else {
      throw new NoSuchElementException(
          "The dialog is in empty state, the wiki needs to contain map.");
    }
    waitForDialogNotVisible();
    return new VisualEditorPageObject();
  }

  public void checkIsEmptyState() {
    waitForDialogVisible();
    if (isElementOnPage(emptyStateDialogHeadline)) {
      wait.forElementVisible(emptyStateDialogHeadline);
      wait.forElementVisible(emptyStateDialogText);
      wait.forElementVisible(emptyStateCreateAMapButton);
      PageObjectLogging.log("checkIsEmptyState", "The Map dialog is in empty state", true, driver);
    } else {
      throw new NoSuchElementException(
          "The wiki is not in an empty state, the wiki contains maps.");
    }
    driver.switchTo().defaultContent();
  }

  public void verifyNumOfMaps(int num) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = mediaDialogBody.findElement(mediaResultsWidgetBy);
    wait.forElementVisible(mediaResultsWidget);
    List<WebElement> maps = mediaResultsWidget.findElements(mediaResultsBy);
    Assertion
        .assertEquals(maps.size(), num, "Expecting " + num + " of maps. Actual is " + maps.size());
    waitForDialogNotVisible();
  }
}
