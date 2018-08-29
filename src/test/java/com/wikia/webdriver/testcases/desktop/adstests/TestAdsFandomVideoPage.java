package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdSlot;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;

import org.testng.annotations.Test;

public class TestAdsFandomVideoPage extends AdsFandomTestTemplate {

  @Test(groups = "AdsVideoPageF2Desktop")
  public void adsVideoPageAdsDesktop() {
    adsVideoPageAds();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "AdsVideoPageF2Mobile")
  public void adsVideoPageAdsMobile() {
    adsVideoPageAds();
  }

  private void adsVideoPageAds() {
    String
        testedPage
        = urlBuilder.globallyEnableGeoInstantGlobalOnPage(FandomAdsDataProvider.VIDEO_PAGE_SLUG,
                                                          FandomAdsDataProvider.INSTANT_GLOBAL_MIDROLL
    );
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage,
                                                                 FandomAdsDataProvider.INSTANT_GLOBAL_POSTROLL
    );

    AdsFandomObject pageObject = loadVideoPage(testedPage);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject();

    jwPlayerObject.verifyPreroll();
    verifySlots(pageObject);
  }

  private void verifySlots(AdsFandomObject fandomPage) {
    fandomPage.triggerOnScrollSlots();
    Assertion.assertNull(fandomPage.getSlot(AdSlot.TOP_LEADERBOARD));
    Assertion.assertNull(fandomPage.getSlot(AdSlot.TOP_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdSlot.FEED_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdSlot.BOTTOM_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdSlot.INCONTENT_BOXAD));
    Assertion.assertNull(fandomPage.getSlot(AdSlot.BOTTOM_LEADERBOARD));
  }
}
