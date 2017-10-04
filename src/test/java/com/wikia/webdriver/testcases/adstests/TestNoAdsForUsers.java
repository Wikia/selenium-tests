package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestNoAdsForUsers extends TemplateNoFirstLoad {
  private static final String LONG_ARTICLE_PAGE = "SyntheticTests/LongPage";
  private static final String CATEGORY_PAGE = "Category:Browse";
  private static final String SPECIAL_PAGE = "Special:Videos";
  private static final String FILE_PAGE =
      "File:Cloudy_With_A_Chance_Of_Meatballs_(Dutch_Trailer_1_Subtitled)";
  private static final String CUSTOM_NAMESPACE_PAGE = "Koperek:SyntheticTests/NoAds";

  @Test(groups = "NoAdsForUsers")
  @Execute(asUser = User.USER)
  public void noAdsForUsers(String wikiName, String path) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyNoAdsOnPage();
  }
}
