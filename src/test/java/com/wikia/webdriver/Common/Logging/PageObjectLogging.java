package com.wikia.webdriver.Common.Logging;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import com.googlecode.jatl.Html;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DriverProvider.DriverProvider;
import com.wikia.webdriver.Common.DriverProvider.NewDriverProvider;

public class PageObjectLogging extends AbstractWebDriverEventListener implements ITestListener {

	private By lastFindBy;
	private WebDriver driver;
	private static StringWriter htmlWriter;
	private static Html builder;

	private static long imageCounter;
	private static String reportPath = "." + File.separator + "logs"
			+ File.separator;
	private static String screenDirPath = reportPath + "screenshots"
			+ File.separator;
	private static String screenPath = screenDirPath + "screenshot";
	private static String logFileName = "log.html";
	private static String logPath = reportPath + logFileName;

//head
	private String httpEquiv = "content type";
	private String content = "text/html";
	private String charset = "UTF-8";
//styles
	private String tableStyle = "table {margin:0 auto;}";
	private String tdStyle = "td {border-top: 1px solid grey;}";
	private String tdFirstChildStyle = "td:first-child {width:200px;}";
	private String tdSecondChildStyle = "td:nth-child(2) {width:660px;}";
	private String tdThirdChildStyle = "td:nth-child(3) {width:100px;}";
	private String trSuccess= "tr.success {color:black;background-color:#CCFFCC;}";
	private String trError = "tr.error {color:black;background-color:#FFCCCC;}";
	private String trStep = "tr.step {color:white;background:grey}";
	private String nl = "\n";
	private static String nbsp = "&nbsp;";
//scripts
	//jquery
	private String scriptType = "text/javascript";
	private String jQyeryScriptSource = "http://code.jquery.com/jquery-1.8.2.min.js";
	//custom wikia qa scripts
	private String wikiaScriptSource = "./src/test/resources/script.js";
	//test information paragraphs
	private String pDate = "asd";
	private String pBrowser = "sdf";
	private String pOS = "dfg";
	private String pScreenRes = "fgh";
	private String pTestEnv = "ghj";
	private String pTestEnv2 = "hjk";
	private String pTestVersion = "jkl";
//buttons
	//hide low level action button
	private String hllButtonID = "hideLowLevel";
	private String hllButtonText = "hide low level actions";
	//show low level action button
	private String sllButtonID = "showLowLevel";
	private String sllButtonText = "show low level actions";
//onStart table
	private String testClassName = "asd";
	private String testName = "sdf";


