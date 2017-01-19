package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "AdsVuapAutoplayOasis")
public class TestAdsVuapOasisAutoplay extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  @Test(groups = "AdsVuapAutoplayAutoplayOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapAutoplayDesktop")
  public void vuapAutoplayShouldStartPlayingAdvertisementAutomatically(Page page, String videoIframeSelector) {
    final AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    final AutoplayVuap vuap = new AutoplayVuap(driver, videoIframeSelector);
    vuap.pause();

    Assert.assertTrue(vuap.hasStarted(), "VUAP did not automatically played when page was opened.");
    Assert.assertEquals(vuap.findTitle(), "Advertisement", "VUAP video title is not Advertisement.");
  }
}
