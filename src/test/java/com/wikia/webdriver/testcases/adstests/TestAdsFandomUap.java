package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
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
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    verifyUapAtf(atfId, AdsFandomContent.GPT_TOP_BOXAD_DESKTOP, fandomPage);
    verifyUapBtf(btfId, AdsFandomContent.GPT_INCONTENT_BOXAD, fandomPage);
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
  @RelatedIssue(issueID = "ADEN-4339")
  public void adsFandomUapMobile(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    verifyUapAtf(atfId, AdsFandomContent.GPT_TOP_BOXAD_MOBILE, fandomPage);
    verifyUapBtf(btfId, AdsFandomContent.GPT_INCONTENT_BOXAD_MOBILE, fandomPage);
  }

  private void verifyUapAtf(long atfId, String slotName, AdsFandomObject fandomPage) {
    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD, AdsFandomContent.GPT_TOP_LEADERBOARD);
    Assertion.assertEquals(atfId, fandomPage.getLineItemId(AdsFandomContent.TOP_LEADERBOARD));

    fandomPage.verifySlot(AdsFandomContent.TOP_BOXAD, slotName);
    Assertion.assertEquals(atfId, fandomPage.getLineItemId(AdsFandomContent.TOP_BOXAD));
  }

  private void verifyUapBtf(long btfId, String slotName, AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();

    fandomPage.verifySlot(AdsFandomContent.INCONTENT_BOXAD, slotName);
    Assertion.assertEquals(btfId, fandomPage.getLineItemId(AdsFandomContent.INCONTENT_BOXAD));

    fandomPage.verifySlot(AdsFandomContent.BOTTOM_LEADERBOARD, AdsFandomContent.GPT_BOTTOM_LEADERBOARD);
    Assertion.assertEquals(btfId, fandomPage.getLineItemId(AdsFandomContent.BOTTOM_LEADERBOARD));
    Assertion.assertNull(fandomPage.getSlot(AdsFandomContent.BOTTOM_BOXAD));
  }
}
