package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.HiViUap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

@Test(
  groups = {"AdsUapHiViOasis"}
)
public class TestAdsUapHiViOasis extends TemplateNoFirstLoad {

  private static final double IMPACT_STATE_ASPECT_RATIO = 1600 / 400;
  private static final double RESOLVED_STATE_ASPECT_RATIO = 1600 / 160;
  private static final By TLB_SELECTOR = By.id(AdsContent.TOP_LB);
  private static final String AD_REDIRECT = "http://fandom.wikia.com/articles/legacy-luke-skywalker";

  @Test
  public void shouldHaveCorrectAspectRatioForImpactState() {
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), IMPACT_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveCorrectAspectRatioForResolvedState() throws InterruptedException {
    AdsBaseObject page = new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    page.refreshPage();

    page.waitForPageLoaded();
    hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveResolvedStateAfterScroll() throws InterruptedException {
    AdsBaseObject page = new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    int defaultStateHeight = slot.getSize().getHeight();
    int scrollBy = 50;

    assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    page.scrollBy(0, scrollBy);
    TimeUnit.SECONDS.sleep(1);
    Assertion.assertEquals(slot.getSize().getHeight(), defaultStateHeight - scrollBy);

    page.scrollBy(0, defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldKeepResolvedStateAspectRatioAfterScroll() throws InterruptedException {
    AdsBaseObject page = new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    page.refreshPage();
    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    int defaultStateHeight = slot.getSize().getHeight();

    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    page.scrollBy(0, defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    page.scrollBy(0, -defaultStateHeight);
    TimeUnit.SECONDS.sleep(1);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldAutoplayVideoForImpactState() {
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();
  }

  @Test
  public void shouldProgressTimeWhilePlaying() throws InterruptedException {
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    final double startProgressBarWidth = hiViUap.getProgressBarWidth();
    TimeUnit.SECONDS.sleep(3);

    Assert.assertTrue(startProgressBarWidth < hiViUap.getProgressBarWidth(), "Video time indicator should move.");
  }

  @Test
  public void shouldMuteVideoForAutoplayedImpactState() throws InterruptedException {
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldUnmuteVideoAfterClickOnIcon() throws InterruptedException {
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoStart();
    hiViUap.toggleSound();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldPlayUnmutedVideoForReplayedAd() throws InterruptedException {
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoEnd();
    hiViUap.clickReplayButton();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldRedirectToPageAfterClickOnAd() {
    AdsBaseObject page = new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    page.refreshPage();
    hiViUap.waitForAdLoaded();
    hiViUap.clickAd();
    page.waitForPageLoaded();

    Assert.assertTrue(page.tabContainsUrl(AD_REDIRECT));
  }

  @Test
  public void shouldPauseOnVideoAfterClickOnPauseIcon() throws InterruptedException {
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
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
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoEnd();

    WebElement slot = driver.findElement(TLB_SELECTOR);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldDisplayResolvedStateOnNextPageView() {
    AdsBaseObject page = new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);

    hiViUap.waitForAdLoaded();
    WebElement slot = driver.findElement(TLB_SELECTOR);
    assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    page.refreshPage();
    hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    slot = driver.findElement(TLB_SELECTOR);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    page.refreshPage();
    hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    slot = driver.findElement(TLB_SELECTOR);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldBeFullscreenAfterClickOnIcon() {
    new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    hiViUap.enableVideoToolbar();
    hiViUap.clickFullscreenIcon();

    Dimension windowSize = driver.findElement(By.cssSelector("body")).getSize();
    Assertion.assertEquals(hiViUap.getVideoWidth(), windowSize.width);
  }

  @Test
  public void shouldAutoplayVideoForResolvedState() {
    AdsBaseObject page = new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    page.refreshPage();
    hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    hiViUap.waitForVideoStart();
  }

  @Test
  public void shouldMuteAutoplayedVideoOnResolvedState() throws InterruptedException {
    AdsBaseObject page = new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    page.refreshPage();
    hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldNotAutoplayVideoForClickToPlay() throws InterruptedException {
    new AdsBaseObject(driver, AdsDataProvider.UAP_CTP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    Assertion.assertFalse(hiViUap.isVideoElementVisible(), "Video started automatically");
  }

  @Test
  public void shouldPlayVideoWithSoundForClickToPlay() throws InterruptedException {
    new AdsBaseObject(driver, AdsDataProvider.UAP_CTP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, AdsContent.TOP_LB);
    hiViUap.waitForAdLoaded();

    hiViUap.clickReplayButton();
    hiViUap.waitForVideoStart();
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  private void assertAspectRatio(Dimension size, double expected) {
    final double actual = (double) size.getWidth() / (double) size.getHeight();
    // Some divergent is possible because of browser size rounding
    Assertion.assertEquals(roundAspectRatio(actual), roundAspectRatio(expected), 0.03, "Aspect ratios are divergent");
  }

  private double roundAspectRatio(double aspectRatio) {
    return new BigDecimal(aspectRatio).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
