package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestAdsAbcdProductsPriority extends TemplateNoFirstLoad {

  private static final String DEBUG_QUERY_STRING = "appnexusast_debug_mode=1&InstantGlobals.wgAdDriverAppNexusAstBidderCountries=[XX]";
  private static final String WIKIA = "project43";
  private static final String URL_ARTICLE = "/SyntheticTests/ProductsPriority/OutstreamOverABCD";

  private static final String ABCD_LINE_ITEM_ID = "4417483196";
  private static final String APPNEXUS_LINE_ITEM_ID = "4417473960";


  @Test(
      groups = {"AdsAppNexusVideoAdIsDisplayedIfAbcdAdIsTargetedOnThisSameArticleOasis", "AbcdProductPriorityOasis"}
  )
  public void adsAppNexusVideoAdIsDisplayedIfAbcdAdIsTargetedOnThisSameArticleOasis() {
    String articleUrl = urlBuilder.getUrlForPage(WIKIA, URL_ARTICLE);
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.appendQueryStringToURL(articleUrl, DEBUG_QUERY_STRING));

    ads.scrollToSlot(AdsContent.TOP_LB);
    verifyAbcdAdIsNotDisplayed(ads);

    ads.scrollToSlot(AdsContent.INCONTENT_PLAYER);
    verifyAppNexussAdIsDisplayed(ads);
  }

  @Test(
      groups = {"AdsAbcdAdIsDisplayedIfBidersAreDisabledOasis", "AbcdProductPriorityOasis"}
  )
  public void adsAbcdAdIsDisplayedIfBidersAreDisabledOasis() {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(WIKIA, URL_ARTICLE));
    verifyAbcdAdIsDisplayed(ads);
  }

  private void verifyAppNexussAdIsDisplayed(AdsBaseObject ads){
    ads.verifyLineItemId(AdsContent.INCONTENT_PLAYER, APPNEXUS_LINE_ITEM_ID);
    ads.verifySlotAttribute(AdsContent.INCONTENT_PLAYER, "data-gpt-slot-params", "appnexusAst");
    ads.verifySlotAttribute(AdsContent.INCONTENT_PLAYER, "data-slot-result", "success");
  }

  private void verifyAbcdAdIsDisplayed(AdsBaseObject ads) {
    ads.verifyLineItemId(AdsContent.TOP_LB, ABCD_LINE_ITEM_ID);
    ads.verifySlotAttribute(AdsContent.TOP_LB, "data-slot-result", "success");
  }

  private void verifyAbcdAdIsNotDisplayed(AdsBaseObject ads) {
    Assertion.assertNotEquals(String.valueOf(ads.getLineItemId(AdsContent.TOP_LB)), ABCD_LINE_ITEM_ID,
        "ABCD " + ABCD_LINE_ITEM_ID + " line item id is displayed");
  }
}
