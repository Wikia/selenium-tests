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

import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.core.url.UrlBuilder;

public class LOG {
  private static final String JIRA_PATH = "https://wikia-inc.atlassian.net/browse/";
  private static final int MAX_CONTENT_LENGTH = 160;
  private static String reportPath = "." + File.separator + "logs" + File.separator;
  private static final String SCREEN_DIR_PATH = reportPath + "screenshots" + File.separator;
  private static final String SCREEN_PATH = SCREEN_DIR_PATH + "screenshot";
  private static String logFileName = "log.html";
  private static final String LOG_PATH = reportPath + logFileName;
  private static long logCounter = 0;
  private static ArrayList<Boolean> logsResults = new ArrayList<>();
  private static boolean testStarted = false;
  private static boolean allowLongDesc = false;

  private static void appendToReport(String command, String description, boolean success,
      boolean makeScreenshot) {
    Type classType = success ? Type.SUCCESS : Type.ERROR;
    appendToReport(command, description, classType, makeScreenshot);
  }

  private static void appendToReport(String command, String description, Type type,
      boolean makeScreenshot) {
    logCounter++;
    logsResults.add(type.isError());
    String className = type.getType();
    StringBuilder builder = new StringBuilder();

    String transformDesc = description;
    if (new UrlValidator().isValid(description)) {
      transformDesc = "<a href='" + description + "'>" + description;
    }
    boolean isDescriptionTooLong = (transformDesc.length() > MAX_CONTENT_LENGTH) && !allowLongDesc;

    if (isDescriptionTooLong) {
      builder.append("<tr class=\"accordion-toggle " + className
          + "\" data-toggle=\"collapse\" data-target=\"#log" + logCounter
          + "\" class=\"accordion-toggle\"><td><div class=\"" + type.getIconClass()
          + "\" style=\"padding: 0 10px 0 0\"></div>" + command
          + "</td><td> CLICK TO SEE MORE </td>");
    } else {
      builder.append("<tr class=\"accordion-toggle " + className
          + "\" data-toggle=\"collapse\" data-target=\"#log" + logCounter
          + "\" class=\"accordion-toggle\"><td><div class=\"" + type.getIconClass()
          + "\" style=\"padding: 0 10px 0 0\"></div>" + command + "</td><td>" + transformDesc
          + "</td>");
    }
    if (makeScreenshot) {
      new Shooter().savePageScreenshot(SCREEN_PATH + logCounter, TestContext.getWebDriver());
      CommonUtils.appendTextToFile(SCREEN_PATH + logCounter + ".html", getPageSource());
      builder.append("<td><a href='screenshots/screenshot" + logCounter
          + ".png'>Screenshot</a><br/><a href='screenshots/screenshot" + logCounter
          + ".html'>HTML Source</a></td></tr>");
    } else {
      builder.append("<td></td></tr>");
    }
    if (isDescriptionTooLong) {
      builder
          .append("<tr class=\""
              + className
              + "\"><td colspan=\"3\" class=\"hiddenRow\" style=\"padding: 0 20px;\"><div class=\"accordian-body collapse\" id=\"log"
              + logCounter + "\">" + description + "</div></td><tr>");
    }
    allowLongDesc = false;
    CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
  }

  public static void stopLogging() {
    StringBuilder builder = new StringBuilder();
    builder
        .append("<tr class=\"step\">"
                + "<td>&nbsp</td><td>"
                + "<div style=\"text-align:center\">"
                + "<a href=\"#toc\" style=\"color:blue\">"
                + "<button class=\"btn btn-info\" style=\"margin: auto\"><div class=\"icon-arrow-up icon-white\" style=\"margin: 0 20px 0 0\"></div>BACK TO MENU</button></a></div> </td><td> <br/> &nbsp;</td></tr>"
                + "</tbody></table>");
    CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
    testStarted = false;
  }

  public static void start(Method testMethod) {
    StringBuilder builder = new StringBuilder();
    String testName = testMethod.getName();
    String className = testMethod.getDeclaringClass().getCanonicalName();

    builder
        .append("<table class=\"table table-condensed\" style=\"border-collapse:collapse;"
            + " margin: 20px auto; table-layout:fixed; width: 960px; word-wrap: break-word;\"><tbody>"
            + "<tr class=\"step\"><td>"
            + "<button class=\"btn btn-info hideLowLevel\" style=\"margin: auto\"><div class=\"icon-eye-open icon-white\" style=\"margin: 0 20px 0 0\"></div>HIDE / SHOW INFO</button>"
            + "</td><td>" + "<em><h4>" + className + "</h4></em></td><td> <br/> &nbsp;</td></tr>");
    if (testMethod.isAnnotationPresent(RelatedIssue.class)) {
      String issueID = testMethod.getAnnotation(RelatedIssue.class).issueID();
      String jiraUrl = JIRA_PATH + issueID;
      builder
          .append("<tr class=\"step\"><td>"
              + "<a href=\""
              + jiraUrl
              + "\"><button class=\"btn btn-danger\" style=\"margin: auto\"><div class=\"icon-fire icon-white\" style=\"margin: 0 20px 0 0\"></div>"
              + issueID + "</button></a>" + "</td><td colspan=\"2\"><h3><em>" + testName
              + testMethod.getAnnotation(RelatedIssue.class).comment() + "</em></h3></td></tr>");
    } else {
      builder.append("<tr class=\"step\"><td colspan=\"3\"><h3><em>" + testName
          + "</em></h3></td></tr>");
    }
    CommonUtils.appendTextToFile(LOG_PATH, builder.toString());
    testStarted = true;
  }

