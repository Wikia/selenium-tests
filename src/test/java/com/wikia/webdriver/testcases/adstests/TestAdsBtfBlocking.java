package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsBtfBlocking extends TemplateNoFirstLoad {
  private static final Dimension DESKTOP_PAGE_SIZE = new Dimension(1366, 768);
  private static final Dimension TABLET_PAGE_SIZE = new Dimension(850, 600);
  private static final Dimension MOBILE_SIZE = new Dimension(414, 736);
  private static final String ARTICLE_MIDDLE_SECTION_SELECTOR = "#ArticleMidSection.mw-headline";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtf",
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDelayBtfOasis(String wikiName, String article, boolean isWgVarOn)
      throws InterruptedException {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC), AdsContent.MEDREC);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN), AdsContent.INVISIBLE_SKIN);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_HIGH_IMPACT_2), AdsContent.INVISIBLE_HIGH_IMPACT_2);

    adsBaseObject.triggerAdSlot(AdsContent.FLOATING_MEDREC)
        .wait
        .forElementPresent(By.cssSelector(AdsContent.getSlotSelector(AdsContent.FLOATING_MEDREC)));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC), AdsContent.FLOATING_MEDREC);

    adsBaseObject.triggerAdSlot(AdsContent.BOTTOM_LB)
      .wait
      .forElementPresent(By.cssSelector(AdsContent.getSlotSelector(AdsContent.BOTTOM_LB)));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.BOTTOM_LB), AdsContent.BOTTOM_LB);
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
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN));

    adsBaseObject.triggerAdSlot(AdsContent.FLOATING_MEDREC)
        .wait
        .forElementPresent(By.cssSelector(AdsContent.getSlotSelector(AdsContent.FLOATING_MEDREC)));

    adsBaseObject.triggerAdSlot(AdsContent.BOTTOM_LB)
        .wait
        .forElementPresent(By.cssSelector(AdsContent.getSlotSelector(AdsContent.BOTTOM_LB)));

    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.BOTTOM_LB), isWgVarOn,
        AdsContent.BOTTOM_LB);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC), isWgVarOn,
        AdsContent.FLOATING_MEDREC);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_HIGH_IMPACT_2), isWgVarOn,
        AdsContent.INVISIBLE_HIGH_IMPACT_2);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/ADEN-2156
   * Test whether ads on small screens are displayed when wgAdDriverDelayBelowTheFold is enabled
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
    adsBaseObject.waitForPageLoadedWithGpt();

    adsBaseObject.triggerAdSlot(AdsContent.BOTTOM_LB)
        .wait
        .forElementPresent(By.cssSelector(AdsContent.getSlotSelector(AdsContent.BOTTOM_LB)));

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.BOTTOM_LB), AdsContent.BOTTOM_LB);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtfExceptHighlyViewableSlots",
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDisableBtfExceptHighlyViewableSlotsOasis(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();
    adsBaseObject.scrollToPosition(ARTICLE_MIDDLE_SECTION_SELECTOR);


    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC), String.format("Ad is not loaded inside %s", AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), String.format("Ad is not loaded inside %s", AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN), String.format("Ad is not loaded inside %s", AdsContent.INVISIBLE_SKIN));

    adsBaseObject.simulateScrollingToElement(By.id("WikiaFooter"), By.id(AdsContent.FLOATING_MEDREC));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC), String.format("Ad is not loaded inside %s", AdsContent.FLOATING_MEDREC));
    Assertion.assertNotEquals(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_HIGH_IMPACT_2), isWgVarOn, AdsContent.INVISIBLE_HIGH_IMPACT_2);
    Assertion.assertNotEquals(adsBaseObject.checkSlotOnPageLoaded(AdsContent.BOTTOM_LB), isWgVarOn, AdsContent.BOTTOM_LB);
  }

  @InBrowser(
      emulator = Emulator.GOOGLE_NEXUS_5,
      browser = Browser.CHROME
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtf",
      groups = "AdsBtfBlockingMercury"
  )
  public void adsAtfDelayBtfMercury(String wikiName, String article,
                                    boolean isWgVarOn)
      throws InterruptedException {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, MOBILE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_AD_IN_CONTENT),
                         AdsContent.MOBILE_AD_IN_CONTENT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_PREFOOTER),
                         AdsContent.MOBILE_PREFOOTER);
  }

  @InBrowser(
      emulator = Emulator.GOOGLE_NEXUS_5,
      browser = Browser.CHROME
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtf",
      groups = "AdsBtfBlockingMercury"
  )
  public void adsAtfDisableBtfMercury(String wikiName, String article, boolean isWgVarOn) {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, MOBILE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

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
