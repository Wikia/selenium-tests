package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonExpectedConditions;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**
 *
 * @author Karol
 *
 */

public class BasePageObject{

	public final WebDriver driver;
	protected int timeOut = 30;
	public WebDriverWait wait;
	public Actions builder;

	@FindBy(css = "#WallNotifications div.notification div.msg-title")
	protected WebElement notifications_LatestNotificationOnWiki;
	@FindBy(css = "#WallNotifications li")
	protected WebElement notifications_ShowNotificationsLogo;
	@FindBy(css = "li.notifications-for-wiki")
	protected WebElement notifications_NotificationsForWiki;
	@FindBy(css = "#wall-notifications-markasread-sub")
	protected WebElement notifications_MarkAllAsReadButton;
	@FindBy(css = "#wall-notifications-markasread-all-wikis")
	protected WebElement notifications_MarkAllWikisAsReadButton;
	@FindBy(css = "#wall-notifications-markasread-this-wiki")
	protected WebElement notifications_MarkOnlyThisWikiAsReadButton;
	@FindBy(css = ".mw-htmlform-submit")
	protected WebElement followSubmit;
	@FindBy(css = "#ca-unwatch")
	protected WebElement followedButton;
	@FindBy(css = "#ca-watch")
	protected WebElement unfollowedButton;
	@FindBy(css = "#AccountNavigation a[href*='User:']")
	protected WebElement userProfileLink;

	public BasePageObject(WebDriver driver) {
		wait = new WebDriverWait(driver, timeOut);
		this.driver = driver;
		builder = new Actions(driver);
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
	}

	/*
	 * Mouse events
	 */

