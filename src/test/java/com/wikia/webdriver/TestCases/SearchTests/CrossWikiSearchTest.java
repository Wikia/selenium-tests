package com.wikia.webdriver.TestCases.SearchTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.Common.DataProvider.SearchDataProvider;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.CustomizedToolbar.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialSearchPageObject;

public class CrossWikiSearchTest extends TestTemplate {
	
	
	@Test(dataProviderClass=SearchDataProvider.class,
          dataProvider="getCrossWikiTermsAndUrls",
          groups = {"CrossWikiSearch_001_wikimatch", "", "Search"})
	public void CrossWikiSearch_001_wikimatch( String searchTerm, String expectedUrl ) {
		SpecialSearchPageObject search = new SpecialSearchPageObject(driver);
		search.goToSearchPage(PageContent.wikiaGlobalUrl);
		SpecialSearchPageObject searched = search.searchFor(searchTerm);
		searched.verifyMatchResultUrl(expectedUrl);
	}
	
}