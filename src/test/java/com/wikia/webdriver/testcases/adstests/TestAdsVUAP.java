package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VUAP;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(
        groups = "AdsVuapDesktop"
)
public class TestAdsVUAP extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);
  private static final String FANDOM_URL = "http://www.wikia.com/fandom";
  private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
  private static final String URL_MIDPOINT = "ad_vast_point=midpoint";
  private static final int DELAY = 2;
  private static final int VIDEO_START_TIME = 0;

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsTopAdVideoClosesWhenFinishPlaysOasis"
  )
  public void adsTopAdVideoClosesWhenFinishPlaysOasis(Page page, String slotName, String iframeId) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, iframeId);
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }


  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPBottomDesktop",
          groups = "AdsBottomAdVideoClosesWhenFinishPlaysOasis"
  )
  public void adsBottomAdVideoClosesWhenFinishPlaysOasis(Page page, String slotName, String iframeId) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.scrollToBottomLeaderboard();

    VUAP vuap = new VUAP(driver, iframeId);
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsTopAdImageClickedOpensNewPageOasis"
  )
  public void adsTopAdImageClickedOpensNewPageOasis(Page page, String slotName, String iframeId) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.clickOnAdImage(slotName);

    Assertion.assertEquals(ads.switchToNewBrowserTab(), FANDOM_URL);
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPBottomDesktop",
          groups = "AdsBottomAdImageClickedOpensNewPageOasis"
  )
  public void adsBottomAdImageClickedOpensNewPageOasis(Page page, String slotName, String iframeId) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.scrollToBottomLeaderboard();
    ads.clickOnAdImage(slotName);

    Assertion.assertEquals(ads.switchToNewBrowserTab(), FANDOM_URL);
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsVuapSizes"
  )
  public void adsCheckSlotSizesOasis(Page page, String slotName, String iframeId) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    final int imageHeight = (int) (ads.getViewPortWidth() / VUAP.IMAGE_ASPECT_RATIO);
    final int videoHeight = (int) (ads.getViewPortWidth() / VUAP.VIDEO_ASPECT_RATIO);

    VUAP vuap = new VUAP(driver, iframeId);

    ads.verifySlotSize(slotName, ads.getViewPortWidth(), imageHeight);
    vuap.play();
    vuap.waitForVideoStart();
    ads.verifySlotSize(slotName, ads.getViewPortWidth(), videoHeight);
    vuap.waitForVideoEnd();
    ads.verifySlotSize(slotName, ads.getViewPortWidth(), imageHeight);
  }

  @NetworkTrafficDump
  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsVuapTimeProgressing"
  )
  public void adsVuapTimeProgressing(Page page, String slotName, String iframeId) throws InterruptedException {
    networkTrafficInterceptor.startIntercepting();

    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    VUAP vuap = new VUAP(driver, iframeId);

    vuap.play();
    vuap.waitForVideoStart();

    ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

    int quartileTime = vuap.getCurrentVideoTime().intValue();

    ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);

    int midTime = vuap.getCurrentVideoTime().intValue();

    Assertion.assertTrue(vuap.isTimeProgressing(quartileTime, midTime));
  }

  @NetworkTrafficDump
  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsVuapPause"
  )
  public void adsVuapPause(Page page, String slotName, String iframeId) throws InterruptedException {
    networkTrafficInterceptor.startIntercepting();

    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    VUAP vuap = new VUAP(driver, iframeId);

    vuap.play();
    vuap.waitForVideoStart();
    ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

    vuap.pause();
    int time = vuap.getCurrentVideoTime().intValue();

    Thread.sleep(DELAY * 1000);

    Assert.assertNotEquals(VIDEO_START_TIME, vuap.getCurrentVideoTime().intValue());
    Assert.assertEquals(time, vuap.getCurrentVideoTime().intValue());
  }
}
