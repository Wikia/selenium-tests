package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsInterstitialObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * @author drets
 * @ownership AdEng
 */
public class TestInterstitial extends TemplateDontLogout {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "Interstitial",
      dataProvider = "interstitial"
  )
  public void interstitialAdScaled(
      String wikiName,
      String article,
      Dimension pageSize,
      double scaledAdRatio
  ) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsInterstitialObject adsInterstitial = new AdsInterstitialObject(driver, testedPage, pageSize);
    Assertion.assertEquals(adsInterstitial.getScaledAdRatio(), scaledAdRatio);
  }
}
