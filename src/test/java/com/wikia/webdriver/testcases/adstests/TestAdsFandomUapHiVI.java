package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestAdsFandomUapHiVI extends AdsFandomTestTemplate {

  private static final float DEFAULT_STATE_ASPECT_RATIO = 4L;
  private static final float RESOLVED_STATE_ASPECT_RATIO = 10L;
  private static final float MOBILE_ASPECT_RATIO = 412 / 232;
  private static final By TLB_SELECTOR = By.id("gpt-top-leaderboard");

  @Test(
    groups = {"AdsFandomUapHiViDesktop"}
  )
  public void TLBShouldHaveDefaultStateAspectRatio() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();

    Assertion.assertEquals(getAspectRatio(driver.findElement(TLB_SELECTOR)), DEFAULT_STATE_ASPECT_RATIO);
  }

  @Test(
    groups = {"AdsFandomUapHiViDesktop"}
  )
  public void TLBShouldHaveResolvedStateAspectRatioOnSecondPageView() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();

    Assertion.assertEquals(getAspectRatio(driver.findElement(TLB_SELECTOR)), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test(
      groups = {"AdsFandomUapHiViDesktop"}
  )
  public void TLBShouldHaveResolvedStateAspectRatioAfterScroll() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);
    int defaultStateHeight = slot.getSize().getHeight();
    int scrollBy = 50;

    Assertion.assertEquals(getAspectRatio(slot), DEFAULT_STATE_ASPECT_RATIO);

    fandomPage.scrollBy(0, scrollBy);
    Assertion.assertEquals(slot.getSize().getHeight(), defaultStateHeight - scrollBy);

    fandomPage.scrollBy(0, 500);
    Assertion.assertEquals(getAspectRatio(slot), RESOLVED_STATE_ASPECT_RATIO);
  }

  @Test(
      groups = {"AdsFandomUapHiViDesktop"}
  )
  public void TLBResolvedStateShouldKeepAspectRatioAfterScroll() {
    AdsFandomObject fandomPage = loadArticle(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    fandomPage.waitForPageLoad();
    fandomPage.refreshPage();
    fandomPage.waitForPageLoad();
    WebElement slot = driver.findElement(TLB_SELECTOR);

    Assertion.assertEquals(getAspectRatio(slot), RESOLVED_STATE_ASPECT_RATIO);

    fandomPage.scrollBy(0, 500);
    Assertion.assertEquals(getAspectRatio(slot), RESOLVED_STATE_ASPECT_RATIO);
  }

  private float getAspectRatio(WebElement slot) {
    final Dimension size = slot.getSize();
    return Math.round((float) size.getWidth() / (float) slot.getSize().getHeight());
  }
}
