package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;

public class TestAdsFandom extends AdsFandomTestTemplate {

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomAds",
      groups = {"AdsDesktopPresenceFandom", "AdsDesktopFandom"}
  )
  public void adsFandomAdsDesktop(String pageType, String pageName) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    verifySlots(fandomPage, pageType);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomAds",
      groups = {"AdsMobilePresenceFandom", "AdsMobileFandom"}
  )
  public void adsFandomAdsMobile(String pageType, String pageName) {
    AdsFandomObject fandomPage = loadPage(pageName);
    verifySlots(fandomPage, pageType);
  }

  private void verifySlots(AdsFandomObject fandomPage, String pageType) {
    if (pageType.equals(AdsFandomTestTemplate.PAGE_TYPE_ARTICLE)) {
      verifyArticleSlots(fandomPage);
    } else if (pageType.equals(AdsFandomTestTemplate.PAGE_TYPE_HUB)) {
      verifyHubSlots(fandomPage);
    }
  }

  private void verifyArticleSlots(AdsFandomObject fandomPage) {
    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD);
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_LEADERBOARD));
  }

  private void verifyHubSlots(AdsFandomObject fandomPage) {
    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD);
    fandomPage.verifySlot(AdsFandomContent.BOTTOM_BOXAD);
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_LEADERBOARD));
  }
}
