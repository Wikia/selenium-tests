package com.wikia.webdriver.testcases.adstests;

import java.io.IOException;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

/**
 * @ownership AdEngineering
 */
public class TestAdSkinPresence extends TemplateNoFirstLoad {

  public TestAdSkinPresence() {
    super();
    urlBuilder = new UrlBuilder(Configuration.getEnv());
  }

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "skin",
      groups = "TestSkinPresence_GeoEdgeFree")
  public void TestSkinPresence(String wikiName, String article, Dimension windowResolution,
      String expectedAdSkinLeftPath, String expectedAdSkinRightPath, String backgroundColor,
      String middleColor) throws IOException {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    LOG.success("Window resolution: ", String.valueOf(windowResolution.width));
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, windowResolution);
    adsBaseObject.verifySkin(expectedAdSkinLeftPath, expectedAdSkinRightPath, backgroundColor,
        middleColor);
  }
}
