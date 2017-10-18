package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestAdsVideoFrequencyCapping extends TemplateNoFirstLoad {
  private static final String CONFIG = "InstantGlobals.wgAdDriverOutstreamVideoFrequencyCapping=[1/2min]";

  private static final String DIRECT_VELES_WIKI = "project43";
  private static final String DIRECT_VELES_PAGE_URI = "SyntheticTests/Video/Porvata/Direct";

  private static final Page PAGE_WITH_VELES = new Page(DIRECT_VELES_WIKI, DIRECT_VELES_PAGE_URI);

  @Test(
      groups = "AdsVideoFrequencyCapping"
  )
  public void adsVideoFrequencyCapping() {
    String testedPage = PAGE_WITH_VELES.getUrl();
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, CONFIG);

    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);

    triggerIncontentPlayer();
    Assertion.assertTrue(isIncontentPlayerDispalyed(ads), "Video Player is not displayed");

    ads.refreshPage();
    Assertion.assertFalse(isIncontentPlayerDispalyed(ads), "Video Player is displayed");
  }

  private boolean isIncontentPlayerDispalyed(AdsBaseObject ads) {
    try {
      ads.waitForSlotExpanded(driver.findElement(By.cssSelector("#INCONTENT_PLAYER")));
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      PageObjectLogging.log("INCONTENT_PLAYER is not displayed", ex, true);
      return false;
    }
  }

  private void triggerIncontentPlayer() {
    JavascriptActions jsActions = new JavascriptActions(driver);
    WebElement slotWrapper = driver.findElement(By.cssSelector("#INCONTENT_WRAPPER"));

    jsActions.scrollToElement(slotWrapper);
    jsActions.scrollBy(0, -10);
  }

}
