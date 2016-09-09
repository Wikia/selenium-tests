package com.wikia.webdriver.common.logging;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
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

public class PageObjectLogging extends AbstractWebDriverEventListener implements ITestListener {

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

  private static String getPageSource(WebDriver driver) {
    return driver.getPageSource().replaceAll("<script", "<textarea style=\"display: none\"><script")
        .replaceAll("</script", "</script></textarea");
  }

  public static void log(String command, String description, boolean success, WebDriver driver) {
    logsResults.add(success);
    imageCounter += 1;
    new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
    CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", getPageSource(driver));
    String className = success ? "success" : "error";
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"" + className + "\"><td>" + command + "</td><td>" + description
        + "</td><td> <br/><a href='screenshots/screenshot" + imageCounter
        + ".png'>Screenshot</a><br/><a href='screenshots/screenshot" + imageCounter
        + ".html'>HTML Source</a></td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
    logJSError(driver);
  }

  public static void log(String command, Throwable e, boolean success, WebDriver driver) {
    logsResults.add(success);
    imageCounter += 1;
    new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
    CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", getPageSource(driver));
    String className = success ? "success" : "error";
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"" + className + "\"><td>" + command + "</td><td>" + e.getMessage()
        + "</td><td> <br/><a href='screenshots/screenshot" + imageCounter
        + ".png'>Screenshot</a><br/><a href='screenshots/screenshot" + imageCounter
        + ".html'>HTML Source</a></td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
    logJSError(driver);
  }

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

