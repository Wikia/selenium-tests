package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
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
    NavigationSideComponentObject leftNav = new NavigationSideComponentObject(driver);
    SEOPageObject seo = new SEOPageObject(driver);
    seo.openMercuryArticleByName(wikiURL, MUPPET_MAIN_PAGE);

    // Uncomment after issue is fixed - related to HG-668
    /*PageObjectLogging.log(
        "link[rel='canonical']",
        "contains current url",
        "contains wrong url",
        seo.isLinkRelCanonical()
    );*/

    boolean result = seo.isOgFbApp();
    PageObjectLogging.log(
        "meta[property='fb:app_id']",
        "is filled",
        "is empty",
        result
    );

    result = seo.isOgImage();
    PageObjectLogging.log(
        "meta[property='og:image']",
        "is filled",
        "is empty",
        result
    );

    result = seo.isOgUrlTag();
    PageObjectLogging.log(
        "meta[property='og:url']",
        "contains current url",
        "contains wrong url",
        result
    );

    result = seo.isOgDescription();
    PageObjectLogging.log(
        "meta[property='og:description']",
        "is filled",
        "is empty",
        result
    );

    result = !seo.isOgSiteName();
    PageObjectLogging.log(
        "meta[property='og:site_name']",
        "is empty",
        "is filled",
        result
    );

    result = seo.isOgTitleWithWiki();
    PageObjectLogging.log(
        "meta[property='og:title']",
        "contains Wiki",
        "is wrong",
        result
    );

    result = seo.isOgTypeWebsite();
    PageObjectLogging.log(
        "meta[property='og:type']",
        "contains website",
        "is wrong",
        result
    );

    // Uncomment after issue is fixed - related to HG-605
    /*String lastDesc = seo.getDescription();
    leftNav.clickSearchButton();
    leftNav.clickNavListElement(0);
    seo.waitForLoadingSpinnerToFinish();

    PageObjectLogging.logWarning(
        "Site status",
        "Page was reloaded asynchronously"
    );

    PageObjectLogging.log(
        "meta[property='og:description']",
        "is filled",
        "is empty",
        seo.isOgDescription()
    );

    PageObjectLogging.log(
        "meta[property='og:description']",
        "is different",
        "does not changed",
        !lastDesc.equals(seo.getDescription())
    );

    PageObjectLogging.log(
        "meta[property='og:site_name']",
        "is filled",
        "is empty",
        seo.isOgSiteName()
    );

    PageObjectLogging.log(
        "meta[property='og:title']",
        "contains Wiki",
        "is wrong",
        seo.isOgTitleWithWiki()
    );

    PageObjectLogging.log(
        "meta[property='og:type']",
        "contains article",
        "is wrong",
        seo.isOgTypeArticle()
    );*/
  }
}
