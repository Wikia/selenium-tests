package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

@Test(groups = "AdsNoAdsOnAdsFreeWikisOasis")
public class TestAdsNoAdsOnFreeWikisOasis extends TemplateNoFirstLoad {

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "adFreeWikis", groups = "AdsNoAdsOnAdsFreeWikisOasis")
  public void testNoAdsOasis(String wikiName, String path) {
    String testedPage = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(path);
    AdsBaseObject wikiPage = new AdsBaseObject(testedPage);
    wikiPage.verifyNoAdsOnPage(false);
  }
}
