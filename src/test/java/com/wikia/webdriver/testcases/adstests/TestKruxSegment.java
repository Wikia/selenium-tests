package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestKruxSegment extends NewTestTemplate {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxRealTimeSegment",
      groups = {"KruxSegmentDesktop_GeoEdgeFree", "KruxSegmentMobile_GeoEdgeFree", "Ads"}
  )
  public void testRealTimeSegment(List<String> pages, String segmentId) {
    AdsKruxObject adsKruxObject = new AdsKruxObject(driver);
    for (String page : pages) {
      adsKruxObject.getUrl(page);
      adsKruxObject.waitForKrux();
    }
    Assertion.assertStringContains(segmentId, adsKruxObject.getKruxSegments());
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxStandartSegment",
      groups = {"KruxSegmentDesktop_GeoEdgeFree", "KruxSegmentMobile_GeoEdgeFree", "Ads"}
  )
  public void testStandardSegment(List<String> pages, String segment, boolean isPresent,
                                  String cookie) {
    AdsKruxObject adsKruxObject = new AdsKruxObject(driver);
    adsKruxObject.setKruxUserCookie(cookie);
    for (String page : pages) {
      adsKruxObject.getUrl(page);
      adsKruxObject.waitForKrux();
      PageObjectLogging.log("DEBUG kxsegs", adsKruxObject.getKxsegs(), true);
      PageObjectLogging.log("DEBUG kxkuid", adsKruxObject.getKxkuid(), true);
    }
    String segments = adsKruxObject.getKruxSegments();
    Assertion.assertEquals(segments.contains(segment), isPresent);
  }
}
