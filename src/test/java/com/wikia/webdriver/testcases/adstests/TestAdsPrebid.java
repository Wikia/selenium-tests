package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPrebidObject;

import org.testng.annotations.Test;

public class TestAdsPrebid extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "prebidCustomAdapter",
      groups = "AdsPrebidOasis"
  )
  public void adsPrebidOasis(String wiki, String article) {
    String url = urlBuilder.getUrlForPath(wiki, article);
    url = urlBuilder.appendQueryStringToURL(url, "wikia_adapter=1881");

    AdsPrebidObject prebidAds = new AdsPrebidObject(driver, url);

    prebidAds.verifyKeyValues(AdsContent.TOP_LB, "wikia", "728x90", "18.50");
    prebidAds.verifyPrebidCreative(AdsContent.TOP_LB, true);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "prebidCustomAdapter",
      groups = "AdsPrebidMercury"
  )
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  public void adsPrebidMercury(String wiki, String article) {
    String url = urlBuilder.getUrlForPath(wiki, article);
    url = urlBuilder.appendQueryStringToURL(url, "wikia_adapter=831");

    AdsPrebidObject prebidAds = new AdsPrebidObject(driver, url);

    prebidAds.verifyKeyValues(AdsContent.MOBILE_TOP_LB, "wikia", "320x50", "8.30");
    prebidAds.verifyPrebidCreative(AdsContent.MOBILE_TOP_LB, true);
  }
}
