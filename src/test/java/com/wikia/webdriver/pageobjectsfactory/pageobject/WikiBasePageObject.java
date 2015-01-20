package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.common.clicktracking.ClickTrackingSupport;
import com.wikia.webdriver.common.contentpatterns.ApiActions;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.WikiaGlobalVariables;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.Global;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.chatpageobject.ChatPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.facebook.FacebookMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.VenusGlobalNavPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.historypage.HistoryPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.*;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockListPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialUnblockPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialMostLinkedFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUncategorizedFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUnusedFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUnusedVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.licensedvideoswap.LicensedVideoSwapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.multiwikifinder.SpecialMultiWikiFinderPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.EditingPreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage.FeaturedVideoAdminPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage.VideoHomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiHistoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiBasePageObject extends BasePageObject {

	@FindBy(css = "body")
	protected WebElement body;
	@FindBy(css = "a.ajaxRegister")
	private WebElement signUpLink;
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
	@FindBy(css = "div.permissions-errors")
	private WebElement premissionErrorMessage;
	@FindBy(css = ".UserLoginModal input[type='submit']")
	protected WebElement modalLoginSubmit;
	@FindBy(css = ".UserLoginModal input[name='password']")
	protected WebElement modalPasswordInput;
	@FindBy(css = "#WikiaPageHeader h1")
	protected WebElement wikiFirstHeader;
	@FindBy(css = "#WikiaArticle a[href*='Special:UserLogin']")
	private WebElement specialUserLoginLink;
	@FindBy(css = ".UserLoginModal input[name='username']")
	protected WebElement modalUserNameInput;
	@FindBy(css = "#AccountNavigation > li > a > .avatar")
	protected WebElement userProfileAvatar;
	@FindBy(css = "#AccountNavigation > li > a ~ ul > li > a[data-id='logout']")
	protected WebElement navigationLogoutLink;
	@FindBy(css = "#userForceLoginModal")
	protected WebElement logInModal;
	@FindBy(css = "#WikiaMainContent a[data-id='edit']")
	protected WebElement editButton;
	@FindBy(css = ".msg")
	protected WebElement userMessage;
	@FindBy(css = "#mw-content-text .source-css")
	protected WebElement cssSource;
	@FindBy(css = "ul#pagehistory > li:first-child .comment")
	protected WebElement cssEditSummary;
	@FindBy(css = "ul#pagehistory > li:first-child .minoredit")
	protected WebElement cssMinorEdit;
	@FindBy(css = "#ca-watch")
	protected WebElement followButton;
	@FindBy(css = "#WikiaMainContent .drop img")
	protected WebElement articleEditDropdown;
	@FindBy(css = "#ca-delete")
	protected WebElement deleteDropdown;
	@FindBy(css = "#ca-protect")
	protected WebElement protectDropdown;
	@FindBy(css = "#ca-move")
	protected WebElement renameDropdown;
	@FindBy(css = "#ca-ve-edit")
	protected WebElement veEditButton;
	@FindBy(css = "body.ve")
	protected WebElement veMode;
	@FindBy(css = ".editsection")
	protected List<WebElement> sectionEditButtons;
	@FindBy(css = "a.new[href$='redlink=1']")
	protected List<WebElement> redLinks;
	@FindBy(css = "body.rte_wysiwyg")
	protected WebElement rteMode;
	@FindBy(css = "body.rte_source")
	protected WebElement srcInRteMode;
	@FindBy(css = "body:not(.rte_source):not(.ve):not(.rte_wysiwyg)")
	protected WebElement srcOnlyMode;
	@FindBy(css = ".oo-ui-widget-enabled.ve-ui-wikiaFocusWidget")
	protected WebElement focusMode;
	@FindBy(css = ".ve-init-mw-viewPageTarget-toolbar")
	protected WebElement veToolMenu;
	@FindBy(css = "h3[id='headerWikis']")
	protected WebElement headerWhereIsMyExtensionPage;
	@FindBy(css = "#globalNavigation")
	protected WebElement newGlobalNavigation;

	protected final static By LOGIN_BUTTON_CSS = By.cssSelector("a[data-id='login']");

	protected By editButtonBy = By.cssSelector("#WikiaMainContent a[data-id='edit']");
	protected By parentBy = By.xpath("./..");

	protected String modalWrapper = "#WikiaConfirm";

	private String loggedInUserSelectorVenus = ".AccountNavigation a[href*=%userName%]";
	private String loggedInUserSelectorMonobook = "#pt-userpage a[href*=%userName%]";

	private VenusGlobalNavPageObject venusGlobalNav;

	public String getWikiUrl() {
		String currentURL = driver.getCurrentUrl();
		return currentURL.substring(0, currentURL.lastIndexOf("wiki/"));
	}

	public enum PositionsVideo {
		LEFT, CENTER, RIGHT
	}

	public enum StyleVideo {
		CAPTION, NOCAPTION;
	}

	public enum HubName {
		VIDEO_GAMES, ENTERTAINMENT, LIFESTYLE
	}

	public WikiBasePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String resetForgotPasswordTime(String userName, String apiToken) {
		String[][] apiRequestParameters = {
			{"action", ApiActions.API_ACTION_FORGOT_PASSWORD},
			{"user", userName},
			{"token", apiToken},
			{"format", "json"},
		};
		return CommonUtils.sendPost(URLsContent.API_URL, apiRequestParameters);
	}

	public void verifyModalLoginAppeared() {
		waitForElementByElement(logInModal);
		PageObjectLogging.log("verifyModalLogin", "verify modal login form is displayed", true);
	}

	public SpecialUnusedFilesPageObject openSpecialUnusedFilesPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_UNUSED_FILES);
		PageObjectLogging.log("openSpecialUnusedFilesPage", URLsContent.SPECIAL_UNUSED_FILES + " opened", true);
		return new SpecialUnusedFilesPageObject(driver);
	}

	public VideoHomePageObject openVideoHomePageObject(String wikiURL) {
		getUrl(wikiURL);
		PageObjectLogging.log("openVideoHomePageObject", wikiURL + " opened", true);
		return new VideoHomePageObject(driver);
	}

	public FeaturedVideoAdminPageObject openVideoPageAdminObject(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_VIDEO_PAGE_ADMIN);
		PageObjectLogging.log("openVideoPageAdminObject", wikiURL + " opened", true);
		return new FeaturedVideoAdminPageObject(driver);
	}

	public SpecialUnusedVideosPageObject openSpecialUnusedVideosPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_UNUSED_VIDEOS);
		PageObjectLogging.log("openSpecialUnusedVideosPage", URLsContent.SPECIAL_UNUSED_VIDEOS + " opened", true);
		return new SpecialUnusedVideosPageObject(driver);
	}

	public SpecialUncategorizedFilesPageObject openSpecialUncategorizedFilesPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_UNCATEGORIZED_FILES);
		PageObjectLogging.log("openSpecialUncategorizedFilesPage", URLsContent.SPECIAL_UNCATEGORIZED_FILES + " opened", true);
		return new SpecialUncategorizedFilesPageObject(driver);
	}

	public SpecialMostLinkedFilesPageObject openSpecialMostLinkedFilesPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_MOST_LINKED_FILES);
		PageObjectLogging.log("openSpecialMostLinkedFilesPage", URLsContent.SPECIAL_MOST_LINKED_FILES + " opened", true);
		return new SpecialMostLinkedFilesPageObject(driver);
	}

	public SpecialManageWikiaHome openSpecialManageWikiaHomePage(String wikiCorpSetupURL) {
		getUrl(wikiCorpSetupURL + URLsContent.SPECIAL_MANAGE_WIKIA_HOME);
		PageObjectLogging.log("openCorpSetupHomePage", "Special:ManageWikiaHome opened", true);
		return new SpecialManageWikiaHome(driver);
	}

	public HomePageObject openCorporateHomePage(String wikiCorporateURL) {
		getUrl(wikiCorporateURL);
		PageObjectLogging.log("openCorporateHomePage", "corporate home page opened", true);
		return new HomePageObject(driver);
	}

	public SpecialContributionsPageObject openContributionsPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_CONTRIBUTIONS);
		PageObjectLogging.log("openContributionsPage", "contributions page is opened", true);
		return new SpecialContributionsPageObject(driver);
	}

	public SpecialBlockListPageObject openSpecialBlockListPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_BLOCKLIST);
		PageObjectLogging.log("Special:BlockList openSpecialBlockListPage", "blocked users list page opened", true);
		return new SpecialBlockListPageObject(driver);
	}


	public SpecialUnblockPageObject openSpecialUnblockPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_UNBLOCK);
		PageObjectLogging.log("openSpecialUnblockPage", "special unblock page opened", true);
		return new SpecialUnblockPageObject(driver);
	}


	public SpecialBlockPageObject openSpecialBlockPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_BLOCK);
		PageObjectLogging.log("openSpecialBlockPage", "history page opened", true);
		return new SpecialBlockPageObject(driver);
	}

	public HistoryPagePageObject openFileHistoryPage(String articlePage, String wikiURL) {
		getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + articlePage, URLsContent.ACTION_HISTORY));
		PageObjectLogging.log("openFileHistoryPage", "history page opened", true);
		return new HistoryPagePageObject(driver);
	}

	public SignUpPageObject openSpecialSignUpPage(String wikiURL) {
		getUrl(wikiURL);
		getVenusGlobalNav().signUp();
		PageObjectLogging.log("openSpecialSignUpPage", "Special:UserSignUp page opened", true);
		return new SignUpPageObject(driver);
	}

	public PreferencesPageObject openSpecialPreferencesPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_PREFERENCES);
		PageObjectLogging.log("openSpecialPreferencesPage", "Special:Prefereces page opened", true);
		return new PreferencesPageObject(driver);
	}

	public EditingPreferencesPageObject openSpecialEditingPreferencesPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_EDITING_PREFERENCES);
		PageObjectLogging.log("EditingPreferencesPageObject", "Special:Prefereces#mw-prefsection-editing page opened", true);
		return new EditingPreferencesPageObject(driver);
	}

	public SpecialPromotePageObject openSpecialPromotePage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_PROMOTE);
		PageObjectLogging.log("openSpecialPromotePage", "Special:Promote page opened", true);
		return new SpecialPromotePageObject(driver);
	}

	public SpecialUserLoginPageObject openSpecialUserLogin(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_USER_LOGIN);
		PageObjectLogging.log("openSpecialUserLogin", "Special:UserLogin page opened", true);
		return new SpecialUserLoginPageObject(driver);
	}

	public UserProfilePageObject openProfilePage(String userName, String wikiURL) {
		getUrl(wikiURL + URLsContent.USER_PROFILE.replace("%userName%", userName));
		return new UserProfilePageObject(driver);
	}

	public SpecialVideosPageObject openSpecialVideoPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_VIDEOS);
		return new SpecialVideosPageObject(driver);
	}

	public SpecialVideosPageObject openSpecialVideoPage(String wikiURL, String queryString) {
		String url = urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.SPECIAL_VIDEOS, queryString);
		getUrl(url);
		return new SpecialVideosPageObject(driver);
	}

	public SpecialVideosPageObject openSpecialVideoPageMostRecent(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_VIDEOS + URLsContent.MOST_RECENT);
		return new SpecialVideosPageObject(driver);
	}

	public SpecialNewFilesPageObject openSpecialNewFiles(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_NEW_FILES);
		return new SpecialNewFilesPageObject(driver);
	}

	public SpecialAdminDashboardPageObject openSpecialAdminDashboard(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_ADMIN_DASHBOARD);
		return new SpecialAdminDashboardPageObject(driver);
	}

	public SpecialCssPageObject openSpecialCss(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_CSS);
		return new SpecialCssPageObject(driver);
	}

	public SpecialUploadPageObject openSpecialUpload(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_UPLOAD);
		return new SpecialUploadPageObject(driver);
	}

	public SpecialCreatePagePageObject openSpecialCreateBlogPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_CREATE_BLOGPAGE);
		return new SpecialCreatePagePageObject(driver);
	}

	public SpecialWikiActivityPageObject openSpecialWikiActivity(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_WIKI_ACTIVITY);
		return new SpecialWikiActivityPageObject(driver);
	}

	public ForumPageObject openForumMainPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_FORUM);
		PageObjectLogging.log("openForumPage", "forum page opened", true);
		return new ForumPageObject(driver);
	}

	public SpecialMultiWikiFinderPageObject openSpecialMultiWikiFinderPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_MULTI_WIKI_FINDER);
		PageObjectLogging.log(
			"openSpecialMultiWikiFinderPage",
			"Special MultiWikiFinder page was opened",
			true
		);
		return new SpecialMultiWikiFinderPageObject(driver);
	}

	public SpecialMultipleUploadPageObject openSpecialMultipleUpload(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_MULTIPLE_UPLOAD);
		return new SpecialMultipleUploadPageObject(driver);
	}

	public InteractiveMapsPageObject openSpecialInteractiveMaps(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_MAPS);
		return new InteractiveMapsPageObject(driver);
	}

	public InteractiveMapPageObject openInteractiveMapById(String wikiURL, Integer id) {
		getUrl(wikiURL + URLsContent.SPECIAL_MAPS + "/" + id);
		return new InteractiveMapPageObject(driver);
	}

	public FilePagePageObject openFilePage(String wikiURL, String fileName) {
		getUrl(wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + fileName);
		return new FilePagePageObject(driver);
	}

	public NewMessageWall openMessageWall(String userName, String wikiURL) {
		getUrl(wikiURL + URLsContent.USER_MESSAGE_WALL + userName);
		return new NewMessageWall(driver);
	}

	public CreateNewWikiPageObjectStep1 openSpecialCreateNewWikiPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_CREATE_NEW_WIKI);
		return new CreateNewWikiPageObjectStep1(driver);
	}

	public SpecialFactoryPageObject openWikiFactoryPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_WIKI_FACTORY, true);
		return new SpecialFactoryPageObject(driver);
	}

	public void openSpecialWatchListPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_WATCHLIST);
	}

	public SpecialEditHubPageObject openSpecialEditHub(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_EDIT_HUB);
		return new SpecialEditHubPageObject(driver);
	}

	public SourceEditModePageObject openCurrectArticleSourceMode() {
		String queryStrings[] = {URLsContent.ACTION_EDIT, URLsContent.SOURCE_MODE};
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
				URLsContent.ACTION_EDIT
			)
		);
		return new VisualEditModePageObject(driver);
	}

	public VisualEditModePageObject navigateToArticleEditPageCK(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.WIKI_DIR + article, URLsContent.ACTION_EDIT
			));
		return new VisualEditModePageObject(driver);
	}

	public SourceEditModePageObject navigateToArticleEditPageSrc(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.WIKI_DIR + article, URLsContent.ACTION_EDIT
			)
		);
		return new SourceEditModePageObject(driver);
	}

	public VisualEditModePageObject goToArticleDefaultContentEditPage(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				urlBuilder.appendQueryStringToURL(
					wikiURL + URLsContent.WIKI_DIR + article,
					URLsContent.ACTION_EDIT
				),
				URLsContent.USE_DEFAULT_FORMAT
			)
		);
		return new VisualEditModePageObject(driver);
	}

	/**
	 * method used to navigate to new visual editor
	 *
	 * @param wikiURL
	 * @param article
	 */
	public VisualEditorPageObject openVEOnArticle(String wikiURL, String article) {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				wikiURL + URLsContent.WIKI_DIR + article, URLsContent.VEACTION_EDIT
			)
		);
		return new VisualEditorPageObject(driver);
	}

	public SpecialUserLoginPageObject openSpecialUserLoginOnWiki(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_USER_LOGIN);
		PageObjectLogging.log(
			"SpecialUserLoginOnWiki",
			"Special:UserLogin opened on: " + wikiURL,
			true
		);
		return new SpecialUserLoginPageObject(driver);
	}

	public LicensedVideoSwapPageObject openLicensedVideoSwap(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_LICENSED_VIDEO_SWAP);
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
		} else {
			//Venus
			driver.findElement(
				By.cssSelector(loggedInUserSelectorVenus.replace("%userName%", userName)));// only for verification
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
		String url = urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_DELETE);
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
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public String getFlashMessageText() {
		return flashMessage.getText();
	}

	public ArticlePageObject openArticleByName(String wikiURL, String articleName) {
		getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
		return new ArticlePageObject(driver);
	}

	public BlogPageObject openBlogByName(String wikiURL, String blogTitle, String userName) {
		getUrl(
			wikiURL +
				URLsContent.BLOG_NAMESPACE.replace("%userName%", userName) +
				blogTitle
		);
		return new BlogPageObject(driver);
	}

	public ChatPageObject openChat(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_CHAT);
		return new ChatPageObject(driver);
	}


	public ArticlePageObject openRandomArticle(String wikiURL) {
		getUrl(wikiURL + URLsContent.SPECIAL_RANDOM);
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
		getUrl(wikiURL + URLsContent.SPECIAL_CREATE_PAGE);
		return new SpecialCreatePagePageObject(driver);
	}

	public void verifyLoginReguiredMessage() {
		waitForTextToBePresentInElementByElement(
			wikiFirstHeader, PageContent.LOGIN_REQUIRED
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
			wikiFirstHeader, PageContent.NOT_LOGGED_IN_MESSAGE
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
		PageObjectLogging.log("cssSource", "the following text was get from Wikia.css: " + source, true);
		return source;
	}

	public String getFirstCssRevision() {
		waitForElementByElement(cssEditSummary);
		String summary = cssEditSummary.getText();
		PageObjectLogging.log("cssEditSummary", "the following edit summaty was get from Wikia.css: " + summary, true);
		return summary;
	}

	public void verifyRevisionMarkedAsMinor() {
		if (checkIfElementOnPage(cssMinorEdit)) {
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
			getUrl(wikiURL + URLsContent.LOGOUT);
		} catch (TimeoutException e) {
			PageObjectLogging.log("logOut",
				"page loads for more than 30 seconds", true);
		}
		waitForElementPresenceByBy(LOGIN_BUTTON_CSS);
		PageObjectLogging.log("logOut", "user is logged out", true, driver);
	}

	public String logInCookie(String userName, String password, String wikiURL) {
		try {
			HttpClient httpclient = HttpClientBuilder.create()
				.setRetryHandler(new DefaultHttpRequestRetryHandler())
				.build();
			HttpPost httpPost = new HttpPost(wikiURL + "api.php");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			nvps.add(new BasicNameValuePair("action", "login"));
			nvps.add(new BasicNameValuePair("format", "xml"));
			nvps.add(new BasicNameValuePair("lgname", userName));
			nvps.add(new BasicNameValuePair("lgpassword", password));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));

			HttpResponse response = httpclient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			String xmlResponse = null;

			xmlResponse = EntityUtils.toString(entity);

			String[] xmlResponseArr = xmlResponse.split("\"");
			String token;
			//Insert here for logging responses -- QAART 371 -- QAART 501
			try {
				token = xmlResponseArr[5];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new WebDriverException(
					"No token received from request.\n lgname is " + nvps.get(2).getValue() + ".\n HTTP response is " + response.toString() +
					".\n xmlReponse is " + xmlResponse);
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
						StandardCharsets.UTF_8));

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
				+ "', {'domain': '" + domain + "', 'path': '/'})");
			js.executeScript("$.cookie('" + xmlResponseArr[11]
				+ "UserName', '" + xmlResponseArr[7]
				+ "', {'domain': '" + domain + "', 'path': '/'})");
			js.executeScript("$.cookie('" + xmlResponseArr[11]
				+ "UserID', '" + xmlResponseArr[5]
				+ "', {'domain': '" + domain + "', 'path': '/'})");
			js.executeScript("$.cookie('" + xmlResponseArr[11]
				+ "Token', '" + xmlResponseArr[9]
				+ "', {'domain': '" + domain + "' , 'path': '/'})");
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
		driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_UNFOLLOW));
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
			URLsContent.DISABLE_CAPTCHA
		);
		getUrl(url);
	}

	public HubBasePageObject openHubByUrl(String hubUrl) {
		getUrl(hubUrl);
		return new HubBasePageObject(driver);
	}

	public FacebookMainPageObject openFacebookMainPage() {
		getUrl(URLsContent.FACEBOOK_MAINPAGE);
		return new FacebookMainPageObject(driver);
	}

	public String getNameForArticle() {
		return PageContent.ARTICLE_NAME_PREFIX + getTimeStamp();
	}

	public void openSpecialPromoteOnCurrentWiki() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String url = (String) js.executeScript("return wgServer");
		getUrl(url + "/" + URLsContent.SPECIAL_PROMOTE);
		PageObjectLogging.log("openSpecialPromote", "special promote page opened", true);
	}

	public void verifyWgVariableValuesTheSame(Object[] value1, Object[] value2) {
		Arrays.sort(value1);
		Arrays.sort(value2);

		if (Arrays.equals(value1, value2)) {
			PageObjectLogging.log(
				"VariablesAreTheSame",
				"Variable on wiki and on community are the same",
				true,
				driver
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
				wikiURL + URLsContent.WIKI_DIR + getNameForArticle(),
				URLsContent.VEACTION_EDIT
			)
		);
		return new VisualEditorPageObject(driver);
	}

	public VisualEditorPageObject openNewArticleEditModeVisualWithRedlink(String wikiURL) {
		String randomArticle = wikiURL + URLsContent.WIKI_DIR + getNameForArticle();
		String randomArticleWithVETrigger = urlBuilder.appendQueryStringToURL(
			randomArticle, URLsContent.VEACTION_EDIT
		);
		String randomArticleWithVEAndRedLink = urlBuilder.appendQueryStringToURL(
			randomArticleWithVETrigger, URLsContent.REDLINK
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
	 *
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
	 *
	 * @author Michal 'justnpT' Nowierski
	 */
	public void compareTrackedEventsTo(List<JsonObject> expectedEventsList) {
		executeScript(ClickTrackingScriptsProvider.EVENTS_CAPTURE_INSTALLATION);
		List<JsonObject> trackedEventsArrayList = new ArrayList<JsonObject>();
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

	public void verifyVEPublishComplete() {
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
		getUrl(urlBuilder.appendQueryStringToURL(getCurrentUrl(), URLsContent.ACTION_HISTORY));
		return new WikiHistoryPageObject(driver);
	}

	private String getArticleName() {
		return executeScriptRet(WikiaGlobalVariables.WG_PAGE_NAME);
	}

	public void verifyArticleName(String targetText) {
		Assertion.assertStringContains(getArticleName(), targetText);
		PageObjectLogging.log(
			"verifyArticleName",
			"The article shows " + targetText,
			true
		);
	}

	public void verifyNumberOfTop1kWikis(Integer numberOfWikis) {
		String pattern = "List of wikis with matched criteria (" + numberOfWikis + ")";
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
		Cookie newCookie = new Cookie(name, value);
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
		if (venusGlobalNav == null) {
			venusGlobalNav = new VenusGlobalNavPageObject(driver);
		}

		return venusGlobalNav;
	}
}
