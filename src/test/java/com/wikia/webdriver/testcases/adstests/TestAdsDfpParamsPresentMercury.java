package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.List;

public class TestAdsDfpParamsPresentMercury extends MobileTestTemplate {

  private static final String LINE_ITEM_ID = "282067812";
  private static final String CREATIVE_ID = "50006703732";
  private static final String SRC_MOBILE = "mobile";

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpParamsSynthetic",
      groups = {"MobileAds", "AdsDfpParamsPresentSyntheticMercury"}
  )
  public void dfpParamsPresentSyntheticMercury(String wikiName,
                                               String article,
                                               String queryString,
                                               String adUnit,
                                               String slot,
                                               List<String> pageParams,
                                               List<String> slotParams) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    if (StringUtils.isNotEmpty(queryString)) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, queryString);
    }

    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, slot, SRC_MOBILE);
    ads.verifyGptParams(slot, pageParams, slotParams);
    ads.verifyGptAdInSlot(slot, LINE_ITEM_ID, CREATIVE_ID);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpRubiconParamsSynthetic",
      groups = {"MobileAds", "AdsDfpParamsPresentSyntheticMercury"}
  )
  public void dfpRubiconParamsPresentSyntheticMercury(String wikiName,
                                                      String article,
                                                      String queryString,
                                                      String adUnit,
                                                      String slot,
                                                      String patternParamTier) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    if (StringUtils.isNotEmpty(queryString)) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, queryString);
    }

    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    String currentGptSlotParams = ads.getGptParams(slot, "data-gpt-slot-params");
    ads.verifyGptIframe(adUnit, slot, SRC_MOBILE);
    Assertion.assertTrue(ads.areRubiconDfpParamsPresent(currentGptSlotParams, patternParamTier),
        currentGptSlotParams + " does not contains " + patternParamTier);
    ads.verifyGptAdInSlot(slot, LINE_ITEM_ID, CREATIVE_ID);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpParams",
      groups = {"MobileAds", "AdsDfpParamsPresentMercury"}
  )
  public void dfpParamsPresentMercury(String wikiName,
                                      String article,
                                      String adUnit,
                                      String slot,
                                      List<String> pageParams,
                                      List<String> slotParams) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);

    ads.verifyGptIframe(adUnit, slot, SRC_MOBILE);
    ads.verifyGptParams(slot, pageParams, slotParams);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpEvolveParamsMercury",
      groups = {"MobileAds", "AdsEvolveMercury"}
  )
  public void dfpEvolveParamsPresentMercury(String wikiName,
                                          String article,
                                          Integer dfpClientId,
                                          String adUnit,
                                          String slot,
                                          List<String> pageParams,
                                          List<String> slotParams) {
    AdsEvolveObject ads = new AdsEvolveObject(driver);
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    ads.enableEvolve(testedPage);
    ads.verifyGptIframe(dfpClientId, adUnit, slot);
    ads.verifyGptParams(slot, pageParams, slotParams);
  }
}
