package com.wikia.webdriver.common.logging;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.driverprovider.DriverProvider;

import lombok.Getter;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.assertj.core.util.Throwables;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log {

  private static final String POLISH_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss ZZ";
  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
  private static final String REPORT_PATH = "." + File.separator + "logs" + File.separator;
  private static final String SCREEN_DIR_PATH = REPORT_PATH + "screenshots" + File.separator;
  private static final String SCREEN_PATH = SCREEN_DIR_PATH + "screenshot";
  private static final String LOG_FILE_NAME = "log.html";
  public static final String LOG_PATH = REPORT_PATH + LOG_FILE_NAME;
  private static final ArrayList<Boolean> LOGS_RESULTS = new ArrayList<>();
  public static String mobileWikiVersion = "";
  private static long imageCounter;

  @Getter
  private static boolean testStarted = false;

  public static void clearLogStack() {
    LOGS_RESULTS.clear();
  }

  public static void log(String command, String description, boolean success, WebDriver driver) {
    LOGS_RESULTS.add(success);
    imageCounter += 1;

    try {
      new Shooter().savePageScreenshot(Log.SCREEN_PATH + Log.imageCounter, driver);
      VelocityWrapper.fillErrorLogRow(Arrays.asList(LogLevel.ERROR), description, Log.imageCounter);
    } catch (Exception e) {
      VelocityWrapper.fillErrorLogRowWoScreenshotAndSource(Arrays.asList(LogLevel.ERROR),
                                                           description
      );
      e.printStackTrace();
      Log.log(
          "onException",
          "driver has no ability to catch screenshot or html source - driver may died",
          false
      );
    }

    new Shooter().savePageScreenshot(SCREEN_PATH + imageCounter, driver);

    LogData logType = success ? LogLevel.OK : LogLevel.ERROR;
    VelocityWrapper.fillLogRowWithScreenshot(Arrays.asList(logType),
                                             command,
                                             description,
                                             imageCounter
    );
    logJSError();
  }

  public static void log(String command, Throwable e, boolean success, WebDriver driver) {
    LOGS_RESULTS.add(success);
    imageCounter += 1;
    new Shooter().savePageScreenshot(SCREEN_PATH + imageCounter, driver);
    String html = VelocityWrapper.fillErrorLogRow(Arrays.asList(
        success ? LogLevel.OK : LogLevel.ERROR), command, imageCounter);
    CommonUtils.appendTextToFile(LOG_PATH, html);
    logJSError();
  }

  //LogData assertion result
  public static void log(String command, String description, boolean success) {
    log(command, description, success, false);
  }

  public static void log(String command, Throwable e, boolean success) {
    log(command, e.getMessage(), success, false);
  }

  public static void log(
      String command, String descriptionOnSuccess, String descriptionOnFail, boolean success
  ) {
    String description = descriptionOnFail;
    if (success) {
      description = descriptionOnSuccess;
    }
    log(command, description, success, false);
  }

  /**
   * LogData an action that is not user facing. LogData file reader can hide these actions to
   * increase test readability
   */
  public static void logOnLowLevel(String command, String description, boolean success) {
    log(command, description, success, true);
  }

  private static void log(
      String command, String description, boolean isSuccess, boolean ifLowLevel
  ) {
    LOGS_RESULTS.add(isSuccess);
    String escapedDescription = escapeHtml(description);

    List<LogData> logTypeList = new ArrayList<>();
    logTypeList.add(isSuccess ? LogLevel.OK : LogLevel.ERROR);

    if (ifLowLevel) {
      logTypeList.add(LogLevel.DEBUG);
    }
    VelocityWrapper.fillLogRow(logTypeList, command, description);
    logJSError();
  }

  public static void ok(String command, String description) {
    log(command, description, true);
  }

  public static void logError(String command, Throwable throwable) {
    log(command, escapeHtml(throwable.getMessage()), false, DriverProvider.getActiveDriver());
    stacktrace(throwable);
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
      bytes = Base64.getEncoder().encode(FileUtils.readFileToByteArray(image));
    } catch (IOException e) {
      log("logImage", e.getMessage(), false);
    }
    image(command, new String(bytes, StandardCharsets.UTF_8), success);
  }

  public static void image(String command, String imageAsBase64, boolean success) {
    String imgHtml = VelocityWrapper.fillImage(imageAsBase64);
    VelocityWrapper.fillLogRow(Arrays.asList(success ? LogLevel.OK : LogLevel.ERROR),
                               command,
                               imgHtml
    );
  }

  public static void logJSError() {
    if ("true".equals(Configuration.getJSErrorsEnabled())) {
      JavascriptExecutor js = DriverProvider.getActiveDriver();
      List<String> error = (ArrayList<String>) js.executeScript(
          "return window.JSErrorCollector_errors.pump()");
      if (!error.isEmpty()) {
        VelocityWrapper.fillLogRow(Arrays.asList(LogLevel.ERROR), "click", error.toString());
      }
    }
  }

  public static List<Boolean> getVerificationStack() {
    return LOGS_RESULTS;
  }

  public static void startTest(Method testMethod) {
    String testName = testMethod.getName();
    String className = testMethod.getDeclaringClass().getCanonicalName();
    String command;
    String description;
    if (testMethod.isAnnotationPresent(RelatedIssue.class)) {
      String issueID = testMethod.getAnnotation(RelatedIssue.class).issueID();
      String jiraPath = "https://wikia-inc.atlassian.net/browse/";
      String jiraUrl = jiraPath + issueID;
      String jiraLink = VelocityWrapper.fillLink(jiraUrl, issueID);
      command = "Known failure";
      description = testName + " - " + jiraLink + " " + testMethod.getAnnotation(RelatedIssue.class)
          .comment();
    } else {
      command = "";
      description = testName;
    }
    String html = VelocityWrapper.fillFirstLogRow(className, testName, command, description);
    CommonUtils.appendTextToFile(LOG_PATH, html);
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
        new Shooter().savePageScreenshot(Log.SCREEN_PATH + Log.imageCounter, driver);
        CommonUtils.appendTextToFile(Log.LOG_PATH, html);
      } catch (Exception e) {
        html = VelocityWrapper.fillErrorLogRowWoScreenshotAndSource(classList, exceptionMessage);
        CommonUtils.appendTextToFile(Log.LOG_PATH, html);
        Log.log(
            "onException",
            "driver has no ability to catch screenshot or html source - driver may died<br/>",
            false
        );
      }
      Log.logJSError();
    }
  }

  public static void stacktrace(Throwable throwable) {
    String exceptionMessage = Throwables.getStackTrace(throwable);
    List<LogData> classList = new ArrayList<>();
    classList.add(LogLevel.ERROR);
    classList.add(LogType.STACKTRACE);

    VelocityWrapper.fillLogRow(classList,
                               "STACKTRACE",
                               escapeHtml(exceptionMessage).replace("\n", "<br>")
    );
  }

  public static void stop() {
    WikiaWebDriver driver = DriverProvider.getActiveDriver();

    if (driver.getProxy() != null && Configuration.getForceHttps()) {
      Har har = driver.getProxy().getHar();
      for (HarEntry entry : har.getLog().getEntries()) {
        URL url;
        try {
          url = new URL(entry.getRequest().getUrl());
          if (url.getHost().contains("wikia")) {
            boolean isHttps = entry.getRequest().getUrl().startsWith("https");
            Log.log("VISITED URL",
                    "Url: " + entry.getRequest().getUrl(),
                    !Configuration.getForceHttps() || isHttps
            );
          }
        } catch (MalformedURLException e) {
          Log.log("MALFORMED URL", "Url: " + entry.getRequest().getUrl(), false);
        }
      }
    }

    if (driver.getProxy() != null && Configuration.getAdsData()) {
      Har har = driver.getProxy().getHar();
      for (HarEntry entry : har.getLog().getEntries()) {
        try {
          if (entry.getRequest().getUrl().contains("adeng")) {
            String[] urlValue = entry.getRequest().getUrl().split("(adeng).+\\?");
            String[] values = urlValue[1].split("&");

            Log.info("Ad parameters", "Header: " + Arrays.toString(values));
          }
        } catch (NullPointerException ex) {
          Log.info("Did not get successful response", ex);
        }
      }
      Pattern pt = Pattern.compile("\\d{2,}");

      WebElement mercuryScriptVersion = null;
      if (Configuration.getEmulator().isMobile()) {
        mercuryScriptVersion = driver.findElement(By.cssSelector("script[src*='mercury_ads_js']"));
      }

      String mercuryAdsJsValue = mercuryScriptVersion.getAttribute("src");

      Matcher matcher = pt.matcher(mercuryAdsJsValue);
      if (matcher.find()) {
        mercuryAdsJsValue = matcher.group(0);
      } else {
        throw new WebDriverException("Missing mobile param in query string");
      }
      Log.info("Mercury Ads Version: " + mercuryAdsJsValue);
    }

    if (Configuration.getMobileWikiVersion() != null) {
      Log.info("Mobile Wiki Version: " + Configuration.getMobileWikiVersion());
    }

    String html = VelocityWrapper.fillLastLogRow();
    CommonUtils.appendTextToFile(Log.LOG_PATH, html);
    Log.testStarted = false;
  }

  public static void startReport() {
    CommonUtils.createDirectory(Log.SCREEN_DIR_PATH);
    imageCounter = 0;

    String date = DateTimeFormat.forPattern(Log.DATE_FORMAT).print(DateTime.now(DateTimeZone.UTC));
    String polishDate = DateTimeFormat.forPattern(Log.POLISH_DATE_FORMAT)
        .print(DateTime.now()
                   .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/Warsaw"))));
    String browser = Configuration.getBrowser();
    String os = System.getProperty("os.name");
    String testingEnvironmentUrl = UrlBuilder.createUrlBuilder().getUrl();
    String testingEnvironment = Configuration.getEnv();
    String testedVersion = "TO DO: GET WIKI VERSION HERE";

    String headerHtml = VelocityWrapper.fillHeader(date,
                                                   polishDate,
                                                   browser,
                                                   os,
                                                   testingEnvironmentUrl,
                                                   testingEnvironment,
                                                   testedVersion,
                                                   mobileWikiVersion
    );
    CommonUtils.appendTextToFile(Log.LOG_PATH, headerHtml);
    appendShowHideButtons();
    try {
      FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
      String content = IOUtils.toString(input);
      CommonUtils.appendTextToFile(Log.LOG_PATH, content);
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
    CommonUtils.appendTextToFile(Log.LOG_PATH, builder.toString());
  }
}
