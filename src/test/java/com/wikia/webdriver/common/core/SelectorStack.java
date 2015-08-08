package com.wikia.webdriver.common.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Stack;

/**
 * Created by Ludwik on 2015-07-28.
 */
public class SelectorStack {

  private static Stack<By> byStack = new Stack<>();
  private static Stack<WebElement> contextStack = new Stack<>();


  public static void contextWrite(WebElement element) {
    contextStack.push(element);
  }

  public static WebElement contextRead() {
    return contextStack.pop();
  }

  public static boolean isContextSet(){
    return !contextStack.empty();
  }

  public static By read() {
    return byStack.pop();
  }

  public static void write(By selector) {
    byStack.push(selector);
  }

}
