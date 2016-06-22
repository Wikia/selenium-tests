package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestAdsFandom extends AdsFandomTestTemplate {


  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fandomAdsPage",
      groups = "AdsDesktopPresenceFandom"
  )
  public void adsFandomDesktopAds(String article) throws IOException {
    String testedPage = urlBuilder.getUrlForFandomPage(article);

    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    ((JavascriptExecutor) driver).executeScript(
        "    (function () {\n"
        + "    var s = document.createElement('script');\n"
        + "    s.type = 'text/javascript';\n"
        + "    s.async = true;\n"
        + "    s.src = 'https://code.jquery.com/jquery-2.2.4.min.js';\n"
        + "    var x = document.getElementsByTagName('script')[0];\n"
        + "    x.parentNode.insertBefore(s, x);\n"
        + "    })();"
    );


    wikiPage.verifyFandomDesktopTopLeaderboard();
    wikiPage.verifyFandomTopBoxad();
    wikiPage.verifyFandomDesktopBottomLeaderboard();

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
  public void adsFandomMobileAds(String article) throws IOException {
    String testedPage = urlBuilder.getUrlForFandomPage(article);

    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    ((JavascriptExecutor) driver).executeScript(
        "    (function () {\n"
        + "    var s = document.createElement('script');\n"
        + "    s.type = 'text/javascript';\n"
        + "    s.async = true;\n"
        + "    s.src = 'https://code.jquery.com/jquery-2.2.4.min.js';\n"
        + "    var x = document.getElementsByTagName('script')[0];\n"
        + "    x.parentNode.insertBefore(s, x);\n"
        + "    })();"
    );


    wikiPage.verifyFandomMobileTopLeaderboard();
    wikiPage.verifyFandomMobileBottomLeaderboard();

  }

}