	public void click(WebElement pageElem) {
		CommonFunctions.scrollToElement(pageElem);
		pageElem.click();
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
			e.printStackTrace();
		}
	}

	public void mouseOverByBy(By by) {
		WebElement element = driver.findElement(by);
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.perform();
	}

	public void mouseReleaseInArticleIframe(String cssSelecotr) {
		executeScript("$($($('iframe[title*=\"Rich\"]')[0].contentDocument.body).find('"
				+ cssSelecotr + "')).mouseleave()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void mouseOver(String cssSelecotr) {
		executeScript("$('" + cssSelecotr + "').mouseenter()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void mouseRelease(String cssSelecotr) {
		executeScript("$('" + cssSelecotr + "').mouseleave()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void clickAndWait(WebElement pageElem)
	{
		try {
			CommonFunctions.scrollToElement(pageElem);
			pageElem.click();
		} catch (TimeoutException e) {
			PageObjectLogging.log("clickAndWait",
					"page loaded for more then 30 seconds after click", true);
		}
	}

	public void jQueryClick(String cssSelector){
		executeScript("$('"+cssSelector+"').click()");
		}

	public void jQueryFocus(String cssSelector){
		executeScript("$('"+cssSelector+"').focus()");
	}

	public void jQueryNthElemClick(String cssSelector, int n){
		executeScript("$('"+cssSelector+"')["+n+"].click()");
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

	public void verifyURLcontains(String GivenString) {
		String currentURL = driver.getCurrentUrl();
		Assertion.assertStringContains(currentURL, GivenString);
		PageObjectLogging.log("verifyURLcontains",
				"current url is the same as expetced url", true, driver);
	}

	public void verifyURL(String givenURL) {
		Assertion.assertEquals(givenURL, driver.getCurrentUrl());
	}

	public String getCurrentUrl() {
		System.out.println(driver.getCurrentUrl());
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

		PageObjectLogging.log("getUrl",
				"page loaded for less then 30 seconds after click", true);
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

	public void navigateBack(){
		try{
			driver.navigate().back();
			PageObjectLogging.log("navigateBack", "previous page loaded", true);
		} catch (TimeoutException e) {
			PageObjectLogging.log("navigateBack",
					"page loaded for more then 30 seconds after navigating back", true);
		}
	}

	/*
	 * Script execution helpers
	 */

	public void executeScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Object executeScriptRet(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (Object) js.executeScript("return " + script);
	}

	public long executeScriptRetLong(String script)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (Long) js.executeScript("return "+script);
	}

        public WebElement executeScriptReturnElement(String script) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (WebElement) js.executeScript(script);
        }

	protected void executeScript(String script, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send keys to WebElement
	 */

	public void sendKeys(WebElement pageElem, String KeysToSend) {
		try {
			pageElem.sendKeys(KeysToSend);
		} catch (Exception e) {
			PageObjectLogging.log("sendKeys", e.toString(), false);
		}
	}

	/**
	 * Returns parent element of the given element
	 *
	 * @author Michal Nowierski ** @param childElement - the element whose
	 *         parent we are looking for
	 */
	public WebElement getParentElement(WebElement childElement) {
		return childElement.findElement(By.xpath(".."));
	}

	/**
	 * Checks if the element is visible on browser
	 *
	 ** @param by
	 *            The By class defined for the element
	 */
	public WebElement waitForElementByBy(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return driver.findElement(by);
	}

	/**
	 * Checks if the element is visible on browser
	 *
	 ** @param element
	 *            The element to be checked
	 * @throws Exception
	 */
	public void waitForElementByElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Checks if the element is present in DOM
	 *
	 * @param element
	 *            The element to be checked
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

	public void waitForElementByClassName(String className) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.className(className)));
	}

	public void waitForElementByClass(String id) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	public WebElement waitForElementByXPath(String xPath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(xPath)));
		return driver.findElement(By.xpath(xPath));
	}

	public void waitForElementNotVisibleByCss(String css) {
		Global.LOG_ENABLED = false;
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.cssSelector(css)));
		Global.LOG_ENABLED = true;
	}

	public void waitForElementNotVisibleByBy(By by) {
		wait.until(CommonExpectedConditions.elementNotPresent(by));
	}

	public void waitForElementNotVisibleByElement(WebElement element) {
		try {
			Global.LOG_ENABLED = false;
			wait.until(CommonExpectedConditions
					.invisibilityOfElementLocated(element));
			Global.LOG_ENABLED = true;
		} catch (Exception e) {
			PageObjectLogging.log("waitForElementNotVisibleByElement",
					e.toString(), false);
		}
	}

	public void waitForElementClickableByClassName(String className) {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.className(className)));

	}

	public void waitForElementClickableByCss(String css) {

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
	}

	public void waitForElementClickableByBy(By by) {

		wait.until(ExpectedConditions.elementToBeClickable(by));
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

	public void waitForValueToNotBePresentInElementsAttributeByCss(
			String selector, String attribute, String value) {
		wait.until(CommonExpectedConditions
				.valueToNotBePresentInElementsAttribute(
						By.cssSelector(selector), attribute, value));
	}

	public void waitForTextToBePresentInElementByElement(WebElement element,
			String text) {
		wait.until(CommonExpectedConditions.textToBePresentInElement(element,
				text));
	}

	public void waitForTextToBePresentInElementByBy(By by, String text) {
		WebElement temp = driver.findElement(by);
		wait.until(CommonExpectedConditions
				.textToBePresentInElement(temp, text));
	}

	public void waitForClassRemovedFromElement(WebElement element,
			String className) {
		wait.until(CommonExpectedConditions.classRemovedFromElement(element,
				className));
	}

	public void waitForStringInURL(String givenString) {
		wait.until(CommonExpectedConditions
				.givenStringtoBePresentInURL(givenString));
	}

	public String getTimeStamp() {
		Date time = new Date();
		long timeCurrent = time.getTime();
		return String.valueOf(timeCurrent);
	}



	/**
	 * <p>
	 * Verify if js alert is or isn't there. You can expect alert with certain
	 * message, or not expect alert with certain message <br>
	 *
	 * @param alert
	 *            message that we do or do not expect
	 * @param ifAlertExpected
	 *            if we expect JS alert - true. If we don't expect JS alert -
	 *            false
	 * @author Michal Nowierski
	 */
	public void checkJSalertIsThere(String alertMessage, Boolean ifAlertExpected) {

		try {
			Thread.sleep(1000);
			Alert alert = driver.switchTo().alert();
			if (alert.getText().equals(alertMessage)) {
				alert.accept();
				PageObjectLogging.log("checkJSalertIsThere",
						"We expect an alret = " + ifAlertExpected
								+ ". JS alert found", ifAlertExpected, driver);
			} else {
				alert.accept();
				PageObjectLogging
						.log("checkJSalertIsThere",
								"We expect an alret = "
										+ ifAlertExpected
										+ ". JS alert found, and it has unexpected message: "
										+ alert.getText()
										+ " while it should be: "
										+ alertMessage, false, driver);

			}
		} catch (NoAlertPresentException Ex) {
			PageObjectLogging.log("checkJSalertIsThere",
					"We expect an alret = " + ifAlertExpected
							+ ". JS alert not found", !ifAlertExpected, driver);
		} catch (InterruptedException e) {

		}
	}

	public void openWikiPage() {
		getUrl(Global.DOMAIN + URLsContent.noexternals);
		PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);
	}

	/*
	 * Wait for expected conditions methods
	 */

	/**
	 * Wait for tags that are visible and are bigger then 1px x 1px
	 *
	 * @param String
	 *            tagNameOne - first tag name
	 * @param String
	 *            tagNameTwo - second tag name
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
		clickAndWait(notifications_ShowNotificationsLogo);
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

	public void notifications_showNotificationsForWikiOnMenu() {
		waitForElementByElement(notifications_NotificationsForWiki);
		waitForElementClickableByElement(notifications_NotificationsForWiki);
		clickAndWait(notifications_NotificationsForWiki);
		PageObjectLogging.log("notifications_showNotificationsForWiki",
				"show the upper wiki notifications on menu", true, driver);
	}

	public void notifications_markLatestNotificationsAsRead() {
		notifications_showNotifications();
		notifications_clickMarkAllAsRead(false);
	}

	public void notifications_clickMarkAllAsRead(boolean allWikis) {
		waitForElementByElement(notifications_MarkAllAsReadButton);
		waitForElementClickableByElement(notifications_MarkAllAsReadButton);
		clickAndWait(notifications_MarkAllAsReadButton);
		if (allWikis) {
			waitForElementClickableByElement(notifications_MarkAllWikisAsReadButton);
			clickAndWait(notifications_MarkAllWikisAsReadButton);
		} else {
			waitForElementClickableByElement(notifications_MarkOnlyThisWikiAsReadButton);
			clickAndWait(notifications_MarkOnlyThisWikiAsReadButton);
		}
		PageObjectLogging.log("notifications_clickMarkAllAsRead",
				(allWikis ? "all wikis" : "only one wiki") + " marked as read",
				true, driver);
	}

    /**
     * Determine whether username contains underscore
     * if so replace it with space
     *
     * @param username
     */
    protected String purifyUserName(String userName) {
        if (userName.contains("_")) {
            userName = userName.replace("_", " ");
        }
        return userName;
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
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	wait.until(
			CommonExpectedConditions.elementNotPresent(selector)
		);
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

    /**
     * Determine if tests are ran on preview or live enviroment
     */
    public String determineEnviroment() {
        if (Global.DOMAIN.contains(URLsContent.previewPrefix)) {
            return "preview";
        } else {
            return "";
        }
    }
	public void enableWikiaTracker() {
		if (driver.getCurrentUrl().contains("?")) {
			appendToUrl("&log_level=info");
		} else {
			appendToUrl("?log_level=info");
		}
	}

	public void appendToUrl(String additionToUrl) {
		driver.get(getCurrentUrl()+additionToUrl);
		PageObjectLogging.log("appendToUrl", additionToUrl+" has been appended to url", true);
	}

        public void setStyle(String selector, String index, String style){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                "document.querySelectorAll(arguments[0])[arguments[1]].style.display=arguments[2]",
                selector, index, style
            );
        }
}
