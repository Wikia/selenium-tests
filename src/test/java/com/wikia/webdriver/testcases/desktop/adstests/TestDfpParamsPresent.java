package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.util.List;

public class TestDfpParamsPresent extends TemplateNoFirstLoad {

  private static final String LINE_ITEM_ID = "282067812";
  private static final String CREATIVE_ID = "37674198492";

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "dfpMEGAParamsTLB", groups = {
      "DfpParamsPresentSyntheticOasis", "Ads"})
  public void dfpMEGAParamsPresentOasisTLB(
      String wikiName,
      String article,
      String adUnit,
      String slot,
      List<String> pageParams,
      List<String> slotParams
  ) {
    String testedPage = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(article);
    AdsBaseObject ads = new AdsBaseObject(testedPage);

    ads.verifyGptMEGAIframe(adUnit, slot);
    ads.verifyGptParams(slot, pageParams, slotParams);
    ads.verifyGptAdInSlot(slot, LINE_ITEM_ID, CREATIVE_ID);
  }

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "dfpParams", groups = {
      "DfpParamsPresentOasis", "Ads"})
  public void dfpParamsPresentOasis(
      String wikiName,
      String article,
      String adUnit,
      String slot,
      List<String> pageParams,
      List<String> slotParams
  ) {
    String testedPage = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(article);
    AdsBaseObject ads = new AdsBaseObject(testedPage);

    ads.verifyGptMEGAIframe(adUnit, slot);
    ads.verifyGptParams(slot, pageParams, slotParams);
  }

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "dfpMEGAParams", groups = {
      "DfpParamsPresentOasis", "Ads"})
  public void dfpMEGAParamsPresentOasis(
      String wikiName,
      String article,
      String adUnit,
      String slot,
      List<String> pageParams,
      List<String> slotParams
  ) {
    String testedPage = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(article);
    AdsBaseObject ads = new AdsBaseObject(testedPage);

    ads.verifyGptMEGAIframe(adUnit, slot);
    ads.verifyGptParams(slot, pageParams, slotParams);
  }
}
