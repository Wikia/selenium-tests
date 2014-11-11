package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Alignment;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Setting;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

public class VisualEditorMediaSettingsDialog extends VisualEditorDialog {

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
	@FindBy(css=".oo-ui-buttonSelectWidget a")
	private List<WebElement> positionButtons;

	private By labeledElementBy = By.cssSelector(".oo-ui-labeledElement-label");

	public VisualEditorMediaSettingsDialog(WebDriver driver) {
		super(driver);
	}

	public void selectSettings(Setting setting) {
		switchToIFrame();
		WebElement generalSetting = outlineMenuItems.get(setting.ordinal()).findElement(labeledElementBy);
		waitForElementClickableByElement(generalSetting);
		generalSetting.click();
		PageObjectLogging.log("selectSettings", setting.toString() + " setting is selected", true);
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
		switchOutOfIFrame();
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

	public void clickAlignment(Alignment align) {
		switchToIFrame();
		WebElement button = positionButtons.get(align.ordinal()).findElement(labeledElementBy);
		waitForElementClickableByElement(button);
		button.click();
		PageObjectLogging.log("clickAlignment", align.toString() + " align is selected", true);
		driver.switchTo().defaultContent();
	}
}
