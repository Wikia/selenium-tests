package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsSlotsOasis extends TemplateNoFirstLoad {

  @Test(groups = "TestSlotsOasis")
  public void adsSmokeTestSlotsOasis() {
    String testedPage = UrlBuilder.createUrlBuilderForWiki("project43")
        .getUrlForPath("SyntheticTests/OasisSlots");
    AdsBaseObject ads = new AdsBaseObject(testedPage);
    ads.waitForPageLoadedWithGpt();
    ads.verifyAds();
  }
}
