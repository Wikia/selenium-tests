package com.wikia.webdriver.testcases.adstests.mobileadstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.Map;


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
                           Map<String, Object> slotInfo,
                           Dimension pageSize,
                           String imageUrl) {
    String slotName = (String) slotInfo.get("slotName");
    String src = (String) slotInfo.get("src");
    Integer lineItemId = (Integer) slotInfo.get("lineItemId");
    Dimension slotSize = (Dimension) slotInfo.get("slotSize");

    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, pageSize);
    adsBaseObject.getUrl(page);

    adsBaseObject
        .verifyLineItemId(slotName, lineItemId)
        .verifySize(slotName, src, slotSize.getWidth(), slotSize.getHeight())
        .verifyAdImage(slotName, src, imageUrl);
  }
}
