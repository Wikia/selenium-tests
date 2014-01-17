package com.wikia.webdriver.TestCases.SearchTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.Common.Clicktracking.TestExpectedEvents.EventsCrossWikiSearchTests;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

public class ClickTrackingCrossWikiSearchTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"CrossWikiSearchTests_001", "Search", "CrossWikiSearch"})
	public void ClicktrackingCrossWikiSearch_001_verifyEvents() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.executeScript(ClickTrackingScriptsProvider.trackerInstallation);
		search.searchForEnter("Mario");
		search.executeScript(ClickTrackingScriptsProvider.trackerInstallation);
		for (int i = 1; i < 7; i++) {
			WikiArticleHomePage home = search.openResult(i);
			search.executeScript(ClickTrackingScriptsProvider.trackerInstallation);
			home.navigateBack();
			search.executeScript(ClickTrackingScriptsProvider.trackerInstallation);
		}
		search.searchFor("noresultsatall");
		search.executeScript(ClickTrackingScriptsProvider.trackerInstallation);

		List<String> expectedEvents;
		expectedEvents = EventsCrossWikiSearchTests.getExpectedEventsForTest001();
		search.compareTrackedEventsTo(expectedEvents);
	}
}