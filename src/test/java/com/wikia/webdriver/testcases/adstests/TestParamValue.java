package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * Created by PMG on 2015-02-09.
 */
public class TestParamValue extends NewTestTemplate {

  public TestParamValue() {
    super();
    urlBuilder = new UrlBuilder(config.getEnv());
  }

  @Test(
      dataProvider = "parameterValueProvider", dataProviderClass = AdsDataProvider.class,
      groups = {"paramValue_GEF"}
  )
  public void TestParamValue_GeoEdgeFree(String wikiName, String article, Boolean paramPresent,
                                         String paramName, String paramValue) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);

    wikiPage.verifyParamValue(paramName, paramValue, paramPresent);
  }
}
