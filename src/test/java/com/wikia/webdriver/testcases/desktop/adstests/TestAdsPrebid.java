package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.*;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.util.List;

@Execute(onWikia = "project43")
public class TestAdsPrebid extends NewTestTemplate {

  private static final String DIRECT_PREROLL_LINE_ITEM_ID = "314345172";
  private static final String BIDDER_PREROLL_LINE_ITEM_ID = "4618393909";

  @NetworkTrafficDump(useMITM = true)
  @UnsafePageLoad
  @Test(groups = {"AdsPrebidOasis", "AdsPrebidFV"})
  public void fvDirectVideoAd() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_FV.getUrl());

    Assertion.assertEquals(getFVStatus(ads), "success");
    Assertion.assertEquals(ads.getFVLineItem(), DIRECT_PREROLL_LINE_ITEM_ID);
  }

  private String getFVStatus(AdsBaseObject ads) {
    return ads.getValueFromTracking(networkTrafficInterceptor, "FEATURED", "ad_status");
  }

  @NetworkTrafficDump(useMITM = true)
  @UnsafePageLoad
  @Test(groups = {"AdsPrebidOasis", "AdsPrebidFV"})
  public void fvBidderVideoAd() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_FV_RUBICON.getUrl());

    Assertion.assertEquals(getFVStatus(ads), "success");
    Assertion.assertEquals(ads.getFVLineItem(), BIDDER_PREROLL_LINE_ITEM_ID);
  }

  @NetworkTrafficDump(useMITM = true)
  @UnsafePageLoad
  @Test(groups = {"AdsPrebidOasis", "AdsPrebidFV"})
  public void fvBidderVideoAdNotDisplayed() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_FV_RUBICON_NO_VIDEO.getUrl());

    Assertion.assertEquals(getFVStatus(ads), "collapse");
  }
}
