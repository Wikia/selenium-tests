package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.F2AdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsF2TestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;

public class TestAdsF2 extends AdsF2TestTemplate {

  @Test(
      dataProviderClass = F2AdsDataProvider.class,
      dataProvider = "fandomAds",
      groups = {"AdsDesktopPresenceF2", "AdsDesktopF2"}
  )
  public void adsFandomAdsDesktop(String pageType, String pageName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    verifySlotsOnDesktop(fandomPage, pageType);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = F2AdsDataProvider.class,
      dataProvider = "fandomAds",
      groups = {"AdsMobilePresenceF2", "AdsMobileF2"}
  )
  public void adsFandomAdsMobile(String pageType, String pageName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    verifySlotsOnMobile(fandomPage, pageType);
  }

  private void verifySlotsOnDesktop(AdsFandomObject fandomPage, String pageType) {
    if (pageType.equals(AdsF2TestTemplate.PAGE_TYPE_ARTICLE)) {
      verifyArticleSlotsOnDesktop(fandomPage);
    } else if (pageType.equals(AdsF2TestTemplate.PAGE_TYPE_TOPIC)) {
      verifyTopicSlots(fandomPage);
    }
  }

  private void verifySlotsOnMobile(AdsFandomObject fandomPage, String pageType) {
    if (pageType.equals(AdsF2TestTemplate.PAGE_TYPE_ARTICLE)) {
      verifyArticleSlotsOnMobile(fandomPage);
    } else if (pageType.equals(AdsF2TestTemplate.PAGE_TYPE_TOPIC)) {
      verifyTopicSlots(fandomPage);
    }
  }

  private void verifyArticleSlotsOnDesktop(AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();
    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD);
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.FEED_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_LEADERBOARD));
  }

  private void verifyArticleSlotsOnMobile(AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();
    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD);
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_LEADERBOARD));
  }

  private void verifyTopicSlots(AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();
    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD);
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.FEED_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_LEADERBOARD));
  }
}
