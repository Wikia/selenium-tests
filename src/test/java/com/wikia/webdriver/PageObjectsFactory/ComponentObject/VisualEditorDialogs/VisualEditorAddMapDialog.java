package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorAddMapDialog extends VisualEditorDialog {

	@FindBy(css=".oo-ui-icon-close")
	private WebElement closeButton;
	@FindBy(css=".oo-ui-window-ready .oo-ui-frame")
	private WebElement insertMapDialogIFrame;
	@FindBy(css=".oo-ui-window-body")
	private WebElement mapDialogBody;
	@FindBy(css=".ve-ui-wikiaMapInsertDialog-results-headline .oo-ui-labeledElement-label")
	private WebElement createAMapButton;
	@FindBy(css=".oo-ui-window-body")
	private WebElement mediaDialogBody;

	private By mediaResultsWidgetBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget");
	private By mediaResultsBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget ul li");
	private By mediaAddIconBy = By.cssSelector(".oo-ui-icon-unchecked");

	public VisualEditorAddMapDialog(WebDriver driver) {
		super(driver);
	}

	public VisualEditorPageObject closeDialog() {
		waitForElementVisibleByElement(insertMapDialogIFrame);
		driver.switchTo().frame(insertMapDialogIFrame);
		waitForElementClickableByElement(closeButton);
		closeButton.click();
		PageObjectLogging.log("closeDialog", "Add Map dialog is closed", true);
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	public void clickLearnMoreLink() {
		//TODO return the correct page object
		//Goes to http://maps.wikia.com/wiki/Maps_Wiki
	}

	public void clickCreateAMapButton() {
		waitForElementVisibleByElement(insertMapDialogIFrame);
		driver.switchTo().frame(insertMapDialogIFrame);
		waitForElementClickableByElement(createAMapButton);
		createAMapButton.click();
		PageObjectLogging.log("clickCreateAMapButton", "Create A Map button is clicked", true);
		driver.switchTo().defaultContent();
		//Goes to http://muppets.ve.wikia-dev.com/wiki/Special:Maps#createMap
	}

	public VisualEditorPageObject addExistingMedia(int number) {
		waitForElementVisibleByElement(insertMapDialogIFrame);
		driver.switchTo().frame(insertMapDialogIFrame);
		WebElement mediaResultsWidget = mediaDialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		List<WebElement> mediaResults = mediaResultsWidget.findElements(mediaResultsBy);
		WebElement mediaAddIcon = mediaResults.get(number).findElement(mediaAddIconBy);
		mediaAddIcon.click();
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}
}
