package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsJWPlayerObject;
import org.testng.annotations.Test;

public class TestAdsFeaturedVideoOasis extends TemplateNoFirstLoad {

  private static final String FV_JWPLAYER_WIKI = "project43";
  private static final String FV_JWPLAYER_PAGE_URI = "SyntheticTests/Premium/FeaturedVideo/JWPlayer";

  private static final Page PAGE_WITH_FV = new Page(FV_JWPLAYER_WIKI, FV_JWPLAYER_PAGE_URI);
  private static final String INSTANT_GLOBAL_MIDROLL = "wgAdDriverFVMidrollCountries";
  private static final String INSTANT_GLOBAL_POSTROLL = "wgAdDriverFVPostrollCountries";

  @Test(
      groups = {"AdsFeaturedVideoOasis"}
  )
  public void adsFeaturedVideoAdsOasis() {
    String testedPage = PAGE_WITH_FV.getUrl();
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_MIDROLL);
    testedPage = urlBuilder.globallyEnableGeoInstantGlobalOnPage(testedPage, INSTANT_GLOBAL_POSTROLL);

    AdsJWPlayerObject jwPlayerObject = new AdsJWPlayerObject(driver, testedPage);

    jwPlayerObject.verifyPlayerOnPage();
    jwPlayerObject.waitForAdStartsPlaying();
    jwPlayerObject.verifyPreroll();
    jwPlayerObject.verifyFeaturedVideo();
    jwPlayerObject.verifyMidroll();
    jwPlayerObject.verifyFeaturedVideo();
    jwPlayerObject.verifyPostroll();
  }
}
