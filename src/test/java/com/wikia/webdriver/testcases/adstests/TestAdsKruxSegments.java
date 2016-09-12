package com.wikia.webdriver.testcases.adstests;

import static com.wikia.webdriver.common.core.Assertion.assertStringContains;
import static com.wikia.webdriver.common.core.Assertion.assertStringNotContains;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

import org.testng.annotations.Test;

import java.util.Map;

public class TestAdsKruxSegments extends NewTestTemplate {

  /**
   * Krux loads after onload event happens.
   *
   * Unfortunately ads tend to load very slowly on the first page view and it means onload event
   * happens really late. It breaks our tests as Krux doesn't load in the specified timeout.
   *
   * Unless we visit the pages without ads :-)
   */
  private static final String NO_ADS_URL_PARAM = "InstantGlobals.wgSitewideDisableGpt=1";

  @Execute(mockAds = "true")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxSegments",
      groups = "AdsKruxSegments"
  )
  public void adsKruxSegments(String kruxKuid,
                              String segment,
                              Page page1,
                              Map<String, Boolean> segmentsOnPage1,
                              Page page2,
                              Map<String, Boolean> segmentsOnPage2) {

    AdsKruxObject adsKruxObject = new AdsKruxObject(driver);

    // Set the kxkuid -- so for Krux we're always the same user:
    if (kruxKuid != null) {
      adsKruxObject.setKruxUserCookie(kruxKuid);
    }

    // Visit page1:
    adsKruxObject.getUrl(page1, NO_ADS_URL_PARAM);
    adsKruxObject.waitForPageLoaded(true);
    adsKruxObject.waitForKrux();

    // Most of the time we get the correct segment here, but let's check it on the next page view:
    adsKruxObject.getUrl(page1, NO_ADS_URL_PARAM);
    adsKruxObject.waitForPageLoaded(true);
    adsKruxObject.waitForKrux();

    // Check the segments:
    assertSegments(adsKruxObject.getKxsegs(), segmentsOnPage1);

    // Refresh once again, this time with ads and check the GPT params:
    adsKruxObject.getUrl(page1);
    assertPageParams(adsKruxObject);

    // Visit page2:
    adsKruxObject.getUrl(page2, NO_ADS_URL_PARAM);
    adsKruxObject.waitForPageLoaded(true);
    adsKruxObject.waitForKrux();

    // Most of the time we get the correct segment here, but let's check it on the next page view:
    adsKruxObject.getUrl(page2, NO_ADS_URL_PARAM);
    adsKruxObject.waitForPageLoaded(true);
    adsKruxObject.waitForKrux();

    // Check the segments:
    assertSegments(adsKruxObject.getKxsegs(), segmentsOnPage2);

    // Refresh once again, this time with ads and check the GPT params:
    adsKruxObject.getUrl(page2);
    assertPageParams(adsKruxObject);

    // Set the segment for the current user
    if (segment != null) {
      adsKruxObject.addSegmentToCurrentUser(segment);
    }
  }

  private void assertPageParams(AdsKruxObject adsKruxObject) {
    assertStringContains(
        adsKruxObject.getGptParams(AdsContent.TOP_LB, "data-gpt-page-params"),
        "\"ksgmnt\":" + adsKruxObject.getKxsegs());
  }

  private void assertSegments(String actualSegments, Map<String, Boolean> expected) {
    for (Map.Entry<String, Boolean> entry : expected.entrySet()) {
      String segment = entry.getKey();
      Boolean isPresent = entry.getValue();

      if (isPresent) {
        assertStringContains(actualSegments, segment);
      } else {
        assertStringNotContains(actualSegments, segment);
      }
    }
  }
}
