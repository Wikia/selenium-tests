package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

@Test(
        groups = "AdsVuapOasis"
)
public class TestAdsVuapOasis extends TemplateNoFirstLoad {

  private static final long MAX_AUTOPLAY_MOVIE_DURATION = 40L;

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "adsVuapDesktop",
          groups = "AdsVideoClosesWhenFinishPlaysOasis"
  )
  public void adsVuapCheckSlotSizesOasis(Page page, String slot, String videoIframeSelector) {
    AdsBaseObject ads  = openPageWithVideoInLocalStorage(page);
    final AutoplayVuap vuap = new AutoplayVuap(driver, slot, videoIframeSelector);
    scrollToSlot(slot, ads);

    double adSlotHeight = vuap.getAdSlotHeight();

    vuap.playVuapVideo();

    vuap.pause();

    double videoAdHeight = vuap.getVideoHeightWhilePaused();

    Assertion.assertTrue(vuap.isVideoAdBiggerThanImageAd(videoAdHeight, adSlotHeight), "Video ad has wrong size");

    vuap.play();

    vuap.waitForVideoToEnd(MAX_AUTOPLAY_MOVIE_DURATION);

    double adSlotHeightAfterVideoClose = vuap.getAdSlotHeight();

    Assertion.assertEquals(adSlotHeight, adSlotHeightAfterVideoClose);

  }

  private void scrollToSlot(String slotName, AdsBaseObject ads) {
    if (slotName == AdsContent.BOTTOM_LB) {
      ads.triggerComments(false);
    }
  }
}
