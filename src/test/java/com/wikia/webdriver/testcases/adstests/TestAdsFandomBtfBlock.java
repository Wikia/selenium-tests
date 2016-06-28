package com.wikia.webdriver.testcases.adstests;

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
    AdsFandomObject fandomPage = loadPage(article, PAGE_TYPE_ARTICLE);

    fandomPage.verifyTopLeaderboard();
    fandomPage.verifyTopBoxad();
    fandomPage.verifyNoIncontentBoxad();
    fandomPage.verifyNoBottomLeaderboard();
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
    AdsFandomObject fandomPage = loadPage(article, PAGE_TYPE_ARTICLE);

    fandomPage.verifyTopLeaderboard();
    fandomPage.verifyTopBoxad();
    fandomPage.verifyNoBottomBoxad();
    fandomPage.verifyNoBottomLeaderboard();
  }
}
