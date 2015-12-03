package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

import java.awt.*;

/**
 * @ownership AdEngineering Wikia
 */
public class TestOoyalaAds extends TemplateNoFirstLoad {

  private static final Color GREEN = new Color(4, 253, 6);
  private static final Color BLUE = new Color(4, 0, 254);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 30;

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"TestOoyalaAds_GeoEdgeFree"},
      dataProvider = "ooyalaAds"
  )
  public void TestOoyalaAds_GeoEdgeFree(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyLightboxAd(BLUE, AD_DURATION_SEC);
    wikiPage.verifyLightboxVideo(GREEN, VIDEO_DURATION_SEC);
  }
}
