package com.wikia.webdriver.common.core;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Method;

/**
 * Created by Ludwik on 2015-08-05.
 */
public class TestContext {
  private static String methodName;
  private static Method testMethod;
  private static boolean isFirstLoad = false;
  private static WebDriver webdriver;

  public static void writeMethodName(Method method) {
    methodName =
        method.getDeclaringClass().getSimpleName() + StringUtils.capitalize(method.getName());

    testMethod = method;
    isFirstLoad = true;
  }

  public static String getCurrentMethodName() {
    return methodName;
  }

  public static Method getCurrentTestMethod(){
    return testMethod;
  }

  public static void setFirstLoad(boolean value){
    isFirstLoad = value;
  }

  public static boolean isIsFirstLoad(){
    return isFirstLoad;
  }

  public static void setDriver(WebDriver driver){
    webdriver = driver;
  }

  public static WebDriver getWebDriver(){
    return webdriver;
  }
}
