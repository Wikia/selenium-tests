package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorMediaSettingsDialog extends VisualEditorDialog {

	private final int GENERAL = 0;
	private final int ADVANCED = 1;

	@FindBy(css=".oo-ui-outlineWidget")
	private WebElement outlineMenu;
	@FindBy(css=".oo-ui-outlineWidget li")
	private List<WebElement> outlineMenuItems;
	@FindBy(css=".ve-ce-documentNode")
	private WebElement captionEditArea;
	@FindBy(css=".oo-ui-window-foot .oo-ui-labeledElement-label")
	private WebElement applyChangesButton;
	@FindBy(css=".ve-ui-dimensionsWidget input")
	private WebElement customSizeInput;

	private By labeledElementBy = By.cssSelector(".oo-ui-labeledElement-label");

	public VisualEditorMediaSettingsDialog(WebDriver driver) {
		super(driver);
	}

	public void selectGeneralSettings() {
		switchToIFrame();
		WebElement generalSetting = outlineMenuItems.get(GENERAL).findElement(labeledElementBy);
		waitForElementClickableByElement(generalSetting);
		generalSetting.click();
		PageObjectLogging.log("selectGeneralSettings", "General settings is selected", true);
		driver.switchTo().defaultContent();
	}

	public void selectAdvancedSettings() {
		switchToIFrame();
		WebElement advancedSetting = outlineMenuItems.get(ADVANCED).findElement(labeledElementBy);
		waitForElementClickableByElement(advancedSetting);
		advancedSetting.click();
		PageObjectLogging.log("selectAdvancedSettings", "Advanved settings is selected", true);
		driver.switchTo().defaultContent();
	}

	public void typeCaption(String text) {
		switchToIFrame();
		waitForElementByElement(captionEditArea);
		captionEditArea.sendKeys(text);
		PageObjectLogging.log("typeCaption", "Typed " + text + " in caption area", true);
		driver.switchTo().defaultContent();
	}

	public VisualEditorPageObject clickApplyChangesButton() {
		switchToIFrame();
		waitForElementVisibleByElement(applyChangesButton);
		waitForElementClickableByElement(applyChangesButton);
		applyChangesButton.click();
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	private void typeCustomSize(int size) {
		waitForElementVisibleByElement(customSizeInput);
		customSizeInput.clear();
		customSizeInput.sendKeys(Integer.toString(size));
		PageObjectLogging.log("typeCustomSize", "Typed " + size + " in the field", true, driver);
		driver.switchTo().defaultContent();
	}

	public void setCustomSize(int size) {
		switchToIFrame();
		typeCustomSize(size);
		driver.switchTo().defaultContent();
	}
}
