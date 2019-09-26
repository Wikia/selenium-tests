package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAdsAbcdProductsPriority extends TemplateNoFirstLoad {

  private static final String DEBUG_QUERY_STRING = "wikia_video_adapter=2000";
  private static final String WIKIA = "project43";
  private static final Page TEST_PAGE = new Page(WIKIA,
                                                 "SyntheticTests/ProductsPriority/OutstreamOverABCD"
  );

  private static final String ABCD_LINE_ITEM_ID = "4417483196";
  private static final String OUTSTREAM_LINE_ITEM_ID = "4417473960";

  @Test(groups = {"AdsAbcdAdIsDisplayedIfBidersAreDisabledOasis", "AbcdProductPriorityOasis"})
  public void adsAbcdAdIsDisplayedIfBidersAreDisabledOasis() {
    AdsBaseObject ads = new AdsBaseObject(TEST_PAGE.getUrl());
    verifyAbcdAdIsDisplayed(ads);
  }

  @Test(groups = {"AdsUAPFamilyAdIsNotDisplayedOnFeaturedVideoPage", "AbcdProductPriorityOasis"})
  public void adsUAPFamilyAdIsNotDisplayedOnFeaturedVideoPage() {
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_FV.getUrl());
    verifyAbcdHIVIAdIsNotDisplayed(ads);
  }


  private void verifyAbcdAdIsDisplayed(AdsBaseObject ads) {
    ads.verifyLineItemId(AdsContent.HIVI_TOP_LB, ABCD_LINE_ITEM_ID);
    ads.verifySlotAttribute(AdsContent.HIVI_TOP_LB, "data-slot-result", "success");
  }

  private void verifyAbcdHIVIAdIsNotDisplayed(AdsBaseObject ads) {
    Assertion.assertNotEquals(String.valueOf(ads.getLineItemId(AdsContent.TOP_LB)),
                              ABCD_LINE_ITEM_ID,
                              String.format("ABCD %s line item id is displayed", ABCD_LINE_ITEM_ID)
    );
  }
}
