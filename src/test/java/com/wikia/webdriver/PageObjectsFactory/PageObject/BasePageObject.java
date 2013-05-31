package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.util.Date;

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
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

/**
 * 
 * @author Karol
 *
 */

public class BasePageObject{

	public final WebDriver driver;
	public String wikiFactoryLiveDomain = "http://community.wikia.com/wiki/Special:WikiFactory";
	protected int timeOut = 30;
	protected String Domain;
	protected String articlename;
	public WebDriverWait wait;
	public Actions builder;

	@FindBy(css="#WallNotifications div.notification div.msg-title")
	protected WebElement notifications_LatestNotificationOnWiki;
	@FindBy(css="#WallNotifications li")
	protected WebElement notifications_ShowNotificationsLogo;
	@FindBy(css="li.notifications-for-wiki")
	protected WebElement notifications_NotificationsForWiki;
	@FindBy(css="#wall-notifications-markasread-sub")
	protected WebElement notifications_MarkAllAsReadButton;
	@FindBy(css="#wall-notifications-markasread-all-wikis")
	protected WebElement notifications_MarkAllWikisAsReadButton;
	@FindBy(css="#wall-notifications-markasread-this-wiki")
	protected WebElement notifications_MarkOnlyThisWikiAsReadButton;
	@FindBy(css="input.control-button")
	private WebElement publishButtonGeneral;
	@FindBy(css="a#ca-edit")
	protected WebElement editButton;
	@FindBy(css=".mw-htmlform-submit") 
	protected WebElement followSubmit;
	@FindBy(css="#ca-unwatch")
	protected WebElement followedButton;
	@FindBy(css="#ca-watch")
	protected WebElement unfollowedButton;
	@FindBy(css = "a[data-canonical='random']")
	private WebElement randomPageButton;
	@FindBy(css = ".sprite.search")
	private WebElement searchButton;
	@FindBy(css="section.modalWrapper .UserLoginModal")
	protected WebElement logInModal;
	@FindBy(css="#AccountNavigation a[href*='User:']")
	protected WebElement userProfileLink;

	@FindBy(css="form.WikiaSearch")
	WebElement wikiaSearch_searchForm;
	@FindBy(css="section.modalContent div.UserLoginModal form")
	WebElement modalLoginForm;
	@FindBy(css="a[data-id='shareButton']")
	WebElement shareButton;
	@FindBy(css="iframe.twitter-share-button")
	WebElement twitterIframe;
	@FindBy(css="a#b")
	WebElement twitterButton;
	@FindBy(css="iframe.fb_ltr")
	WebElement fBIframe;
	@FindBy(css="div.pluginConnectButton .pluginConnectButtonDisconnected button")
	WebElement fBLikeButton;
	@FindBy(css="a.email-link")
	WebElement emailButton;
	@FindBy(css="a[id='ok']")
	WebElement emailModalSendButton;
	@FindBy(css="a[id='cancel']")
	WebElement emailModalCancelButton;
	@FindBy(css="button.wikia-chiclet-button")
	WebElement emailModalCloseButton;
	@FindBy(css="input#lightbox-share-email-text")
	WebElement emailModalEmailInputField;

	public BasePageObject(WebDriver driver) {
		this.driver = driver;
		this.Domain = Global.DOMAIN;
		wait = new WebDriverWait(driver, timeOut);
		this.builder = new Actions(driver);
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
	}

	/**
	 * Click  on Publish button
	 *  
	 * @author Michal Nowierski
	 */
	public WikiArticlePageObject clickOnPublishButton() {
		mouseOver("#GlobalNavigation li:nth(1)");
		mouseRelease("#GlobalNavigation li:nth(1)");
		waitForElementByElement(publishButtonGeneral);
		waitForElementClickableByElement(publishButtonGeneral);
		jQueryClick("input.control-button");
		waitForElementByElement(editButton);
		PageObjectLogging.log("ClickOnPublishButton", "Click on 'Publish' button", true, driver);

		return new WikiArticlePageObject(driver, Domain, articlename);
	}


