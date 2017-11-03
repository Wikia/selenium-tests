package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;
import org.testng.annotations.Test;

@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5,
    browserSize = "414x736"
)
public class TestAdsPremiumPrerollMercury extends TemplateNoFirstLoad {

  private static final String NO_ADS = "noads=1";

  @Test(
      groups = {"AdsPremiumPrerollMercury"}
  )
  public void adsPremiumPrerollMercury() {
    String url = urlBuilder.getUrlForPage(AdsDataProvider.PAGE_FV_JWPLAYER);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);
    wikiPage.verifyArticleAd();
    wikiPage.verifyMobileArticleVideo();
  }

  @Test(
      groups = {"AdsPremiumPrerollMercury"}
  )
  public void adsPremiumPrerollMercuryNoAds() {
    String url = urlBuilder.getUrlForPage(AdsDataProvider.PAGE_FV_JWPLAYER);
    url = urlBuilder.appendQueryStringToURL(url, NO_ADS);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);
    wikiPage.verifyMobileArticleVideo();
  }
}
