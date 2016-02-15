package com.wikia.webdriver.common.core.elemnt;

import com.wikia.webdriver.common.contentpatterns.XSSContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Set of commonly used actions invoked by executing JavaScript on a web page
 */
public class JavascriptActions {

  private JavascriptExecutor js;
  private WebDriver webDriver;

  public JavascriptActions(WebDriver webDriver) {
    this.js = (JavascriptExecutor) webDriver;
    this.webDriver = webDriver;
  }

  public void click(String cssSelector) {
    js.executeScript("$('" + cssSelector + "').click()");
  }

  public void click(WebElement element) {
    js.executeScript("$(arguments[0])[0].click()", element);
  }

  public void focus(String cssSelector) {
    js.executeScript("$('" + cssSelector + "').focus()");
  }

  public void focus(WebElement element) {
    js.executeScript("$(arguments[0]).focus()", element);
  }

  public Object execute(String script) {
    // TODO: Get rid of this wait
    try {
      Object value = js.executeScript("return " + script);
      Thread.sleep(1000);
      return value;
    } catch (InterruptedException e) {
      PageObjectLogging.log("execute", e, false);
      return null;
    } catch (UnsupportedOperationException e) {
      PageObjectLogging.log("execute", e, true);
      return null;
    }
  }

  public void mouseOver(WebElement element) {
    js.executeScript("$(arguments[0]).mouseenter()", element);
  }

  public boolean isElementInViewPort(WebElement element) {
    return (Boolean) js
        .executeScript(
            "return ($(window).scrollTop() + 60 < $(arguments[0]).offset().top) && ($(window).scrollTop() "
            + "+ $(window).height() > $(arguments[0]).offset().top + $(arguments[0]).height() + 60)",
            element);
  }

  public void scrollToElement(By elementBy) {
    try {
      js.executeScript(
          "var x = $(arguments[0]);" + "window.scroll(0,parseInt(x.offset().top - 60));",
          webDriver.findElement(elementBy));
    } catch (WebDriverException e) {
      if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
        PageObjectLogging.log("JSError", "JQuery is not defined", false);
      }
    }
  }

  public void scrollToElement(WebElement element) {
    try {
      js.executeScript(
          "var x = $(arguments[0]); " +
          "window.scroll(0,parseInt(x.offset().top - 100));",
          element
      );
    } catch (WebDriverException e) {
      if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
        PageObjectLogging.log("JSError", "JQuery is not defined", false);
      }
    }
  }

  public void scrollToElement(WebElement element, int offset) {
    try {
      js.executeScript(
          "var x = $(arguments[0]);" +
          "window.scroll(0,parseInt(x.offset().top - arguments[1]));",
          element,
          offset
      );
    } catch (WebDriverException e) {
      if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
        PageObjectLogging.log("JSError", "JQuery is not defined", false);
      }
    }
  }

  public void scrollToElementInModal(WebElement element, WebElement modal) {
    int elementOffsetTop = Integer.parseInt(
        js.executeScript("return Math.round($(arguments[0]).offset().top)", element).toString());
    int modalOffsetTop = Integer.parseInt(
        js.executeScript("return Math.round($(arguments[0]).offset().top)", modal).toString());
    int scrollTop = elementOffsetTop - modalOffsetTop;

    js.executeScript("$(arguments[0]).scrollTop(arguments[1])", modal, scrollTop);
  }

  public void scrollElementIntoViewPort(WebElement element){
    if (!isElementInViewPort(element)) {
      scrollToElement(element);
    }
  }

  public void scrollBy(int x, int y) {
    js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
  }
}
