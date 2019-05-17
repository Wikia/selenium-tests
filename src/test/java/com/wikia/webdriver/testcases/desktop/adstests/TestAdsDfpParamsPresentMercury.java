package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.List;


@Execute(onWikia = "project43")
public class TestAdsDfpParamsPresentMercury extends MobileTestTemplate {

  private static final String LINE_ITEM_ID = "282067812";
  private static final String CREATIVE_ID = "50006703732";

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(dataProviderClass = MobileAdsDataProvider.class, dataProvider = "dfpParamsSynthetic", groups = {
      "MobileAds", "AdsDfpParamsPresentSyntheticMercury"})
  public void dfpParamsPresentSyntheticMercury(
      String wikiName,
      String article,
      String queryString,
      String adUnit,
      String slot,
      List<String> pageParams,
      List<String> slotParams
  ) {
    UrlBuilder urlBuilder = UrlBuilder.createUrlBuilderForWiki(wikiName);
    String testedPage = urlBuilder.getUrlForPath(article);
    if (StringUtils.isNotEmpty(queryString)) {
      testedPage = urlBuilder.appendQueryStringToURL(testedPage, queryString);
    }

    AdsBaseObject ads = new AdsBaseObject(testedPage);
    ads.refreshPage();
    ads.verifyGptMEGAIframe(adUnit, slot);
    ads.verifyGptParams(slot, pageParams, slotParams);
    ads.verifyGptAdInSlot(slot, LINE_ITEM_ID, CREATIVE_ID);
  }

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
