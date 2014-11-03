
package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.XSSContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonExpectedConditions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.Configuration.ConfigurationFactory;
import com.wikia.webdriver.Common.Core.Purge.PurgeMethod;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**
 * @author Karol
 */
public class BasePageObject{

	public WebDriver driver;
	protected int timeOut = 30;
	public WebDriverWait wait;
	public Actions builder;
	protected UrlBuilder urlBuilder;
	private LogEntries logEntries;

	@FindBy(css = "#WallNotifications div.notification div.msg-title")
	protected WebElement notifications_LatestNotificationOnWiki;
	@FindBy(css = "#WallNotifications > li")
	protected WebElement notifications_ShowNotificationsLogo;
	@FindBy(css = ".mw-htmlform-submit")
	protected WebElement followSubmit;
	@FindBy(css = "#ca-unwatch")
	protected WebElement followedButton;

	public BasePageObject(WebDriver driver) {
		wait = new WebDriverWait(driver, timeOut);
		this.driver = driver;
		builder = new Actions(driver);
		PageFactory.initElements(driver, this);
		this.setWindowSize();
		urlBuilder = new UrlBuilder(ConfigurationFactory.getConfig().getEnv());
	}

	protected void setWindowSize() {
		driver.manage().window().maximize();
	}

	public static String getAttributeValue(WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}

	public void clickActions(WebElement pageElem) {
		try {
			Actions builder = new Actions(driver);
			Actions click = builder.click(pageElem);
			click.perform();
		} catch (Exception e) {
			PageObjectLogging.log("clickActions", e.toString(), false);
		}
	}

	public void mouseOverInArticleIframe(String cssSelecotr) {
		executeScript("$($($('iframe[title*=\"Rich\"]')[0].contentDocument.body).find('"
				+ cssSelecotr + "')).mouseenter()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			PageObjectLogging.log("mouseOverInArticleIframe", e.getMessage(), false);
		}
	}

	/*
	 * Simple method for checking if element is on page or not.
	 * Changing the implicitWait value allows us no need for waiting 30 seconds
	 */
	protected boolean checkIfElementOnPage(String cssSelector) {
		changeImplicitWait(500, TimeUnit.MILLISECONDS);
		boolean isElementOnPage = true;
		try {
			if (driver.findElements(By.cssSelector(cssSelector)).size() < 1) {
				isElementOnPage = false;
			}
		} catch (Exception ex) {
			isElementOnPage = false;
		} finally {
			restoreDeaultImplicitWait();
		}
		return isElementOnPage;
	}

	/*
	 * Simple method for checking if element is on page or not.
	 * Changing the implicitWait value allows us no need for waiting 30 seconds
	 */
	protected boolean checkIfElementOnPage(By cssSelectorBy) {
		changeImplicitWait(500, TimeUnit.MILLISECONDS);
		try {
			return driver.findElements(cssSelectorBy).size() > 0;
		} finally {
			restoreDeaultImplicitWait();
		}
	}

	/*
	 * Simple method for checking if element is on page or not.
	 * Changing the implicitWait value allows us no need for waiting 30 seconds
	 */
	protected boolean checkIfElementOnPage(WebElement element) {
		changeImplicitWait(500, TimeUnit.MILLISECONDS);
		boolean isElementOnPage = true;
		try {
			//Get location on WebElement is rising exception when element is not present
			element.getLocation();
		} catch (Exception ex) {
			isElementOnPage = false;
		} finally {
			restoreDeaultImplicitWait();
		}
		return isElementOnPage;
	}

	/*
	 * Simple method for getting number of element on page.
	 * Changing the implicitWait value allows us no need for waiting 30 seconds
	 */
	protected int getNumOfElementOnPage(By cssSelectorBy) {
		changeImplicitWait(500, TimeUnit.MILLISECONDS);
		int numElementOnPage;
		try {
			numElementOnPage = driver.findElements(cssSelectorBy).size();
		} catch (Exception ex) {
			numElementOnPage = 0;
		} finally {
			restoreDeaultImplicitWait();
		}
		return numElementOnPage;
	}

