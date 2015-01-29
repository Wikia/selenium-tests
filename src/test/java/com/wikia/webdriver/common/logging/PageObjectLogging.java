package com.wikia.webdriver.common.logging;

import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.Global;
import com.wikia.webdriver.common.core.imageutilities.Shooter;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

public class PageObjectLogging extends AbstractWebDriverEventListener implements ITestListener {

	private By lastFindBy;
	private WebDriver driver;

	private static long imageCounter;
	private static String reportPath = "." + File.separator + "logs"
		+ File.separator;
	private static String screenDirPath = reportPath + "screenshots"
		+ File.separator;
	private static String screenPath = screenDirPath + "screenshot";
	private static String logFileName = "log.html";
	private static String logPath = reportPath + logFileName;

	public static void log(String command, String description, boolean success,
						   WebDriver driver) {
		imageCounter += 1;
		new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
		CommonUtils.appendTextToFile(screenPath + imageCounter + ".html",
			driver.getPageSource());
		String className = success ? "success" : "error";
		StringBuilder builder = new StringBuilder();
		builder.append(
			"<tr class=\"" + className + "\"><td>" + command
				+ "</td><td>" + description
				+ "</td><td> <br/><a href='screenshots/screenshot"
				+ imageCounter
				+ ".png'>Screenshot</a><br/><a href='screenshots/screenshot"
				+ imageCounter + ".html'>HTML Source</a></td></tr>");
		CommonUtils.appendTextToFile(logPath, builder.toString());
		logJSError(driver);
	}

	public static void log(String command, String description, boolean success) {
		log(command, description, success, false);
	}

	private static void log(String command, String description, boolean success,
							boolean ifLowLevel) {
		description = escapeHtml(description);

		String className = success ? "success" : "error";
		StringBuilder builder = new StringBuilder();
		if (ifLowLevel) {
			builder.append("<tr class=\"" + className + " lowLevelAction"
				+ "\"><td>" + command + "</td><td>" + description
				+ "</td><td> <br/> &nbsp;</td></tr>");
		} else {
			builder.append("<tr class=\"" + className + "\"><td>" + command
				+ "</td><td>" + description
				+ "</td><td> <br/> &nbsp;</td></tr>");
		}
		CommonUtils.appendTextToFile(logPath, builder.toString());
		logJSError(NewDriverProvider.getWebDriver());
	}


	public static void logImage(String command, File image, boolean success) {
		byte[] bytes = new byte[0];
		try {
			bytes = new Base64().encode(FileUtils.readFileToByteArray(image));
		} catch (IOException e) {
			log("logImage", e.getMessage(), false);
		}
		String imageAsBase64 = new String(bytes, StandardCharsets.UTF_8);
		imageAsBase64 = "<img src=\"data:image/png;base64," + imageAsBase64 + "\">";
		String className = success ? "success" : "error";
		CommonUtils.appendTextToFile(logPath, ("<tr class=\"" + className + "\"><td>" + command
			+ "</td><td>" + imageAsBase64
			+ "</td><td> <br/> &nbsp;</td></tr>"));
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		StringBuilder builder = new StringBuilder();
		builder.append("<tr class=\"success\"><td>Navigate to</td><td>" + url
			+ "</td><td> <br/> &nbsp;</td></tr>");
		CommonUtils.appendTextToFile(logPath, builder.toString());
		logJSError(driver);
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		lastFindBy = by;
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		logJSError(driver);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		StringBuilder builder = new StringBuilder();
		builder.append("<tr class=\"success lowLevelAction\"><td>click</td><td>" + lastFindBy
			+ "</td><td> <br/> &nbsp;</td></tr>");
		CommonUtils.appendTextToFile(logPath, builder.toString());
	}

