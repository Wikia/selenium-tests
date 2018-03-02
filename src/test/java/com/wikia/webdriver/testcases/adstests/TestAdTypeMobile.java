package com.wikia.webdriver.testcases.adstests;

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
@InBrowser(
  browser = Browser.CHROME,
  emulator = Emulator.GOOGLE_NEXUS_5
)
@Test(groups = "AdTypeMercury")
public class TestAdTypeMobile extends MobileTestTemplate {

  @Test
  public void adsAdTypeAsyncSuccessWithAd() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260851332&orderId=245575332
    String imgUrl = "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg";
    Page page = new Page("project43", "SyntheticTests/AdType/Async/Success");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.waitForSlot(AdsContent.MOBILE_BOTTOM_LB);
    ads.verifyImgAdLoadedInSlot(AdsContent.MOBILE_BOTTOM_LB, imgUrl);
  }

  @Test
  public void adsAdTypeAsyncHopWithoutAd() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260955852&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/Async/Hop");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.wait.forElementPresent(By.id(AdsContent.MOBILE_AD_IN_CONTENT));
    ads.verifyNoAdInSlot(AdsContent.MOBILE_AD_IN_CONTENT);
  }

  @Test
  public void adsAdTypeAsyncSuccessNoAd() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261075132&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/Async/Success/NoAd");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.wait.forElementPresent(By.id(AdsContent.MOBILE_BOTTOM_LB));
    ads.verifySlotExpanded(AdsContent.MOBILE_BOTTOM_LB);
  }

  @Test
  public void adsAdTypeAsyncHopWithAd() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261089652&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/Async/Hop/WithAd");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.wait.forElementPresent(By.id(AdsContent.MOBILE_AD_IN_CONTENT));
    ads.verifyNoAdInSlot(AdsContent.MOBILE_AD_IN_CONTENT);
  }

  @Test
  public void adsAdTypeAsyncSuccessAndHop() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260851332&orderId=245575332
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=260955852&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/Async/Success,Hop");
    String imgUrl = "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg";
    String slotNameWithAd = AdsContent.MOBILE_BOTTOM_LB;
    String slotNameWithoutAd = AdsContent.MOBILE_AD_IN_CONTENT;
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.wait.forElementPresent(By.id(slotNameWithAd));
    ads.wait.forElementPresent(By.id(slotNameWithoutAd));
    ads.verifyImgAdLoadedInSlot(slotNameWithAd, imgUrl);
    ads.verifyNoAdInSlot(slotNameWithoutAd);
  }

  @Test
  public void adsAdTypeAsyncForcedSuccess() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261157332&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/ForcedSuccess");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.waitForSlot(AdsContent.MOBILE_BOTTOM_LB);
    ads.verifySlotExpanded(AdsContent.MOBILE_BOTTOM_LB);
  }

  @Test
  public void adsAdTypeInspectIframe() {
    // https://www.google.com/dfp/5441#delivery/LineItemDetail/lineItemId=261158532&orderId=245575332
    Page page = new Page("project43", "SyntheticTests/AdType/InspectIframe");
    String imgUrl = "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg";

    final By slotSelector = By.id(AdsContent.MOBILE_BOTTOM_LB);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.waitForSlotExpanded(slotSelector);
    ads.scrollToPosition(slotSelector);
    ads.verifyImgAdLoadedInSlot(AdsContent.MOBILE_BOTTOM_LB, imgUrl);
  }
}
