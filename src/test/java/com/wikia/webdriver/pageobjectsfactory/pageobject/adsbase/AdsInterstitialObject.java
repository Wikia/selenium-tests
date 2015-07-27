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

  @FindBy(css = "iframe[class=wikia-ad-iframe]")
  private WebElement interstitialAdIframe;

  @FindBy(css = "div[id=ext-wikia-adEngine-template-modal], "
                + "div[class=lightbox-content-inner] > div")
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
    Assertion.assertEquals(interstitialAdWrapper.getSize(), size);
  }
}
