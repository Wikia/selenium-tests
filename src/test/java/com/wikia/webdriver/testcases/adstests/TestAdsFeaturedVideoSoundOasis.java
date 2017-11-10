package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAdsFeaturedVideoSoundOasis extends TemplateNoFirstLoad {

  private static final Duration AD_LENGTH = Duration.ofSeconds(30);

  @Test(
      groups = {"AdsFeaturedVideoSoundOasis", "AdsJWPlayerPrerollOasisWithSound"}
  )
  public void adsJWPlayerOasisWithSound() {
    AdsJWPlayerObject wikiPage = new AdsJWPlayerObject(driver, AdsDataProvider.PAGE_FV_JWPLAYER_AND_SOUND.getUrl());
    wikiPage.waitForAdPlaying();
    wikiPage.scrollToPlayer();
    wikiPage.clickVolumeButton();
    wikiPage.allowToPlayVideoForSomeTime(Duration.ofSeconds(3));
    Assertion.assertTrue(wikiPage.wasSoundHeard());
  }

  @Test(
      groups = {"AdsFeaturedVideoSoundOasis", "AdsJWPlayerOasisWithoutSound"}
  )
  public void adsJWPlayerOasisWithoutSound() {
    AdsJWPlayerObject wikiPage = new AdsJWPlayerObject(driver, AdsDataProvider.PAGE_FV_JWPLAYER_AND_SOUND.getUrl());
    wikiPage.waitForAdPlaying();
    wikiPage.allowToPlayVideoForSomeTime(Duration.ofSeconds(3));
    wikiPage.waitForAdFinish(AD_LENGTH);
    Assertion.assertFalse(wikiPage.wasSoundHeard());
  }
}
