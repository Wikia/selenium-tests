package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.contentpatterns.WikiFactoryVariablesProvider.WikiFactoryVariables;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public class SpecialFactoryPageObject extends SpecialPageObject {
	@FindBy(css = "a[href*=close]")
	private WebElement closeWikiButton;
	@FindBy(css = "#flag_1")
	private WebElement dumpCheckBox;
	@FindBy(css = "#flag_2")
	private WebElement imageArchiveCheckBox;
	@FindBy(css = "input[name='close_saveBtn']")
	private WebElement confirmCloseButton;
	@FindBy(css = "a.free")
	private WebElement closedWikiaLink;
	@FindBy(css = "select#wk-variable-select")
	private WebElement variableList;
	@FindBy(css = "div > div[style*=left] > pre")
	private WebElement variableValue;
	@FindBy(css = "div > div[style*=right] > pre")
	private WebElement defaultVariableValue;

	public SpecialFactoryPageObject(WebDriver driver) {
		super(driver);
	}

	private void clickCloseWikiButton() {
		scrollAndClick(closeWikiButton);
		PageObjectLogging.log("clickCloseWikiButton ", "Close wiki button clicked", true, driver);
	}

	private void deselectCreateDumpCheckBox() {
		scrollAndClick(dumpCheckBox);
		PageObjectLogging.log("deselectCreateDumpCheckBox ", "Create dump checkbox deselected", true, driver);
	}

	private void deselectImageArchiveCheckBox() {
		scrollAndClick(imageArchiveCheckBox);
		PageObjectLogging.log("deselectImageArchiveCheckBox ", "Create image archive checkbox deselected", true, driver);
	}

	private void confirmClose() {
		scrollAndClick(confirmCloseButton);
		PageObjectLogging.log("confirmClose ", "Close confirmation button clicked", true, driver);
	}

	private void clickClosedWikiaLink() {
		scrollAndClick(closedWikiaLink);
		PageObjectLogging.log("clickClosedWikiaLink ", "Closed wikia link clicked", true, driver);
	}

	private void verifyWikiaClosed() {
		waitForElementById("close-title");
		waitForElementById("close-info");
		PageObjectLogging.log("verifyWikiaClosed ", "Closed wikia verified", true, driver);
	}

	public void deleteWiki() {
		clickCloseWikiButton();
		deselectCreateDumpCheckBox();
		deselectImageArchiveCheckBox();
		confirmClose();
		confirmClose();
		clickClosedWikiaLink();
		verifyWikiaClosed();
	}

	public void verifyVariableValue(WikiFactoryVariables variableName, String expectedValue) {
		selectVariableByVisibleText(variableName);
		Assertion.assertEquals(expectedValue, variableValue.getText());
	}

	public String getVariableDefaultValue(WikiFactoryVariables variableName) {
		selectVariableByVisibleText(variableName);
		return defaultVariableValue.getText();
	}

	private void selectVariableByVisibleText(WikiFactoryVariables variableName) {
		Select select = new Select(variableList);
		select.selectByVisibleText(variableName.toString());
	}

	public Object[] getVariableDefaultValueKeys(WikiFactoryVariables wgHighValueCountries) {
		return extractKeysFromWgVariable(getVariableDefaultValue(wgHighValueCountries));
	}
}
