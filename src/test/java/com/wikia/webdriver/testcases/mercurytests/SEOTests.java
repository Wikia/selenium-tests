package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SearchNavSideMenuComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
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
    MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    openGraph.verifyOgTypeWebsite();
    SearchNavSideMenuComponentObject leftNav = article.clickSearchButton();
    leftNav.clickRandomPage();
    openGraph.verifyOgTypeArticle();
  }

  // SEOT02
  @Test(groups = {"MercurySEOTest_002", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_002_CheckTitleMetaTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    openGraph.verifyOgTitleMainPage();
    SearchNavSideMenuComponentObject leftNav = article.clickSearchButton();
    leftNav.clickRandomPage();
    openGraph.verifyOgTitleArticlePage();
  }

  // SEOT03
  @Test(groups = {"MercurySEOTest_003", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_003_CheckSiteNameTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    openGraph.verifyOgSiteNameNotExists();
    SearchNavSideMenuComponentObject leftNav = article.clickSearchButton();
    leftNav.clickRandomPage();
    openGraph.verifyOgSiteNameExists();
  }

  // SEOT04
  @Test(groups = {"MercurySEOTest_004", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_004_CheckDescriptionTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article = base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    openGraph.verifyOgDescription();
    String descOne = openGraph.getDescription();
    System.out.println(descOne);
    SearchNavSideMenuComponentObject leftNav = article.clickSearchButton();
    leftNav.clickRandomPage();
    openGraph.verifyOgDescription();
    String descTwo = openGraph.getDescription();
    System.out.println(descTwo);
    openGraph.verifyOgDescriptionTagWasChanged(descOne, descTwo);
  }

  // SEOT05
  @Test(groups = {"MercurySEOTest_005", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_005_CheckUrlTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    openGraph.verifyOgUrlTag();
  }

  // SEOT06
  @Test(groups = {"MercurySEOTest_006", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_006_CheckImageTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    openGraph.verifyOgImage();
  }

  // SEOT07
  @Test(groups = {"MercurySEOTest_007", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_007_CheckFbAppTag() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    OpenGraphPageObject openGraph = new OpenGraphPageObject(driver);
    openGraph.verifyOgFbApp();
  }
}
