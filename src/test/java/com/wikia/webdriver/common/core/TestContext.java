package com.wikia.webdriver.common.core;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ludwik on 2015-08-05.
 */
public class TestContext {
  private static String methodName;
  private static Method testMethod;
  private static boolean isFirstLoad = false;
  public static HashMap<String, String> tokenCache= new HashMap<String, String>();

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
}
