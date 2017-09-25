package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;
import org.testng.annotations.Test;

public class TestAmazonAds extends TemplateNoFirstLoad {
  private static final String A9_TEST_LINE_ITEM = "4397742201";
  private static final Page TEST_PAGE = new Page("project43", "SyntheticTests/Amazon");

  private static final String[] DESKTOP_SLOTS = {
          AdsContent.TOP_LB,
          AdsContent.MEDREC
  };

  private static final String[] MOBILE_SLOTS = {
          AdsContent.MOBILE_TOP_LB,
          AdsContent.MOBILE_AD_IN_CONTENT,
          AdsContent.MOBILE_PREFOOTER
  };

  @NetworkTrafficDump
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AmazonAds", "AmazonAdsDesktop"}
  )
  public void amazonAdsDesktop() {
    checkAmazonSlots(DESKTOP_SLOTS);
  }

  @NetworkTrafficDump
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AmazonAds", "AmazonAdsMobile"}
  )
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void amazonAdsMobile() {
    checkAmazonSlots(MOBILE_SLOTS);
  }

  private void checkAmazonSlots(String[] slots) {
    networkTrafficInterceptor.startIntercepting();
    AdsAmazonObject amazonAds = new AdsAmazonObject(driver, TEST_PAGE.getUrl());
    amazonAds.runA9DebugMode();

    for (String slotName : slots) {
      amazonAds.verifyTestBidAdInSlot(slotName, A9_TEST_LINE_ITEM);
    }
  }
}
