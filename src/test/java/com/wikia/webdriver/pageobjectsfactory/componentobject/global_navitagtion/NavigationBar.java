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

public class NavigationBar extends WikiBasePageObject {

	@FindBy(css = "#searchInput")
	private WebElement searchInput;
	@FindBys(@FindBy(css = ".autocomplete"))
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
		for (int i = 0; i < suggestionsList.size(); i++) {
			Assertion.assertStringContains(suggestion, suggestionsList.get(i).getText());
		}
	}

	public ArticlePageObject ArrowDownAndEnterSuggestion(String suggestion) {
		waitForElementByElement(suggestionsList.get(0));
		for (int i = 0; i < suggestionsList.size(); i++) {
			WebElement currentSuggestion = suggestionsList.get(i);
			if (currentSuggestion.getText().contains(suggestion)) {
				currentSuggestion.sendKeys(Keys.ENTER);
				PageObjectLogging.log("ArrowDownToSuggestion", "arrowed down to desired suggestion" +suggestion+ "and clicked enter", true);
				return new ArticlePageObject(driver);
			}
			else {
				currentSuggestion.sendKeys(Keys.ARROW_DOWN);
			}
		}
		PageObjectLogging.log("ArrowDownToSuggestion", "didn't find suggestion: "+suggestion, true);
		return null;
	}

}
