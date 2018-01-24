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

@Test(
  groups = {"AdsUapHiViFandomDesktop"}
)
public class TestAdsUapHiViFandomDesktop extends AdsFandomTestTemplate {

  private static final double DEFAULT_STATE_ASPECT_RATIO = 4.0;
  private static final double RESOLVED_STATE_ASPECT_RATIO = 10.0;
  private static final double MOBILE_VIDEO_ASPECT_RATIO = 272.0 / 153.0;
  private static final String TLB_SLOT_ID = "gpt-top-leaderboard";
  private static final By TLB_SELECTOR = By.id(TLB_SLOT_ID);
  private static final String AD_REDIRECT = "http://fandom.wikia.com/articles/legacy-luke-skywalker";

  @Test
  public void shouldHaveCorrectAspectRatioForImpactState() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), DEFAULT_STATE_ASPECT_RATIO);
  }

  @InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test
  public void TLBShouldHaveVideoAspectRatioOnMobile() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), MOBILE_VIDEO_ASPECT_RATIO);
  }

  @Test
  public void TLBShouldHaveResolvedStateAspectRatioOnSecondPageView() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void TLBShouldHaveResolvedStateAspectRatioAfterScroll() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);
    int defaultStateHeight = slot.getSize().getHeight();
    int scrollBy = 50;

    assertAspectRatio(slot.getSize(), DEFAULT_STATE_ASPECT_RATIO);

    fandomPage.scrollBy(0, scrollBy);
    Assertion.assertEquals(slot.getSize().getHeight(), defaultStateHeight - scrollBy);

    fandomPage.scrollBy(0, 500);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void TLBShouldKeepResolvedStateAspectRatioAfterScroll() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);

    fandomPage.scrollBy(0, 500);
    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void TLBShouldDisplayResolvedStateAfterVideoEnds() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.waitForVideoEnd();

    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test
  public void TLBVideoClickedOpensNewPage() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);
    hiViUap.clickVideo();

    Assert.assertTrue(fandomPage.tabContainsUrl(AD_REDIRECT));
  }

  @Test
  public void TLBVideoPauses() throws Exception {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);

    hiViUap.waitForVideoStart();
    TimeUnit.SECONDS.sleep(2);
    hiViUap.togglePause();
    double time = hiViUap.getCurrentTime();

    TimeUnit.SECONDS.sleep(3);

    Assert.assertNotEquals(0, hiViUap.getCurrentTime(), "Video did not start");
    Assert.assertEquals(time, hiViUap.getCurrentTime(), "Video did not togglePause");
  }

  @Test
  public void TLBVideoPlaysSound() throws Exception {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    HiViUap hiViUap = new HiViUap(driver, TLB_SLOT_ID);

    hiViUap.waitForVideoStart();
    hiViUap.toggleSound();
    TimeUnit.SECONDS.sleep(3);

    Assertion.assertTrue(SoundMonitor.wasSoundHeardOnPage(new JavascriptActions()));
  }

  @Test
  public void videoShouldBeFullscreenAfterClickOnIcon() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();

    HiViUap hiViUap = new HiViUap(driver, "gpt-top-leaderboard");
    hiViUap.waitForVideoStart();
    hiViUap.clickFullscreenIcon();
    Dimension windowSize = driver.manage().window().getSize();

    Assertion.assertEquals(hiViUap.getVideoWidth(), windowSize.width);
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
