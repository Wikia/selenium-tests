package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsHopObject;

import org.testng.annotations.Test;

/**
 * @author drets
 * @ownership AdEng
 */
public class TestAdsHop extends TemplateDontLogout {

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "testAdsHopPostMessage",
      groups = "TestAdsHop"
  )
  public void testAdsHopPostMessage(String wikiName, String article, int testedDivNumber,
                                    String src) {
    new AdsHopObject(driver, urlBuilder.getUrlForPath(wikiName, article))
        .verifyClassHidden(AdsContent.MOBILETOP_LB, testedDivNumber)
        .verifyPostMessage(AdsContent.MOBILETOP_LB, testedDivNumber, src)
        .verifyLineItemIdsDiffer(AdsContent.MOBILETOP_LB);
  }

}
