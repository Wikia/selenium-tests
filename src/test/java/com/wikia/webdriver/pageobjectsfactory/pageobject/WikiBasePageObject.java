package com.wikia.webdriver.pageobjectsfactory.pageobject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.wikia.webdriver.common.clicktracking.ClickTrackingScriptsProvider;
import com.wikia.webdriver.common.clicktracking.ClickTrackingSupport;
import com.wikia.webdriver.common.contentpatterns.ApiActions;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.WikiaGlobalVariables;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialAdminDashboardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialContributionsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCssPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCuratedContentPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialEditHubPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFactoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialManageWikiaHome;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPromotePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialUploadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWhatLinksHerePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiHistoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

public class WikiBasePageObject extends BasePageObject {

  protected final static By LOGIN_BUTTON_CSS = By.cssSelector("a[data-id='login']");
  private static final String LOGGED_IN_USER_SELECTOR_VENUS =
      ".AccountNavigation a[title*=%userName%]";
  @FindBy(css = "body")
  protected WebElement body;
  @FindBy(css = ".UserLoginModal input[type='submit']")
  protected WebElement modalLoginSubmit;
  @FindBy(css = ".UserLoginModal input[name='password']")
  protected WebElement modalPasswordInput;
  @FindBy(css = "#WikiaPageHeader h1")
  protected WebElement wikiFirstHeader;
  @FindBy(css = ".UserLoginModal input[name='username']")
  protected WebElement modalUserNameInput;
  @FindBy(css = "a[data-id='logout']")
  protected WebElement navigationLogoutLink;
  @FindBy(css = "#AccountNavigation .subnav")
  protected WebElement userMenuDropdown;
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
  @FindBy(css = ".editsection a")
  protected List<WebElement> sectionEditButtons;
  @FindBy(css = "a.new[href$='redlink=1']")
  protected List<WebElement> redLinks;
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
  @FindBy(css = "#userForceLoginModal .wikia-button-facebook")
  protected WebElement formConnectWithFbButtonModal;
  @FindBy(css = "#mw-content-text .wikia-button-facebook")
  protected WebElement formConnectWithFbButtonBasic;
  @FindBy(css = "#UserLoginDropdown .wikia-button-facebook")
  protected WebElement formConnectWithFbButtonDropDown;
  protected By editButtonBy = By.cssSelector("#WikiaMainContent a[data-id='edit']");
  protected By parentBy = By.xpath("./..");
  protected String modalWrapper = "#WikiaConfirm";
  @FindBy(css = "input#wpConfirmB")
  private WebElement deleteConfirmationButton;
  @FindBy(css = ".banner-notification div.msg a")
  private WebElement undeleteLink;
  @FindBy(css = ".banner-notification")
  private WebElement flashMessage;
  @FindBy(css = "input#mw-undelete-submit")
  private WebElement restoreButton;
  @FindBy(css = "input#wpReason")
  private WebElement deleteCommentReasonField;
  @FindBy(css = "div.permissions-errors")
  private WebElement premissionErrorMessage;
  @FindBy(css = "#WikiaArticle a[href*='Special:UserLogin']")
  private WebElement specialUserLoginLink;
  @FindBy(css = ".avatar-container")
  private WebElement globalNavigationAvatar;
  @FindBy(css = "#WikiaFooter")
  private WebElement footer;
  @FindBy(css = "#globalNavigation")
  private WebElement globalNavigationBar;
  private String globalNavigationAvatarPlaceholder = ".avatar-container.logged-avatar-placeholder";
  private String loggedInUserSelectorMonobook = "#pt-userpage a[href*=%userName%]";
  private VenusGlobalNavPageObject venusGlobalNav;

  public WikiBasePageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public String getWikiUrl() {
    String currentURL = driver.getCurrentUrl();
    return currentURL.substring(0, currentURL.lastIndexOf("wiki/"));
  }

  public String resetForgotPasswordTime(String userName, String apiToken) {
    String[][] apiRequestParameters =
        { {"action", ApiActions.API_ACTION_FORGOT_PASSWORD}, {"user", userName},
            {"token", apiToken}, {"format", "json"},};
    return CommonUtils.sendPost(URLsContent.API_URL, apiRequestParameters);
  }

