package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @ownership AdEngineering Wikia
 */
public class TestIVW2AnalyticsProvider extends TemplateNoFirstLoad {

  @Test(groups = "TestIVW2AnalyticsProvider")
  public void testIVW2AnalyticsProvider() throws IOException {
    for (Object[] data : GermanAdsDataProvider.IVW2_TEST_DATA) {
      String wikiName = (String) data[0];
      String article = (String) data[1];
      String ivw2Param = (String) data[2];

      String testedPage = urlBuilder.getUrlForPath(wikiName, article);
      driver.get(testedPage);
      String htmlSource = driver.getPageSource();

      Boolean isParamOnPage = htmlSource.contains(ivw2Param);
      PageObjectLogging.log(
          "IVW2",
          ivw2Param + " param on the page: " + String.valueOf(isParamOnPage),
          isParamOnPage
      );
    }
  }
}
