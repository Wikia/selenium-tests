package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.ContentPatterns.ApiActions;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.DeletePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.RenamePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWall;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialAdminDashboardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCssPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFBConnectPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialRestorePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialWikiActivityPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Multiwikifinder.SpecialMultiWikiFinderPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Top10.Top_10_list;

public class WikiBasePageObject extends BasePageObject {

	@FindBy(css = "a.createpage")
	private WebElement createArticleButton;
	@FindBy(css = "article span.drop")
	private WebElement editDropDown;
	@FindBy(css = "input#wpConfirmB")
	private WebElement deleteConfirmationButton;
	@FindBy(xpath = "//div[@class='msg' and contains(text(), 'The comment has been deleted.')]")
	private WebElement deleteCommentConfirmationMessage;
	@FindBy(css = ".global-notification div.msg a")
	private WebElement undeleteLink;
	@FindBy(css = ".global-notification")
	private WebElement flashMessage;
	@FindBy(css = ".global-notification .close")
	private WebElement flashMessageClose;
	@FindBy(css = "input#mw-undelete-submit")
	private WebElement restoreButton;
	@FindBy(css = "input#wpNewTitleMain")
	private WebElement renameArticleField;
	@FindBy(css = "input[name='wpMove']")
	private WebElement confirmRenamePageButton;
	@FindBy(css = "input#wpReason")
	private WebElement deleteCommentReasonField;
	@FindBy(css="div.permissions-errors")
	private WebElement premissionErrorMessage;
	@FindBy(css="div.mw-warning-with-logexcerpt p")
	private WebElement pageDeletedInfo;
	@FindBy(css = ".UserLoginModal input[type='submit']")
	protected WebElement modalLoginSubmit;
	@FindBy(css = ".WikiaMenuElement a[data-id='createpage']")
	protected WebElement contributeAddPage;
	@FindBy(css = ".UserLoginModal input[name='password']")
	protected WebElement modalPasswordInput;
	@FindBy (css = "#WikiaPageHeader h1")
	protected WebElement wikiFirstHeader;
	@FindBy (css = "#WikiaArticle a[href*='Special:UserLogin']")
	private WebElement specialUserLoginLink;
	@FindBy(css = ".UserLoginModal input[name='username']")
	protected WebElement modalUserNameInput;
	@FindBy(css="#AccountNavigation > li > a > .avatar")
	protected WebElement userProfileAvatar;
	@FindBy(css="#AccountNavigation > li > a ~ ul > li > a[data-id='logout']")
	protected WebElement navigationLogoutLink;
	@FindBy(css="section.modalWrapper .UserLoginModal")
	protected WebElement logInModal;
	@FindBy(css = "#WikiaMainContent a[data-id='edit']")
	protected WebElement editButton;
	@FindBy(css=".msg")
	protected WebElement userMessage;
	@FindBy(css = "#mw-content-text .source-css")
	protected WebElement cssSource;
	@FindBy(css = "ul#pagehistory > li:first-child .comment")
	protected WebElement cssEditSummary;
	@FindBy(css = "ul#pagehistory > li:first-child .minoredit")
	protected WebElement cssMinorEdit;
	@FindBy(css = "#ca-watch")
	protected WebElement followButton;
	@FindBy(css="#WikiaMainContent .drop img")
	protected WebElement articleEditDropdown;
	@FindBy(css="#ca-delete")
	protected WebElement deleteDropdown;
	@FindBy(css="#ca-protect")
	protected WebElement protectDropdown;
	@FindBy(css="#ca-move")
	protected WebElement renameDropdown;

	protected By editButtonBy = By.cssSelector("#WikiaMainContent a[data-id='edit']");

	protected String buildUrlFile(String wikiURL, String fileName) {
		return wikiURL + URLsContent.wikiDir + URLsContent.fileNameSpace + fileName;
	}

	public enum PositionsVideo {
		left, center, right
	}

	public enum StyleVideo {
		caption, nocaption;
	}

	public WikiBasePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String resetForgotPasswordTime(String userName) {
		String[][] apiRequestParameters = {
				{"action", ApiActions.apiActionForgotPassword},
				{"user", userName},
				{"token", Properties.apiToken},
				{"format", "json"},
		};
		return CommonUtils.sendPost(URLsContent.apiUrl, apiRequestParameters);
	}

