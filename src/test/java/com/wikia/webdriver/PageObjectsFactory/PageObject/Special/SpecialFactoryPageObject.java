package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.WikiFactoryVariables.wikiFactoryVariables;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialFactoryPageObject extends SpecialPageObject
{
	@FindBy(id="citydomain")
	private WebElement domainField;
	@FindBy(css="form[id='WikiFactoryDomainSelector'] button")
	private WebElement getConfigButton;
	@FindBy(css="a[href*=close]")
	private WebElement closeWikiButton;
	@FindBy(css="#flag_1")
	private WebElement dumpCheckBox;
	@FindBy(css="#flag_2")
	private WebElement imageArchiveCheckBox;
	@FindBy(css="input[name='close_saveBtn']")
	private WebElement confirmCloseButton;
	@FindBy(css="a.free")
	private WebElement closedWikiaLink;
	@FindBy(css="#wfOnlyWithString")
	private WebElement variableFiled;
	@FindBy(css="select#wk-variable-select")
	private WebElement variableList;
	@FindBy(css="div > div[style*=left] > pre")
	private WebElement variableValue;

	public SpecialFactoryPageObject(WebDriver driver) {
		super(driver);
	}

	public SpecialFactoryPageObject openWikiFactoryPage() {
		getUrl(URLsContent.wikiFactoryLiveDomain);
		PageObjectLogging.log("openWikiFactoryPage ", "Wiki factory page opened", true, driver);
		return new SpecialFactoryPageObject(driver);
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

	public void verifyVariableValue(wikiFactoryVariables variableName, String expectedValue) {
		Select select = new Select(variableList);
		select.selectByVisibleText(variableName.toString());
		Assertion.assertEquals(expectedValue, variableValue.getText());
	}
}
