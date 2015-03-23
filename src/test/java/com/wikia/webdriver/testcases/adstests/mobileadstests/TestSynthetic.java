package com.wikia.webdriver.testcases.adstests.mobileadstests;

import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestSynthetic extends NewTestTemplate {

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "testSynthetic",
      groups = {"TestSynthetic"}
  )
  public void testSynthetic(String wikiPage, String article,
                            String slotName, int slotWidth, int slotHeight,
                            int lineItemId, String src,
                            String imageUrl) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiPage, article))
        .verifyLineItemId(slotName, src, lineItemId)
        .verifySize(slotName, src, slotWidth, slotHeight)
        .verifyAdImage(slotName, src, imageUrl);
  }
}
