package com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.thoughtworks.xstream.alias.ClassMapper.Null;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;

public class NavigationBar extends WikiBasePageObject {

	@FindBy(css = "#searchInput")
	private WebElement searchInput;
	@FindBy(css = "#searchSubmit")
	private WebElement searchSubmit;
	@FindBys(@FindBy(css = ".autocomplete div"))
	private List<WebElement> suggestionsList;
	
	private By jqueryAutocompleteBy = By.cssSelector("[src*='jquery.autocomplete']");

	public NavigationBar(WebDriver driver) {
		super(driver);
	}

	public void triggerSuggestions(String query) {
	    searchInput.click();
	    waitForElementByBy(jqueryAutocompleteBy);
	    searchInput.sendKeys(query);
	    waitForElementByElement(suggestionsList.get(0));		
	}
	
	public void verifySuggestions(String suggestion) {
		waitForElementByElement(suggestionsList.get(0));
		String allSuggestions = "";
		for (int i = 0; i < suggestionsList.size(); i++) {
			allSuggestions += suggestionsList.get(i).getAttribute("title");
		}
		Assertion.assertStringContains(suggestion, allSuggestions);
	}

	public ArticlePageObject ArrowDownAndEnterSuggestion(String suggestion) {
		waitForElementByElement(suggestionsList.get(0));
		for (int i = 0; i < suggestionsList.size(); i++) {
			WebElement currentSuggestion = suggestionsList.get(i);
			searchInput.sendKeys(Keys.ARROW_DOWN);
			if (currentSuggestion.getText().contains(suggestion)) {
				searchInput.sendKeys(Keys.ENTER);
				PageObjectLogging.log("ArrowDownToSuggestion", "arrowed down to desired suggestion" +suggestion+ "and clicked enter", true);
				return new ArticlePageObject(driver);
			}
			else {
				searchInput.sendKeys(Keys.ARROW_DOWN);
			}
		}
		PageObjectLogging.log("ArrowDownToSuggestion", "didn't find suggestion: "+suggestion, true);
		return null;
	}

	public IntraWikiSearchPageObject searchFor(String query) {
	    searchInput.sendKeys(query);
	    searchSubmit.click();
	    PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
	    return new IntraWikiSearchPageObject(driver);
	}
	
	 /**
	 * method will return article page object if invoked by user with goSearch preference turned on
	 */
	public ArticlePageObject goSearchFor(String query) {
	     searchInput.sendKeys(query);
	     searchSubmit.click();
	     PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
	     return new ArticlePageObject(driver);
	 }

}
