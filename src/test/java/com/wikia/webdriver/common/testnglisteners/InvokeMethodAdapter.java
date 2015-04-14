package com.wikia.webdriver.common.testnglisteners;

import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.exceptions.TestFailed;
import com.wikia.webdriver.common.logging.PageObjectLogging;


public class InvokeMethodAdapter implements IInvokedMethodListener {

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult result) {
    if (method.isTestMethod()) {
      List verificationFailures = Assertion.getVerificationFailures(result);
      // if there are verification failures...
      if (PageObjectLogging.getVerificationStack().contains(false)) {
        result.setStatus(ITestResult.FAILURE);
        result.setThrowable(new TestFailed());
      }
      if (verificationFailures.size() > 0) {
        // set the test to failed
        result.setStatus(ITestResult.FAILURE);
        for (Object failure : verificationFailures) {
          result.setThrowable((Throwable) failure);
        }
      }
    }
  }

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    // TODO Auto-generated method stub
  }
}
