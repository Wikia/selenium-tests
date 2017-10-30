package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X, browserSize = "414x736")
public class TestAdsOoyalaMercury extends TemplateNoFirstLoad {

  private static final String OOYALA_VIDEO_MODAL = "file=Synthetic_video_ad_test_(all_green_video)_320x240_(ooyala-stored_video)";
  private static final Page TEST_PAGE = new Page("project43", "SyntheticTests/OoyalaVideo/Simple");

  @Test(
      groups = {"AdsOoyalaPrerollMercury"}
  )
  public void adsOoyalaPrerollMercury() {
    String url = urlBuilder.getUrlForPage(TEST_PAGE);
    url = urlBuilder.appendQueryStringToURL(url, OOYALA_VIDEO_MODAL);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, url);
    wikiPage.verifyLightboxAd();
    wikiPage.verifyLightboxVideo();
  }
}
