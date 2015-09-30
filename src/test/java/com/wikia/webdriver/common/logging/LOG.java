package com.wikia.webdriver.common.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.core.url.UrlBuilder;

public class LOG {
  private static String reportPath = "." + File.separator + "logs" + File.separator;
  private static String logFileName = "log.html";

  private static final String JIRA_PATH = "https://wikia-inc.atlassian.net/browse/";
  private static final String LOG_PATH = reportPath + logFileName;
  private static final String SCREEN_DIR_PATH = reportPath + "screenshots" + File.separator;
  private static final String SCREEN_PATH = SCREEN_DIR_PATH + "screenshot";
  private static long logCounter = 0;
  private static long imageIndex = 0;
  private static ArrayList<Boolean> logsResults = new ArrayList<>();
  private static final int MAX_CONTENT_LENGTH = 160;

  private static boolean testStarted = false;

  private static void appendToReport(String command, String description, boolean success,
      boolean makeScreenshot) {
    Type classType = success ? Type.SUCCESS : Type.ERROR;
    appendToReport(command, description, classType, makeScreenshot);
  }

  private static void appendToReport(String command, String description, Type type,
      boolean makeScreenshot) {
    logCounter++;
    imageIndex++;
    logsResults.add(type.isError());
    String className = type.getType();
    StringBuilder builder = new StringBuilder();

    String transformDesc = description;
    if (new UrlValidator().isValid(description)) {
      transformDesc = "<a href='" + description + "'>" + description;
    }
    System.out.println("STRING LENGHTH: " + transformDesc.length());

    if (transformDesc.length() > MAX_CONTENT_LENGTH) {
      builder.append("<tr class=\"accordion-toggle " + className
          + "\" data-toggle=\"collapse\" data-target=\"#log" + logCounter
          + "\" class=\"accordion-toggle\"><td>" + command + "</td><td> CLICK TO SEE MORE </td>");
    } else {
      builder.append("<tr class=\"accordion-toggle " + className
          + "\" data-toggle=\"collapse\" data-target=\"#log" + logCounter
          + "\" class=\"accordion-toggle\"><td>" + command + "</td><td>" + transformDesc + "</td>");
    }
    if (makeScreenshot) {
      new Shooter().savePageScreenshot(SCREEN_PATH + imageIndex, TestContext.getWebDriver());
      CommonUtils.appendTextToFile(SCREEN_PATH + imageIndex + ".html", getPageSource());
      builder.append("<td> <br/><a href='screenshots/screenshot" + imageIndex
          + ".png'>Screenshot</a><br/><a href='screenshots/screenshot" + imageIndex
          + ".html'>HTML Source</a></td></tr>)");
    } else {
      builder.append("<td></td></tr>");
    }
    if (transformDesc.length() > MAX_CONTENT_LENGTH) {
      builder
          .append("<tr class=\""
              + className
              + "\"><td colspan=\"3\" class=\"hiddenRow\"><div class=\"accordian-body collapse\" id=\"log"
              + logCounter + "\">" + description + "</div></td><tr>");
    }
    CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
  }

  public static void stopLogging() {
    StringBuilder builder = new StringBuilder();
    builder.append("<tr class=\"step\">" + "<td>&nbsp</td><td>STOP LOGGING METHOD  "
        + "<div style=\"text-align:center\">" + "<a href=\"#toc\" style=\"color:blue\">"
        + "<b>BACK TO MENU</b></a></div> </td><td> <br/> &nbsp;</td></tr>" + "</tbody></table>");
    CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
    testStarted = false;
  }

  public static void start(Method testMethod) {
    StringBuilder builder = new StringBuilder();
    String testName = testMethod.getName();
    String className = testMethod.getDeclaringClass().getCanonicalName();

    builder.append("<table class=\"table table-condensed\" style=\"border-collapse:collapse;"
        + " margin: 0 auto; table-layout:fixed; width: 960px; word-wrap: break-word;\"><tbody>"
        + "<h3>Class: <em>" + className + "." + testName + " </em></h3>");
    if (testMethod.isAnnotationPresent(RelatedIssue.class)) {
      String issueID = testMethod.getAnnotation(RelatedIssue.class).issueID();
      String jiraUrl = JIRA_PATH + issueID;
      builder.append("<tr class=\"step\"><td>Known failure</td><td><h2><em>" + testName + " - "
          + "<a href=\"" + jiraUrl + "\">" + issueID + "</a> "
          + testMethod.getAnnotation(RelatedIssue.class).comment()
          + "</em></h2></td><td> <br/> &nbsp;</td></tr>");
    } else {
      builder.append("<tr class=\"step\"><td>&nbsp</td><td><h1><em>" + testName
          + "</em></h1></td><td> <br/> &nbsp;</td></tr>");
    }
    CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
    testStarted = true;
  }

  public static void log(String command, String description, Type logType) {
    appendToReport(command, description, logType, false);
  }

  public static void error(String command, String description) {
    appendToReport(command, description, Type.ERROR, true);
  }

  public static void error(String command, Exception exception) {
    error(command, exception.getMessage());
  }

  public static void warning(String command, String description) {
    log(command, description, Type.WARNING);
  }

  public static void warning(String command, Exception exception) {
    warning(command, exception.getMessage());
  }

  public static void info(String command, String description) {
    log(command, description, Type.INFO);
  }

  public static void info(String command, Exception exception) {
    log(command, exception, Type.INFO);
  }

  public static void success(String command, String description) {
    appendToReport(command, description, Type.SUCCESS, false);
  }

