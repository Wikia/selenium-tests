package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * @ownership AdEngineering Wikia
 */
public class TestAdsExtraMarkerOasis extends TemplateNoFirstLoad {

  private static final int TIMEOUT_SEC = 30;

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = "AdsExtraMarkerOasis",
      dataProvider = "adsExtraMarkerOasis"
  )
  public void AdsExtraMarkerOasis(final String wikiName,
                                  final String article,
                                  final String adType,
                                  final String message) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    final AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
    new WebDriverWait(driver, TIMEOUT_SEC).until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        return wikiPage.getBrowserLogs(adType).toString().contains(message);
      }

      @Override
      public String toString() {
        return String.format("Expected message: [ %s ], Logs: [ %s ]",
                             message,
                             wikiPage.getBrowserLogs(adType).toString());
      }
    });
  }
}
