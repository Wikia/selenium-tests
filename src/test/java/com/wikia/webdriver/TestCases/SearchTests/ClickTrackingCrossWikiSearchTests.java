package com.wikia.webdriver.TestCases.SearchTests;

import org.openqa.selenium.remote.server.handler.ExecuteScript;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Clicktracking.ClickTrackingScripts;
import com.wikia.webdriver.Common.DataProvider.CrossWikiSearchProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;

public class ClickTrackingCrossWikiSearchTests extends NewTestTemplate {
	
	Credentials credentials = config.getCredentials();

	@Test(groups = {"CrossWikiSearchTests_001", "Search", "CrossWikiSearch"})
	public void ClicktrackingCrossWikiSearch_001_verifyEvents() {
		CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
		search.goToSearchPage(wikiCorporateURL);
		search.executeScript(ClickTrackingScripts.trackerInstallation);
		search.searchFor("Mario");
//		search.verifyFirstResultTitle(wikiName);
//		search.verifyFirstResultVertical(vertical);
		search.verifyFirstResultDescription();
		search.verifyFirstResultPageCount();
		search.verifyFirstResultPageImages();
		search.verifyFirstResultPageVideos();

}

}	