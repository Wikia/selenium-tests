package com.wikia.webdriver.pageobjectsfactory.pageobject.special.multiwikifinder;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.common.dataprovider.SearchDataProvider;
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
	private final String linksLimit = "a[href*='/wiki/Special:Multiwikifinder?limit=%limit%']";

	@FindBy(css="#mw-content-text input[type=submit]")
	private WebElement findButton;
	@FindBy(css="input[name=target]")
	private WebElement enterPagenameField;
	@FindBy(css = ".mw-nextlink")
	private WebElement nextResultsButton;
	@FindBy(css = ".mw-prevlink")
	private WebElement previousResultsButton;
	@FindBys(@FindBy(css=".special > li > a"))
	private List <WebElement> listOfLinks;

	public SpecialMultiWikiFinderPageObject(WebDriver driver) {
		super(driver);
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

	public void compareResultsCount(int limit){
		if(limit==0) {
			waitForElementNotPresent(listOfLinksSelector);
			PageObjectLogging.log(
				"verifyNoPagenameFounded",
				"Not existing pagename is not founded",
				true, driver
			);
		}
		else {
			waitForElementByElement(listOfLinks.get(0));
			Assertion.assertTrue(listOfLinks.size() <= limit);
		}
	}

	public void verifyPagination(){
		String firstLinkOnFirstPage = listOfLinks.get(0).getAttribute("href");
		String lastLinkOnFirstPage = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
		nextResultsButton.click();
		String firstLinkOnSecondPage = listOfLinks.get(0).getAttribute("href");
		String lastLinkOnSecondPage = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
		Assertion.assertNotEquals(firstLinkOnFirstPage, firstLinkOnSecondPage);
		Assertion.assertNotEquals(lastLinkOnFirstPage, lastLinkOnSecondPage);
		previousResultsButton.click();
		String firstLinkAfterBack = listOfLinks.get(0).getAttribute("href");
		String lastLinkAfterBack = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
		Assertion.assertEquals(firstLinkOnFirstPage, firstLinkAfterBack);
		Assertion.assertEquals(lastLinkOnFirstPage, lastLinkAfterBack);
	}

	public void verifyAllLinksHavePagenameInPath(String pageName){
		for(int i=0; i<listOfLinks.size(); i++) {
			Assertion.assertTrue(listOfLinks.get(i).getAttribute("href").endsWith("/" + pageName));
		}
	}

	public void checkAllLimits(){
		for(int limit : SearchDataProvider.getSearchLimits()) {
			String limitCssSelector = linksLimit.replace("%limit%", Integer.toString(limit));
			WebElement limitButton = driver.findElement(By.cssSelector(limitCssSelector));
			limitButton.click();
			compareResultsCount(limit);
		}
	}

}
