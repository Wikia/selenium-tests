package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.util.List;

public class TestAdsAdDriverForcedStatusOasis extends TemplateNoFirstLoad {

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "adDriverForcedStatusSuccess", groups = "AdsAdDriverForcedStatusOasis")
  public void adsAdDriverForcedStatusSuccessOasis(
      String wikiName, String article, List<String> slots
  ) {
    String testedPage = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(article);
    AdsBaseObject ads = new AdsBaseObject(testedPage);
    ads.verifyForcedSuccessScriptInSlots(slots);
  }
}
