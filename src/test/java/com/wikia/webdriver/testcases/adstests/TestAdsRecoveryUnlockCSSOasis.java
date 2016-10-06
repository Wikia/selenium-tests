package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;

import org.testng.annotations.Test;

public class TestAdsRecoveryUnlockCSSOasis extends NewTestTemplate {

  @Execute(mockAds = "true")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryUnlockCSSOasis",
      groups = "AdsRecoveryUnlockCSSOasis"
  )
  public void adsRecoveryUnlockCSSOasis(Page page, boolean isRecoveryEnabled)
      throws InterruptedException {
    String url = urlBuilder.getUrlForPage(page);

    AdsRecoveryObject adsObject = new AdsRecoveryObject(driver, url);

    if (isRecoveryEnabled) {
      adsObject.verifyRecoveryUnlockCSS();
    } else {
      adsObject.verifyUnlockCSS();
    }
  }
}
