package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;


/**
 * @author drets
 * @ownership AdEng
 */
public class TestProvidersChain extends NewTestTemplate {

  @GeoEdgeProxy
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "testProvidersChain",
      groups = {"TestProvidersChain", "Ads"}
  )
  public void testProvidersChain(String countryCode, String wikiName, String article,
                                 String slotName, String providers, int times) {
    setGeoEdge(countryCode);
    new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiName, article))
        .refresh(times)
        .waitPageLoaded()
        .verifyProvidersChain(slotName, providers);
  }
}
