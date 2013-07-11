package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Multiwikifinder;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

/**
 *
 * @author łukasz
 */
public class SpecialMultiWikiFinderPageObject extends WikiBasePageObject{

	private final String listOfLinksSelector = ".special > li > a";
	private String linksLimit = "a[href*=\"/wiki/Special:Multiwikifinder?limit=%n%&offset=0&target=%k%\"]";

	@FindBy(css="#mw-content-text input[type=submit]")
	private WebElement findButton;
	@FindBy(css="input[name=target]")
	private WebElement enterPagenameField;
	@FindBy(css=".AdminDashboardGeneralHeader.AdminDashboardArticleHeader > h1")
	private WebElement multiWikiFinderPageHeader;
	@FindBy(css = ".mw-nextlink")
	private WebElement nextResultsButton;
	@FindBy(css = ".mw-prevlink")
	private WebElement previousResultsButton;
	@FindBys(@FindBy(css=".special > li > a"))
	private List <WebElement> listOfLinks;

	public SpecialMultiWikiFinderPageObject(WebDriver driver) {
		super(driver);
	}

	public SpecialMultiWikiFinderPageObject openSpecialMultiWikiFinderPage(){
		getUrl(Global.DOMAIN + URLsContent.specialMultiWikiFinderPage);
		waitForElementByElement(multiWikiFinderPageHeader);
		PageObjectLogging.log(
			"openSpecialMultiWikiFinderPage",
			"Special MultiWikiFinder page was opened",
			true, driver
		);
		return new SpecialMultiWikiFinderPageObject(driver);
	}

	public void findPageName(String pagename){
		waitForElementByElement(enterPagenameField);
		enterPagenameField.sendKeys(pagename);
		waitForElementByElement(findButton);
		findButton.click();
	}

	public void verifyEmptyPagename(){
		waitForElementNotPresent(".mw-spcontent > p");
		PageObjectLogging.log(
			"verifyEmptyPageName",
			"Empty pagename is not founded",
			true, driver
		);
	}

	public void limitResults(String pageName, int limit) {
		String limitCssSelector = linksLimit.replace("%n%", Integer.toString(limit));
		String newLimitCssSelector = limitCssSelector.replace("%k%", pageName);
		WebElement limitButton = driver.findElement(By.cssSelector(newLimitCssSelector));
		limitButton.click();
	}

	public void compareResultsCount(int limit){
		if(limit==0){
			waitForElementNotPresent(listOfLinksSelector);
			PageObjectLogging.log(
				"verifyNoPagenameFounded",
				"Not existing pagename is not founded",
				true, driver
			);
		}
		else{
			waitForElementByElement(listOfLinks.get(0));
			int amountOfLinks = listOfLinks.size();
			Assertion.assertTrue(amountOfLinks <= limit);
		}
	}

	public void verifyPagination(){
		String firstLinkOnFirstPage = listOfLinks.get(0).getAttribute("href");
		String lastLinkOnFirstPage = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
		waitForElementByElement(nextResultsButton);
		nextResultsButton.click();
		String firstLinkOnSecondPage = listOfLinks.get(0).getAttribute("href");
		String lastLinkOnSecondPage = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
		Assertion.assertNotEquals(firstLinkOnFirstPage, firstLinkOnSecondPage);
		Assertion.assertNotEquals(lastLinkOnFirstPage, lastLinkOnSecondPage);
		waitForElementByElement(previousResultsButton);
		previousResultsButton.click();
		String firstLinkAfterBack = listOfLinks.get(0).getAttribute("href");
		String lastLinkAfterBack = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
		Assertion.assertEquals(firstLinkOnFirstPage, firstLinkAfterBack);
		Assertion.assertEquals(lastLinkOnFirstPage, lastLinkAfterBack);
	}

	public void verifyAllLinksHavePagenameInPath(String pageName){
		for(int i=0; i<listOfLinks.size(); i++){
			Assertion.assertTrue(listOfLinks.get(i).getAttribute("href").endsWith("/" + pageName));
		}
	}
}