package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME_MOBILE, browserSize = "414x736")
public class TestAdsPremiumPrerollMercury extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsPremiumPrerollMercury"},
      dataProvider = "adsPremiumPreroll"
  )
  public void adsPremiumPrerollMercury(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyArticleAd();
    wikiPage.verifyMobileArticleVideo();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsPremiumPrerollMercury"},
      dataProvider = "adsPremiumPreroll"
  )
  public void adsPremiumPrerollMercuryNoAds(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article + "?noads=1");
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyMobileArticleVideo();
  }
}
