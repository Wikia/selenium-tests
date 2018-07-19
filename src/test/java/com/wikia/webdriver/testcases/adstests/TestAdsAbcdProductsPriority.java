package com.wikia.webdriver.testcases.adstests;

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

  @Test(groups = {"AdsOutstreamVideoAdIsDisplayedIfAbcdAdIsTargetedOnThisSameArticleOasis",
                  "AbcdProductPriorityOasis"})
  public void adsOutstreamVideoAdIsDisplayedIfAbcdAdIsTargetedOnThisSameArticleOasis() {
    AdsBaseObject ads = new AdsBaseObject(urlBuilder.appendQueryStringToURL(
        TEST_PAGE.getUrl(),
        DEBUG_QUERY_STRING
    ));

    ads.scrollToSlot(AdsContent.TOP_LB);
    verifyAbcdAdIsNotDisplayed(ads);

    ads.scrollToSlot(AdsContent.INCONTENT_PLAYER);
    verifyOutstreamAdIsDisplayed(ads);
  }

  @Test(groups = {"AdsAbcdAdIsDisplayedIfBidersAreDisabledOasis", "AbcdProductPriorityOasis"})
  public void adsAbcdAdIsDisplayedIfBidersAreDisabledOasis() {
    AdsBaseObject ads = new AdsBaseObject(TEST_PAGE.getUrl());
    verifyAbcdAdIsDisplayed(ads);
  }

  @Test(groups = {"AdsUAPFamilyAdIsNotDisplayedOnFeaturedVideoPage", "AbcdProductPriorityOasis"})
  public void adsUAPFamilyAdIsNotDisplayedOnFeaturedVideoPage() {
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.PAGE_FV.getUrl());
    verifyAbcdAdIsNotDisplayed(ads);

    Assert.assertTrue(ads.slotHasSize(AdsContent.TOP_LB, 728, 90));
    verifyNoUAPSizesInSlot(ads, AdsContent.TOP_LB);
  }

  private void verifyNoUAPSizesInSlot(AdsBaseObject ads, String slot) {
    Assert.assertFalse(ads.slotHasSize(slot, 2, 2),
                       String.format("Slot %s has UAP supported 2x2 size", slot)
    );
    Assert.assertFalse(ads.slotHasSize(slot, 3, 3),
                       String.format("Slot %s has UAP supported 3x3 size", slot)
    );
  }

  private void verifyOutstreamAdIsDisplayed(AdsBaseObject ads) {
    ads.verifyLineItemId(AdsContent.INCONTENT_PLAYER, OUTSTREAM_LINE_ITEM_ID);
    ads.verifySlotAttribute(AdsContent.INCONTENT_PLAYER, "data-gpt-slot-params", "wikiaVideo");
    ads.verifySlotAttribute(AdsContent.INCONTENT_PLAYER, "data-slot-result", "success");
  }

  private void verifyAbcdAdIsDisplayed(AdsBaseObject ads) {
    ads.verifyLineItemId(AdsContent.TOP_LB, ABCD_LINE_ITEM_ID);
    ads.verifySlotAttribute(AdsContent.TOP_LB, "data-slot-result", "success");
  }

  private void verifyAbcdAdIsNotDisplayed(AdsBaseObject ads) {
    Assertion.assertNotEquals(String.valueOf(ads.getLineItemId(AdsContent.TOP_LB)),
                              ABCD_LINE_ITEM_ID,
                              String.format("ABCD %s line item id is displayed", ABCD_LINE_ITEM_ID)
    );
  }
}
