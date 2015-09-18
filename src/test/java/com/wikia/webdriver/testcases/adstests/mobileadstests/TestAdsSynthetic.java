package com.wikia.webdriver.testcases.adstests.mobileadstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering
 */
public class TestAdsSynthetic extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsSynthetic",
      groups = "AdsSynthetic"
  )
  public void adsSynthetic(Page page,
                           String slotName,
                           int slotWidth,
                           int slotHeight,
                           int lineItemId,
                           String src,
                           String imageUrl) {
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver);
    adsBaseObject.getUrl(page);
    adsBaseObject
        .verifyLineItemId(slotName, lineItemId)
        .verifySize(slotName, src, slotWidth, slotHeight)
        .verifyAdImage(slotName, src, imageUrl);
  }
}
