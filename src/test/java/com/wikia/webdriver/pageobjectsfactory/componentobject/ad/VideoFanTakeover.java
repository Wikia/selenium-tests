package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.contentpatterns.AdsContent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class VideoFanTakeover {
  private static final int VIDEO_LENGTH = 6000;
  private static final String VIDEO_IFRAME_SELECTOR = "#%s .video-ima-container iframe";
  private static final String VIDEO_CONTAINER_SELECTOR = "#%s .video-ima-container.hidden";
  private static final String UI_ELEMENT_SELECTOR = "#%s .overVideoLayer";
  private static final String FANDOM_URL = "http://www.wikia.com/fandom";
  private static By playTriggerButtonSelector = By.id("button");
  private static By closeVideoButton = By.className("close-ad");
  private final Wait wait;
  private WikiaWebDriver driver;
  private WebElement iframe;
  private WebElement playTriggerButton;

  public VideoFanTakeover(WikiaWebDriver driver, String iframeId) {
    this.wait = new Wait(driver);
    this.driver = driver;
    setIframe(iframeId);
    setTriggerButton(driver);
  }

  private void setIframe(String iframeId) {
    By iframeSelector = By.id(iframeId);
    wait.forElementPresent(iframeSelector);
    iframe = driver.findElement(iframeSelector);
  }

  private void setTriggerButton(WikiaWebDriver driver) {
    runInAdFrame(() -> {
      wait.forElementClickable(playTriggerButtonSelector);
      playTriggerButton = driver.findElement(playTriggerButtonSelector);
    });
  }

  public WebElement getIframe() {
    return iframe;
  }

  public void play() {
    runInAdFrame(() -> wait.forElementClickable(playTriggerButtonSelector).click());
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

  public void waitForVideoPlayerVisible(String slotName) {
    wait.forElementVisible(By.cssSelector(String.format(UI_ELEMENT_SELECTOR, slotName)));
  }

  public void waitForVideoPlayerHidden(String slotName) {
    wait.forElementNotVisible(By.cssSelector(String.format(UI_ELEMENT_SELECTOR, slotName)));
  }

  public void waitforAdToLoad() {
    runInAdFrame(() -> wait.forElementClickable(playTriggerButtonSelector));
  }

  public void waitForVideoEnd(String slotName) {
    wait.forElementPresent(By.cssSelector(String.format(VIDEO_CONTAINER_SELECTOR, slotName)), VIDEO_LENGTH);
  }

  public void clickOnAdImage(){
    runInAdFrame(() -> {
    Actions action = new Actions(driver);
    action.moveToElement(wait.forElementClickable(playTriggerButtonSelector), -20, -20).click().build().perform();
    });
  }

  public void waitForVideoStart(String slotName) { waitForVideoPlayerVisible(slotName); }

  public void clickOnVideoCloseButon() { wait.forElementVisible(closeVideoButton).click(); }

  public Double getCurrentVideoTime(String slotName) {
    String result;

    driver.switchTo().frame(driver.findElement(By.cssSelector(String.format(VIDEO_IFRAME_SELECTOR, slotName))));
    result = driver.findElement(By.cssSelector("video")).getAttribute("currentTime");
    driver.switchTo().defaultContent();

    return Double.parseDouble(result);
  }

  public double getAdSlotHigh(String slotName) {
    return driver.findElement(By.cssSelector(AdsContent.getSlotSelector(slotName))).getSize().getHeight();
  }

  public double getAdVideoHigh(String slotName) {
    return driver.findElement(By.cssSelector(String.format(UI_ELEMENT_SELECTOR , slotName))).getSize().getHeight();
  }

  public void verifyFandomTabOpened(String tabUrl) {
    Assertion.assertEquals(tabUrl, FANDOM_URL);
  }

  public void verifyVideoHasBigerSizeThenAdImage(double videoHeight, double imageHeight, String slotName) {
    int percentResult = (int)Math.round(100-(100/(videoHeight/imageHeight)));
    Assertion.assertNumber(percentResult, expectedSlotSize(slotName), "Video ad is 23 percent bigger then image ad");
  }

  public void verifyAdImageHasRequiredSize (double imageHeight, String slotName) {
    Assertion.assertEquals(imageHeight, getAdSlotHigh(slotName));
  }

  public int expectedSlotSize(String slotName) {
    int percent = 0;
    switch(slotName) {
      case AdsContent.TOP_LB:
        percent = 23;
        break;
      case AdsContent.BOTTOM_LB:
        percent = 23;
        break;
      case AdsContent.MOBILE_TOP_LB:
        percent = 0;
        break;
      case AdsContent.MOBILE_BOTTOM_LB:
        percent = 0;
        break;
    }
    return percent;
  }

  public boolean isTimeProgressing(double quartileTime, double midTime) {
    if (quartileTime < midTime){
      return true;
    }
    return false;
  }
}
