package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VUAP;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Test(
        groups = "AdsVuapDesctop"
)
public class TestAdsVUAP extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);
  private static final String FANDOM_URL = "http://www.wikia.com/fandom";
  private static final String SLOT_NAME = "slotName";
  private static final String SRC = "src";
  private static final int DELAY = 2;
  private static final int VIDEO_START_TIME = 0;


  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsTopAdVideoClosesWhenFinishPlaysOasis"
  )
  public void adsTopAdVideoClosesWhenFinishPlaysOasis(Page page, Map<String, String> map) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, map.get(SRC), map.get(SLOT_NAME));
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }


  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPBottomDesktop",
          groups = "AdsBottomAdVideoClosesWhenFinishPlaysOasis"
  )
  public void adsBottomAdVideoClosesWhenFinishPlaysOasis(Page page, Map<String,String> map) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.scrollToBottomLeaderboard();

    VUAP vuap = new VUAP(driver, map.get(SRC), map.get(SLOT_NAME));
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsTopAdImageClickedOpensNewPageOasis"
  )
  public void adsTopAdImageClickedOpensNewPageOasis(Page page, Map<String, String> map) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.clickOnAdImage(map.get(SLOT_NAME));

    Assertion.assertEquals(ads.switchToNewBrowserTab(), FANDOM_URL);
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPBottomDesktop",
          groups = "AdsBottomAdImageClickedOpensNewPageOasis"
  )
  public void adsBottomAdImageClickedOpensNewPageOasis(Page page, Map<String, String> map) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    ads.scrollToBottomLeaderboard();
    ads.clickOnAdImage(map.get(SLOT_NAME));

    Assertion.assertEquals(ads.switchToNewBrowserTab(), FANDOM_URL);
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsVuapSizes"
  )
  public void adsCheckSlotSizesOasis(Page page, Map<String, String> map) {
    String slotName = map.get(SLOT_NAME);

    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    final int imageHeight = (int) (ads.getViewPortWidth() / VUAP.IMAGE_ASPECT_RATIO);
    final int videoHeight = (int) (ads.getViewPortWidth() / VUAP.VIDEO_ASPECT_RATIO);

    VUAP vuap = new VUAP(driver, map.get(SRC), slotName);

    ads.verifySlotSize(slotName, ads.getViewPortWidth(), imageHeight);
    vuap.play();
    vuap.waitForVideoStart();
    ads.verifySlotSize(slotName, ads.getViewPortWidth(), videoHeight);
    vuap.waitForVideoEnd();
    ads.verifySlotSize(slotName, ads.getViewPortWidth(), imageHeight);
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsVuapTimeProgressing"
  )
  public void adsVuapTimeProgressing(Page page, Map<String, String> map) throws InterruptedException {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    VUAP vuap = new VUAP(driver, map.get(SRC), map.get(SLOT_NAME));

    vuap.play();
    vuap.waitForVideoStart();
    Thread.sleep(DELAY * 1000);

    int time = vuap.getCurrentVideoTime().intValue();

    Thread.sleep(DELAY * 1000);

    Assert.assertEquals(time + DELAY, vuap.getCurrentVideoTime().intValue());
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPTopDesktop",
          groups = "AdsVuapPause"
  )
  public void adsVuapPause(Page page, Map<String, String> map) throws InterruptedException {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    VUAP vuap = new VUAP(driver, map.get(SRC), map.get(SLOT_NAME));

    vuap.play();
    vuap.waitForVideoStart();
    Thread.sleep(DELAY * 1000);

    vuap.pause();
    int time = vuap.getCurrentVideoTime().intValue();

    Thread.sleep(DELAY * 1000);

    Assert.assertNotEquals(VIDEO_START_TIME, vuap.getCurrentVideoTime().intValue());
    Assert.assertEquals(time, vuap.getCurrentVideoTime().intValue());
  }
}
