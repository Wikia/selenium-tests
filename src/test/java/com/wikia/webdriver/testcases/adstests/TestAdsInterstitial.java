package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsInterstitialObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsInterstitial extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestInterstitialOasis",
      dataProvider = "interstitialOasis"
  )
  public void adsInterstitialAdScaledOasis(Page page, String selector, Dimension adSize) throws InterruptedException {
    testInterstitial2(page, selector, adSize);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestInterstitialMercury",
      dataProvider = "interstitialMercury"
  )
  public void adsInterstitialAdScaledOnMobileInPortraitPosition(Page page, String selector, Dimension adSize) throws InterruptedException {
    testInterstitial2(page, selector, adSize);
  }

  private void testInterstitial2(Page page, String selector, Dimension adSize) throws InterruptedException {
    AdsInterstitialObject adsInterstitial = new AdsInterstitialObject(driver);
    adsInterstitial.getUrl(page);

    adsInterstitial.waitForPageLoadedWithGpt();
    Assertion.assertTrue(adsInterstitial.isInterstitialAdDisplayed(), "No Interstitial ad is not displayed");
    adsInterstitial.verifySize(selector, adSize);
    adsInterstitial.closeInterstitial();
    adsInterstitial.verifyInterstitialIsClosed();
  }


}
