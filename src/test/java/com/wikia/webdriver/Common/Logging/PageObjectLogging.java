package com.wikia.webdriver.Common.Logging;

import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.google.common.base.Throwables;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DriverProvider.DriverProvider;

public class PageObjectLogging implements WebDriverEventListener {

	private By lastFindBy;

	private static long imageCounter;
	private static String reportPath = "." + File.separator + "logs"
			+ File.separator;
	private static String screenDirPath = reportPath + "screenshots"
			+ File.separator;
	private static String screenPath = screenDirPath + "screenshot";
	private static String logFileName = "log.html";
	private static String logPath = reportPath + logFileName;

	public static void startLoggingSuite() {
		CommonUtils.createDirectory(screenDirPath);
		imageCounter = 0;
		// date time
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// resolution
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();

		String l1 = "<html><style>table {margin:0 auto;}td:first-child {width:200px;}td:nth-child(2) {width:660px;}td:nth-child(3) {width:100px;}tr.success{color:black;background-color:#CCFFCC;}tr.error{color:black;background-color:#FFCCCC;}tr.step{color:white;background:grey}</style><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><style>td { border-top: 1px solid grey; } </style></head><body>";
		String l2 = "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.8.2.min.js\"></script>";
		String l3 = "<p>Date: " + dateFormat.format(date) + " UTC</p>";
		String l4 = "<p>Browser: " + Global.BROWSER + "</p>";
		String l5 = "<p>OS: " + System.getProperty("os.name") + "</p>";
		String l6 = "<p>Screen resolution: " + dim.width + "x"+dim.height+"</p>";
		String l7 = "<p>Testing environment: "+ Global.DOMAIN+"</p>";
		String l8 = "<p>Testing environment: "+ Global.LIVE_DOMAIN+"</p>";
		String l9 = "<p>Tested version: "+ Global.WIKI_VERSION+"</p>";
		String l10 = "<div id='toc'></div>";
		CommonUtils.appendTextToFile(logPath, l1);
		CommonUtils.appendTextToFile(logPath, l2);
		CommonUtils.appendTextToFile(logPath, l3);
		CommonUtils.appendTextToFile(logPath, l4);
		CommonUtils.appendTextToFile(logPath, l5);
		CommonUtils.appendTextToFile(logPath, l6);
		CommonUtils.appendTextToFile(logPath, l7);
		CommonUtils.appendTextToFile(logPath, l8);
		CommonUtils.appendTextToFile(logPath, l9);
		CommonUtils.appendTextToFile(logPath, l10);
		try{
			FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
			String content = IOUtils.toString(input);
			CommonUtils.appendTextToFile(logPath, content);
		}
		catch(IOException e)
		{
			System.out.println("no script.txt file available");
		}
		
	}
	

	public static void stopLoggingSuite() {
		String l1 = "</body></html>";
		CommonUtils.appendTextToFile(logPath, l1);
	}

	public static void startLoggingMethod(String className, String methodName) {
		String l1 = "<table>";
		String l2 = "<h1>Class: <em>" + className + "." + methodName+ "</em></h1>";
		String l3 = "<tr class=\"step\"><td>&nbsp</td><td><h1><em>" + className + "." + methodName+ "</em></h1></td><td> <br/> &nbsp;</td></tr>";
		CommonUtils.appendTextToFile(logPath, l1);
		CommonUtils.appendTextToFile(logPath, l2);
		CommonUtils.appendTextToFile(logPath, l3);
		System.out.println(className + " " + methodName);
	}

	public static void stopLoggingMethod() {
		String l1 = "<tr class=\"step\"><td>&nbsp</td><td>STOP LOGGING METHOD  <div style=\"text-align:center\"> <a href=\"#toc\" style=\"color:blue\"><b>BACK TO MENU</b></a></div> </td><td> <br/> &nbsp;</td></tr>";
		String l2 = "</table>";
		CommonUtils.appendTextToFile(logPath, l1);
		CommonUtils.appendTextToFile(logPath, l2);
	}

	public static void log(String command, String description, boolean success,
			WebDriver driver) {
		imageCounter += 1;
		CommonUtils.captureScreenshot(screenPath + imageCounter, driver);
		CommonUtils.appendTextToFile(screenPath + imageCounter + ".html",
				driver.getPageSource());
		String className = success ? "success" : "error";
		String s = "<tr class=\"" + className + "\"><td>" + command
				+ "</td><td>" + description
				+ "</td><td> <br/><a href='screenshots/screenshot"
				+ imageCounter
				+ ".png'>Screenshot</a><br/><a href='screenshots/screenshot"
				+ imageCounter + ".html'>HTML Source</a></td></tr>";
		CommonUtils.appendTextToFile(logPath, s);
		logJSError(driver);
//		if (!success)
//		{
//			Assert.fail(description);
//		}
		
	}

