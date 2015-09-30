package com.wikia.webdriver.common.core.elemnt;

import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Ludwik on 2015-07-27.
 */
public class JavascriptActions {

  private JavascriptExecutor js;

  public JavascriptActions(WebDriver webDriver) {
    this.js = (JavascriptExecutor) webDriver;
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
      LOG.error("execute", e);
      return null;
    }
  }

  public void mouseOver(WebElement element) {
    js.executeScript("$(arguments[0]).mouseenter()", element);
  }

  public boolean isElementInViewPort(WebElement element) {
    return (Boolean) js.executeScript(
        "return ($(window).scrollTop() + 60 < $(arguments[0]).offset().top) && ($(window).scrollTop() "
        + "+ $(window).height() > $(arguments[0]).offset().top + $(arguments[0]).height() + 60)",
        element);
  }
}
