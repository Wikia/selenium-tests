package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAdsFeaturedVideoSound extends TemplateNoFirstLoad {
  private static final Duration AD_LENGTH = Duration.ofSeconds(30);

  @Test(
      groups = {"AdsFeaturedVideoSoundOasis"}
  )
  public void adsFeaturedVideoWithSoundOasis() {
    verifyFeaturedVideoWithSound(AdsDataProvider.PAGE_FV_JWPLAYER_AND_SOUND.getUrl());
  }

  @Test(
      groups = {"AdsFeaturedVideoSoundOasis"}
  )
  public void adsFeaturedVideoWithoutSoundOasis() {
    verifyFeaturedVideoWithoutSound(AdsDataProvider.PAGE_FV_JWPLAYER_AND_SOUND.getUrl());
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.NEXUS_5X_WITHOUT_TOUCH
  )
  @Test(
      groups = {"AdsFeaturedVideoSoundMercury"}
  )
  public void adsFeaturedVideoWithSoundMercury() {
    verifyFeaturedVideoWithSound(AdsDataProvider.PAGE_FV_JWPLAYER_AND_SOUND.getUrl());
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFeaturedVideoSoundMercury"}
  )
  public void adsFeaturedVideoWithoutSoundMercury() {
    verifyFeaturedVideoWithoutSound(AdsDataProvider.PAGE_FV_JWPLAYER_AND_SOUND.getUrl());
  }

  private void verifyFeaturedVideoWithSound(String pageUrl) {
    AdsBaseObject pageObject = new AdsBaseObject(driver, pageUrl);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.waitForAdPlaying();
    pageObject.scrollTo(driver.findElement(AdsJWPlayerObject.PLAYER_SELECTOR));
    jwPlayerObject.clickVolumeButton();
    jwPlayerObject.allowToPlayVideoForSomeTime(Duration.ofSeconds(3));
    Assertion.assertTrue(jwPlayerObject.wasSoundHeard());
  }

  private void verifyFeaturedVideoWithoutSound(String pageUrl) {
    new AdsBaseObject(driver, pageUrl);
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver);

    jwPlayerObject.waitForAdPlaying();
    jwPlayerObject.allowToPlayVideoForSomeTime(Duration.ofSeconds(3));
    jwPlayerObject.waitForAdFinish(AD_LENGTH);
    jwPlayerObject.waitForMoviePlaying();
    Assertion.assertFalse(jwPlayerObject.wasSoundHeard());
  }
}
