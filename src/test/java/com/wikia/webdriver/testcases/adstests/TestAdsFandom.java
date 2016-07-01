package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
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
      dataProvider = "fandomAdsPage",
      groups = {"AdsDesktopPresenceFandom", "AdsDesktopFandom"}
  )
  public void adsFandomDesktopArticleAds(String article) {
    AdsFandomObject fandomPage = loadPage(article);
    verifySlots(fandomPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomAdsPage",
      groups = {"AdsMobilePresenceFandom", "AdsMobileFandom"}
  )
  public void adsFandomMobileArticleAds(String article) {
    AdsFandomObject fandomPage = loadPage(article);
    verifySlots(fandomPage);
  }

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomAdsHub",
      groups = {"AdsDesktopPresenceHubFandom", "AdsDesktopFandom"}
  )
  public void adsFandomDesktopHubAds(String hub) {
    AdsFandomObject fandomPage = loadPage(hub, PAGE_TYPE_HUB);
    verifySlots(fandomPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomAdsHub",
      groups = {"AdsMobilePresenceHubFandom", "AdsMobileFandom"}
  )
  public void adsFandomMobileHubAds(String hub) {
    AdsFandomObject fandomPage = loadPage(hub, PAGE_TYPE_HUB);
    verifySlots(fandomPage);
  }

  private void verifySlots(AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();

    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD);
    fandomPage.verifySlot(AdsFandomContent.INCONTENT_BOXAD);
    fandomPage.verifySlot(AdsFandomContent.BOTTOM_LEADERBOARD);
  }
}
