package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorAddMediaDialog extends VisualEditorDialog {

	@FindBy(css=".oo-ui-textInputWidget-decorated>input")
	private WebElement searchInputTextField;
	@FindBy(css=".oo-ui-pageLayout-active .ve-ui-wikiaUploadButtonWidget")
	private WebElement clickToUploadArea;
	@FindBy(css=".oo-ui-pageLayout-active .oo-ui-buttonWidget")
	private WebElement midUploadButton;
	@FindBy(css=".oo-ui-icon-close")
	private WebElement closeButton;
	@FindBy(css=".oo-ui-window-foot .oo-ui-buttonedElement-button")
	private WebElement addMediaButton;
	@FindBy(css=".ve-ui-wikiaUploadButtonWidget")
	private WebElement topUploadButton;
	@FindBy(css=".video.oo-ui-pageLayout-active .oo-ui-buttonedElement-button")
	private WebElement removeThisItemButton;
	@FindBy(css=".video.oo-ui-pageLayout-active .video-thumbnail")
	private WebElement externalVideoThumbnail;
	@FindBy(css=".oo-ui-dialog-open .oo-ui-frame")
	private WebElement insertMediaDialogIFrame;

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

	public VisualEditorPageObject addMedia(String url) {
		waitForElementVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().frame(insertMediaDialogIFrame);
		typeInSearchTextField(url);
		clickAddMediaButton();
		waitForElementNotVisibleByElement(insertMediaDialogIFrame);
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}
}
