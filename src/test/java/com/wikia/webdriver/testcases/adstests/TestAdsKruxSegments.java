package com.wikia.webdriver.testcases.adstests;

import static com.wikia.webdriver.common.core.Assertion.assertEquals;
import static com.wikia.webdriver.common.core.Assertion.assertStringContains;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

/**
 * @ownership AdEngineering
 */
public class TestAdsKruxSegments extends TemplateNoFirstLoad {

  /**
   * Krux loads after onload event happens.
   *
   * Unfortunately ads tend to load very slowly on the first page view and it means onload event
   * happens really late. It breaks our tests as Krux doesn't load in the specified timeout.
   *
   * Unless we visit the pages without ads :-)
   */
  private static final String NO_ADS_URL_PARAM =
      "InstantGlobals.wgSitewideDisableGpt=1&InstantGlobals.wgSitewideDisableLiftium=1";

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "kruxSegments", groups = {
      "AdsKruxSegments", "Ads"})
  public void adsKruxSegments(String kruxKuid, String segment, Page page1,
      String expectedSegmentsOnPage1, Page page2, String expectedSegmentsOnPage2) {

    AdsKruxObject adsKruxObject = new AdsKruxObject(driver);

    // Set the kxkuid -- so for Krux we're always the same user:
    if (kruxKuid != null) {
      adsKruxObject.setKruxUserCookie(kruxKuid);
    }

    // Visit page1:
    adsKruxObject.getUrl(page1, NO_ADS_URL_PARAM);
    adsKruxObject.waitForPageLoaded();
    adsKruxObject.waitForKrux();

    // Most of the time we get the correct segment here, but let's check it on the next page view:
    adsKruxObject.getUrl(page1, NO_ADS_URL_PARAM);
    adsKruxObject.waitForPageLoaded();
    adsKruxObject.waitForKrux();

    // Check the segments:
    assertContainsSegs(adsKruxObject.getKxsegs(), expectedSegmentsOnPage1);

    // Refresh once again, this time with ads and check the GPT params:
    adsKruxObject.getUrl(page1);
    assertPageParams(adsKruxObject);

    // Visit page2:
    adsKruxObject.getUrl(page2, NO_ADS_URL_PARAM);
    adsKruxObject.waitForPageLoaded();
    adsKruxObject.waitForKrux();

    // Most of the time we get the correct segment here, but let's check it on the next page view:
    adsKruxObject.getUrl(page2, NO_ADS_URL_PARAM);
    adsKruxObject.waitForPageLoaded();
    adsKruxObject.waitForKrux();

    // Check the segments:
    assertContainsSegs(adsKruxObject.getKxsegs(), expectedSegmentsOnPage2);

    // Refresh once again, this time with ads and check the GPT params:
    adsKruxObject.getUrl(page2);
    assertPageParams(adsKruxObject);

    // Set the segment for the current user
    if (segment != null) {
      adsKruxObject.addSegmentToCurrentUser(segment);
    }
  }

  private void assertPageParams(AdsKruxObject adsKruxObject) {
    assertStringContains(adsKruxObject.getGptParams(AdsContent.TOP_LB, "data-gpt-page-params"),
        "\"ksgmnt\":" + adsKruxObject.getKxsegs());
  }

  private void assertContainsSegs(String actual, String expected) {

    if ("[]".equals(expected)) {
      assertEquals(actual, expected);
    }

    String[] expectedSegs = expected.replace("[", "").replace("]", "").split(",");
    for (String expectedSeg : expectedSegs) {
      assertStringContains(actual, expectedSeg);
    }

  }
}
