package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Device;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.article.ArticleNavigationComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.article.OasisFooterComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.MercuryFooterComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Execute(
    onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING,
    onDevice = Device.GOOGLE_NEXUS_5,
    browser = Browser.CHROME
)
public class RedirectionTests extends NewTestTemplate {

  private static final String ARTICLE_NAME = MercurySubpages.MAIN_PAGE;
  private static final String QUERY_STRING = "noads=1";
  private String url;
  private String expectedUrl;

  @BeforeMethod(alwaysRun = true)
  public void prepareURL() {
    String articleUrl = urlBuilder.getWebUrlForPath(Configuration.getWikiName(), ARTICLE_NAME);
    url = urlBuilder.appendQueryStringToURL(articleUrl, QUERY_STRING);

    String expectedArticleUrl = urlBuilder.getUrlForPath(Configuration.getWikiName(), ARTICLE_NAME);
    expectedUrl = urlBuilder.appendQueryStringToURL(expectedArticleUrl, QUERY_STRING);
  }

  @Test(groups = "MercuryRedirectionTest_001")
  public void MercuryRedirectionTest_001_RedirectFromWWW() {
    new ArticlePageObject(driver).openWikiPage(url);
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);
  }

  private void redirectFromFullSiteToOasis() {
    ArticleNavigationComponentObject navigation = new ArticleNavigationComponentObject(driver);
    SkinHelper helper = new SkinHelper(driver);

    new ArticlePageObject(driver).openWikiPage(url);
    new MercuryFooterComponentObject(driver).clickFullSiteLink();
    navigation.clickRandomArticle();

    Assertion.assertTrue(helper.isSkin(Skin.OASIS));
  }

  @Test(groups = "MercuryRedirectionTest_002")
  public void MercuryRedirectionTest_002_RedirectFromFullSiteToOasisAsAnon() {
    redirectFromFullSiteToOasis();
  }

  @Test(groups = "MercuryRedirectionTest_003")
  @Execute(asUser = User.USER)
  public void MercuryRedirectionTest_003_RedirectFromFullSiteToOasisAsLoggedInUser() {
    redirectFromFullSiteToOasis();
  }

  @Test(groups = "MercuryRedirectionTest_004")
  @Execute(browser = Browser.CHROME_ANDROID)
  public void MercuryRedirectionTest_004_RedirectFromFullSiteToMobile() {
    OasisFooterComponentObject oasisFooter = new OasisFooterComponentObject(driver);
    SkinHelper helper = new SkinHelper(driver);
    NavigationSideComponentObject navigation = new NavigationSideComponentObject(driver);

    new ArticlePageObject(driver).openWikiPage(url);
    new MercuryFooterComponentObject(driver).clickFullSiteLink();
    oasisFooter.clickMobileSiteLink();
    navigation.clickSearchButton();
    navigation.clickRandomPageButton();

    Assertion.assertTrue(helper.isSkin(Skin.MERCURY));
  }
}
