package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SEOPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership Content X-Wing
 */
public class SEOTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki("muppet");
  }

  private static final String MUPPET_MAIN_PAGE = "Muppet_Wiki";

  // SEOT01
  @RelatedIssue(issueID = "HG-671")
  @Test(groups = {"MercurySEOTest_001", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_001_MetaTags_CanonicalLink() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MUPPET_MAIN_PAGE);
    SEOPageObject seo = new SEOPageObject(driver);
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    // Uncomment after issue is fixed - related to HG-668
    /*PageObjectLogging.log("link[rel='canonical']", "contains current url", "contains wrong url",
                          seo.isLinkRelCanonical());*/
    PageObjectLogging.log("meta[property='fb:app_id']", "is filled", "is empty", seo.isOgFbApp());
    PageObjectLogging.log("meta[property='og:image']", "is filled", "is empty", seo.isOgImage());
    PageObjectLogging.log("meta[property='og:url']", "contains current url", "contains wrong url",
                          seo.isOgUrlTag());
    PageObjectLogging
        .log("meta[property='og:description']", "is filled", "is empty", seo.isOgDescription());
    PageObjectLogging
        .log("meta[property='og:site_name']", "is empty", "is filled", !seo.isOgSiteName());
    PageObjectLogging
        .log("meta[property='og:title']", "contains Wiki", "is wrong", seo.isOgTitleWithWiki());
    PageObjectLogging
        .log("meta[property='og:type']", "contains website", "is wrong", seo.isOgTypeWebsite());
    // Uncomment after issue is fixed - related to HG-605
    /*String lastDesc = seo.getDescription();
    leftNav.clickSearchButton();
    leftNav.clickNavListElement(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.logWarning("Site status", "Page was reloaded asynchronously");
    PageObjectLogging
        .log("meta[property='og:description']", "is filled", "is empty", seo.isOgDescription());
    PageObjectLogging.log("meta[property='og:description']", "is different", "does not changed",
                          !lastDesc.equals(seo.getDescription()));
    PageObjectLogging
        .log("meta[property='og:site_name']", "is filled", "is empty", seo.isOgSiteName());
    PageObjectLogging
        .log("meta[property='og:title']", "contains Wiki", "is wrong", seo.isOgTitleWithWiki());
    PageObjectLogging
        .log("meta[property='og:type']", "contains article", "is wrong", seo.isOgTypeArticle());*/
  }
}
