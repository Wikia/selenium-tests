package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

import java.io.IOException;

public class TestAdsFandom extends AdsFandomTestTemplate {


  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fandomAdsPage",
      groups = "AdsFandomPresenceOasis"
  )
  public void adsFandomAds(String article) throws IOException {
    String testedPage = urlBuilder.getUrlForFandomPage(article);

    AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    wikiPage.verifyFandomTopLeaderboard();

  }
}
