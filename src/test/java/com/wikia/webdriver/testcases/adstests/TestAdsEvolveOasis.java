package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;

import org.testng.annotations.Test;

public class TestAdsEvolveOasis extends TemplateNoFirstLoad {

  @UseUnstablePageLoadStrategy
  @Test(groups = "AdsEvolveOasis")
  public void adsEvolveOasis() {
    AdsEvolveObject wikiPage = new AdsEvolveObject(driver);
    String testedPage = urlBuilder.getUrlForPath("project43", "SyntheticTests/Evolve");
    wikiPage.enableEvolve(testedPage);
    wikiPage.verifyEvolveInAdSlots();
  }
}
