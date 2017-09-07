package com.wikia.webdriver.testcases.adstests;

import com.sun.tools.javac.code.DeferredLintHandler;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestAdsAbcdProductsPriority extends TemplateNoFirstLoad {

  private static final String WIKIA = "project43";
  private static final String URL_ARTICLE = "/SyntheticTests/ProductsPriority/OutstreamOverABCD";
  private static final String URL_ARTICLE_WITH_DEBUG_MODE = URL_ARTICLE + "?appnexusast_debug_mode";

  private static final String ABCD_LINE_ITEM_ID = "4376117186";
  private static final String APPNEXUS_LINE_ITEM_ID = "4406759476";


  @Test(
      groups = "AbcdProductPriorityOasis"
  )
  public void adsAppNexusVideoAdIsDisplayedIfAbcdAdIsTargetedOnThisSameArticleOasis() {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(WIKIA, URL_ARTICLE_WITH_DEBUG_MODE));
    ads.scrollToSlot(AdsContent.INCONTENT_PLAYER);
    verifyAppNexussAdIsDisplayed(ads);

    ads.scrollToSlot(AdsContent.TOP_LB);
    verifyAbcdAdIsNotDisplayed(ads);
  }

  @Test(
      groups = "AbcdProductPriorityOasis"
  )
  public void adsAbcdAdIsDisplayedIfBidersAreDisabledOasis() {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(WIKIA, URL_ARTICLE));
    verifyAbcdAdIsDisplayed(ads);
  }

  private void verifyAppNexussAdIsDisplayed(AdsBaseObject ads){
    ads.verifyLineItemId(AdsContent.INCONTENT_PLAYER, APPNEXUS_LINE_ITEM_ID );
    ads.verifyGptSlotParam(AdsContent.INCONTENT_PLAYER, "appnexusAst");
    ads.verifySLotResult(AdsContent.INCONTENT_PLAYER, "success");
  }

  private void verifyAbcdAdIsDisplayed(AdsBaseObject ads) {
    ads.verifyLineItemId(AdsContent.TOP_LB, ABCD_LINE_ITEM_ID);
    ads.verifySLotResult(AdsContent.INCONTENT_PLAYER, "success");
  }

  private void verifyAbcdAdIsNotDisplayed(AdsBaseObject ads) {
    Assertion.assertEquals(ads.verifyLineItemId(AdsContent.TOP_LB, ABCD_LINE_ITEM_ID), false,
        "ABCD" + ABCD_LINE_ITEM_ID + " line item id is displayed");
  }
}