  public static void success(String command, String description, boolean makeScreenshot) {
    appendToReport(command, description, Type.SUCCESS, makeScreenshot);
  }

  /**
   * This method will log warning to log file (line in yellow color)
   */
  public static void logWarning(String command, String description) {
    log(command, description, Type.WARNING);
  }

  public static void log(String command, Throwable e, Type logType) {
    log(command, e.getMessage(), logType);
  }

  private static String getPageSource() {
    return TestContext.getWebDriver().getPageSource()
        .replaceAll("<script", "<textarea style=\"display: none\"><script")
        .replaceAll("</script", "</script></textarea");
  }

  public static void clearLogs() {
    logsResults.clear();
  }

  public static void logJSError() {
    if ("true".equals(Configuration.getJSErrorsEnabled())) {
      JavascriptExecutor js = (JavascriptExecutor) TestContext.getWebDriver();
      List<String> error =
          (ArrayList<String>) js.executeScript("return window.JSErrorCollector_errors.pump()");
      if (!error.isEmpty()) {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr class=\"error\"><td>click</td><td>" + error
            + "</td><td> <br/> &nbsp;</td></tr>");
        CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
      }
    }
  }

  public static void log(String command, String description, boolean success, WebDriver driver) {
    appendToReport(command, description, success, true);
    logJSError();
  }

  public static void log(String command, Throwable e, boolean success, WebDriver driver) {
    appendToReport(command, e.getMessage(), success, true);
    logJSError();
  }

  public static void log(String command, Throwable e, boolean success, boolean makeScreenshot) {
    appendToReport(command, e.getMessage(), success, true);
    logJSError();
  }

  public static void logResult(String command, String description, boolean success) {
    appendToReport(command, description, success, false);
  }

  public static void log(String command, Throwable e, boolean success) {
    logResult(command, e.getMessage(), success);
  }

  public static void log(String command, String descriptionOnSuccess, String descriptionOnFail,
      boolean success) {
    String description = descriptionOnFail;
    if (success) {
      description = descriptionOnSuccess;
    }
    logResult(command, description, success);
  }

  public static boolean isTestStarted() {
    return testStarted;
  }

  public static List<Boolean> getVerificationStack() {
    return logsResults;
  }

  public static void logImage(String command, File image, boolean success) {
    byte[] bytes = new byte[0];
    try {
      bytes = new Base64().encode(FileUtils.readFileToByteArray(image));
    } catch (IOException e) {
      logResult("logImage", e.getMessage(), false);
    }
    logImage(command, new String(bytes, StandardCharsets.UTF_8), success);
  }

  public static void logImage(String command, String imageAsBase64, boolean success) {
    imageAsBase64 = "<img src=\"data:image/png;base64," + imageAsBase64 + "\">";
    String className = success ? "success" : "error";
    CommonUtils.appendTextToFile(LOG_PATH, ("<tr class=\"" + className + "\"><td>" + command
        + "</td><td>" + imageAsBase64 + "</td><td> <br/> &nbsp;</td></tr>"));
  }

  public static void startReport(){
    CommonUtils.createDirectory(SCREEN_DIR_PATH);

    StringBuilder builder = new StringBuilder();
    builder
        .append("<html><style>"
                + ".hiddenRow {padding: 0;"
                + "} td:first-child {width:200px;}td:nth-child(2) {width:660px;}td:nth-child(3) "
                + "{width:100px;}tr.success{color:black;background-color:#CCFFCC;}"
                + "tr.warning{color:black;background-color:#FEE01E;}"
                + "tr.error{color:black;background-color:#FFCCCC;}"
                + "tr.step{color:white;background:grey}"
                + "</style><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
                + "<style>td { border-top: 1px solid grey; } </style></head><body>"
                + "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.8.3.min.js\"></script>"
                + "<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css\">\n"
                + "<script src=\"http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js\"></script>"
                + "<p>Date: "
                + DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ").print(
            DateTime.now(DateTimeZone.UTC))
                + "</p>"
                + "<p>Polish Time: "
                + DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss ZZ").print(
            DateTime.now().withZone(
                DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/Warsaw")))) + "</p>"
                + "<p>Browser: " + Configuration.getBrowser() + "</p>" + "<p>OS: "
                + System.getProperty("os.name") + "</p>" + "<p>Testing environment: "
                + new UrlBuilder().getUrlForWiki(Configuration.getWikiName()) + "</p>"
                + "<p>Testing environment: " + Configuration.getEnv() + "</p>" + "<p>Tested version: "
                + "TO DO: GET WIKI VERSION HERE" + "</p>" + "<div id='toc'></div>");
    CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
    appendShowHideButtons();
    try {
      FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
      String content = IOUtils.toString(input);
      CommonUtils.appendTextToFile(LOG_PATH, content);
    } catch (IOException e) {
      System.out.println("no script.txt file available");
    }
  }

  public static void finishReport(){
    CommonUtils.appendTextToFile(LOG_PATH, "</body></html>");
  }

  private static void appendShowHideButtons() {
    String hideButton = "<button id=\"hideLowLevel\">hide low level actions</button>";
    String showButton = "<button id=\"showLowLevel\">show low level actions</button>";
    StringBuilder builder = new StringBuilder();
    builder.append(hideButton);
    builder.append(showButton);
    CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
  }

  public enum Type {
    SUCCESS("success", true), WARNING("warning", true), INFO("info", true), ERROR("error", false);

    private String logType;
    private boolean isError;

    Type(String logType, boolean isError) {
      this.logType = logType;
      this.isError = isError;
    }

    public String getType() {
      return logType;
    }

    public boolean isError() {
      return isError;
    }
  }
}
