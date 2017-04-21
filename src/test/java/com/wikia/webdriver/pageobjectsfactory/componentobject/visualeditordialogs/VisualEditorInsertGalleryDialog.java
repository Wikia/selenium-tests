package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class VisualEditorInsertGalleryDialog extends VisualEditorDialog {

  @FindBy(css = ".ve-ui-wikiaSingleMediaQueryWidget input")
  private WebElement searchInput;
  @FindBy(css = ".oo-ui-processDialog-actions-primary .oo-ui-labelElement-label")
  private WebElement doneButton;
  @FindBy(css = ".oo-ui-clearableTextInputWidget-clearButton")
  private WebElement clearInputButton;
  @FindBy(css = ".oo-ui-window-body")
  private WebElement dialogBody;
  @FindBy(css = ".ve-ui-wikiaSingleMediaQueryWidget .oo-ui-pendingElement-pending")
  private WebElement queryPending;
  @FindBy(css = ".ve-ui-mwMediaResultWidget-done:nth-child(9)")
  private WebElement resultsList;

  //Cart
  @FindBy(css = ".ve-ui-wikiaSingleMediaCartOptionWidget")
  private List<WebElement> cartItems;

  private static final By
      MEDIA_RESULTS_WIDGET_BY =
      By.cssSelector(".ve-ui-wikiaMediaResultsWidget .oo-ui-selectWidget-depressed");
  private static final By MEDIA_RESULTS_BY = By.cssSelector(".ve-ui-mwMediaResultWidget-done");
  private static final By MEDIA_ADD_ICON_BY = By.cssSelector(".oo-ui-icon-unchecked");
  private static final By
      MEDIA_TITLES_BY =
      By.cssSelector(".ve-ui-mwMediaResultWidget-done>.oo-ui-labelElement-label");
  private static final By MEDIA_CHECKED_ICON_BY = By.cssSelector(".oo-ui-icon-checked");
  private static final By MEDIA_META_BY = By.cssSelector(".ve-ui-wikiaMediaOptionWidget-metaData");

  public VisualEditorInsertGalleryDialog(WebDriver driver) {
    super(driver);
  }

  private void typeInSearchTextField(String input) {
    wait.forElementClickable(searchInput);
    searchInput.sendKeys(input);
    PageObjectLogging.log("typeInSearchTextField", "Typed " + input + " in the search field", true);
  }

  private void clickAddGalleryButton() {
    wait.forElementVisible(doneButton);
    wait.forElementClickable(doneButton);
    doneButton.click();
  }

  public VisualEditorInsertGalleryDialog searchMedia(String searchText) {
    waitForDialogVisible();
    wait.forElementClickable(searchInput);
    searchInput.clear();
    typeInSearchTextField(searchText);
    waitForElementNotVisibleByElement(queryPending);
    wait.forElementVisible(resultsList);
    return new VisualEditorInsertGalleryDialog(driver);
  }

  public VisualEditorPageObject addExistingMedia(int number) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    wait.forElementVisible(mediaResultsWidget);
    List<WebElement> mediaResults = mediaResultsWidget.findElements(MEDIA_RESULTS_BY);
    //only selects available number of media
    for (int i = 0; i < Math.min(number, mediaResults.size()); i++) {
      WebElement mediaAddIcon = mediaResults.get(i).findElement(MEDIA_ADD_ICON_BY);
      mediaAddIcon.click();
    }
    clickAddGalleryButton();
    return new VisualEditorPageObject();
  }

  public void removeMediaFromCart(int number) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    wait.forElementVisible(mediaResultsWidget);
    List<WebElement> mediaResults = mediaResultsWidget.findElements(MEDIA_CHECKED_ICON_BY);
    for (int i = 0; i < number; i++) {
      mediaResults.get(i).click();
    }
  }

  public void verifyNumOfCartItems(int expected) {
    waitForDialogVisible();
    Assertion.assertNumber(cartItems.size(), expected, "Verify number of items in cart");
  }

  public void addMediaToCart(int number) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    wait.forElementVisible(mediaResultsWidget);
    List<WebElement> mediaResults = mediaResultsWidget.findElements(MEDIA_ADD_ICON_BY);
    for (int i = 0; i < number; i++) {
      mediaResults.get(i).click();
    }
  }

  public VisualEditorPageObject clickTitleToPreview(int index) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    wait.forElementVisible(mediaResultsWidget);
    WebElement targetMedia = mediaResultsWidget.findElements(MEDIA_TITLES_BY).get(index);
    scrollAndClick(targetMedia);
    return new VisualEditorPageObject();
  }

  public VisualEditorPageObject clickMetaDataToPreview(int index) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    wait.forElementVisible(mediaResultsWidget);
    WebElement targetMedia = mediaResultsWidget.findElements(MEDIA_META_BY).get(index);
    scrollAndClick(targetMedia);
    return new VisualEditorPageObject();
  }
}
