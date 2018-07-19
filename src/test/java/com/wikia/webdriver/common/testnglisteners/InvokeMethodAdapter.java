package com.wikia.webdriver.common.testnglisteners;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.RunOnly;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.exceptions.TestFailedException;
import com.wikia.webdriver.common.logging.Log;
import java.lang.reflect.Method;
import java.util.List;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class InvokeMethodAdapter implements IInvokedMethodListener {

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult result) {
    if (method.isTestMethod()) {
      List verificationFailures = Assertion.getVerificationFailures(result);
      if (Log.getVerificationStack().contains(false)) {
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
    } else {
      if (result.getStatus() == ITestResult.FAILURE) {
        Log.logError("TEST CONFIGURATION FAILED", result.getThrowable());
      }
    }
  }

  @Override
  public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult testResult) {
    Method method = invokedMethod.getTestMethod().getConstructorOrMethod().getMethod();
    if (invokedMethod.isTestMethod()) {
      if (isTestExcludedFromEnv(method)) {
        throw new SkipException("Test can't be run on " + Configuration.getEnv() + " environment");
      } else if (isTestExcludedFromLang(method)) {
        throw new SkipException(
            "Test can't be run with " + Configuration.getWikiLanguage() + " language");
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
        if (Configuration.getEnv().equals(excludedEnv)) {
          return true;
        }
      }
    }
    if (method.isAnnotationPresent(RunOnly.class)) {
      String[] includedEnvs = method.getAnnotation(RunOnly.class).env();

      for (String includedEnv : includedEnvs) {
        if (Configuration.getEnv().equals(includedEnv)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Returns true if test is excluded from running with current language
   */
  private boolean isTestExcludedFromLang(Method method) {
    if (method.isAnnotationPresent(DontRun.class)) {
      String[] excludedLangs = method.getAnnotation(DontRun.class).language();

      for (String excludedLang : excludedLangs) {
        if (Configuration.getWikiLanguage().contains(excludedLang)) {
          return true;
        }
      }
    }
    if (method.isAnnotationPresent(RunOnly.class)) {
      String[] includedLangs = method.getAnnotation(RunOnly.class).language();

      for (String includedLang : includedLangs) {
        if (Configuration.getWikiLanguage().contains(includedLang)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
}
