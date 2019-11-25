package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;


public class TestDfpParamsPresent extends TemplateNoFirstLoad {


  @Test(groups = "DfpMEGAParamsPresentOasis")
  public void testAdsMEGAValMorganAUOasis() {
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.UAP_PAGE.getUrl());
    ads.verifyValMorgan("AU", "AU", false);
  }

  @Test(groups = "DfpMEGAParamsPresentOasis")
  public void testAdsMEGAValMorganNZOasis() {
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.UAP_PAGE.getUrl());
    ads.verifyValMorgan("AU", "NZ", false);
  }
}
