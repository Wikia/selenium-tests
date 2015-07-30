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
      boolean shouldAdBeScaled
  ) {
    testInterstitial(wikiName, article, pageSize, adSize, shouldAdBeScaled);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "InterstitialMobile",
      dataProvider = "interstitialMobile",
      enabled = false // wf ADEN-2255
  )
  public void interstitialAdScaledMobile(
      String wikiName,
      String article,
      Dimension pageSize,
      Dimension adSize,
      boolean shouldAdBeScaled
  ) {
    testInterstitial(wikiName, article, pageSize, adSize, shouldAdBeScaled);
  }

  private void testInterstitial(
      String wikiName,
      String article,
      Dimension pageSize,
      Dimension adSize,
      boolean shouldAdBeScaled
  ) {
    String url = urlBuilder.getUrlForPath(wikiName, article);
    String testedPage = urlBuilder.appendQueryStringToURL(url, "highimpactslot=1");
    AdsInterstitialObject adsInterstitial = new AdsInterstitialObject(driver, testedPage, pageSize);
    adsInterstitial.waitForPageLoaded();
    adsInterstitial.verifySize(adSize);
    if (shouldAdBeScaled) {
      adsInterstitial.verifyAdRatio();
    }
  }
}
