package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;


public class TestAdsPageFairRecoveryOasis extends TemplateNoFirstLoad {

  private static Dimension DESKTOP_SIZE = new Dimension(1050, 600);

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryPageFairOasis",
      groups = "AdsRecoveryPageFairOasis"
  )
  public void adsRecoveryPageFairOasis(Page page) {
    String url = urlBuilder.getUrlForPage(page);
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);
    adsRecoveryObject.refreshPageAddingCacheBuster();

    adsRecoveryObject.verifyPageFairRecoveryWithAdBlock();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryPageFairOasis",
      groups = "AdsRecoveryNoAdblockPageFairOasis"
  )
  public void AdsRecoveryNoAdblockPageFairOasis(Page page) {
    String url = urlBuilder.getUrlForPage(page);
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);
    adsRecoveryObject.refreshPageAddingCacheBuster();

    adsRecoveryObject.verifyPageFairRecoveryWithNoAdBlock();
  }
}

