package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * Created by PMG on 2015-02-09.
 */
public class TestParamValue extends TemplateNoFirstLoad {

  public TestParamValue() {
    super();
    urlBuilder = new UrlBuilder(Configuration.getEnv());
  }

  @Test(
      dataProvider = "parameterValueProvider",
      dataProviderClass = AdsDataProvider.class,
      groups = {"paramValue_GEF"}
  )
  public void TestParamValue_GeoEdgeFree(String wikiName, String article, String paramName,
                                         String paramValue, Boolean paramPresent) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);

    wikiPage.verifyParamValue(paramName, paramValue, paramPresent);
  }
}
