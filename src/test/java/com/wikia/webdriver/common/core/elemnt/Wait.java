package com.wikia.webdriver.common.core.elemnt;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.SelectorStack;
import com.wikia.webdriver.common.logging.LOG;

/**
 * Created by Ludwik on 2015-07-22.
 */
public class Wait {

  /**
   * Checks if the element is present in browser DOM
   */

  private static final int DEFAULT_TIMEOUT = 30;
  private static final String INIT_MESSAGE = "INIT ELEMENT";
  private static final String INIT_ERROR_MESSAGE = "PROBLEM WITH ELEMENT INIT";
  private static final String ELEMENT_PRESENT_MESSAGE = "ELEMENT PRESENT";
  private static final String ELEMENT_PRESENT_ERROR_FORMAT = "PROBLEM WITH FINDING ELEMENT %s";

  private WebDriverWait wait;
  private WebDriver webDriver;

  public Wait(WebDriver webDriver) {
    this.webDriver = webDriver;
    this.wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
  }

  /**
   * Checks if the element is present in browser DOM
   */
  public WebElement forElementPresent(By by) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    } catch(TimeoutException e) {
      LOG.result(
          ELEMENT_PRESENT_MESSAGE,
          String.format(ELEMENT_PRESENT_ERROR_FORMAT, by.toString()),
          false
      );
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
      LOG.info(INIT_MESSAGE, e);
    }
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(ExpectedConditions.elementToBeClickable(element));
      } else {
        return forElementClickable(SelectorStack.read());
      }
    }finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementClickable(WebElement element, int timeout) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      LOG.info(INIT_MESSAGE, e);
    }
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
      }
      return new WebDriverWait(webDriver, timeout).until(ExpectedConditions
          .elementToBeClickable(element));
    }finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementClickable(List<WebElement> elements, int index, int timeout) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      elements.get(0).getTagName();
    } catch (WebDriverException e) {
      LOG.success(INIT_MESSAGE, INIT_ERROR_MESSAGE);
    }
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return new WebDriverWait(webDriver, timeout).until(ExpectedConditions
            .elementToBeClickable(elements.get(index)));
      } else {
        return forElementClickable(SelectorStack.read(), timeout);
      }
    }finally {
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
      return new WebDriverWait(webDriver, timeout).until(ExpectedConditions
          .elementToBeClickable(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is visible in browser
   * <p/>
   * * @param element The element to be checked
   */
  public WebElement forElementVisible(WebElement element) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      LOG.info(INIT_MESSAGE, e);
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
      return new WebDriverWait(webDriver, timeout, polling).until(ExpectedConditions
          .visibilityOf(element));
    } finally {
      restoreDeaultImplicitWait();
    }
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
      return new WebDriverWait(webDriver, timeout, polling).until(ExpectedConditions
          .visibilityOfElementLocated(by));
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
      return new WebDriverWait(webDriver, DEFAULT_TIMEOUT).until(ExpectedConditions
          .invisibilityOfElementLocated(by));
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
      return new WebDriverWait(webDriver, timeout, polling).until(ExpectedConditions
          .invisibilityOfElementLocated(by));
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
      LOG.info(INIT_MESSAGE, e);
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
      LOG.info(INIT_MESSAGE, e);
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

  private void restoreDeaultImplicitWait() {
    changeImplicitWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
  }

  private void changeImplicitWait(int value, TimeUnit timeUnit) {
    webDriver.manage().timeouts().implicitlyWait(value, timeUnit);
  }
}
