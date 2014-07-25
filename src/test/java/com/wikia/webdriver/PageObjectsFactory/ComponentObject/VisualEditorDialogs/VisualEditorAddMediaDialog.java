package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorAddMediaDialog extends VisualEditorDialog {

	@FindBy(css=".oo-ui-textInputWidget-decorated>input")
	private WebElement searchInputTextField;
	@FindBy(css=".oo-ui-pageLayout-active .ve-ui-wikiaUploadButtonWidget")
	private WebElement clickToUploadArea;
	@FindBy(css=".oo-ui-pageLayout-active .oo-ui-labeledElement-label")
	private WebElement midUploadButton;
	@FindBy(css=".oo-ui-icon-close")
	private WebElement closeButton;
	@FindBy(css=".oo-ui-window-foot .oo-ui-buttonedElement-button")
	private WebElement addMediaButton;
	@FindBy(css=".ve-ui-wikiaMediaQueryWidget-uploadWrapper .oo-ui-labeledElement-label")
	private WebElement topUploadButton;
	@FindBy(css=".video.oo-ui-pageLayout-active .oo-ui-buttonedElement-button")
	private WebElement removeThisItemButton;
	@FindBy(css=".video.oo-ui-pageLayout-active .video-thumbnail")
	private WebElement externalVideoThumbnail;
	@FindBy(css=".oo-ui-window-ready .oo-ui-frame")
	private WebElement insertMediaDialogIFrame;
	@FindBy(css=".oo-ui-window-body")
	private WebElement mediaDialogBody;
	@FindBy(css=".oo-ui-bookletLayout .ve-ui-wikiaUploadButtonWidget input")
	private WebElement fileInput;
	@FindBy(css=".oo-ui-bookletLayout .ve-ui-wikiaUploadButtonWidget form")
	private WebElement fileInput1;

	private By mediaResultsWidgetBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget");
	private By mediaResultsBy = By.cssSelector(".ve-ui-wikiaMediaResultsWidget ul li");
	private By mediaAddIconBy = By.cssSelector(".oo-ui-icon-unchecked");

	public VisualEditorAddMediaDialog(WebDriver driver) {
		super(driver);
	}

	public VisualEditorPageObject closeDialog() {
		waitForElementVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().frame(insertMediaDialogIFrame);
		waitForElementClickableByElement(closeButton);
		closeButton.click();
		PageObjectLogging.log("closeAddMediaDialog", "Add Media dialog is closed", true);
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	private void typeInSearchTextField(String input) {
		waitForElementByElement(searchInputTextField);
		searchInputTextField.sendKeys(input);
	}

	private void clickAddMediaButton() {
		waitForElementClickableByElement(addMediaButton);
		addMediaButton.click();
	}

	public VisualEditorPageObject addMediaByURL(String url) {
		waitForElementVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().frame(insertMediaDialogIFrame);
		typeInSearchTextField(url);
		waitForElementVisibleByElement(topUploadButton);
		clickAddMediaButton();
		waitForElementNotVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	public VisualEditorAddMediaDialog searchMedia(String searchText) {
		waitForElementVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().frame(insertMediaDialogIFrame);
		typeInSearchTextField(searchText);
		driver.switchTo().defaultContent();
		return new VisualEditorAddMediaDialog(driver);
	}

	public VisualEditorPageObject addExistingMedia(int number) {
		waitForElementVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().frame(insertMediaDialogIFrame);
		WebElement mediaResultsWidget = mediaDialogBody.findElement(mediaResultsWidgetBy);
		waitForElementVisibleByElement(mediaResultsWidget);
		List<WebElement> mediaResults = mediaResultsWidget.findElements(mediaResultsBy);
		for (int i = 0; i<number; i++) {
			WebElement mediaAddIcon = mediaResults.get(i).findElement(mediaAddIconBy);
			mediaAddIcon.click();
		}
		clickAddMediaButton();
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	public VisualEditorPageObject uploadImage(String fileName) {
		waitForElementVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().frame(insertMediaDialogIFrame);
		selectFileToUpload(fileName);
		waitForElementVisibleByElement(topUploadButton);
		clickAddMediaButton();
		waitForElementNotVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	private void selectFileToUpload(String fileName) {
		fileInput.sendKeys(
			getAbsolutePathForFile(PageContent.resourcesPath + fileName)
		);
		PageObjectLogging.log(
			"selectFileToUpload",
			"file " + fileName + " added to upload",
			true
		);
	}
}
