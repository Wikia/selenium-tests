package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;

public class TestAdsFandom extends AdsFandomTestTemplate {

  private void getJquery() {
    driver.executeScript(
        "    (function () {\n"
        + "    var s = document.createElement('script');\n"
        + "    s.type = 'text/javascript';\n"
        + "    s.async = true;\n"
        + "    s.src = 'https://code.jquery.com/jquery-2.2.4.min.js';\n"
        + "    var x = document.getElementsByTagName('script')[0];\n"
        + "    x.parentNode.insertBefore(s, x);\n"
        + "    })();"
    );
  }

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomAdsPage",
      groups = {"AdsDesktopPresenceFandom", "AdsDesktopFandom"}
  )
  public void adsFandomDesktopArticleAds(String article) {
    String testedPage = urlBuilder.getUrlForFandomPage(article);
    AdsFandomObject fandomPage = new AdsFandomObject(driver, testedPage);
    getJquery();
    fandomPage.verifyFandomTopLeaderboard();
    fandomPage.verifyFandomDesktopTopBoxad();
    fandomPage.verifyFandomDesktopArticleBottomLeaderboard();
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
    String testedPage = urlBuilder.getUrlForFandomPage(article);
    AdsFandomObject wikiPage = new AdsFandomObject(driver, testedPage);
    getJquery();
    wikiPage.verifyFandomMobileTopBoxad();
    wikiPage.verifyFandomMobileArticleBottomLeaderboard();
  }

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomAdsHub",
      groups = {"AdsDesktopPresenceHubFandom", "AdsDesktopFandom"}
  )
  public void adsFandomDesktopHubAds(String hub) {
    String testedHub = urlBuilder.getUrlForFandomHub(hub);
    AdsFandomObject fandomHub = new AdsFandomObject(driver, testedHub);
    getJquery();
    fandomHub.verifyFandomTopLeaderboard();
    fandomHub.verifyFandomTopBoxad();
    fandomHub.verifyFandomHubBottomLeaderboard();
    fandomHub.verifyFandomBottomBoxad();
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
    String testedHub = urlBuilder.getUrlForFandomHub(hub);
    AdsFandomObject fandomHub = new AdsFandomObject(driver, testedHub);
    getJquery();
    fandomHub.verifyFandomTopLeaderboard();
    fandomHub.verifyFandomTopBoxad();
    fandomHub.verifyFandomHubBottomLeaderboard();
    fandomHub.verifyFandomBottomBoxad();
  }

}
