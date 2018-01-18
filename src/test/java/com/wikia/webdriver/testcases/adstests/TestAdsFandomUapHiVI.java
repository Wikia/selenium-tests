package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.HiviUap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestAdsFandomUapHiVI extends AdsFandomTestTemplate {

  private static final double DEFAULT_STATE_ASPECT_RATIO = 4.0;
  private static final double RESOLVED_STATE_ASPECT_RATIO = 10.0;
  private static final double MOBILE_VIDEO_ASPECT_RATIO = 272.0 / 153.0;
  private static final By TLB_SELECTOR = By.id("gpt-top-leaderboard");
  private static final String AD_REDIRECT = "http://fandom.wikia.com/articles/legacy-luke-skywalker";

  @Test(
    groups = {"AdsFandomUapHiVi"}
  )
  public void TLBShouldHaveDefaultStateAspectRatio() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), DEFAULT_STATE_ASPECT_RATIO);
  }

  @InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
    groups = {"AdsFandomUapHiVi"}
  )
  public void TLBShouldHaveVideoAspectRatioOnMobile() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), MOBILE_VIDEO_ASPECT_RATIO);
  }

  @Test(
    groups = {"AdsFandomUapHiVi"}
  )
  public void TLBShouldHaveResolvedStateAspectRatioOnSecondPageView() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();

    assertAspectRatio(driver.findElement(TLB_SELECTOR).getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test(
      groups = {"AdsFandomUapHiVi"}
  )
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

  @Test(
      groups = {"AdsFandomUapHiVi"}
  )
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

  @Test(
      groups = {"AdsFandomUapHiVi"}
  )
  public void TLBShouldDisplayResolvedStateAfterVideoEnds() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    HiviUap hiviUap = new HiviUap(driver, "gpt-top-leaderboard");
    hiviUap.waitForVideoEnd();

    assertAspectRatio(slot.getSize(), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test(
      groups = {"AdsFandomUapHiVi"}
  )
  public void adsImageClickedOpensNewPageFandom() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    HiviUap hiviUap = new HiviUap(driver, "gpt-top-leaderboard");
    hiviUap.clickVideo();

    Assert.assertTrue(fandomPage.tabContainsUrl(AD_REDIRECT));
  }

  private void assertAspectRatio(Dimension size, double expected) {
    final double actual = (double) size.getWidth() / (double) size.getHeight();
    Assertion.assertEquals(roundAspectRatio(actual), roundAspectRatio(expected), 0.02, "Aspect ratios are divergent");
  }

  private double roundAspectRatio(double aspectRatio) {
    return new BigDecimal(aspectRatio).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
