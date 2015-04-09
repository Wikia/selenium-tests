package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SEOPageObject;

import org.testng.annotations.AfterMethod;
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

  @AfterMethod(alwaysRun = true)
  public void failCheck() {
    Assertion.assertFalse(failTest, "Test logged some errors");
  }

  private boolean failTest = false;

  // SEOT01
  @Test(groups = {"MercurySEOTest_001", "MercurySEOTests", "Mercury"})
  public void MercurySEOTest_001_CheckMetaTagsAndCanonicalLink() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    SEOPageObject seo = new SEOPageObject(driver);
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    if (seo.isLinkRelCanonical()) {
      PageObjectLogging.log("link[rel='canonical']", "contains current url", true);
    } else {
      PageObjectLogging.log("link[rel='canonical']", "contains wrong url", false);
      failTest = true;
    }
    if (seo.isOgFbApp()) {
      PageObjectLogging.log("meta[property='fb:app_id']", "is filled", true);
    } else {
      PageObjectLogging.log("meta[property='fb:app_id']", "is empty", false);
      failTest = true;
    }
    if (seo.isOgImage()) {
      PageObjectLogging.log("meta[property='og:image']", "is filled", true);
    } else {
      PageObjectLogging.log("meta[property='og:image']", "is empty", false);
      failTest = true;
    }
    if (seo.isOgUrlTag()) {
      PageObjectLogging.log("meta[property='og:url']", "contains current url", true);
    } else {
      PageObjectLogging.log("meta[property='og:url']", "contains wrong url", false);
      failTest = true;
    }
    if (seo.isOgDescription()) {
      PageObjectLogging.log("meta[property='og:description']", "is filled", true);
    } else {
      PageObjectLogging.log("meta[property='og:description']", "is empty", false);
      failTest = true;
    }
    if (seo.isOgSiteName()) {
      PageObjectLogging.log("meta[property='og:site_name']", "is filled", false);
      failTest = true;
    } else {
      PageObjectLogging.log("meta[property='og:site_name']", "is empty", true);
    }
    if (seo.isOgTitleWithWiki()) {
      PageObjectLogging.log("meta[property='og:title']", "contains Wiki", true);
    } else {
      PageObjectLogging.log("meta[property='og:title']", "is wrong", false);
      failTest = true;
    }
    if (seo.isOgTypeWebsite()) {
      PageObjectLogging.log("meta[property='og:type']", "contains website", true);
    } else {
      PageObjectLogging.log("meta[property='og:type']", "is wrong", false);
      failTest = true;
    }
    String lastDesc = seo.getDescription();
    leftNav.clickSearchButton();
    leftNav.clickNavListElement(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.appendTextToLogFile(new StringBuilder()
                                              .append(
                                                  "<tr class=\"warning\"><td>Site status</td>"
                                                  + "<td>Page was reloaded asynchronously</td>"
                                                  + "<td> <br/> &nbsp;</td></tr>"));
    if (seo.isOgDescription()) {
      PageObjectLogging.log("meta[property='og:description']", "is filled", true);
    } else {
      PageObjectLogging.log("meta[property='og:description']", "is empty", false);
      failTest = true;
    }
    if (lastDesc.equals(seo.getDescription())) {
      PageObjectLogging.log("meta[property='og:description']", "does not changed", false);
      failTest = true;
    } else {
      PageObjectLogging.log("meta[property='og:description']", "is different", true);
    }
    if (seo.isOgSiteName()) {
      PageObjectLogging.log("meta[property='og:site_name']", "is filled", true);
    } else {
      PageObjectLogging.log("meta[property='og:site_name']", "is empty", false);
      failTest = true;
    }
    if (seo.isOgTitleWithWiki()) {
      PageObjectLogging.log("meta[property='og:title']", "contains Wiki", true);
    } else {
      PageObjectLogging.log("meta[property='og:title']", "is wrong", false);
      failTest = true;
    }
    if (seo.isOgTypeArticle()) {
      PageObjectLogging.log("meta[property='og:type']", "contains article", true);
    } else {
      PageObjectLogging.log("meta[property='og:type']", "is wrong", false);
      failTest = true;
    }
  }
}
