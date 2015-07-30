package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @ownership AdEngineering
 */
public class TestAdSkinPresence extends TemplateNoFirstLoad {

  public TestAdSkinPresence() {
    super();
    urlBuilder = new UrlBuilder(Configuration.getEnv());
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "skin",
      groups = "TestSkinPresence_GeoEdgeFree"
  )
  public void TestSkinPresence(String wikiName, String article,
                               Dimension windowResolution,
                               String expectedAdSkinLeftPath,
                               String expectedAdSkinRightPath,
                               String backgroundColor,
                               String middleColor) throws IOException {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    PageObjectLogging.log("Window resolution: ", String.valueOf(windowResolution.width), true);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, windowResolution);
    adsBaseObject.checkSkin(expectedAdSkinLeftPath,
                            expectedAdSkinRightPath,
                            backgroundColor,
                            middleColor);
  }

}
