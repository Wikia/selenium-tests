package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.FIREFOX, browserSize = "414x736")
public class TestAdsPremiumPrerollMercury extends TemplateNoFirstLoad {

  private static final String MERCURY_STRING = "useskin=mercury";
  private static final Page TEST_PAGE = new Page("project43", "SyntheticTests/Premium/FeaturedVideo");

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsPremiumPrerollMercury"},
      dataProvider = "adsPremiumPreroll"
  )
  public void adsPremiumPrerollMercury() {
    String url = urlBuilder.getUrlForPage(TEST_PAGE);
    url = urlBuilder.appendQueryStringToURL(url, MERCURY_STRING);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);
    wikiPage.verifyArticleAd();
    wikiPage.verifyMobileArticleVideo();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsPremiumPrerollMercury"},
      dataProvider = "adsPremiumPreroll"
  )
  public void adsPremiumPrerollMercuryNoAds() {
    String url = urlBuilder.getUrlForPage(TEST_PAGE);
    url = urlBuilder.appendQueryStringToURL(url, "noads=1" + "&" + MERCURY_STRING);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);
    wikiPage.verifyMobileArticleVideo();
  }
}
