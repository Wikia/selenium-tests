package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

/**
 * @ownership AdEng
 */
public class TestAdsMonocolor extends TemplateNoFirstLoad {

  @Test(groups = "AdsMonocolorOasis")
  public void adsMonocolorOasis() {
    String testPage = urlBuilder.getUrlForPath("adtest", "Monocolor_Ad");
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testPage);
    adsBaseObject.verifyMonocolorAd(AdsContent.TOP_LB);
  }

}
