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
                                       String containerId,
                                       String src,
                                       String extraParam) {
    String testPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsHopObject adsHopObject = new AdsHopObject(driver, testPage);
    adsHopObject.verifyClassHidden(AdsContent.MOBILE_TOP_LB, containerId);
    adsHopObject.verifyPostMessage(AdsContent.MOBILE_TOP_LB, src, extraParam);
    adsHopObject.verifyLineItemIdsDiffer(AdsContent.MOBILE_TOP_LB);
  }
}
