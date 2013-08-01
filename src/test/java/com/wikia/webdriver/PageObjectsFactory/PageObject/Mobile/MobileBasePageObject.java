package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class MobileBasePageObject extends BasePageObject {

	public MobileBasePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		driver.manage().window().setSize(new Dimension(640, 960));
	}

	private String span = "span";
	private String title = "title";
	private String value = "value";
	private String block = "block";
	private String none = "none";
	private String display = "display";
	private String articleComments = "wiki/Article_comments";

	// UI Mapping
	@FindBy(css = ".tgl.lgdout")
	private WebElement loginDropDownTrigger;
	@FindBy(css = ".wkInp[name='username']")
	private WebElement userNameField;
	@FindBy(css = ".wkInp[name='password']")
	private WebElement passwordField;
	@FindBy(css = "#wkLgnBtn")
	private WebElement loginButton;
	@FindBy(css = "#ssoFbBtn")
	private WebElement loginFbButton;
	@FindBy(css = ".tiapl.input[type='email']")
	private WebElement fbUserName;
	@FindBy(css = ".tiapl.input[type='password']")
	private WebElement fbPassword;
	@FindBy(css = "[name='login']")
	private WebElement fbLogin;
	@FindBy(css = ".avatar")
	private WebElement avatar;
	@FindBy(css = "#wkNavTgl")
	private WebElement mainMenuTrigger;
	@FindBy(css = "a[href='/wiki/Special:Random']")
	private WebElement randomPageButton;
	@FindBy(css = "#wkSrhTgl")
	private WebElement searchTrigger;
	@FindBy(css = "#wkSrhInp")
	private WebElement searchField;
	@FindBy(css = "#wkSrhSub")
	private WebElement searchButton;
	@FindBy(css = "#wkWrdMrk")
	protected WebElement wikiaTopPageLogo;
	@FindBy(css = "#wkCurtain")
	private WebElement curtain;
	@FindBy(css="h2.collSec.open")
	protected WebElement sectionHeaderOpened;
	@FindBys(@FindBy(css="ul[id='wkSrhSug'] li[class='show']"))
	private List<WebElement> searchSuggestion;
	@FindBys(@FindBy(css="ul[id='wkSrhSug'] li span[class='copySrh']"))
	private List<WebElement> addSuggestionButton;

	public void triggerLoginDropDown() {
		waitForElementByElement(loginDropDownTrigger);
		loginDropDownTrigger.click();
		PageObjectLogging.log("triggerLoginDropDown",
				"login drop-dwon triggered", true, driver);
	}

	public void typeInUserNameAndPassword(String username, String password) {
		waitForElementByElement(userNameField);
		waitForElementByElement(passwordField);
		userNameField.sendKeys(username);
		passwordField.sendKeys(password);
		PageObjectLogging.log("typeInUserNameAndPassword",
				"user name and password fields were populated", true, driver);
	}

	public void clickFbWindowTriggerButton() {
		waitForElementByElement(loginFbButton);
//		if (driver.findElement(By.cssSelector("#ssoFbBtn[disabled]"))
//				.isDisplayed()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		}
		scrollAndClick(loginFbButton);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PageObjectLogging.log("clickFbWindowTriggerButton",
				"facebook button clicked in login drop-down", true, driver);
	}

	public void typeInUserNameAndPasswordAndSubmitFacebook(String username,
			String password) {
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		waitForElementByElement(fbUserName);
		waitForElementByElement(fbPassword);
		waitForElementByElement(fbLogin);
		fbUserName.sendKeys(username);
		fbPassword.sendKeys(password);
		fbLogin.click();
		driver.switchTo().window(windows[0].toString());
		waitForElementByElement(avatar);
		PageObjectLogging.log("typeInUserNameAndPasswordAndSubmitFacebook",
				"user logged in by facebook", true, driver);
	}

	public void submitLogin() {
		scrollAndClick(loginButton);
		waitForElementByElement(avatar);
		PageObjectLogging.log("submitLogin", "login submit button clicked",
				true, driver);
	}

	public void login(String username, String password) {
		triggerLoginDropDown();
		typeInUserNameAndPassword(username, password);
		submitLogin();
		PageObjectLogging.log("login", "user logged in successfully", true,
				driver);
	}

	public void loginFB(String username, String password) {
		triggerLoginDropDown();
		clickFbWindowTriggerButton();
		typeInUserNameAndPasswordAndSubmitFacebook(username, password);
		PageObjectLogging.log("login facebook", "user logged in successfully",
				true, driver);
	}

	public MobileBasePageObject openHome(String wikiURL) {
		getUrl(wikiURL);
		return new MobileBasePageObject(driver);
	}

	public MobileArticlePageObject openRandomPage() {
		triggerMainMenu();
		waitForElementByElement(randomPageButton);
		scrollAndClick(randomPageButton);
		waitForElementByElement(mainMenuTrigger);
		PageObjectLogging.log("openRandomPage", "randomPageOpened", true,
				driver);
		return new MobileArticlePageObject(driver);
	}

	public MobileArticlePageObject openCommentsWithPagination(String wikiURL) {
		getUrl(wikiURL + articleComments);
		return new MobileArticlePageObject(driver);
	}

	public void triggerMainMenu() {
		waitForElementByElement(mainMenuTrigger);
		mainMenuTrigger.click();
		PageObjectLogging.log("mainMenuTrigger", "main menu opened", true,
				driver);
	}

	public void triggerSearch() {
		waitForElementByElement(searchTrigger);
		searchTrigger.click();
		waitForElementByElement(searchField);
		waitForElementByElement(searchButton);
		PageObjectLogging.log("triggerSearch", "search drop-down opened", true,
				driver);
	}

	public void typeInSearchQuery(String query) {
		searchField.sendKeys(query);
		PageObjectLogging.log("typeInSearchQuery",
				"search field populated with " + query, true, driver);
	}

	private void submitSearchQuery() {
		searchButton.click();
		PageObjectLogging.log("submitSearchQuery",
				"search submit button clicked", true, driver);
	}

	public MobileSearchPageObject searchQuery(String query){
		triggerSearch();
		typeInSearchQuery(query);
		submitSearchQuery();
		PageObjectLogging.log("searchQuery", "searching for "+query, true, driver);
		return new MobileSearchPageObject(driver);
	}

	public void verifySuggestions()
	{
		for (WebElement elem:searchSuggestion)
		{
			waitForElementByElement(elem);
		}
		PageObjectLogging.log("verifySuggestions", "search suggestions verified", true, driver);
	}


	public void verifySuggestionsPlusButton(){
		for (WebElement elem:addSuggestionButton){
			waitForElementByElement(elem);
		}
		PageObjectLogging.log("verifySuggestionsPlusButton", "search suggestions plus button verified", true, driver);
	}

	public void selectPlusFromSuggestions(int n){
		waitForElementByElement(addSuggestionButton.get(n));
		String text = searchSuggestion.get(n).findElement(By.cssSelector(span)).getAttribute(title);
		addSuggestionButton.get(n).click();
		Assertion.assertEquals(text, searchField.getAttribute(value));
	}

	public long getPosition()
	{
		return executeScriptRetLong("window.pageYOffset");
	}

	public void clickOnWikiaTopPageLogo(){
		waitForElementByElement(wikiaTopPageLogo);
		clickActions(wikiaTopPageLogo);
	}

	public void verifyCurtainOpened(){
		Assertion.assertEquals(block, curtain.getCssValue(display));
	}

	public void verifyCurtainClosed(){
		Assertion.assertEquals(none, curtain.getCssValue(display));
	}

	public void logOutMobile(String wikiURL){
		try {
			driver.manage().deleteAllCookies();
			driver.get(wikiURL + URLsContent.logout);
		} catch (TimeoutException e) {
			PageObjectLogging.log("logOut",
					"page loads for more than 30 seconds", true);
		}
		waitForElementByElement(loginDropDownTrigger);
		PageObjectLogging.log("logOut", "uses is logged out", true, driver);
	}
}
