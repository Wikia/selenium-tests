package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNav.VenusGlobalNavPageObject;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.Clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.Common.Clicktracking.ClickTrackingSupport;
import com.wikia.webdriver.Common.ContentPatterns.ApiActions;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.WikiaGlobalVariables;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.DeletePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.RenamePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ChatPageObject.ChatPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Facebook.FacebookMainPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HistoryPage.HistoryPagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWall;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialAdminDashboardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialContributionsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCssPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialEditHubPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFBConnectPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFactoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialManageWikiaHome;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPromotePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialRestorePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialWikiActivityPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialUnblockPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialMostLinkedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUncategorizedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUnusedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUnusedVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.LicensedVideoSwap.LicensedVideoSwapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Multiwikifinder.SpecialMultiWikiFinderPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences.EditingPreferencesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences.PreferencesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VideoHomePage.FeaturedVideoAdminPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VideoHomePage.VideoHomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiHistoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class WikiBasePageObject extends BasePageObject {

	@FindBy(css = "body")
	protected WebElement body;
	@FindBy(css = "a.ajaxRegister")
	private WebElement signUpLink;
	@FindBy(css = "article span.drop")
	private WebElement editDropDown;
	@FindBy(css = "input#wpConfirmB")
	private WebElement deleteConfirmationButton;
	@FindBy(css = ".global-notification div.msg a")
	private WebElement undeleteLink;
	@FindBy(css = ".global-notification")
	private WebElement flashMessage;
	@FindBy(css = "input#mw-undelete-submit")
	private WebElement restoreButton;
	@FindBy(css = "input#wpReason")
	private WebElement deleteCommentReasonField;
	@FindBy(css="div.permissions-errors")
	private WebElement premissionErrorMessage;
	@FindBy(css="div.mw-warning-with-logexcerpt p")
	private WebElement pageDeletedInfo;
	@FindBy(css = ".UserLoginModal input[type='submit']")
	protected WebElement modalLoginSubmit;
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
	@FindBy(css="#userForceLoginModal")
	protected WebElement logInModal;
	@FindBy(css="a[data-id='login']")
	protected WebElement loginButton;
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
	@FindBy(css="#ca-ve-edit")
	protected WebElement veEditButton;
	@FindBy(css="body.ve")
	protected WebElement veMode;
	@FindBy(css=".editsection")
	protected List<WebElement> sectionEditButtons;
	@FindBy(css="a.new[href$='redlink=1']")
	protected List<WebElement> redLinks;
	@FindBy(css="body.rte_wysiwyg")
	protected WebElement rteMode;
	@FindBy(css="body.rte_source")
	protected WebElement srcInRteMode;
	@FindBy(css="body:not(.rte_source):not(.ve):not(.rte_wysiwyg)")
	protected WebElement srcOnlyMode;
	@FindBy(css=".oo-ui-widget-enabled.ve-ui-wikiaFocusWidget")
	protected WebElement focusMode;
	@FindBy(css=".ve-init-mw-viewPageTarget-toolbar")
	protected WebElement veToolMenu;
	@FindBy(css="h3[id='headerWikis']")
	protected WebElement headerWhereIsMyExtensionPage;
	@FindBy(css="#globalNavigation")
	protected WebElement newGlobalNavigation;

	protected By editButtonBy = By.cssSelector("#WikiaMainContent a[data-id='edit']");
	protected By parentBy = By.xpath("./..");

	protected String modalWrapper = "#WikiaConfirm";

	private String loggedInUserSelectorOasis = ".AccountNavigation a[href*=%userName%]";
	private String loggedInUserSelectorMonobook = "#pt-userpage a[href*=%userName%]";
	private String loggedInUserSelectorVenus = ".ajaxLogin.global-navigation-link[title*='%s']";
	
	private VenusGlobalNavPageObject venusGlobalNav;

	public String getWikiUrl() {
		String currentURL = driver.getCurrentUrl();
		return currentURL.substring(0, currentURL.lastIndexOf("wiki/"));
	}

	public enum PositionsVideo {
		left, center, right
	}

	public enum HubName {
		Video_Games, Entertainment, Lifestyle
	}

	public WikiBasePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String resetForgotPasswordTime(String userName, String apiToken) {
		String[][] apiRequestParameters = {
				{"action", ApiActions.apiActionForgotPassword},
				{"user", userName},
				{"token", apiToken},
				{"format", "json"},
		};
		return CommonUtils.sendPost(URLsContent.apiUrl, apiRequestParameters);
	}

	public void verifyModalLoginAppeared() {
		waitForElementByElement(logInModal);
		PageObjectLogging.log("verifyModalLogin", "verify modal login form is displayed", true);
	}

	public SpecialUnusedFilesPageObject openSpecialUnusedFilesPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialUnusedFiles);
		PageObjectLogging.log("openSpecialUnusedFilesPage", URLsContent.specialUnusedFiles + " opened", true);
		return new SpecialUnusedFilesPageObject(driver);
	}

	public VideoHomePageObject openVideoHomePageObject(String wikiURL) {
		getUrl(wikiURL);
		PageObjectLogging.log("openVideoHomePageObject", wikiURL + " opened", true);
		return new VideoHomePageObject(driver);
	}

	public FeaturedVideoAdminPageObject openVideoPageAdminObject(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialVideoPageAdmin);
		PageObjectLogging.log("openVideoPageAdminObject", wikiURL + " opened", true);
		return new FeaturedVideoAdminPageObject(driver);
	}

	public SpecialUnusedVideosPageObject openSpecialUnusedVideosPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialUnusedVideos);
		PageObjectLogging.log("openSpecialUnusedVideosPage", URLsContent.specialUnusedVideos + " opened", true);
		return new SpecialUnusedVideosPageObject(driver);
	}

	public SpecialUncategorizedFilesPageObject openSpecialUncategorizedFilesPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialUncategorizedFiles);
		PageObjectLogging.log("openSpecialUncategorizedFilesPage", URLsContent.specialUncategorizedFiles + " opened", true);
		return new SpecialUncategorizedFilesPageObject(driver);
	}

	public SpecialMostLinkedFilesPageObject openSpecialMostLinkedFilesPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialMostLinkedFiles);
		PageObjectLogging.log("openSpecialMostLinkedFilesPage", URLsContent.specialMostLinkedFiles + " opened", true);
		return new SpecialMostLinkedFilesPageObject(driver);
	}

	public SpecialManageWikiaHome openSpecialManageWikiaHomePage(String wikiCorpSetupURL) {
		getUrl(wikiCorpSetupURL + URLsContent.specialManageWikiaHome);
		PageObjectLogging.log("openCorpSetupHomePage", "Special:ManageWikiaHome opened", true);
		return new SpecialManageWikiaHome(driver);
	}

	public HomePageObject openCorporateHomePage(String wikiCorporateURL) {
		getUrl(wikiCorporateURL);
		PageObjectLogging.log("openCorporateHomePage", "corporate home page opened", true);
		return new HomePageObject(driver);
	}

	public SpecialContributionsPageObject openContributionsPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialContributions);
		PageObjectLogging.log("openContributionsPage", "contributions page is opened", true);
		return new SpecialContributionsPageObject(driver);
	}

	public SpecialBlockListPageObject openSpecialBlockListPage(String wikiURL){
		getUrl(wikiURL + URLsContent.specialBlockList);
		PageObjectLogging.log("Special:BlockList openSpecialBlockListPage", "blocked users list page opened", true);
		return new SpecialBlockListPageObject(driver);
	}


	public SpecialUnblockPageObject openSpecialUnblockPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialUnblock);
		PageObjectLogging.log("openSpecialUnblockPage", "special unblock page opened", true);
		return new SpecialUnblockPageObject(driver);
	}


	public SpecialBlockPageObject openSpecialBlockPage(String wikiURL){
		getUrl(wikiURL + URLsContent.specialBlock);
		PageObjectLogging.log("openSpecialBlockPage", "history page opened", true);
		return new SpecialBlockPageObject(driver);
	}

	public HistoryPagePageObject openFileHistoryPage(String articlePage, String wikiURL) {
		getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.wikiDir + URLsContent.fileNameSpace + articlePage, URLsContent.historyAction));
		PageObjectLogging.log("openFileHistoryPage", "history page opened", true);
		return new HistoryPagePageObject(driver);
	}

	public SignUpPageObject openSpecialSignUpPage(String wikiURL) {
		getUrl(wikiURL);
		signUpLink.click();
		PageObjectLogging.log("openSpecialSignUpPage", "Special:UserSignUp page opened", true);
		return new SignUpPageObject(driver);
	}

	public PreferencesPageObject openSpecialPreferencesPage(String wikiURL){
		getUrl(wikiURL+URLsContent.specialPreferences);
		PageObjectLogging.log("openSpecialPreferencesPage", "Special:Prefereces page opened", true);
		return new PreferencesPageObject(driver);
	}

	public EditingPreferencesPageObject openSpecialEditingPreferencesPage(String wikiURL) {
		getUrl(wikiURL+URLsContent.specialEditingPreferences);
		PageObjectLogging.log("EditingPreferencesPageObject", "Special:Prefereces#mw-prefsection-editing page opened", true);
		return new EditingPreferencesPageObject(driver);
	}

	public SpecialPromotePageObject openSpecialPromotePage(String wikiURL){
		getUrl(wikiURL+URLsContent.specialPromote);
		PageObjectLogging.log("openSpecialPromotePage", "Special:Promote page opened", true);
		return new SpecialPromotePageObject(driver);
	}

	public SpecialUserLoginPageObject openSpecialUserLogin(String wikiURL){
		getUrl(wikiURL + URLsContent.specialUserLogin);
		PageObjectLogging.log("openSpecialUserLogin", "Special:UserLogin page opened", true);
		return new SpecialUserLoginPageObject(driver);
	}

	public UserProfilePageObject openProfilePage(String userName, String wikiURL) {
		getUrl(wikiURL + URLsContent.userProfile.replace("%userName%", userName));
		return new UserProfilePageObject(driver);
	}

	public SpecialVideosPageObject openSpecialVideoPage(String wikiURL){
		getUrl(wikiURL+URLsContent.specialVideos);
		return new SpecialVideosPageObject(driver);
	}

	public SpecialVideosPageObject openSpecialVideoPage(String wikiURL, String queryString){
		String url = urlBuilder.appendQueryStringToURL(wikiURL+URLsContent.specialVideos, queryString);
		getUrl(url);
		return new SpecialVideosPageObject(driver);
	}

	public SpecialVideosPageObject openSpecialVideoPageMostRecent(String wikiURL){
		getUrl(wikiURL+URLsContent.specialVideos+URLsContent.mostRecent);
		return new SpecialVideosPageObject(driver);
	}

	public SpecialNewFilesPageObject openSpecialNewFiles(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialNewFiles);
		return new SpecialNewFilesPageObject(driver);
	}

	public SpecialAdminDashboardPageObject openSpecialAdminDashboard(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialAdminDashboard);
		return new SpecialAdminDashboardPageObject(driver);
	}

	public SpecialCssPageObject openSpecialCss(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialCSS);
		return new SpecialCssPageObject(driver);
	}

	public SpecialUploadPageObject openSpecialUpload(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialUpload);
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

	public SpecialMultipleUploadPageObject openSpecialMultipleUpload(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialMultipleUpload);
		return new SpecialMultipleUploadPageObject(driver);
	}

	public InteractiveMapsPageObject openSpecialInteractiveMaps(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialMaps);
		return new InteractiveMapsPageObject(driver);
	}

	public InteractiveMapPageObject openInteractiveMapById(String wikiURL, Integer id) {
		getUrl(wikiURL + URLsContent.specialMaps + "/" + id);
		return new InteractiveMapPageObject(driver);
	}

	public FilePagePageObject openFilePage(String wikiURL, String fileName) {
		getUrl(wikiURL + URLsContent.wikiDir + URLsContent.fileNameSpace + fileName);
		return new FilePagePageObject(driver);
	}

	public NewMessageWall openMessageWall(String userName, String wikiURL) {
		getUrl(wikiURL + URLsContent.userMessageWall + userName);
		return new NewMessageWall(driver);
	}

	public CreateNewWikiPageObjectStep1 openSpecialCreateNewWikiPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialCreateNewWiki);
		return new CreateNewWikiPageObjectStep1(driver);
	}

	public SpecialFactoryPageObject openWikiFactoryPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialWikiFactory);
		return new SpecialFactoryPageObject(driver);
	}

	public void openSpecialWatchListPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialWatchList);
	}

	public SpecialEditHubPageObject openSpecialEditHub(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialEditHub);
		return new SpecialEditHubPageObject(driver);
	}

	public SourceEditModePageObject openCurrectArticleSourceMode() {
		String queryStrings [] = {URLsContent.actionEditParameter, URLsContent.sourceMode};
		appendMultipleQueryStringsToUrl(queryStrings);
		return new SourceEditModePageObject(driver);
	}

	public SourceEditModePageObject openSrcModeWithMainEditButton() {
		waitForElementByElement(editButton);
		editButton.click();
		PageObjectLogging.log("openSrcModeWithMainEditButton", "Src main edit button clicked", true, driver);
		return new SourceEditModePageObject(driver);
	}

	public VisualEditModePageObject openCKModeWithMainEditButton() {
		waitForElementByElement(editButton);
		editButton.click();
		PageObjectLogging.log("openCKModeWithMainEditButton", "CK main edit button clicked", true, driver);
		return new VisualEditModePageObject(driver);
	}

	public VisualEditorPageObject openVEModeWithMainEditButton() {
		waitForElementByElement(veEditButton);
		veEditButton.click();
		PageObjectLogging.log("openVEModeWithMainEditButton", "VE main edit button clicked", true, driver);
		return new VisualEditorPageObject(driver);
	}

	public VisualEditorPageObject openVEModeWithSectionEditButton(int section) {
		WebElement sectionEditButton = sectionEditButtons.get(section);
		waitForElementClickableByElement(sectionEditButton);
		sectionEditButton.click();
		PageObjectLogging.log(
			"openVEModeWithSectionEditButton",
			"VE edit button clicked at section: " + section,
			true,
			driver
		);
		return new VisualEditorPageObject(driver);
	}

	public VisualEditModePageObject openCKModeWithSectionEditButton(int section) {
		WebElement sectionEditButton = sectionEditButtons.get(section);
		waitForElementByElement(sectionEditButton);
		sectionEditButton.click();
		PageObjectLogging.log(
			"openCKModeWithSectionEditButton",
			"RTE edit button clicked at section: " + section,
			true,
			driver
		);
		return new VisualEditModePageObject(driver);
	}

	public SourceEditModePageObject openSrcModeWithSectionEditButton(int section) {
		WebElement sectionEditButton = sectionEditButtons.get(section);
		waitForElementByElement(sectionEditButton);
		sectionEditButton.click();
		PageObjectLogging.log(
			"openSrcModeWithSectionEditButton",
			"Src edit button clicked at section: " + section,
			true,
			driver
		);
		return new SourceEditModePageObject(driver);
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

	public VisualEditModePageObject navigateToArticleEditPageCK(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.wikiDir + article, URLsContent.actionEditParameter
			)
		);
		return new VisualEditModePageObject(driver);
	}

	public SourceEditModePageObject navigateToArticleEditPageSrc(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.wikiDir + article, URLsContent.actionEditParameter
			)
		);
		return new SourceEditModePageObject(driver);
	}

	public VisualEditModePageObject goToArticleDefaultContentEditPage(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				urlBuilder.appendQueryStringToURL(
					wikiURL + URLsContent.wikiDir + article,
					URLsContent.actionEditParameter
				),
				URLsContent.useDefaultFormat
			)
		);
		return new VisualEditModePageObject(driver);
	}

	/**
	 * method used to navigate to new visual editor
	 * @param wikiURL
	 * @param article
	 */
	public VisualEditorPageObject navigateToArticleEditModeVisual(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.wikiDir + article, URLsContent.actionVisualEditParameter
			)
		);
		return new VisualEditorPageObject(driver);
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

	public LicensedVideoSwapPageObject openLicensedVideoSwap (String wikiURL) {
		getUrl(wikiURL + URLsContent.specialLicensedVideoSwap);
		PageObjectLogging.log(
			"LicensedVideoSwapPageObject",
			"Special:LicensedVideoSwap opened on: " + wikiURL,
			true
		);
		return new LicensedVideoSwapPageObject(driver);
	}

	public void verifyAvatarPresent() {
		waitForElementByElement(userProfileAvatar);
		PageObjectLogging.log(
			"verifyAvatarPresent",
			"avatar is visible",
			true
		);
	}

	public void verifyUserLoggedIn(String userName) {
		if (body.getAttribute("class").contains("skin-monobook")) {
			driver.findElement(
				By.cssSelector(loggedInUserSelectorMonobook.replace("%userName%", userName.replace(" ", "_"))));// only for verification
		}
		else {
			//oasis
			driver.findElement(
				By.cssSelector(loggedInUserSelectorOasis.replace("%userName%", userName.replace(" ", "_"))));// only for verification
		}
		PageObjectLogging.log(
				"verifyUserLoggedIn",
				"user " + userName + " logged in",
				true
		);
	}

	protected void clickArticleDeleteConfirmationButton() {
		waitForElementByElement(deleteConfirmationButton);
		waitForElementByElement(deleteCommentReasonField);
		deleteCommentReasonField.clear();
		deleteCommentReasonField.sendKeys("QAReason");
		deleteConfirmationButton.click();
	}

	public DeletePageObject deletePage() {
		String url = urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.deleteParameter);
		getUrl(url);
		PageObjectLogging.log("deletePage", "delete page opened", true);
		return new DeletePageObject(driver);
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

	public void verifyNotificationMessage() {
		driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
		try {
			waitForElementVisibleByElement(flashMessage);
		}finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public String getFlashMessageText() {
		return flashMessage.getText();
	}

	public ArticlePageObject openArticleByName(String wikiURL, String articleName) {
		getUrl(wikiURL + URLsContent.wikiDir + articleName);
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

	public ChatPageObject openChat(String wikiURL) {
		getUrl(wikiURL + URLsContent.specialChat);
		return new ChatPageObject(driver);
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

	public SpecialUserLoginPageObject clickLoginOnSpecialPage() {
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

		return new SpecialUserLoginPageObject(driver);
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

	public String receiveMailWithNewPassowrd(String email, String password) {
		MailFunctions.deleteAllEmails(email, password);
		String newPassword = MailFunctions.getPasswordFromEmailContent((
				MailFunctions.getFirstEmailContent(
						email, password
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
		if(checkIfElementOnPage(cssMinorEdit)) {
			PageObjectLogging.log("cssEditSummary", "minor edit is marked in first revision", true);
		} else {
			throw new NoSuchElementException("Minor Edit is not present on the page");
		}
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

	public void logOut(String wikiURL) {
		try {
			getUrl(wikiURL + URLsContent.logout);
		} catch (TimeoutException e) {
			PageObjectLogging.log("logOut",
					"page loads for more than 30 seconds", true);
		}
		waitForElementByElement(loginButton);
		PageObjectLogging.log("logOut", "user is logged out", true, driver);
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
			String token;
			//Insert here for logging responses -- QAART 371
			try {
				token = xmlResponseArr[5];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new WebDriverException(
					"No token received from request. HTTP response is " + response.toString() +
					", xmlReponse is " + xmlResponse);
			}

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

				if (xmlResponse.contains("WrongPass")) {
					throw new WebDriverException("Incorrect password provided for user: " + userName);
				}
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

			verifyUserLoggedIn(userName);

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
			PageObjectLogging.log("logInCookie", e.getMessage(), false);
			return null;
		} catch (IOException e) {
			PageObjectLogging.log("logInCookie", e.getMessage(), false);
			return null;
		}
	}

	public void openWikiPage(String wikiURL) {
		getUrl(wikiURL);
		PageObjectLogging.log("openWikiPage", "Wiki page is opened", true);
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

	public String getHeaderText() {
		waitForElementByElement(wikiFirstHeader);
		return wikiFirstHeader.getText();
	}

	public void verifyHeader(String fileName) {
		waitForElementByElement(wikiFirstHeader);
		Assertion.assertStringContains(fileName, wikiFirstHeader.getText());
	}

	public void disableCaptcha() {
		String url = urlBuilder.appendQueryStringToURL(
			driver.getCurrentUrl(),
			URLsContent.disableCaptchaParameter
		);
		getUrl(url);
	}

	public HubBasePageObject openHubByUrl(String HubUrl) {
		getUrl(HubUrl);
		return new HubBasePageObject(driver);
	}

	public FacebookMainPageObject openFacebookMainPage() {
		getUrl(URLsContent.facebookMainPage);
		return new FacebookMainPageObject(driver);
	}

	public String getNameForArticle () {
		return PageContent.articleNamePrefix + getTimeStamp();
	}

	public void openSpecialPromoteOnCurrentWiki() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String url = (String) js.executeScript("return wgServer");
		getUrl(url + "/" + URLsContent.specialPromote);
		PageObjectLogging.log("openSpecialPromote", "special promote page opened", true);
	}

	public void verifyWgVariableValuesTheSame(Object[] value1, Object[] value2) {
		Arrays.sort(value1);
		Arrays.sort(value2);

		if (Arrays.equals(value1, value2)) {
			PageObjectLogging.log(
				"VariablesAreTheSame",
				"Variable on wiki and on community are the same",
				true
			);
		} else {
			throw new WebDriverException("Values on community and on wiki are different");
		}
	}

	public Object[] getWgVariableKeysFromPage(String url, String variableName) {
		getUrl(url);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Integer> variableValueFromPage = (Map<String, Integer>) js.executeScript(
			"return eval(arguments[0])", variableName
		);
		return variableValueFromPage.keySet().toArray();
	}

	protected Object[] extractKeysFromWgVariable(String variableValue) {
		List<String> keysFromDefaultList = new ArrayList<>();
		Pattern pattern = Pattern.compile("\'.*\'");
		Matcher matcher = pattern.matcher(variableValue);
		while (matcher.find()) {
			keysFromDefaultList.add(matcher.group().replaceAll("'", ""));
		}
		return keysFromDefaultList.toArray();
	}

	public VisualEditorPageObject openNewArticleEditModeVisual(String wikiURL) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.wikiDir +	getNameForArticle(),
				URLsContent.actionVisualEditParameter
			)
		);
		return new VisualEditorPageObject(driver);
	}

	public VisualEditorPageObject openNewArticleEditModeVisualWithRedlink(String wikiURL) {
		String randomArticle = wikiURL + URLsContent.wikiDir + getNameForArticle();
		String randomArticleWithVETrigger = urlBuilder.appendQueryStringToURL(
			randomArticle, URLsContent.actionVisualEditParameter
		);
		String randomArticleWithVEAndRedLink = urlBuilder.appendQueryStringToURL(
			randomArticleWithVETrigger, URLsContent.redLink
		);
		getUrl(randomArticleWithVEAndRedLink);
		return new VisualEditorPageObject(driver);
	}

	public void addVideoViaAjax(String videoURL) {
		executeScript("$.ajax('" + getWikiUrl() + "wikia.php?controller=Videos&method=addVideo&format=json', {" +
				"data: {url: '" + videoURL + "'}," +
				"type: 'POST' } );");
	}

	/**
	 * Refresh the page limit number of times until element found by cssSelector appears. Return true of false
	 * depending on success of finding element.
	 * @param cssSelector
	 * @param limit
	 * @return bool
	 */
	public boolean refreshUntilElementOnPage(String cssSelector, int limit) {
		for (int refreshCount = 0; refreshCount < limit; refreshCount++) {
			if (checkIfElementOnPage(cssSelector)) {
				return true;
			}
			refreshPage();
		}
		return false;
	}

	/**
	 * this method should be called after clicktracking test, in order
	 * to verify if expected events were tracked
	 * @author Michal 'justnpT' Nowierski
	 */
	public void compareTrackedEventsTo(List<JsonObject> expectedEventsList){
		executeScript(ClickTrackingScriptsProvider.eventsCaptureInstallation);
		ArrayList<JsonObject> trackedEventsArrayList = new ArrayList<JsonObject>();
		List<JsonObject> trackedEventsList;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//prepare list of tracked events
		Object event = js.executeScript("return selenium_popEvent()");
		StringReader reader = new StringReader(event.toString());
		JsonReader jsonReader = Json.createReader(reader);
		while (!(event == null)) {
			reader = new StringReader(event.toString());
			jsonReader = Json.createReader(reader);
			trackedEventsArrayList.add(jsonReader.readObject());
			// take next tracked event
			event = js.executeScript("return selenium_popEvent()");
		}
		trackedEventsList = trackedEventsArrayList;
		ClickTrackingSupport support = new ClickTrackingSupport();
		support.compare(expectedEventsList, trackedEventsList);
	}

	public void verifyVEPublishComplete () {
		waitForElementNotVisibleByElement(veMode);
		waitForElementNotVisibleByElement(focusMode);
		waitForElementNotVisibleByElement(veToolMenu);
		PageObjectLogging.log("verifyVEPublishComplete", "Publish is done", true, driver);
	}

	public void disableOptimizely() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window['optimizely'].push(['disable']);");
	}

	public VisualEditorPageObject launchVisualEditorWithMainEdit(String articleName, String wikiURL) {
		ArticlePageObject article = openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		return new VisualEditorPageObject(driver);
	}

	public WikiHistoryPageObject openArticleHistoryPage() {
		getUrl(urlBuilder.appendQueryStringToURL(getCurrentUrl(), URLsContent.historyAction));
		return new WikiHistoryPageObject(driver);
	}

	private String getArticleName() {
		return executeScriptRet(WikiaGlobalVariables.wgPageName);
	}

	public void verifyArticleName(String targetText) {
		Assertion.assertStringContains(getArticleName(), targetText);
		PageObjectLogging.log(
			"verifyArticleName",
			"The article shows " + targetText,
			true
		);
	}

	public void verifyNumberOfTop1kWikis(Integer numberOfWikis){
		String pattern = "List of wikis with matched criteria ("+numberOfWikis+")";
		waitForElementByElement(headerWhereIsMyExtensionPage);
		PageObjectLogging.log(
			"verifyNumberOfTop1kWikis",
			"Verification of top 1k wikis",
			true,
			driver
		);
		Assertion.assertStringContains(pattern, headerWhereIsMyExtensionPage.getText());
	}

	protected Boolean isNewGlobalNavPresent() {
		return checkIfElementOnPage(newGlobalNavigation);
	}

	public void setCookie(String name, String value) {
		Cookie newCookie= new Cookie(name, value);
		driver.manage().addCookie(newCookie);
		PageObjectLogging.log("setCookie", "Set cookie: '" + name + "' to " + value, true);
	}

	public void deleteCookie(String name) {
		driver.manage().deleteCookieNamed(name);
		PageObjectLogging.log("deleteCookie", "Remove '" + name + "' from cookie", true);
	}

	public void setCookieGeo(String countryCode) {
		String cookieName = "Geo";
		try {
			JSONObject geo = new JSONObject();
			geo.put("country", countryCode);
			setCookie(cookieName, geo.toString());
		} catch (JSONException ex) {
			PageObjectLogging.log("setCookieGeo", "Cannot set cookie ('" + cookieName + "')", true);
		}
	}

	public void resizeWindow(int width, int height) {
		try {
			driver.manage().window().setSize(new Dimension(width, height));
			PageObjectLogging.log("ResizeWindow", "Resize window (width=" + width + ", height=" + height + ")", true);
		} catch (WebDriverException ex) {
			PageObjectLogging.log("ResizeWindow", "Cannot resize window (width=" + width + ", height=" + height + ")", true);
		}
	}

	public void resizeWindow(Dimension resolution) {
		resizeWindow(resolution.width, resolution.height);
	}
	
	public VenusGlobalNavPageObject getVenusGlobalNav() {
		if(venusGlobalNav==null){
			venusGlobalNav = new VenusGlobalNavPageObject(driver);
		}

		return venusGlobalNav;
	}
}
