package com.wikia.webdriver.testcases.globalnavigationtests;

import java.util.Arrays;
import java.util.List;

import javax.json.JsonObject;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.common.clicktracking.events.EventsArticleEditMode;
import com.wikia.webdriver.common.clicktracking.events.EventsGlobalNavigation;
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

    String script = "window.onbeforeunload = function () {return 'blocking redirect - performing clicktracking test';}";
    
    @Test(
        groups = {"TestGlobalSearchInGlobalNav_001", "ClickTracking", "GlobalNav"}
    )
    public void hubs() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.openMainPage(wikiURL);
        NavigationBar navbar = new NavigationBar(driver);
        navbar.executeScript(ClickTrackingScriptsProvider.REDIRECT_BLOCK);
        navbar.executeScript(ClickTrackingScriptsProvider.TRACKER_INSTALLATION);
        navbar.searchFor("asd");
        navbar.dismissPopupWindow();
        
        List<JsonObject> expectedEvents = Arrays.asList(
                EventsGlobalNavigation.searchButtonClick
            );
        
        navbar.compareTrackedEventsTo(expectedEvents);
   
    }
}
