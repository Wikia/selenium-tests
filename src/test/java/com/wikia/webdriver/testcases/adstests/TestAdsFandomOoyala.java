package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TestAdsFandomOoyala extends AdsFandomTestTemplate {
  private static final String PLAY_BUTTON_SELECTOR = ".ooyala-video .oo-action-icon";
  private static final String PLAYER_SELECTOR = ".ooyala-video .oo-player";
  private static final Color BLUE = new Color(0, 1, 253);
  private static final int AD_DURATION_SEC = 30;

  @Test(
      groups = {"AdsFandomOoyalaClickToPlayPrerollDesktop"}
  )
  public void adsFandomOoyalaPrerollClickToPlayDesktop() {
    loadPage("the-best-movies-of-2017-so-far");
    Wait wait = new Wait(driver);
    WebElement playButton = driver.findElement(By.cssSelector(PLAY_BUTTON_SELECTOR));

    wait.forElementVisible(playButton);
    playButton.click();

    verifyColorAd(driver.findElement(By.cssSelector(PLAYER_SELECTOR)), BLUE, AD_DURATION_SEC);
  }

  private void verifyColorAd(WebElement element, Color color, int durationSec) {
    AdsComparison adsComparison = new AdsComparison();
    waitForColorAds(element, color);
    adsComparison.verifyColorAd(element, color, durationSec, driver);
  }

  private void waitForColorAds(WebElement element, Color color) {
    WebDriverWait waitFor = new WebDriverWait(driver, 15);
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MICROSECONDS);

    try {
      waitFor.until(CommonExpectedConditions
                        .elementToHaveColor(element, color,
                                            AdsComparison.IMAGES_THRESHOLD_PERCENT));
    } finally {
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
  }
}
