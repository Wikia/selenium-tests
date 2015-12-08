package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.clicktracking.events.EventsGlobalNavigation;
import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import javax.json.JsonObject;

/**
 * @ownership Content X-Wing Wikia
 */
public class GlobalNavigationClicktracking extends NewTestTemplate {

  /**
   * search test flow: action - expected event navigate to main page
   *
   * trigger suggestion - search-suggest-show click enter on suggestion - search-suggest-enter click
   * enter after suggestion - search-after-suggest-enter clear suggestion
   *
   * trigger suggestion mouse click on suggestion - search-suggest click search after suggestion -
   * search-after-suggest-button clear suggestion
   */
  @RelatedIssue(issueID = "MAIN-5917",
      comment = "Automation test is broken. NB Not possible to test manually")
  @Test(groups = {"ClicktrackingGlobalSearch", "ClickTracking", "GlobalNav",
                  "TestGlobalNavClickTracking_001"}, enabled = false)
  public void TestGlobalNavClickTracking_001() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.openMainPage(wikiURL);
    NavigationBar navbar = new NavigationBar(driver);
    navbar.setUpTracking();

    navbar.triggerSuggestions(SearchContent.SEARCH_SUGGESTION_PHRASE);
    navbar.verifySuggestions(SearchContent.SEARCH_ARTICLE);
    navbar.ArrowDownAndEnterSuggestion(SearchContent.SEARCH_ARTICLE);
    AlertHandler.dismissPopupWindow(driver);
    navbar.clickSearchButton();
    AlertHandler.dismissPopupWindow(driver);

    navbar.triggerSuggestions(SearchContent.SEARCH_SUGGESTION_PHRASE);
    navbar.verifySuggestions(SearchContent.SEARCH_ARTICLE);
    navbar.clickSuggestion(SearchContent.SEARCH_ARTICLE);
    AlertHandler.dismissPopupWindow(driver);
    navbar.clickEnterToSearch();
    AlertHandler.dismissPopupWindow(driver);

    List<JsonObject> expectedEvents =
        Arrays.asList(EventsGlobalNavigation.searchSuggestShow,
                      EventsGlobalNavigation.searchSuggestionEnter,
                      EventsGlobalNavigation.searchAfterSuggestionEnter,
                      EventsGlobalNavigation.searchAfterSuggestionButtonClick);

    navbar.compareTrackedEventsTo(expectedEvents);
  }
}
