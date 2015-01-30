package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestKruxSegment extends NewTestTemplate {

  private static final String KRUX_SEGMENT_ID = "l7lznzoty";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "mainWikiPages",
      groups = {"KruxSegmentDesktop_GeoEdgeFree", "KruxSegmentMobile_GeoEdgeFree", "Ads"}
  )
  public void TestKruxSegment_GeoEdgeFree(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsKruxObject ads = new AdsKruxObject(driver, testedPage);
    ads.refreshPage();
    ads.refreshPage();
    ads.refreshPage();
    ads.verifyKruxSegment(KRUX_SEGMENT_ID);
  }
}