	protected boolean checkIfElementInElement(String cssSelector, WebElement element) {
		changeImplicitWait(500, TimeUnit.MILLISECONDS);
		boolean isElementInElement = true;
		try {
			if (element.findElements(By.cssSelector(cssSelector)).size() < 1) {
				isElementInElement = false;
			}
		} catch (Exception ex) {
			isElementInElement = false;
		} finally {
			restoreDeaultImplicitWait();
		}
		return isElementInElement;
	}

	public void mouseOver(WebElement elem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$(arguments[0]).mouseenter()", elem);
	}

	public void mouseOver(String cssSelecotr) {
		executeScript("$('" + cssSelecotr + "').mouseenter()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			PageObjectLogging.log("mouseOver", e.getMessage(), false);
		}
	}

	public void mouseRelease(String cssSelecotr) {
		executeScript("$('" + cssSelecotr + "').mouseleave()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			PageObjectLogging.log("mouseRelease", e.getMessage(), false);
		}
	}

	protected String getPseudoElementValue(WebElement element, String pseudoElement, String cssValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String value = (String) js.executeScript(
			"return getComputedStyle(arguments[0], arguments[1])[arguments[2]];",
			element, pseudoElement, cssValue
		);
		return value;
	}

	protected void scrollAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
	}

	protected void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript(
					"var x = $(arguments[0]);"
					+ "window.scroll(0,parseInt(x.offset().top));",
					element
			);
		} catch (WebDriverException e) {
			if (e.getMessage().contains(XSSContent.noJQueryError)) {
				PageObjectLogging.log(
					"JSError", "JQuery is not defined", false
				);
			}
		}
	}

	public void jQueryClick(String cssSelector){
		executeScript("$('" + cssSelector + "').click()");
	}

	public void jQueryClick(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$(arguments[0])[0].click()", element);
	}

	protected void actionsClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.click(element);
		actions.perform();
	}

	public void jQueryFocus(String cssSelector){
		executeScript("$('" + cssSelector + "').focus()");
	}

	public void jQueryFocus(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$(arguments[0]).focus()", element);
	}
	/*
	 * Url helpers
	 */

	public boolean verifyTitle(String title) {
		String currentTitle = driver.getTitle();
		if (!currentTitle.equals(title)) {
			return false;
		}
		return true;
	}

	public void verifyURLcontains(String givenString) {
		String currentURL = driver.getCurrentUrl();
		Assertion.assertStringContains(givenString.toLowerCase(), currentURL.toLowerCase());
		PageObjectLogging.log("verifyURLcontains",
				"current url is the same as expetced url", true);
	}

	public void verifyURL(String givenURL) {
		Assertion.assertEquals(givenURL, driver.getCurrentUrl());
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void getUrl(String url) {
		try {
			driver.get(url);
		} catch (TimeoutException e) {
			PageObjectLogging.log("getUrl",
					"page %page% loaded for more then 30 seconds".replace(
						"%page%", url), false);
			return;
		}
		PageObjectLogging.log(
			"NavigateTo",
			String.format("Navigate to %s", url),
			true
		);
	}

	public void refreshPage() {
		try {
			driver.navigate().refresh();
			PageObjectLogging.log("refreshPage", "page refreshed", true);
		} catch (TimeoutException e) {
			PageObjectLogging.log("refreshPage",
				"page loaded for more then 30 seconds after click", true);
		}
	}

	public void waitForWindow(String windowName, String comment) {
		Object[] windows = driver.getWindowHandles().toArray();
		int delay = 500;
		int sumDelay = 500;
		while (windows.length == 1) {
			try {
				Thread.sleep(delay);
				windows = driver.getWindowHandles().toArray();
				sumDelay += 500;
			} catch (InterruptedException e) {
				PageObjectLogging.log(windowName, e.getMessage(), false);
			}
			if (sumDelay > 5000) {
				PageObjectLogging.log(windowName, comment, false);
				break;
			}
		}
	}

	protected Boolean scrollToSelector(String selector) {
		if (checkIfElementOnPage(selector)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				js.executeScript(
					"var x = $(arguments[0]);"
					+ "window.scroll(0,x.position()['top']+x.height()+100);"
					+ "$(window).trigger('scroll');",
					selector
				);
			} catch (WebDriverException e) {
				if (e.getMessage().contains(XSSContent.noJQueryError)) {
					PageObjectLogging.log(
						"JSError", "JQuery is not defined", false
					);
				}
			}
			return true;
		} else {
			PageObjectLogging.log(
				"SelectorNotFound",
				"Selector " + selector + " not found on page",
				true
			);
			return false;
		}
	}

	protected Boolean scrollToSelectorNoJQ(String selector) {
		if (checkIfElementOnPage(selector)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
				"var x = document.querySelector(arguments[0]);"
				+ "var event = new Event('scroll');"
				+ "window.scroll(0,x.offsetTop + x.clientHeight);"
				+ "window.dispatchEvent(event)",
				selector
			);
			return true;
		} else {
			PageObjectLogging.log(
				"SelectorNotFound",
				"Selector " + selector + " not found on page",
				true
			);
			return false;
		}
	}

	public void navigateBack(){
		try{
			driver.navigate().back();
			PageObjectLogging.log("navigateBack", "previous page loaded", true);
		} catch (TimeoutException e) {
			PageObjectLogging.log("navigateBack",
					"page loaded for more then 30 seconds after navigating back", true);
		}
	}

	public void executeScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			PageObjectLogging.log("executeScript", e.getMessage(), false);
		}
	}

	public String executeScriptRet(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return " + script);
	}

	public Boolean executeScriptRetBool(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (Boolean) js.executeScript("return " + script);
	}

	public long executeScriptRetLong(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (Long) js.executeScript("return " + script);
	}

	protected void executeScript(String script, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			PageObjectLogging.log("executeScript", e.getMessage(), false);
		}
	}

	public void sendKeys(WebElement pageElem, String KeysToSend) {
		try {
			pageElem.sendKeys(KeysToSend);
		} catch (Exception e) {
			PageObjectLogging.log("sendKeys", e.getMessage(), false);
		}
	}

	//You can get access to hidden elements by changing class
	public void unhideElementByClassChange(String elementName,String classWithoutHidden, int... OptionalIndex){
		int numElem = OptionalIndex.length==0 ? 0 : OptionalIndex[0];
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementsByName('" + elementName + "')[" + numElem + "].setAttribute('class', '" + classWithoutHidden + "');");
	}

	//You can get access to hidden elements by type
	public void unhideElementByTypeChange(String elementName, String newType, int... OptionalIndex){
			int numElem = OptionalIndex.length==0 ? 0 : OptionalIndex[0];
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("document.getElementsByName('" + elementName + "')[" + numElem + "].setAttribute('type', '" + newType + "');");
	}
	/**
	 * Checks if the element is visible on browser
	 *
	 ** @param by
	 *			The By class defined for the element
	 */
	public WebElement waitForElementByBy(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return driver.findElement(by);
	}

	/**
	 * Checks if the element is visible on browser
	 *
	 ** @param element
	 *			The element to be checked
	 * @throws Exception
	 */
	public void waitForElementByElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Checks if the element is present in DOM
	 *
	 * @param locator The element to be checked
	 */
	public void waitForElementPresenceByBy(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * Wait for element to be displayed
	 *
	 * @param element
	 */
	public void waitForElementVisibleByElement(WebElement element) {
		wait.until(CommonExpectedConditions.elementVisible(element));
	}

	public WebElement waitForElementByCss(String cssSelector) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.cssSelector(cssSelector)));
		return driver.findElement(By.cssSelector(cssSelector));
	}

	public WebElement waitForElementByXPath(String xPath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(xPath)));
		return driver.findElement(By.xpath(xPath));
	}

	public void waitForElementNotVisibleByElement(WebElement element) {
		wait.until(CommonExpectedConditions
				.invisibilityOfElementLocated(element));
	}

	public void waitForElementClickableByElement(WebElement element) {
		wait.until(CommonExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementNotClickableByElement(WebElement element) {
		wait.until(CommonExpectedConditions.elementNotToBeClickable(element));
	}

	public void waitForElementById(String id) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	public void waitForValueToBePresentInElementsAttributeByCss(
			String selector, String attribute, String value) {
		wait.until(CommonExpectedConditions
				.valueToBePresentInElementsAttribute(By.cssSelector(selector),
						attribute, value));
	}

	public void waitForValueToBePresentInElementsAttributeByElement(
			WebElement element, String attribute, String value) {
		wait.until(CommonExpectedConditions
				.valueToBePresentInElementsAttribute(element, attribute, value));
	}

	public void waitForTextToBePresentInElementByElement(WebElement element, String text) {
		wait.until(CommonExpectedConditions.textToBePresentInElement(element, text));
	}

	public void waitForTextToBePresentInElementByBy(By by, String text) {
		wait.until(CommonExpectedConditions
				.textToBePresentInElement(by, text));
	}

	public void waitForStringInURL(String givenString) {
		wait.until(CommonExpectedConditions.givenStringtoBePresentInURL(givenString));
		PageObjectLogging.log("waitForStringInURL", "verify that url contains "+givenString, true);
	}

	public void waitForAlertAndAccept() {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		PageObjectLogging.log("waitForAlertAndAccept", "detected and closed alert with text " + alertText, true);
	}

	public String getTimeStamp() {
		Date time = new Date();
		long timeCurrent = time.getTime();
		return String.valueOf(timeCurrent);
	}

	public String getRandomDigits(int length) {
		String timeStamp = getTimeStamp();
		int timeStampLenght = timeStamp.length();
		int timeStampCut = timeStampLenght-length;
		return timeStamp.substring(timeStampCut);
	}

	public String getRandomString(int length) {
		char [] alphabet = {
				'A','B','C','D','E','F','G','H','I',
				'J','K','L','M','N','O','P','Q','R',
				'S','T','U','V','W','X','Y','Z',
				'a','b','c','d','e','f','g','h','i',
				'j','l','k','l','m','n','o','p','r',
				's','t','u','v','w','x','y','z'};
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<length; i++) {
			sb.append(alphabet[rnd.nextInt(alphabet.length)]);
		}
		return sb.toString();
	}

	public void openWikiPage() {
		getUrl(Global.DOMAIN + URLsContent.noexternals);
		PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);
	}

	/**
	 * Wait for tags that are visible and are bigger then 1px x 1px
	 *
	 * @param tagNameOne - first tag name
	 * @param tagNameTwo - second tag name
	 */
	public void waitForOneOfTagsPresentInElement(WebElement slot,
			String tagNameOne, String tagNameTwo) {
		wait.until(CommonExpectedConditions.oneOfTagsPresentInElement(slot,
				tagNameOne, tagNameTwo));
	}



	/*
	 * notifications methods - will be moved to other class
	 */
	public void notifications_verifyLatestNotificationTitle(String title) {
		notifications_showNotifications();
		// the below method is native click which is the only way to load
		// notification
		notifications_clickOnNotificationsLogo();
		waitForElementByElement(notifications_LatestNotificationOnWiki);
		waitForTextToBePresentInElementByElement(
				notifications_LatestNotificationOnWiki, title);
		PageObjectLogging.log("notifications_verifyNotificationTitle",
				"Verify that the latest notification has the following title: "
						+ title, true, driver);
	}

	public void notifications_clickOnNotificationsLogo() {
		waitForElementByElement(notifications_ShowNotificationsLogo);
		waitForElementClickableByElement(notifications_ShowNotificationsLogo);
		notifications_ShowNotificationsLogo.click();
		PageObjectLogging.log("notifications_clickOnNotificationsLogo",
				"click on notifications logo on the upper right corner", true,
				driver);
	}

	public void notifications_showNotifications() {
		waitForElementByElement(notifications_ShowNotificationsLogo);
		executeScript("$('#WallNotifications ul.subnav').addClass('show')");
		PageObjectLogging.log("norifications_showNotifications",
				"show notifications by adding 'show' class to element", true,
				driver);
	}

	/**
	 * Wait for element to not be present in DOM
	 *
	 * @param cssSelector
	 */
	public void waitForElementNotPresent(final String cssSelector) {
		this.waitForElementNotPresent(By.cssSelector(cssSelector));
	}

	/**
	 * Wait for element to not be present in DOM
	 * @param selector
	 */
	public void waitForElementNotPresent(final By selector) {
		changeImplicitWait(0, TimeUnit.SECONDS);
		wait.until(
			CommonExpectedConditions.elementNotPresent(selector)
		);
		restoreDeaultImplicitWait();
	}

	/**
	 * Wait for element to be in viewport
	 * Either position top or left is bigger then -1
	 *
	 * @param element
	 */
	public void waitForElementInViewPort(final WebElement element) {
		wait.until(
			CommonExpectedConditions.elementInViewPort(element)
		);
	}

	/**
	 * Wait for new window present
	 */
	public void waitForNewWindow() {
		wait.until(
			CommonExpectedConditions.newWindowPresent()
		);
	};

	public void enableWikiaTracker() {
		driver.get(
			urlBuilder.appendQueryStringToURL(
				driver.getCurrentUrl(),
				URLsContent.wikiaTracker
			)
		);
	}

	public void appendToUrl(String additionToUrl) {
		driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), additionToUrl));
		PageObjectLogging.log("appendToUrl", additionToUrl + " has been appended to url", true);
	}

	public void appendMultipleQueryStringsToUrl(String[] queryStrings) {
		String currentUrl = getCurrentUrl();
		for(int i = 0; i < queryStrings.length; i++) {
			currentUrl = urlBuilder.appendQueryStringToURL(currentUrl, queryStrings[i]);
		}
		driver.get(currentUrl);
		PageObjectLogging.log("appendToUrl", queryStrings + " have been appended to url", true);
	}

	public void pressEnter(WebElement element) {
		Actions actions = new Actions(driver);
		actions.sendKeys(element, "\n");
		actions.build().perform();
	}

	public void pressDownArrow(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"var e = jQuery.Event(\"keydown\"); " +
				"e.which=40; $(arguments[0]).trigger(e);",
				element
		);
	}

	public void setDisplayStyle(String  selector, String style) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(arguments[0]).style.display = arguments[1]", selector, style);
	}

	private void purge(String URL) throws Exception {
		HttpClient client = new HttpClient();
		HttpMethod method = new PurgeMethod(URL);
		try {
			int status = client.executeMethod(method);
			if (status != HttpStatus.SC_OK && status != HttpStatus.SC_NOT_FOUND) {
				throw new Exception("HTTP PURGE failed for: " + URL + "(" + status + ")");
			}
			PageObjectLogging.log("purge", URL, true);
			return;
		} finally {
			method.releaseConnection();
		}
	}

	/**
	 * return status code of given URL
	 * @param URL
	 * @return
	 */
	public int getURLStatus(String URL) {
		try {
			purge(URL);
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
			connection.disconnect();
			connection.setRequestMethod("GET");
			connection.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) " +
					"Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)"
			);
			int status = connection.getResponseCode();
			connection.disconnect();
			return status;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * check if current HTTP status of given URL is the same as expected
	 * @param desiredStatus
	 * @param URL
	 */
	public void verifyURLStatus(int desiredStatus, String URL) {
		int timeOut = 500;
		int statusCode = 0;
		boolean status = false;
		while (!status) {
			try {
				statusCode = getURLStatus(URL);
				if (statusCode == desiredStatus){
					status = true;
				} else {
					Thread.sleep(500);
					timeOut += 500;
				}
				if (timeOut > 20000) {
					break;
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		Assertion.assertEquals(statusCode, desiredStatus);
		PageObjectLogging.log("verifyURLStatus", URL + " has status " + statusCode, true);
	}

	protected void changeImplicitWait(int value, TimeUnit timeUnit) {
		driver.manage().timeouts().implicitlyWait(value, timeUnit);
	}

	protected void restoreDeaultImplicitWait() {
		changeImplicitWait(timeOut, TimeUnit.SECONDS);
	}

	protected String getAbsolutePathForFile(String relativePath) {
		File fileCheck  = new File(relativePath);
		if (!fileCheck.isFile()) {
			throw new RuntimeException("file " + relativePath + " doesn't exists");
		}
		return fileCheck.getAbsolutePath();
	}

	public void verifyUrlInNewWindow(String URL) {
		waitForWindow("", "");
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		waitForStringInURL(URL);
		driver.close();
		driver.switchTo().window(windows[0].toString());
		PageObjectLogging.log("verifyUrlInNewWindow", "url in new window verified", true);
	}

	public void verifyElementMoved(Point source, WebElement element) {
		Point target = element.getLocation();
		if (source.x == target.x && source.y == target.y) {
			Assertion.fail(
				"Element did not move. Old coordinate (" + source.x + "," + source.y + ") " +
					"New coordinate (" + target.x + "," + target.y + ")"
			);
		}
		PageObjectLogging.log(
			"verifyElementMoved", "Element did move. From (" + source.x + "," + source.y + ") to ("
				+ target.x + ","+target.y + ")",
			true,
			driver
		);
	}

	public void verifyElementResized(Dimension source, WebElement element) {
		Dimension target = element.getSize();
		int sourceWidth = source.width;
		int sourceHeight = source.height;
		int targetWidth = target.width;
		int targetHeight = target.height;

		if (sourceWidth == targetWidth && sourceHeight == targetHeight) {
			Assertion.fail(
				"Element did not resize. Old dimension (" + sourceWidth + "," + sourceHeight + ") " +
					"New dimension (" + targetWidth + "," + targetHeight + ")"
			);
		}
		PageObjectLogging.log(
			"verifyElementMoved", "Element did resize. From (" + sourceWidth + "," + sourceHeight + ") to ("
				+ targetWidth + ","+targetHeight + ")",
			true,
			driver
		);
	}

	public WebElement getElementByValue(List<WebElement> elements, String attribute, String value) {
		WebElement foundElement = null;
		for(WebElement element : elements) {
			String retAttribute = element.getAttribute(attribute);
			if (attribute.equals("href")) {
				retAttribute = retAttribute.substring(retAttribute.indexOf("File:")+5).replace("%20", " ");
				if (!element.getAttribute("class").contains("video")) {
					retAttribute = retAttribute.substring(0, retAttribute.indexOf('.'));
				}
			}
			if (retAttribute.equals(value)) {
				foundElement = element;
				PageObjectLogging.log("getElementByValue",
					"Element with attribute: " + attribute + " with the value: " + value + " is found from the list",
					true
				);
				break;
			}
		}
		if (foundElement == null) {
			throw new NoSuchElementException(
				"Element with attribute: " + attribute + " with the value: "
				+ value + " is not found from the list"
			);
		}
		return foundElement;
	}

	public WebElement getElementByText(List<WebElement> elements, String value) {
		WebElement foundElement = null;
		for(WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(value)) {
				foundElement = element;
				PageObjectLogging.log("getElementByText", "Element with text: " + value + " is found from the list", true);
				break;
			}
		}
		if (foundElement == null) {
			throw new NoSuchElementException(
				"Element with text: " + value + " is not found from the list");
		}
		return foundElement;
	}

	public void switchToBrowserTab(int index) {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}

	public void switchToNewBrowserTab() {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size()-1));
	}

	public WebElement getElementByChildText(List<WebElement> elements, By childBySelector, String value) {
		WebElement foundElement = null;
		for(WebElement element : elements) {
			if (element.findElement(childBySelector).getText().equalsIgnoreCase(value)) {
				foundElement = element;
				PageObjectLogging.log("getElementByChildText", "Element's child with text: " + value + " is found from the list", true);
				break;
			}
		}
		if (foundElement == null) {
			throw new NoSuchElementException(
				"Element's child with text: " + value + " is not found from the list");
		}
		return foundElement;
	}

	private void setBrowserLogs() {
		logEntries = driver.manage().logs().get(LogType.BROWSER);
	}

	public void extractBrowserLogs() {
		setBrowserLogs();
		for (LogEntry entry : logEntries) {
			PageObjectLogging.log("extractJSLogs", entry.getMessage(), false);
		}
	}
}
