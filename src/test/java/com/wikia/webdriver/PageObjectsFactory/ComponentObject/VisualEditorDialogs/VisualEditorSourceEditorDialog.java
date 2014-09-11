package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorSourceEditorDialog extends VisualEditorDialog {

	@FindBy(css=".oo-ui-window-foot .oo-ui-buttonedElement-button")
	private WebElement applyChangesButton;
	@FindBy(css=".oo-ui-widget-enabled.oo-ui-textInputWidget textarea")
	private WebElement editArea;
	@FindBy(css=".wikiaThrobber")
	private WebElement loadingIndicator;

	public VisualEditorSourceEditorDialog(WebDriver driver) {
		super(driver);
	}

	public VisualEditorPageObject clickApplyChangesButton() {
		switchToIFrame();
		waitForElementClickableByElement(applyChangesButton);
		applyChangesButton.click();
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}

	public VisualEditorPageObject typeInEditArea(String text) {
		switchToIFrame();
		waitForElementNotVisibleByElement(loadingIndicator);
		waitForElementVisibleByElement(editArea);
		waitForElementClickableByElement(editArea);
		editArea.sendKeys(text);
		PageObjectLogging.log("typeInEditArea", "Typed " + text, true, driver);
		waitForElementClickableByElement(applyChangesButton);
		scrollAndClick(applyChangesButton);
		switchOutOfIFrame();
		return new VisualEditorPageObject(driver);
	}
}
