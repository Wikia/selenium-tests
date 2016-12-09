package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VUAP;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

@Test(
        groups = "AdsVuap"
)
public class TestAdsVUAP extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);
  private static final String FANDOM_URL = "http://www.wikia.com/fandom";

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = {"AdsVuapOasis" , "AdsTopAdVideoClosesWhenFinishPlaysOasis"}
  )
  public void adsTopAdVideoClosesWhenFinishPlaysOasis(String slotName, Page page) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, slotName);
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPBottomDesktop",
          groups = {"AdsVuapOasis", "AdsBottomAdVideoClosesWhenFinishPlaysOasis"}
  )
  public void adsBottomAdVideoClosesWhenFinishPlaysOasis(String slotName, Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.scrollToBottomLeaderboard();

    VUAP vuap = new VUAP(driver, slotName);
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = {"AdsVuapOasis", "AdsTopAdImageClickedOpensNewPageOasis"}
  )
  public void adsTopAdImageClickedOpensNewPageOasis(String slotName, Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.clickOnAdImage(slotName);

    Assertion.assertEquals(ads.switchToNewBrowserTab(), FANDOM_URL);

  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPBottomDesktop",
          groups = {"AdsVuapOasis", "AdsBottomAdImageClickedOpensNewPageOasis"}
  )
  public void adsBottomAdImageClickedOpensNewPageOasis(String slotName, Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.scrollToBottomLeaderboard();
    ads.clickOnAdImage(slotName);

    Assertion.assertEquals(ads.switchToNewBrowserTab(), FANDOM_URL);
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = {"AdsVuapSizes", "AdsVuapTopOasis"}
  )
  public void adsCheckSlotSizesOasis(String slotName, Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    final int imageHeight = (int) (ads.getViewPortWidth() / VUAP.IMAGE_ASPECT_RATIO);
    final int videoHeight = (int) (ads.getViewPortWidth() / VUAP.VIDEO_ASPECT_RATIO);

    VUAP vuap = new VUAP(driver, slotName);

    ads.verifySlotSize(slotName, ads.getViewPortWidth(), imageHeight);
    vuap.play();
    vuap.waitForVideoStart();
    ads.verifySlotSize(slotName, ads.getViewPortWidth(), videoHeight);
    vuap.waitForVideoEnd();
    ads.verifySlotSize(slotName, ads.getViewPortWidth(), imageHeight);
  }
}
