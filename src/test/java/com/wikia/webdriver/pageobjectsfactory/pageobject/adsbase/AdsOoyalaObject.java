package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsOoyalaObject extends AdsBaseObject {

  @FindBy(css = "object[data^='http://player.ooyala.com/player.swf']")
  private WebElement lightbox;

  public AdsOoyalaObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public void verifyLightboxAd(Color lightboxAdColor, int adDurationSec) {
    verifyColorAd(lightbox, lightboxAdColor, adDurationSec);
    LOG.log("LightboxAd",
            "Lightbox had " + lightboxAdColor + " during " + adDurationSec
            + " seconds", LOG.Type.SUCCESS);
  }

  public void verifyLightboxVideo(Color lightboxVideoColor, int videoDurationSec) {
    verifyColorAd(lightbox, lightboxVideoColor, videoDurationSec);
    LOG.log("LightboxVideo",
            "Lightbox had " + lightboxVideoColor + " during " + videoDurationSec
            + " seconds", LOG.Type.SUCCESS);
  }

  private void verifyColorAd(WebElement element, Color color, int durationSec) {
    AdsComparison adsComparison = new AdsComparison();
    waitForColorAds(element, color);
    adsComparison.verifyColorAd(element, color, durationSec, driver);
  }

  private void waitForColorAds(WebElement element, Color color) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions
                        .elementToHaveColor(element, color,
                                            AdsComparison.IMAGES_THRESHOLD_PERCENT));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void verifyFlash() {
    Boolean hasFlash = (Boolean) jsActions.execute(
        "'undefined' != typeof navigator.mimeTypes['application/x-shockwave-flash']");
    LOG.logResult("Verify flash", "Flash should be turned on", hasFlash);
  }
}
