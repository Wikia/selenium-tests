package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.List;

public class TestDfpParamsPresent extends TemplateNoFirstLoad {

  private static final String LINE_ITEM_ID = "282067812";
  private static final String CREATIVE_ID = "37674198492";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "dfpParamsSynthetic",
      groups = {"DfpParamsPresentSyntheticOasis", "Ads"}
  )
  public void dfpParamsPresentSyntheticOasis(String wikiName,
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
    ads.verifyGptIframe(adUnit, slot, "gpt");
    ads.verifyGptParams(slot, pageParams, slotParams);
    ads.verifyGptAdInSlot(slot, LINE_ITEM_ID, CREATIVE_ID);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "dfpParams",
      groups = {"DfpParamsPresentOasis", "Ads"}
  )
  public void dfpParamsPresentOasis(String wikiName,
                                    String article,
                                    String adUnit,
                                    String slot,
                                    List<String> pageParams,
                                    List<String> slotParams) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);

    ads.verifyGptIframe(adUnit, slot, "gpt");
    ads.verifyGptParams(slot, pageParams, slotParams);
  }
}