	public static void log(String command, String description, boolean success) {
		log(command, description, success, false);
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
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
	public void onStart(ITestContext context) {
		//TODO: wywal te ustawianie zmiennych do osobnej metody albo gdzies
		CommonUtils.createDirectory(screenDirPath);
		imageCounter = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();

		pDate = "Date: " + dateFormat.format(date) + " UTC";
		pBrowser = "Browser: " + Global.BROWSER;
		pOS = "OS: " + System.getProperty("os.name");
		pScreenRes = "Screen resolution: " + dim.width + "x"+dim.height;
		pTestEnv = "Testing environment: "+ Global.DOMAIN;
		pTestEnv2 = "Testing environment: "+ Global.LIVE_DOMAIN;
		pTestVersion = "Tested version: "+ Global.WIKI_VERSION;

		htmlWriter = new StringWriter();
		this.builder = new Html(htmlWriter);

		builder.html();
			builder.style().raw(nl+tableStyle+nl+tdFirstChildStyle+nl+tdSecondChildStyle+nl+tdThirdChildStyle+nl+trSuccess+nl+trError+nl+trStep).end();
			builder.head();
				builder.meta().httpEquiv(httpEquiv).content(content).charset(charset);
				//TODO: check if you can extract below style to previous styles
				builder.style().raw(nl+tdStyle).end();
			builder.end();
			builder.body();
				builder.script().type(scriptType).src(jQyeryScriptSource).end();
				builder.p().raw(pDate).end();
				builder.p().raw(pBrowser).end();
				builder.p().raw(pOS).end();
				builder.p().raw(pScreenRes).end();
				builder.p().raw(pTestEnv).end();
				builder.p().raw(pTestEnv2).end();
				builder.p().raw(pTestVersion).end();
				builder.div().id("toc").end();
				builder.button().id(hllButtonID).raw(hllButtonText).end();
				builder.button().id(sllButtonID).raw(sllButtonText).end();
				FileInputStream input;
				try {
					input = new FileInputStream(wikiaScriptSource);
					String content = IOUtils.toString(input);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				builder.script().raw(content).end();
//		CommonUtils.appendTextToFile(logPath, builder.toString());
//		try{
//			FileInputStream input = new FileInputStream("./src/test/resources/script.txt");
//			String content = IOUtils.toString(input);
//			CommonUtils.appendTextToFile(logPath, content);
//		}
//		catch(IOException e){
//			System.out.println("no script.txt file available");
//		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		this.testName = result.getName().toString();
		this.testClassName = result.getTestClass().getName().toString();

		builder.h1().raw("Class: ");
			builder.em().raw(testClassName+"." +testName).end();
		builder.end();
		builder.table(); //open table (will not be closed till onTestSuccess)
		builder.tr().classAttr("step");
			builder.td().raw(nbsp).end();
			builder.td().h1().em().raw(testName).end().end().end();
			builder.td().br().raw(nbsp).end();
		builder.end();

		System.out.println(testClassName + " " + testName);
	}

	private static void log(String command, String description, boolean success,
			boolean ifLowLevel) {


		String className = success ? "success" : "error";

		if (ifLowLevel) {
			className = className.concat(" lowLevelAction");
		}

		builder.tr().classAttr(className);
			builder.td().raw(command).end();
			builder.td().raw(description).end();
			builder.td().br().raw(nbsp).end();
		builder.end();

//		CommonUtils.appendTextToFile(logPath, builder.toString());
		logJSError(DriverProvider.getWebDriver());
	}

	public static void log(String command, String description, boolean success,
			WebDriver driver) { //jtal DONE
		imageCounter += 1;
		CommonUtils.captureScreenshot(screenPath + imageCounter, driver);
		CommonUtils.appendTextToFile(screenPath + imageCounter + ".html",
				driver.getPageSource());
		String className = success ? "success" : "error";

		builder.tr().classAttr(className+"lowLevelAction");
			builder.td().raw(description).end();
			builder.td().raw(command).end();
			builder.td();
				builder.br().a().href("screenshots/screenshot"+imageCounter+".png").raw("screenshot").end();
				builder.br().a().href("screenshots/screenshot"+imageCounter+".html").raw("HTML Source").end();
			builder.end();
		builder.end();
		logJSError(driver);
	}

	@Override
	public void onTestSuccess(ITestResult result) { //jatl DONE
		builder.tr().classAttr("step");
		builder.td().raw(nbsp).end();
			builder.td().raw("STOP LOGGING METHOD");
				builder.div().style("text-align:center");
					builder.a().style("color:blue");
						builder.b().raw("BACK TO MENU").end();
						builder.end();
					builder.end();
				builder.end();
			builder.td().br().raw(nbsp).end();
		builder.end();
		builder.end(); // end </table>
//		CommonUtils.appendTextToFile(logPath, builder.toString());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		driver = DriverProvider.getWebDriver(); //jatl DONE
		if (driver == null){
			driver = NewDriverProvider.getWebDriver();
		}



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

			String exception = result.getThrowable().toString() +
					"\n" +ExceptionUtils.getStackTrace(result.getThrowable());

			builder.tr().classAttr("error");
				builder.td().raw("error").end();
				builder.td().raw(exception).end();
				builder.td();
					builder.br().a().href("screenshots/screenshot"+imageCounter+".png").raw("Screenshot").end();
					builder.br().a().href("screenshots/screenshot"+imageCounter+".html").raw("HTML Source").end();
					builder.end();
				builder.end();
			builder.end();

			imageCounter += 1;
			logJSError(driver);
			onTestSuccess(result);
		}
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) { //jatl DONE
		builder.tr().classAttr("success lowLevelAction");
			builder.td().raw("ChangeValueOfField").end();
			builder.td().raw(lastFindBy.toString()).end();
			builder.td().br().raw(nbsp).end();
		builder.end();
//		CommonUtils.appendTextToFile(logPath, builder.toString());

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) { //jtal DONE
		builder.tr().classAttr("success lowLevelAction");
			builder.td().raw("click").end();
			builder.td().raw(lastFindBy.toString()).end();
			builder.td().br().raw(nbsp).end();
		builder.end();
//		CommonUtils.appendTextToFile(logPath, builder.toString());
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) { //jtal DONE
		builder.tr().classAttr("success");
			builder.td().raw("Navigate to").end();
			builder.td().raw(url).end();
			builder.td().br().raw(nbsp).end();
		builder.end();
//		CommonUtils.appendTextToFile(logPath, builder.toString());
		logJSError(driver);
	}

	private static void logJSError(WebDriver driver){ //jtal DONE
		if (Global.JS_ERROR_ENABLED){
			JavascriptExecutor js = (JavascriptExecutor) driver;
			ArrayList<String> error = (ArrayList<String>) js.executeScript("return window.JSErrorCollector_errors.pump()");
			if (!error.isEmpty()) {
				builder.tr().classAttr("error");
					builder.td().raw("click").end();
					builder.td().raw(error.toString()).end(); //TODO: check if it can be toString, originally was 'error' alone
					builder.td().br().raw(nbsp).end();
				builder.end();
//				CommonUtils.appendTextToFile(logPath, builder.toString());
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onFinish(ITestContext context) { //jatl DONE
			builder.end();  // end </body>
		builder.end(); // end </html>
		CommonUtils.appendTextToFile(logPath, htmlWriter.getBuffer().toString());
	}
}
