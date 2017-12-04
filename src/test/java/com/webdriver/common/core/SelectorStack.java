package com.webdriver.common.core;

import org.openqa.selenium.By;

import java.util.Stack;

public class SelectorStack {

  private static Stack<By> byStack = new Stack<>();
  private static boolean contextStack = false;


  public static void contextWrite() {
    contextStack=true;
  }

  public static void contextRead() {
    contextStack=false;
  }

  public static boolean isContextSet(){
    return contextStack;
  }

  public static By read() {
    return byStack.pop();
  }

  public static void write(By selector) {
    byStack.push(selector);
  }
}
