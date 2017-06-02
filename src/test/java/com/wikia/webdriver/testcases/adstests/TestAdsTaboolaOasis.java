package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsTaboolaObject;

import org.testng.annotations.Test;

public class TestAdsTaboolaOasis extends TemplateNoFirstLoad {

  @NetworkTrafficDump
  @Test(groups = "AdsTaboolaOasis")
  public void adsTaboolaOasis() {
    String testedPage = urlBuilder.getUrlForPath("project43",
        "SyntheticTests/Taboola?InstantGlobals.wgAdDriverPageFairRecoveryCountries=[]&InstantGlobals.wgAdDriverSourcePointRecoveryCountries=[]");

    networkTrafficInterceptor.startIntercepting();

    AdsTaboolaObject adsTaboolaObject = new AdsTaboolaObject(driver);
    adsTaboolaObject.getUrl(testedPage);

    adsTaboolaObject.verifyTaboolaContainer(AdsTaboolaObject.ABOVE_ARTICLE_CSS_SELECTOR);
    adsTaboolaObject.verifyTaboolaContainer(AdsTaboolaObject.BELOW_ARTICLE_CSS_SELECTOR);

    adsTaboolaObject.wait.forSuccessfulResponse(
        networkTrafficInterceptor,
        AdsTaboolaObject.TABOOLA_LOADER_REQUEST
    );

    PageObjectLogging.log(
        "adsTaboolaOasis",
        "Taboola loader.js has been requested",
        true
    );
  }

}
