package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

@Test(groups = "AdsUapHiViOasis")
public class TestAdsUapHiViOasis extends TemplateNoFirstLoad {
  private static final double IMPACT_STATE_ASPECT_RATIO = 1600 / 400;
  private static final double RESOLVED_STATE_ASPECT_RATIO = 1600 / 160;
  private static final String AD_REDIRECT = "http://fandom.wikia.com/articles/legacy-luke-skywalker";

  private TestAdsUapHiVi test() {
    return test(AdsDataProvider.UAP_HIVI_PAGE);
  }

  private TestAdsUapHiVi test(Page page) {
    return new TestAdsUapHiVi(driver, new AdsBaseObject(driver, page.getUrl()), AdsContent.TOP_LB);
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
  public void shouldBeFullscreenAfterClickOnIcon() {
    test().shouldBeFullscreenAfterClickOnIcon();
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
