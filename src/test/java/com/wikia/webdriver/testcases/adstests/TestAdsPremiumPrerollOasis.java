package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAdsPremiumPrerollOasis extends TemplateNoFirstLoad {

  private static final Page PAGE_WITH_FV = new Page("project43", "SyntheticTests/Premium/FeaturedVideo");
  private static final Page PAGE_WITH_FV_WITH_SOUND = new Page("project43", "SyntheticTests/Premium/FeaturedVideo/JWPlayer/WithSound");

  private static final Duration AD_LENGTH = Duration.ofSeconds(30);

  @Test(
      groups = {"AdsPremiumPrerollOasis"}
  )
  public void adsPremiumPrerollOasis() {
    AdsJWPlayerObject wikiPage = new AdsJWPlayerObject(driver, PAGE_WITH_FV.getUrl());
    wikiPage.waitForAdStartsPlaying();
    wikiPage.verifyPlayerOnPage();
    wikiPage.verifyPreroll();
    wikiPage.verifyFeaturedVideo();
  }

  @Test(
      groups = {"AdsPremiumPrerollOasis"}
  )
  public void adsPremiumPrerollOasisNoAds() {
    AdsJWPlayerObject wikiPage = new AdsJWPlayerObject(driver, PAGE_WITH_FV.getUrl() + "?noads=1");
    wikiPage.verifyPlayerOnPage();
    wikiPage.verifyFeaturedVideo();
  }

  @Test(
      groups = {"AdsPremiumPrerollOasis", "AdsJWPlayerPrerollOasisWithSound"}
  )
  public void adsJWPlayerPrerollOasisWithSound() {
    AdsJWPlayerObject wikiPage = new AdsJWPlayerObject(driver, PAGE_WITH_FV_WITH_SOUND.getUrl());
    wikiPage.waitForAdStartsPlaying();
    wikiPage.scrollToPlayer();
    wikiPage.clickVolumeButton();
    wikiPage.allowToPlayVideoForSomeTime(Duration.ofSeconds(3));
    Assertion.assertTrue(wikiPage.wasSoundHeard());
  }

  @Test(
      groups = {"AdsPremiumPrerollOasis", "AdsJWPlayerPrerollOasisWithoutSound"}
  )
  public void adsJWPlayerPrerollOasisWithoutSound() {
    AdsJWPlayerObject wikiPage = new AdsJWPlayerObject(driver, PAGE_WITH_FV_WITH_SOUND.getUrl());
    wikiPage.waitForAdStartsPlaying();
    wikiPage.allowToPlayVideoForSomeTime(Duration.ofSeconds(3));
    wikiPage.waitForAdFinish(AD_LENGTH);
    Assertion.assertFalse(wikiPage.wasSoundHeard());
  }
}
