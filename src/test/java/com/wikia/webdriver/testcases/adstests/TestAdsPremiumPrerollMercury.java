package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

@InBrowser(
    browser = Browser.CHROME_MOBILE,
    browserSize = "414x736"
)
public class TestAdsPremiumPrerollMercury extends TemplateNoFirstLoad {

  private static final String NO_ADS = "noads=1";

  private static final Page TEST_PAGE = new Page("project43", "SyntheticTests/Premium/FeaturedVideo");

  @Test(
      groups = {"AdsPremiumPrerollMercury"}
  )
  public void adsPremiumPrerollMercury() {
    String url = urlBuilder.getUrlForPage(TEST_PAGE);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);
    wikiPage.playFeaturedVideo();
    wikiPage.verifyArticleAd();
    wikiPage.verifyMobileArticleVideo();
  }

  @Test(
      groups = {"AdsPremiumPrerollMercury"}
  )
  public void adsPremiumPrerollMercuryNoAds() {
    String url = urlBuilder.getUrlForPage(TEST_PAGE);
    url = urlBuilder.appendQueryStringToURL(url, NO_ADS);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);
    wikiPage.playFeaturedVideo();
    wikiPage.verifyMobileArticleVideo();
  }
}