	private static void logJSError(WebDriver driver) {
		if (Global.JS_ERROR_ENABLED) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			List<String> error = (ArrayList<String>) js.executeScript("return window.JSErrorCollector_errors.pump()");
			if (!error.isEmpty()) {
				StringBuilder builder = new StringBuilder();
				builder.append("<tr class=\"error\"><td>click</td><td>" + error + "</td><td> <br/> &nbsp;</td></tr>");
				CommonUtils.appendTextToFile(logPath, builder.toString());
			}
		}
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		StringBuilder builder = new StringBuilder();
		builder.append("<tr class=\"success lowLevelAction\"><td>ChangeValueOfField</td><td>" + lastFindBy
			+ "</td><td> <br/> &nbsp;</td></tr>");
		CommonUtils.appendTextToFile(logPath, builder.toString());
	}

	@Override
	public void onTestStart(ITestResult result) {
		StringBuilder builder = new StringBuilder();
		String testName = result.getName().toString();
		String className = result.getTestClass().getName().toString();
		builder.append(
			"<table>" +
				"<h1>Class: <em>" + className + "." + testName + " </em></h1>" +
				"<tr class=\"step\"><td>&nbsp</td><td><h1><em>" + testName + "</em></h1></td><td> <br/> &nbsp;</td></tr>"
		);
		CommonUtils.appendTextToFile(logPath, builder.toString());
		System.out.println(className + " " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		StringBuilder builder = new StringBuilder();
		builder.append(
			"<tr class=\"step\">" +
				"<td>&nbsp</td><td>STOP LOGGING METHOD  " +
				"<div style=\"text-align:center\">" +
				"<a href=\"#toc\" style=\"color:blue\">" +
				"<b>BACK TO MENU</b></a></div> </td><td> <br/> &nbsp;</td></tr>" +
				"</table>");
		CommonUtils.appendTextToFile(logPath, builder.toString());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		driver = NewDriverProvider.getWebDriver();
		if (driver == null) {
			driver = NewDriverProvider.getWebDriver();
		}
		if (Global.LOG_ENABLED) {
			try {
				new Shooter().savePageScreenshot(screenPath + imageCounter, driver);
				CommonUtils.appendTextToFile(screenPath + imageCounter
					+ ".html", driver.getPageSource());
			} catch (Exception e) {
				log("onException",
					"driver has no ability to catch screenshot or html source - driver may died",
					false);
			}

			String exception = escapeHtml(result.getThrowable().toString()
				+ "\n" + ExceptionUtils.getStackTrace(result.getThrowable()));

			StringBuilder builder = new StringBuilder();
			builder.append("<tr class=\"error\"><td>error</td><td>"
				+ exception
				+ "</td><td> <br/><a href='screenshots/screenshot"
				+ imageCounter
				+ ".png'>Screenshot</a><br/><a href='screenshots/screenshot"
				+ imageCounter + ".html'>HTML Source</a></td></tr>");
			CommonUtils.appendTextToFile(logPath, builder.toString());
			imageCounter += 1;
			logJSError(driver);
			onTestSuccess(result);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		result.setStatus(ITestResult.FAILURE);
		result.setThrowable(new SkipException("TEST SKIPPED"));
		onTestFailure(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		CommonUtils.createDirectory(screenDirPath);
		imageCounter = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		StringBuilder builder = new StringBuilder();
		builder.append(
			"<html><style>" +
				"table {margin:0 auto;}td:first-child {width:200px;}td:nth-child(2) {width:660px;}td:nth-child(3) " +
				"{width:100px;}tr.success{color:black;background-color:#CCFFCC;}" +
				"tr.error{color:black;background-color:#FFCCCC;}" +
				"tr.step{color:white;background:grey}" +
				"</style><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">" +
				"<style>td { border-top: 1px solid grey; } </style></head><body>" +
				"<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.8.2.min.js\"></script>" +
				"<p>Date: " + dateFormat.format(date) + " UTC</p>" +
				"<p>Browser: " + Global.BROWSER + "</p>" +
				"<p>OS: " + System.getProperty("os.name") + "</p>" +
				"<p>Testing environment: " + Global.DOMAIN + "</p>" +
				"<p>Testing environment: " + Global.LIVE_DOMAIN + "</p>" +
				"<p>Tested version: " + Global.WIKI_VERSION + "</p>" +
				"<div id='toc'></div>"
		);
		CommonUtils.appendTextToFile(logPath, builder.toString());
		appendShowHideButtons();
		try {
			FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
			String content = IOUtils.toString(input);
			CommonUtils.appendTextToFile(logPath, content);
		} catch (IOException e) {
			System.out.println("no script.txt file available");
		}
	}

	private void appendShowHideButtons() {
		String hideButton = "<button id=\"hideLowLevel\">hide low level actions</button>";
		String showButton = "<button id=\"showLowLevel\">show low level actions</button>";
		StringBuilder builder = new StringBuilder();
		builder.append(hideButton);
		builder.append(showButton);
		CommonUtils.appendTextToFile(logPath, builder.toString());
	}

	@Override
	public void onFinish(ITestContext context) {
		CommonUtils.appendTextToFile(logPath, "</body></html>");
	}
}
