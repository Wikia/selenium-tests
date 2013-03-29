package com.wikia.webdriver.TestCases.SearchTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CrossWikiSearch.CrossWikiSearchPage;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import org.testng.annotations.Test;

/**
 * Author: Artur Dwornik
 * Date: 29.03.13
 * Time: 11:22
 */
public class CrossWikiSearchTests extends TestTemplate {


    @Test(groups= {"CrossWikiSearchTests_001", "CrossWikiSearchTests", "CrossWikiSearchTests_ExactMatch"} )
    public void crossWikiSearch_001_MainPageExactMatch() {
        verifyExactMatch("call of duty", "Call of Duty Wiki", "GAMING");
    }
    @Test(groups= {"CrossWikiSearchTests_002", "CrossWikiSearchTests", "CrossWikiSearchTests_ExactMatch"} )
    public void crossWikiSearch_002_MainPageExactMatch() {
        verifyExactMatch("call-of-duty", "Call of Duty Wiki", "GAMING");
    }
    @Test(groups= {"CrossWikiSearchTests_003", "CrossWikiSearchTests", "CrossWikiSearchTests_ExactMatch"} )
    public void crossWikiSearch_003_MainPageExactMatch() {
        verifyExactMatch("call_of_duty", "Call of Duty Wiki", "GAMING");
    }
    @Test(groups= {"CrossWikiSearchTests_004", "CrossWikiSearchTests", "CrossWikiSearchTests_ExactMatch"} )
    public void crossWikiSearch_004_MainPageExactMatch() {
        verifyExactMatch("callofduty", "Call of Duty Wiki", "GAMING");
    }
    @Test(groups= {"CrossWikiSearchTests_005", "CrossWikiSearchTests", "CrossWikiSearchTests_ExactMatch"} )
    public void crossWikiSearch_005_MainPageExactMatch() {
        verifyExactMatch("cod", "Call of Duty Wiki", "GAMING");
    }
    @Test(groups= {"CrossWikiSearchTests_006", "CrossWikiSearchTests", "CrossWikiSearchTests_ExactMatch"} )
    public void crossWikiSearch_006_MainPageExactMatch() {
        verifyExactMatch("lohgame", "Legacy of Heroes Wiki", "GAMING");
    }

    private void verifyExactMatch(String query, String wikiName, String vertical) {
        CommonFunctions.logOut(driver);
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
}
