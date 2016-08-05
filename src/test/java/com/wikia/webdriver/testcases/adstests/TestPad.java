package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPadObject;

import org.testng.annotations.Test;

public class TestPad extends NewTestTemplate {

  private static final String DISASTER_RECOVERY_URL_PARAM_ON =
      "InstantGlobals.wgSitewideDisablePaidAssetDrop=1";

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestPadMercury",
      dataProvider = "testPad"
  )
  public void testPadMercury(String wikiName, String article, int adHeight) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsPadObject adsPadObject = new AdsPadObject(driver, testedPage);
    adsPadObject.verifyPadHeight(adHeight);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestPadMercury",
      dataProvider = "testPad"
  )
  public void testPadDisasterRecoveryMercury(String wikiName, String article, int adHeight) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, DISASTER_RECOVERY_URL_PARAM_ON);
    AdsPadObject adsPadObject = new AdsPadObject(driver, testedPage);
    adsPadObject.verifyNoPadOnPage();
  }

  @Execute(mockAds = "true")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestPadOasis",
      dataProvider = "testPad"
  )
  public void testPadOasis(String wikiName, String article, int adHeight) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsPadObject adsPadObject = new AdsPadObject(driver, testedPage);
    adsPadObject.verifyPadHeight(adHeight);
  }

  @Execute(mockAds = "true")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "TestPadOasis",
      dataProvider = "testPad"
  )
  public void testPadDisasterRecoveryOasis(String wikiName, String article, int adHeight) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, DISASTER_RECOVERY_URL_PARAM_ON);
    AdsPadObject adsPadObject = new AdsPadObject(driver, testedPage);
    adsPadObject.verifyNoPadOnPage();
  }
}
