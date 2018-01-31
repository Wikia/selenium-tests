package com.wikia.webdriver.common.testnglisteners;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.exceptions.TestFailedException;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.lang.reflect.Method;
import java.util.List;


public class InvokeMethodAdapter implements IInvokedMethodListener {

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult result) {
    if (method.isTestMethod()) {
      List verificationFailures = Assertion.getVerificationFailures(result);
      if (PageObjectLogging.getVerificationStack().contains(false)) {
        result.setStatus(ITestResult.FAILURE);
        if (result.getThrowable() == null) {
          result.setThrowable(new TestFailedException(null));
        }
      }
      if (verificationFailures.size() > 0) {
        result.setStatus(ITestResult.FAILURE);
        for (Object failure : verificationFailures) {
          result.setThrowable((Throwable) failure);
        }
      }
    }
  }

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    if (method.isTestMethod()) {
      if (isTestExcludedFromEnv(method.getTestMethod().getConstructorOrMethod().getMethod())) {
        throw new SkipException("Test can't be run on " + Configuration.getEnv() + " environment");

      }
    }
  }

  /**
   * Returns true if test is excluded from running on current test environment
   */
  private boolean isTestExcludedFromEnv(Method method) {
    if (method.isAnnotationPresent(DontRun.class)) {
      String[] excludedEnvs = method.getAnnotation(DontRun.class).env();

      for (String excludedEnv : excludedEnvs) {
        if (Configuration.getEnv().contains(excludedEnv)) {
          return true;
        }
      }
    }
    return false;
  }
}
