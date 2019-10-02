package com.wikia.webdriver.testcases.desktop.adstests;

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

  @Test(dataProviderClass = AdsDataProvider.class, groups = "TestInterstitialOasis", dataProvider = "interstitialOasis")
  public void adsInterstitialAdOnOasis(Page page, Dimension adSize) {
    testInterstitial(page, adSize);
  }

  private void testInterstitial(Page page, Dimension adSize) {
    AdsInterstitialObject adsInterstitial = new AdsInterstitialObject(driver);
    adsInterstitial.getUrl(page);

    adsInterstitial.waitForPageLoadedWithGpt();
    Assertion.assertTrue(adsInterstitial.isInterstitialAdDisplayed(),
                         "No Interstitial ad is not displayed"
    );
    adsInterstitial.verifySize(adSize);
    adsInterstitial.closeInterstitial();
    adsInterstitial.verifyInterstitialIsClosed();
  }
}
