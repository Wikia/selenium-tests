package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestAdsFandomUapHiVI extends AdsFandomTestTemplate {

  private static final int DEFAULT_STATE_ASPECT_RATIO = 3;
  private static final int RESOLVED_STATE_ASPECT_RATIO = 10;

  private By TLB_SELECTOR = By.id("gpt-top-leaderboard");

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

  private int getAspectRatio(WebElement TLB) {
    return TLB.getSize().getWidth() / TLB.getSize().getHeight();
  }
}
