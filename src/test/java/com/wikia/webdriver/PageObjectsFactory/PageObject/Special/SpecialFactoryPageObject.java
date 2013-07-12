package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
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

	public SpecialFactoryPageObject(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
	}



	public SpecialFactoryPageObject openWikiFactoryPage()
	{
		getUrl(URLsContent.wikiFactoryLiveDomain);
		PageObjectLogging.log("openWikiFactoryPage ", "Wiki factory page opened", true, driver);
		return new SpecialFactoryPageObject(driver);
	}

	private void typeInDomainName(String name)
	{
		domainField.sendKeys(name);
		PageObjectLogging.log("typeInDomainName ", "Typed domain name " +name, true, driver);
	}

	private void getConfiguration()
	{
		clickAndWait(getConfigButton);
		PageObjectLogging.log("getConfiguration ", "Get configuration button clicked", true, driver);
	}

	private void clickCloseWikiButton()
	{
		clickAndWait(closeWikiButton);
		PageObjectLogging.log("clickCloseWikiButton ", "Close wiki button clicked", true, driver);
	}

	private void deselectCreateDumpCheckBox()
	{
		clickAndWait(dumpCheckBox);
		PageObjectLogging.log("deselectCreateDumpCheckBox ", "Create dump checkbox deselected", true, driver);
	}

	private void deselectImageArchiveCheckBox()
	{
		clickAndWait(imageArchiveCheckBox);
		PageObjectLogging.log("deselectImageArchiveCheckBox ", "Create image archive checkbox deselected", true, driver);
	}

	private void confirmClose()
	{
		clickAndWait(confirmCloseButton);
		PageObjectLogging.log("confirmClose ", "Close confirmation button clicked", true, driver);
	}

	private void clickClosedWikiaLink()
	{
		clickAndWait(closedWikiaLink);
		PageObjectLogging.log("clickClosedWikiaLink ", "Closed wikia link clicked", true, driver);
	}

	private void verifyWikiaClosed()
	{
		waitForElementById("close-title");
		waitForElementById("close-info");
		PageObjectLogging.log("verifyWikiaClosed ", "Closed wikia verified", true, driver);
	}

	public void deleteWiki(String wikiName)
	{
		typeInDomainName(wikiName);
		getConfiguration();
		clickCloseWikiButton();
		deselectCreateDumpCheckBox();
		deselectImageArchiveCheckBox();
		confirmClose();
		confirmClose();
		clickClosedWikiaLink();
		verifyWikiaClosed();
	}

}
