package com.wikia.webdriver.common.logging;

import com.google.gson.Gson;

import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.Global;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

public class PageObjectLogging extends AbstractWebDriverEventListener implements ITestListener {

  private static long imageCounter;
  private static String reportPath = "." + File.separator + "logs" + File.separator;
  private static String screenDirPath = reportPath + "screenshots" + File.separator;
  private static String screenPath = screenDirPath + "screenshot";
  private static String logFileName = "log.html";
  private static String logPath = reportPath + logFileName;
  private static String jiraPath = "https://wikia-inc.atlassian.net/browse/";
  private static ArrayList<Boolean> logsResults = new ArrayList<>();
  private By lastFindBy;
  private WebDriver driver;
  private static Gson gson;
  private static String jsonFileName = "log.json";
  private static String jsonPath = reportPath + jsonFileName;
  private static TestLogging testLogging;
  private static TestSuiteLogging testSuiteLogging;

  public static void start() {
    testLogging = new TestLogging();
  }

  public static void clear() {
    testLogging = null;
  }

  public static void log(String command, String description, boolean success, WebDriver driver) {
    logsResults.add(success);
    imageCounter += 1;
    new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
    CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", driver.getPageSource());
    String className = success ? "success" : "error";
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"" + className + "\"><td>" + command + "</td><td>" + description
        + "</td><td> <br/><a href='screenshots/screenshot" + imageCounter
        + ".png'>Screenshot</a><br/><a href='screenshots/screenshot" + imageCounter
        + ".html'>HTML Source</a></td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
    logJSError(driver);

    String screenshotPath = screenPath + imageCounter + ".png";
    String htmlPath = screenPath + imageCounter + ".html";
    testLogging.addSteps(
        new TestStepsLogging(className, command, description, success, screenshotPath, htmlPath));
  }

  public static void log(String command, String description, boolean success) {
    log(command, description, success, false);
  }

  public static void log(String command, String descriptionOnSuccess, String descriptionOnFail, boolean success) {
    String description = descriptionOnFail;
    if(success) {
      description = descriptionOnSuccess;
    }
    log(command, description, success, false);
  }

