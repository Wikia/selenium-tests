package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VUAP;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsVUAP extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);
  private static final String FANDOM_URL = "http://www.wikia.com/fandom";
  private static final String PROJECT43 = "project43";
  private static final String VUAP_ARTICLE = "SyntheticTests/VUAP";

  private final String clickThroughUrl() {
    return driver.getCurrentUrl();
  }

  @Test(
          groups = "AdsUapDesktop"
  )
  public void adsTopAdVideoClosesWhenFinishPlaysOasis() {
    Page page = new Page(PROJECT43, VUAP_ARTICLE);
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, AdsContent.TOP_LB);
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          groups = "AdsUapDesktop"
  )
  public void adsBottomAdVideoClosesWhenFinishPlaysOasis() {
    Page page = new Page(PROJECT43, VUAP_ARTICLE);
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.scrollToBottomLeaderboard();

    VUAP vuap = new VUAP(driver, AdsContent.BOTTOM_LB);
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          groups = "AdsUapDesktop"
  )
  public void adsTopAdImageClickedOpensNewPageOasis() {
    Page page = new Page(PROJECT43, VUAP_ARTICLE);
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    Assertion.assertTrue(ads.isAdDisplayed(AdsContent.TOP_LB));
    ads.clickOnAdImage(AdsContent.TOP_LB);
    ads.switchToNewBrowserTab();
    Assertion.assertEquals(clickThroughUrl(), FANDOM_URL);

  }

  @Test(
          groups = "AdsUapDesktop"
  )
  public void adsBottomAdImageClickedOpensNewPageOasis() {
    Page page = new Page(PROJECT43, VUAP_ARTICLE);
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    ads.scrollToBottomLeaderboard();
    Assertion.assertTrue(ads.isAdDisplayed(AdsContent.BOTTOM_LB));
    ads.clickOnAdImage(AdsContent.BOTTOM_LB);
    ads.switchToNewBrowserTab();
    Assertion.assertEquals(clickThroughUrl(), FANDOM_URL);
  }
}
