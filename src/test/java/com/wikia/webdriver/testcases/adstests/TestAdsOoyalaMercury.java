package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME_MOBILE, browserSize = "414x736")
public class TestAdsOoyalaMercury extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsOoyalaPrerollMercury"},
      dataProvider = "ooyalaAds"
  )
  public void adsOoyalaPrerollMercury(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.playLightboxVideo();
    wikiPage.verifyLightboxAd();
    wikiPage.verifyLightboxVideo();
  }
}

//browser = Browser.FIREFOX, browserSize = "414x736"
