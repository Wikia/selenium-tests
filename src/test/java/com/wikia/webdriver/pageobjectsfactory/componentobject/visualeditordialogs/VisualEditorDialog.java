package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 *
 */

public class VisualEditorDialog extends WikiBasePageObject{

	@FindBy(css=".oo-ui-window-ready .oo-ui-frame")
	private WebElement iframe;
	@FindBy(css=".oo-ui-window-ready")
	private WebElement dialog;
	@FindBy(css=".oo-ui-icon-close")
	private WebElement closeButton;

	public VisualEditorDialog(WebDriver driver) {
		super(driver);
	}

	public void switchToIFrame() {
		waitForElementByElement(dialog);
		waitForElementVisibleByElement(dialog);
		driver.switchTo().frame(iframe);
	}

	public void switchOutOfIFrame() {
		waitForElementNotVisibleByElement(dialog);
		driver.switchTo().defaultContent();
	}

	public void switchOutOfAllIFrame() {
		driver.switchTo().defaultContent();
		waitForElementNotVisibleByElement(dialog);
	}

	public VisualEditorPageObject closeDialog() {
		switchToIFrame();
		waitForElementClickableByElement(closeButton);
		closeButton.click();
		PageObjectLogging.log("closeDialog", "Closed button on the dialog is clicked", true);
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}
}
