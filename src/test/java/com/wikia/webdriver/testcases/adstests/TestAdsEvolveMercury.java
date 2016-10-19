package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;

import org.testng.annotations.Test;

@Test(groups = "AdsEvolveMercury")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TestAdsEvolveMercury extends TemplateNoFirstLoad {

  @Test(
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "evolveTestPage"
  )
  public void adsEvolveMercury(String wikiName, String article) {
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver);
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    wikiPage.enableEvolve(testedPage);
    wikiPage.verifyEvolveCallMercury();
  }
}
