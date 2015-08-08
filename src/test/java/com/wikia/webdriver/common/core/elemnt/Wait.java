package com.wikia.webdriver.common.core.elemnt;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.SelectorStack;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ludwik on 2015-07-22.
 */
public class Wait {

  /**
   * Checks if the element is present in browser DOM
   */

  private static final int DEFAULT_TIMEOUT = 30;

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
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is visible on browser <p/> * @param element The element to be checked
   */
  public WebElement forElementVisible(WebElement element) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      PageObjectLogging.log("INIT ELEMENT", "PROBLEM WITH ELEMENT INIT", true);
    }
    if(SelectorStack.isContextSet()){
      SelectorStack.contextRead();
      return wait.until(ExpectedConditions.visibilityOf(element));
    }else{
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

  private void restoreDeaultImplicitWait() {
    changeImplicitWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
  }

  private void changeImplicitWait(int value, TimeUnit timeUnit) {
    webDriver.manage().timeouts().implicitlyWait(value, timeUnit);
  }
}
