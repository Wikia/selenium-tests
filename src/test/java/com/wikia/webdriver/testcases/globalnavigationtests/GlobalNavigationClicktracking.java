package com.wikia.webdriver.testcases.globalnavigationtests;

import java.util.Arrays;
import java.util.List;

import javax.json.JsonObject;

import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.common.clicktracking.events.EventsArticleEditMode;
import com.wikia.webdriver.common.clicktracking.events.EventsGlobalNavigation;
import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.SearchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Michal 'justnpT' Nowierski
 */
public class GlobalNavigationClicktracking extends NewTestTemplate {

	
    /** search test flow: action - expected event
    *  navigate to main page
    *  
    *  search for query
    *  click search button - search-button
    *  click enter button - search-enter
    *  
    *  trigger suggestion - search-suggest-show
    *  click enter on suggestion - search-suggest-enter
    *  click enter after suggestion - search-after-suggest-enter
    *  clear suggestion
    *  
    *  trigger suggestion
    *  mouse click on suggestion - search-suggest
    *  click search after suggestion - search-after-suggest-button
    *  clear suggestion
    */	
    @Test(
        groups = {"TestGlobalSearchInGlobalNav_001", "ClickTracking", "GlobalNav"}
    )
    public void search() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.openMainPage(wikiURL);
        NavigationBar navbar = new NavigationBar(driver);
        navbar.executeScript(ClickTrackingScriptsProvider.REDIRECT_BLOCK);
        navbar.executeScript(ClickTrackingScriptsProvider.TRACKER_INSTALLATION);
	
    	navbar.typeQuery(SearchContent.SEARCH_ARTICLE);
		navbar.clickSearchButton();
		navbar.dismissPopupWindow();
    	navbar.clickEnterToSearch();
    	navbar.dismissPopupWindow();
        
        navbar.triggerSuggestions(SearchContent.SEARCH_SUGGESTION_PHRASE);
		navbar.verifySuggestions(SearchContent.SEARCH_ARTICLE);
		navbar.ArrowDownAndEnterSuggestion(SearchContent.SEARCH_ARTICLE);
		navbar.dismissPopupWindow();
    	navbar.clickEnterToSearch();
    	navbar.dismissPopupWindow();
    	
        navbar.triggerSuggestions(SearchContent.SEARCH_SUGGESTION_PHRASE);
		navbar.verifySuggestions(SearchContent.SEARCH_ARTICLE);
		navbar.clickSuggestion(SearchContent.SEARCH_ARTICLE);
		navbar.dismissPopupWindow();
    	navbar.clickSearchButton();
    	navbar.dismissPopupWindow();
    	
        List<JsonObject> expectedEvents = Arrays.asList(
                EventsGlobalNavigation.searchButtonClick
            );
        
        navbar.compareTrackedEventsTo(expectedEvents);
    }
}
