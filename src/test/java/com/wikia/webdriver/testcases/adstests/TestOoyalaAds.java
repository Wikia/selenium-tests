package com.wikia.webdriver.testcases.adstests;

import java.awt.*;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestOoyalaAds extends TemplateNoFirstLoad {

  private static final Color GREEN = new Color(0, 214, 0);
  private static final Color BLUE = new Color(0, 13, 255);
  private static final int AD_DURATION_SEC = 30;
  private static final int VIDEO_DURATION_SEC = 30;

  @Test(dataProviderClass = AdsDataProvider.class, groups = {"TestOoyalaAds_GeoEdgeFree"},
      dataProvider = "ooyalaAds")
  public void TestOoyalaAds_GeoEdgeFree(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyFlash();
    wikiPage.verifyLightboxAd(BLUE, AD_DURATION_SEC);
    wikiPage.verifyLightboxVideo(GREEN, VIDEO_DURATION_SEC);
  }
}
