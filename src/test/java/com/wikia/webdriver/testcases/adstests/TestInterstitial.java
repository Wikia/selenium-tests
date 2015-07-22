package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsInterstitialObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * @author drets
 * @ownership AdEng
 */
public class TestInterstitial extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "Interstitial",
      dataProvider = "interstitial"
  )
  public void interstitialAdScaled(
      String wikiName,
      String article,
      Dimension pageSize,
      Dimension adSize,
      boolean isAdShouldBeScaled
  ) {
    testInterstitial(wikiName, article, pageSize, adSize, isAdShouldBeScaled);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "InterstitialMobile",
      dataProvider = "interstitialMobile"
  )
  public void interstitialAdScaledMobile(
      String wikiName,
      String article,
      Dimension pageSize,
      Dimension adSize,
      boolean isAdShouldBeScaled
  ) {
    testInterstitial(wikiName, article, pageSize, adSize, isAdShouldBeScaled);
  }

  private void testInterstitial(
      String wikiName,
      String article,
      Dimension pageSize,
      Dimension adSize,
      boolean isAdShouldBeScaled
  ) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsInterstitialObject adsInterstitial = new AdsInterstitialObject(driver, testedPage, pageSize);
    adsInterstitial.verifySize(adSize);
    if (isAdShouldBeScaled) {
      adsInterstitial.verifyAdRatio();
    }
  }
}
