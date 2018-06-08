package com.wikia.webdriver.common.logging;

import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.driverprovider.DriverProvider;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

public class Log {

    static final String POLISH_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss ZZ";
    static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
    static long imageCounter;
    private static String reportPath = "." + File.separator + "logs" + File.separator;
    static String screenDirPath = reportPath + "screenshots" + File.separator;
    static String screenPath = screenDirPath + "screenshot";
    private static String logFileName = "log.html";
    static String logPath = reportPath + logFileName;
    private static String jiraPath = "https://wikia-inc.atlassian.net/browse/";
    static ArrayList<Boolean> logsResults = new ArrayList<>();
    static boolean testStarted = false;

    static String getPageSource(WebDriver driver) {
      return driver.getPageSource().replaceAll("<script", "<textarea style=\"display: none\"><script")
          .replaceAll("</script", "</script></textarea");
    }

    public static void log(String command, String description, boolean success, WebDriver driver) {
      logsResults.add(success);
      imageCounter += 1;
      new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
      CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", getPageSource(driver));

      LogData logType = success ? LogLevel.OK : LogLevel.ERROR;
      String html =
              VelocityWrapper.fillLogRowWithScreenshot(Arrays.asList(logType), command, description, imageCounter);
      CommonUtils.appendTextToFile(logPath, html);
      logJSError();
    }

    public static void log(String command, Throwable e, boolean success, WebDriver driver) {
      logsResults.add(success);
      imageCounter += 1;
      new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
      CommonUtils.appendTextToFile(screenPath + imageCounter + ".html", getPageSource(driver));
      String html = VelocityWrapper.fillErrorLogRow(Arrays.asList(success ? LogLevel.OK : LogLevel.ERROR), command, imageCounter);
      CommonUtils.appendTextToFile(logPath, html);
      logJSError();
    }

    //LogData assertion result
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
     * LogData an action that is not user facing. LogData file reader can hide these actions to increase test
     * readability
     */
    public static void logOnLowLevel(String command, String description, boolean success) {
      log(command, description, success, true);
    }

    private static void log(String command, String description, boolean isSuccess,
                              boolean ifLowLevel) {
      logsResults.add(isSuccess);
      String escapedDescription = escapeHtml(description);

      List<LogData> logTypeList = new ArrayList<>();
      logTypeList.add(isSuccess ? LogLevel.OK : LogLevel.ERROR);

      if (ifLowLevel) {
        logTypeList.add(LogLevel.DEBUG);
      }
      VelocityWrapper.fillLogRow(logTypeList, command, escapedDescription);
      logJSError();
    }

    public static void ok(String command, String description){
        log(command, description, true);
    }

    public static void logError(String command, Throwable throwable) {
      String exception = escapeHtml(throwable.toString() + "\n"
        + ExceptionUtils.getStackTrace(throwable));

      log(command, exception, false, DriverProvider.getActiveDriver());
    }

    public static void warning(String command, Exception exception) {
      warning(command, exception.getMessage());
    }

    /**
     * This method will log warning to log file (line in yellow color)
     */
    public static void warning(String command, String description) {
      VelocityWrapper.fillLogRow(Arrays.asList(LogLevel.WARNING), command, description);
    }

    /**
     * This method will log info to log file (line in blue color)
     */
    public static void info(String description) {
      VelocityWrapper.fillLogRow(Arrays.asList(LogLevel.INFO), "INFO", description);
    }

    /**
     * This method will log info to log file (line in blue color)
     */
    public static void info(String command, String description) {
      VelocityWrapper.fillLogRow(Arrays.asList(LogLevel.INFO), command, description);
    }

    /**
     * This method will log info to log file (line in blue color)
     */
    public static void info(String description, Throwable e) {
      String finalDescription = description + " : " + e.getMessage();
      VelocityWrapper.fillLogRow(Arrays.asList(LogLevel.INFO), "INFO", finalDescription);
    }

    public static void image(String command, File image, boolean success) {
      byte[] bytes = new byte[0];
      try {
        bytes = new Base64().encode(FileUtils.readFileToByteArray(image));
      } catch (IOException e) {
        log("logImage", e.getMessage(), false);
      }
      image(command, new String(bytes, StandardCharsets.UTF_8), success);
    }

    public static void image(String command, String imageAsBase64, boolean success) {
      String imgHtml = VelocityWrapper.fillImage(imageAsBase64);
      VelocityWrapper.fillLogRow(Arrays.asList(success ? LogLevel.OK : LogLevel.ERROR), command, imgHtml);
    }

    static void logJSError() {
      if ("true".equals(Configuration.getJSErrorsEnabled())) {
        JavascriptExecutor js = DriverProvider.getActiveDriver();
        List<String> error =
            (ArrayList<String>) js.executeScript("return window.JSErrorCollector_errors.pump()");
        if (!error.isEmpty()) {
          VelocityWrapper.fillLogRow(Arrays.asList(LogLevel.ERROR), "click", error.toString());
        }
      }
    }

