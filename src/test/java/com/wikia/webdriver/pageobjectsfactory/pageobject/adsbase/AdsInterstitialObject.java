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
  private WebElement interstitialAd;

  public AdsInterstitialObject(WebDriver driver, String testedPage,
                               Dimension resolution) {
    super(driver, testedPage, resolution);
  }

  public Double getScaledAdRatio() {
    String scaledAdRatio = interstitialAd.getCssValue("transform");
    Pattern pattern = Pattern.compile("matrix\\((.*), 0, 0, (.*), 0, 0\\)");
    Matcher matcher = pattern.matcher(scaledAdRatio);
    if (!matcher.find()) {
      return 0.0;
    }
    Assertion.assertEquals(matcher.group(1), matcher.group(2), "Ad is scaled unproportionally");
    return Double.parseDouble(matcher.group(1));
  }
}
