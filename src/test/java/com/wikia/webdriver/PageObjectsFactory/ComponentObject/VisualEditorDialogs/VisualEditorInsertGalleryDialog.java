package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
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

	public VisualEditorInsertGalleryDialog(WebDriver driver) {
		super(driver);
	}

	private void typeInSearchTextField(String input) {
		waitForElementByElement(searchInput);
		searchInput.sendKeys(input);
	}

	private void clickAddGalleryButton() {
		waitForElementVisibleByElement(doneButton);
		waitForElementClickableByElement(doneButton);
		doneButton.click();
	}

	public VisualEditorInsertGalleryDialog searchMedia(String searchText) {
		switchToIFrame();
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
