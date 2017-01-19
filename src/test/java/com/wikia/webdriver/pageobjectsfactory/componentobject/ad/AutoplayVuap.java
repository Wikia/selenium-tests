package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public class AutoplayVuap {

  private static final String PAUSE_CLASS_NAME = "pause-overlay";

  private final WikiaWebDriver driver;

  private final Wait wait;

  private final String iframeSelector;

  public AutoplayVuap(WikiaWebDriver driver, String iframeSelector) {
    this.driver = driver;
    this.wait = new Wait(driver);

    this.iframeSelector = iframeSelector;
  }

  public void pause() {
    wait.forElementClickable(By.className(PAUSE_CLASS_NAME)).click();
  }

  public boolean hasStarted() {
    final String currentTime = usingIframeContext(video -> video.getAttribute("currentTime"));
    return Double.parseDouble(currentTime) > 0;
  }

  public String findTitle() {
    return usingIframeContext(video -> video.getAttribute("title"));
  }

  private <T> T usingIframeContext(final Function<WebElement, T> fun) {
    final WebElement iframe = driver.findElement(By.cssSelector(iframeSelector));
    driver.switchTo().frame(iframe);
    final T result = fun.apply(driver.findElement(By.tagName("video")));
    driver.switchTo().defaultContent();
    return result;
  }
}
