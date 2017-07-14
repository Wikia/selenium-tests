package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsBtfBlocking extends NewTestTemplate {

  private static final Dimension DESKTOP_PAGE_SIZE = new Dimension(1366, 768);
  private static final Dimension TABLET_PAGE_SIZE = new Dimension(850, 600);
  private static final Dimension MOBILE_SIZE = new Dimension(414, 736);
  private static final String ARTICLE_MIDDLE_SECTION_SELECTOR = "#ArticleMidSection.mw-headline";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtf",
      groups = "AdsBtfBlockingOasis"
  )
  @RelatedIssue(issueID = "ADEN-4344")
  public void adsAtfDelayBtfOasis(String wikiName, String article, boolean isWgVarOn)
      throws InterruptedException {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC), AdsContent.MEDREC);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN),
                         AdsContent.INVISIBLE_SKIN);

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT),
                         AdsContent.PREFOOTER_LEFT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT),
                         AdsContent.PREFOOTER_RIGHT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2),
                         AdsContent.LEFT_SKYSCRAPPER_2);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC),
                         AdsContent.FLOATING_MEDREC);
  }

  @Execute(mockAds = "true")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtfPluto",
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDelayBtfOasisPluto(String wikiName, String article, boolean isWgVarOn)
      throws InterruptedException {
    adsAtfDelayBtfOasis(wikiName, article, isWgVarOn);
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

  @Execute(mockAds = "true")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtfPluto",
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDisableBtfOasisPluto(String wikiName, String article, boolean isWgVarOn) {
    adsAtfDisableBtfOasis(wikiName, article, isWgVarOn);
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
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT),
                         AdsContent.PREFOOTER_LEFT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT),
                         AdsContent.PREFOOTER_RIGHT);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtfExceptHighlyViewableSlots",
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDisableBtfExceptHighlyViewableSlotsOasis(String wikiName, String article,
                                                             boolean isWgVarOn) {
    PageObjectLogging.log("$wgAdDriverDelayBelowTheFold", String.valueOf(isWgVarOn), true);

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();
    adsBaseObject.scrollToPosition(ARTICLE_MIDDLE_SECTION_SELECTOR);

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC), String.format("Ad is not loaded inside %s", AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), String.format("Ad is not loaded inside %s", AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN), String.format("Ad is not loaded inside %s", AdsContent.INVISIBLE_SKIN));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_HIGH_IMPACT_2), String.format("Ad is not loaded inside %s", AdsContent.INVISIBLE_HIGH_IMPACT_2));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.FLOATING_MEDREC), String.format("Ad is not loaded inside %s", AdsContent.FLOATING_MEDREC));

    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT), isWgVarOn,
        AdsContent.PREFOOTER_LEFT);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT), isWgVarOn,
        AdsContent.PREFOOTER_RIGHT);
    Assertion.assertNotEquals(
        adsBaseObject.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2), isWgVarOn,
        AdsContent.LEFT_SKYSCRAPPER_2);
  }

  @Execute(mockAds = "true")
  @InBrowser(
      emulator = Emulator.GOOGLE_NEXUS_5,
      browser = Browser.CHROME
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "delayBtfPluto",
      groups = "AdsBtfBlockingMercury"
  )
  public void adsAtfDelayBtfMercuryPluto(String wikiName, String article,
                                         boolean isWgVarOn)
      throws InterruptedException {
    adsAtfDelayBtfMercury(wikiName, article, isWgVarOn);
  }

  @Execute(mockAds = "true")
  @InBrowser(
      emulator = Emulator.GOOGLE_NEXUS_5,
      browser = Browser.CHROME
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "disableBtfPluto",
      groups = "AdsBtfBlockingMercury"
  )
  public void adsAtfDisableBtfMercuryPluto(String wikiName, String article, boolean isWgVarOn)
      throws InterruptedException {
    adsAtfDisableBtfMercury(wikiName, article, isWgVarOn);
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
  @RelatedIssue(issueID = "ADEN-4344")
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
