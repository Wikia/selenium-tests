package com.wikia.webdriver.common.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.logging.LOG;

public class Assertion extends Assert {

  private static Map<ITestResult, List> verificationFailuresMap = new HashMap<ITestResult, List>();

  public static boolean assertStringContains(String current, String pattern) {
    String currentEncoded = encodeSpecialChars(current);
    String patternEncoded = encodeSpecialChars(pattern);
    boolean assertion = true;
    try {
      if (!current.contains(pattern)) {
        throw new AssertionError();
      }
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    LOG.result("assertStringContains", "assertion " + assertion + "! Current \"" + currentEncoded
        + "\" Pattern: \"" + patternEncoded + "\"", assertion);
    return assertion;
  }

  public static boolean assertStringNotContains(String current, String pattern) {
    String currentEncoded = encodeSpecialChars(current);
    String patternEncoded = encodeSpecialChars(pattern);
    boolean assertion = true;
    try {
      if (current.contains(pattern)) {
        throw new AssertionError();
      }
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    LOG.result("assertStringNotContains", "assertion " + assertion + "! Current \""
        + currentEncoded + "\" Pattern: \"" + patternEncoded + "\"", assertion);
    return assertion;
  }

  public static void assertEquals(String current, String pattern) {
    String patternEncoded = encodeSpecialChars(pattern);
    String currentEncoded = encodeSpecialChars(current);
    boolean assertion = true;
    try {
      Assert.assertEquals(current, pattern);
    } catch (AssertionError err) {
      addVerificationFailure(err);
      assertion = false;
    }
    LOG.result("assertEquals", "assertion " + assertion + "! Pattern: \"" + patternEncoded
        + "\" Current: \"" + currentEncoded + "\"", assertion);
  }

  public static void assertNotEquals(String current, String pattern) {
    String patternEncoded = encodeSpecialChars(pattern);
    String currentEncoded = encodeSpecialChars(current);
    boolean assertion = true;
    try {
      Assert.assertNotEquals(pattern, current);
    } catch (AssertionError err) {
      addVerificationFailure(err);
      assertion = false;
    }
    LOG.result("assertNotEquals", "assertion " + assertion + "! Pattern: \"" + patternEncoded
        + "\" Current: \"" + currentEncoded + "\"", assertion);
  }

  public static void assertNumber(Number actual, Number expected, String message) {
    boolean assertion = true;
    try {
      Assert.assertEquals(expected, actual);
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    LOG.result("assertNumber", message + ", expected: " + expected + ", got: " + actual, assertion);
  }

  private static void addVerificationFailure(Throwable e) {
    List verificationFailures = getVerificationFailures();
    verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
    verificationFailures.add(e);
  }

  public static List getVerificationFailures() {
    List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
    return verificationFailures == null ? new ArrayList() : verificationFailures;
  }

  public static List getVerificationFailures(ITestResult result) {
    List verificationFailures = verificationFailuresMap.get(result);
    return verificationFailures == null ? new ArrayList() : verificationFailures;
  }

  /*
   * Method responsive for encoding special characters like ">" and "<" inside provided string.
   * Special characters are changed to characters entities
   * 
   * @param String pattern
   * 
   * @return String pattern - with characters entities
   */
  private static String encodeSpecialChars(String pattern) {
    String encodedPattern = pattern;
    if (pattern.contains("<") || pattern.contains(">")) {
      String tmp = pattern.replaceAll("<", "&lt");
      encodedPattern = tmp.replaceAll(">", "&gt");
    }
    return encodedPattern;
  }

  public static void assertStringNotEmpty(String current) {
    String currentEncoded = encodeSpecialChars(current);
    boolean assertion = true;
    try {
      Assert.assertNotEquals("", current);
    } catch (AssertionError err) {
      addVerificationFailure(err);
      assertion = false;
    }
    LOG.result("assertStringNotEmpty", "assertion " + assertion + "! Current: \"" + currentEncoded
        + "\"", assertion);
  }

  /**
   * This method checks that URL equals current URL and logs result The method is case-insensitive
   */
  public static void assertUrlEqualToCurrentUrl(WebDriver driver, String url) {
    String currentUrl = driver.getCurrentUrl();
    LOG.log("Log Url", "Url " + url + " is equal to current Url " + currentUrl, "Url " + url
        + " isn't equal to current Url " + currentUrl,
        UrlChecker.isUrlEqualToCurrentUrl(driver, url));
  }
}
