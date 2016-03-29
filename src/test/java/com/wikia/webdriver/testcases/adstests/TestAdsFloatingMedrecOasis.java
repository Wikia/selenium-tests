package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsFloatingMedrecOasis extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsFloatingMedrec",
      groups = "AdsFloatingMedrec"
  )
  public void adsFloatingMedrec(String wikiName,
                                String article,
                                int lineItemId,
                                int slotWidth,
                                int slotHeight) {

    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article))
        .triggerFloatingMedrec()
        .verifyLineItemId(AdsContent.FLOATING_MEDREC, lineItemId)
        .verifyIframeSize(AdsContent.FLOATING_MEDREC, "gpt", slotWidth, slotHeight);
  }
}