  /**
   * This method will log warning to log file (line in yellow color)
   *
   * @param command
   * @param description
   */
  public static void logWarning(String command, String description) {
    StringBuilder builder = new StringBuilder().append("<tr class=\"warning\">"
                                                       + "<td>" + command + "</td>"
                                                       + "<td>" + description + "</td>"
                                                       + "<td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  private static void log(String command, String description, boolean success, boolean ifLowLevel) {
    logsResults.add(success);
    String escapedDescription = escapeHtml(description);

    String className = success ? "success" : "error";
    StringBuilder builder = new StringBuilder();
    if (ifLowLevel) {
      builder.append("<tr class=\"" + className + " lowLevelAction" + "\"><td>" + command
          + "</td><td>" + escapedDescription + "</td><td> <br/> &nbsp;</td></tr>");
    } else {
      builder.append("<tr class=\"" + className + "\"><td>" + command + "</td><td>"
          + escapedDescription + "</td><td> <br/> &nbsp;</td></tr>");
    }
    CommonUtils.appendTextToFile(logPath, builder.toString());
    logJSError(NewDriverProvider.getWebDriver());

    testLogging.addSteps(new TestStepsLogging(className, command, description, success));
  }


  public static void logImage(String command, File image, boolean success) {
    byte[] bytes = new byte[0];
    try {
      bytes = new Base64().encode(FileUtils.readFileToByteArray(image));
    } catch (IOException e) {
      log("logImage", e.getMessage(), false);
    }
    String imageAsBase64 = new String(bytes, StandardCharsets.UTF_8);
    imageAsBase64 = "<img src=\"data:image/png;base64," + imageAsBase64 + "\">";
    String className = success ? "success" : "error";
    CommonUtils.appendTextToFile(logPath, ("<tr class=\"" + className + "\"><td>" + command
                                           + "</td><td>" + imageAsBase64
                                           + "</td><td> <br/> &nbsp;</td></tr>"));

    testLogging.addSteps(new TestStepsLogging(className, command, imageAsBase64, success));
  }

  private static void logJSError(WebDriver driver) {
    if (Global.JS_ERROR_ENABLED) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      List<String> error =
          (ArrayList<String>) js.executeScript("return window.JSErrorCollector_errors.pump()");
      if (!error.isEmpty()) {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr class=\"error\"><td>click</td><td>" + error
            + "</td><td> <br/> &nbsp;</td></tr>");
        CommonUtils.appendTextToFile(logPath, builder.toString());
      }
    }
  }

  public static List<Boolean> getVerificationStack() {
    return logsResults;
  }

  @Override
  public void beforeNavigateTo(String url, WebDriver driver) {
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"success\"><td>Navigate to</td><td>" + url
        + "</td><td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
    logJSError(driver);

    testLogging.addSteps(new TestStepsLogging("success", "Navigate to", url, true));
  }

  @Override
  public void afterNavigateTo(String url, WebDriver driver) {
    StringBuilder builder = new StringBuilder();
    String status;
    if (!AlertHandler.isAlertPresent(driver)) {
      if (url.equals(driver.getCurrentUrl())) {
        builder.append("<tr class=\"success\"><td>Url after navigation</td><td>"
            + driver.getCurrentUrl() + "</td><td> <br/> &nbsp;</td></tr>");
        CommonUtils.appendTextToFile(logPath, builder.toString());

        status = "success";
      } else {
        builder.append("<tr class=\"warning\"><td>Url after navigation</td><td>"
            + driver.getCurrentUrl() + "</td><td> <br/> &nbsp;</td></tr>");
        CommonUtils.appendTextToFile(logPath, builder.toString());

        status = "warning";
        logWarning("Url after navigation", driver.getCurrentUrl());
      }
      testLogging.addSteps(new TestStepsLogging(status, "Url after navigation", driver.getCurrentUrl(), true));
    } else {
      builder
          .append("<tr class=\"warning\"><td>Url after navigation</td><td>Unable to check URL after navigation - alert present</td><td> <br/> &nbsp;</td></tr>");
      CommonUtils.appendTextToFile(logPath, builder.toString());

      status = "warning";
      testLogging.addSteps(new TestStepsLogging(status, "Url after navigation", "Unable to check URL after navigation - alert present", true));
      logWarning("Url after navigation", "Unable to check URL after navigation - alert present");
    }
    logJSError(driver);
  }

  @Override
  public void beforeFindBy(By by, WebElement element, WebDriver driver) {
    lastFindBy = by;
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

    testLogging.addSteps(new TestStepsLogging("success lowLevelAction", "click", lastFindBy.toString(), true));
  }

  @Override
  public void afterChangeValueOf(WebElement element, WebDriver driver) {
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"success lowLevelAction\"><td>ChangeValueOfField</td><td>"
                   + lastFindBy + "</td><td> <br/> &nbsp;</td></tr>");
    CommonUtils.appendTextToFile(logPath, builder.toString());

    testLogging.addSteps(new TestStepsLogging("success ChangeValueOfField", "click", lastFindBy.toString(), true));
  }

  @Override
  public void onTestStart(ITestResult result) {
    logsResults.clear();
    StringBuilder builder = new StringBuilder();
    String testName = result.getName().toString();
    String className = result.getTestClass().getName().toString();

    Method testMethod = result.getMethod().getConstructorOrMethod().getMethod();

    builder.append("<table>" + "<h1>Class: <em>" + className + "." + testName + " </em></h1>");

    testLogging.setClassName(className);
    testLogging.setTestName(testName);

    if (testMethod.isAnnotationPresent(RelatedIssue.class)) {
      String issueID = testMethod.getAnnotation(RelatedIssue.class).issueID();
      String jiraUrl = jiraPath + issueID;
      builder.append("<tr class=\"step\"><td>Known failure</td><td><h1><em>" + testName + " - "
                     + "<a href=\"" + jiraUrl + "\">" + issueID + "</a>"
                     + "</em></h1></td><td> <br/> &nbsp;</td></tr>");

      testLogging.setRelatedIssueID(issueID);
      testLogging.addSteps(new TestStepsLogging("step", "Known failure", jiraUrl, true));
    } else {
      builder.append("<tr class=\"step\"><td>&nbsp</td><td><h1><em>" + testName
                     + "</em></h1></td><td> <br/> &nbsp;</td></tr>");

      testLogging.addSteps(new TestStepsLogging("step", "", testName, true));
    }
    CommonUtils.appendTextToFile(logPath, builder.toString());
    System.out.println(className + " " + testName);
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"step\">" + "<td>&nbsp</td><td>STOP LOGGING METHOD  "
                   + "<div style=\"text-align:center\">" + "<a href=\"#toc\" style=\"color:blue\">"
                   + "<b>BACK TO MENU</b></a></div> </td><td> <br/> &nbsp;</td></tr>" + "</table>");
    CommonUtils.appendTextToFile(logPath, builder.toString());

    testSuiteLogging.addTest(testLogging);
  }

  @Override
  public void onTestFailure(ITestResult result) {
    driver = NewDriverProvider.getWebDriver();
    if (driver == null) {
      driver = NewDriverProvider.getWebDriver();
    }

    imageCounter += 1;
    if (Global.LOG_ENABLED) {
      try {
        new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
        CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", driver.getPageSource());
      } catch (Exception e) {
        log("onException",
            "driver has no ability to catch screenshot or html source - driver may died", false);
      }

      String exception =
          escapeHtml(result.getThrowable().toString() + "\n"
              + ExceptionUtils.getStackTrace(result.getThrowable()));

      StringBuilder builder = new StringBuilder();
      builder.append("<tr class=\"error\"><td>error</td><td>" + exception
          + "</td><td> <br/><a href='screenshots/screenshot" + imageCounter
          + ".png'>Screenshot</a><br/><a href='screenshots/screenshot" + imageCounter
          + ".html'>HTML Source</a></td></tr>");
      CommonUtils.appendTextToFile(logPath, builder.toString());

      String screenshotPath = screenPath + imageCounter + ".png";
      String htmlPath = screenPath + imageCounter + ".html";

      testLogging.addSteps(
          new TestStepsLogging("error", "error", exception, false, screenshotPath, htmlPath));

      logJSError(driver);
      onTestSuccess(result);
    }
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    if(result.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(DontRun.class)) {
      log("Test SKIPPED", "this test is not supported in this environment", true);
      result.setStatus(ITestResult.SUCCESS);
      onTestSuccess(result);
    } else {
      result.setStatus(ITestResult.FAILURE);
      result.setThrowable(new SkipException("TEST SKIPPED"));
      onTestFailure(result);
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
    builder
        .append("<html><style>"
                + "table {margin:0 auto;}td:first-child {width:200px;}td:nth-child(2) {width:660px;}td:nth-child(3) "
                + "{width:100px;}tr.success{color:black;background-color:#CCFFCC;}"
                + "tr.warning{color:black;background-color:#FEE01E;}"
                + "tr.error{color:black;background-color:#FFCCCC;}"
                + "tr.step{color:white;background:grey}"
                + "</style><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
                + "<style>td { border-top: 1px solid grey; } </style></head><body>"
                + "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.8.2.min.js\"></script>"
                + "<p>Date: "
                + DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ").print(
            DateTime.now(DateTimeZone.UTC))
                + "</p>"
                + "<p>Polish Time: "
                + DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss ZZ").print(
            DateTime.now().withZone(
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/Warsaw")))) + "</p>"
                + "<p>Browser: " + Global.BROWSER + "</p>" + "<p>OS: " + System
                    .getProperty("os.name")
                + "</p>" + "<p>Testing environment: " + Global.DOMAIN + "</p>"
                + "<p>Testing environment: " + Global.LIVE_DOMAIN + "</p>" + "<p>Tested version: "
                + Global.WIKI_VERSION + "</p>" + "<div id='toc'></div>");
    CommonUtils.appendTextToFile(logPath, builder.toString());
    appendShowHideButtons();

    try {
      FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
      String content = IOUtils.toString(input);
      CommonUtils.appendTextToFile(logPath, content);
    } catch (IOException e) {
      System.out.println("no script.txt file available");
    }

    testSuiteLogging = new TestSuiteLogging(
        DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ").print(DateTime.now(DateTimeZone.UTC)),
        DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss ZZ").print(DateTime.now().withZone(
            DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/Warsaw")))),
        Global.BROWSER,
        System.getProperty("os.name"),
        Global.DOMAIN,
        Global.LIVE_DOMAIN,
        Global.WIKI_VERSION
    );
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
  public void onFinish(ITestContext context) {
    CommonUtils.appendTextToFile(logPath, "</body></html>");

    Gson gson = new Gson();
    CommonUtils.appendTextToFile(jsonPath, gson.toJson(testSuiteLogging));
  }
}
