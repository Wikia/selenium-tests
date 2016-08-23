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

public class TestAdsFandomUap extends AdsFandomTestTemplate {

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomUapPage",
      groups = {"AdsFandomUapDesktop"}
  )
  public void adsFandomUapDesktop(String pageType, String pageName, long atfId, long btfId) {
    verifyUap(pageType, pageName, atfId, btfId);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomUapPage",
      groups = {"AdsFandomUapMobile"}
  )
  public void adsFandomUapMobile(String pageType, String pageName, long atfId, long btfId) {
    verifyUap(pageType, pageName, atfId, btfId);
  }

  private void verifyUap(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);

    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD);
    Assertion.assertEquals(atfId, fandomPage.getLineItemId(AdsFandomContent.TOP_LEADERBOARD));

    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD);
    Assertion.assertEquals(atfId, fandomPage.getLineItemId(AdsFandomContent.TOP_BOXAD));

    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_LEADERBOARD));

    fandomPage.triggerOnScrollSlots();

    fandomPage.verifySlot(AdsFandomContent.INCONTENT_BOXAD);
    Assertion.assertEquals(btfId, fandomPage.getLineItemId(AdsFandomContent.INCONTENT_BOXAD));

    fandomPage.verifySlot(AdsFandomContent.BOTTOM_LEADERBOARD);
    Assertion.assertEquals(btfId, fandomPage.getLineItemId(AdsFandomContent.BOTTOM_LEADERBOARD));

    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_BOXAD));
  }
}
