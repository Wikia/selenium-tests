package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.apache.commons.collections.ListUtils;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestUap extends TemplateNoFirstLoad {

  private static final String RESOLVED_STATE = "resolved_state=0";

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "adsUapOasis", groups = "AdsUapOasis")
  public void adsUapOasis(
      Page page, List<Map<String, Object>> atfSlots, List<Map<String, Object>> btfSlots
  ) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl(RESOLVED_STATE), WindowSize.DESKTOP);
    verifySlotsUnblocked(ads, atfSlots);
    for (Map<String, Object> slotData : btfSlots) {
      verifySlotsBlocked(ads, slotData.get("slotName").toString());
    }
    verifySlotsUnblocked(ads, ListUtils.union(atfSlots, btfSlots));
  }

  private void verifySlotsBlocked(AdsBaseObject ads, String slotName) {
    ads.verifyNoAdWithoutTrigger(AdsContent.getSlotSelector(slotName));
  }

  private void verifySlotsUnblocked(AdsBaseObject ads, List<Map<String, Object>> slotsData) {
    for (Map<String, Object> slotData : slotsData) {
      String slotName = slotData.get("slotName").toString();
      Dimension slotSize = (Dimension) slotData.get("slotSize");

      ads.checkSlotOnPageLoaded(slotName, false);
      ads.verifyLineItemId(slotName, slotData.get("lineItemId").toString());
      ads.verifyIframeSize(slotName, slotSize.getWidth(), slotSize.getHeight());
    }
  }
}
