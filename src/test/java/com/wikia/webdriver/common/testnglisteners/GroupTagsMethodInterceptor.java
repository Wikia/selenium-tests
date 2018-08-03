package com.wikia.webdriver.common.testnglisteners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.*;

public class GroupTagsMethodInterceptor implements IMethodInterceptor {

  @Override
  public List<IMethodInstance> intercept(
      List<IMethodInstance> methods, ITestContext context
  ) {
    List<IMethodInstance> result = new ArrayList<>();

    for (IMethodInstance m : methods) {
      Set<String> groups = new HashSet<>();

      groups.add(m.getMethod().getMethodName());

      if (m.getMethod()
          .getConstructorOrMethod()
          .getDeclaringClass()
          .isAnnotationPresent(Test.class)) {
        for (String group : m.getMethod()
            .getConstructorOrMethod()
            .getDeclaringClass()
            .getAnnotation(Test.class)
            .groups()) {
          groups.add(group);
        }
      }

      if (m.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(Test.class)) {
        for (String group : m.getMethod()
            .getConstructorOrMethod()
            .getMethod()
            .getAnnotation(Test.class)
            .groups()) {
          groups.add(group);
        }
      }

      if (!groups.isEmpty()) {
        for (Set<String> tagSet : TestFilters.getFilters()) {
          if (groups.containsAll(tagSet)) {
            result.add(0, m);
            break;
          }
        }
      }
    }

ko     return result;
  }
}
