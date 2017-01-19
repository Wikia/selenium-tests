package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public class AutoplayVuap {

  private static final String PAUSE_CLASS_NAME = "pause-overlay";

  private static final String STOP_CLASS_NAME = "close-ad";

  private static final String PAUSE_BUTTON_SELECTOR_FORMAT = "#%s ." + PAUSE_CLASS_NAME;

  private static final String STOP_BUTTON_SELECTOR_FORMAT = "#%s ." + STOP_CLASS_NAME;

  private final WikiaWebDriver driver;

  private final Wait wait;

  private final String slot;

  private final String iframeSelector;

  public AutoplayVuap(WikiaWebDriver driver, String slot, String iframeSelector) {
    this.driver = driver;
    this.wait = new Wait(driver);

    this.slot = slot;
    this.iframeSelector = iframeSelector;
  }

  public void pause() {
    clickElement(String.format(PAUSE_BUTTON_SELECTOR_FORMAT, slot));
  }

  public void stop() {
    clickElement(String.format(STOP_BUTTON_SELECTOR_FORMAT, slot));
  }

  public boolean hasStarted() {
    final String currentTime = usingVideoContext(video -> video.getAttribute("currentTime"));
    return Double.parseDouble(currentTime) > 0;
  }

  public boolean isInvisible() {
    return usingVideoContext(video -> wait.forElementNotVisible(video));
  }

  public String findTitle() {
    return usingVideoContext(video -> video.getAttribute("title"));
  }

  private void clickElement(final String selector) {
    wait.forElementClickable(By.cssSelector(selector)).click();
  }

  private <T> T usingVideoContext(final Function<WebElement, T> fun) {
    return usingIframeContext(webDriver -> fun.apply(driver.findElement(By.tagName("video"))));
  }

  private <T> T usingIframeContext(final Function<WikiaWebDriver, T> fun) {
    final WebElement iframe = driver.findElement(By.cssSelector(iframeSelector));
    driver.switchTo().frame(iframe);
    final T result = fun.apply(driver);
    driver.switchTo().defaultContent();
    return result;
  }
}
