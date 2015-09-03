package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEng
 */
public class TestAdsGptPageParamOasis extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsGptPageParam",
      groups = "AdsGptPageParamOasis"
  )
  public void adsGptPageParamOasis(String wikiName,
                                   String article,
                                   String paramName,
                                   String paramValue,
                                   Boolean paramShouldPresent) {
    AdsBaseObject wikiPage = new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article));
    String gptPageParams = wikiPage.getGptPageParams(AdsContent.TOP_LB);
    String gptPattern = String.format("\"%s\":\"%s\"", paramName, paramValue);
    if (paramShouldPresent) {
      Assertion.assertStringContains(gptPageParams, gptPattern);
    } else {
      Assertion.assertStringNotContains(gptPageParams, gptPattern);
    }
  }
}