	/**
	 * Click  on Publish button
	 *  
	 * @author Michal Nowierski
	 */
	public WikiArticlePageObject clickOnPublishButtonAndCheckJSalertNotThere(String alertMessage) {
		waitForElementByElement(publishButtonGeneral);
		waitForElementClickableByElement(publishButtonGeneral);
		clickAndWait(publishButtonGeneral);
		checkJSalertIsThere(alertMessage, false);
		waitForElementByElement(editButton);
		PageObjectLogging.log("clickOnPublishButtonAndCheckJSalertNotThere", "Click on 'Publish' button and check there is no JS alert", true, driver);
		
		return new WikiArticlePageObject(driver, Domain, articlename);
	}
	

	/**
	 * Checks page title
	 *
	 ** @param title Specifies the title that you want to compare with the actual current title
	 */

	public boolean verifyTitle(String title)
	{
		String currentTitle = driver.getTitle();
		if (!currentTitle.equals(title))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the current URL contains the given String
	 *
	 *  @author Michal Nowierski
	 ** @param GivenString 
	 */
	public void verifyURLcontains(String GivenString)
	{
            String currentURL = driver.getCurrentUrl();
            Assertion.assertStringContains(currentURL, GivenString);
            PageObjectLogging.log(
                "verifyURLcontains",
                "current url is the same as expetced url",
                true, driver
            );
	}

	/**
	 * Checks if the current URL is the given URL
	 *
	 *  @author Michal Nowierski
	 ** @param givenURL 
	 */
	public void verifyURL(String givenURL){
		Assertion.assertEquals(givenURL, driver.getCurrentUrl());
	}
	
	public String getCurrentUrl()
	{
		System.out.println(driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}
	
	
	
	/**
	 * Clicks on an element
	 */

	public void click(WebElement pageElem)
	{
		pageElem.click();
	}
	
	public void getUrl(String url) {
	    try	{
		driver.get(url);
            } catch(TimeoutException e) {
                PageObjectLogging.log(
                    "getUrl",
                    "page %page% loaded for more then 30 seconds".replace("%page%", url),
                     false
                );
		return;
            }

            PageObjectLogging.log(
                "getUrl",
                "page loaded for less then 30 seconds after click",
                true
            );
	}

	public void refreshPage()
	{
		try{
			driver.navigate().refresh();
			PageObjectLogging.log("refreshPage", "page refreshed", true);
		}
		catch(TimeoutException e)
		{
			PageObjectLogging.log("refreshPage", "page loaded for more then 30 seconds after click", true);
		}
	}

	
	public void clickAndWait(WebElement pageElem)
	{
		try{
			CommonFunctions.scrollToElement(pageElem);
			pageElem.click();
		}
		catch(TimeoutException e)
		{
			PageObjectLogging.log("clickAndWait", "page loaded for more then 30 seconds after click", true);
		}
	}
	
	/**
	 * Send keys to WebElement
	 */

	public void sendKeys(WebElement pageElem, String KeysToSend)
	{
		try
		{
			pageElem.sendKeys(KeysToSend);			
		}
		catch(Exception e)
		{
			PageObjectLogging.log("sendKeys", e.toString(), false);			
		}
	}
	
	/**
	 * Clicks on an element using Actions click method
	 * 
	 * @author Michal Nowierski
	 * ** @param pageElem The WebElement to be clicked on 
	 */
	public void clickActions(WebElement pageElem)
	{
		try
		{
			Actions builder = new Actions(driver);
			Actions click = builder.click(pageElem);
			click.perform();				
		}
		catch(Exception e)
		{
			PageObjectLogging.log("clickActions", e.toString(), false);			
		}
	}
	
	/**
	 * Sends Keys to an element within iFrame, using jQuery
	 * 
	 * @author Michal Nowierski
	 * ** @param cssSelecotr The WebElement to which keys will be sent
	 * ** @param text the text to be sent
	 * 
	 */
	
	public void sendKeysInArticleIframe(String cssSelecotr, String text)
	{
		executeScript("$($($('iframe[title*=\"Rich\"]')[0].contentDocument.body).find('"+cssSelecotr+"')).text('"+text+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void mouseOverInArticleIframe(String cssSelecotr)
	{
		executeScript("$($($('iframe[title*=\"Rich\"]')[0].contentDocument.body).find('"+cssSelecotr+"')).mouseenter()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void mouseReleaseInArticleIframe(String cssSelecotr)
	{
		executeScript("$($($('iframe[title*=\"Rich\"]')[0].contentDocument.body).find('"+cssSelecotr+"')).mouseleave()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void mouseOver(String cssSelecotr)
	{
		executeScript("$('"+cssSelecotr+"').mouseenter()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void mouseRelease(String cssSelecotr)
	{
		executeScript("$('"+cssSelecotr+"').mouseleave()");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void jQueryClick(String cssSelector)
	{
		executeScript("$('"+cssSelector+"').click()");
	}
	
	/**
	 * Click on nth element with given css
	 * 
	 * @author Michal Nowierski
	 * ** @param n - the indicator of element from those which match the css selector  
	 */
	public void jQueryNthElemClick(String cssSelector, int n)
	{
		executeScript("$('"+cssSelector+"')["+n+"].click()");
	}
	
	public void jQueryFocus(String cssSelector)
	{
		executeScript("$('"+cssSelector+"').focus()");
	}
	
	/**
	 * Returns parent element of the given element
	 * 
	 * @author Michal Nowierski
	 * ** @param childElement - the element whose parent we are looking for
	 */
	public WebElement getParentElement(WebElement childElement) {
		return childElement.findElement(By.xpath(".."));
	}

	public void executeScript(String script)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String executeScriptRet(String script)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return "+script);
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

	protected void executeScript(String script, WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the element is visible on browser
	 *
	 ** @param by The By class defined for the element
	 */
	public WebElement waitForElementByBy(By by)
	{
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return driver.findElement(by);
	}
	
	/**
	 * Checks if the element is visible on browser
	 *
	 ** @param element The element to be checked
	 * @throws Exception 
	 */
	public void waitForElementByElement(WebElement element)
	{
            wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Checks if the element is present in DOM
	 *
	 * @param element The element to be checked
	 */
	public void waitForElementPresenceByBy(By locator)
	{
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * Wait for element to be displayed
	 * @param element
	 */
	public void waitForElementVisibleByElement(WebElement element) {
		wait.until(CommonExpectedConditions.elementVisible(element));
	}

	public WebElement waitForElementByCss(String cssSelector)
	{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
			return driver.findElement(By.cssSelector(cssSelector));
	}
	
	public void waitForElementByClassName(String className)
	{							
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
	}
	
	public void waitForElementByClass(String id)
	{							
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	
	public WebElement waitForElementByXPath(String xPath)
	{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
			return driver.findElement(By.xpath(xPath));
	}
	
	public void waitForElementNotVisibleByCss(String css)
	{
			Global.LOG_ENABLED = false;
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(css)));
			Global.LOG_ENABLED = true;
	}
	
	public void waitForElementNotVisibleByBy(By by)
	{
			Global.LOG_ENABLED = false;
			wait.until(CommonExpectedConditions.invisibilityOfElementLocated(by));
			Global.LOG_ENABLED = true;
	}
	
	public void waitForElementNotVisibleByElement(WebElement element)
	{
		try
		{
			Global.LOG_ENABLED = false;			
			wait.until(CommonExpectedConditions.invisibilityOfElementLocated(element));						
			Global.LOG_ENABLED = true;
		}
		catch(Exception e)
		{
			PageObjectLogging.log("waitForElementNotVisibleByElement", e.toString(), false);			
		}
	}
	
	public void waitForElementClickableByClassName(String className)
	{
			wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));								

	}
	
	public void waitForElementClickableByCss(String css)
	{

			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));								
	}
	
	public void waitForElementClickableByBy(By by)
	{

			wait.until(ExpectedConditions.elementToBeClickable(by));								
	}
	
	public void waitForElementClickableByElement(WebElement element)
	{
			wait.until(CommonExpectedConditions.elementToBeClickable(element));								
	}
	public void waitForElementNotClickableByElement(WebElement element)
	{
			wait.until(CommonExpectedConditions.elementNotToBeClickable(element));								
	}
	
	public void waitForElementById(String id)
	{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));								
	}
	
	public void waitForValueToBePresentInElementsAttributeByCss(String selector, String attribute, String value)
	{
			wait.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(By.cssSelector(selector), attribute, value));								
	}
	
	public void waitForValueToBePresentInElementsAttributeByElement(WebElement element, String attribute, String value)
	{
			wait.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(element, attribute, value));								
	}

	public void waitForValueToNotBePresentInElementsAttributeByCss(String selector, String attribute, String value)
	{
			wait.until(CommonExpectedConditions.valueToNotBePresentInElementsAttribute(By.cssSelector(selector), attribute, value));								
	}
	
	public void waitForTextToBePresentInElementByElement(WebElement element, String text)
	{
			wait.until(CommonExpectedConditions.textToBePresentInElement(element, text));								
	}
	
	public void waitForTextToBePresentInElementByBy(By by, String text)
	{
			WebElement temp = driver.findElement(by);
			wait.until(CommonExpectedConditions.textToBePresentInElement(temp, text));										
	}

	public void waitForClassRemovedFromElement(WebElement element, String className)
	{
			wait.until(CommonExpectedConditions.classRemovedFromElement(element, className));								
	}

	public void waitForStringInURL(String givenString)
	{						
			wait.until(CommonExpectedConditions.givenStringtoBePresentInURL(givenString));
	}
	
	/**
	 * Navigates back to the previous page
	 * 
	 * @author Michal Nowierski
	 */
	public void navigateBack()
	{
		try
		{								
			driver.navigate().back();
			PageObjectLogging.log("navigateBack", "succesfully navigated back", true);			
		}
		catch(Exception e)
		{
			PageObjectLogging.log("navigateBack", e.toString(), false);			
		}
	}
	
	/**
	 * verify that wikia search field is displayed
	 * 
	 * @author Michal Nowierski
	 */	
	public void verifyWikiaSearchFieldIsDisplayed() {
		waitForElementByElement(wikiaSearch_searchForm);
		PageObjectLogging.log("verifyWikiaSearchFieldIsDisplayed", "verify that wikia search field is displayed", true);
	}
	
	public String getTimeStamp()
	{
		Date time = new Date();
		long timeCurrent = time.getTime();
		return String.valueOf(timeCurrent);
	} 
	
	public void verifyModalLoginAppeared()
	{
		waitForElementByElement(modalLoginForm);
		PageObjectLogging.log("verifyModalLogin", "verify modal login form is displayed", true, driver);
	}
	
	public UserProfilePageObject navigateToProfilePage(String domain, String userName) {
		driver.navigate().to(domain+"wiki/User:"+userName);
		PageObjectLogging.log("UserProfilePageObject ", "navigate to username page: "+domain+"wiki/User:"+userName, true, driver);	        			
		return new UserProfilePageObject(driver);
	}
	
	/**
	 * <p> Verify if js alert is or isn't there. You can expect alert with certain message, or not expect alert with certain message <br> 
	 * 
	 * @param alert message that we do or do not expect 
	 * @param ifAlertExpected  if we expect JS alert - true. If we don't expect JS alert - false 
	 * @author Michal Nowierski
	 */
	public void checkJSalertIsThere(String alertMessage, Boolean ifAlertExpected) {

	    try 
	    { 
	    	Thread.sleep(1000);
	    	Alert alert =  driver.switchTo().alert(); 
	       	if (alert.getText().equals(alertMessage)) {
	    		alert.accept();
	    		PageObjectLogging.log("checkJSalertIsThere", "We expect an alret = "+ifAlertExpected+". JS alert found", ifAlertExpected, driver);	 
			}
	       else {
	    	   alert.accept();
	    	   PageObjectLogging.log("checkJSalertIsThere", "We expect an alret = "+ifAlertExpected+". JS alert found, and it has unexpected message: "+alert.getText()+" while it should be: "+alertMessage, false, driver);	        			
		
	       } 
	    }  
	    catch (NoAlertPresentException Ex) 
	    { 
	    	PageObjectLogging.log("checkJSalertIsThere", "We expect an alret = "+ifAlertExpected+". JS alert not found", !ifAlertExpected, driver);	    	
	    } 
	    catch (InterruptedException e) {
			
		}  	
	}

    public void openWikiPage() {
        getUrl(Domain + URLsContent.noexternals);
        PageObjectLogging.log(
            "WikiPageOpened",
            "Wiki page is opened", true
        );
    }

	public WikiArticlePageObject openRandomArticle() {
            clickAndWait(randomPageButton);
            waitForElementByElement(searchButton);
            PageObjectLogging.log(
                "openRandomArticle",
                "random page button clicked", true, driver
            );
            return new WikiArticlePageObject(driver, Domain, "random");
	}

	public void openRandomArticleByUrl() {
		navigateToRandomPage();
		waitForElementByElement(searchButton);
		PageObjectLogging.log("openRandomArticle",
				"random page button clicked", true, driver);
	}
	
	private void navigateToRandomPage() {
		String temp = Domain;
		try {
			temp = Domain + "wiki/Special:Random";
			getUrl(temp);
		} catch (TimeoutException e) {
			PageObjectLogging.log("logOut",
					"page loads for more than 30 seconds", true);
		}
		
	}
	public void clickShareButton() {
		
		waitForElementByElement(shareButton);
		shareButton.click();
		PageObjectLogging.log("clickShareButton", "Share button was clicked", true, driver);
	}
	
	public void verifyTwitterIframeVisibility() {
		
		waitForElementByElement(twitterIframe);
		PageObjectLogging.log("VerifyTwitterIframePresence", "Verify that the Twitter Iframe Is Present", true, driver);
	}
	
	
	public void verifyFBIframeVisibility() {
		
		waitForElementByElement(fBIframe);
		PageObjectLogging.log("VerifyFBIframeVisibility", "Verify that the FB Iframe Is Present", true, driver);
	}

	public void verifyEmailButtonVisibility() {
		
		waitForElementByElement(emailButton);
		PageObjectLogging.log("verifyEmailButtonVisibility", "Verify that the Email Button Is Present", true, driver);
	}
	
	public void navigteTweetButtonUrl() {
		
		waitForElementByElement(twitterIframe);
		driver.switchTo().frame(twitterIframe);
		String href = twitterButton.getAttribute("href");
		driver.switchTo().defaultContent();
		getUrl(href);
		PageObjectLogging.log("clickTweetButton", "Twitter button was clicked", true, driver);
		
	}
	
	public void clickFBLikeButton() {
		
		waitForElementByElement(fBIframe);
		driver.switchTo().frame(fBIframe);
		fBLikeButton.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickFBLikeButton", "FB Like button was clicked", true, driver);
	}
	
	public void clickEmailButton() {
		
		waitForElementByElement(emailButton);
		emailButton.click();
		PageObjectLogging.log("clickEmailButton", "Email button was clicked", true, driver);
	}
	
	public void verifyTwitterModalURL() {
		Assertion.assertStringContains(getCurrentUrl(), "twitter.com");
		PageObjectLogging.log("VerifyTwitterModalURL", "Verify that the Twitter Modal URL is correct", true, driver);
	}
	
	public void verifyFBModalURL() {
		
		CommonFunctions.waitForWindow("", "");
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		Assertion.assertStringContains(getCurrentUrl(), "facebook.com");
		driver.switchTo().window(windows[0].toString());
		PageObjectLogging.log("VerifyFBModalURL", "Verify that the FB Modal URL is correct", true, driver);
	}
	
	public void verifyEmailModalElements() {
		
		waitForElementByElement(emailModalSendButton);
		waitForElementByElement(emailModalCancelButton);
		waitForElementByElement(emailModalCloseButton);
		waitForElementByElement(emailModalEmailInputField);
		PageObjectLogging.log("VerifyEmailModalElements", "Verify that the Email Modal elements are present", true, driver);
	}
	

	public void notifications_verifyLatestNotificationTitle(String title) {
		notifications_showNotifications();
		//the below method is native click which is the only way to load notification
		notifications_clickOnNotificationsLogo();
		waitForElementByElement(notifications_LatestNotificationOnWiki);
		waitForTextToBePresentInElementByElement(notifications_LatestNotificationOnWiki, title);
		PageObjectLogging.log("notifications_verifyNotificationTitle", "Verify that the latest notification has the following title: "+title, true, driver);
	}

	public void notifications_clickOnNotificationsLogo() {
		waitForElementByElement(notifications_ShowNotificationsLogo);
		waitForElementClickableByElement(notifications_ShowNotificationsLogo);
		clickAndWait(notifications_ShowNotificationsLogo);
		PageObjectLogging.log("notifications_clickOnNotificationsLogo", "click on notifications logo on the upper right corner", true, driver);				
	}

	public void notifications_showNotifications() {
		waitForElementByElement(notifications_ShowNotificationsLogo);
		executeScript("$('#WallNotifications ul.subnav').addClass('show')");
		PageObjectLogging.log("norifications_showNotifications", "show notifications by adding 'show' class to element", true, driver);		
	}
	
	public void notifications_showNotificationsForWikiOnMenu() {
		waitForElementByElement(notifications_NotificationsForWiki);
		waitForElementClickableByElement(notifications_NotificationsForWiki);
		clickAndWait(notifications_NotificationsForWiki);
		PageObjectLogging.log("notifications_showNotificationsForWiki", "show the upper wiki notifications on menu", true, driver);		
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
		}
		else {
			waitForElementClickableByElement(notifications_MarkOnlyThisWikiAsReadButton);
			clickAndWait(notifications_MarkOnlyThisWikiAsReadButton);
		}
		PageObjectLogging.log("notifications_clickMarkAllAsRead", (allWikis ? "all wikis" : "only one wiki")+" marked as read", true, driver);				
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
     * Verify if modal for forced login is present
     */
    public void verifyLogInModalForAnonsVisibility() {
        waitForElementVisibleByElement(logInModal);
        PageObjectLogging.log(
            "VerifyLogInModalForAnonsVisibility",
            "Verify that the Log In modal is present",
            true,
            driver
        );
    }

    /**
     * Wait for element to not be present in DOM
     *
     * @param cssSelector
     */
    public void waitForElementNotPresent(final String cssSelector) {
        wait.until(
            CommonExpectedConditions.elementNotPresent(cssSelector)
        );
    }
    
    /**
     * Wait for element to not be present in DOM
     * @param selector
     */
    public void waitForElementNotPresent(final By selector) {
    	wait.until(
    			CommonExpectedConditions.elementNotPresent(selector)
    			);
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
     * Wait for tags that are visible and are bigger then 1px x 1px
     * @param String tagNameOne - first tag name
     * @param String tagNameTwo - second tag name
     */
    public void waitForOneOfTagsPresentInElement(WebElement slot, String tagNameOne, String tagNameTwo) {
        wait.until(
            CommonExpectedConditions.oneOfTagsPresentInElement(slot, tagNameOne, tagNameTwo)
        );
    }

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

}
