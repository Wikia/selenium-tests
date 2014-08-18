package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 *
 */

public class VisualEditorDialog extends WikiBasePageObject{

	@FindBy(css=".oo-ui-window-ready .oo-ui-frame")
	private WebElement iframe;
	@FindBy(css=".oo-ui-icon-close")
	private WebElement closeButton;

	public VisualEditorDialog(WebDriver driver) {
		super(driver);
	}

	public void switchToIFrame() {
		waitForElementVisibleByElement(iframe);
		driver.switchTo().frame(iframe);
	}

	public void switchOutOfIFrame() {
		waitForElementNotVisibleByElement(iframe);
		driver.switchTo().defaultContent();
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
