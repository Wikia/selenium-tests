package com.wikia.webdriver.Common.Core;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Assertion extends Assert {
	
	public static boolean assertStringContains(String bigger, String smaller){
		boolean isAssertPassed = true;
		try{
			if (bigger.contains(smaller)){				
				PageObjectLogging.log("assertStringContains", "assertion passed<br/>pattern: "+bigger+"<br/>current: "+smaller,  true);
				return true;
			}
			else{
				throw new AssertionError();
			}
		}
		catch(AssertionError ass){
			isAssertPassed = false;
			addVerificationFailure(ass);
			PageObjectLogging.log("assertStringContains", "assertion failed<br/>pattern: "+bigger+"<br/>current: "+smaller,  false);
			return false;
		}
	}

	public static void assertEquals(String pattern, String current){
		boolean isAssertPassed = true;
		String patternEncoded = encodeSpecialChars(pattern);
		String currentEncoded = encodeSpecialChars(current);
		try {
			Assert.assertEquals(pattern, current);
		} catch(AssertionError err){
			isAssertPassed = false;
			addVerificationFailure(err);
			PageObjectLogging.log(
				"assertEquals",
				"assertion failed<br/>pattern: " + patternEncoded
				+ "<br/>current: " + currentEncoded,
				false
			);
		}
		PageObjectLogging.log(
			"assertEquals",
			"assertion passed<br/>pattern: " + patternEncoded
			+ "<br/>current: " + currentEncoded,
			true
		);
	}

	public static void assertNotEquals(String pattern, String current){
		boolean isAssertPassed = true;
		String patternEncoded = encodeSpecialChars(pattern);
		String currentEncoded = encodeSpecialChars(current);
		try {
			Assert.assertNotEquals(pattern, current);
		} catch(AssertionError err) {
			isAssertPassed = false;
			addVerificationFailure(err);
			PageObjectLogging.log(
				"assertNotEquals",
				"assertion failed<br/>pattern: " + patternEncoded
				+ "<br/>current: " + currentEncoded,
				false
			);
		}
		PageObjectLogging.log(
			"assertNotEquals",
			"assertion passed<br/>pattern: " + patternEncoded
			+ "<br/>current: " + currentEncoded,
			true
		);
	}

	public static void assertNumber(Number expected, Number actual,
			String message) {
		boolean isAssertPassed = true;
		try {
			Assert.assertEquals(expected, actual);
			PageObjectLogging.log("assertNumber", message + ", expected: "
					+ expected + ", got: " + actual, true);
		} catch (AssertionError ass) {
			isAssertPassed = false;
			addVerificationFailure(ass);
			PageObjectLogging.log("assertNumber", message + ", expected: "
					+ expected + ", got: " + actual, false);
		}
	}
	
	private static Map<ITestResult, List> verificationFailuresMap = new HashMap<ITestResult, List>();
	
	private static void addVerificationFailure(Throwable e) 
	{
		List verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(e);
	}

	public static List getVerificationFailures() 
	{
		List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList() : verificationFailures;
	}
	
	public static List getVerificationFailures(ITestResult result) 
	{
		List verificationFailures = verificationFailuresMap.get(result);
		return verificationFailures == null ? new ArrayList() : verificationFailures;
	}

	/*
	 * Method responsive for encoding special characters
	 * like ">" and "<" inside provided string.
	 * Special characters are changed to characters entities
	 *
	 * @param   String pattern
	 * @rerturn String pattern - with characters entities
	 */
	private static String encodeSpecialChars(String pattern) {
		if (pattern.contains("<") || pattern.contains(">")) {
			String tmp = pattern.replaceAll("<", "&lt");
			pattern = tmp.replaceAll(">", "&gt");
		}
		return pattern;
	}
}
