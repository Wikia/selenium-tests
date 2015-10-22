package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPadObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering Wikia
 */
public class TestPad extends TemplateNoFirstLoad {

  private static final String DISASTER_RECOVERY_URL_PARAM_ON =
      "InstantGlobals.wgSitewideDisablePaidAssetDrop=1";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestPad",
      dataProvider = "testPad"
  )
  public void testPad(String wikiName, String article, int adHeight) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsPadObject adsPadObject = new AdsPadObject(driver, testedPage);
    adsPadObject.verifyPadHeight(adHeight);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestPad",
      dataProvider = "testPad"
  )
  public void testPadDisasterRecovery(String wikiName, String article, int adHeight) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, DISASTER_RECOVERY_URL_PARAM_ON);
    AdsPadObject adsPadObject = new AdsPadObject(driver, testedPage);
    adsPadObject.verifyNoPadOnPage();
  }
}
