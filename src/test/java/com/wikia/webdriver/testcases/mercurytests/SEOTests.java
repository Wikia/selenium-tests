package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.OpenGraphPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @ownership: Mobile Web
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class SEOTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    wikiURL = urlBuilder.getUrlForWiki("muppet");
    MercuryContent.turnOnMercurySkin(driver, wikiURL);
  }

  // SEOT01
  @Test(groups = {"MercurySEOTest_001", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_001_CheckTypeMetaTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    Assertion.assertTrue(openGraph.isOgTypeWebsite(), "og:type meta tag is wrong");
    leftNav.clickSearchButton();
    leftNav.clickRandomPage();
    Assertion.assertTrue(openGraph.isOgTypeArticle(), "og:type meta tag is wrong");
  }

  // SEOT02
  @Test(groups = {"MercurySEOTest_002", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_002_CheckTitleMetaTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    Assertion.assertTrue(openGraph.isOgTitleMainPage(), "og:title meta tag is wrong");
    leftNav.clickSearchButton();
    leftNav.clickRandomPage();
    Assertion.assertTrue(openGraph.isOgTitleArticlePage(), "og:title meta tag is wrong");
  }

  // SEOT03
  @Test(groups = {"MercurySEOTest_003", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_003_CheckSiteNameTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    Assertion.assertFalse(openGraph.isOgSiteName(), "og:site_name is in DOM");
    leftNav.clickSearchButton();
    leftNav.clickRandomPage();
    Assertion.assertTrue(openGraph.isOgSiteName(), "og:site_name isn't in DOM");
  }

  // SEOT04
  @Test(groups = {"MercurySEOTest_004", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_004_CheckDescriptionTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    Assertion.assertTrue(openGraph.isOgDescription(), "og:description isn't in DOM");
    String lastDesc = openGraph.getDescription();
    leftNav.clickSearchButton();
    leftNav.clickRandomPage();
    Assertion.assertTrue(openGraph.isOgDescription(), "og:description isn't in DOM");
    Assertion.assertFalse(lastDesc.equals(openGraph.getDescription()), "og:description tags are the same");
  }

  // SEOT05
  @Test(groups = {"MercurySEOTest_005", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_005_CheckUrlTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    Assertion.assertTrue(openGraph.isOgUrlTag(), "og:url meta tag is wrong");
  }

  // SEOT06
  @Test(groups = {"MercurySEOTest_006", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_006_CheckImageTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    Assertion.assertTrue(openGraph.isOgImage(), "og:image is wrong");
  }

  // SEOT07
  @Test(groups = {"MercurySEOTest_007", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_007_CheckFbAppTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    Assertion.assertTrue(openGraph.isOgFbApp(), "fb:app_id is wrong");
  }
}
