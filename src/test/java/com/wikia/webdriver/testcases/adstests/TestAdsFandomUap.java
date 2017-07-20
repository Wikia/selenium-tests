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
      dataProvider = "fandomArticleUapPage",
      groups = {"AdsFandomUapDesktop"}
  )
  public void adsFandomArticleUapDesktop(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    verifyUapAtf(atfId, AdsFandomContent.TOP_BOXAD, fandomPage);
    verifyUapBtf(btfId, AdsFandomContent.INCONTENT_BOXAD, AdsFandomContent.BOTTOM_LEADERBOARD, fandomPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomArticleUapPage",
      groups = {"AdsFandomUapMobile"}
  )
  @RelatedIssue(issueID = "ADEN-4339")
  public void adsFandomArticleUapMobile(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    
    verifyUapAtf(atfId, AdsFandomContent.TOP_BOXAD, fandomPage);
    fandomPage.triggerOnScrollSlots();
    verifyUapBtf(btfId, AdsFandomContent.INCONTENT_BOXAD, AdsFandomContent.BOTTOM_LEADERBOARD, fandomPage);
  }

  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomTopicPage",
      groups = {"AdsFandomUapDesktop"}
  )
  public void adsFandomUapOnTopicDesktop(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    verifyUapAtf(atfId, AdsFandomContent.TOP_BOXAD, fandomPage);
    verifyUapBtf(btfId, AdsFandomContent.FEED_BOXAD, AdsFandomContent.BOTTOM_LEADERBOARD, fandomPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      dataProviderClass = FandomAdsDataProvider.class,
      dataProvider = "fandomTopicPage",
      groups = {"AdsFandomUapMobile"}
  )
  @RelatedIssue(issueID = "ADEN-4339")
  public void adsFandomUapOnTopicMobile(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    verifyUapAtf(atfId, AdsFandomContent.TOP_BOXAD, fandomPage);
    verifyUapBtf(btfId, AdsFandomContent.FEED_BOXAD, AdsFandomContent.BOTTOM_LEADERBOARD, fandomPage);
  }

  private void verifyUapAtf(long atfId, String slotName, AdsFandomObject fandomPage) {
    fandomPage.verifySlot(AdsFandomContent.TOP_LEADERBOARD);
    Assertion.assertEquals(atfId, fandomPage.getLineItemId(AdsFandomContent.TOP_LEADERBOARD));

    fandomPage.verifySlot(slotName);
    Assertion.assertEquals(atfId, fandomPage.getLineItemId(AdsFandomContent.TOP_BOXAD));
  }

  private void verifyUapBtf(long btfId, String slotName, String bottomSlotName, AdsFandomObject fandomPage) {
    fandomPage.verifySlot(slotName);
    Assertion.assertEquals(btfId, fandomPage.getLineItemId(slotName));

    fandomPage.verifySlot(bottomSlotName);
    Assertion.assertEquals(btfId, fandomPage.getLineItemId(bottomSlotName));
  }
}
