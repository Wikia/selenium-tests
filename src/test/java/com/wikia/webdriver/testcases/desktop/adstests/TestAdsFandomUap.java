package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdSlot;
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

  @Test(dataProviderClass = FandomAdsDataProvider.class, dataProvider = "fandomArticleUapPage", groups = {
      "AdsFandomUapDesktop"})
  public void adsFandomArticleUapDesktop(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    verifyUapAtf(atfId, fandomPage);
    verifyUapBtf(btfId,
                 AdSlot.INCONTENT_BOXAD,
                 fandomPage
    );
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(dataProviderClass = FandomAdsDataProvider.class, dataProvider = "fandomArticleUapPage", groups = {
      "AdsFandomUapMobile"})
  public void adsFandomArticleUapMobile(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();

    verifyUapAtf(atfId, fandomPage);
    fandomPage.triggerOnScrollSlots();
    verifyUapBtf(btfId,
                 AdSlot.INCONTENT_BOXAD,
                 fandomPage
    );
  }

  @Test(dataProviderClass = FandomAdsDataProvider.class, dataProvider = "fandomTopicPage", groups = {
      "AdsFandomUapDesktop"})
  public void adsFandomUapOnTopicDesktop(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    verifyUapAtf(atfId, fandomPage);
    verifyUapBtf(btfId,
                 AdSlot.FEED_BOXAD_IFRAME,
                 fandomPage
    );
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(dataProviderClass = FandomAdsDataProvider.class, dataProvider = "fandomTopicPage", groups = {
      "AdsFandomUapMobile"})
  public void adsFandomUapOnTopicMobile(String pageType, String pageName, long atfId, long btfId) {
    AdsFandomObject fandomPage = loadPage(pageName, pageType);
    fandomPage.triggerOnScrollSlots();
    verifyUapAtf(atfId, fandomPage);
    verifyUapBtf(btfId,
                 AdSlot.FEED_BOXAD_MOBILE_IFRAME,
                 fandomPage
    );
  }

  private void verifyUapAtf(long atfId, AdsFandomObject fandomPage) {
    fandomPage.verifySlot(AdSlot.TOP_LEADERBOARD);
    Assertion.assertEquals(fandomPage.getLineItemId(AdsFandomContent.TOP_LEADERBOARD), atfId);
  }

  private void verifyUapBtf(
      long btfId, AdSlot slot, AdsFandomObject fandomPage
  ) {
    fandomPage.verifySlot(slot);

    fandomPage.verifySlot(AdSlot.BOTTOM_LEADERBOARD);
    fandomPage.verifySlot(AdSlot.TOP_BOXAD);
    Assertion.assertEquals(fandomPage.getLineItemId(AdsFandomContent.BOTTOM_LEADERBOARD), btfId);
  }
}
