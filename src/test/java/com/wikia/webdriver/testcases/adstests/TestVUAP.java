package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VUAP;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestVUAP extends TemplateNoFirstLoad {
  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);
  private static final String TOP_LEADERBOARD_IMAGE = "TOP_LEADERBOARD";
  private static final String BOTTOM_LEADERBOARD_IMAGE = "BOTTOM_LEADERBOARD";
  private static final String FANDOM_URL = "http://www.wikia.com/fandom";

  private final String currentBrowserTab() {
    return driver.getWindowHandle();
  }

  @Test(
          groups = "AdsUapDesktop"
  )
  public void adsTopAdVideoClosesWhenFinishPlaysOasis() {
    Page page = new Page("project43", "SyntheticTests/VUAP");
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, "TOP_LEADERBOARD");
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          groups = "AdsUapDesktop"
  )
  public void adsTopAdImageClickedOpensNewPageOasis() {
    Page page = new Page("project43", "SyntheticTests/VUAP");
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    Assertion.assertTrue(ads.isAdDisplayed(TOP_LEADERBOARD_IMAGE));
    ads.clickOnAdImage(TOP_LEADERBOARD_IMAGE);
    ads.switchToSecondTab(currentBrowserTab());
    verifyUrl();
  }

  @Test(
          groups = "AdsUapDesktop"
  )
  public void adsBottomAdImageClickedOpensNewPageOasis() {
    Page page = new Page("project43", "SyntheticTests/VUAP");
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    ads.scrollToBottomLeaderboard();
    Assertion.assertTrue(ads.isAdDisplayed(BOTTOM_LEADERBOARD_IMAGE));
    ads.clickOnAdImage(BOTTOM_LEADERBOARD_IMAGE);
    ads.switchToSecondTab(currentBrowserTab());
    verifyUrl();
  }

  public void verifyUrl(){
    String ActualUrl = driver.getCurrentUrl().toString();
    Assertion.assertEquals(ActualUrl, FANDOM_URL );
  }
}
