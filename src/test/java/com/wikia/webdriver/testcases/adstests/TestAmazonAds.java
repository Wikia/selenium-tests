package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;
import org.testng.annotations.Test;

@Test(groups = "AmazonAds")
public class TestAmazonAds extends TemplateNoFirstLoad {

  @NetworkTrafficDump
  @Test(groups = "AmazonAdsDesktop")
  public void amazonAdsDesktop() {
    checkAmazonSlots(AdsAmazonObject.DESKTOP_SLOTS);
  }

  @Test(groups = "AmazonAdsMobile")
  @NetworkTrafficDump
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void amazonAdsMobile() {
    checkAmazonSlots(AdsAmazonObject.MOBILE_SLOTS);
  }

  @Test(groups = "AmazonAdsDesktop")
  @NetworkTrafficDump(useMITM = true)
  public void testAmazonVideoOasis() {
    testAmazonVideo();
  }

  @Test(groups = "AmazonAdsMobile")
  @NetworkTrafficDump(useMITM = true)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void testAmazonVideoMobile() {
    testAmazonVideo();
  }

  private void checkAmazonSlots(String[] slots) {
    networkTrafficInterceptor.startIntercepting();
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, AdsDataProvider.PAGE_A9_DISPLAY.getUrl());
    amazonAds.runA9DebugMode();

    for (String slotName : slots) {
      amazonAds.verifyTestBidAdInSlot(slotName, AdsAmazonObject.A9_TEST_LINE_ITEM);
    }
  }

  private void testAmazonVideo() {
    networkTrafficInterceptor.startIntercepting();
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, AdsDataProvider.PAGE_FV.getUrl(AdsAmazonObject.A9_VIDEO_DEBUG_MODE));
    amazonAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, AdsAmazonObject.A9_VIDEO_DEBUG_BID_PATTERN);
  }
}
