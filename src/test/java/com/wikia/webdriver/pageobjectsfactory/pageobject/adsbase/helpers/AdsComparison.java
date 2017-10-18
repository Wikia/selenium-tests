package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AdsComparison {

  public static final int IMAGES_THRESHOLD_PERCENT = 12;
  private static final int MILLIS_IN_SEC = 1000;
  private static final int DURATION_ACCURACY_PERCENT = 70;
  private static final int TIME_STEP_MILLS = 1000;
  private static final int MAX_ATTEMPTS = 600;
  private static final int AD_TIMEOUT_SEC = 15;
  protected ImageComparison imageComparison;
  private Shooter shooter;

  public AdsComparison() {
    imageComparison = new ImageComparison();
    shooter = new Shooter();
  }

  public void hideSlot(String selector, WebDriver driver) {
    changeOpacity(selector, 0, driver);
  }

  public void showSlot(String selector, WebDriver driver) {
    changeOpacity(selector, 100, driver);
  }

  private void changeOpacity(String selector, int value, WebDriver driver) {
    PageObjectLogging.log("CSS selector", selector, true, driver);
    JavascriptActions javascriptActions = new JavascriptActions(driver);
    javascriptActions.changeElementOpacity(selector, value);
  }

  public boolean isAdVisible(final WebElement element, final String selector,
                             final WebDriver driver) {
    hideSlot(selector, driver);
    final BufferedImage backgroundImg = shooter.takeScreenshot(element, driver);
    PageObjectLogging.log("ScreenshotsComparison", "Background image in " + selector, true, driver);
    showSlot(selector, driver);
    try {
      WebDriverWait wait = new WebDriverWait(driver, AD_TIMEOUT_SEC);
      wait.until(new ExpectedCondition<Object>() {
        @Override
        public Object apply(WebDriver driver) {
          BufferedImage adImg = shooter.takeScreenshot(element, driver);
          PageObjectLogging.log("ScreenshotsComparison", "Ad image in " + selector, true);
          if (adImg.getHeight() == 1 || imageComparison.isMonocolorImage(adImg)) {
            return false;
          }
          return imageComparison.areImagesDifferent(backgroundImg, adImg, IMAGES_THRESHOLD_PERCENT);
        }
      });
    } catch (TimeoutException e) {
      PageObjectLogging.logWarning("ScreenshotsComparison", e);
      return false;
    }
    return true;
  }

  /**
   * @param durationSec time until element should have a color.
   */
  public void verifyColorAd(WebElement element, Color color, int durationSec, WebDriver driver) {
    long startTime = System.currentTimeMillis();
    long currentTime;
    double acceptableDurationSec = (DURATION_ACCURACY_PERCENT / 100D) * durationSec;
    int attempts = 0;
    try {
      do {
        verifyColorAd(element, color, driver);
        Thread.sleep(TIME_STEP_MILLS);
        attempts += 1;
        currentTime = (System.currentTimeMillis() - startTime) / MILLIS_IN_SEC;
        PageObjectLogging.log("verifyColor", "Current time: " + currentTime + " seconds", true);
      } while ((currentTime < acceptableDurationSec) && (attempts < MAX_ATTEMPTS));
    } catch (InterruptedException e) {
      PageObjectLogging.log("verifyColor", e, false, driver);
    }
  }

  public boolean isMostFrequentColorValid(WebDriver driver, WebElement element, Color color) {
    BufferedImage image = shooter.takeScreenshot(element, driver);
    Color mostFrequentColor = imageComparison.getMostFrequentColor(image);

    PageObjectLogging.logWarning("Comparing two colors", "Actual: " + mostFrequentColor + "; expected: " + color);

    return imageComparison.areColorsSimilar(mostFrequentColor, color);
  }

  private void verifyColorAd(WebElement element, Color color, WebDriver driver) {
    BufferedImage image = shooter.takeScreenshot(element, driver);
    if (imageComparison.isColorImage(image, color, IMAGES_THRESHOLD_PERCENT)) {
      PageObjectLogging.log(
          "verifyColor",
          "At least " + IMAGES_THRESHOLD_PERCENT + " percents of Ad has " + color,
          true,
          driver
      );
    } else {
      throw new NoSuchElementException(
          "At least " + (100 - IMAGES_THRESHOLD_PERCENT) + " percents of Ad does not have " + color
      );
    }
  }
}