  public static void error(String command, String description) {
    appendToReport(command, description, Type.ERROR, true);
  }

  public static void error(String command, Exception exception) {
    error(command, exception.getMessage());
  }

  public static void warning(String command, String description) {
    appendToReport(command, description, Type.WARNING, false);
  }

  public static void warning(String command, Exception exception) {
    warning(command, exception.getMessage());
  }

  public static void info(String command, String description) {
    appendToReport(command, description, Type.INFO, false);
  }

  public static void info(String command, Exception exception) {
    info(command, exception.getMessage());
  }

  public static void info(String command, Error error) {
    info(command, error.getMessage());
  }

  public static void success(String command, String description, boolean makeScreenshot) {
    appendToReport(command, description, Type.SUCCESS, makeScreenshot);
  }

  public static void success(String command, String description) {
    success(command, description, false);
  }

  public static void success(String command, Exception exception, boolean makeScreenshot) {
    success(command, exception.getMessage(), makeScreenshot);
  }

  public static void success(String command, Exception exception) {
    success(command, exception.getMessage(), false);
  }

  public static void result(String command, String description, boolean success,
      boolean makeScreenshot) {
    appendToReport(command, description, success, makeScreenshot);
  }

  public static void result(String command, String description, boolean success) {
    result(command, description, success, false);
  }

  public static void result(String command, Exception exception, boolean success,
      boolean makeScreenshot) {
    result(command, exception.getMessage(), success, makeScreenshot);
  }

  public static void result(String command, Exception exception, boolean success) {
    result(command, exception.getMessage(), success, false);
  }

  public static void result(String command, String descriptionOnSuccess, String descriptionOnFail,
      boolean success) {
    String description = descriptionOnFail;
    if (success) {
      description = descriptionOnSuccess;
    }
    result(command, description, success);
  }

  public static void result(String command, File image, boolean success) {
    byte[] bytes = new byte[0];
    try {
      bytes = new Base64().encode(FileUtils.readFileToByteArray(image));
    } catch (IOException e) {
      error("logImage", e);
    }
    allowLongDesc = true;
    appendToReport(command, "<img src=\"data:image/png;base64,"
        + new String(bytes, StandardCharsets.UTF_8) + "\">", success, false);
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

  public static boolean isTestStarted() {
    return testStarted;
  }

  public static List<Boolean> getVerificationStack() {
    return logsResults;
  }

  public static void startReport() {
    CommonUtils.createDirectory(SCREEN_DIR_PATH);

    StringBuilder builder = new StringBuilder();
    builder
        .append("<html><style>"
            + "table td {vertical-align: middle;}.hiddenRow {padding: 0;"
            + "} td:first-child {width:200px;}td:nth-child(2) {width:660px;}td:nth-child(3) "
            + "{width:100px;}tr.success{color:black;background-color:#CCFFCC;}"
            + "tr.warning{color:black;background-color:#FEE01E;}"
            + "tr.error{color:black;background-color:#FFCCCC;}"
            + "tr.step{color:white;background:#606078}"
            + "</style><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
            + "<style>td { border-top: 1px solid grey; } </style></head><body>"
            + "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.8.3.min.js\"></script>"
            + "<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap.css\">\n"
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
    try {
      FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
      String content = IOUtils.toString(input);
      CommonUtils.appendTextToFile(LOG_PATH, content);
    } catch (IOException e) {
      LOG.error("no script.txt file available", e);
    }
  }

  public static void finishReport() {
    CommonUtils.appendTextToFile(LOG_PATH, "</body></html>");
  }

  public enum Type {
    SUCCESS("success", true, ""), WARNING("warning", true, "icon-warning-sign"), INFO("info", true,
        ""), ERROR("error", false, "icon-fire");

    private String logType;
    private boolean isError;
    private String iconClass;

    Type(String logType, boolean isError, String iconClass) {
      this.logType = logType;
      this.isError = isError;
      this.iconClass = iconClass;
    }

    public String getType() {
      return logType;
    }

    public boolean isError() {
      return isError;
    }

    public String getIconClass() {
      return iconClass;
    }
  }
}
