package com.wikia.webdriver.testcases.multiwikifindertests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.multiwikifinder.SpecialMultiWikiFinderPageObject;

/**
 * @author łukasz
 */
public class MultiWikiFinderTests extends NewTestTemplate {

  private String pageName = "Wiki";
  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"MultiWikiFinder_001", "MultiWikiFinder"})
  public void multiWikiFinderTests_001_notExistingPagename() {
    SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
    multiWikiFinder.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    multiWikiFinder.openSpecialMultiWikiFinderPage(wikiURL);
    multiWikiFinder.findPageName(multiWikiFinder.getTimeStamp());
    multiWikiFinder.compareResultsCount(0);
  }

  @Test(groups = {"MultiWikiFinder_002", "MultiWikiFinder"})
  public void multiWikiFinderTests_002_maxAmoutOfLinksOnPage() {
    SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
    multiWikiFinder.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    multiWikiFinder.openSpecialMultiWikiFinderPage(wikiURL);
    multiWikiFinder.findPageName(pageName);
    multiWikiFinder.checkAllLimits();
  }

  @Test(groups = {"MultiWikiFinder_003", "MultiWikiFinder"})
  @Execute(asUser = User.STAFF)
  public void multiwikiFinderTests_003_checkPagination() {
    SpecialMultiWikiFinderPageObject multiWikiFinder =
        new SpecialMultiWikiFinderPageObject(driver).openSpecialMultiWikiFinderPage(wikiURL);
    multiWikiFinder.findPageName(pageName);
    multiWikiFinder.verifyPagination();
  }

  @Test(dataProviderClass = ArticleDataProvider.class, dataProvider = "getPageNames", groups = {
      "MultiWikiFinder_004", "MultiWikiFinder"})
  public void multiWikifinderTests_004_pagenameInPath(String popularPagename) {
    SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
    multiWikiFinder.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    multiWikiFinder.openSpecialMultiWikiFinderPage(wikiURL);
    multiWikiFinder.findPageName(popularPagename);
    multiWikiFinder.verifyAllLinksHavePagenameInPath(popularPagename);
  }

  @Test(groups = {"MultiWikiFinder_005", "MultiWikiFinder"})
  public void multiWikiFinderTests_005_emptyPagename() {
    SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
    multiWikiFinder.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    multiWikiFinder.openSpecialMultiWikiFinderPage(wikiURL);
    multiWikiFinder.findPageName("");
    multiWikiFinder.verifyEmptyPagename();
  }
}
