package com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;

public class NavigationBar extends WikiBasePageObject {

	public NavigationBar(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#searchInput")
	private WebElement searchInput;
	@FindBy(css = "#searchSubmit")
	private WebElement searchSubmit;
	@FindBy(css = ".autocomplete div")
	private WebElement suggestion;
	@FindBys(@FindBy(css = ".autocomplete div"))
	private List<WebElement> suggestionsList;
	private String suggestionCss = ".autocomplete div"; 
	
    private By jqueryAutocompleteBy = By.cssSelector("[src*='jquery.autocomplete']");

	public void triggerSuggestions(String query) {
		waitForElementByElement(searchInput);
		searchInput.clear();
		waitForElementClickableByElement(searchInput);
	    searchInput.click();
	    waitForElementByBy(jqueryAutocompleteBy);
	    sendKeysHumanSpeed(searchInput, query);
	    waitForElementByCss(suggestionCss);
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

	/**
    * Arrow down through suggestions, and click enter on the desired one
	*/
	public ArticlePageObject ArrowDownAndEnterSuggestion(String suggestion) {
		waitForElementByElement(suggestionsList.get(0));
		for (int i = 0; i < suggestionsList.size(); i++) {
			WebElement currentSuggestion = suggestionsList.get(i);
			searchInput.sendKeys(Keys.ARROW_DOWN);
			if (currentSuggestion.getText().contains(suggestion)) {
				searchInput.sendKeys(Keys.ENTER);
				PageObjectLogging.log("ArrowDownToSuggestion", 
				        "arrowed down to desired suggestion" +suggestion+"and clicked enter",
				        true);
				return new ArticlePageObject(driver);
			}
			else {
				searchInput.sendKeys(Keys.ARROW_DOWN);
			}
		}
		PageObjectLogging.log("ArrowDownToSuggestion", "didn't find suggestion: "+suggestion, false);
		return null;
	}
	
	/**
    * click on desired suggestion 
	*/
	public ArticlePageObject clickSuggestion(String suggestion) {
		waitForElementByElement(suggestionsList.get(0));
		for (int i = 0; i < suggestionsList.size(); i++) {
			WebElement currentSuggestion = suggestionsList.get(i);
			if (currentSuggestion.getText().contains(suggestion)) {
				currentSuggestion.click();
				PageObjectLogging.log("clickSuggestion", 
				        "clicked on desired suggestion" +suggestion,
				        true);
				return new ArticlePageObject(driver);
			}
		}
		PageObjectLogging.log("clickSuggestion", "didn't find suggestion: "+suggestion, false);
		return null;
	}	
	
	public void typeQuery(String query) {
		waitForElementByElement(searchInput);
		searchInput.clear();
		searchInput.sendKeys(query);
		PageObjectLogging.log("typeQuery", "typed query: " + query, true);
	}

	public IntraWikiSearchPageObject searchFor(String query) {
		typeQuery(query);
	    PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
	    return clickSearchButton();
	}
	
	public IntraWikiSearchPageObject clickEnterToSearch() {
		waitForElementClickableByElement(searchInput);
		searchInput.sendKeys(Keys.ENTER);
		PageObjectLogging.log("clickEnterInSearch", "clicked enter in search", true);
		return new IntraWikiSearchPageObject(driver);
	}
	
	public IntraWikiSearchPageObject clickSearchButton() {
		waitForElementClickableByElement(searchSubmit);
		searchSubmit.click();
		PageObjectLogging.log("clickSearchButton", "clicked on search button", true);
		return new IntraWikiSearchPageObject(driver);
	}
	
	 /**
	 * Returns article page object if invoked by user with goSearch preference turned on
	 */
	public ArticlePageObject goSearchFor(String query) {
	     searchInput.sendKeys(query);
	     searchSubmit.click();
	     PageObjectLogging.log("searchFor", "searching for query: " + query, true, driver);
	     return new ArticlePageObject(driver);
	 }

}
