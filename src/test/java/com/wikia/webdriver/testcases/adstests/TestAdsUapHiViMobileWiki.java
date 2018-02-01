package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AssertionAds;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.HiViUap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.SoundMonitor;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@InBrowser(
  browser = Browser.CHROME_MOBILE,
  emulator = Emulator.GOOGLE_NEXUS_5
)
@Test(groups = "AdsUapHiViMobileWiki")
public class TestAdsUapHiViMobileWiki extends TemplateNoFirstLoad {
  private static final double IMPACT_STATE_ASPECT_RATIO = 272.0 / 153.0;
  private static final double RESOLVED_STATE_ASPECT_RATIO = 640.0 / 213;
  private static final String TLB_SLOT_ID = "MOBILE_TOP_LEADERBOARD";
  private static final By TLB_SELECTOR = By.id(TLB_SLOT_ID);
  private static final String AD_REDIRECT = "http://fandom.wikia.com/articles/legacy-luke-skywalker";
  private static final Cookie NO_SMART_BANNER_COOKIE = new Cookie("fandom-sb-closed", "1");

  private AdsBaseObject openPage() {
    final Page mainPage = new Page("project43", "Project43_Wikia");
    final AdsBaseObject adsBaseObject = new AdsBaseObject(driver, mainPage.getUrl());
    driver.manage().addCookie(NO_SMART_BANNER_COOKIE); // it must be run until ADEN-6608 will be released
    adsBaseObject.getUrl(AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    return adsBaseObject.waitForPageLoaded();
  }

  private AdsBaseObject openPageOnSecondPV() {
    final AdsBaseObject adsBaseObject = openPage();
    adsBaseObject.refreshPage();
    final WebElement slot = adsBaseObject.wait.forElementPresent(TLB_SELECTOR);
    adsBaseObject.waitForSlotExpanded(slot);
    return adsBaseObject;
  }

  @Test
  public void shouldHaveCorrectAspectRatioForImpactState() {
    openPage();
    (new TestAdsUapHiVi(driver, openPage(), TLB_SLOT_ID)).shouldHaveCorrectAspectRatioForImpactState(IMPACT_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveCorrectAspectRatioForResolvedState() {
    openPageOnSecondPV();
    final WebElement slot = (new HiViUap(driver, TLB_SLOT_ID)).waitForAdLoaded();
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveResolvedStateAfterScroll() throws InterruptedException {
    AdsBaseObject adsPage = openPage();
    WebElement slot = driver.findElement(TLB_SELECTOR);
    int defaultStateHeight = slot.getSize().getHeight();
    int scrollBy = 50;

    AssertionAds.assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    adsPage.scrollBy(0, scrollBy);
    TimeUnit.SECONDS.sleep(1);
    Assertion.assertEquals(slot.getSize().getHeight(), defaultStateHeight - scrollBy);

    adsPage.scrollBy(0, 500);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldKeepResolvedStateAspectRatioAfterScroll() throws InterruptedException {
    AdsBaseObject adsPage = openPageOnSecondPV();

    HiViUap hivi = new HiViUap(driver, TLB_SLOT_ID);
    WebElement slot = hivi.waitForAdLoaded();

    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    adsPage.scrollBy(0, 500);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    adsPage.scrollBy(0, -500);
    TimeUnit.SECONDS.sleep(1);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldAutoplayVideoForImpactState() {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();
  }

  @Test
  public void shouldProgressTimeWhilePlaying() throws InterruptedException {
    openPage();

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
    openPage();
    new HiViUap(driver, TLB_SLOT_ID);

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldUnmuteVideoAfterClickOnIcon() throws InterruptedException {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);

    hiViUap.waitForVideoStart();
    hiViUap.clickVideo();
    hiViUap.toggleSound();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldPlayUnmutedVideoForReplayedAd() throws InterruptedException {
    openPage();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();
    hiViUap.waitForVideoEnd();
    hiViUap.clickReplayButton();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldRedirectToPageAfterClickOnAd() {
    AdsBaseObject adsPage = openPageOnSecondPV();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.clickVideo();
    hiViUap.clickAd();

    Assert.assertTrue(adsPage.tabContainsUrl(AD_REDIRECT));
  }

  @Test
  public void shouldRedirectAfterClickOnLearnMore() {
    AdsBaseObject adsPage = new AdsBaseObject(driver, AdsDataProvider.UAP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.clickLearnMore();

    Assert.assertTrue(adsPage.tabContainsUrl(AD_REDIRECT));
  }

  @Test
  public void shouldPauseOnVideoAfterClickOnPauseIcon() throws InterruptedException {
    openPage();
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
    openPage();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();
    hiViUap.waitForVideoEnd();

    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldDisplayResolvedStateOnNextPageView() {
    openPage();

    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();
    hiViUap.clickVideo();
    hiViUap.clickFullscreenIcon();
    Dimension windowSize = driver.findElement(By.cssSelector("body")).getSize();

    Assertion.assertEquals(hiViUap.getVideoWidthMobile(), windowSize.width);
  }

  @Test
  public void shouldAutoplayVideoForResolvedState() {
    AdsBaseObject adsPage = openPage();

    WebElement slot = adsPage.wait.forElementPresent(TLB_SELECTOR);
    adsPage.waitForSlotExpanded(slot);
    AssertionAds.assertAspectRatio(slot.getSize(), IMPACT_STATE_ASPECT_RATIO);

    adsPage.refreshPage();
    adsPage.waitForPageLoaded();
    slot = adsPage.wait.forElementPresent(TLB_SELECTOR);
    adsPage.waitForSlotExpanded(slot);
    AssertionAds.assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();
  }

  @Test
  public void shouldMuteAutoplayedVideoOnResolvedState() throws InterruptedException {
    openPageOnSecondPV();

    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoStart();

    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertFalse(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void shouldNotAutoplayVideoForClickToPlay() {
    new AdsBaseObject(driver, AdsDataProvider.UAP_CTP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    Assertion.assertFalse(hiViUap.isMobileVideoElementVisible(), "Video started automatically");
  }

  @Test
  public void shouldPlayVideoWithSoundForClickToPlay() throws InterruptedException {
    new AdsBaseObject(driver, AdsDataProvider.UAP_CTP_HIVI_PAGE.getUrl());
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForAdLoaded();

    hiViUap.clickReplayButton();
    hiViUap.waitForVideoStart();
    TimeUnit.SECONDS.sleep(3);
    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }
}
