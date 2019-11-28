package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.annotations.UnsafePageLoad;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;

import org.testng.annotations.Test;

public class TestAdsFeaturedVideo extends TemplateNoFirstLoad {

  private static final String INSTANT_GLOBAL_MIDROLL = "wgAdDriverFVMidrollCountries";
  private static final String INSTANT_GLOBAL_PUBMATIC = "wgAdDriverPubMaticBidderCountries";
  private static final String INSTANT_GLOBAL_POSTROLL = "wgAdDriverFVPostrollCountries";

  @Test(groups = {"AdsFeaturedVideoOasis"})
  public void adsFeaturedVideoAdsDesktop() {
    String testedPage = AdsDataProvider.PAGE_FV_JWPLAYER.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage,
                                                                 INSTANT_GLOBAL_MIDROLL
    );
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage,
                                                                 INSTANT_GLOBAL_POSTROLL
    );
    testedPage = urlBuilder.globallyDisableGeoInstantGlobalOnPage(testedPage,
                                                                  INSTANT_GLOBAL_PUBMATIC
    );

    new AdsBaseObject(testedPage);

    verifyAdPositions();
  }

  @Test(groups = {"AdsFeaturedVideoOasis"})
  public void adsFeaturedVideoNoAdsDesktop() {
    String testedPage = urlBuilder.appendQueryStringToURL(AdsDataProvider.PAGE_FV_JWPLAYER.getUrl(),
                                                          "noads=1"
    );

    new AdsBaseObject(testedPage);

    verifyNoAds();
  }


  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @UnsafePageLoad
  @Test(groups = {"AdsFeaturedVideoMercury"})
  public void adsFeaturedVideoAdsMobile() {
    adsFeaturedVideoAdsDesktop();
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @UnsafePageLoad
  @Test(groups = {"AdsFeaturedVideoMercury"})
  public void adsFeaturedVideoNoAdsMobile() {
    adsFeaturedVideoNoAdsDesktop();
  }

  private void verifyAdPositions() {
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject();

    jwPlayerObject.verifyAllAdPositions();
  }

  private void verifyNoAds() {
    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject();

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.verifyFeaturedVideo();
  }
}
