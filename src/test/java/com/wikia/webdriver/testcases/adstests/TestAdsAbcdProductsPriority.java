package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestAdsAbcdProductsPriority extends TemplateNoFirstLoad {

  private final String WIKIA = "project43";
  private final String URL_ARTICLE = "/SyntheticTests/UAP/ABCD/ProductsPriority";
  private final String URL_ARTICLE_WITH_DEBUG_MODE = URL_ARTICLE + "?appnexusast_debug_mode";


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

    Assertion.assertTrue(isExpectedSlotParamPresent(ads, AdsContent.INCONTENT_PLAYER, "appnexusAst"),
        "AppNexus ad param: appnexusAst is not present");
    Assertion.assertTrue(isExpectedLineItemIdPresent(ads, AdsContent.INCONTENT_PLAYER, "4406759476"),
        "AppNexus ad line item id: 4406759476 is not present");
    Assertion.assertTrue(isExpectedSlotRessultPresent(ads,AdsContent.INCONTENT_PLAYER, "success"),
        "AppNexus slot result: success is not present");
  }

  private void verifyAbcdAdIsDisplayed(AdsBaseObject ads) {
    ads.scrollToSlot(AdsContent.TOP_LB);
    Assertion.assertTrue(isExpectedLineItemIdPresent(ads, AdsContent.TOP_LB, "4376117186"),
        "TOP_LEADERBOARD slot has ABCD ad line item id 4376117186, ABCD slot should not be displayed");
    Assertion.assertTrue(isExpectedSlotRessultPresent(ads,AdsContent.TOP_LB, "success"),
        "ABCD slot result: success is not present");
  }

  private void verifyAbcdAdIsNotDisplayed(AdsBaseObject ads) {
    ads.scrollToSlot(AdsContent.TOP_LB);
    Assertion.assertFalse(isExpectedLineItemIdPresent(ads, AdsContent.TOP_LB, "4376117186"),
    "TOP_LEADERBOARD slot has ABCD ad line item id 4376117186, ABCD slot should not be displayed");
  }

  private boolean isExpectedSlotParamPresent(AdsBaseObject ads, String slotName, String param) {
    return ads.getGptParams(slotName, "data-gpt-slot-params").contains(param);
  }

  private boolean isExpectedLineItemIdPresent(AdsBaseObject ads, String slotName, String lineItemId) {
    return ads.getGptParams(slotName, "data-gpt-line-item-id").equals(lineItemId);
  }

  private boolean isExpectedSlotRessultPresent(AdsBaseObject ads, String slotName, String slotResult) {
    return ads.getGptParams(slotName, "data-slot-result").equals(slotResult);
  }
}

