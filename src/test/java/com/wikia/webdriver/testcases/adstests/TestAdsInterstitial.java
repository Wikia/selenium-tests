package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
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
  public void adsInterstitialAdScaledOasis(
      String wikiName,
      String article,
      Dimension pageSize,
      Dimension adSize,
      boolean shouldAdBeScaled
  ) {
    testInterstitial(wikiName, article, pageSize, adSize, shouldAdBeScaled);
  }

  @RelatedIssue(issueID = "ADEN-3531")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestInterstitialMercury",
      dataProvider = "interstitialMercury"
  )
  public void adsInterstitialAdScaledMercury(
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
    adsInterstitial.closeInterstitial();
    adsInterstitial.verifyInterstitialIsClosed();
  }
}
