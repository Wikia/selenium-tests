package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsPrefooters extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsMiddlePrefooter",
      groups = "AdsMiddlePrefooter"
  )
  public void adsMiddlePrefooter(
      String wikiName,
      String path,
      Dimension windowResolution,
      boolean middlePrefooterEnabled
  ) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, path);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, windowResolution);
    adsBaseObject.scrollToFooter(true);

    boolean middlePrefooterOnPage = adsBaseObject.isMiddlePrefooterOnPage();

    Assertion.assertEquals(middlePrefooterOnPage, middlePrefooterEnabled,
                           "Middle prefooter on page: " + middlePrefooterOnPage);

    if (middlePrefooterEnabled) {
      adsBaseObject.verifyMiddlePrefooterAdPresent();
    }
  }
}
