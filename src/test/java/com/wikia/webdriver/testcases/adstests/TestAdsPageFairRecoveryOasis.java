package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.CaptureScreenshot;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.Map;

public class TestAdsPageFairRecoveryOasis extends TemplateNoFirstLoad {

  private static Dimension DESKTOP_SIZE = new Dimension(1050, 600);


  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsRecoveryPageFairOasis",
      groups = "AdsRecoveryPageFairOasis"
  )
  public void adsRecoveryPageFairOasis(Page page, String urlParam, String imagePath) {

    String pp = urlBuilder.getUrlForWiki("project43");
    String url = urlBuilder.getUrlForPage(page);
    url = urlBuilder.appendQueryStringToURL(url, urlParam);
   AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, pp, DESKTOP_SIZE);
   adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    AdsComparison kk = new AdsComparison();
    Assertion.assertTrue(kk.compareImageWithScreenshot(imagePath, driver));

  }
}

