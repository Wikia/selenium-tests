package com.wikia.webdriver.common.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WindowHandler {

  private WebDriver driver;

  public WindowHandler(WebDriver driver){
    this.driver = driver;
  }
  
  public Boolean isScrollBarPresent() {
    String scrollbarPresent =
        "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
    boolean scorllBarPresent = (boolean) ((JavascriptExecutor) this.driver).executeScript(scrollbarPresent);
    return scorllBarPresent;
  }
  
}
