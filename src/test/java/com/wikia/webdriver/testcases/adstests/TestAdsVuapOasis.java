package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VideoFanTakeover;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(
        groups = "AdsVuapOasis"
)
public class TestAdsVuapOasis extends TemplateNoFirstLoad {

  private static final String URL_FIRSTQUARTILE = "ad_vast_point=firstquartile";
  private static final String URL_MIDPOINT = "ad_vast_point=midpoint";
  private static final int DELAY = 2;

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVuapDesktop",
          groups = "AdsVideoClosesWhenFinishPlaysOasis"
  )
  public void adsVideoClosedAfterPlayingOasis(Page page, String slotName, String iframeId) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), WindowSize.DESKTOP);
    scrollToSlot(slotName, ads);
    VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

    videoFanTakeover.play();

    videoFanTakeover.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVuapDesktop",
          groups = "AdsImageClickedOpensNewPageOasis"
  )
  public void adsImageClickedOpensNewPageOasis(Page page, String slotName, String iframeId) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), WindowSize.DESKTOP);
    scrollToSlot(slotName, ads);
    VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

    videoFanTakeover.clickOnAdImage();

    String tabUrl = ads.switchToNewBrowserTab();
    videoFanTakeover.verifyFandomTabOpened(tabUrl);
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVuapDesktop",
          groups = "AdsVuapVideoClosesWhenTapCloseButtonOasis"
  )
  public void adsVuapVideoClosesWhenTapCloseButtonOasis(Page page, String slotName, String iframeId) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), WindowSize.DESKTOP);
    scrollToSlot(slotName, ads);
    VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

    videoFanTakeover.play();

    videoFanTakeover.clickOnVideoCloseButton();

    videoFanTakeover.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVuapDesktop",
          groups = "AdsVuapCheckSlotSizesOasis"
  )
  public void adsVuapCheckSlotSizesOasis(Page page, String slotName, String iframeId) throws InterruptedException {
    String slotSelector = AdsContent.getSlotSelector(slotName);
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), WindowSize.DESKTOP);
    scrollToSlot(slotName, ads);
    VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

    videoFanTakeover.waitForAdToLoad();
    double imageHeight = videoFanTakeover.getAdSlotHeight(slotSelector);

    videoFanTakeover.play();

    double videoHeight = videoFanTakeover.getAdVideoHeight();
    Assertion.assertTrue(videoFanTakeover.isVideoAdBiggerThanImageAdOasis(videoHeight, imageHeight ));

    videoFanTakeover.waitForVideoPlayerHidden();
    Assertion.assertTrue(videoFanTakeover.isImageAdInCorrectSize(imageHeight, slotSelector));
  }

  @NetworkTrafficDump
  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVuapDesktop",
          groups = "AdsVuapTimeProgressingOasis"
  )
  public void adsVuapTimeProgressingOasis(Page page, String slotName, String iframeId) throws InterruptedException {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), WindowSize.DESKTOP);
    scrollToSlot(slotName, ads);
    VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

    videoFanTakeover.play();

    ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);
    double quartileTime = videoFanTakeover.getCurrentVideoTimeOnDesktop().doubleValue();

    ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_MIDPOINT);
    double midTime = videoFanTakeover.getCurrentVideoTimeOnDesktop().doubleValue();
    Assertion.assertTrue(videoFanTakeover.isTimeProgressing(quartileTime, midTime));
  }

  @NetworkTrafficDump
  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVuapDesktop",
          groups = "AdsVuapVideoPauseOasis"
  )
  public void adsVuapVideoPauseOasis(Page page, String slotName, String iframeId) throws InterruptedException {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), WindowSize.DESKTOP);
    scrollToSlot(slotName, ads);
    VideoFanTakeover videoFanTakeover = new VideoFanTakeover(driver, iframeId, slotName);

    videoFanTakeover.play();

    ads.wait.forSuccessfulResponse(networkTrafficInterceptor, URL_FIRSTQUARTILE);

    videoFanTakeover.pause();

    double time = videoFanTakeover.getCurrentVideoTimeOnDesktop().doubleValue();

    Thread.sleep(DELAY * 1000);

    Assert.assertNotEquals(0, videoFanTakeover.getCurrentVideoTimeOnDesktop().doubleValue());
    Assert.assertEquals(time, videoFanTakeover.getCurrentVideoTimeOnDesktop().doubleValue());
  }

  private void scrollToSlot(String slotName, AdsBaseObject ads) {
    if (slotName == AdsContent.BOTTOM_LB) {
      ads.triggerComments();
    }
  }
}
