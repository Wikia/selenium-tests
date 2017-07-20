package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;


public class TestAdsPageFairRecoveryOasis extends TemplateNoFirstLoad {

  private static Dimension DESKTOP_SIZE = new Dimension(1920, 768);

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryPageFairOasis",
      groups = "AdsRecoveryPageFairOasis"
  )
  public void adsRecoveryPageFairOasis(Page page) {
    String url = urlBuilder.getUrlForPage(page);
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);
    adsRecoveryObject.refreshPageAddingCacheBuster();

    adsRecoveryObject.verifyPageFairRecoveryWithAdBlock(4);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryPageFairOasis",
      groups = "AdsRecoveryNoAdblockPageFairOasis"
  )
  public void adsRecoveryNoAdblockPageFairOasis(Page page) {
    String url = urlBuilder.getUrlForPage(page);
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);
    adsRecoveryObject.refreshPageAddingCacheBuster();

    adsRecoveryObject.verifyNumberOfPageFairRecoveredSlots(4);
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryPageFairOasis",
      groups = "AdsRecoveryLoggedInPageFairOasis"
  )
  @Execute(asUser = User.USER_2)
  public void adsRecoveryLoggedInPageFairOasis(Page page) {
    String url = urlBuilder.getUrlForPage(page);
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);
    adsRecoveryObject.refreshPageAddingCacheBuster();

    adsRecoveryObject.verifyNumberOfPageFairRecoveredSlots(0);
  }
}

