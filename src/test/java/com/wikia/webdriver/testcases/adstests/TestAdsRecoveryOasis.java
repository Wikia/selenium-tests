package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsTaboolaObject;

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
    String adUnitId = slotInfo.get("adUnitId").toString();
    String slotName = slotInfo.get("slotName").toString();
    String url = urlBuilder.getUrlForPage(page);

    AdsRecoveryObject adsBaseObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);

    String recoveredAdUnitIdSelector = "#" + adsBaseObject.getRecoveredAdUnitId(adUnitId);
    WebElement recoveredSlot = driver.findElement(By.cssSelector(recoveredAdUnitIdSelector));

    adsBaseObject.triggerAdSlot(slotName)
        .verifyLineItemId(slotName, Integer.valueOf(slotInfo.get("lineItemId").toString()))
        .verifyExpandedAdVisibleInSlot(recoveredAdUnitIdSelector, recoveredSlot);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryOasisProject43",
      groups = "AdsRecoveryOasis"
  )
  public void adsRecoveryOasisByCountry(Page page, Map<String, Object> slotInfo) {
    adsRecoveryOasis(page, slotInfo);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryOasisHopToTaboola",
      groups = "AdsRecoveryOasis"
  )
  public void adsRecoveryOasisHopToTaboola(Page page, Map<String, Object> slotInfo) {
    String slotName = slotInfo.get("slotName").toString();
    String adUnitId = slotInfo.get("adUnitId").toString();
    String url = urlBuilder.getUrlForPage(page);

    AdsRecoveryObject adsBaseObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);

    String recoveredAdUnitIdSelector = "#" + adsBaseObject.getRecoveredAdUnitId(adUnitId);
    adsBaseObject.triggerAdSlot(slotName).verifyNoAd(recoveredAdUnitIdSelector);

    AdsTaboolaObject adsTaboolaObject = new AdsTaboolaObject(driver);
    adsTaboolaObject.verifyTaboolaContainer(AdsTaboolaObject.ABOVE_ARTICLE_CSS_SELECTOR);
  }
}
