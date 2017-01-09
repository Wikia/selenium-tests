package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class VideoFanTakeover {
  private static final String VIDEO_IFRAME_SELECTOR_FORMAT = "#%s .video-ima-container iframe";
  private static final String MOBILE_VIDEO_SELECTOR_FORMAT = "#%s .video-ima-container video";
  private static final String UI_ELEMENT_SELECTOR_FORMAT = "#%s .overVideoLayer";
  public static final String AD_REDIRECT_URL = "http://fandom.wikia.com/";
  private static final int PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD = 28;
  private static By playTriggerButtonSelector = By.id("button");
  private static By closeVideoButtonSelector = By.className("close-ad");
  private final Wait wait;
  private final String slotName;
  private WikiaWebDriver driver;
  private WebElement iframe;

  public VideoFanTakeover(WikiaWebDriver driver, String iframeId, String slotName) {
    this.wait = new Wait(driver);
    this.driver = driver;
    this.slotName = slotName;
    setIframe(iframeId);
  }

  private void setIframe(String iframeId) {
    By iframeSelector = By.id(iframeId);
    wait.forElementPresent(iframeSelector);
    iframe = driver.findElement(iframeSelector);
  }

  public WebElement getIframe() {
    return iframe;
  }

  public void play() {
    runInAdFrame(() -> wait.forElementClickable(playTriggerButtonSelector).click());
    waitForVideoStart();
  }

  public void pause() {
    driver.findElement(By.className("overVideoLayer")).click();
  }

  private interface Lambda {
    void run();
  }

  private void runInAdFrame(Lambda f) {
    driver.switchTo().frame(iframe);
    f.run();
    driver.switchTo().defaultContent();
  }

  public void waitForVideoStart() {
    wait.forElementVisible(By.cssSelector(String.format(UI_ELEMENT_SELECTOR_FORMAT, slotName)));
    PageObjectLogging.log("waitForVideoStart", "video started", true, driver);
  }

  public void waitForVideoPlayerHidden() {
    wait.forElementNotVisible(By.cssSelector(String.format(UI_ELEMENT_SELECTOR_FORMAT, slotName)));
    PageObjectLogging.log("waitForVideoPlayerHidden", "video ended, video hidden", true, driver);
  }

  public void waitForAdToLoad() {
    runInAdFrame(() -> wait.forElementClickable(playTriggerButtonSelector));
  }

  public void clickOnAdImage(){
    runInAdFrame(() -> {
      final By adImageTrigger = By.cssSelector("#adContainer a");
      wait.forElementClickable(adImageTrigger);
      driver.findElement(adImageTrigger).click();
      PageObjectLogging.log("clickOnAdImage", "ad image clicked", true, driver);
    });
  }

  public void clickOnVideoCloseButton() {
    wait.forElementVisible(closeVideoButtonSelector).click();
    PageObjectLogging.log("clickOnVideoCloseButton", "close video button clicked", true, driver);
  }

  public Double getCurrentVideoTimeOnDesktop() {
    String result;

    driver.switchTo().frame(driver.findElement(By.cssSelector(String.format(VIDEO_IFRAME_SELECTOR_FORMAT, slotName))));
    result = driver.findElement(By.cssSelector("video")).getAttribute("currentTime");
    driver.switchTo().defaultContent();

    return Double.parseDouble(result);
  }

  public Double getCurrentVideoTimeOnMobile() {
    String result;

    result = driver.findElement(By.cssSelector(String.format(MOBILE_VIDEO_SELECTOR_FORMAT, slotName))).getAttribute("currentTime");
    return Double.parseDouble(result);
  }

  public double getAdSlotHeight(String slotSelector) {
    return driver.findElement(By.cssSelector(slotSelector)).getSize().getHeight();
  }

  public double getAdVideoHeight() {
    return driver.findElement(By.cssSelector(String.format(UI_ELEMENT_SELECTOR_FORMAT, slotName))).getSize().getHeight();
  }

  public void verifyFandomTabOpened(String tabUrl) {Assertion.assertEquals(tabUrl, AD_REDIRECT_URL);
  }

  public boolean isVideoAdBiggerThanImageAdOasis(double videoHeight, double imageHeight) {
    int percentResult = (int)Math.round(100-(100/(videoHeight/imageHeight)));
    if (percentResult == PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD) {
      return true;
    }
    PageObjectLogging.log("isVideoAdBiggerThanImageAdOasis",
            "Expected percentage difference between video height and image height is not equal with "
            + PERCENTAGE_DIFFERENCE_BETWEEN_VIDEO_AND_IMAGE_AD + " percent", false, driver);
    return false;
  }

// Different way of checking slot sizes on mercury because of the very small difference between two slots sizes
  public boolean isVideoAdBiggerThanImageAdMobile(double videoHeight, double imageHeight) {
    if (videoHeight > imageHeight) {
      return true;
    }
    PageObjectLogging.log("isVideoAdBiggerThanImageAdMobile",
            "Ad image height = " + imageHeight + " is not smaller than video height = " + videoHeight, false, driver);
    return false;
  }

  public boolean isImageAdInCorrectSize(double imageHeight, String slotSelector) throws InterruptedException {
    long time = System.currentTimeMillis();
    long endTime = time+3000;
    while(time < endTime) {
      if (imageHeight == getAdSlotHeight(slotSelector)){
        return true;
      }
      TimeUnit.MILLISECONDS.sleep(200);
      time = System.currentTimeMillis();
    }
    PageObjectLogging.log("isImageAdInCorrectSize",
            "Image is not in correct, required size " + imageHeight + " is not equal with actual size " + getAdSlotHeight(slotSelector), false, driver);
    return false;
  }

  public boolean isTimeProgressing(double quartileTime, double midTime) {
    if (quartileTime < midTime){
      return true;
    }
    PageObjectLogging.log("isTimeProgressing",
            "Video time is not progressing, quartileTime " + quartileTime + " is not smaller than midTime " + midTime , false, driver);
    return false;
  }
}
