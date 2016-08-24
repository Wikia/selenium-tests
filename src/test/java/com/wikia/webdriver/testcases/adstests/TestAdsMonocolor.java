package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsMonocolor extends NewTestTemplate {

  @Test(
      groups = "AdsMonocolorOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsMonocolorOasis"
  )
  public void adsMonocolorOasis(String wikiName, String pageName) {
    String testPage = urlBuilder.getUrlForPath(wikiName, pageName);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testPage);
    adsBaseObject.verifyMonocolorAd(AdsContent.TOP_LB);
  }

}
