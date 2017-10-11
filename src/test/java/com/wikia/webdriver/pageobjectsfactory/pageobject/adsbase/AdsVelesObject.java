package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class AdsVelesObject extends AdsBaseObject {

  private static final String INCONTENT_WRAPPER = "#INCONTENT_WRAPPER,.mobile-in-content";
  private static final String INCONTENT_VIDEO = ".video-display-wrapper";

  public static final String BIDDER_PLAYER_EVENT_PATTERN = ".*adengplayerinfo.*event_name=in_viewport_with_fallback_bid.*";
  public static final String DIRECT_PLAYER_EVENT_PATTERN = ".*adengplayerinfo.*event_name=in_viewport_with_direct.*";
  public static final String NO_OFFER_PLAYER_EVENT_PATTERN = ".*adengplayerinfo.*event_name=in_viewport_without_offer.*";

  public AdsVelesObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  public void verifyVelesPlayerInIncontentSlot() {
    triggerIncontentPlayer();
    wait.forElementVisible(driver.findElement(By.cssSelector(INCONTENT_VIDEO)));
  }

  public void triggerIncontentPlayer() {
    final WebElement wrapper = driver.findElement(By.cssSelector(INCONTENT_WRAPPER));

    jsActions.scrollToElement(wrapper);
  }

  public void triggerPorvataAlien() {
    final WebElement wrapper = driver.findElement(By.cssSelector(presentLeaderboardSelector));

    jsActions.scrollToElement(wrapper);
  }

  public boolean isVideoVisible() {
    final WebElement element = driver.findElement(By.cssSelector(INCONTENT_VIDEO));

    wait.forElementVisible(element);

    try {
      waitFor.until(isElementNotBlack(element));

      return true;
    } finally {
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
  }

  private ExpectedCondition<Boolean> isElementNotBlack(WebElement element) {
    final Color blackColor = new Color(0, 0, 0);
    final ImageComparison imageComparison = new ImageComparison();
    final Shooter shooter = new Shooter();

    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        BufferedImage image = shooter.takeScreenshot(element, driver);
        // Big Buck Bunny movie has about 33% black pixels
        return !imageComparison.isColorImage(image, blackColor, 40);
      }

      @Override
      public String toString() {
        return "Element is black";
      }
    };
  }
}
