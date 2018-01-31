package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.HiViUap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

@InBrowser(
  browser = Browser.CHROME,
  emulator = Emulator.GOOGLE_NEXUS_5
)
@Test(
  groups = {"AdsUapHiViFandomMobile"}
)
public class TestAdsUapHiViFandomMobile extends AdsFandomTestTemplate {

  private static final double IMPACT_STATE_ASPECT_RATIO = 272.0 / 153.0;
  private static final double RESOLVED_STATE_ASPECT_RATIO = 640.0 / 213;
  private static final String TLB_SLOT_ID = "gpt-top-leaderboard";
  private static final By TLB_SELECTOR = By.id(TLB_SLOT_ID);
  private static final String AD_REDIRECT = "http://fandom.wikia.com/articles/legacy-luke-skywalker";

  private AdsFandomObject openPage() {
    return loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
  }

  @Test
  public void shouldHaveCorrectAspectRatioForImpactState() {
    (new TestAdsUapHiVi(driver, openPage(), TLB_SLOT_ID)).shouldHaveCorrectAspectRatioForImpactState(IMPACT_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveCorrectAspectRatioForResolvedState() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveResolvedStateAfterScroll() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);
    int defaultStateHeight = slot.getSize().getHeight();
    int scrollBy = 50;

    assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    fandomPage.scrollBy(0, scrollBy);
    Assertion.assertEquals(slot.getSize().getHeight(), defaultStateHeight - scrollBy);

    fandomPage.scrollBy(0, 500);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldKeepResolvedStateAspectRatioAfterScroll() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    fandomPage.scrollBy(0, 500);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    fandomPage.scrollBy(0, -500);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldAutoplayVideoForImpactState() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();
  }

  @Test
  public void shouldProgressTimeWhilePlaying() throws InterruptedException {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();

    hiViUap.clickVideo();
    final double startProgressBarWidth = hiViUap.getProgressBarWidth();
    TimeUnit.SECONDS.sleep(3);
    hiViUap.clickVideo();

    Assert.assertTrue(startProgressBarWidth < hiViUap.getProgressBarWidth(), "Video time indicator should move.");
  }

  @Test
  public void shouldMuteVideoForAutoplayedImpactState() throws InterruptedException {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldUnmuteVideoAfterClickOnIcon() throws InterruptedException {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);

    hiViUap.waitForVideoStart();
    hiViUap.clickVideo();
    hiViUap.toggleSound();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldPlayUnmutedVideoForReplayedAd() throws InterruptedException {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);

    hiViUap.waitForVideoEnd();
    hiViUap.clickReplayButton();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldRedirectToPageAfterClickOnAd() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.clickVideo();
    hiViUap.clickAd();
    fandomPage.waitForPageLoad();

    Assert.assertTrue(fandomPage.tabContainsUrl(AD_REDIRECT));
  }

  @Test
  public void shouldRedirectAfterClickOnLearnMore() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.clickLearnMore();
    fandomPage.waitForPageLoad();

    Assert.assertTrue(fandomPage.tabContainsUrl(AD_REDIRECT));

  }

  @Test
  public void shouldPauseOnVideoAfterClickOnPauseIcon() throws InterruptedException {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);

    hiViUap.waitForVideoStart();
    TimeUnit.SECONDS.sleep(2);
    hiViUap.clickVideo(); // to trigger ui
    hiViUap.togglePause();
    double time = hiViUap.getCurrentTimeMobile();

    TimeUnit.SECONDS.sleep(3);

    Assert.assertNotEquals(0, hiViUap.getCurrentTimeMobile(), "Video did not start");
    Assert.assertEquals(time, hiViUap.getCurrentTimeMobile(), "Video did not togglePause");
  }

  @Test
  public void shouldBeResolvedStateAfterVideoEnds() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoEnd();

    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldDisplayResolvedStateOnNextPageView() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();
    slot = driver.findElement(TLB_SELECTOR);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();
    slot = driver.findElement(TLB_SELECTOR);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldBeFullscreenAfterClickOnIcon() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();

    HiViUap hiViUap = new HiViUap(driver, "gpt-top-leaderboard");
    hiViUap.waitForVideoStart();
    hiViUap.clickVideo();
    hiViUap.clickFullscreenIcon();
    Dimension windowSize = driver.findElement(By.cssSelector("body")).getSize();

    Assertion.assertEquals(hiViUap.getVideoWidthMobile(), windowSize.width);
  }

  @Test
  public void shouldAutoplayVideoForResolvedState() {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();

    WebElement slot = driver.findElement(TLB_SELECTOR);
    assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();
    slot = driver.findElement(TLB_SELECTOR);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    HiViUap hiViUap = new HiViUap(driver, "gpt-top-leaderboard");
    hiViUap.waitForVideoStart();
  }

  @Test
  public void shouldMuteAutoplayedVideoOnResolvedState() throws InterruptedException {
    AdsFandomObject fandomPage = openPage();
    fandomPage.waitForPageLoad();

    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();

    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
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
