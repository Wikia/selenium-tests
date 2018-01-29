package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsBtfBlocking extends TemplateNoFirstLoad {
  private static final Dimension DESKTOP_PAGE_SIZE = new Dimension(1366, 768);
  private static final Dimension TABLET_PAGE_SIZE = new Dimension(850, 600);
  private static final Dimension MOBILE_SIZE = new Dimension(414, 736);
  private static final String ARTICLE_MIDDLE_SECTION_SELECTOR = "#ArticleMidSection.mw-headline";
  private static final Page PAGE_DISABLE_BTF = new Page("project43", "SyntheticTests/Disable_BTF");
  private static final Page PAGE_DISABLE_BTF_HIVI = new Page("project43", "SyntheticTests/Disable_BTF/Unblock_HIVI");
  private static final Page PAGE_DELAY_BTF = new Page("project43", "SyntheticTests/Delay_BTF");

  @Test(
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDelayBtfOasis() {
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, PAGE_DELAY_BTF.getUrl(), DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC), AdsContent.MEDREC);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN), AdsContent.INVISIBLE_SKIN);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.BOTTOM_LB), AdsContent.BOTTOM_LB);
  }

  @Test(
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDisableBtfOasis() {
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, PAGE_DISABLE_BTF.getUrl(), DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC), "Slot" + AdsContent.MEDREC);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), "Slot" + AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN), "Slot" + AdsContent.INVISIBLE_SKIN);

    Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.BOTTOM_LB), "Slot: " + AdsContent.BOTTOM_LB);
    Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_HIGH_IMPACT_2), "Slot: " + AdsContent.INVISIBLE_HIGH_IMPACT_2);
  }

  /**
   * Test whether ads on small screens are displayed when wgAdDriverDelayBelowTheFold is enabled
   */
  @Test(
      dataProviderClass = AdsDataProvider.class
  )
  public void adsAtfOnTabletOasis() {
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, PAGE_DISABLE_BTF.getUrl(), TABLET_PAGE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), AdsContent.TOP_LB);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.BOTTOM_LB), AdsContent.BOTTOM_LB);
  }

  @Test(
      groups = "AdsBtfBlockingOasis"
  )
  public void adsAtfDisableBtfExceptHighlyViewableSlotsOasis() {
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, PAGE_DISABLE_BTF_HIVI.getUrl(), DESKTOP_PAGE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();
    adsBaseObject.scrollToPosition(ARTICLE_MIDDLE_SECTION_SELECTOR);

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MEDREC), String.format("Ad is not loaded inside %s", AdsContent.MEDREC));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.TOP_LB), String.format("Ad is not loaded inside %s", AdsContent.TOP_LB));
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.INVISIBLE_SKIN), String.format("Ad is not loaded inside %s", AdsContent.INVISIBLE_SKIN));

    Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.BOTTOM_LB), "Slot: " + AdsContent.BOTTOM_LB);
  }

  @InBrowser(
      emulator = Emulator.GOOGLE_NEXUS_5,
      browser = Browser.CHROME
  )
  @Test(
      groups = "AdsBtfBlockingMercury"
  )
  public void adsAtfDelayBtfMercury() {
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, PAGE_DELAY_BTF.getUrl(), MOBILE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_AD_IN_CONTENT), AdsContent.MOBILE_AD_IN_CONTENT);
    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_PREFOOTER), AdsContent.MOBILE_PREFOOTER);
  }

  @InBrowser(
      emulator = Emulator.GOOGLE_NEXUS_5,
      browser = Browser.CHROME
  )
  @Test(
      groups = "AdsBtfBlockingMercury"
  )
  public void adsAtfDisableBtfMercury() {
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, PAGE_DISABLE_BTF.getUrl(), MOBILE_SIZE);
    adsBaseObject.waitForPageLoadedWithGpt();

    Assertion.assertTrue(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_TOP_LB));
    Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_AD_IN_CONTENT));
    Assertion.assertFalse(adsBaseObject.checkSlotOnPageLoaded(AdsContent.MOBILE_PREFOOTER));
  }
}
