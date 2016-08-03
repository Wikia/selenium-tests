package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsKruxObject;

import org.testng.annotations.Test;

public class TestAdsKruxIntegration extends TemplateNoFirstLoad {

  private static final String KRUX_SITE_ID_DESKTOP = "JU3_GW1b";
  private static final String KRUX_SITE_ID_MOBILE = "JTKzTN3f";

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "kruxIntegration",
      groups = "AdsKruxIntegrationMercury"
  )
  @UseUnstablePageLoadStrategy
  public void adsKruxIntegrationMercury(String wikiName, String article) {
    adsKruxIntegration(wikiName, article, KRUX_SITE_ID_MOBILE, AdsContent.MOBILE_TOP_LB);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "kruxIntegration",
      groups = "AdsKruxIntegrationOasis"
  )
  public void adsKruxIntegrationOasis(String wikiName, String article) {
    adsKruxIntegration(wikiName, article, KRUX_SITE_ID_DESKTOP, AdsContent.TOP_LB);
  }

  private void adsKruxIntegration(String wikiName,
                                  String article,
                                  String kruxSiteId,
                                  String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    AdsKruxObject ads = new AdsKruxObject(driver, testedPage);
    ads.verifyKruxControlTag(kruxSiteId);

    // Second page view
    ads.refreshPage();
    ads.verifyKruxControlTag(kruxSiteId);

    // Third page view
    ads.refreshPage();
    ads.verifyKruxControlTag(kruxSiteId);

    ads.verifyKruxUserParam(slotName);
  }
}
