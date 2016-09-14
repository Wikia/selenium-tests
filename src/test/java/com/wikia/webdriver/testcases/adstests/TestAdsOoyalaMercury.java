package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

import java.awt.*;

@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TestAdsOoyalaMercury extends TemplateNoFirstLoad {

  private static final Color GREEN = new Color(4, 253, 6);
  private static final Color BLUE = new Color(4, 0, 254);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 30;

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsOoyalaPrerollMercury"},
      dataProvider = "ooyalaAds"
  )
  public void adsOoyalaPrerollOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.playVideo();
    wikiPage.verifyLightboxAd(BLUE, AD_DURATION_SEC);
    wikiPage.verifyLightboxVideo(GREEN, VIDEO_DURATION_SEC);
  }
}
