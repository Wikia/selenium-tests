package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsSlotsOasis extends TemplateNoFirstLoad {

  @Test(groups = "TestSlotsOasis")
  public void adsSmokeTestSlotsOasis() {
    String testedPage = urlBuilder.getUrlForPath("project43", "SyntheticTests/OasisSlots");
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.waitForPageLoadedWithGpt();
    ads.verifyAds();
  }

}
