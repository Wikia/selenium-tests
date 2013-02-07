package com.wikia.webdriver.Common.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class Assertion extends Assert{
	
	public static void assertStringContains(String bigger, String smaller){
		boolean isAssertPassed = true;
		try{
			if (bigger.contains(smaller)){				
				PageObjectLogging.log("assertStringContains", "assertion passed<br/>pattern: "+bigger+"<br/>current: "+smaller,  true);
			}
			else{
				throw new AssertionError();
			}
		}
		catch(AssertionError ass){
			isAssertPassed = false;
			addVerificationFailure(ass);
			PageObjectLogging.log("assertStringContains", "assertion failed<br/>pattern: "+bigger+"<br/>current: "+smaller,  false);
		}
	}
	
	public static void assertEquals(String pattern, String current){
		boolean isAssertPassed = true;
		try{
			Assert.assertEquals(pattern, current);
			if (pattern.contains("<")||current.contains("<"))
			{
				String tmp1 = pattern.replaceAll("<", "&lt");
				tmp1 = tmp1.replaceAll(">", "&gt");
				String tmp2 = current.replaceAll("<", "&lt");
				tmp2 = tmp2.replaceAll(">", "&gt");
				pattern = tmp1;
				current = tmp2;
			}
			PageObjectLogging.log("assertEquals", "assertion passed<br/>pattern: "+pattern+"<br/>current: "+current,  true);
		}
		catch(AssertionError ass){
			isAssertPassed = false;
			addVerificationFailure(ass);
			if (pattern.contains("<")||current.contains("<"))
			{
				String tmp1 = pattern.replaceAll("<", "&lt");
				tmp1 = tmp1.replaceAll(">", "&gt");
				String tmp2 = current.replaceAll("<", "&lt");
				tmp2 = tmp2.replaceAll(">", "&gt");
				pattern = tmp1;
				current = tmp2;
			}
			PageObjectLogging.log("assertEquals", "assertion failed<br/>pattern: "+pattern+"<br/>current: "+current,  false);
		}
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
}
