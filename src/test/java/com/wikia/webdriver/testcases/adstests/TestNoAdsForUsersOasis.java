package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestNoAdsForUsersOasis extends TemplateNoFirstLoad {
  private static final String WIKI_NAME = "project43";
  private static final String LONG_ARTICLE_PAGE_PATH = "SyntheticTests/LongPage";
  private static final String CATEGORY_PAGE_PATH = "Category:Browse";
  private static final String SPECIAL_PAGE_PATH = "Special:Videos";
  private static final String FILE_PAGE_PATH =
      "File:Cloudy_With_A_Chance_Of_Meatballs_(Dutch_Trailer_1_Subtitled)";
  private static final String CUSTOM_NAMESPACE_PAGE_PATH = "Koperek:SyntheticTests/NoAds";

  private AdsBaseObject buildAdsObject(String pagePath) {
    String testedPage = urlBuilder.getUrlForPath(WIKI_NAME, pagePath);
    return new AdsBaseObject(driver, testedPage);
  }

  @Test(groups = "NoAdsForUsersOasis")
  @Execute(asUser = User.USER)
  public void noAdsForUsersOnArticlePageOasis() {
    AdsBaseObject ads = buildAdsObject(LONG_ARTICLE_PAGE_PATH);
    ads.verifyNoAdsOnPage();
  }

  @Test(groups = "NoAdsForUsersOasis")
  @Execute(asUser = User.USER)
  public void noAdsForUsersOnCategoryPageOasis() {
    AdsBaseObject ads = buildAdsObject(CATEGORY_PAGE_PATH);
    ads.setPageType("category");
    ads.verifyNoAdsOnPage();
  }

  @Test(groups = "NoAdsForUsersOasis")
  @Execute(asUser = User.USER)
  public void noAdsForUsersOnSpecialPageOasis() {
    AdsBaseObject ads = buildAdsObject(SPECIAL_PAGE_PATH);
    ads.verifyNoAdsOnPage();
  }

  @Test(groups = "NoAdsForUsersOasis")
  @Execute(asUser = User.USER)
  public void noAdsForUsersOnFilePageOasis() {
    AdsBaseObject ads = buildAdsObject(FILE_PAGE_PATH);
    ads.verifyNoAdsOnPage();
  }

  @Test(groups = "NoAdsForUsersOasis")
  @Execute(asUser = User.USER)
  public void noAdsForUsersOnCustomPageOasis() {
    AdsBaseObject ads = buildAdsObject(CUSTOM_NAMESPACE_PAGE_PATH);
    ads.verifyNoAdsOnPage();
  }
}
