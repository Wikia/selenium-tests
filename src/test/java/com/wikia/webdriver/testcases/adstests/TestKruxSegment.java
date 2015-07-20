package com.wikia.webdriver.testcases.adstests;

import static com.wikia.webdriver.common.core.Assertion.assertFalse;
import static com.wikia.webdriver.common.core.Assertion.assertStringContains;
import static com.wikia.webdriver.common.core.Assertion.assertTrue;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

import org.testng.annotations.Test;


/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestKruxSegment extends TemplateDontLogout {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxRealTimeSegment",
      groups = {"KruxRealtimeSegment", "Ads"}
  )
  public void testRealTimeSegment(String wikiName1,
                                  String article1,
                                  String wikiName2,
                                  String article2,
                                  String segmentId) {
    AdsKruxObject adsKruxObject = new AdsKruxObject(driver);
    adsKruxObject.initKruxUser(wikiName1);
    adsKruxObject.getUrl(urlBuilder.getUrlForPath(wikiName1, article1));
    assertTrue(adsKruxObject.getKxsegs().contains(segmentId));
    adsKruxObject.getUrl(urlBuilder.getUrlForPath(wikiName2, article2));
    assertFalse(adsKruxObject.getKxsegs().contains(segmentId));
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxStandardSegment",
      groups = {"KruxStandardSegment", "Ads"}
  )
  public void testStandardSegment(String wikiName1,
                                  String article1,
                                  String wikiName2,
                                  String article2,
                                  String segmentId) {
    AdsKruxObject adsKruxObject = new AdsKruxObject(driver);
    adsKruxObject.initKruxUser(wikiName1);
    adsKruxObject.addSegmentToCurrentUser(segmentId, wikiName1);
    adsKruxObject.getUrl(urlBuilder.getUrlForPath(wikiName1, article1));
    assertPageParams(adsKruxObject);
    assertTrue(adsKruxObject.getKxsegs().contains(segmentId));
    adsKruxObject.initKruxUser(wikiName2);
    adsKruxObject.getUrl(urlBuilder.getUrlForPath(wikiName2, article2));
    assertPageParams(adsKruxObject);
    assertTrue(adsKruxObject.getKxsegs().contains(segmentId));
  }

  private void assertPageParams(AdsKruxObject adsKruxObject) {
    assertStringContains(
        adsKruxObject.getGptParams(AdsContent.TOP_LB, "data-gpt-page-params"),
        adsKruxObject.getKxsegs());
  }
}
