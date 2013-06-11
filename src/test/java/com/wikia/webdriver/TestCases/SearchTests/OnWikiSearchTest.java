package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.DataProvider.SearchDataProvider;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CrossWikiSearch.CrossWikiSearchPage;

public class OnWikiSearchTest extends TestTemplate {

	@Test(dataProviderClass=SearchDataProvider.class,
			dataProvider="getOnWikiHostsTermsAndMatchUrls",
	          groups = {"OnWikiSearch_001_articlematch", "", "Search"})
		public void OnWikiSearch_001_articlematch( String searchHost, String searchTerm, String expectedUrl ) {
			CrossWikiSearchPage search = new CrossWikiSearchPage(driver);
			search.goToSearchPage(searchHost);
			CrossWikiSearchPage searched = search.searchFor(searchTerm);
			searched.verifyMatchResultUrl(expectedUrl);
		}
}
