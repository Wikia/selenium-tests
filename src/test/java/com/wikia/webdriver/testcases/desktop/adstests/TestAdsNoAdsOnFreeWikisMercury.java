package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

@Test(groups = "AdsNoAdsOnAdsFreeWikisMercury")
public class TestAdsNoAdsOnFreeWikisMercury extends TemplateNoFirstLoad {

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "adFreeWikis", groups = "AdsNoAdsOnAdsFreeWikisMercury")
  public void testNoAdsMercury(String wikiName, String path) {
    String testedPage = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(path);
    AdsBaseObject wikiPage = new AdsBaseObject(testedPage);
    wikiPage.setEnvironment(AdsContent.ENV_MOBILE);
    wikiPage.verifyNoAdsOnPage(true);
  }
}
