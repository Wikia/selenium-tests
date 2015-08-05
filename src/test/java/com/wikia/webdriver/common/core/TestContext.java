package com.wikia.webdriver.common.core;

import com.sun.xml.internal.ws.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by Ludwik on 2015-08-05.
 */
public class TestContext {
  private static String methodName;

  public static void writeMethodName(Method method) {
    methodName = method.getDeclaringClass().getSimpleName() + StringUtils.capitalize(method.getName());
  }

  public static String getCurrentMethodName() {
    return methodName;
  }
}
