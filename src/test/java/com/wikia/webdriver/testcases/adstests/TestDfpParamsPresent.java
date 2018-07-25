package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.List;

public class TestDfpParamsPresent extends TemplateNoFirstLoad {

  private static final String LINE_ITEM_ID = "282067812";
  private static final String CREATIVE_ID = "37674198492";

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "dfpParamsSynthetic", groups = {
      "DfpParamsPresentSyntheticOasis", "Ads"})
  public void dfpParamsPresentSyntheticOasis(
      String wikiName,
      String article,
      String queryString,
      String adUnit,
      String slot,
      List<String> pageParams,
      List<String> slotParams
  ) {
    UrlBuilder urlBuilder = UrlBuilder.createUrlBuilderForWiki(wikiName);
    String testedPage = urlBuilder.getUrlForPath(article);
    if (StringUtils.isNotEmpty(queryString)) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, queryString);
    }
    AdsBaseObject ads = new AdsBaseObject(testedPage);
    ads.verifyGptIframe(adUnit, slot, "gpt");
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

    ads.verifyGptIframe(adUnit, slot, "gpt");
    ads.verifyGptParams(slot, pageParams, slotParams);
  }
}