  private static void log(String command, String description, boolean isSuccess,
      boolean ifLowLevel) {
    logsResults.add(isSuccess);
    String escapedDescription = escapeHtml(description);

    String className = isSuccess ? "success" : "error";
    StringBuilder builder = new StringBuilder();
    if (ifLowLevel) {
      builder.append("<tr class=\"" + className + " lowLevelAction" + "\"><td>" + command
          + "</td><td>" + escapedDescription + "</td><td> <br/> &nbsp;</td></tr>");
    } else {
      builder.append("<tr class=\"" + className + "\"><td>" + command + "</td><td>"
          + escapedDescription + "</td><td> <br/> &nbsp;</td></tr>");
    }
    CommonUtils.appendTextToFile(logPath, builder.toString());
    logJSError(DriverProvider.getActiveDriver());
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
    StringBuilder builder = new StringBuilder().append("<tr class=\"warning\">" + "<td>" + command
        + "</td>" + "<td>" + description + "</td>" + "<td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  /**
   * This method will log info to log file (line in blue color)
   */
  public static void logInfo(String description) {
    StringBuilder builder = new StringBuilder().append(
        "<tr class=\"info\"><td>INFO</td><td>" + description + "</td><td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  /**
   * This method will log info to log file (line in blue color)
   */
  public static void logInfo(String description, Throwable e) {
    StringBuilder builder = new StringBuilder().append("<tr class=\"info\"><td>INFO</td><td>"
        + description + " : " + e.getMessage() + "</td><td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
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

  public static void logImage(String command, String imageAsBase64, boolean success) {
    imageAsBase64 = "<img src=\"data:image/png;base64," + imageAsBase64 + "\">";
    String className = success ? "success" : "error";
    CommonUtils.appendTextToFile(logPath, ("<tr class=\"" + className + "\"><td>" + command
        + "</td><td>" + imageAsBase64 + "</td><td> <br/> &nbsp;</td></tr>"));
  }

  private static void logJSError(WebDriver driver) {
    if ("true".equals(Configuration.getJSErrorsEnabled())) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      List<String> error =
          (ArrayList<String>) js.executeScript("return window.JSErrorCollector_errors.pump()");
      if (!error.isEmpty()) {
        StringBuilder builder = new StringBuilder();
        builder.append(
            "<tr class=\"error\"><td>click</td><td>" + error + "</td><td> <br/> &nbsp;</td></tr>");
        CommonUtils.appendTextToFile(logPath, builder.toString());
      }
    }
  }

  public static List<Boolean> getVerificationStack() {
    return logsResults;
  }

  public static void start(Method testMethod) {
    StringBuilder builder = new StringBuilder();
    String testName = testMethod.getName();
    String className = testMethod.getDeclaringClass().getCanonicalName();

    builder.append("<table>" + "<h1>Class: <em>" + className + "." + testName + " </em></h1>");
    if (testMethod.isAnnotationPresent(RelatedIssue.class)) {
      String issueID = testMethod.getAnnotation(RelatedIssue.class).issueID();
      String jiraUrl = jiraPath + issueID;
      builder.append("<tr class=\"step\"><td>Known failure</td><td><h1><em>" + testName + " - "
          + "<a href=\"" + jiraUrl + "\">" + issueID + "</a> "
          + testMethod.getAnnotation(RelatedIssue.class).comment()
          + "</em></h1></td><td> <br/> &nbsp;</td></tr>");
    } else {
      builder.append("<tr class=\"step\"><td>&nbsp</td><td><h1><em>" + testName
          + "</em></h1></td><td> <br/> &nbsp;</td></tr>");
    }
    CommonUtils.appendTextToFile(logPath, builder.toString());
    testStarted = true;
  }

  public static void stopLogging() {
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"step\">" + "<td>&nbsp</td><td>STOP LOGGING METHOD  "
        + "<div style=\"text-align:center\">" + "<a href=\"#toc\" style=\"color:blue\">"
        + "<b>BACK TO MENU</b></a></div> </td><td> <br/> &nbsp;</td></tr>" + "</table>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
    testStarted = false;
  }

  @Override
  public void beforeNavigateTo(String url, WebDriver driver) {
    new JavascriptActions(driver).execute("window.stop()");
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"success\"><td>Navigate to</td><td>" + "<a href='" + url + "'>" + url
        + "</a></td><td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
    logJSError(driver);
  }

  @Override
  public void afterNavigateTo(String url, WebDriver driver) {
    StringBuilder builder = new StringBuilder();
    if (!AlertHandler.isAlertPresent(driver)) {
      if (url.equals(driver.getCurrentUrl())) {
        builder.append("<tr class=\"success\"><td>Url after navigation</td><td>" + "<a href='"
            + driver.getCurrentUrl() + "'>" + driver.getCurrentUrl()
            + "</a></td><td> <br/> &nbsp;</td></tr>");
        CommonUtils.appendTextToFile(logPath, builder.toString());
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
           Configuration.getEnvType().getWikiaDomain(), null, null));
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

      checkCountryCode(driver, StringUtils.defaultIfEmpty(Configuration.getCountryCode(),
          Configuration.getGeoEdgeCountry()));
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
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"success lowLevelAction\"><td>click</td><td>" + lastFindBy
        + "</td><td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  @Override
  public void afterChangeValueOf(WebElement element, WebDriver driver) {
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"success lowLevelAction\"><td>ChangeValueOfField</td><td>"
        + lastFindBy + "</td><td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
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

      StringBuilder builder = new StringBuilder();
      builder.append("<tr class=\"error\"><td>error</td><td><pre>" + exception
          + "</pre></td><td> <br/><a href='screenshots/screenshot" + imageCounter
          + ".png'>Screenshot</a><br/><a href='screenshots/screenshot" + imageCounter
          + ".html'>HTML Source</a></td></tr>");
      CommonUtils.appendTextToFile(logPath, builder.toString());
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
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    StringBuilder builder = new StringBuilder();
    builder.append("<html><style>"
        + "table {margin:0 auto;}td:first-child {width:200px;}td:nth-child(2) {width:660px;}td:nth-child(3) "
        + "{width:100px;}tr.success{color:black;background-color:#CCFFCC;}"
        + "tr.warning{color:black;background-color:#FEE01E;}"
        + "tr.error{color:black;background-color:#FFCCCC;}"
        + "tr.info{color:white;background-color:#78a1c0}" + "tr.step{color:white;background:grey}"
        + "</style><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
        + "<style>td { border-top: 1px solid grey; } </style></head><body>"
        + "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.8.2.min.js\"></script>"
        + "<p>Date: "
        + DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ").print(DateTime.now(DateTimeZone.UTC))
        + "</p>" + "<p>Polish Time: "
        + DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss ZZ")
            .print(DateTime.now()
                .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/Warsaw"))))
        + "</p>" + "<p>Browser: " + Configuration.getBrowser() + "</p>" + "<p>OS: "
        + System.getProperty("os.name") + "</p>" + "<p>Testing environment: "
        + new UrlBuilder().getUrlForWiki(Configuration.getWikiName()) + "</p>"
        + "<p>Testing environment: " + Configuration.getEnv() + "</p>" + "<p>Tested version: "
        + "TO DO: GET WIKI VERSION HERE" + "</p>" + "<div id='toc'></div>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
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
    String hideButton = "<button id=\"hideLowLevel\">hide low level actions</button>";
    String showButton = "<button id=\"showLowLevel\">show low level actions</button>";
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

  private void checkCountryCode(WebDriver driver, String expectedCountryCode) {
    if (StringUtils.isBlank(expectedCountryCode)) {
      return;
    }

    String actualCountryCode = new JavascriptActions(driver).getCountry();

    if (expectedCountryCode.equalsIgnoreCase(actualCountryCode)) {
      logInfo("Country code: " + actualCountryCode);
    } else {
      logWarning("Country code",
          String.format("Expected: %s, Actual: %s", expectedCountryCode, actualCountryCode));
    }
  }
}
