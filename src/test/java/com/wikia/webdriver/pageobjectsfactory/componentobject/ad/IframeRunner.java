package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class IframeRunner {
  private final WikiaWebDriver driver;
  private final Wait wait;

  IframeRunner(WikiaWebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
  }

  public <T> T usingIframeGet(By iframeSelector, final Supplier<T> f) {
    WebElement iframe = wait.forElementPresent(iframeSelector);
    return usingIframeGet(iframe, f);
  }

  private <T> T usingIframeGet(WebElement iframe, final Supplier<T> f) {
    driver.switchTo().frame(iframe);
    T result = f.get();
    driver.switchTo().defaultContent();
    return result;
  }

  public void usingIframe(By iframeSelector, Runnable f) {
    WebElement adIframe = wait.forElementPresent(iframeSelector);
    usingIframe(adIframe, f);
  }

  private void usingIframe(WebElement iframe, Runnable f) {
    driver.switchTo().frame(iframe);
    f.run();
    driver.switchTo().defaultContent();
  }
}