package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;



@Execute(onWikia = "project43")
public class TestAdsDfpParamsPresentMercury extends MobileTestTemplate {

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"MobileAds", "AdsDfpParamsPresentMercury"})
  public void testAdsMEGAValMorganAUMercury(
  ) {
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.UAP_PAGE.getUrl());
    ads.verifyValMorgan("AU", "AU", true);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"MobileAds", "AdsDfpParamsPresentMercury"})
  public void testAdsMEGAValMorganNZMercury(
  ) {
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.UAP_PAGE.getUrl());
    ads.verifyValMorgan("AU", "NZ", true);
  }
}