	public void verifyModalLoginAppeared()
	{
		waitForElementByElement(logInModal);
		PageObjectLogging.log("verifyModalLogin", "verify modal login form is displayed", true, driver);
	}

	public UserProfilePageObject openProfilePage(String userName, String wikiURL) {
		getUrl(wikiURL + "wiki/User:" + userName);
		return new UserProfilePageObject(driver);
	}

	public SpecialVideosPageObject openSpecialVideoPage(String wikiURL){
		getUrl(wikiURL+URLsContent.specialNewVideo);
		return new SpecialVideosPageObject(driver);
	}

	public SpecialNewFilesPageObject openSpecialNewFiles(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialNewFiles);
		return new SpecialNewFilesPageObject(driver);
	}

	public SpecialNewFilesPageObject openSpecialNewFiles() {
		getUrl(Global.DOMAIN + URLsContent.specialNewFiles);
		return new SpecialNewFilesPageObject(driver);
	}

	public SpecialAdminDashboardPageObject openSpecialAdminDashboard() {
		getUrl(Global.DOMAIN + URLsContent.specialAdminDashboard);
		return new SpecialAdminDashboardPageObject(driver);
	}

	public SpecialCssPageObject openSpecialCss() {
		getUrl(Global.DOMAIN + URLsContent.specialCSS);
		return new SpecialCssPageObject(driver);
	}

	public SpecialUploadPageObject openSpecialUpload() {
		getUrl(Global.DOMAIN + URLsContent.specialUpload);
		return new SpecialUploadPageObject(driver);
	}

	public SpecialCreatePagePageObject openSpecialCreateBlogPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialCreateBlogPage);
		return new SpecialCreatePagePageObject(driver);
	}

	public SpecialWikiActivityPageObject openSpecialWikiActivity(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialWikiActivity);
		return new SpecialWikiActivityPageObject(driver);
	}

	public SpecialFBConnectPageObject openSpecialFBConnectPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialConnect);
		return new SpecialFBConnectPageObject(driver);
	}

	public ForumPageObject openForumMainPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialForum);
		PageObjectLogging.log("openForumPage", "forum page opened", true);
		return new ForumPageObject(driver);
	}


	public SpecialMultiWikiFinderPageObject openSpecialMultiWikiFinderPage(String wikiURL){
		getUrl(wikiURL + URLsContent.specialMultiWikiFinderPage);
		PageObjectLogging.log(
			"openSpecialMultiWikiFinderPage",
			"Special MultiWikiFinder page was opened",
			true
		);
		return new SpecialMultiWikiFinderPageObject(driver);
	}

	public SpecialMultipleUploadPageObject openSpecialMultipleUpload() {
		getUrl(Global.DOMAIN + URLsContent.specialMultipleUpload);
		return new SpecialMultipleUploadPageObject(driver);
	}

	public FilePagePageObject openFilePage(String wikiURL, String fileName) {
		getUrl(wikiURL + URLsContent.wikiDir + URLsContent.fileNameSpace + fileName);
		return new FilePagePageObject(driver);
	}

	public NewMessageWall openMessageWall(String userName, String wikiURL) {
		getUrl(wikiURL + URLsContent.userMessageWall + userName);
		return new NewMessageWall(driver);
	}

	private void clickContributeButton() {
		executeScript("document.querySelectorAll(\".wikia-menu-button\")[0].click()");
		executeScript("document.querySelectorAll(\".wikia-menu-button\")[0].click()");
		waitForElementByElement(createArticleButton);
		PageObjectLogging.log("clickOnContributeButton",
				"contribute button clicked", true);
	}

	public WikiArticleEditMode clickEditButton() {
		mouseOver("#GlobalNavigation li:nth(1)");
		mouseRelease("#GlobalNavigation li:nth(1)");
		waitForElementByElement(editButton);
		waitForElementClickableByElement(editButton);
		scrollAndClick(editButton);
		PageObjectLogging.log("clickEditButton", "edit button clicked", true, driver);
		return new WikiArticleEditMode(driver);
	}

	public VisualEditModePageObject goToCurrentArticleEditPage() {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				driver.getCurrentUrl(),
				URLsContent.actionEditParameter
			)
		);
		return new VisualEditModePageObject(driver);
	}

	public VisualEditModePageObject goToArticleEditPage(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.wikiDir + article, URLsContent.actionEditParameter
			)
		);
		return new VisualEditModePageObject(driver);
	}

	public VisualEditModePageObject goToArticleDefaultContentEditPage(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				urlBuilder.appendQueryStringToURL(
					wikiURL + URLsContent.wikiDir + article,
					URLsContent.actionEditParameter
			),
			URLsContent.useDefaultFormat)
		);
		return new VisualEditModePageObject(driver);
	}

	public SpecialUserLoginPageObject openSpecialUserLoginOnWiki(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialUserLogin);
		PageObjectLogging.log(
			"SpecialUserLoginOnWiki",
			"Special:UserLogin opened on: " + wikiURL,
			true
		);
		return new SpecialUserLoginPageObject(driver);
	}

    public void verifyUserLoggedIn(String userName) {
		waitForElementByElement(userProfileAvatar);
		waitForElementByElement(navigationLogoutLink);
		PageObjectLogging.log(
				"LgoutLinkPresent",
				"Verify that logout link is present in navigation dropdown",
				true
		);
	}

	protected void clickArticleDeleteConfirmationButton(String articleName) {
		waitForElementByElement(deleteConfirmationButton);
		waitForElementByElement(deleteCommentReasonField);
		deleteCommentReasonField.clear();
		deleteCommentReasonField.sendKeys("QAReason");
		deleteConfirmationButton.click();
	}

	public void deleteTop10List(String top10listName) {
		String top10listURL = driver.getCurrentUrl();
		getUrl(top10listURL + "?action=delete");
		clickArticleDeleteConfirmationButton(top10listName);
		getUrl(top10listURL);
		waitForTextToBePresentInElementByElement(pageDeletedInfo, "has been deleted.");
		PageObjectLogging.log("deleteArticle", "top 10 list: "+top10listName+" has been deleted",
				true, driver);
	}

	public DeletePageObject deletePage() {
		String url = urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.deleteParameter);
		getUrl(url);
		PageObjectLogging.log("deletePage", "delete page opened", true);
		return new DeletePageObject(driver);
	}

	public void renameArticle(String articleName, String articleNewName) {
		getUrl(Global.DOMAIN + "wiki/Special:MovePage/" + articleName);
		waitForElementByElement(renameArticleField);
		waitForElementByElement(confirmRenamePageButton);
		renameArticleField.clear();
		renameArticleField.sendKeys(articleNewName);
		scrollAndClick(confirmRenamePageButton);
	}

	public void verifyEditButtonNotPresent() {
		waitForElementNotVisibleByElement(editButton);
		PageObjectLogging.log(
			"verifyEditButtonNotPresent",
			"edit button is not present", true
		);
	}

	protected void clickRestoreArticleButton() {
		waitForElementByElement(restoreButton);
		scrollAndClick(restoreButton);
		waitForElementByElement(userMessage);
		PageObjectLogging.log(
				"clickUndeleteArticle",
				"undelete article button clicked",
				true, driver
		);
	}

	public SpecialRestorePageObject undeleteByFlashMessage() {
		waitForElementByElement(undeleteLink);
		undeleteLink.click();
		return new SpecialRestorePageObject(driver);
	}

	public SpecialRestorePageObject undeleteFileByUrl(String name, String wikiURL) {
		driver.get(wikiURL + URLsContent.specialUndelete + "/" + URLsContent.fileNameSpace + name);
		return new SpecialRestorePageObject(driver);
	}

	public void verifyCloseNotificationMessage() {
		waitForElementVisibleByElement(flashMessage);
		flashMessageClose.click();
		waitForElementNotVisibleByElement(flashMessage);
	}

	public void verifyNotificationMessage() {
		waitForElementVisibleByElement(flashMessage);
	}

	public SpecialCreateTopListPageObject createNewTop_10_list(String top_10_list_Name) {
		getUrl(Global.DOMAIN + "wiki/Special:CreateTopList/" + top_10_list_Name);
		PageObjectLogging.log("SpecialCreateTopListPageObject",
				"create top 10 list with name: "+top_10_list_Name, true, driver);
		return new SpecialCreateTopListPageObject(driver);

	}

	public WikiArticlePageObject openArticle(String articleName) {
		URI uri;
		try {
			uri = new URI(Global.DOMAIN + "wiki/" + articleName);
			String url = uri.toASCIIString();
			getUrl(url);
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		catch (TimeoutException e) {
			PageObjectLogging.log("OpenArticle",
					"page loads for more than 30 seconds", true);
		}
		PageObjectLogging.log("openArticle", "article " + articleName
				+ " opened", true);
		return new WikiArticlePageObject(driver);
	}

	public ArticlePageObject openArticleByName(String wikiURL, String articleName) {
		getUrl(
				wikiURL +
				URLsContent.wikiDir +
				articleName
		);
		return new ArticlePageObject(driver);
	}

	public BlogPageObject openBlogByName(String wikiURL, String blogTitle, String userName) {
		getUrl(
				wikiURL +
				URLsContent.blogNameSpace.replace("%userName%", userName) +
				blogTitle
		);
		return new BlogPageObject(driver);
	}

	public Top_10_list openTop10List(String topTenListName) {
		URI uri;
		try {
			uri = new URI(Global.DOMAIN + "wiki/" + topTenListName);
			String url = uri.toASCIIString();
			getUrl(url);
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		catch (TimeoutException e) {
			PageObjectLogging.log("openTop10List",
					"page loads for more than 30 seconds", true, driver);
		}
		PageObjectLogging.log("openTop10List", topTenListName
				+ " opened", true);
		return new Top_10_list(driver);
	}

	public WikiArticlePageObject openRandomArticleByUrl() {
		try {
			getUrl(Global.DOMAIN + URLsContent.specialRandom);
		} catch (TimeoutException e) {
			PageObjectLogging.log(
				"Loading page",
				"Page loads for more than 30 seconds", false
			);
		}
		waitForElementByElement(editDropDown);
		PageObjectLogging.log(
				"openRandomArticle",
				"Random article opened",
				true
		);
		return new WikiArticlePageObject(driver, wikiFirstHeader.getText());
	}

	public ArticlePageObject openRandomArticle(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialRandom);
		return new ArticlePageObject(driver);
	}

	public void verifyPermissionsErrorsPresent() {
		waitForElementByElement(premissionErrorMessage);
		PageObjectLogging.log("verifyPermissionsErrors", "premission error found, as expected",
				true, driver);
	}

	public void verifyUrl(String url) {
		waitForStringInURL(url);
	}

	public void openSpecialPage(String specialPage) {
		getUrl(Global.DOMAIN + specialPage);
	}

	public SpecialCreatePagePageObject openSpecialCreatePage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialCreatePage);
		return new SpecialCreatePagePageObject(driver);
	}

	public void verifyLoginReguiredMessage() {
		waitForTextToBePresentInElementByElement(
				wikiFirstHeader, PageContent.loginRequired
		);
		PageObjectLogging.log(
				"LoginRequiredMessage",
				"Login required message in first header present",
				true, driver
		);
	}

	public void clickLoginOnSpecialPage() {
		waitForElementByElement(specialUserLoginLink);
		PageObjectLogging.log(
				"LoginLinkPresent",
				"Link to login special page present",
				true, driver
		);
		scrollAndClick(specialUserLoginLink);
		PageObjectLogging.log(
				"LoginLinkClicked",
				"Link to login special page clicked",
				true, driver
		);
	}

	public void verifyNotLoggedInMessage() {
		waitForTextToBePresentInElementByElement(
				wikiFirstHeader, PageContent.notLoggedInMessage
		);
		PageObjectLogging.log(
				"NotLoggedInMessage",
				"Not logged in message present",
				true, driver
		);
	}

	public void clickContributeNewPage() {
		clickContributeButton();
		waitForElementVisibleByElement(contributeAddPage);
		scrollAndClick(contributeAddPage);
	}

	public void logInViaModal(String userName, String password) {
		waitForElementByElement(modalUserNameInput);
		modalUserNameInput.sendKeys(userName);
		waitForElementByElement(modalPasswordInput);
		modalPasswordInput.sendKeys(password);
		PageObjectLogging.log(
				"FillLoginForm",
				"Login form in modal is filled",
				true, driver
		);

		scrollAndClick(modalLoginSubmit);
		PageObjectLogging.log(
				"LoginFormSubmitted",
				"Login form is submitted",
				true
		);

		waitForElementNotVisibleByElement(logInModal);
		PageObjectLogging.log(
				"LoginModalDissapears",
				"Login modal is no longer visible",
				true
		);
	}

	public String receiveMailWithNewPassowrd() {
		MailFunctions.deleteAllMails(Properties.email, Properties.emailPassword);
		String newPassword = MailFunctions.getPasswordFromMailContent((
				MailFunctions.getFirstMailContent(
						Properties.email, Properties.emailPassword
						)
				)
		);
		PageObjectLogging.log(
				"NewPasswordRecived",
				"New password recived from mail",
				true
		);

		return newPassword;
	}

	protected Boolean checkIfMainPage() {
		WebElement body = driver.findElement(By.cssSelector("body"));
		return (body.getAttribute("class").contains("mainpage"));
	}

	public String getWikiaCssContent() {
		waitForElementByElement(cssSource);
		String source = cssSource.getText();
		PageObjectLogging.log("cssSource", "the following text was get from Wikia.css: "+source, true);
		return source;
	}

	public String getFirstCssRevision() {
		waitForElementByElement(cssEditSummary);
		String summary = cssEditSummary.getText();
		PageObjectLogging.log("cssEditSummary", "the following edit summaty was get from Wikia.css: "+summary, true);
		return summary;
	}

	public void verifyRevisionMarkedAsMinor() {
		waitForElementByElement(cssMinorEdit);
		PageObjectLogging.log("cssEditSummary", "minor edit is marked in first revision", true);
	}

	public void logOut(WebDriver driver) {
		try {
			driver.manage().deleteAllCookies();
			driver.get(Global.DOMAIN + "wiki/Special:UserLogout?noexternals=1");
		} catch (TimeoutException e) {
			PageObjectLogging.log("logOut",
					"page loads for more than 30 seconds", true);
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.cssSelector("a[data-id='login']")));
		PageObjectLogging.log("logOut", "user is logged out", true, driver);
	}

	public String logInCookie(String userName, String password) {
			try {
				DefaultHttpClient httpclient = new DefaultHttpClient();

				HttpPost httpPost = new HttpPost(Global.DOMAIN + "api.php");
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();

				nvps.add(new BasicNameValuePair("action", "login"));
				nvps.add(new BasicNameValuePair("format", "xml"));
				nvps.add(new BasicNameValuePair("lgname", userName));
				nvps.add(new BasicNameValuePair("lgpassword", password));

				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

				HttpResponse response = null;

				response = httpclient.execute(httpPost);

				HttpEntity entity = response.getEntity();
				String xmlResponse = null;

				xmlResponse = EntityUtils.toString(entity);

				String[] xmlResponseArr = xmlResponse.split("\"");
				String token = xmlResponseArr[5];

				// System.out.println(token);

				while (xmlResponseArr.length < 11) {// sometimes first request
					// does
					// not contain full
					// information,
					// in such situation
					// xmlResponseArr.length <
					// 11
					List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();

					nvps2.add(new BasicNameValuePair("action", "login"));
					nvps2.add(new BasicNameValuePair("format", "xml"));
					nvps2.add(new BasicNameValuePair("lgname", userName));
					nvps2.add(new BasicNameValuePair("lgpassword", password));
					nvps2.add(new BasicNameValuePair("lgtoken", token));

					httpPost.setEntity(new UrlEncodedFormEntity(nvps2,
							HTTP.UTF_8));

					response = httpclient.execute(httpPost);

					entity = response.getEntity();

					xmlResponse = EntityUtils.toString(entity);

					xmlResponseArr = xmlResponse.split("\"");
				}

				String domain = (Global.DOMAIN.contains("wikia-dev")) ? "wikia-dev.com" : "wikia.com";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("$.cookie('" + xmlResponseArr[11]
						+ "_session', '" + xmlResponseArr[13]
						+ "', {'domain': '"+domain+"', 'path': '/'})");
				js.executeScript("$.cookie('" + xmlResponseArr[11]
						+ "UserName', '" + xmlResponseArr[7]
						+ "', {'domain': '"+domain+"', 'path': '/'})");
				js.executeScript("$.cookie('" + xmlResponseArr[11]
						+ "UserID', '" + xmlResponseArr[5]
						+ "', {'domain': '"+domain+"', 'path': '/'})");
				js.executeScript("$.cookie('" + xmlResponseArr[11]
						+ "Token', '" + xmlResponseArr[9]
						+ "', {'domain': '"+domain+"' , 'path': '/'})");
				try {
					driver.get(Global.DOMAIN + "Special:Random");
				} catch (TimeoutException e) {
					PageObjectLogging.log("loginCookie",
							"page timeout after login by cookie", true);
				}

				driver.findElement(By
						.cssSelector(".AccountNavigation a[href*='User:"
								+ userName + "']"));// only for verification
				PageObjectLogging.log("loginCookie",
						"user was logged in by cookie", true, driver);
				return xmlResponseArr[11];
			} catch (UnsupportedEncodingException e) {
				PageObjectLogging.log("logInCookie",
						"UnsupportedEncodingException", false);
				return null;
			} catch (ClientProtocolException e) {
				PageObjectLogging.log("logInCookie", "ClientProtocolException",
						false);
				return null;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

	public String logInCookie(String userName, String password, String wikiURL) {
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(wikiURL + "api.php");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			nvps.add(new BasicNameValuePair("action", "login"));
			nvps.add(new BasicNameValuePair("format", "xml"));
			nvps.add(new BasicNameValuePair("lgname", userName));
			nvps.add(new BasicNameValuePair("lgpassword", password));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = null;

			response = httpclient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			String xmlResponse = null;

			xmlResponse = EntityUtils.toString(entity);

			String[] xmlResponseArr = xmlResponse.split("\"");
			String token = xmlResponseArr[5];

			while (xmlResponseArr.length < 11) {// sometimes first request
				// does
				// not contain full
				// information,
				// in such situation
				// xmlResponseArr.length <
				// 11
				List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();

				nvps2.add(new BasicNameValuePair("action", "login"));
				nvps2.add(new BasicNameValuePair("format", "xml"));
				nvps2.add(new BasicNameValuePair("lgname", userName));
				nvps2.add(new BasicNameValuePair("lgpassword", password));
				nvps2.add(new BasicNameValuePair("lgtoken", token));

				httpPost.setEntity(new UrlEncodedFormEntity(nvps2,
						HTTP.UTF_8));

				response = httpclient.execute(httpPost);

				entity = response.getEntity();

				xmlResponse = EntityUtils.toString(entity);

				xmlResponseArr = xmlResponse.split("\"");
			}

			String domain = (wikiURL.contains("wikia-dev")) ? "wikia-dev.com" : "wikia.com";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("$.cookie('" + xmlResponseArr[11]
					+ "_session', '" + xmlResponseArr[13]
					+ "', {'domain': '"+domain+"', 'path': '/'})");
			js.executeScript("$.cookie('" + xmlResponseArr[11]
					+ "UserName', '" + xmlResponseArr[7]
					+ "', {'domain': '"+domain+"', 'path': '/'})");
			js.executeScript("$.cookie('" + xmlResponseArr[11]
					+ "UserID', '" + xmlResponseArr[5]
					+ "', {'domain': '"+domain+"', 'path': '/'})");
			js.executeScript("$.cookie('" + xmlResponseArr[11]
					+ "Token', '" + xmlResponseArr[9]
					+ "', {'domain': '"+domain+"' , 'path': '/'})");
			try {
				driver.get(wikiURL);
			} catch (TimeoutException e) {
				PageObjectLogging.log("loginCookie",
						"page timeout after login by cookie", true);
			}

			driver.findElement(By
					.cssSelector(".AccountNavigation a[href*='User:"
							+ userName + "']"));// only for verification
			PageObjectLogging.log("loginCookie",
					"user was logged in by cookie", true, driver);
			return xmlResponseArr[11];
		} catch (UnsupportedEncodingException e) {
			PageObjectLogging.log("logInCookie",
					"UnsupportedEncodingException", false);
			return null;
		} catch (ClientProtocolException e) {
			PageObjectLogging.log("logInCookie", "ClientProtocolException",
					false);
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public void openWikiPage() {
		getUrl(Global.DOMAIN + URLsContent.noexternals);
		PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);
	}

	public void verifyPageUnfollowed() {
		waitForTextToBePresentInElementByElement(followButton, "Follow");
		PageObjectLogging.log("verifyPageUnfollowed", "page is not followed", true);
	}

	public void follow() {
		waitForElementByElement(followButton);
		jQueryClick(followButton);
		waitForTextToBePresentInElementByElement(followButton, "Following");
		PageObjectLogging.log("followArticle", "page is followed", true, driver);
	}

	public WatchPageObject unfollowCurrentUrl() {
		driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.unfollowParameter));
		return new WatchPageObject(driver);
	}

	public RenamePageObject renameUsingDropdown() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(renameDropdown);
		renameDropdown.click();
		return new RenamePageObject(driver);
	}


	public DeletePageObject deleteUsingDropdown() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(deleteDropdown);
		deleteDropdown.click();
		return new DeletePageObject(driver);
	}

}
