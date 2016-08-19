package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsMonocolor extends NewTestTemplate {

  @Execute(mockAds = "true")
  @Test(groups = "AdsMonocolorOasis")
  public void adsMonocolorOasis() {
    String testPage = urlBuilder.getUrlForPath("adtest", "Monocolor_Ad");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testPage);
    adsBaseObject.verifyMonocolorAd(AdsContent.TOP_LB);
  }

}
