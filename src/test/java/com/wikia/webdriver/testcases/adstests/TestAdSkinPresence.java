package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @ownership AdEngineering
 */
public class TestAdSkinPresence extends TemplateDontLogout {

  public TestAdSkinPresence() {
    super();
    urlBuilder = new UrlBuilder(config.getEnv());
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "skin",
      groups = {"TestSkinPresence_GeoEdgeFree"}
  )
  public void TestSkinPresence_GeoEdgeFree(String wikiName, String article,
                                           Dimension windowResolution,
                                           String expectedAdSkinLeftPath,
                                           String expectedAdSkinRightPath) throws IOException {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    PageObjectLogging.log("Window resolution: ", String.valueOf(windowResolution.width), true);
    new AdsBaseObject(driver, testedPage, windowResolution).checkSkin(expectedAdSkinLeftPath,
                                                                      expectedAdSkinRightPath);
  }

}
