package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AssertionAds;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.HiViUap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test(
  groups = {"AdsUapHiViFandomDesktop"}
)
public class TestAdsUapHiViFandomDesktop extends AdsFandomTestTemplate {
  private static final double IMPACT_STATE_ASPECT_RATIO = 4.0;
  private static final double RESOLVED_STATE_ASPECT_RATIO = 10.0;
  private static final String TLB_SLOT_ID = "gpt-top-leaderboard";
  private static final By TLB_SELECTOR = By.id(TLB_SLOT_ID);
  private static final String AD_REDIRECT = "http://fandom.wikia.com/articles/legacy-luke-skywalker";

  private AdsBaseObject openPage() {
    return loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
  }

  @Test
  public void shouldHaveCorrectAspectRatioForImpactState() {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    AssertionAds.assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), IMPACT_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveCorrectAspectRatioForResolvedState() {
    AdsBaseObject page = openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    page.refreshPage();

    page.waitForPageLoaded();
    hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    AssertionAds.assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveResolvedStateAfterScroll() throws InterruptedException {
    AdsBaseObject page = openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    int defaultStateHeight = slot.getSize().getHeight();
    int scrollBy = 50;

    AssertionAds.assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    page.scrollBy(0, scrollBy);
    TimeUnit.SECONDS.sleep(1);
    Assertion.assertEquals(slot.getSize().getHeight(), defaultStateHeight - scrollBy);

    page.scrollBy(0, defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldKeepResolvedStateAspectRatioAfterScroll() throws InterruptedException {
    AdsBaseObject page = openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    page.refreshPage();
    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    int defaultStateHeight = slot.getSize().getHeight();

    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    page.scrollBy(0, defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    page.scrollBy(0, -defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldAutoplayVideoForImpactState() {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();
  }

  @Test
  public void shouldProgressTimeWhilePlaying() throws InterruptedException {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    final double startProgressBarWidth = hiViUap.getProgressBarWidth();
    TimeUnit.SECONDS.sleep(3);

    Assert.assertTrue(startProgressBarWidth < hiViUap.getProgressBarWidth(), "Video time indicator should move.");
  }

  @Test
  public void shouldMuteVideoForAutoplayedImpactState() throws InterruptedException {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldUnmuteVideoAfterClickOnIcon() throws InterruptedException {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoStart();
    hiViUap.toggleSound();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldPlayUnmutedVideoForReplayedAd() throws InterruptedException {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoEnd();
    hiViUap.clickReplayButton();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldRedirectToPageAfterClickOnAd() {
    AdsBaseObject page = openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    page.refreshPage();
    hiViUap.waitForAdLoaded();
    hiViUap.clickAd();
    page.waitForPageLoaded();

    Assert.assertTrue(page.tabContainsUrl(AD_REDIRECT));
  }

  @Test
  public void shouldPauseOnVideoAfterClickOnPauseIcon() throws InterruptedException {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoStart();
    TimeUnit.SECONDS.sleep(2);
    hiViUap.enableVideoToolbar();
    hiViUap.togglePause();

    final double startProgressBarWidth = hiViUap.getProgressBarWidth();
    TimeUnit.SECONDS.sleep(3);

    Assert.assertTrue(hiViUap.getProgressBarWidth() > 0, "Video did not start");
    Assert.assertEquals(startProgressBarWidth, hiViUap.getProgressBarWidth(), "Video did not togglePause");
  }

  @Test
  public void shouldBeResolvedStateAfterVideoEnds() {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoEnd();

    WebElement slot = driver.findElement(TLB_SELECTOR);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldDisplayResolvedStateOnNextPageView() {
    AdsBaseObject page = openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);

    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(TLB_SELECTOR);
    AssertionAds.assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    page.refreshPage();
    hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    slot = driver.findElement(TLB_SELECTOR);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    page.refreshPage();
    hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    slot = driver.findElement(TLB_SELECTOR);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldBeFullscreenAfterClickOnIcon() {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    hiViUap.enableVideoToolbar();
    hiViUap.clickFullscreenIcon();

    Dimension windowSize = driver.findElement(By.cssSelector("body")).getSize();
    Assertion.assertEquals(hiViUap.getVideoWidth(), windowSize.width);
  }

  @Test
  public void shouldAutoplayVideoForResolvedState() {
    AdsBaseObject page = openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    page.refreshPage();
    hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoStart();
  }

  @Test
  public void shouldMuteAutoplayedVideoOnResolvedState() throws InterruptedException {
    AdsBaseObject page = openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    page.refreshPage();
    hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldNotAutoplayVideoForClickToPlay() {
    loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_CTP);
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    Assertion.assertFalse(hiViUap.isVideoElementVisible(), "Video started automatically");
  }

  @Test
  public void shouldPlayVideoWithSoundForClickToPlay() throws InterruptedException {
    loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_CTP);
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    hiViUap.clickReplayButton();
    hiViUap.waitForVideoStart();
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }
}
