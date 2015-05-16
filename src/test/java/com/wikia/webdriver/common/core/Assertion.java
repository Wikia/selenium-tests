package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assertion extends Assert {

  public static boolean assertStringContains(String pattern, String current) {
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
    PageObjectLogging.log(
        "assertStringContains",
        "assertion " + assertion + "! Current \"" + currentEncoded + "\" Pattern: \""
        + patternEncoded + "\"",
        assertion
    );
    return assertion;
  }

  public static void assertEquals(String pattern, String current) {
    String patternEncoded = encodeSpecialChars(pattern);
    String currentEncoded = encodeSpecialChars(current);
    boolean assertion = true;
    try {
      Assert.assertEquals(pattern, current);
    } catch (AssertionError err) {
      addVerificationFailure(err);
      assertion = false;
    }
    PageObjectLogging.log(
        "assertEquals",
        "assertion " + assertion + "! Pattern: \"" + patternEncoded
        + "\" Current: \"" + currentEncoded + "\"",
        assertion
    );
  }

  public static void assertNotEquals(String pattern, String current) {
    String patternEncoded = encodeSpecialChars(pattern);
    String currentEncoded = encodeSpecialChars(current);
    boolean assertion = true;
    try {
      Assert.assertNotEquals(pattern, current);
    } catch (AssertionError err) {
      addVerificationFailure(err);
      assertion = false;
    }
    PageObjectLogging.log(
        "assertNotEquals",
        "assertion " + assertion + "! Pattern: \"" + patternEncoded
        + "\" Current: \"" + currentEncoded + "\"",
        assertion
    );
  }

  public static void assertNumber(Number expected, Number actual, String message) {
    boolean assertion = true;
    try {
      Assert.assertEquals(expected, actual);
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    PageObjectLogging.log("assertNumber", message + ", expected: "
                                          + expected + ", got: " + actual, assertion);
  }

  private static Map<ITestResult, List> verificationFailuresMap = new HashMap<ITestResult, List>();

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
   * Method responsive for encoding special characters
   * like ">" and "<" inside provided string.
   * Special characters are changed to characters entities
   *
   * @param   String pattern
   * @return  String pattern - with characters entities
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
    PageObjectLogging.log(
        "assertStringNotEmpty",
        "assertion " + assertion + "! Current: \"" + currentEncoded + "\"",
        assertion
    );
  }
}
