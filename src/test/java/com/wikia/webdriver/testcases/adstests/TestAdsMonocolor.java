package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEngineering Wikia
 */
public class TestAdsMonocolor extends TemplateNoFirstLoad {

  @Test(groups = "AdsMonocolorOasis")
  public void adsMonocolorOasis() {
    String testPage = urlBuilder.getUrlForPath("adtest", "Monocolor_Ad");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testPage);
    adsBaseObject.verifyMonocolorAd(AdsContent.TOP_LB);
  }

}
