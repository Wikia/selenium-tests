package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;


/**
 * @author drets
 * @ownership AdEng
 */
public class TestATF extends TemplateDontLogout {

  private final static Dimension PAGE_SIZE = new Dimension(1366, 768);

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtf",
      groups = {"TestATF", "Ads"}
  )
  public void TestATFDelayBTF(String wikiName, String article, int delaySec, boolean isWgVarOn)
      throws InterruptedException {
    PageObjectLogging.log("wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, PAGE_SIZE);
    adsBaseObject.waitPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));

    if (isWgVarOn) {
      Assertion.assertFalse(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
      Assertion.assertFalse(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
      Assertion.assertFalse(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_3));
      Assertion.assertFalse(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.FLOATING_MEDREC));
      Thread.sleep(1000 * delaySec);
    }

    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_3));
    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.FLOATING_MEDREC));
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtf",
      groups = {"TestATF", "Ads"}
  )
  public void TestATFDisableBTF(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, PAGE_SIZE);
    adsBaseObject.waitPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));

    Assertion.assertNotEquals(
        adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_3), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.checkIfSlotOnPageLoaded(AdsContent.FLOATING_MEDREC), isWgVarOn);
  }

}
