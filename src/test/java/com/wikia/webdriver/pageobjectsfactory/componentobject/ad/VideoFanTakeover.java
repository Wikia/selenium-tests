package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class VideoFanTakeover extends AutoplayVuap {
  private static final String MOBILE_VIDEO_SELECTOR_FORMAT = "#%s .video-player video";
  private static final String UI_ELEMENT_SELECTOR_FORMAT = "#%s .pause-overlay";
  private static final String UI_ELEMENT_CLOSE_BUTTON_FORMAT = "#%s .close-ad";
  private static final int GLOBAL_NAV_HEIGHT = 60;
  private static final String AD_REDIRECT_URL = "http://fandom.wikia.com/";
  private static By playTriggerButtonSelector = By.id("button");
  private final Wait wait;
  private final String slotName;
  private WikiaWebDriver driver;
  private WebElement iframe;

  public VideoFanTakeover(WikiaWebDriver driver, String iframeId, String slotName) {
    this(driver, By.id(iframeId), slotName);
  }

  private VideoFanTakeover(WikiaWebDriver driver, By iframeSelector, String slotName) {
    super(driver,slotName, iframeSelector, false);

    this.wait = new Wait(driver);
    this.driver = driver;
    this.slotName = slotName;
  }


  public WebElement getIframe() {
    return iframe;
  }

  private WebElement getVideoCloseButton(String slotName) {
   return wait.forElementVisible(By.cssSelector(String.format(UI_ELEMENT_CLOSE_BUTTON_FORMAT, slotName)));
  }

  public void togglePause() {
    driver.findElement(By.cssSelector(String.format(UI_ELEMENT_SELECTOR_FORMAT, slotName))).click();
  }

  private void runInAdFrame(Runnable f) {
    driver.switchTo().frame(iframe);
    f.run();
    driver.switchTo().defaultContent();
  }

  public void waitForVideoStart() {
    wait.forElementVisible(By.cssSelector(String.format(UI_ELEMENT_SELECTOR_FORMAT, slotName)));
    Log.log("waitForVideoStart", "video started", true, driver);
  }

  public void waitForVideoPlayerHidden() {
    wait.forElementNotVisible(By.cssSelector(String.format(UI_ELEMENT_SELECTOR_FORMAT, slotName)));
    Log.log("waitForVideoPlayerHidden", "video ended, video hidden", true, driver);
  }

  public void waitForAdToLoad() {
    runInAdFrame(() -> wait.forElementClickable(playTriggerButtonSelector));
  }

  public void clickOnAdImage(){
    runInAdFrame(() -> {
      final By adImageTrigger = By.cssSelector("#adContainer a");
      wait.forElementClickable(adImageTrigger);
      //do not click in the middle of image, there might be click to play there
      new Actions(driver).moveToElement(driver.findElement(adImageTrigger), 10, 10).click().build().perform();
      Log.log("clickOnAdImage", "ad image clicked", true, driver);
    });
  }

  public void clickOnVideoCloseButton() {
    scrollToAdVideo(getVideoCloseButton(slotName));

    getVideoCloseButton(slotName).click();
    Log.log("clickOnVideoCloseButton", "close video button clicked", true, driver);
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

// Different way of checking slot sizes on mercury because of the very small difference between two slots sizes
  public boolean isVideoAdBiggerThanImageAdMobile(double videoHeight, double imageHeight) {
    if (videoHeight > imageHeight) {
      return true;
    }
    Log.log("isVideoAdBiggerThanImageAdMobile",
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
    Log.log("isImageAdInCorrectSize",
            "Image is not in correct, required size " + imageHeight + " is not equal with actual size " + getAdSlotHeight(slotSelector), false, driver);
    return false;
  }

  public boolean isTimeProgressing(double quartileTime, double midTime) {
    if (quartileTime < midTime){
      return true;
    }
    Log.log("isTimeProgressing",
            "Video time is not progressing, quartileTime " + quartileTime + " is not smaller than midTime " + midTime , false, driver);
    return false;
  }

  private void scrollToAdVideo(WebElement element) {
    JavascriptActions javascriptActions = new JavascriptActions(driver);
    javascriptActions.scrollToElement(element, GLOBAL_NAV_HEIGHT);
  }
}
