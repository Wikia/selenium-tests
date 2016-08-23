package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsBtfBlocking extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_PAGE_SIZE = new Dimension(1366, 768);
  private static final Dimension TABLET_PAGE_SIZE = new Dimension(850, 600);
  private static final Dimension MOBILE_SIZE = new Dimension(414, 736);

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtf",
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDelayBtfOasis(String wikiName, String article, int delaySec, boolean isWgVarOn)
      throws InterruptedException {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC), AdsContent.MEDREC);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN),
                         AdsContent.INVISIBLE_SKIN);

    if (isWgVarOn) {
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT),
                            AdsContent.PREFOOTER_LEFT);
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT),
                            AdsContent.PREFOOTER_RIGHT);
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2),
                            AdsContent.LEFT_SKYSCRAPPER_2);
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC),
                            AdsContent.FLOATING_MEDREC);
      Thread.sleep(1000 * delaySec);
    }

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT),
                         AdsContent.PREFOOTER_LEFT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT),
                         AdsContent.PREFOOTER_RIGHT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2),
                         AdsContent.LEFT_SKYSCRAPPER_2);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC),
                         AdsContent.FLOATING_MEDREC);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtf",
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDisableBtfOasis(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));

    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT), isWgVarOn,
        AdsContent.PREFOOTER_LEFT);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT), isWgVarOn,
        AdsContent.PREFOOTER_RIGHT);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2), isWgVarOn,
        AdsContent.LEFT_SKYSCRAPPER_2);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC), isWgVarOn,
        AdsContent.FLOATING_MEDREC);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/ADEN-2156 Test whether ads on small screens are
   * displayed when wgAdDriverDelayBelowTheFold is enabled
   */
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtf",
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfOnTabletOasis(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, TABLET_PAGE_SIZE);
    adsBaseObject.waitForPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT),
                         AdsContent.PREFOOTER_LEFT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT),
                         AdsContent.PREFOOTER_RIGHT);
  }

  @RelatedIssue(issueID = "ADEN-3761")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtf",
      groups = "AdsBtfBlockingMercury"
  )
  public void adsAtfDelayBtfMercury(String wikiName, String article, int delaySec, boolean isWgVarOn)
      throws InterruptedException {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, MOBILE_SIZE);
    adsBaseObject.waitForPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_TOP_LB),
                         AdsContent.MOBILE_TOP_LB);

    if (isWgVarOn) {
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_AD_IN_CONTENT),
                            AdsContent.MOBILE_AD_IN_CONTENT);
      Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_PREFOOTER),
                            AdsContent.MOBILE_PREFOOTER);
      Thread.sleep(1000 * delaySec);
    }

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_AD_IN_CONTENT),
                         AdsContent.MOBILE_AD_IN_CONTENT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_PREFOOTER),
                         AdsContent.MOBILE_PREFOOTER);
  }

  @RelatedIssue(issueID = "ADEN-3761")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtf",
      groups = "AdsBtfBlockingMercury"
  )
  public void adsAtfDisableBtfMercury(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, MOBILE_SIZE);
    adsBaseObject.waitForPageLoaded();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_TOP_LB),
                         AdsContent.MOBILE_TOP_LB);

    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_AD_IN_CONTENT), isWgVarOn,
        AdsContent.MOBILE_AD_IN_CONTENT);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_PREFOOTER), isWgVarOn,
        AdsContent.MOBILE_PREFOOTER);
  }
}
