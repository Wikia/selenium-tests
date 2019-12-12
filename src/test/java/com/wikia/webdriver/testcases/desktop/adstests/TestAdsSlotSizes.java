package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.Map;

public class TestAdsSlotSizes extends TemplateNoFirstLoad {

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "adsSlotSizeOasis", groups = "AdsSlotSizesOasis")
  public void adsSlotSizesOasis(Page page, String urlParamToEnable, Map<String, Object> slotInfo) {
    adsSlotSizes(page, urlParamToEnable, WindowSize.DESKTOP, slotInfo, false);
  }

  private void adsSlotSizes(
      Page page, String urlParamToEnable, Dimension pageSize, Map<String, Object> slotInfo, Boolean isMobile
  ) {
    String slotName = slotInfo.get("slotName").toString();
    Dimension slotSize = (Dimension) slotInfo.get("slotSize");

    String url = page.getUrl();
    if (StringUtils.isNotEmpty(urlParamToEnable)) {
      url = urlBuilder.appendQueryStringToURL(url, urlParamToEnable);
    }

    log(slotName, slotSize);

    AdsBaseObject ads = new AdsBaseObject(driver, url, pageSize);

    ads.triggerAdSlotWithMobileState(slotName, isMobile).wait.forElementPresent(By.cssSelector(AdsContent.getSlotSelector(
        slotName)));

    ads.verifyLineItemId(slotName, Integer.valueOf(slotInfo.get("lineItemId").toString()));
    ads.verifyIframeSize(slotName, slotSize.getWidth(), slotSize.getHeight());
  }

  private void log(String slotName, Dimension slotSize) {
    Log.log("adsSlotSizes", "Slot name: " + slotName, true);
    Log.log("adsSlotSizes", "Width: " + slotSize.getWidth(), true);
    Log.log("adsSlotSizes", "Height: " + slotSize.getHeight(), true);
  }
}
