package com.wikia.webdriver.TestCases.SearchTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.DataProvider.CrossWikiSearchProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CrossWikiSearch.CrossWikiSearchPage;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiHomePage;
import org.testng.annotations.Test;

/**
 * Author: Artur Dwornik
 * Date: 29.03.13
 * Time: 11:22
 */
public class CrossWikiSearchTests extends TestTemplate {
    private static final int resultsPerPage = 7;
    private static final String searchPhrase = "muppets";

    @Test(dataProviderClass = CrossWikiSearchProvider.class,
            dataProvider = "getExactMatchQueries",
            groups = {"CrossWikiSearchTests", "CrossWikiSearchTests_ExactMatch" })
    public void testExactMatch(String query, String wikiName, String vertical) {
        HomePageObject home = new HomePageObject(driver);
        home.openHomePage();
        CrossWikiSearchPage searchPage = home.searchFor(query);
        searchPage.verifyFirstResultTitle(wikiName);
        searchPage.verifyFirstResultVertical(vertical);
        searchPage.verifyFirstResultDescription();
        searchPage.verifyFirstResultPageCount();
        searchPage.verifyFirstResultPageImages();
        searchPage.verifyFirstResultPageVideos();
    }

    @Test(groups= {"CrossWikiSearchTests_Pagination_001"
                 , "CrossWikiSearchTests"
                 , "CrossWikiSearchTests_Pagination"} )
    public void crossWikiSearchTests_Pagination_001() {
        HomePageObject home = new HomePageObject(driver);
        home.openHomePage();
        CrossWikiSearchPage searchPage = home.searchFor(searchPhrase);

        // verify results pos parameter for first page
        searchPage.verifyResultsPosForPage(0, resultsPerPage);
        searchPage.verifyResultsCount(resultsPerPage);
        searchPage.nextPage();
        // verify results pos parameter for second page
        searchPage.verifyResultsPosForPage(1, resultsPerPage);
        searchPage.verifyResultsCount(resultsPerPage);
        searchPage.prevPage();
        // verify results pos parameter for first page
        searchPage.verifyResultsPosForPage(0, resultsPerPage);
        searchPage.verifyResultsCount(resultsPerPage);
    }

    @Test(groups= {"CrossWikiSearchTests_ResultClick_001"
                 , "CrossWikiSearchTests"
                 , "CrossWikiSearchTests_ResultClick"} )
    public void CrossWikiSearchTests_ResultClick_001() {
        HomePageObject home = new HomePageObject(driver);
        home.openHomePage();
        CrossWikiSearchPage searchPage = home.searchFor(searchPhrase);

        WikiHomePage wikiHomePage = searchPage.openResult(0);
        wikiHomePage.verifyThisIsWikiHomePage();
    }

    @Test(groups= {"CrossWikiSearchTests_ResultClick_002"
                 , "CrossWikiSearchTests"
                 , "CrossWikiSearchTests_ResultClick"} )
    public void CrossWikiSearchTests_ResultClick_002() {
        HomePageObject home = new HomePageObject(driver);
        home.openHomePage();
        CrossWikiSearchPage searchPage = home.searchFor(searchPhrase);

        WikiHomePage wikiHomePage = searchPage.openResult(1);
        wikiHomePage.verifyThisIsWikiHomePage();
    }
}
