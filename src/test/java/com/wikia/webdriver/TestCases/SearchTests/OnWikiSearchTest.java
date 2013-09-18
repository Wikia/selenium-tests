package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.DataProvider.SearchDataProvider;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;

public class OnWikiSearchTest extends TestTemplate {

	@Test(dataProviderClass=SearchDataProvider.class,
			dataProvider="getOnWikiHostsTermsAndMatchUrls",
	          groups = {"OnWikiSearch_001_articlematch", "OnWikiSearch", "Search"})
		public void OnWikiSearch_001_articlematch( String searchHost, String searchTerm, String expectedUrl ) {
			CrossWikiSearchPageObject search = new CrossWikiSearchPageObject(driver);
			search.goToSearchPage(searchHost);
			CrossWikiSearchPageObject searched = search.searchFor(searchTerm);
			searched.verifyMatchResultUrl(expectedUrl);
		}
}
