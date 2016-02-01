package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.CountryCode;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.util.List;

/**
 * https://www.google.com/dfp/5441#delivery/LineItemDetail/LINE_ITEM_ID=115974612
 */
public class TestDfpParamsPresentMobile extends MobileTestTemplate {

  private static final String LINE_ITEM_ID = "115974612";
  private static final String CREATIVE_ID = "50006703732";

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpParamsSynthetic",
      groups = {"MobileAds", "DfpParamsPresentSyntheticMercury"}
  )
  public void dfpParamsPresentSyntheticMercury(String wikiName,
                                               String article,
                                               String adUnit,
                                               String slot,
                                               List<String> pageParams,
                                               List<String> slotParams) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, slot, "mobile");
    ads.verifyGptParams(slot, pageParams, slotParams);
    ads.verifyGptAdInSlot(slot, LINE_ITEM_ID, CREATIVE_ID);
  }

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpParams",
      groups = {"MobileAds", "DfpParamsPresentMercury"}
  )
  public void dfpParamsPresentMercury(String wikiName,
                                      String article,
                                      String adUnit,
                                      String slot,
                                      List<String> pageParams,
                                      List<String> slotParams) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, slot, "mobile");
    ads.verifyGptParams(slot, pageParams, slotParams);
  }

  @GeoEdgeBrowserMobProxy(country = CountryCode.NEW_ZEALAND)
  @UseUnstablePageLoadStrategy
  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "dfpEvolveParamsMercury",
      groups = {"Ads", "AdsEvolveMercury"}
  )
  public void dfpEvolveParamsPresentOasis(String wikiName,
                                          String article,
                                          Integer dfpClientId,
                                          String adUnit,
                                          String slot,
                                          List<String> pageParams,
                                          List<String> slotParams) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(dfpClientId, adUnit, slot, "");
    ads.verifyGptParams(slot, pageParams, slotParams);
  }
}
