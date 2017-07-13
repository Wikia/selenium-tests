package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.F2AdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsF2TestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.testng.annotations.Test;

public class TestAdsF2Uap extends AdsF2TestTemplate {

  @Test(
      dataProviderClass = F2AdsDataProvider.class,
      dataProvider = "fandomArticleUapPage",
      groups = {"AdsF2UapDesktop"}
  )
  public void adsFandomArticleUapDesktop(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    verifyUapAtf(atfId, AdsFandomContent.TOP_BOXAD, fandomPage);
    verifyUapBtf(btfId, AdsFandomContent.INCONTENT_BOXAD, AdsFandomContent.BOTTOM_LEADERBOARD, fandomPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.NEXUS_5X
  )
  @Test(
      dataProviderClass = F2AdsDataProvider.class,
      dataProvider = "fandomArticleUapPage",
      groups = {"AdsF2UapMobile"}
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
      dataProviderClass = F2AdsDataProvider.class,
      dataProvider = "fandomTopicPage",
      groups = {"AdsF2UapDesktop"}
  )
  public void adsF2UapOnTopicDesktop(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    verifyUapAtf(atfId, AdsFandomContent.TOP_BOXAD, fandomPage);
    verifyUapBtf(btfId, AdsFandomContent.FEED_BOXAD, AdsFandomContent.BOTTOM_LEADERBOARD, fandomPage);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.NEXUS_5X
  )
  @Test(
      dataProviderClass = F2AdsDataProvider.class,
      dataProvider = "fandomTopicPage",
      groups = {"AdsF2UapMobile"}
  )
  @RelatedIssue(issueID = "ADEN-4339")
  public void adsF2UapOnTopicMobile(String pageType, String pageName, long atfId, long btfId) {
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
