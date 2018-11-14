package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsVelesObject;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME_MOBILE, browserSize = "414x736")
public class TestVideoVelesMobile extends TemplateNoFirstLoad {

  private static final String WIKIA = "project43";
  private static final String WITH_BIDDER = "wikia_video_adapter=2000";

  private static final String ASSERTION_VELES_PLAYER_NOT_RENDERED = "Veles player not rendered";

  private static final Page TEST_PAGE = new Page(WIKIA,
                                                        "SyntheticTests/Video/Porvata3"
  );
  private static final Page TEST_PAGE_NO_OFFER = new Page(WIKIA,
                                                        "SyntheticTests/Video/Porvata3/NoOffer"
  );

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesMobile", "AdsVelesWithDirectOfferEventMobile"})
  public void adsVelesWithDirectOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE.getUrl());

    Assertion.assertTrue(velesAds.isVelesPlayerInIncontentSlotDisplayed(true),
                         ASSERTION_VELES_PLAYER_NOT_RENDERED
    );
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor,
                                                    AdsVelesObject.MOBILE_PLAYER_EVENT_PATTERN_WITH_OFFER
    );
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesMobile", "AdsVelesWithBidderOfferEventMobile"})
  public void adsVelesWithBidderOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    String url = TEST_PAGE.getUrl();
    AdsVelesObject velesAds = new AdsVelesObject(driver,
                                                 urlBuilder.appendQueryStringToURL(url,
                                                                                   WITH_BIDDER
                                                 )
    );

    Assertion.assertTrue(velesAds.isVelesPlayerInIncontentSlotDisplayed(true),
                         ASSERTION_VELES_PLAYER_NOT_RENDERED
    );
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor,
                                                    AdsVelesObject.MOBILE_PLAYER_EVENT_PATTERN_WITH_OFFER
    );
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {"AdsVideoVelesMobile", "AdsVelesWithoutOfferEventMobile"})
  public void adsVelesWithoutOfferEvent() {
    networkTrafficInterceptor.startIntercepting();
    AdsVelesObject velesAds = new AdsVelesObject(driver, TEST_PAGE_NO_OFFER.getUrl());

    velesAds.triggerMobileIncontentPlayer();
    velesAds.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor,
                                                    AdsVelesObject.MOBILE_PLAYER_EVENT_PATTERN_WITHOUT_OFFER
    );
    Assertion.assertTrue(velesAds.isVideoHidden(), "Video player not hidden");
  }

}
