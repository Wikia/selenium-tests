package com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 *
 * @author Karol Kujawiak
 *
 */

public class CreateNewWikiPageObjectStep1 extends BasePageObject{

	@FindBy(name="wiki-name")
	private WebElement wikiName;
	@FindBy(name="wiki-domain")
	private WebElement wikiDomain;
	@FindBy(css="span.domain-status-icon img[src*='check.png']")
	private WebElement successIcon;
	@FindBy(css=".next.enabled")
	private WebElement submitButton;
	@FindBy(css="select[name='wiki-language']")
	private WebElement languageSelector;
	@FindBy(css="#ChangeLang")
	private WebElement languageSelectorTrigger;
	@FindBy(css=".domain-country")
	private WebElement languageSelectedIndicator;

	private String wikiNameString;

	public CreateNewWikiPageObjectStep1(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wikiNameString = PageContent.wikiNamePrefix + getRandomDigits(3) + getRandomString(5);
	}

	public String getWikiName(){
		return this.wikiNameString;
	}


	public void selectLanguage(String lang)
	{
		scrollAndClick(languageSelectorTrigger);
		waitForElementByElement(languageSelector);
		Select language = new Select(languageSelector);
		List<WebElement> langList = language.getOptions();
		for (int i=0; i<langList.size(); i++)
		{
			String langDropElement = langList.get(i).getText();
			if (langDropElement.contains(lang+":"))
			{
				language.selectByIndex(i);
				Assertion.assertEquals(lang+".", languageSelectedIndicator.getText());
				break;
			}
		}
	}

	/**
	 *
	 * @param name
	 * @author Karol Kujawiak
	 */
	public void typeInWikiName(String name)
	{
		wikiName.sendKeys(name);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PageObjectLogging.log("typeInWikiName ", "Typed wiki name" +name, true);
	}

	/**
	 *
	 * @param domain
	 * @author Karol Kujawiak
	 */
	public void typeInWikiDomain(String domain)
	{
		wikiDomain.clear();
		wikiDomain.sendKeys(domain);
		PageObjectLogging.log("typeInWikiDomain ", "Typed wiki domain" +domain, true);
	}

	/**
	 * @author Karol Kujawiak
	 */
	public void waitForSuccessIcon()
	{
		waitForElementByElement(successIcon);
		waitForElementByElement(submitButton);
		PageObjectLogging.log("waitForSuccessIcon", "Success icon found", true, driver);
	}

	public void verifyOccupiedWikiAddress(String wikiName)
	{
		wikiName = wikiName.toLowerCase();
		waitForElementByCss("div.wiki-domain-error a[href='http://"+wikiName+".wikia.com']");
		PageObjectLogging.log("verifyOccupiedWikiAddress", "Verified occupied wiki address", true, driver);
	}

	public CreateNewWikiPageObjectStep2 submit()
	{
		scrollAndClick(submitButton);
		PageObjectLogging.log("submit", "Submit button clicked", true, driver);
		return new CreateNewWikiPageObjectStep2(driver);
	}

	public CreateNewWikiLogInSignUpPageObject submitToLogInSignUp()
	{
		scrollAndClick(submitButton);
		PageObjectLogging.log("submit", "Submit button clicked", true, driver);
		return new CreateNewWikiLogInSignUpPageObject(driver);
	}

	public void verifyWikiName(String expectedWikiName) {
		PageObjectLogging.log("verifyWikiName", "Verifyimg wiki name equals: " + expectedWikiName, true, driver);
		Assertion.assertEquals(expectedWikiName, wikiName.getAttribute("value"));
	}

}
