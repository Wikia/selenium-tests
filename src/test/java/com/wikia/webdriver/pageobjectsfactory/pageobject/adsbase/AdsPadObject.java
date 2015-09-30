package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership AdEng
 */
public class AdsPadObject extends AdsBaseObject {

  private static final String PAD_IMG_CSS =
      "#mw-content-text #pad-test-img, #NATIVE_PAID_ASSET_DROP #pad-test-img";

  @FindBy(css = PAD_IMG_CSS)
  private WebElement padImg;

  public AdsPadObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void verifyPadHeight(int height) {
    Assertion.assertEquals(padImg.getSize().height, height);
    LOG.log("PAD", "PAD is on the page", true, driver);
  }

  public void verifyNoPadOnPage() {
    Assertion.assertFalse(isElementOnPage(By.cssSelector(PAD_IMG_CSS)));
    LOG.log("PAD", "PAD is not on the page", true, driver);
  }
}