	public static void log(String command, String description, boolean success) {
		String className = success ? "success" : "error";
		String s = "<tr class=\"" + className + "\"><td>" + command
				+ "</td><td>" + description
				+ "</td><td> <br/> &nbsp;</td></tr>";
		CommonUtils.appendTextToFile(logPath, s);
		logJSError(DriverProvider.getWebDriver());
//		if (!success)
//		{
//			Assert.fail(description);
//		}
	
		
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// System.out.println("Before navigate to " + url);
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// System.out.println("After navigate to " + url);
		String s = "<tr class=\"success\"><td>Navigate to</td><td>" + url
				+ "</td><td> <br/> &nbsp;</td></tr>";
		CommonUtils.appendTextToFile(logPath, s);
		logJSError(driver);
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// System.out.println("beforeFindBy");

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		lastFindBy = by;
		// String s =
		// "<tr style=\"background:#CCFFCC;\"><td>Found element</td><td>"+lastFindBy+"</td><td> <br/> &nbsp;</td></tr>";
		// appendTextToFile(reportPath, s);

	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// System.out.println("beforeClick");
		logJSError(driver);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {

		String s = "<tr class=\"success\"><td>click</td><td>" + lastFindBy
				+ "</td><td> <br/> &nbsp;</td></tr>";
		CommonUtils.appendTextToFile(logPath, s);
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {

		// sometimes there is no ability to capture from browser, if it's not
		// responding
		if (Global.LOG_ENABLED) {
			try {
				CommonUtils
						.captureScreenshot(screenPath + imageCounter, driver);
				CommonUtils.appendTextToFile(screenPath + imageCounter
						+ ".html", driver.getPageSource());
			} catch (Exception e) {
				log("onException",
						"driver has no ability to catch screenshot or html source - driver may died",
						false);
			}

			// getting stacktrace of exception
			String stackTrace = Throwables.getStackTraceAsString(throwable);
			Throwable cause = Throwables.getRootCause(throwable);
			// creating array with stacktrace
			StackTraceElement[] stacktraceElements = cause.getStackTrace();
			try {
				// looking for stacktrace element with method name
				// findInvisibleElement when exception message is
				// "Unable to find element"
				for (int i = 0; i < stacktraceElements.length; i++) {
					if (stacktraceElements[i].getMethodName().contains(
							"findInvisibleElement")
							&& throwable.getMessage().contains(
									"Unable to find element")) {
						// if below conditions were met:
						// exception comes from findInvisibleElement method and
						// exception message contains text
						// "Unable to find element"
						// exception "elementIsInvisible" is thrown to be caught
						// at the bottom

						throw new Exception("elementIsInvisible");
					}
					if (throwable.getMessage().contains(
							"Timed out waiting for page")) {
						throw new Exception("pageLoadTimeOut");
					}
				}
				String s1 = "<tr class=\"error\"><td>error</td><td>"
						+ stackTrace
						+ "</td><td> <br/><a href='screenshots/screenshot"
						+ imageCounter
						+ ".png'>Screenshot</a><br/><a href='screenshots/screenshot"
						+ imageCounter + ".html'>HTML Source</a></td></tr>";
				CommonUtils.appendTextToFile(logPath, s1);
				imageCounter += 1;
				logJSError(driver);
			} catch (Exception e) {
				if (e.getMessage().equals("elementIsInvisible")) {
					String s1 = "<tr class=\"success\"><td>findInvisibleElement</td><td>element is not visible which is expected</td><td> <br/><a href='screenshots/screenshot"
							+ imageCounter
							+ ".png'>Screenshot</a><br/><a href='screenshots/screenshot"
							+ imageCounter + ".html'>HTML Source</a></td></tr>";
					CommonUtils.appendTextToFile(logPath, s1);
					imageCounter += 1;
				}
				if (e.getMessage().equals("pageLoadTimeOut")) {
					String s1 = "<tr class=\"success\"><td>pageLoadTimeOut</td><td>page loads for more than 30 seconds</td><td> <br/><a href='screenshots/screenshot"
							+ imageCounter
							+ ".png'>Screenshot</a><br/><a href='screenshots/screenshot"
							+ imageCounter + ".html'>HTML Source</a></td></tr>";
					CommonUtils.appendTextToFile(logPath, s1);
					imageCounter += 1;
				}
			}
		}
	}
	
	private static void logJSError(WebDriver driver){
		if (Global.JS_ERROR_ENABLED){
			JavascriptExecutor js = (JavascriptExecutor) driver;
			ArrayList<String> error = (ArrayList<String>) js.executeScript("return window.JSErrorCollector_errors.pump()");
			if (!(error.size()==0)){			
				String s1 = "<tr class=\"error\"><td>click</td><td>"+error+"</td><td> <br/> &nbsp;</td></tr>";
				CommonUtils.appendTextToFile(logPath, s1);
			}
		}
	}

}
