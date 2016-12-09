package com.wikia.webdriver.testcases.adstests;

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
public class TestVUAP extends TemplateNoFirstLoad {
  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPDesktop",
          groups = "AdsVuapFlow"
  )
  public void testVideoPlayFlow(String slotName, Page page) {
    new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, slotName);
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVUAPDesktop",
          groups = "AdsVuapSizes"
  )
  public void testSlotSizes(String slotName, Page page) {
    final int imageWidth = 1830;
    final int imageHeight = 744;

    final int videoWidth = 1830;
    final int videoHeight = 1034;

    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, slotName);

    ads.verifySlotSize(slotName, imageWidth, imageHeight);
    vuap.play();
    vuap.waitForVideoStart();
    ads.verifySlotSize(slotName, videoWidth, videoHeight);
    vuap.waitForVideoEnd();
    ads.verifySlotSize(slotName, imageWidth, imageHeight);
  }
}
