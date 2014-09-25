package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorAddMapDialog extends VisualEditorDialog {

	@FindBy(css=".oo-ui-window-body")
	private WebElement mapDialogBody;
	@FindBy(css=".ve-ui-wikiaMapInsertDialog-results-headline a .oo-ui-labeledElement-label")
	private WebElement createAMapButton;
	@FindBy(css=".oo-ui-window-body")
	private WebElement mediaDialogBody;
	@FindBy(css=".ve-ui-wikiaMapInsertDialog-empty-headline")
	private WebElement emptyStateDialogHeadline;
	@FindBy(css=".ve-ui-wikiaMapInsertDialog-empty-text")
	private WebElement emptyStateDialogText;
	@FindBy(css=".ve-ui-wikiaMapInsertDialog-empty-text a")
	private WebElement emptyStateLearnMoreLink;
	@FindBy(css=".ve-ui-wikiaMapInsertDialog-empty-button .oo-ui-labeledElement-label")
	private WebElement emptyStateCreateAMapButton;

	private By mediaResultsWidgetBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget");
	private By mediaResultsBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget ul li");

	public VisualEditorAddMapDialog(WebDriver driver) {
		super(driver);
	}

	public void clickLearnMoreLink() {
		//TODO return the correct page object
		//Goes to http://maps.wikia.com/wiki/Maps_Wiki
	}

	public CreateAMapComponentObject clickCreateAMapButton() {
		switchToIFrame();
		if (checkIfElementOnPage(emptyStateCreateAMapButton)) {
			waitForElementClickableByElement(emptyStateCreateAMapButton);
			emptyStateCreateAMapButton.click();
			PageObjectLogging.log("clickCreateAMapButton", "Empty State: Create A Map button is clicked", true);
		} else {
			waitForElementClickableByElement(createAMapButton);
			createAMapButton.click();
			PageObjectLogging.log("clickCreateAMapButton", "Create A Map button is clicked", true);
		}
		switchToNewBrowserTab();
		return new CreateAMapComponentObject(driver);
	}

	public VisualEditorPageObject addExistingMap(int number) {
		switchToIFrame();
		WebElement mediaResultsWidget = mediaDialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		List<WebElement> maps = mediaResultsWidget.findElements(mediaResultsBy);
		WebElement map = maps.get(number);
		map.click();
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}

	public void checkIsEmptyState() {
		switchToIFrame();
		if (checkIfElementOnPage(emptyStateDialogHeadline)) {
			waitForElementVisibleByElement(emptyStateDialogHeadline);
			waitForElementVisibleByElement(emptyStateDialogText);
			waitForElementVisibleByElement(emptyStateCreateAMapButton);
			PageObjectLogging.log("checkIsEmptyState", "The Map dialog is in empty state", true, driver);
		} else {
			throw new NoSuchElementException("The wiki is not in an empty state, the wiki contains maps.");
		}
		driver.switchTo().defaultContent();
	}

	public void verifyNumOfMaps(int num) {
		switchToIFrame();
		WebElement mediaResultsWidget = mediaDialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		List<WebElement> maps = mediaResultsWidget.findElements(mediaResultsBy);
		Assertion.assertEquals(maps.size(), num, "Expecting " + num + " of maps. Actual is " + maps.size());
		switchOutOfIFrame();
	}
}
