package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.Common.DataProvider.SearchDataProvider;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialSearchPageObject;

public class OnWikiSearchTest extends TestTemplate {

	@Test(dataProviderClass=SearchDataProvider.class,
			dataProvider="getOnWikiHostsTermsAndMatchUrls",
	          groups = {"OnWikiSearch_001_articlematch", "", "Search"})
		public void OnWikiSearch_001_articlematch( String searchHost, String searchTerm, String expectedUrl ) {
			SpecialSearchPageObject search = new SpecialSearchPageObject(driver);
			search.goToSearchPage(searchHost);
			SpecialSearchPageObject searched = search.searchFor(searchTerm);
			searched.verifyMatchResultUrl(expectedUrl);
		}
	
}
