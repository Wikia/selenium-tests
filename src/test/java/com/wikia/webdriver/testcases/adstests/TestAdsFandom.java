package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.io.IOException;

public class TestAdsFandom extends AdsFandomTestTemplate {


  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fandomAdsPage",
      groups = "AdsDesktopPresenceFandom"
  )
  public void adsFandomDesktopArticleAds(String article) throws IOException {
    String testedPage = urlBuilder.getUrlForFandomPage(article);

    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
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

    wikiPage.verifyFandomDesktopArticleTopLeaderboard();
    wikiPage.verifyFandomTopBoxad();
    wikiPage.verifyFandomDesktopArticleBottomLeaderboard();

  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fandomAdsPage",
      groups = "AdsMobilePresenceFandom"
  )
  public void adsFandomMobileArticleAds(String article) throws IOException {
    String testedPage = urlBuilder.getUrlForFandomPage(article);

    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
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

    wikiPage.verifyFandomMobileArticleTopLeaderboard();
    wikiPage.verifyFandomMobileArticleBottomLeaderboard();

  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fandomAdsHub",
      groups = "AdsDesktopPresenceHubFandom"
  )
  public void adsFandomDesktopHubAds(String article) throws IOException {
    String testedPage = urlBuilder.getUrlForFandomHub(article);

    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
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

    wikiPage.verifyFandomHubTopLeaderboard();
    wikiPage.verifyFandomTopBoxad();
    wikiPage.verifyFandomHubBottomLeaderboard();
    wikiPage.verifyFandomBottomBoxad();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fandomAdsHub",
      groups = "AdsMobilePresenceHubFandom"
  )
  public void adsFandomMobileHubAds(String article) throws IOException {
    String testedPage = urlBuilder.getUrlForFandomHub(article);

    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
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

    wikiPage.verifyFandomHubTopLeaderboard();
    wikiPage.verifyFandomTopBoxad();
    wikiPage.verifyFandomHubBottomLeaderboard();
    wikiPage.verifyFandomBottomBoxad();
  }

}
