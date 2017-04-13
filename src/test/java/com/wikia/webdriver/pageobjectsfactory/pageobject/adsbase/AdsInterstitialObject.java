package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class AdsInterstitialObject extends AdsBaseObject {

  /**
   * Checks if the actual size (width or height) is within the "range of correctness" ;)
   *
   * We don't want to be pixel perfect in terms of checking the size of element because
   * it depends on many things such as in example components of a browser's window.
   *
   * We also don't want to just check if the content of scalable interstitial is bigger
   * than the original image. So, we came up with a reasonable size difference margin.
   *
   * The size defined here is doubled at the end.
   *
   * @see AdsInterstitialObject::isSizeCorrect()
   */
  private static final int SIZE_DIFFERENCE_TOLERANCE = 32;

  private static final int WAIT_BUTTON_DELAY_TOLERANCE = 2;

  private static final String INTERSTITSIAL_AD_SELECTOR = "#INVISIBLE_HIGH_IMPACT_2 .provider-container iframe";

  @FindBy(css = "a.close")
  private WebElement interstitialCloseButton;

  @FindBy(css = ".lightbox-close-wrapper")
  private WebElement interstitialCloseButtonMercury;

  @FindBy(css = "#INVISIBLE_HIGH_IMPACT_2")
  private WebElement interstitialAd;

  public AdsInterstitialObject(WebDriver driver) {
    super(driver);
  }

  public boolean isInterstitialAdDisplayed() {
    try {
      wait.forElementVisible(interstitialAd);
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      PageObjectLogging.log("Hight Impact Interstitial ad is not Displayed", ex, true);
      return false;
    }
  }

  public void verifySize(Dimension expectedAdSize) {

    Dimension actualSize = driver.findElement(By.cssSelector(INTERSTITSIAL_AD_SELECTOR)).getSize();
    Dimension expectedSize = expectedAdSize;


    Assertion.assertTrue(
        isSizeCorrect(actualSize.getWidth(), expectedSize.getWidth()),
        String.format(
            "The width of ad unit is not within tolerance range " +
                "[actual: %d, expected: %d, tolerance: +-%d]",
            actualSize.getWidth(),
            expectedSize.getWidth(),
            SIZE_DIFFERENCE_TOLERANCE
        )
    );

    Assertion.assertTrue(
        isSizeCorrect(actualSize.getHeight(), expectedSize.getHeight()),
        String.format(
            "The width of ad unit is not within tolerance range " +
                "[actual: %d, expected: %d, tolerance: +-%d]",
            actualSize.getHeight(),
            expectedSize.getHeight(),
            SIZE_DIFFERENCE_TOLERANCE
        )
    );
  }

  public void closeInterstitial() throws InterruptedException {

    boolean isMercury = false;
    try {
      isMercury = interstitialCloseButtonMercury.isDisplayed();
      PageObjectLogging.log("Interstitial Mercury", "Yes", true);
    } catch (NoSuchElementException e) {
      PageObjectLogging.log("Interstitial Mercury", "No", true);
    }


    if (isMercury) {
      String closeButtonText = interstitialCloseButtonMercury.getText();
      if (closeButtonText.length() > 0) {
        Integer waitTillCloseButtonAppears = Integer.parseInt(closeButtonText);
        PageObjectLogging.log("Wait time for close button", String.valueOf(waitTillCloseButtonAppears), true);
        Thread.sleep((waitTillCloseButtonAppears+WAIT_BUTTON_DELAY_TOLERANCE) * 1000);
      }
    }

    wait.forElementClickable(interstitialCloseButton);
    interstitialCloseButton.click();
  }

  public void verifyInterstitialIsClosed() {
    waitForElementNotVisibleByElement(interstitialAd);
  }

  private boolean isSizeCorrect(int actualSize, int expectedSize) {
    return
        actualSize >= expectedSize - SIZE_DIFFERENCE_TOLERANCE &&
        actualSize <= expectedSize + SIZE_DIFFERENCE_TOLERANCE;
  }
}
