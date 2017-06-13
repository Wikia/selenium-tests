package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsHopObject;

import org.testng.annotations.Test;

public class TestAdsHopMercury extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "testAdsHopPostMessage",
      groups = "AdsHopPostMessageMercury"
  )
  public void adsHopPostMessageMercury(String wikiName,
                                       String article,
                                       String providerName,
                                       String extraParam) {

    AdsHopObject adsHopObject = new AdsHopObject(driver, urlBuilder.getUrlForPath(wikiName, article));

    adsHopObject.verifyClassHidden(AdsContent.MOBILE_TOP_LB, providerName);
    adsHopObject.verifyPostMessage(AdsContent.MOBILE_TOP_LB, providerName, extraParam);
    adsHopObject.verifyLineItemIdsDiffer(AdsContent.MOBILE_TOP_LB);
  }
}
