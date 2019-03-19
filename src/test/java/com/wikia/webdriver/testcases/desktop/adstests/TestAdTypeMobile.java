package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * https://www.google.com/dfp/5441#delivery/OrderDetail/orderId=245575332
 */
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Test(groups = "AdTypeMercury")
public class TestAdTypeMobile extends MobileTestTemplate {

  private static final String
      DFP_IMAGE_URL
      = "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg";

  @Test
  public void adsAdTypeAsyncSuccessWithAd() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260851332&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/Async/Success");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(page.getUrl());
    ads.scrollToRecirculationPrefooter();
    ads.waitForSlot(AdsContent.MOBILE_BOTTOM_LB);
    ads.verifyImgAdLoadedInSlot(AdsContent.MOBILE_BOTTOM_LB, DFP_IMAGE_URL);
  }

  @Test
  public void adsAdTypeAsyncSuccessNoAd() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261075132&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/Async/Success/NoAd");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(page.getUrl());
    ads.scrollToRecirculationPrefooter();
    ads.wait.forElementPresent(By.id(AdsContent.MOBILE_BOTTOM_LB));
    ads.verifySlotExpanded(AdsContent.MOBILE_BOTTOM_LB);
  }

  @Test
  public void adsAdTypeAsyncForcedSuccess() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261157332&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/ForcedSuccess");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(page.getUrl());
    ads.scrollToRecirculationPrefooter();
    ads.waitForSlot(AdsContent.MOBILE_BOTTOM_LB);
    ads.verifySlotExpanded(AdsContent.MOBILE_BOTTOM_LB);
  }
}