    public static List<Boolean> getVerificationStack() {
      return logsResults;
    }

    public static void startTest(Method testMethod) {
      String testName = testMethod.getName();
      String className = testMethod.getDeclaringClass().getCanonicalName();
      String command;
      String description;
      if (testMethod.isAnnotationPresent(RelatedIssue.class)) {
        String issueID = testMethod.getAnnotation(RelatedIssue.class).issueID();
        String jiraUrl = jiraPath + issueID;
        String jiraLink = VelocityWrapper.fillLink(jiraUrl, issueID);
        command = "Known failure";
        description =
            testName + " - " + jiraLink + " " + testMethod.getAnnotation(RelatedIssue.class)
                .comment();
      } else {
        command = "";
        description = testName;
      }
      String html = VelocityWrapper.fillFirstLogRow(className, testName, command, description);
      CommonUtils.appendTextToFile(logPath, html);
      testStarted = true;
    }

    public static void logAssertionStacktrace(AssertionError exception) {
        WikiaWebDriver driver = DriverProvider.getActiveDriver();

        Log.imageCounter += 1;
        if ("true".equals(Configuration.getLogEnabled())) {
            String exceptionMessage = ExceptionUtils.getStackTrace(exception);
            List<LogData> classList = new ArrayList<>();
            classList.add(LogLevel.ERROR);
            classList.add(LogType.STACKTRACE);
            String html = VelocityWrapper.fillErrorLogRow(classList, exceptionMessage, Log.imageCounter);
            try {
                new Shooter().savePageScreenshot(Log.screenPath + Log.imageCounter, driver);
                CommonUtils.appendTextToFile(Log.screenPath + Log.imageCounter + ".html", Log.getPageSource(driver));
                CommonUtils.appendTextToFile(Log.logPath, html);
            } catch (Exception e) {
                html = VelocityWrapper.fillErrorLogRowWoScreenshotAndSource(classList, exceptionMessage);
                CommonUtils.appendTextToFile(Log.logPath, html);
                Log.log("onException",
                    "driver has no ability to catch screenshot or html source - driver may died<br/>",
                    false);
            }
            Log.logJSError();
        }
    }

    public static void stop() {
        WikiaWebDriver driver = DriverProvider.getActiveDriver();
        if (driver.getProxy() != null && Configuration.getForceHttps()) {
            Har har = driver.getProxy().getHar();
            for (HarEntry entry : har.getLog().getEntries()) {
                URL url = null;
                try {
                    url = new URL(entry.getRequest().getUrl());
                    if (url.getHost().contains("wikia")) {
                        Boolean isHttps = entry.getRequest().getUrl().startsWith("https");
                        Log.log("VISITED URL", "Url: " + entry.getRequest().getUrl(),
                            !Configuration.getForceHttps() || isHttps);
                    }
                } catch (MalformedURLException e) {
                    Log.log("MALFORMED URL", "Url: " + entry.getRequest().getUrl(), false);
                }
            }
        }
        String html = VelocityWrapper.fillLastLogRow();
        CommonUtils.appendTextToFile(Log.logPath, html);
        Log.testStarted = false;
    }

    public static void startReport() {
        CommonUtils.createDirectory(Log.screenDirPath);
        Log.imageCounter = 0;

        String date = DateTimeFormat.forPattern(Log.DATE_FORMAT).print(DateTime.now(DateTimeZone.UTC));
        String polishDate = DateTimeFormat.forPattern(Log.POLISH_DATE_FORMAT).print(DateTime.now().withZone(DateTimeZone
            .forTimeZone(TimeZone.getTimeZone("Europe/Warsaw"))));
        String browser = Configuration.getBrowser();
        String os = System.getProperty("os.name");
        String testingEnvironmentUrl = UrlBuilder.createUrlBuilder().getUrl();
        String testingEnvironment = Configuration.getEnv();
        String testedVersion = "TO DO: GET WIKI VERSION HERE";

        String headerHtml = VelocityWrapper.fillHeader(date, polishDate, browser, os, testingEnvironmentUrl,
            testingEnvironment, testedVersion);
        CommonUtils.appendTextToFile(Log.logPath, headerHtml);
        appendShowHideButtons();
        try {
            FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
            String content = IOUtils.toString(input);
            CommonUtils.appendTextToFile(Log.logPath, content);
        } catch (IOException e) {
            System.out.println("no script.txt file available");
        }
    }


  private static void appendShowHideButtons() {
    String hideButton = VelocityWrapper.fillButton("hideLowLevel", "hide low level actions");
    String showButton = VelocityWrapper.fillButton("showLowLevel", "show low level actions");
    StringBuilder builder = new StringBuilder();
    builder.append(hideButton);
    builder.append(showButton);
    CommonUtils.appendTextToFile(Log.logPath, builder.toString());
  }

}
