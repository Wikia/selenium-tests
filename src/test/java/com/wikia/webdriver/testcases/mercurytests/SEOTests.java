package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SEOPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class SEOTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki("muppet");
    BasePageObject.turnOnMercurySkin(driver, wikiURL);
  }

  // SEOT01
  @Test(groups = {"MercurySEOTest_001", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_001_CheckMetaTagsAndCanonicalLink() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    SEOPageObject seo = new SEOPageObject(driver);
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    Assertion.assertTrue(seo.isLinkRelCanonical(), "Url isn't canonical");
    Assertion.assertTrue(seo.isOgFbApp(), "fb:app_id is wrong");
    Assertion.assertTrue(seo.isOgImage(), "og:image is wrong");
    Assertion.assertTrue(seo.isOgUrlTag(), "og:url meta tag is wrong");
    Assertion.assertTrue(seo.isOgDescription(), "og:description isn't in DOM");
    Assertion.assertFalse(seo.isOgSiteName(), "og:site_name is in DOM");
    Assertion.assertTrue(seo.isOgTitleMainPage(), "og:title meta tag is wrong");
    Assertion.assertTrue(seo.isOgTypeWebsite(), "og:type meta tag is wrong");
    String lastDesc = seo.getDescription();
    leftNav.clickSearchButton();
    leftNav.clickNavListElement(0);
    Assertion.assertTrue(seo.isOgDescription(), "og:description isn't in DOM");
    Assertion.assertFalse(lastDesc.equals(seo.getDescription()),
                          "og:description tags are the same");
    Assertion.assertTrue(seo.isOgSiteName(), "og:site_name isn't in DOM");
    Assertion.assertTrue(seo.isOgTitleArticlePage(), "og:title meta tag is wrong");
    Assertion.assertTrue(seo.isOgTypeArticle(), "og:type meta tag is wrong");
  }
}
