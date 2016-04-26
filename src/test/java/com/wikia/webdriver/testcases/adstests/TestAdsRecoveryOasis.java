package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Map;

public class TestAdsRecoveryOasis extends TemplateNoFirstLoad {

  private static Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryOasis",
      groups = "AdsRecoveryOasis"
  )
  public void adsRecoveryOasis(Page page, Map<String, Object> slotInfo) {

    adsSlotSizes(page, slotInfo);
  }

  private void adsSlotSizes(Page page, Map<String, Object> slotInfo) {
    String slotName = slotInfo.get("slotName").toString();
    String url = urlBuilder.getUrlForPage(page);

    String slotSelector = AdsContent.getSlotSelector(slotName);
    WebElement slot = driver.findElement(By.cssSelector(slotSelector));

    new AdsBaseObject(driver, url, DESKTOP_SIZE)
        .triggerAdSlot(slotName)
        .verifyLineItemId(slotName, Integer.valueOf(slotInfo.get("lineItemId").toString()))
        .waitForSlotExpanded(slot);
  }
}
