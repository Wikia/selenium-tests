package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.logging.Log;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.*;
import java.util.stream.Collectors;

public class Assertion extends Assert {

  private static Map<ITestResult, List> verificationFailuresMap = new HashMap<>();

  public static boolean assertStringContains(String current, String pattern) {
    String currentEncoded = encodeSpecialChars(current);
    String patternEncoded = encodeSpecialChars(pattern);

    boolean assertion = true;
    try {
      if (!current.contains(pattern)) {
        throw new AssertionError(
            "String [" + current + "] doesn't match pattern [" + pattern + "]");
      }
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    Log.log(
        "assertStringContains",
        "assertion " + assertion + "! Current \"" + currentEncoded + "\" Pattern: \""
        + patternEncoded + "\"",
        assertion
    );
    return assertion;
  }

  public static boolean assertListContains(List<String> currentList, String expectedElement) {
    List<String> currentListEncoded = currentList.stream()
        .map(e -> encodeSpecialChars(e))
        .collect(Collectors.toList());
    String expectedElementEncoded = encodeSpecialChars(expectedElement);
    boolean assertion = true;
    try {
      if (!currentList.stream().anyMatch(e -> expectedElement.equals(e))) {
        throw new AssertionError("List [" + currentList.stream().collect(Collectors.joining(", "))
                                 + "] doesn't contain string [" + expectedElement + "]");
      }
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    Log.log(
        "assertStringContainsAnyPattern",
        "assertion " + assertion + "! String: \"" + expectedElementEncoded
        + "\". List of patterns \"" + currentListEncoded.stream().collect(Collectors.joining(", "))
        + ".",
        assertion
    );
    return assertion;
  }

  /**
   * Checks if element matches any of the patterns from the list, useful to check if expected video
   * title on the list of videos on the page which titles can be shortened (the ones with ...
   * suffix)
   *
   * @param currentList List of patterns
   */
  public static boolean assertStringContainsAnyPattern(
      String expectedElement, List<String> currentList
  ) {
    List<String> currentListEncoded = currentList.stream()
        .map(e -> encodeSpecialChars(e))
        .collect(Collectors.toList());
    String expectedElementEncoded = encodeSpecialChars(expectedElement);
    boolean assertion = true;
    try {
      if (!currentList.stream().anyMatch(e -> expectedElement.contains(e))) {
        throw new AssertionError(
            "String [" + expectedElement + "] doesn't match any of the patterns ["
            + currentList.stream().collect(Collectors.joining(", ")) + "]");
      }
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    Log.log(
        "assertStringContainsAnyPattern",
        "assertion " + assertion + "! String: \"" + expectedElementEncoded
        + "\". List of patterns \"" + currentListEncoded.stream().collect(Collectors.joining(", "))
        + ".",
        assertion
    );
    return assertion;
  }

  public static boolean assertStringNotContains(String current, String pattern) {
    String currentEncoded = encodeSpecialChars(current);
    String patternEncoded = encodeSpecialChars(pattern);
    boolean assertion = true;
    try {
      if (current.contains(pattern)) {
        throw new AssertionError(
            "String [" + currentEncoded + "] contains pattern [" + patternEncoded + "]");
      }
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    Log.log(
        "assertStringNotContains",
        "assertion " + assertion + "! Current \"" + currentEncoded + "\" Pattern: \""
        + patternEncoded + "\"",
        assertion
    );
    return assertion;
  }

  public static void assertEquals(String current, String pattern) {
    String patternEncoded = encodeSpecialChars(pattern);
    String currentEncoded = encodeSpecialChars(current);
    AssertionError caughtException = null;
    boolean assertion = true;
    try {
      Assert.assertEquals(current, pattern);
    } catch (AssertionError err) {
      addVerificationFailure(err);
      assertion = false;
      caughtException = err;
    }
    Log.log(
        "assertEquals",
        "assertion " + assertion + "! Pattern: \"" + patternEncoded + "\" Current: \""
        + currentEncoded + "\"",
        assertion
    );
    if (caughtException != null) {
      Log.logAssertionStacktrace(caughtException);
    }
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
    Log.log(
        "assertNotEquals",
        "assertion " + assertion + "! Pattern: \"" + patternEncoded + "\" Current: \""
        + currentEncoded + "\"",
        assertion
    );
  }

  public static void assertNumber(Number actual, Number expected, String message) {
    boolean assertion = true;
    try {
      Assert.assertEquals(actual, expected);
    } catch (AssertionError ass) {
      addVerificationFailure(ass);
      assertion = false;
    }
    Log.log("assertNumber", message + ", expected: " + expected + ", got: " + actual, assertion);
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
}
