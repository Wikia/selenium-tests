package com.wikia.webdriver.testcases.adstests;

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

    fandomPage.verifySlot(AdsFandomObject.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomObject.TOP_BOXAD);
    fandomPage.verifySlot(AdsFandomObject.BOTTOM_LEADERBOARD);
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

    fandomPage.verifySlot(AdsFandomObject.TOP_BOXAD);
    fandomPage.verifySlot(AdsFandomObject.BOTTOM_LEADERBOARD);
  }

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomAdsHub",
      groups = {"AdsDesktopPresenceHubFandom", "AdsDesktopFandom"}
  )
  public void adsFandomDesktopHubAds(String hub) {
    verifySlotsOnHubPage(hub);
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
    verifySlotsOnHubPage(hub);
  }

  private void verifySlotsOnHubPage(String hub) {
    AdsFandomObject fandomPage = loadPage(hub, PAGE_TYPE_HUB);

    fandomPage.verifySlot(AdsFandomObject.TOP_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomObject.TOP_BOXAD);
    fandomPage.verifySlot(AdsFandomObject.BOTTOM_LEADERBOARD);
    fandomPage.verifySlot(AdsFandomObject.BOTTOM_BOXAD);
  }
}
