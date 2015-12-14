package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestATF extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_PAGE_SIZE = new Dimension(1366, 768);
  private static final Dimension TABLET_PAGE_SIZE = new Dimension(850, 600);

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtf",
      groups = {"TestATF", "Ads"}
  )
  public void TestATFDelayBTF(String wikiName, String article, int delaySec, boolean isWgVarOn)
      throws InterruptedException {
    PageObjectLogging.log("wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));

    if (isWgVarOn) {
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2));
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC));
      Thread.sleep(1000 * delaySec);
    }

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC));
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtf",
      groups = {"TestATF", "Ads"}
  )
  public void TestATFDisableBTF(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));

    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2), isWgVarOn);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC), isWgVarOn);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/ADEN-2156 Test whether ads on small screens are
   * displayed when wgAdDriverDelayBelowTheFold is enabled
   */
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtf",
      groups = {"TestATF", "Ads"}
  )
  public void TestATFonTablet(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, TABLET_PAGE_SIZE);
    adsBaseObject.waitForPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
  }

}
