package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author drets
 * @ownership AdEng
 */
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
  private final int SIZE_DIFFERENCE_TOLERANCE = 1;

  @FindBy(css = "iframe.wikia-ad-iframe")
  private WebElement interstitialAdIframe;

  @FindBy(css = "div#ext-wikia-adEngine-template-modal, div.lightbox-content-inner > div")
  private WebElement interstitialAdWrapper;

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

  public void verifySize(Dimension size) {
    Assertion.assertEquals(
        isSizeCorrect(interstitialAdWrapper.getSize().getWidth(), size.getWidth()),
        true
    );

    Assertion.assertEquals(
        isSizeCorrect(interstitialAdWrapper.getSize().getHeight(), size.getHeight()),
        true
    );
  }

  private boolean isSizeCorrect(int actualSize, int expectedSize) {
    return (
        actualSize >= expectedSize - SIZE_DIFFERENCE_TOLERANCE ||
        actualSize <= expectedSize + SIZE_DIFFERENCE_TOLERANCE
    );
  }
}
