package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorSourceEditorDialog extends VisualEditorDialog {

	@FindBy(css=".oo-ui-icon-close")
	private WebElement closeButton;
	@FindBy(css=".oo-ui-window-ready .oo-ui-frame")
	private WebElement sourceEditorDialogIFrame;
	@FindBy(css=".oo-ui-window-foot .oo-ui-buttonedElement-button")
	private WebElement applyChangesButton;
	@FindBy(css=".oo-ui-textInputWidget textarea")
	private WebElement editArea;

	public VisualEditorSourceEditorDialog(WebDriver driver) {
		super(driver);
	}

	public VisualEditorPageObject clickCloseButton() {
		waitForElementVisibleByElement(sourceEditorDialogIFrame);
		driver.switchTo().frame(sourceEditorDialogIFrame);
		waitForElementClickableByElement(closeButton);
		closeButton.click();
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	public VisualEditorPageObject clickApplyChangesButton() {
		waitForElementVisibleByElement(sourceEditorDialogIFrame);
		driver.switchTo().frame(sourceEditorDialogIFrame);
		waitForElementClickableByElement(applyChangesButton);
		applyChangesButton.click();
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	public void typeInEditArea(String text) {
		waitForElementVisibleByElement(sourceEditorDialogIFrame);
		driver.switchTo().frame(sourceEditorDialogIFrame);
		waitForElementByElement(editArea);
		editArea.sendKeys(text);
		driver.switchTo().defaultContent();
	}
}
