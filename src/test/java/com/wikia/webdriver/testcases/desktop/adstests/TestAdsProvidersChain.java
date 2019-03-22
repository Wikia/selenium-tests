package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsProvidersChain extends TemplateNoFirstLoad {

  private static final Dimension DESKTOP_SIZE = new Dimension(1900, 900);

  @UseUnstablePageLoadStrategy
  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "providersChainOasis", groups = {
      "AdsProvidersChainOasis", "Ads"})
  public void adsProvidersChainOasis(
      String wikiName, String article, String slotName, String providers
  ) {
    adsProvidersChain(wikiName, article, slotName, providers, DESKTOP_SIZE);
  }

  private void adsProvidersChain(
      String wikiName, String article, String slotName, String providers, Dimension browserDimension
  ) {
    String url = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(article);

    new AdsBaseObject(driver, url, browserDimension).verifyProvidersChain(slotName, providers);
  }
}
