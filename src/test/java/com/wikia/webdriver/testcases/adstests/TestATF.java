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
  private final static Dimension TABLET_PAGE_SIZE = new Dimension(850, 600);

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

    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));

    if (isWgVarOn) {
      Assertion.assertFalse(adsBaseObject.isSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
      Assertion.assertFalse(adsBaseObject.isSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
      Assertion.assertFalse(adsBaseObject.isSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2));
      Assertion.assertFalse(adsBaseObject.isSlotOnPageLoaded(AdsContent.FLOATING_MEDREC));
      Thread.sleep(1000 * delaySec);
    }

    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.FLOATING_MEDREC));
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

    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));

    Assertion.assertNotEquals(
        adsBaseObject.isSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.isSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.isSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.isSlotOnPageLoaded(AdsContent.FLOATING_MEDREC), isWgVarOn);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtf",
      groups = {"TestATF", "Ads"}
  )
  public void TestATFonTablet(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, TABLET_PAGE_SIZE);
    adsBaseObject.waitPageLoaded();

    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
    Assertion.assertTrue(adsBaseObject.isSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));

    Assertion.assertFalse(adsBaseObject.isSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertFalse(adsBaseObject.isSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));
    Assertion.assertFalse(adsBaseObject.isSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2));
  }

}
