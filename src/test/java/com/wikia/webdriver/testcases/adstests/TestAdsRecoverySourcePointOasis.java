package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Map;

public class TestAdsRecoverySourcePointOasis extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoverySourcePointOasis",
      groups = "AdsRecoverySourcePointOasis"
  )
  public void adsRecoverySourcePointOasis(Page page, Map<String, Object> slotInfo) {
    String adUnitId = slotInfo.get("adUnitId").toString();
    String slotName = slotInfo.get("slotName").toString();

    String url = urlBuilder.getUrlForPage(page);
    AdsRecoveryObject adsBaseObject = new AdsRecoveryObject(driver, url, WindowSize.DESKTOP);
    adsBaseObject.refreshPageAddingCacheBuster();

    adsBaseObject.waitForRecoveredSlot(slotName);

    String recoveredAdUnitIdSelector = "#" + adsBaseObject.getRecoveredAdUnitId(adUnitId);
    WebElement recoveredSlot = driver.findElement(By.cssSelector(recoveredAdUnitIdSelector));

    adsBaseObject.triggerAdSlot(slotName)
        .verifyLineItemId(slotName, Integer.valueOf(slotInfo.get("lineItemId").toString()))
        .verifyExpandedAdVisibleInSlot(recoveredAdUnitIdSelector, recoveredSlot);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoverySourcePointOasisProject43",
      groups = "AdsRecoverySourcePointOasis"
  )
  public void adsRecoverySourcePointOasisByCountry(Page page, Map<String, Object> slotInfo) {
    adsRecoverySourcePointOasis(page, slotInfo);
  }
}
