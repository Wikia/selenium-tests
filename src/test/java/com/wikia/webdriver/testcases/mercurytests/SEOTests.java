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
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class SEOTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki("muppet");
  }

  private boolean failTest = false;

  // SEOT01
  @RelatedIssue(issueID = "CONCF-412")
  @Test(groups = {"MercurySEOTest_001", "MercurySEOTests", "Mercury"}, enabled = false)
  public void MercurySEOTest_001_MetaTags_CanonicalLink() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, "");
    SEOPageObject seo = new SEOPageObject(driver);
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    PageObjectLogging.log("link[rel='canonical']", "contains current url", "contains wrong url", seo.isLinkRelCanonical());
    if(! seo.isLinkRelCanonical()) failTest = true;
    PageObjectLogging.log("meta[property='fb:app_id']", "is filled", "is empty", seo.isOgFbApp());
    if(! seo.isOgFbApp()) failTest = true;
    PageObjectLogging.log("meta[property='og:image']", "is filled", "is empty", seo.isOgImage());
    if(! seo.isOgImage()) failTest = true;
    PageObjectLogging.log("meta[property='og:url']", "contains current url", "contains wrong url", seo.isOgUrlTag());
    if(! seo.isOgUrlTag()) failTest = true;
    PageObjectLogging.log("meta[property='og:description']", "is filled", "is empty", seo.isOgDescription());
    if(! seo.isOgDescription()) failTest = true;
    PageObjectLogging.log("meta[property='og:site_name']", "is empty", "is filled", ! seo.isOgSiteName());
    if(seo.isOgSiteName()) failTest = true;
    PageObjectLogging.log("meta[property='og:title']", "contains Wiki", "is wrong", seo.isOgTitleWithWiki());
    if(! seo.isOgTitleWithWiki()) failTest = true;
    PageObjectLogging.log("meta[property='og:type']", "contains website", "is wrong", seo.isOgTypeWebsite());
    if(! seo.isOgTypeWebsite()) failTest = true;
    String lastDesc = seo.getDescription();
    leftNav.clickSearchButton();
    leftNav.clickNavListElement(0);
    base.waitForLoadingSpinnerToFinishReloadingPage();
    PageObjectLogging.appendTextToLogFile(new StringBuilder()
                                              .append(
                                                  "<tr class=\"warning\"><td>Site status</td>"
                                                  + "<td>Page was reloaded asynchronously</td>"
                                                  + "<td> <br/> &nbsp;</td></tr>"));
    PageObjectLogging.log("meta[property='og:description']", "is filled", "is empty", seo.isOgDescription());
    if(! seo.isOgDescription()) failTest = true;
    PageObjectLogging.log("meta[property='og:description']", "is different", "does not changed", ! lastDesc.equals(seo.getDescription()));
    if(lastDesc.equals(seo.getDescription())) failTest = true;
    PageObjectLogging.log("meta[property='og:site_name']", "is filled", "is empty", seo.isOgSiteName());
    if(! seo.isOgSiteName()) failTest = true;
    PageObjectLogging.log("meta[property='og:title']", "contains Wiki", "is wrong", seo.isOgTitleWithWiki());
    if(! seo.isOgTitleWithWiki()) failTest = true;
    PageObjectLogging.log("meta[property='og:type']", "contains article", "is wrong", seo.isOgTypeArticle());
    if(! seo.isOgTypeArticle()) failTest = true;
    base.failTest(failTest);
  }
}
