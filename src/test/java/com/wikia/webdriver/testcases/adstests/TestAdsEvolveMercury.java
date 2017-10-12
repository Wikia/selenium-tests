package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;

import org.testng.annotations.Test;

@Test(groups = "AdsEvolveMercury")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TestAdsEvolveMercury extends TemplateNoFirstLoad {

  private static final Page EVOLVE_PAGE = new Page("project43", "SyntheticTests/Evolve");

  @Test
  public void adsEvolveMercury() {
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver);
    String testedPage = urlBuilder.getUrlForPage(EVOLVE_PAGE);
    wikiPage.enableEvolve(testedPage);
    wikiPage.verifyEvolveCallMercury();
  }
}
