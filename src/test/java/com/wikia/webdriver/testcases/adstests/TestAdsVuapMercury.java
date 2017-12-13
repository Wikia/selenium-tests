package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapAssertions;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VuapVideos;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test(groups = "AdsVuapMercury")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TestAdsVuapMercury extends TemplateNoFirstLoad {
  private static final String AD_REDIRECT_URL = "http://fandom.wikia.com/";

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "adsVuapClickToPlayMobile",
      groups = {"AdsVuapClickToPlayMobile"}
  )
  public void vuapCheckTopAreasMercury(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver,
                                               slot,
                                               ads.findFirstIframeWithAd(slot),
                                               true
    );
    ads.scrollToSlot(slot);

    vuap.clickOnArea(1);
    vuap.clickOnArea(2);

    List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    Assert.assertEquals(
        tabs.size(),
        3,
        "There should be three tabs opened after two clicks."
    );
    final String actual = ads.switchToNewBrowserTab();
    Assert.assertEquals(
        actual,
        AD_REDIRECT_URL,
        "Top part of creative should point to FANDOM page but it points to " + actual
    );
  }

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "adsVuapClickToPlayMobile",
      groups = {"AdsVuapClickToPlayMobile"}
  )
  public void vuapClickToPlayShouldStartPlayingAdvertisementAfterClickOnPlayArea(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);

    ads.scrollToSlot(slot);

    vuap.clickOnArea(3);
    vuap.waitForVideoStart();

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "adsVuapMobile",
      groups = "AdsVuapDefaultStateMercury"
  )
  public void vuapDefaultStateShouldStartPlayingAdvertisementAutomatically(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);

    ads.scrollToSlot(slot);
    vuap.waitForVideoStart();

    VuapAssertions.verifyVideoPlay(vuap);
  }

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "adsVuapMobile",
      groups = {"AdsVuapDefaultStateMercury"}
  )
  public void vuapDefaultStateReplayIsNotMuted(Page page, String slot) throws InterruptedException {
    AdsBaseObject ads = openPageWithVideoInLocalStorage(page, VuapVideos.VIDEO_10s);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), false);
    ads.scrollToSlot(slot);

    // 2017-10-12 scrollTo takes so much time that the video for sure has started and ended
    /*
    vuap.waitForVideoStart();
    vuap.togglePause();
    Assert.assertTrue(vuap.isMuted());
    vuap.play();
    VuapAssertions.verifyReplyButtonDisplayedAfterVideoEnds(vuap, MAX_AUTOPLAY_MOVIE_DURATION);
    */

    vuap.clickOnArea(3);
    vuap.mute();
    vuap.togglePause();

    VuapAssertions.verifyVideoUnmuteAndMute(vuap);
  }

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "adsVuapMobile",
      groups = "AdsVuapTimeProgressMercury"
  )
  public void vuapDefaultStateShouldProgressInTime(Page page, String slot) throws InterruptedException {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForWiki("project43"));
    ads.getUrl(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, ads.findFirstIframeWithAd(slot), true);
    ads.scrollToSlot(slot);

    VuapAssertions.verifyVideoTimeIsProgressing(vuap);
  }
}
