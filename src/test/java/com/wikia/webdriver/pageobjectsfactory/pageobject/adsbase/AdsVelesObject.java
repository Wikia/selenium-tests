package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.logging.Log;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class AdsVelesObject extends AdsBaseObject {

  public static final String
      BIDDER_PLAYER_EVENT_PATTERN
      = ".*adengplayerinfo.*event_name=in_viewport_with_fallback_bid.*";
  public static final String
      DIRECT_PLAYER_EVENT_PATTERN
      = ".*adengplayerinfo.*event_name=in_viewport_with_direct.*";
  public static final String
      MOBILE_PLAYER_EVENT_PATTERN_WITH_OFFER
      = ".*adengplayerinfo.*event_name=in_viewport_with_offer.*";
  public static final String
      MOBILE_PLAYER_LINE_ITEM_WITH_OFFER
      = ".*adengplayerinfo.*line_item_id=4407275016.*";
  public static final String
      MOBILE_PLAYER_LINE_ITEM_WITH_BIDDER
      = ".*adengplayerinfo.*line_item_id=4762545758.*";
  public static final String
      MOBILE_PLAYER_EVENT_PATTERN_WITHOUT_OFFER
      = ".*adengplayerinfo.*event_name=in_viewport_without_offer.*";
  public static final String
      NO_OFFER_PLAYER_EVENT_PATTERN
      = ".*adengplayerinfo.*event_name=in_viewport_without_offer.*";
  private static final String INCONTENT_WRAPPER = "#INCONTENT_WRAPPER";
  private static final String MOBILE_INCONTENT_WRAPPER = "#incontent_player";
  private static final String INCONTENT_VIDEO = ".video-display-wrapper";
  private static final String INCONTENT_VIDEO_HIDDEN = ".video-display-wrapper .hide";
  private static final int DEFAULT_TIMEOUT = 15;

  public AdsVelesObject(WebDriver driver, String testedPage) {
    super(testedPage);
  }

  public boolean isVelesPlayerInIncontentSlotDisplayed(boolean isMobile) {
    try {
      if (isMobile) {
        triggerMobileIncontentPlayer();
      } else {
        triggerIncontentPlayer();
      }
      wait.forElementVisible(driver.findElement(By.cssSelector(INCONTENT_VIDEO)), DEFAULT_TIMEOUT);
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      Log.log("Video Veles ad not displayed", ex, true);
      return false;
    }
  }

  public boolean isIncontentWrapperOnPage() {
    return isElementOnPage(By.cssSelector(INCONTENT_WRAPPER));
  }

  public void triggerIncontentPlayer() {
    final WebElement wrapper = driver.findElement(By.cssSelector(INCONTENT_WRAPPER));

    jsActions.scrollElementIntoViewPort(wrapper);
  }

  public void triggerMobileIncontentPlayer() {
    final WebElement wrapper = driver.findElement(By.cssSelector(MOBILE_INCONTENT_WRAPPER));

    jsActions.scrollElementIntoViewPort(wrapper);
  }

  public void triggerPorvataAlien() {
    final WebElement wrapper = driver.findElement(By.cssSelector(AdsContent.TOP_LB));

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

  public boolean isVideoHidden() {
    return isElementOnPage(By.cssSelector(INCONTENT_VIDEO_HIDDEN));
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
        return !imageComparison.isColorImage(image, blackColor, 50);
      }

      @Override
      public String toString() {
        return "Element is black";
      }
    };
  }
}
