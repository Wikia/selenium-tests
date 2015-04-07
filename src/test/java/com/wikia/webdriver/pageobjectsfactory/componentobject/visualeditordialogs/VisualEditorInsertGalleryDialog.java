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
  @FindBy(css = ".secondary .oo-ui-labelElement-label")
  private WebElement cancelButton;
  @FindBy(css = ".oo-ui-processDialog-actions-primary .oo-ui-labelElement-label")
  private WebElement doneButton;
  @FindBy(css = ".oo-ui-clearableTextInputWidget-clearButton")
  private WebElement clearInputButton;
  @FindBy(css = ".oo-ui-window-body")
  private WebElement dialogBody;

  //Cart
  @FindBy(css = ".oo-ui-icon-cart-grid")
  private WebElement gridViewButton;
  @FindBy(css = ".oo-ui-icon-cart-list")
  private WebElement listViewButton;
  @FindBy(css = ".oo-ui-widget-enabled.ve-ui-wikiaSingleMediaCartWidget")
  private WebElement cart;
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
    waitForElementClickableByElement(searchInput);
    searchInput.sendKeys(input);
    PageObjectLogging.log("typeInSearchTextField", "Typed " + input + " in the search field", true);
  }

  private void clickClearInputButton() {
    if (clearInputButton.isDisplayed()) {
      waitForElementClickableByElement(clearInputButton);
      clearInputButton.click();
    }
  }

  private void clickAddGalleryButton() {
    waitForElementVisibleByElement(doneButton);
    waitForElementClickableByElement(doneButton);
    doneButton.click();
  }

  public VisualEditorInsertGalleryDialog searchMedia(String searchText) {
    waitForDialogVisible();
    waitForElementClickableByElement(searchInput);
    searchInput.clear();
    typeInSearchTextField(searchText);
    return new VisualEditorInsertGalleryDialog(driver);
  }

  public VisualEditorPageObject addExistingMedia(int number) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    waitForElementVisibleByElement(mediaResultsWidget);
    List<WebElement> mediaResults = mediaResultsWidget.findElements(MEDIA_RESULTS_BY);
    for (int i = 0; i < number; i++) {
      WebElement mediaAddIcon = mediaResults.get(i).findElement(MEDIA_ADD_ICON_BY);
      mediaAddIcon.click();
    }
    clickAddGalleryButton();
    return new VisualEditorPageObject(driver);
  }

  public void removeMediaFromCart(int number) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    waitForElementVisibleByElement(mediaResultsWidget);
    List<WebElement> mediaResults = mediaResultsWidget.findElements(MEDIA_CHECKED_ICON_BY);
    for (int i = 0; i < number; i++) {
      mediaResults.get(i).click();
    }
  }

  public void verifyNumOfCartItems(int expected) {
    waitForDialogVisible();
    Assertion.assertNumber(expected, cartItems.size(), "Verify number of items in cart");
  }

  public void addMediaToCart(int number) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    waitForElementVisibleByElement(mediaResultsWidget);
    List<WebElement> mediaResults = mediaResultsWidget.findElements(MEDIA_ADD_ICON_BY);
    for (int i = 0; i < number; i++) {
      mediaResults.get(i).click();
    }
  }

  public VisualEditorPageObject clickTitleToPreview(int index) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    waitForElementByElement(mediaResultsWidget);
    WebElement targetMedia = mediaResultsWidget.findElements(MEDIA_TITLES_BY).get(index);
    scrollAndClick(targetMedia);
    return new VisualEditorPageObject(driver);
  }

  public VisualEditorPageObject clickMetaDataToPreview(int index) {
    waitForDialogVisible();
    WebElement mediaResultsWidget = dialogBody.findElement(MEDIA_RESULTS_WIDGET_BY);
    waitForElementVisibleByElement(mediaResultsWidget);
    WebElement targetMedia = mediaResultsWidget.findElements(MEDIA_META_BY).get(index);
    scrollAndClick(targetMedia);
    return new VisualEditorPageObject(driver);
  }
}
