package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;

public class TestAdsFandomBtfBlock extends AdsFandomTestTemplate {

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
      dataProvider = "fandomBtfBlockPage",
      groups = {"AdsFandomBtfBlockDesktop"}
  )
  public void adsFandomBtfBlockDesktop(String article) {
    String testedPage = urlBuilder.getUrlForFandomPage(article);
    AdsFandomObject fandomPage = new AdsFandomObject(driver, testedPage);
    getJquery();

    fandomPage.verifyFandomTopLeaderboard();
    fandomPage.verifyFandomTopBoxadDesktop();
    fandomPage.verifyNoFandomIncontentBoxadDesktop();
    fandomPage.verifyNoFandomBottomLeaderboardDesktop();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomBtfBlockPage",
      groups = {"AdsFandomBtfBlockMobile"}
  )
  public void adsFandomBtfBlockMobile(String article) {
    String testedPage = urlBuilder.getUrlForFandomPage(article);
    AdsFandomObject fandomPage = new AdsFandomObject(driver, testedPage);
    getJquery();

    fandomPage.verifyFandomTopLeaderboard();
    fandomPage.verifyFandomTopBoxadMobile();
    fandomPage.verifyNoFandomBottomBoxadMobile();
    fandomPage.verifyNoFandomBottomLeaderboardMobile();
  }
}
