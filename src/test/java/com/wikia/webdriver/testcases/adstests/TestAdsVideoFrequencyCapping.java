package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;


public class TestAdsVideoFrequencyCapping extends NewTestTemplate {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVideoFrequencyCapping",
      groups = "AdsVideoFrequencyCapping"
  )
  public void adsVideoFrequencyCapping(Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
    ads.scrollToPosition("#Section_2.mw-headline");
    Assertion.assertTrue(isIncontentPlayerDispalyed(ads), "Video Player is not displayed");
    ads.refreshPage();
    Assertion.assertFalse(isIncontentPlayerDispalyed(ads), "Video PLayer is displayed");
  }

  private boolean isIncontentPlayerDispalyed(AdsBaseObject ads) {
    try {
      ads.waitForSlotExpanded(driver.findElement(By.cssSelector("#INCONTENT_PLAYER")));
      return true;
    }catch (TimeoutException | NoSuchElementException ex) {
      PageObjectLogging.log("INCONTENT_PLAYER is not displayed", ex, true);
      return false;
    }
  }

}