  public void verifyModalLoginAppeared() {
    wait.forElementVisible(logInModal);
    PageObjectLogging.log("verifyModalLogin", "verify modal login form is displayed", true);
  }

  public SpecialUnusedFilesPageObject openSpecialUnusedFilesPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_UNUSED_FILES);
    PageObjectLogging.log("openSpecialUnusedFilesPage", URLsContent.SPECIAL_UNUSED_FILES
        + " opened", true);
    return new SpecialUnusedFilesPageObject(driver);
  }

  public FeaturedVideoAdminPageObject openVideoPageAdminObject(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_VIDEO_PAGE_ADMIN);
    PageObjectLogging.log("openVideoPageAdminObject", wikiURL + " opened", true);
    return new FeaturedVideoAdminPageObject(driver);
  }

  public SpecialUnusedVideosPageObject openSpecialUnusedVideosPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_UNUSED_VIDEOS);
    PageObjectLogging.log("openSpecialUnusedVideosPage", URLsContent.SPECIAL_UNUSED_VIDEOS
        + " opened", true);
    return new SpecialUnusedVideosPageObject(driver);
  }

  public SpecialUncategorizedFilesPageObject openSpecialUncategorizedFilesPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_UNCATEGORIZED_FILES);
    PageObjectLogging.log("openSpecialUncategorizedFilesPage",
        URLsContent.SPECIAL_UNCATEGORIZED_FILES + " opened", true);
    return new SpecialUncategorizedFilesPageObject(driver);
  }

  public SpecialMostLinkedFilesPageObject openSpecialMostLinkedFilesPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_MOST_LINKED_FILES);
    PageObjectLogging.log("openSpecialMostLinkedFilesPage", URLsContent.SPECIAL_MOST_LINKED_FILES
        + " opened", true);
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
    PageObjectLogging.log("Special:BlockList openSpecialBlockListPage",
        "blocked users list page opened", true);
    return new SpecialBlockListPageObject(driver);
  }

  public SpecialUnblockPageObject openSpecialUnblockPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_UNBLOCK);
    PageObjectLogging.log("openSpecialUnblockPage", "special unblock page opened", true);
    return new SpecialUnblockPageObject(driver);
  }

  public HistoryPagePageObject openFileHistoryPage(String articlePage, String wikiURL) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR
        + URLsContent.FILE_NAMESPACE + articlePage, URLsContent.ACTION_HISTORY));
    PageObjectLogging.log("openFileHistoryPage", "history page opened", true);
    return new HistoryPagePageObject(driver);
  }

  public SignUpPageObject openSpecialSignUpPage(String wikiURL) {
    getUrl(wikiURL);
    getVenusGlobalNav().signUp();
    PageObjectLogging.log("openSpecialSignUpPage", "Special:UserSignUp page opened", true);
    return new SignUpPageObject(driver);
  }

  public SignUpPageObject navigateToSpecialSignUpPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_USER_SIGNUP);
    return new SignUpPageObject(driver);
  }

  public PreferencesPageObject openSpecialPreferencesPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_PREFERENCES);
    PageObjectLogging.log("openSpecialPreferencesPage", "Special:Prefereces page opened", true);
    return new PreferencesPageObject(driver);
  }

  public EditingPreferencesPageObject openSpecialEditingPreferencesPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_EDITING_PREFERENCES);
    PageObjectLogging.log("EditingPreferencesPageObject",
        "Special:Prefereces#mw-prefsection-editing page opened", true);
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
    String url =
        urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.SPECIAL_VIDEOS, queryString);
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

  public SpecialCuratedContentPageObject openSpecialCuratedContent(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_CURATED_CONTENT);
    return new SpecialCuratedContentPageObject(driver);
  }

  public SpecialUploadPageObject openSpecialUpload(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_UPLOAD);
    return new SpecialUploadPageObject(driver);
  }

  public SpecialCreatePagePageObject openSpecialCreateBlogPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_CREATE_BLOGPAGE);
    return new SpecialCreatePagePageObject(driver);
  }

  public SpecialWikiActivityPageObject openSpecialWikiActivity() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName())
        + URLsContent.SPECIAL_WIKI_ACTIVITY);
    return new SpecialWikiActivityPageObject(driver);
  }

  public ForumPageObject openForumMainPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_FORUM);
    PageObjectLogging.log("openForumPage", "forum page opened", true);
    return new ForumPageObject(driver);
  }

  public SpecialMultiWikiFinderPageObject openSpecialMultiWikiFinderPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_MULTI_WIKI_FINDER);
    PageObjectLogging.log("openSpecialMultiWikiFinderPage",
        "Special MultiWikiFinder page was opened", true);
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

  public SpecialWhatLinksHerePageObject openSpecialWhatLinksHere(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_WHAT_LINKS_HERE);
    return new SpecialWhatLinksHerePageObject(driver);
  }

  public InteractiveMapPageObject openInteractiveMapById(String wikiURL, Integer id) {
    getUrl(wikiURL + URLsContent.SPECIAL_MAPS + "/" + id);
    return new InteractiveMapPageObject(driver);
  }

  public FilePagePageObject openFilePage(String wikiURL, String fileName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + fileName);
    return new FilePagePageObject(driver);
  }

  public FilePagePageObject openFilePage(String wikiURL, String fileName, boolean noRedirect) {
    String url = wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + fileName;
    if (noRedirect) {
      String parameter = "redirect=no";
      url = urlBuilder.appendQueryStringToURL(url, parameter);
    }
    getUrl(url);

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
    wait.forElementVisible(editButton);
    editButton.click();
    PageObjectLogging.log("openSrcModeWithMainEditButton", "Src main edit button clicked", true,
        driver);
    return new SourceEditModePageObject(driver);
  }

  public VisualEditModePageObject openCKModeWithMainEditButton() {
    wait.forElementVisible(editButton);
    editButton.click();
    PageObjectLogging.log("openCKModeWithMainEditButton", "CK main edit button clicked", true,
        driver);
    return new VisualEditModePageObject(driver);
  }

  public VisualEditorPageObject openVEModeWithMainEditButton() {
    wait.forElementVisible(veEditButton);
    veEditButton.click();
    PageObjectLogging.log("openVEModeWithMainEditButton", "VE main edit button clicked", true,
        driver);
    return new VisualEditorPageObject(driver);
  }

  public VisualEditorPageObject openVEModeWithSectionEditButton(int section) {
    WebElement sectionEditButton = sectionEditButtons.get(section);
    waitForElementClickableByElement(sectionEditButton);
    sectionEditButton.click();
    PageObjectLogging.log("openVEModeWithSectionEditButton", "VE edit button clicked at section: "
        + section, true, driver);
    return new VisualEditorPageObject(driver);
  }

  public VisualEditModePageObject openCKModeWithSectionEditButton(int section) {
    WebElement sectionEditButton = sectionEditButtons.get(section);
    wait.forElementVisible(sectionEditButton);
    sectionEditButton.click();
    PageObjectLogging.log("openCKModeWithSectionEditButton", "RTE edit button clicked at section: "
        + section, true, driver);
    return new VisualEditModePageObject(driver);
  }

  public SourceEditModePageObject openSrcModeWithSectionEditButton(int section) {
    WebElement sectionEditButton = sectionEditButtons.get(section);
    wait.forElementVisible(sectionEditButton);
    sectionEditButton.click();
    PageObjectLogging.log("openSrcModeWithSectionEditButton",
        "Src edit button clicked at section: " + section, true, driver);
    return new SourceEditModePageObject(driver);
  }

  public VisualEditModePageObject goToCurrentArticleEditPage() {
    getUrl(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_EDIT));
    return new VisualEditModePageObject(driver);
  }

  public VisualEditModePageObject navigateToArticleEditPageCK(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + article,
        URLsContent.ACTION_EDIT));
    return new VisualEditModePageObject(driver);
  }

  public SourceEditModePageObject navigateToArticleEditPageSrc(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + article,
        URLsContent.ACTION_EDIT));
    return new SourceEditModePageObject(driver);
  }

  public VisualEditModePageObject goToArticleDefaultContentEditPage(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.appendQueryStringToURL(wikiURL
        + URLsContent.WIKI_DIR + article, URLsContent.ACTION_EDIT), URLsContent.USE_DEFAULT_FORMAT));
    return new VisualEditModePageObject(driver);
  }

  /**
   * method used to navigate to new visual editor
   */
  public VisualEditorPageObject openVEOnArticle(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + article,
        URLsContent.VEACTION_EDIT));
    return new VisualEditorPageObject(driver);
  }

  public SpecialUserLoginPageObject openSpecialUserLoginOnWiki(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_USER_LOGIN);
    PageObjectLogging
        .log("SpecialUserLoginOnWiki", "Special:UserLogin opened on: " + wikiURL, true);
    return new SpecialUserLoginPageObject(driver);
  }

  public LicensedVideoSwapPageObject openLicensedVideoSwap(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_LICENSED_VIDEO_SWAP);
    PageObjectLogging.log("LicensedVideoSwapPageObject", "Special:LicensedVideoSwap opened on: "
        + wikiURL, true);
    return new LicensedVideoSwapPageObject(driver);
  }


  public void verifyUserLoggedIn(final String userName) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver driver) {
          try {
            if (driver.findElement(By.tagName("body")).getAttribute("class")
                .contains("skin-monobook")) {
              return driver.findElements(
                  By.cssSelector(loggedInUserSelectorMonobook.replace("%userName%",
                      userName.replace(" ", "_")))).size() > 0;// only for verification
            } else {
              // Venus
              return driver.findElements(
                  By.cssSelector(LOGGED_IN_USER_SELECTOR_VENUS.replace("%userName%", userName)))
                  .size() > 0;// only for verification
            }
          } catch (StaleElementReferenceException e) {
            return false;
          }
        }
      });
    } finally {
      restoreDeaultImplicitWait();
    }
    PageObjectLogging.log("verifyUserLoggedIn", "user " + userName + " logged in", true);
  }

  protected void clickArticleDeleteConfirmationButton() {
    wait.forElementVisible(deleteConfirmationButton);
    wait.forElementVisible(deleteCommentReasonField);
    deleteCommentReasonField.clear();
    deleteCommentReasonField.sendKeys("QAReason");
    deleteConfirmationButton.click();
  }

  public DeletePageObject deletePage() {
    String url =
        urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_DELETE);
    getUrl(url);
    PageObjectLogging.log("deletePage", "delete page opened", true);
    return new DeletePageObject(driver);
  }

  public void verifyEditButtonNotPresent() {
    waitForElementNotVisibleByElement(editButton);
    PageObjectLogging.log("verifyEditButtonNotPresent", "edit button is not present", true);
  }

  protected void clickRestoreArticleButton() {
    wait.forElementVisible(restoreButton);
    scrollAndClick(restoreButton);
    wait.forElementVisible(userMessage);
    PageObjectLogging.log("clickUndeleteArticle", "undelete article button clicked", true, driver);
  }

  public SpecialRestorePageObject undeleteByFlashMessage() {
    wait.forElementVisible(undeleteLink);
    undeleteLink.click();
    return new SpecialRestorePageObject(driver);
  }

  public void verifyNotificationMessage() {
    driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
    try {
      wait.forElementVisible(flashMessage);
    } finally {
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
  }

  public String getFlashMessageText() {
    return flashMessage.getText();
  }

  public BlogPageObject openBlogByName(String wikiURL, String blogTitle, String userName) {
    getUrl(wikiURL + URLsContent.BLOG_NAMESPACE.replace("%userName%", userName) + blogTitle);
    return new BlogPageObject(driver);
  }

  public ChatPageObject openChat(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_CHAT);
    return new ChatPageObject(driver);
  }

  public ArticlePageObject openMainPage(String wikiURL) {
    getUrl(wikiURL);
    return new ArticlePageObject(driver);
  }

  public void verifyPermissionsErrorsPresent() {
    wait.forElementVisible(premissionErrorMessage);
    PageObjectLogging.log("verifyPermissionsErrors", "premission error found, as expected", true,
        driver);
  }

  public void verifyUrl(String url) {
    waitForStringInURL(url);
  }

  public SpecialCreatePagePageObject openSpecialCreatePage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_CREATE_PAGE);
    return new SpecialCreatePagePageObject(driver);
  }

  public void verifyLoginReguiredMessage() {
    waitForTextToBePresentInElementByElement(wikiFirstHeader, PageContent.LOGIN_REQUIRED);
    PageObjectLogging.log("LoginRequiredMessage", "Login required message in first header present",
        true, driver);
  }

  public SpecialUserLoginPageObject clickLoginOnSpecialPage() {
    wait.forElementVisible(specialUserLoginLink);
    PageObjectLogging.log("LoginLinkPresent", "Link to login special page present", true, driver);
    scrollAndClick(specialUserLoginLink);
    PageObjectLogging.log("LoginLinkClicked", "Link to login special page clicked", true, driver);

    return new SpecialUserLoginPageObject(driver);
  }

  public void verifyNotLoggedInMessage() {
    waitForTextToBePresentInElementByElement(wikiFirstHeader, PageContent.NOT_LOGGED_IN_MESSAGE);
    PageObjectLogging.log("NotLoggedInMessage", "Not logged in message present", true, driver);
  }

  public void logInViaModal(String userName, String password) {
    wait.forElementVisible(modalUserNameInput);
    modalUserNameInput.sendKeys(userName);
    wait.forElementVisible(modalPasswordInput);
    modalPasswordInput.sendKeys(password);
    PageObjectLogging.log("FillLoginForm", "Login form in modal is filled", true, driver);

    scrollAndClick(modalLoginSubmit);
    PageObjectLogging.log("LoginFormSubmitted", "Login form is submitted", true);

    waitForElementNotVisibleByElement(logInModal);
    PageObjectLogging.log("LoginModalDissapears", "Login modal is no longer visible", true);
  }

  public String receiveMailWithNewPassword(String email, String password) {
    String newPassword =
        MailFunctions.getPasswordFromEmailContent((MailFunctions.getFirstEmailContent(email,
            password, "Reset your Wikia password")));
    PageObjectLogging.log("NewPasswordRecived", "New password recived from mail: " + newPassword,
        true);

    return newPassword;
  }

  public String getWikiaCssContent() {
    wait.forElementVisible(cssSource);
    String source = cssSource.getText();
    PageObjectLogging
        .log("cssSource", "the following text was get from Wikia.css: " + source, true);
    return source;
  }

  public String getFirstCssRevision() {
    wait.forElementVisible(cssEditSummary);
    String summary = cssEditSummary.getText();
    PageObjectLogging.log("cssEditSummary", "the following edit summaty was get from Wikia.css: "
        + summary, true);
    return summary;
  }

  public void verifyRevisionMarkedAsMinor() {
    if (isElementOnPage(cssMinorEdit)) {
      PageObjectLogging.log("cssEditSummary", "minor edit is marked in first revision", true);
    } else {
      throw new NoSuchElementException("Minor Edit is not present on the page");
    }
  }

  /**
   * Logout by navigating to 'logout' button href attribute value;
   */
  public void logOut() {
    try {
      if (navigationLogoutLink.getAttribute("href") != null) {
        driver.get(navigationLogoutLink.getAttribute("href"));
      } else {
        throw new WebDriverException("No logout link provided");
      }
    } catch (TimeoutException e) {
      PageObjectLogging.log("logOut", "page loads for more than 30 seconds", true);
    }
  }

  public void logOut(WebDriver driver) {
    try {
      driver.manage().deleteAllCookies();
      driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.LOGOUT);
    } catch (TimeoutException e) {
      PageObjectLogging.log("logOut", "page loads for more than 30 seconds", true);
    }
    wait.forElementPresent(LOGIN_BUTTON_CSS);
    PageObjectLogging.log("logOut", "user is logged out", true, driver);
  }

  public void logOut(String wikiURL) {
    try {
      getUrl(wikiURL + URLsContent.LOGOUT);
    } catch (TimeoutException e) {
      PageObjectLogging.log("logOut", "page loads for more than 30 seconds", true);
    }
    wait.forElementPresent(LOGIN_BUTTON_CSS);
    PageObjectLogging.log("logOut", "user is logged out", true, driver);
  }

  public String loginAs(String userName, String password, String wikiURL) {

    try {
      String token = Helios.getAccessToken(userName, password);

      String domian = Configuration.getEnvType().equals("dev") ? ".wikia-dev.com" : ".wikia.com";

      driver.manage().addCookie(new Cookie("access_token", token, domian, null, null));

      if (driver.getCurrentUrl().contains("Logout")) {
        driver.get(wikiURL);
      } else {
        driver.navigate().refresh();
      }

      verifyUserLoggedIn(userName);
      PageObjectLogging.log("loginCookie", "user was logged in by by helios using acces token: "
          + token, true);
      return token;
    } catch (TimeoutException e) {
      PageObjectLogging.log("loginCookie", "page timeout after login by cookie", false);
    }
    return "";
  }

  public String loginAs(User user) {
    return loginAs(user.getUserName(), user.getPassword(),
        urlBuilder.getUrlForWiki(Configuration.getWikiName()));
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
    wait.forElementVisible(followButton);
    jsActions.click(followButton);
    waitForTextToBePresentInElementByElement(followButton, "Following");
    PageObjectLogging.log("followArticle", "page is followed", true, driver);
  }

  public WatchPageObject unfollowCurrentUrl() {
    driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(),
        URLsContent.ACTION_UNFOLLOW));
    return new WatchPageObject(driver);
  }

  public RenamePageObject renameUsingDropdown() {
    articleEditDropdown.click();
    wait.forElementVisible(renameDropdown);
    renameDropdown.click();
    return new RenamePageObject(driver);
  }

  public DeletePageObject deleteUsingDropdown() {
    articleEditDropdown.click();
    wait.forElementVisible(deleteDropdown);
    deleteDropdown.click();
    return new DeletePageObject(driver);
  }

  public String getHeaderText() {
    wait.forElementVisible(wikiFirstHeader);
    return wikiFirstHeader.getText();
  }

  public void verifyHeader(String fileName) {
    wait.forElementVisible(wikiFirstHeader);
    Assertion.assertStringContains(wikiFirstHeader.getText(), fileName);
  }

  public void disableCaptcha() {
    String url =
        urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.DISABLE_CAPTCHA);
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

  public VisualEditorPageObject openNewArticleEditModeVisual(String wikiURL) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + getNameForArticle(),
        URLsContent.VEACTION_EDIT));
    return new VisualEditorPageObject(driver);
  }

  public void addVideoViaAjax(String videoURL) {
    jsActions.execute("$.ajax('" + getWikiUrl()
        + "wikia.php?controller=Videos&method=addVideo&format=json', {" + "data: {url: '"
        + videoURL + "'}," + "type: 'POST' } );");
  }

  /**
   * this method should be called after clicktracking test, in order to verify if expected events
   * were tracked
   *
   * @author Michal 'justnpT' Nowierski
   */
  public void compareTrackedEventsTo(List<JsonObject> expectedEventsList) {
    jsActions.execute(ClickTrackingScriptsProvider.EVENTS_CAPTURE_INSTALLATION);
    List<JsonObject> trackedEventsArrayList = new ArrayList<JsonObject>();
    List<JsonObject> trackedEventsList;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    // prepare list of tracked events
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

  public WikiHistoryPageObject openArticleHistoryPage() {
    getUrl(urlBuilder.appendQueryStringToURL(getCurrentUrl(), URLsContent.ACTION_HISTORY));
    return new WikiHistoryPageObject(driver);
  }

  private String getArticleName() {
    return (String) jsActions.execute(WikiaGlobalVariables.WG_PAGE_NAME);
  }

  public void verifyArticleName(String targetText) {
    Assertion.assertStringContains(targetText, getArticleName());
    PageObjectLogging.log("verifyArticleName", "The article shows " + targetText, true);
  }

  public void verifyNumberOfTop1kWikis(Integer numberOfWikis) {
    String pattern = "List of wikis with matched criteria (" + numberOfWikis + ")";
    wait.forElementVisible(headerWhereIsMyExtensionPage);
    PageObjectLogging.log("verifyNumberOfTop1kWikis", "Verification of top 1k wikis", true, driver);
    Assertion.assertStringContains(headerWhereIsMyExtensionPage.getText(), pattern);
  }

  protected Boolean isNewGlobalNavPresent() {
    return isElementOnPage(newGlobalNavigation);
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

  public Dimension getWindowSize() {
    return driver.manage().window().getSize();
  }

  public void resizeWindow(int width, int height) {
    try {
      driver.manage().window().setSize(new Dimension(width, height));
      PageObjectLogging.log("ResizeWindow", "Resize window (width=" + width + ", height=" + height
          + ")", true);
    } catch (WebDriverException ex) {
      PageObjectLogging.log("ResizeWindow", "Cannot resize window (width=" + width + ", height="
          + height + ")", true);
    }
  }

  public void resizeWindow(Dimension resolution) {
    resizeWindow(resolution.width, resolution.height);
  }

  public void scrollToFooter() {
    wait.forElementVisible(footer);
    scrollToElement(footer);
    PageObjectLogging.log("scrollToFooter", "Scroll to the footer of the page", true);
  }

  public void verifyGlobalNavigation() {
    wait.forElementVisible(globalNavigationBar);
    PageObjectLogging.log("verifyGlobalNavigation", "Verified global navigation", true);
  }

  public VenusGlobalNavPageObject getVenusGlobalNav() {
    if (venusGlobalNav == null) {
      venusGlobalNav = new VenusGlobalNavPageObject(driver);
    }

    return venusGlobalNav;
  }

  public void verifyModalFBButtonVisible() {
    Assertion.assertTrue(isElementOnPage(formConnectWithFbButtonModal));
  }

  public void verifyFBButtonVisible() {
    Assertion.assertTrue(isElementOnPage(formConnectWithFbButtonBasic));
  }

  public void verifyDropDownFBButtonVisible() {
    Assertion.assertTrue(isElementOnPage(formConnectWithFbButtonDropDown));
  }

  public void verifyAvatarPlaceholder() {
    // prevent http://docs.seleniumhq.org/exceptions/stale_element_reference.jsp
    WebElement placeholder = driver.findElement(By.cssSelector(globalNavigationAvatarPlaceholder));
    wait.forElementVisible(placeholder);
    PageObjectLogging.log("verifyAvatarPlaceholder", "Avatar placeholder is visible", true);
  }

  public void verifyAvatarNotPresent() {
    wait.forElementNotPresent(By.cssSelector("a[data-id='userpage']"));
    PageObjectLogging.log("verifyAvatarNotPresent", "Avatar is not visible", true);
  }

  public void verifyAvatarVisible() {
    wait.forElementVisible(globalNavigationAvatar);
    PageObjectLogging.log("verifyAvatarVisible", "desired avatar is visible on navbar", true);
  }

  public UserProfilePageObject clickOnAvatar() {
    getVenusGlobalNav().openAccountNAvigation();
    globalNavigationAvatar.click();
    PageObjectLogging.log("clickOnAvatar", "clicked on avatar", true);
    return new UserProfilePageObject(driver);
  }

  public void redirectToAnotherRandomArticle() {
    String wikiURL = getCurrentUrl().substring(0, getCurrentUrl().indexOf("wiki/"));
    getUrl(wikiURL + URLsContent.WIKI_DIR + "Special:Random/article");
  }

  public enum PositionsVideo {
    LEFT, CENTER, RIGHT
  }

  public enum HubName {
    VIDEO_GAMES, ENTERTAINMENT, LIFESTYLE
  }

}
