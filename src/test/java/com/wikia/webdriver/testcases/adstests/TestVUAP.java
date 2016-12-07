package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VUAP;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestVUAP extends TemplateNoFirstLoad {
  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPDesktop",
          groups = "AdsVuapDesktop"
  )
  public void testVideoPlayFlow(String slotName, Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, slotName);
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPDesktop",
          groups = "AdsVuapDesktop"
  )
  public void testSlotSizes(String slotName, Page page) {
    final int IMAGE_WIDTH = 1830;
    final int IMAGE_HEIGHT = 744;

    final int VIDEO_WIDTH = 1830;
    final int VIDEO_HEIGHT = 1034;

    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, slotName);

    ads.verifySlotSize(slotName, IMAGE_WIDTH, IMAGE_HEIGHT);
    vuap.play();
    vuap.waitForStartOfVideo();
    ads.verifySlotSize(slotName, VIDEO_WIDTH, VIDEO_HEIGHT);
    vuap.waitForEndOfVideo();
    ads.verifySlotSize(slotName, IMAGE_WIDTH, IMAGE_HEIGHT);
  }
}
