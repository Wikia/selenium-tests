package com.wikia.webdriver.common.core.elemnt;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.SelectorStack;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import com.google.common.base.Predicate;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Wait {

  /**
   * Checks if the element is present in browser DOM
   */

  private static final int DEFAULT_TIMEOUT = 15;
  private static final String INIT_MESSAGE = "INIT ELEMENT";
  private static final String INIT_ERROR_MESSAGE = "PROBLEM WITH ELEMENT INIT";
  private static final String ELEMENT_PRESENT_MESSAGE = "ELEMENT PRESENT";
  private static final String ELEMENT_PRESENT_ERROR_FORMAT = "PROBLEM WITH FINDING ELEMENT %s";

  private WebDriverWait wait;
  private WebDriver driver;

  public Wait(WebDriver webDriver) {
    this.driver = webDriver;
    this.wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
  }

  public void forPageLoaded() {
    wait.until((Predicate<WebDriver>) arg0 -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
  }

  /**
   * Checks if the element is present in browser DOM
   */
  public WebElement forElementPresent(By by) {
    return forElementPresent(by, true);
  }

  /**
   * Checks if the element is present in browser DOM
   */
  public WebElement forElementPresent(By by, boolean failOnTimeout) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    } catch (TimeoutException e) {
      if (failOnTimeout) {
        PageObjectLogging.log(ELEMENT_PRESENT_MESSAGE,
                              String.format(ELEMENT_PRESENT_ERROR_FORMAT, by.toString()), false);
      }

      throw e;
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is present in browser DOM
   */
  public WebElement forElementPresent(By by, int timeout) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout).until(ExpectedConditions
                                                          .presenceOfElementLocated(by));
    } catch (TimeoutException e) {
      PageObjectLogging.log(ELEMENT_PRESENT_MESSAGE, e, false);
      throw e;
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is clickable in browser
   *
   * @param element The element to be checked
   */
  public WebElement forElementClickable(WebElement element) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      PageObjectLogging.log(INIT_MESSAGE, INIT_ERROR_MESSAGE, true);
    }
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(ExpectedConditions.elementToBeClickable(element));
      } else {
        return forElementClickable(SelectorStack.read());
      }
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementClickable(WebElement element, int timeout) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      PageObjectLogging.log(INIT_MESSAGE, INIT_ERROR_MESSAGE, true);
    }
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
      }
      return new WebDriverWait(driver, timeout).until(ExpectedConditions
                                                          .elementToBeClickable(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementClickable(List<WebElement> elements, int index, int timeout) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      elements.get(index).getTagName();
    } catch (WebDriverException e) {
      PageObjectLogging.log(INIT_MESSAGE, INIT_ERROR_MESSAGE, true);
    }
    try {
      SelectorStack.contextRead();
      return new WebDriverWait(driver, timeout).until(
          ExpectedConditions.elementToBeClickable(elements.get(index)));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is clickable on the browser
   */
  public WebElement forElementClickable(By by) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(ExpectedConditions.elementToBeClickable(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is clickable on the browser
   */
  public WebElement forElementClickable(By by, int timeout) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout).until(ExpectedConditions
                                                          .elementToBeClickable(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is visible in browser
   *
   * @param element The element to be checked
   */
  public WebElement forElementVisible(WebElement element) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      PageObjectLogging.log(INIT_MESSAGE, INIT_ERROR_MESSAGE, true);
    }
    if (SelectorStack.isContextSet()) {
      SelectorStack.contextRead();
      return wait.until(ExpectedConditions.visibilityOf(element));
    } else {
      return forElementVisible(SelectorStack.read());
    }
  }

  public WebElement forElementVisible(WebElement element, int timeout, int polling) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout, polling).until(ExpectedConditions
                                                                   .visibilityOf(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementVisible(WebElement element, int timeout) {
    return forElementVisible(element, timeout, 500);
  }

  /**
   * Checks if the element is visible on the browser
   */
  public WebElement forElementVisible(By by) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementVisible(By by, int timeout, int polling) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout, polling).until(
          ExpectedConditions.visibilityOfElementLocated(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to be either invisible or not present on the DOM.
   */
  public boolean forElementNotVisible(By by) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, DEFAULT_TIMEOUT).until(
          ExpectedConditions.invisibilityOfElementLocated(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to be either invisible or not present on the DOM.
   */
  public boolean forElementNotVisible(WebElement element) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, DEFAULT_TIMEOUT).until(
          CommonExpectedConditions.invisibilityOfElementLocated(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to be either invisible or not present on the DOM.
   */
  public boolean forElementNotVisible(By by, int timeout, int polling) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout, polling).until(
          ExpectedConditions.invisibilityOfElementLocated(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to be in viewport Either position top or left is bigger then -1
   */
  public boolean forElementInViewPort(WebElement element) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(CommonExpectedConditions.elementInViewPort(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forValueToBeNotPresentInElementsAttribute(
      WebElement element, String attribute, String value) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.valueToBeNotPresentInElementsAttribute(
          element, attribute, value));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to not be present in DOM
   */
  public boolean forElementNotPresent(By selector) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.elementNotPresent(selector));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextNotInElement(WebElement element, String text) {
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      PageObjectLogging.log(INIT_MESSAGE, INIT_ERROR_MESSAGE, true);
    }
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(CommonExpectedConditions.textToBeNotPresentInElement(element, text));
      } else {
        return forTextNotInElement(SelectorStack.read(), text);
      }
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forValueToBePresentInElementsAttribute (
      WebElement element, String attribute, String value) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(
          element, attribute, value));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextNotInElement(By by, String text) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.textToBeNotPresentInElement(by, text));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextInElement(By by, String text) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.textToBePresentInElement(by, text));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextInElement(By by, int index, String text) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.textToBePresentInElement(by, index, text));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextInElement(WebElement element, String text) {
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      PageObjectLogging.log(INIT_MESSAGE, INIT_ERROR_MESSAGE, true);
    }
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(CommonExpectedConditions.textToBePresentInElement(element, text));
      } else {
        return forTextInElement(SelectorStack.read(), text);
      }
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextInElement(List<WebElement> elements, int index, String text) {
    try {
      elements.get(0).getTagName();
    } catch (WebDriverException e) {
      PageObjectLogging.log(INIT_MESSAGE, INIT_ERROR_MESSAGE, true);
    }
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(CommonExpectedConditions.textToBePresentInElement(elements, index, text));
      } else {
        return forTextInElement(SelectorStack.read(), index, text);
      }
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forAttributeToContain(WebElement element, String attribute, String expectedValue) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions
                            .valueToBePresentInElementsAttribute(element, attribute,
                                                                 expectedValue));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for successful (http response code less than 400) response from specific service
   *
   * @param url Url which was used for making request
   */
  public void forSuccessfulResponse(final NetworkTrafficInterceptor networkTrafficInterceptor,
                                    final String url) {
    changeImplicitWait(0, TimeUnit.SECONDS);

    try {

      wait.until(
          new ExpectedCondition<Boolean>() {
            private HarEntry entry;

            @Override
            public Boolean apply(WebDriver webDriver) {
              entry = networkTrafficInterceptor.getEntryByUrlPart(url);
              return entry != null && entry.getResponse().getStatus() < 400;
            }

            @Override
            public String toString() {
              return entry == null ? String.format("sent request with url: %s", url) :
                     String.format(
                         "successful response (url: %s, status: %d)",
                         url,
                         entry.getResponse().getStatus()
                     );
            }
          }
      );

    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void forSuccessfulResponseByUrlPattern(final NetworkTrafficInterceptor trafficInterceptor,
                                                final String pattern) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      wait.until(
          new ExpectedCondition<Boolean>() {
            private HarEntry entry;

            @Override
            public Boolean apply(WebDriver webDriver) {
              entry = trafficInterceptor.getEntryByUrlPattern(pattern);
              return entry != null && entry.getResponse().getStatus() < 400;
            }

            @Override
            public String toString() {
              return entry == null ? String.format("sent request matching pattern: %s", pattern) :
                     String.format("successful response (pattern: %s)", pattern);
            }
          }
      );
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void forUrlContains(String text) {
    wait.until(ExpectedConditions.urlContains(text));
  }

  private void restoreDeaultImplicitWait() {
    changeImplicitWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
  }

  private void changeImplicitWait(int value, TimeUnit timeUnit) {
    driver.manage().timeouts().implicitlyWait(value, timeUnit);
  }

  /**
   * Wait for fixed time
   *
   * @param time - in milliseconds
   */
  public void forXMilliseconds(int time) {
    PageObjectLogging.logInfo("Wait for " + time + " ms");
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      PageObjectLogging.log("Wait.forXMilliseconds", e, false);
    }
  }
}
