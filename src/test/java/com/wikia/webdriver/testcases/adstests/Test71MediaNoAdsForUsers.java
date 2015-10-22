package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering Wikia
 */
public class Test71MediaNoAdsForUsers extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = GermanAdsDataProvider.class,
      dataProvider = "germanArticles",
      groups = {"Ads", "NoAds71Media_GeoEdgeFree", "NoAds71Media"}
  )
  @Execute(asUser = User.USER)
  public void NoAds71Media_GeoEdgeFree(String wikiName, String path) throws Exception {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsGermanObject ads71Media = new AdsGermanObject(driver, testedPage);
    ads71Media.verifyNo71MediaAds();
  }
}
