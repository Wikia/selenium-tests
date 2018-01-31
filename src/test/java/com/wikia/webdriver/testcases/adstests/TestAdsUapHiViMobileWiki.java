package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

@InBrowser(
  browser = Browser.CHROME_MOBILE,
  emulator = Emulator.GOOGLE_NEXUS_5
)
@Test(groups = "AdsUapHiViMobileWiki")
public class TestAdsUapHiViMobileWiki extends TemplateNoFirstLoad {
  private static final double IMPACT_STATE_ASPECT_RATIO = 272.0 / 153.0;
  private static final double RESOLVED_STATE_ASPECT_RATIO = 640.0 / 213;
  private static final String TLB_SLOT_ID = "MOBILE_TOP_LEADERBOARD";
  private static final String AD_REDIRECT = "http://fandom.wikia.com/articles/legacy-luke-skywalker";
  private static final Cookie NO_SMART_BANNER_COOKIE = new Cookie("fandom-sb-closed", "1");

  private AdsBaseObject openPage(Page page) {
    final Page mainPage = new Page("project43", "Project43_Wikia");
    final AdsBaseObject adsBaseObject = new AdsBaseObject(driver, mainPage.getUrl());
    driver.manage().addCookie(NO_SMART_BANNER_COOKIE); // it must be run until ADEN-6608 will be released
    adsBaseObject.getUrl(page.getUrl());
    return adsBaseObject.waitForPageLoaded();
  }

  private TestAdsUapHiVi test() {
    return test(AdsDataProvider.UAP_HIVI_PAGE);
  }

  private TestAdsUapHiVi test(Page page) {
    return new TestAdsUapHiVi(driver, openPage(page), TLB_SLOT_ID);
  }

  @Test
  public void shouldHaveCorrectAspectRatioForImpactState() {
    test().shouldHaveCorrectAspectRatioForImpactState(IMPACT_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveCorrectAspectRatioForResolvedState() {
    test().shouldHaveCorrectAspectRatioForResolvedState(RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldHaveResolvedStateAfterScroll() throws InterruptedException {
    test().shouldHaveResolvedStateAfterScroll(IMPACT_STATE_ASPECT_RATIO, RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldKeepResolvedStateAspectRatioAfterScroll() throws InterruptedException {
    test().shouldKeepResolvedStateAspectRatioAfterScroll(RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldAutoplayVideoForImpactState() {
    test().shouldAutoplayVideoForImpactState();
  }

  @Test
  public void shouldProgressTimeWhilePlaying() throws InterruptedException {
    test().shouldProgressTimeWhilePlaying();
  }

  @Test
  public void shouldMuteVideoForAutoplayedImpactState() throws InterruptedException {
    test().shouldMuteVideoForAutoplayedImpactState();
  }

  @Test
  public void shouldUnmuteVideoAfterClickOnIcon() throws InterruptedException {
    test().shouldUnmuteVideoAfterClickOnIcon();
  }

  @Test
  public void shouldPlayUnmutedVideoForReplayedAd() throws InterruptedException {
    test().shouldPlayUnmutedVideoForReplayedAd();
  }

  @Test
  public void shouldRedirectToPageAfterClickOnAd() {
    test().shouldRedirectToPageAfterClickOnAd(AD_REDIRECT);
  }

  @Test
  public void shouldRedirectAfterClickOnLearnMore() {
    test().shouldRedirectAfterClickOnLearnMore(AD_REDIRECT);
  }

  @Test
  public void shouldPauseOnVideoAfterClickOnPauseIcon() throws InterruptedException {
    test().shouldPauseOnVideoAfterClickOnPauseIcon();
  }

  @Test
  public void shouldBeResolvedStateAfterVideoEnds() {
    test().shouldBeResolvedStateAfterVideoEnds(RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldDisplayResolvedStateOnNextPageView() {
    test().shouldDisplayResolvedStateOnNextPageView(IMPACT_STATE_ASPECT_RATIO, RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void shouldAutoplayVideoForResolvedState() {
    test().shouldAutoplayVideoForResolvedState();
  }

  @Test
  public void shouldMuteAutoplayedVideoOnResolvedState() throws InterruptedException {
    test().shouldMuteAutoplayedVideoOnResolvedState();
  }

  @Test
  public void shouldNotAutoplayVideoForClickToPlay() {
    test(AdsDataProvider.UAP_CTP_HIVI_PAGE).shouldNotAutoplayVideoForClickToPlay();
  }

  @Test
  public void shouldPlayVideoWithSoundForClickToPlay() throws InterruptedException {
    test(AdsDataProvider.UAP_CTP_HIVI_PAGE).shouldPlayVideoWithSoundForClickToPlay();
  }
}
