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

  @Test(groups = "NoAdsForUsersOasis")
  @Execute(asUser = User.USER)
  public void noAdsForUsersOnArticlePageOasis() {
    String testedPage = urlBuilder.getUrlForPath(WIKI_NAME, LONG_ARTICLE_PAGE_PATH);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }
}
