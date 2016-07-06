package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  @FindBy(css = "iframe.wikia-ad-iframe")
  private WebElement interstitialAdIframe;

  @FindBy(css = "div#ext-wikia-adEngine-template-modal, div.lightbox-content-inner > div")
  private WebElement interstitialAdWrapper;

  @FindBy(css = ".lightbox-close-wrapper, #ext-wikia-adEngine-template-modal .close")
  private WebElement interstitialCloseButton;

  public AdsInterstitialObject(WebDriver driver, String testedPage,
                               Dimension resolution) {
    super(driver, testedPage, resolution);
  }

  public void verifyAdRatio() {
    String scaledAdRatio = interstitialAdIframe.getCssValue("transform");
    Pattern pattern = Pattern.compile("matrix\\((.*), 0, 0, (.*), 0, 0\\)");
    Matcher matcher = pattern.matcher(scaledAdRatio);
    if (!matcher.find()) {
      throw new AssertionError("Ad is not scaled");
    }
    Assertion.assertEquals(matcher.group(1), matcher.group(2), "Ad is scaled unproportionally");
  }

  public void verifySize(Dimension expectedSize) {
    Dimension actualSize = interstitialAdWrapper.getSize();

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
            "The height of ad unit is not within tolerance range " +
            "[actual: %d, expected: %d, tolerance: +-%d]",
            actualSize.getHeight(),
            expectedSize.getHeight(),
            SIZE_DIFFERENCE_TOLERANCE
        )
    );
  }

  public void closeInterstitial() {
    wait.forElementClickable(interstitialCloseButton);
    interstitialCloseButton.click();
  }

  public void verifyInterstitialIsClosed() {
    waitForElementNotVisibleByElement(interstitialAdWrapper);
  }

  private boolean isSizeCorrect(int actualSize, int expectedSize) {
    return
        actualSize >= expectedSize - SIZE_DIFFERENCE_TOLERANCE &&
        actualSize <= expectedSize + SIZE_DIFFERENCE_TOLERANCE;
  }
}
