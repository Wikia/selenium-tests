package com.wikia.webdriver.common.logging;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import java.lang.reflect.Method;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.core.SelectorStack;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class PageObjectLogging extends AbstractWebDriverEventListener implements ITestListener {
  private By lastFindBy;

  @Override
  public void beforeNavigateTo(String url, WebDriver driver) {
    LOG.info("Navigate to", url);
    LOG.logJSError();
  }

  @Override
  public void afterNavigateTo(String url, WebDriver driver) {
    if (!AlertHandler.isAlertPresent(driver)) {
      if (url.equals(driver.getCurrentUrl())) {
        LOG.info("Url after navigation", driver.getCurrentUrl());
      } else {
        if (driver.getCurrentUrl().contains("data:text/html,chromewebdata ")) {
          driver.get(url);
        }
        LOG.warning("Url after navigation", driver.getCurrentUrl());
      }
    } else {
      LOG.warning("Url after navigation", "Unable to check URL after navigation - alert present");
    }

    Method method = TestContext.getCurrentTestMethod();

    if (method.isAnnotationPresent(Execute.class) && TestContext.isIsFirstLoad()) {
      TestContext.setFirstLoad(false);
      User user = method.getAnnotation(Execute.class).asUser();
      if (user == User.ANONYMOUS) {
      } else {
        new WikiBasePageObject(driver).loginAs(user);
      }
    }

    LOG.logJSError();
  }

  @Override
  public void beforeFindBy(By by, WebElement element, WebDriver driver) {
    lastFindBy = by;
    SelectorStack.write(by);
    if (element != null) {
      SelectorStack.contextWrite();
    }
  }

  @Override
  public void beforeClickOn(WebElement element, WebDriver driver) {
    LOG.logJSError();
  }

  @Override
  public void afterClickOn(WebElement element, WebDriver driver) {
    LOG.info("CLICK", lastFindBy.toString());
  }

  @Override
  public void afterChangeValueOf(WebElement element, WebDriver driver) {
    LOG.info("CHANGE FIELD VALUE", lastFindBy.toString());
  }

  @Override
  public void onTestStart(ITestResult result) {
    LOG.clearLogs();
    String testName = result.getName().toString();
    String className = result.getTestClass().getName().toString();
    System.out.println(className + " " + testName);
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    LOG.stopLogging();
  }

  @Override
  public void onTestFailure(ITestResult result) {
      String exception =
          escapeHtml(result.getThrowable().toString() + "\n"
              + ExceptionUtils.getStackTrace(result.getThrowable()));
      LOG.error("ERROR", exception);
      LOG.logJSError();
      LOG.stopLogging();
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    if (!LOG.isTestStarted()) {
      LOG.start(result.getMethod().getConstructorOrMethod().getMethod());
    }
    if (result.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(DontRun.class)) {
      LOG.result("Test SKIPPED", "this test is not supported in this environment", true);
      result.setStatus(ITestResult.SUCCESS);
      onTestSuccess(result);
    } else {
      result.setStatus(ITestResult.FAILURE);
      result.setThrowable(new SkipException("TEST SKIPPED"));
      onTestFailure(result);
    }
    if (LOG.isTestStarted()) {
      LOG.stopLogging();
    }
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

  @Override
  public void beforeNavigateBack(WebDriver driver) {
    LOG.info("Navigate Back", "attempting to navigate back");
  }

  @Override
  public void afterNavigateBack(WebDriver driver) {
    LOG.info("Navigate Back", "previous page loaded");
  }

  @Override
  public void beforeNavigateForward(WebDriver driver) {
    LOG.info("Navigate Froward", "attempting to navigate forward");
  }

  @Override
  public void afterNavigateForward(WebDriver driver) {
    LOG.info("Navigate Froward", "forward page loaded");
  }

  @Override
  public void onStart(ITestContext context) {
    LOG.startReport();
  }

  @Override
  public void onFinish(ITestContext context) {
    LOG.finishReport();
  }
}
