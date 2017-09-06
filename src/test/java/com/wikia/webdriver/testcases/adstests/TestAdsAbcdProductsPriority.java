package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestAdsAbcdProductsPriority extends TemplateNoFirstLoad {

  private static final String WIKIA = "project43";
  private static final String URL_ARTICLE = "/SyntheticTests/UAP/ABCD/ProductsPriority";
  private static final String URL_ARTICLE_WITH_DEBUG_MODE = URL_ARTICLE + "?appnexusast_debug_mode";


  @Test(
      groups = "AbcdProductPriorityOasis"
  )
  public void adsAppNexusVideoAdIsDisplayedIfAbcdAdIsTargetedOnThisSameArticleOasis() {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(WIKIA, URL_ARTICLE_WITH_DEBUG_MODE));
    verifyAppNexussAdIsDisplayed(ads);
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
    ads.scrollToSlot(AdsContent.INCONTENT_PLAYER);

    Assertion.assertTrue(ads.getGptParams(AdsContent.INCONTENT_PLAYER, "data-gpt-slot-params").contains("appnexusAst"),
        "AppNexus ad param: appnexusAst is not present");
    Assertion.assertTrue(ads.getGptParams(AdsContent.INCONTENT_PLAYER, "data-gpt-line-item-id").equals("4406759476"),
        "AppNexus ad line item id: 4406759476 is not present");
    Assertion.assertTrue(ads.getGptParams(AdsContent.INCONTENT_PLAYER, "data-slot-result").equals("success"),
        "AppNexus slot result: success is not present");
  }

  private void verifyAbcdAdIsDisplayed(AdsBaseObject ads) {
    ads.scrollToSlot(AdsContent.TOP_LB);
    Assertion.assertTrue(ads.getGptParams(AdsContent.TOP_LB, "data-gpt-line-item-id").equals("4376117186"),
        "TOP_LEADERBOARD slot has ABCD ad line item id 4376117186, ABCD slot should not be displayed");
    Assertion.assertTrue(ads.getGptParams(AdsContent.TOP_LB, "data-slot-result").equals("success"),
        "ABCD slot result: success is not present");
  }

  private void verifyAbcdAdIsNotDisplayed(AdsBaseObject ads) {
    ads.scrollToSlot(AdsContent.TOP_LB);
    Assertion.assertFalse(ads.getGptParams(AdsContent.TOP_LB, "data-gpt-line-item-id").equals("4376117186"),
    "TOP_LEADERBOARD slot has ABCD ad line item id 4376117186, ABCD slot should not be displayed");
  }
}
