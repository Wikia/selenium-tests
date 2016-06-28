package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;

public class TestAdsFandomBtfBlock extends AdsFandomTestTemplate {

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomBtfBlockPage",
      groups = {"AdsFandomBtfBlockDesktop"}
  )
  public void adsFandomBtfBlockDesktop(String article) {
    AdsFandomObject fandomPage = loadPage(article);

    fandomPage.verifyTopLeaderboard();
    fandomPage.verifyTopBoxad();

    Assertion.assertNull(fandomPage.getSlot(AdsFandomObject.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomObject.BOTTOM_LEADERBOARD));
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
    AdsFandomObject fandomPage = loadPage(article);

    fandomPage.verifyTopLeaderboard();
    fandomPage.verifyTopBoxad();

    Assertion.assertNull(fandomPage.getSlot(AdsFandomObject.BOTTOM_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomObject.BOTTOM_LEADERBOARD));
  }
}
