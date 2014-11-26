package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import com.wikia.webdriver.Common.Core.Assertion;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorInsertGalleryDialog extends VisualEditorDialog {

	@FindBy(css=".oo-ui-textInputWidget-decorated>input")
	private WebElement searchInput;
	@FindBy(css=".secondary .oo-ui-labeledElement-label")
	private WebElement cancelButton;
	@FindBy(css=".oo-ui-flaggableElement-primary .oo-ui-labeledElement-label")
	private WebElement doneButton;
	@FindBy(css=".oo-ui-clearableTextInputWidget-clearButton")
	private WebElement clearInputButton;
	@FindBy(css=".oo-ui-window-body")
	private WebElement dialogBody;

	//Cart
	@FindBy(css=".oo-ui-icon-cart-grid")
	private WebElement gridViewButton;
	@FindBy(css=".oo-ui-icon-cart-list")
	private WebElement listViewButton;
	@FindBy(css=".oo-ui-widget-enabled.ve-ui-wikiaSingleMediaCartWidget")
	private WebElement cart;
	@FindBy(css=".ve-ui-wikiaSingleMediaCartWidget li")
	private List<WebElement> cartItems;

	private By mediaResultsWidgetBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget");
	private By mediaResultsBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget ul li");
	private By mediaAddIconBy = By.cssSelector(".oo-ui-icon-unchecked");
	private By mediaTitlesBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget ul li>.oo-ui-labeledElement-label");
	private By mediaCheckedIconBy = By.cssSelector(".oo-ui-icon-checked");

	public VisualEditorInsertGalleryDialog(WebDriver driver) {
		super(driver);
	}

	private void typeInSearchTextField(String input) {
		waitForElementByElement(searchInput);
		searchInput.sendKeys(input);
		PageObjectLogging.log("typeInSearchTextField", "Typed " + input + " in the search field", true);
	}

	private void clickClearInputButton() {
		if (clearInputButton.isDisplayed()) {
			clearInputButton.click();
			PageObjectLogging.log("clickClearInputButton", "'x' button clicked to clear search", true);
		}
	}

	private void clickAddGalleryButton() {
		waitForElementVisibleByElement(doneButton);
		waitForElementClickableByElement(doneButton);
		doneButton.click();
		PageObjectLogging.log("clickAddGalleryButton", "'Done' button clicked", true);
	}

	public VisualEditorInsertGalleryDialog searchMedia(String searchText) {
		switchToIFrame();
		clickClearInputButton();
		typeInSearchTextField(searchText);
		switchOutOfIFrame();
		return new VisualEditorInsertGalleryDialog(driver);
	}

	public VisualEditorPageObject addExistingMedia(int number) {
		switchToIFrame();
		WebElement mediaResultsWidget = dialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		List<WebElement> mediaResults = mediaResultsWidget.findElements(mediaResultsBy);
		for (int i = 0; i<number; i++) {
			WebElement mediaAddIcon = mediaResults.get(i).findElement(mediaAddIconBy);
			mediaAddIcon.click();
		}
		clickAddGalleryButton();
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}

	public void removeMediaFromCart(int number) {
		switchToIFrame();
		WebElement mediaResultsWidget = dialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		List<WebElement> mediaResults = mediaResultsWidget.findElements(mediaCheckedIconBy);
		for (int i = 0; i<number; i++) {
			mediaResults.get(i).click();
			PageObjectLogging.log("removeMediaFromCart", "1 item unchecked from grid", true);
		}
		switchOutOfIFrame();
	}

	public void verifyNumOfCartItems(int expected) {
		switchToIFrame();
		Assertion.assertNumber(expected, cartItems.size(), "Verify number of items in cart");
		switchOutOfIFrame();
	}

	public void addMediaToCart(int number) {
		switchToIFrame();
		WebElement mediaResultsWidget = dialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		List<WebElement> mediaResults = mediaResultsWidget.findElements(mediaAddIconBy);
		for (int i = 0; i<number; i++) {
			mediaResults.get(i).click();
			PageObjectLogging.log("addMediaToCart", "1 item checked from grid", true);
		}
		switchOutOfIFrame();
	}

	public VisualEditorPageObject previewExistingMediaByIndex(int index) {
		switchToIFrame();
		WebElement mediaResultsWidget = dialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		WebElement targetMedia = mediaResultsWidget.findElements(mediaTitlesBy).get(index);
		targetMedia.click();
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}

	public VisualEditorPageObject previewExistingMediaByTitle(String title) {
		switchToIFrame();
		WebElement media = findMediaByTitle(title);
		media.click();
		PageObjectLogging.log("previewExistingMediaByTitle", "Media clicked", true);
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}

	private WebElement findMediaByTitle(String title) {
		WebElement elementFound = null;
		WebElement mediaResultsWidget = dialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		List<WebElement> mediaTitles = mediaResultsWidget.findElements(mediaTitlesBy);
		int i = 0;
		boolean found = false;
		while(i<mediaTitles.size() && found == false) {
			String mediaTitle = mediaTitles.get(i).getAttribute("title");
			if (mediaTitle.equals(title)) {
				found = true;
				elementFound = mediaTitles.get(i);
				PageObjectLogging.log("findMediaByTitle", title + " found from media dialog", true);
			}
			i++;
		}
		if (found == false) {
			throw new NoSuchElementException("Media with the title: " + title + " is not found");
		}
		return elementFound;
	}
}
