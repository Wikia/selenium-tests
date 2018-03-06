package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsSkinPresence extends NewTestTemplate {

//  @Test(
//      dataProviderClass = AdsDataProvider.class,
//      dataProvider = "skinWithoutTheme",
//      groups = "AdsSkinPresence"
//  )
//  public void adsSkinPresenceOnWikiWithoutTheme(String wikiName, String article,
//                                                Dimension resolution,
//                                                String expectedLeftSide,
//                                                String expectedRightSide,
//                                                String backgroundColor,
//                                                String middleColor) {
//    verifySkin(wikiName, article, resolution, expectedLeftSide, expectedRightSide, backgroundColor,
//               middleColor);
//  }

  @Execute(mockAds = "true")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "skinWithTheme",
      groups = "AdsSkinPresence"
  )
  public void adsSkinPresenceOnWikiWithTheme(String wikiName, String article, Dimension resolution,
                                             String expectedLeftSide,
                                             String expectedRightSide,
                                             String backgroundColor,
                                             String middleColor) {
    verifySkin(wikiName, article, resolution, expectedLeftSide, expectedRightSide, backgroundColor,
               middleColor);
  }

  private void verifySkin(String wikiName, String article,
                          Dimension resolution,
                          String expectedLeftSide,
                          String expectedRightSide,
                          String backgroundColor,
                          String middleColor) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    PageObjectLogging.log("Window resolution: ", String.valueOf(resolution.width), true);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, resolution);
    adsBaseObject.verifySkin(expectedLeftSide,
                             expectedRightSide,
                             backgroundColor,
                             middleColor);
  }

}
