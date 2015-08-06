package com.wikia.webdriver.common.core;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Ludwik on 2015-08-05.
 */
public class TestContext {
  private static String methodName;

  public static void writeMethodName(Method method) {
    methodName =
        method.getDeclaringClass().getSimpleName() + StringUtils.capitalize(method.getName());
  }

  public static String getCurrentMethodName() {
    return methodName;
  }
}
