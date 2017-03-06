package com.wikia.webdriver.common.logging;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.SelectorStack;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class PageObjectLogging extends AbstractWebDriverEventListener implements ITestListener {

  public static final String SUCCESS_CLASS = "success";
  public static final String LOW_LEVEL_ACTION_CLASS = "lowLevelAction";
  private static final String ERROR_CLASS = "error";
  private static final String STACKTRACE_CLASS = "stacktrace";
  private static final String WARNING_CLASS = "warning";
  private static final String INFO_CLASS = "info";
  private static final String STEP_CLASS = "step";
  public static final String POLISH_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss ZZ";
  public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
  private static long imageCounter;
  private static String reportPath = "." + File.separator + "logs" + File.separator;
  private static String screenDirPath = reportPath + "screenshots" + File.separator;
  private static String screenPath = screenDirPath + "screenshot";
  private static String logFileName = "log.html";
  private static String logPath = reportPath + logFileName;
  private static String jiraPath = "https://wikia-inc.atlassian.net/browse/";
  private static ArrayList<Boolean> logsResults = new ArrayList<>();
  private static boolean testStarted = false;
  private By lastFindBy;
  private WebDriver driver;
  public static Throwable lastThrow = null;

  private static String getPageSource(WebDriver driver) {
    return driver.getPageSource().replaceAll("<script", "<textarea style=\"display: none\"><script")
        .replaceAll("</script", "</script></textarea");
  }

  public static void log(String command, String description, boolean success, WebDriver driver) {
    logsResults.add(success);
    imageCounter += 1;
    new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
    CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", getPageSource(driver));
    String className = success ? SUCCESS_CLASS : ERROR_CLASS;
    String html = VelocityWrapper.appendErrorLogRow(Arrays.asList(className), command, String.valueOf(imageCounter));
    CommonUtils.appendTextToFile(logPath, html);
    logJSError(driver);
  }

  public static void log(String command, Throwable e, boolean success, WebDriver driver) {
    logsResults.add(success);
    imageCounter += 1;
    new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
    CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", getPageSource(driver));
    String className = success ? SUCCESS_CLASS : ERROR_CLASS;
    String html = VelocityWrapper.appendErrorLogRow(Arrays.asList(className), command, String.valueOf(imageCounter));
    CommonUtils.appendTextToFile(logPath, html);
    logJSError(driver);
  }

  //Log assertion result
  public static void log(String command, String description, boolean success) {
    log(command, description, success, false);
  }

  public static void log(String command, Throwable e, boolean success) {
    log(command, e.getMessage(), success, false);
  }

  public static void log(String command, String descriptionOnSuccess, String descriptionOnFail,
      boolean success) {
    String description = descriptionOnFail;
    if (success) {
      description = descriptionOnSuccess;
    }
    log(command, description, success, false);
  }

  public static void log(String command, String descriptionOnSuccess, String descriptionOnFail,
      boolean success, boolean ifLowLevel) {
    String description = descriptionOnFail;
    if (success) {
      description = descriptionOnSuccess;
    }
    log(command, description, success, ifLowLevel);
  }

  /**
   * Log an action that is not user facing. Log file reader can hide these actions to increase test
   * readability
   */
  public static void logOnLowLevel(String command, String description, boolean success) {
    log(command, description, success, true);
  }

  //TODO: Check error class here
  private static void log(String command, String description, boolean isSuccess,
                   boolean ifLowLevel) {
    logsResults.add(isSuccess);
    String escapedDescription = escapeHtml(description);

    String className = isSuccess ? SUCCESS_CLASS : ERROR_CLASS;
//    if(!isSuccess){
//      lastThrow = new Throwable();
//      lastThrow.setStackTrace(Thread.currentThread().getStackTrace());
//    }
    List<String> classList = new ArrayList<>();
    classList.add(className);

    if (ifLowLevel)
      classList.add(LOW_LEVEL_ACTION_CLASS);
      String html = VelocityWrapper.appendLogRow(classList, command, escapedDescription);
      CommonUtils.appendTextToFile(logPath, html);
      logJSError(DriverProvider.getActiveDriver());

      if (!isSuccess) {
        lastThrow = new Throwable();
        lastThrow.setStackTrace(Thread.currentThread().getStackTrace());
//      logStacktrace();
      }
  }

  public void logAssertionStacktrace(AssertionError exception) {
    driver = DriverProvider.getActiveDriver();

    imageCounter += 1;
    if ("true".equals(Configuration.getLogEnabled())) {
      try {
        new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
        CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", getPageSource(driver));
      } catch (Exception e) {
        log("onException",
            "driver has no ability to catch screenshot or html source - driver may died", false);
      }
      String exceptionMessage = escapeHtml(exception.getMessage());
      //TODO: Check imageCounter

      List<String> classList = new ArrayList<>();
      classList.add(ERROR_CLASS);
      classList.add(STACKTRACE_CLASS);
      String
          html =
          VelocityWrapper
              .appendErrorLogRow(classList, exceptionMessage, String.valueOf(imageCounter));
      CommonUtils.appendTextToFile(logPath, html);
      logJSError(driver);
    }
  }

  public static void logError(String command, Exception exception) {
    log(command, exception.getMessage(), false);
  }

  public static void logWarning(String command, Exception exception) {
    logWarning(command, exception.getMessage());
  }

  /**
   * This method will log warning to log file (line in yellow color)
   */
  public static void logWarning(String command, String description) {
    String html = VelocityWrapper.appendLogRow(Arrays.asList(WARNING_CLASS), command, description);
    CommonUtils.appendTextToFile(logPath, html);
  }

  /**
   * This method will log info to log file (line in blue color)
   */
  public static void logInfo(String description) {
    String html = VelocityWrapper.appendLogRow(Arrays.asList(INFO_CLASS), "INFO", description);
    CommonUtils.appendTextToFile(logPath, html);
  }

  /**
   * This method will log info to log file (line in blue color)
   */
  public static void logInfo(String description, Throwable e) {
    String finalDescription = description + " : " + e.getMessage();
    String html = VelocityWrapper.appendLogRow(Arrays.asList(INFO_CLASS), "INFO", finalDescription);
    CommonUtils.appendTextToFile(logPath, html);
  }

  public static void logImage(String command, File image, boolean success) {
    byte[] bytes = new byte[0];
    try {
      bytes = new Base64().encode(FileUtils.readFileToByteArray(image));
    } catch (IOException e) {
      log("logImage", e.getMessage(), false);
    }
    logImage(command, new String(bytes, StandardCharsets.UTF_8), success);
  }
  //TODO: Check error class here
  public static void logImage(String command, String imageAsBase64, boolean success) {
    String imgHtml = VelocityWrapper.appendImage(imageAsBase64);
    String className = success ? SUCCESS_CLASS : ERROR_CLASS;
    String html = VelocityWrapper.appendLogRow(Arrays.asList(className), "INFO", imgHtml);
    CommonUtils.appendTextToFile(logPath, html);
  }

  private static void logJSError(WebDriver driver) {
    if (String.valueOf(true).equals(Configuration.getJSErrorsEnabled())) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      List<String> error =
          (ArrayList<String>) js.executeScript("return window.JSErrorCollector_errors.pump()");
      if (!error.isEmpty()) {
        //TODO:Check error to avoid toString method
        String html = VelocityWrapper.appendLogRow(Arrays.asList(ERROR_CLASS), "click", error.toString());
        CommonUtils.appendTextToFile(logPath, html);
      }
    }
  }

  public static List<Boolean> getVerificationStack() {
    return logsResults;
  }

  public static void start(Method testMethod) {
    String testName = testMethod.getName();
    String className = testMethod.getDeclaringClass().getCanonicalName();
    String command;
    String description;
    if (testMethod.isAnnotationPresent(RelatedIssue.class)) {
      String issueID = testMethod.getAnnotation(RelatedIssue.class).issueID();
      String jiraUrl = jiraPath + issueID;
      String jiraLink = VelocityWrapper.appendLink(jiraUrl, issueID);
      command = "Known failure";
      description = testName + " - " + jiraLink + " " + testMethod.getAnnotation(RelatedIssue.class).comment();
    } else {
      command = "";
      description = testName;
    }
    String html = VelocityWrapper.appendFirstLogRow(Arrays.asList(STEP_CLASS), className, testName, command, description);
    CommonUtils.appendTextToFile(logPath, html);
    testStarted = true;
  }

  public void stopLogging() {
    String html = VelocityWrapper.appendLastLogRow();
    CommonUtils.appendTextToFile(logPath, html);
    testStarted = false;
  }

  @Override
  public void beforeNavigateTo(String url, WebDriver driver) {
    new JavascriptActions(driver).execute("window.stop()");

    List<String> classList = new ArrayList<>();
    classList.add(SUCCESS_CLASS);
    String command = "Navigate to";
    String description = VelocityWrapper.appendLink(url, url);
    String html = VelocityWrapper.appendLogRow(classList, command, description);
    CommonUtils.appendTextToFile(logPath, html);
    logJSError(driver);
  }

  @Override
  public void afterNavigateTo(String url, WebDriver driver) {
    if (!AlertHandler.isAlertPresent(driver)) {
      if (url.equals(driver.getCurrentUrl())) {
        List<String> classList = new ArrayList<>();
        classList.add(SUCCESS_CLASS);
        String command = "Url after navigation";
        String description = VelocityWrapper.appendLink(driver.getCurrentUrl(), driver.getCurrentUrl());
        String html = VelocityWrapper.appendLogRow(classList, command, description);
        CommonUtils.appendTextToFile(logPath, html);
      } else {
        if (driver.getCurrentUrl().contains("data:text/html,chromewebdata ")) {
          driver.get(url);
        }
        logWarning("Url after navigation", driver.getCurrentUrl());
      }
    } else {
      logWarning("Url after navigation", "Unable to check URL after navigation - alert present");
    }

    if (driver.getCurrentUrl().contains(Configuration.getWikiaDomain())) {
      // HACK FOR DISABLING NOTIFICATIONS
      try {
        new JavascriptActions(driver).execute("$(\".sprite.close-notification\")[0].click()");
      } catch (WebDriverException e) {
        //TODO: Handle exception
      }

      /**
       * We want to disable sales pitch dialog for new potential contributors to avoid hiding other
       * UI elements. see https://wikia-inc.atlassian.net/browse/CE-3768
       */
      if ("true".equals(Configuration.getDisableCommunityPageSalesPitchDialog())) {
        driver.manage().addCookie(
            new Cookie("cpBenefitsModalShown", "1", Configuration.getWikiaDomain(), null, null));
      }

      if (TestContext.isFirstLoad() && "true".equals(Configuration.getMockAds())) {
        driver.manage().addCookie(new Cookie("mock-ads", XMLReader.getValue("mock.ads_token"),
           String.format(".%s", Configuration.getEnvType().getWikiaDomain()), null, null));
      }
    }

    Method method = TestContext.getCurrentTestMethod();
    Class<?> declaringClass = method.getDeclaringClass();

    if (TestContext.isFirstLoad()) {
      User user = null;
      TestContext.setFirstLoad(false);

      if (declaringClass.isAnnotationPresent(Execute.class)) {
        user = declaringClass.getAnnotation(Execute.class).asUser();
      }

      if (method.isAnnotationPresent(Execute.class)) {
        user = method.getAnnotation(Execute.class).asUser();
      }

      if (user != null && user != User.ANONYMOUS) {
        // log in, make sure user is logged in and flow is on the requested url
        new WikiBasePageObject().loginAs(user);
      }
    }

    logJSError(driver);
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

    logJSError(driver);
  }

  @Override
  public void afterClickOn(WebElement element, WebDriver driver) {
    List<String> classList = new ArrayList<>();
    classList.add(SUCCESS_CLASS);
    classList.add(LOW_LEVEL_ACTION_CLASS);
    String command = "click";
    //TODO:Check toString()
    String description = lastFindBy.toString();

    String html = VelocityWrapper.appendLogRow(classList, command, description);
    CommonUtils.appendTextToFile(logPath, html);
  }



  @Override
  public void afterChangeValueOf(WebElement element, WebDriver driver) {
    List<String> classList = new ArrayList<>();
    classList.add(SUCCESS_CLASS);
    classList.add(LOW_LEVEL_ACTION_CLASS);
    String command = "ChangeValueOfField";
    String description = lastFindBy.toString();
    String html = VelocityWrapper.appendLogRow(classList, command, description);
    CommonUtils.appendTextToFile(logPath, html);
  }

  @Override
  public void onTestStart(ITestResult result) {
    logsResults.clear();
    String testName = result.getName().toString();
    String className = result.getTestClass().getName().toString();
    System.out.println(className + " " + testName);
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    stopLogging();
  }

  @Override
  public void onTestFailure(ITestResult result) {
    driver = DriverProvider.getActiveDriver();

    imageCounter += 1;
    if ("true".equals(Configuration.getLogEnabled())) {
      try {
        new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
        CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", getPageSource(driver));
      } catch (Exception e) {
        log("onException",
            "driver has no ability to catch screenshot or html source - driver may died", false);
      }
      String exception = escapeHtml(result.getThrowable().toString() + "\n"
          + ExceptionUtils.getStackTrace(result.getThrowable()));
      //TODO: Check imageCounter

      List<String> classList = new ArrayList<>();
      classList.add(ERROR_CLASS);
//      classList.add(STACKTRACE_CLASS);
      String html = VelocityWrapper.appendErrorLogRow(classList, exception, String.valueOf(imageCounter));
      CommonUtils.appendTextToFile(logPath, html);
      logJSError(driver);
      stopLogging();
    }
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    if (!testStarted) {
      start(result.getMethod().getConstructorOrMethod().getMethod());
    }
    if (result.getMethod().getConstructorOrMethod().getMethod()
        .isAnnotationPresent(DontRun.class)) {
      log("Test SKIPPED", "this test is not supported in this environment", true);
      result.setStatus(ITestResult.SUCCESS);
      onTestSuccess(result);
    } else {
      result.setStatus(ITestResult.FAILURE);
      result.setThrowable(new SkipException("TEST SKIPPED"));
      onTestFailure(result);
    }
    if (testStarted) {
      stopLogging();
    }
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

  @Override
  public void onStart(ITestContext context) {
    CommonUtils.createDirectory(screenDirPath);
    imageCounter = 0;

    String date = DateTimeFormat.forPattern(DATE_FORMAT)
                  .print(DateTime.now(DateTimeZone.UTC));
    String polishDate = DateTimeFormat.forPattern(POLISH_DATE_FORMAT)
                        .print(DateTime.now()
                        .withZone(DateTimeZone
                        .forTimeZone(TimeZone.getTimeZone("Europe/Warsaw"))));
    String browser = Configuration.getBrowser();
    String os = System.getProperty("os.name");
    String testingEnvironmentUrl = new UrlBuilder().getUrlForWiki(Configuration.getWikiName());
    String testingEnvironment = Configuration.getEnv();
    String testedVersion = "TO DO: GET WIKI VERSION HERE";

    String
        headerHtml = VelocityWrapper
                     .appendHeader(date, polishDate, browser, os, testingEnvironmentUrl,
                                   testingEnvironment, testedVersion);
    CommonUtils.appendTextToFile(logPath, headerHtml);
    appendShowHideButtons();
    try {
      FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
      String content = IOUtils.toString(input);
      CommonUtils.appendTextToFile(logPath, content);
    } catch (IOException e) {
      System.out.println("no script.txt file available");
    }
  }

  private void appendShowHideButtons() {
    String hideButton = VelocityWrapper.appendButton("hideLowLevel", "hide low level actions");
    String showButton = VelocityWrapper.appendButton("showLowLevel", "show low level actions");
    StringBuilder builder = new StringBuilder();
    builder.append(hideButton);
    builder.append(showButton);
    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  @Override
  public void beforeNavigateBack(WebDriver driver) {
    PageObjectLogging.log("Navigate Back", "attempting to navigate back", true);
  }

  @Override
  public void afterNavigateBack(WebDriver driver) {
    PageObjectLogging.log("Navigate Back", "previous page loaded", true);
  }

  @Override
  public void beforeNavigateForward(WebDriver driver) {
    PageObjectLogging.log("Navigate Froward", "attempting to navigate forward", true);
  }

  @Override
  public void afterNavigateForward(WebDriver driver) {
    PageObjectLogging.log("Navigate Froward", "forward page loaded", true);
  }

  @Override
  public void onFinish(ITestContext context) {
    CommonUtils.appendTextToFile(logPath, "</body></html>");
  }
}
