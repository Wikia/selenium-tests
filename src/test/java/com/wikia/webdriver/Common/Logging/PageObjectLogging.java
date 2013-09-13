package com.wikia.webdriver.Common.Logging;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	private StringWriter htmlWriter;
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
	String httpEquiv = "content type";
	String content = "text/html";
	String charset = "UTF-8";
//styles
	String tableStyle = "table {margin:0 auto;}";
	String tdStyle = "td {border-top: 1px solid grey;}";
	String tdFirstChildStyle = "td:first-child {width:200px;}";
	String tdSecondChildStyle = "td:nth-child(2) {width:660px;}";
	String tdThirdChildStyle = "td:nth-child(3) {width:100px;}";
	String trSucces= "tr.success {color:black;background-color:#CCFFCC;}";
	String trError = "tr.error {color:black;background-color:#FFCCCC;}";
	String trStep = "tr.step {color:white;background:grey}";
	String nl = "\n";
//scripts
	//jquery
	String scriptType = "text/javascript";
	String jQyeryScriptSource = "http://code.jquery.com/jquery-1.8.2.min.js";
	//custom wikia qa scripts
	String wikiaScriptSource = "./src/test/resources/script.js";
	//test information paragraphs
	String pDate = "asd";
	String pBrowser = "sdf";
	String pOS = "dfg";
	String pScreenRes = "fgh";
	String pTestEnv = "ghj";
	String pTestEnv2 = "hjk";
	String pTestVersion = "jkl";
//buttons
	//hide low level action button
	String hllButtonID = "hideLowLevel";
	String hllButtonText = "hide low level actions";
	//show low level action button
	String sllButtonID = "showLowLevel";
	String sllButtonText = "show low level actions";
//onStart table
	String testClassName = "asd";
	String testName = "sdf";


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
			builder.style().text(nl+tableStyle+nl+tdFirstChildStyle+nl+tdSecondChildStyle+nl+tdThirdChildStyle+nl+trSucces+nl+trError+nl+trStep).end();
			builder.head();
				builder.meta().httpEquiv(httpEquiv).content(content).charset(charset);
				//TODO: check if you can extract below style to previous styles
				builder.style().text(nl+tdStyle).end();
			builder.end();
			builder.body();
				builder.script().type(scriptType).src(jQyeryScriptSource).end();
				builder.p().text(pDate).end();
				builder.p().text(pBrowser).end();
				builder.p().text(pOS).end();
				builder.p().text(pScreenRes).end();
				builder.p().text(pTestEnv).end();
				builder.p().text(pTestEnv2).end();
				builder.p().text(pTestVersion).end();
				builder.div().id("toc").end();
				builder.button().id(hllButtonID).text(hllButtonText).end();
				builder.button().id(sllButtonID).text(sllButtonText).end();
				builder.script().src(wikiaScriptSource).end();
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

		builder.h1().text("Class: ");
			builder.em().text(testClassName+"." +testName).end();
		builder.end();
		builder.table(); //open table (will not be closed till onTestSucces)
		builder.tr().classAttr("step");
			builder.td().text("&nbsp").end();
			builder.td().h1().em().text(testName).end().end().end();
			builder.td().br().text("&nbsp").end(); //TODO: sprawdz czy dziala &nbsp zamiast &nbsp; Jesli tak wywal nbsp do zmiennej
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
			builder.td().text(description).end();
			builder.td().text(command).end();
			builder.td().br().text("&nbsp").end();
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
			builder.td().text(description).end();
			builder.td().text(command).end();
			builder.td();
				builder.br().a().href("screenshots/screenshot"+imageCounter+".png").text("screenshot").end();
				builder.br().a().href("screenshots/screenshot"+imageCounter+".html").text("HTML Source").end();
			builder.end();
		builder.end();
		logJSError(driver);
	}

	@Override
	public void onTestSuccess(ITestResult result) { //jatl DONE
		builder.tr().classAttr("step");
		builder.td().text("&nbsp").end();
			builder.td().text("STOP LOGGING METHOD");
				builder.div().style("text-align:center");
					builder.a().style("color:blue");
						builder.b().text("BACK TO MENU").end();
						builder.end();
					builder.end();
				builder.end();
			builder.td().br().text("&nbsp").end();
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
				builder.td().text("error").end();
				builder.td().text(exception).end();
				builder.td();
					builder.br().a().href("screenshots/screenshot"+imageCounter+".png").text("Screenshot").end();
					builder.br().a().href("screenshots/screenshot"+imageCounter+".html").text("HTML Source").end();
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
			builder.td().text("ChangeValueOfField").end();
			builder.td().text(lastFindBy.toString()).end(); //TODO: verify if lastFindBy.ToString works properly. Before it was lastFindBy alone
			builder.td().br().text("&nbsp").end();
		builder.end();
//		CommonUtils.appendTextToFile(logPath, builder.toString());

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) { //jtal DONE
		builder.tr().classAttr("success lowLevelAction");
			builder.td().text("click").end();
			builder.td().text(lastFindBy.toString()).end();
			builder.td().br().text("&nbsp").end();
		builder.end();
//		CommonUtils.appendTextToFile(logPath, builder.toString());
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) { //jtal DONE
		builder.tr().classAttr("succes");
			builder.td().text("Navigate to").end();
			builder.td().text(url).end();
			builder.td().br().text("&nbsp").end();
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
					builder.td().text("click").end();
					builder.td().text(error.toString()).end(); //TODO: check if it can be toString, originally was 'error' alone
					builder.td().br().text("&nbsp").end();
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
//		CommonUtils.appendTextToFile(logPath, "</body></html>");
	}
}
