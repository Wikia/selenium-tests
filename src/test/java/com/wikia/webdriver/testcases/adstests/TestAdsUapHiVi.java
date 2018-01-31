package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AssertionAds;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.HiViUap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class TestAdsUapHiVi {
  private WikiaWebDriver driver;
  private AdsBaseObject page;
  private String slotName;

  TestAdsUapHiVi(WikiaWebDriver driver, AdsBaseObject page, String slotName) {
    this.driver = driver;
    this.page = page;
    this.slotName = slotName;
  }

  public void shouldHaveCorrectAspectRatioForImpactState(double impactStateAspectRatio) {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    AssertionAds.assertAspectRatio(driver.findElement(By.id(slotName)).getSize(), impactStateAspectRatio);
  }

  public void shouldHaveCorrectAspectRatioForResolvedState(double expectedResolvedState) {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    page.refreshPage();

    page.waitForPageLoaded();
    hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    AssertionAds.assertAspectRatio(driver.findElement(By.id(slotName)).getSize(), expectedResolvedState);
  }

  public void shouldHaveResolvedStateAfterScroll(double impactStateAspectRatio, double resolvedStateAspectRatio) throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(By.id(slotName));

    int defaultStateHeight = slot.getSize().getHeight();
    int scrollBy = 50;

    AssertionAds.assertAspectRatio(slot.getSize(), impactStateAspectRatio);

    page.scrollBy(0, scrollBy);
    TimeUnit.SECONDS.sleep(1);
    Assertion.assertEquals(slot.getSize().getHeight(), defaultStateHeight - scrollBy);

    page.scrollBy(0, defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), resolvedStateAspectRatio);
  }

  public void shouldKeepResolvedStateAspectRatioAfterScroll(double resolvedStateAspectRatio) throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    page.refreshPage();
    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(By.id(slotName));

    int defaultStateHeight = slot.getSize().getHeight();

    AssertionAds.assertAspectRatio(slot.getSize(), resolvedStateAspectRatio);

    page.scrollBy(0, defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), resolvedStateAspectRatio);

    page.scrollBy(0, -defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), resolvedStateAspectRatio);
  }

  public void shouldAutoplayVideoForImpactState() {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();
  }

  public void shouldProgressTimeWhilePlaying() throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    final double startProgressBarWidth = hiViUap.getProgressBarWidth();
    TimeUnit.SECONDS.sleep(3);

    Assert.assertTrue(startProgressBarWidth < hiViUap.getProgressBarWidth(), "Video time indicator should move.");
  }

  public void shouldMuteVideoForAutoplayedImpactState() throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  public void shouldUnmuteVideoAfterClickOnIcon() throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoStart();
    hiViUap.toggleSound();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  public void shouldPlayUnmutedVideoForReplayedAd() throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoEnd();
    hiViUap.clickReplayButton();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  public void shouldRedirectToPageAfterClickOnAd(String redirectUrl) {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    page.refreshPage();
    hiViUap.waitForAdLoaded();
    hiViUap.clickAd();
    page.waitForPageLoaded();

    Assert.assertTrue(page.tabContainsUrl(redirectUrl));
  }

  public void shouldPauseOnVideoAfterClickOnPauseIcon() throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
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

  public void shouldBeResolvedStateAfterVideoEnds(double resolvedStateAspectRatio) {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoEnd();

    WebElement slot = driver.findElement(By.id(slotName));
    AssertionAds.assertAspectRatio(slot.getSize(), resolvedStateAspectRatio);
  }

  public void shouldDisplayResolvedStateOnNextPageView(double impactStateAspectRatio, double resolvedStateAspectRatio) {
    HiViUap hiViUap = new HiViUap(driver, slotName);

    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(By.id(slotName));
    AssertionAds.assertAspectRatio(slot.getSize(), impactStateAspectRatio);

    page.refreshPage();
    hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    slot = driver.findElement(By.id(slotName));
    AssertionAds.assertAspectRatio(slot.getSize(), resolvedStateAspectRatio);

    page.refreshPage();
    hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    slot = driver.findElement(By.id(slotName));
    AssertionAds.assertAspectRatio(slot.getSize(), resolvedStateAspectRatio);
  }

  public void shouldBeFullscreenAfterClickOnIcon() {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    hiViUap.enableVideoToolbar();
    hiViUap.clickFullscreenIcon();

    Dimension windowSize = driver.findElement(By.cssSelector("body")).getSize();
    Assertion.assertEquals(hiViUap.getVideoWidth(), windowSize.width);
  }

  public void shouldAutoplayVideoForResolvedState() {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    page.refreshPage();
    hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoStart();
  }

  public void shouldMuteAutoplayedVideoOnResolvedState() throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    page.refreshPage();
    hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  public void shouldNotAutoplayVideoForClickToPlay() {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    Assertion.assertFalse(hiViUap.isVideoElementVisible(), "Video started automatically");
  }

  public void shouldPlayVideoWithSoundForClickToPlay() throws InterruptedException {
    HiViUap hiViUap = new HiViUap(driver, slotName);
    hiViUap.waitForAdLoaded();

    hiViUap.clickReplayButton();
    hiViUap.waitForVideoStart();
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }
}
