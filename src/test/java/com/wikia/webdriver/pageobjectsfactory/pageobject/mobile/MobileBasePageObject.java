package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class MobileBasePageObject extends WikiBasePageObject {

	public MobileBasePageObject(WebDriver driver) {
		super(driver);
	}

	@Override
	protected void setWindowSizeAndroid() {
		try {
			driver.manage().window().setSize(new Dimension(768, 1280));
		} catch (WebDriverException ex) {
			PageObjectLogging.log(
				"ResizeWindowForMobile",
				"Resize window method not available - possibly running on real device",
				true
			);
		}
	}

	// UI Mapping
	@FindBy(css = ".tgl.lgdout")
	private WebElement loginDropDownTrigger;
	@FindBy(css = ".wkInp[name='username']")
	private WebElement userNameField;
	@FindBy(css = ".wkInp[name='password']")
	private WebElement passwordField;
	@FindBy(css = ".wkBtn.main.round")
	private WebElement loginButton;
	@FindBy(css = "#ssoFbBtn")
	private WebElement loginFbButton;
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
	@FindBy(css = "#wkPrfTgl")
	protected WebElement topbarLoginButton;
	@FindBy(css = "#wkNavTgl")
	protected WebElement topbarMenuButton;
	@FindBy(css = "#wkSrhTgl")
	protected WebElement topbarSearchButton;
	@FindBy(css = "#wkCurtain")
	private WebElement curtain;
	@FindBy(css = "h2.collSec.open")
	protected WebElement sectionHeaderOpened;
	@FindBys(@FindBy(css = "ul#wkSrhSug li.show"))
	private List<WebElement> searchSuggestion;
	@FindBys(@FindBy(css = ".show:nth-child(10)"))
	private WebElement searchSuggestionLast;
	@FindBys(@FindBy(css = ".show > span"))
	private List<WebElement> suggestions;
	@FindBys(@FindBy(css = "ul#wkSrhSug li span.copySrh"))
	private List<WebElement> addSuggestionButton;
	@FindBy(css = "#wkMainCntHdr > h1")
	protected WebElement selectedPageHeader;
	@FindBy(css = "#wkMainCntHdr > a")
	private WebElement editButton;

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
			PageObjectLogging.log("clickFbWindowTriggerButton", e.getMessage(), false);
		}
//		}
		scrollAndClick(loginFbButton);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			PageObjectLogging.log("clickFbWindowTriggerButton", e.getMessage(), false);
		}
		PageObjectLogging.log("clickFbWindowTriggerButton",
			"facebook button clicked in login drop-down", true, driver);
	}

	public void verifyFBLogin() {
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		Assertion.assertStringContains(URLsContent.FACEBOOK_DOMAIN, getCurrentUrl());
		PageObjectLogging.log("VerifyFBLogin", "FB login window was opened", true, driver);
	}

	public void loginDropDown(String username, String password) {
		triggerLoginDropDown();
		typeInUserNameAndPassword(username, password);
		loginButton.click();
		waitForElementByElement(avatar);
		PageObjectLogging.log("loginDropDown", "user logged in successfully", true,
			driver);
	}

	public MobileSpecialUserLogin loginFailedDropDown(String username, String password) {
		triggerLoginDropDown();
		typeInUserNameAndPassword(username, password);
		loginButton.click();
		PageObjectLogging.log("loginFailedDropDown", "user logged in successfully", true,
			driver);
		return new MobileSpecialUserLogin(driver);
	}

	public void clickLoginFBButton() {
		triggerLoginDropDown();
		clickFbWindowTriggerButton();
		PageObjectLogging.log("clickLoginFBButton", "FB button was clicked",
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
		getUrl(wikiURL + URLsContent.ARTICLE_COMMENTS);
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

	public MobileSearchPageObject searchQuery(String query) {
		triggerSearch();
		typeInSearchQuery(query);
		submitSearchQuery();
		PageObjectLogging.log("searchQuery", "searching for " + query, true, driver);
		return new MobileSearchPageObject(driver);
	}

	public void verifySuggestions(String query) {
		waitForElementByElement(suggestions.get(0));
		for (WebElement elem : suggestions) {
			Assertion.assertTrue(elem.getAttribute("title").startsWith(query),
				"title Attribute doesn't start with given string"
			);
		}
		PageObjectLogging.log("verifySuggestions", "search suggestions verified", true, driver);
	}

	public void verifySuggestionsPlusButton() {
		waitForElementByElement(searchSuggestionLast);
		Assertion.assertEquals(addSuggestionButton.size(), searchSuggestion.size(), "sizes are not equals");
		PageObjectLogging.log("verifySuggestionsPlusButton", "search suggestions plus button verified", true, driver);
	}

	public void selectPlusFromSuggestions(int n) {
		waitForElementByElement(addSuggestionButton.get(n));
		String text = searchSuggestion.get(n).findElement(By.cssSelector("span")).getAttribute("title");
		addSuggestionButton.get(n).click();
		Assertion.assertEquals(text, searchField.getAttribute("value"), "strings are not equals");
	}

	public void selectAndVerifyClickOnSuggestion(int n) {
		String selectedSuggestionText = suggestions.get(n).getAttribute("innerText").replace("+", "");
		suggestions.get(n).click();
		waitForElementByElement(selectedPageHeader);
		Assertion.assertTrue(getCurrentUrl().endsWith("/" + selectedSuggestionText), "Url doesn't end with correct string");
	}

	public long getPosition() {
		return executeScriptRetLong("window.pageYOffset");
	}

	public void verifyPositionDifferent(Long firstPosition) {
		Assertion.assertTrue(firstPosition < getPosition(), "position is still the same");
	}

	public void verifyPositionTheSame(Long firstPosition) {
		Assertion.assertTrue(firstPosition == getPosition(), "position is different");
	}

	public void clickOnWikiaTopPageLogo() {
		waitForElementByElement(wikiaTopPageLogo);
		clickActions(wikiaTopPageLogo);
	}

	public void verifyCurtainOpened() {
		Assertion.assertEquals("block", curtain.getCssValue("display"), "curtain is not opened");
	}

	public void clickOutsideSearchField() {
		waitForElementByElement(curtain);
		curtain.click();
	}

	public void verifyCurtainClosed() {
		Assertion.assertEquals("none", curtain.getCssValue("display"), "curtain is not closed");
	}

	public void logOutMobile(String wikiURL) {
		try {
			driver.manage().deleteAllCookies();
			driver.get(wikiURL + URLsContent.LOGOUT);
		} catch (TimeoutException e) {
			PageObjectLogging.log("logOut",
				"page loads for more than 30 seconds", true);
		}
		waitForElementByElement(loginDropDownTrigger);
		PageObjectLogging.log("logOut", "uses is logged out", true, driver);
	}

	public MobileEditModePageObject clickEdit() {
		scrollAndClick(editButton);
		return new MobileEditModePageObject(driver);
	}

	public MobileEditModePageObject openNewArticleEditMode(String wikiURL) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.WIKI_DIR + getNameForArticle(),
				URLsContent.ACTION_EDIT
			)
		);
		PageObjectLogging.log(
			"openNewArticleEditMode",
			"going to edit mobile edit mode",
			true
		);
		return new MobileEditModePageObject(driver);
	}
}
