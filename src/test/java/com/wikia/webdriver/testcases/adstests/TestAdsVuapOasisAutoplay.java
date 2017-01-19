package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test(groups = "AdsVuapAutoplayOasis")
public class TestAdsVuapOasisAutoplay extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  @Test(groups = "AdsVuapAutoplayAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapAutoplayDesktop")
  public void vuapAutoplayShouldStartPlayingAdvertisementAutomatically(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    vuap.pause();

    Assert.assertTrue(vuap.hasStarted(), "VUAP did not automatically played when page was opened.");
    Assert.assertEquals(vuap.findTitle(), "Advertisement", "VUAP video title is not Advertisement.");
  }

  @Test(groups = "AdsVuapAutoplayStopOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapAutoplayDesktop")
  public void vuapAutoplayShouldCloseWhenClickingCloseButton(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    vuap.stop();

    Assert.assertTrue(vuap.isInvisible(), "VUAP should not be visible.");
  }

  @Test(groups = "AdsVuapAutoplayTimeProgressOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapAutoplayDesktop")
  public void vuapAutoplayShouldProgressInTime(Page page, String slot, String videoIframeSelector) {
    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    vuap.pause();

    final double currentTime = vuap.getCurrentTime();
    final double indicatorCurrentTime = vuap.getIndicatorCurrentTime();

    playVideoForOneSecond(vuap);

    vuap.pause();

    Assert.assertTrue(currentTime < vuap.getCurrentTime(), "Video should be played.");
    Assert.assertTrue(indicatorCurrentTime > vuap.getIndicatorCurrentTime(), "Video time indicator should move.");
  }

  @Test(groups = "AdsVuapAutoplayClickOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapAutoplayDesktop")
  public void vuapAutoplayShouldHaveLinkToFandomOnImage(Page page, String slot, String videoIframeSelector) {
    final String expected = "http://fandom.wikia.com/";

    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    vuap.clickOnImage();

    final String actual = ads.switchToNewBrowserTab();
    Assert.assertTrue(actual.equals(expected), "Image should point to page on fandom.");
  }

  private void playVideoForOneSecond(final AutoplayVuap vuap) {
    vuap.play();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException x) {
      // ignore this exception
    }
  }
}
