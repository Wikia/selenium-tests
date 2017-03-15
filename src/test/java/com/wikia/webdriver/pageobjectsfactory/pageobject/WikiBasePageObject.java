package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.contentpatterns.ApiActions;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.WikiaGlobalVariables;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.RegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.SignInPage;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.ActionExplorerModal;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.KeyboardShortcutsModal;
import com.wikia.webdriver.elements.oasis.components.notifications.BannerNotifications;
import com.wikia.webdriver.elements.oasis.components.wikiabar.WikiaBar;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.historypage.HistoryPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPromotePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialUploadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWhatLinksHerePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.multiwikifinder.SpecialMultiWikiFinderPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiHistoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

import lombok.Getter;
import org.apache.commons.lang3.Range;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WikiBasePageObject extends BasePageObject {

  private static final String LOGGED_IN_USER_SELECTOR_OASIS =
      ".wds-global-navigation__user-menu img";
  private static final By MERCURY_SKIN = By.cssSelector("#ember-container");
  private static final By MERCURY_NAV_ICON = By.cssSelector(".site-head .site-head-icon-nav");
  private static final String LOGGED_IN_USER_SELECTOR_MERCURY =
      ".wikia-nav__avatar img[alt*=%userName%]";
  @Getter(lazy = true)
  private final GlobalNavigation globalNavigation = new GlobalNavigation();
  @Getter(lazy = true)
  private final WikiaBar wikiaBar = new WikiaBar();
  @Getter(lazy = true)
  private final KeyboardShortcutsModal keyboardShortcuts = new KeyboardShortcutsModal();
  @Getter(lazy = true)
  private final ActionExplorerModal actionExplorer = new ActionExplorerModal();
  @Getter(lazy = true)
  private final TopBar topBar = new TopBar(driver);
  @Getter(lazy = true)
  private final AuthModal authModal = new AuthModal();
  @Getter(lazy = true)
  private final RegisterPage registerPage = new RegisterPage(true);
  @Getter(lazy = true)
  private final BannerNotifications bannerNotifications = new BannerNotifications();
  @FindBy(css = "body")
  protected WebElement body;
  @FindBy(css = "#WikiaPageHeader h1")
  protected WebElement wikiFirstHeader;
  @FindBy(css = "#global-navigation-user-sign-out")
  protected WebElement navigationLogoutLink;
  @FindBy(css = "#WikiaMainContent a[data-id='edit']")
  protected WebElement editButton;
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
  @FindBy(css = "#mw-content-text .wikia-button-facebook")
  protected WebElement formConnectWithFbButtonBasic;
  protected By editButtonBy = By.cssSelector("#WikiaMainContent a[data-id='edit']");
  protected By parentBy = By.xpath("./..");
  protected String modalWrapper = "#WikiaConfirm";
  @FindBy(css = ".banner-notification")
  private WebElement bannerNotification;
  @FindBy(css = "#WikiaArticle a[href*='Special:UserLogin']")
  private WebElement specialUserLoginLink;
  @FindBy(css = ".wds-global-navigation__user-menu")
  private WebElement globalNavigationAvatar;
  @FindBy(css = "#WikiaFooter")
  private WebElement footer;
  @FindBy(css = ".wds-global-footer__header")
  private WebElement mobileFooter;
  @FindBy(css = "#globalNavigation")
  private WebElement globalNavigationBar;

  public String getWikiUrl() {
    String currentURL = driver.getCurrentUrl();
    return currentURL.substring(0, currentURL.lastIndexOf("wiki/"));
  }

  public String resetForgotPasswordTime(String userName, String apiToken) {
    String[][] apiRequestParameters = {{"action", ApiActions.API_ACTION_FORGOT_PASSWORD},
        {"user", userName}, {"token", apiToken}, {"format", "json"},};
    return CommonUtils.sendPost(URLsContent.API_URL, apiRequestParameters);
  }

  public HistoryPagePageObject openFileHistoryPage(String articlePage, String wikiURL) {
    getUrl(urlBuilder.appendQueryStringToURL(
        wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + articlePage,
        URLsContent.ACTION_HISTORY));
    PageObjectLogging.log("openFileHistoryPage", "history page opened", true);
    return new HistoryPagePageObject(driver);
  }

  public RegisterPage openSpecialUserSignUpPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_USER_SIGNUP);
    PageObjectLogging.log("openSpecialUserSignUpPage", "Special:UserSignup page opened", true);
    return new RegisterPage();
  }

  public PreferencesPageObject openSpecialPreferencesPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_PREFERENCES);
    PageObjectLogging.log("openSpecialPreferencesPage", "Special:Prefereces page opened", true);
    return new PreferencesPageObject(driver);
  }

  public SpecialPromotePageObject openSpecialPromotePage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_PROMOTE);
    PageObjectLogging.log("openSpecialPromotePage", "Special:Promote page opened", true);
    return new SpecialPromotePageObject(driver);
  }

  public SignInPage openSpecialUserLogin(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_USER_LOGIN);
    PageObjectLogging.log("openSpecialUserLogin", "Special:UserLogin page opened", true);
    return new SignInPage();
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

  public SpecialNewFilesPage openSpecialNewFiles(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_NEW_FILES);
    return new SpecialNewFilesPage();
  }

  public SpecialUploadPageObject openSpecialUpload(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_UPLOAD);
    return new SpecialUploadPageObject(driver);
  }

  public SpecialCreatePage openSpecialCreateBlogPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_CREATE_BLOGPAGE);
    return new SpecialCreatePage();
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
    return new InteractiveMapsPageObject();
  }

  public SpecialWhatLinksHerePageObject openSpecialWhatLinksHere(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_WHAT_LINKS_HERE);
    return new SpecialWhatLinksHerePageObject(driver);
  }

  public InteractiveMapPageObject openInteractiveMapById(String wikiURL, Integer id) {
    getUrl(wikiURL + URLsContent.SPECIAL_MAPS + "/" + id);
    return new InteractiveMapPageObject();
  }

  public CreateNewWikiPageObjectStep1 openSpecialCreateNewWikiPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_CREATE_NEW_WIKI);
    return new CreateNewWikiPageObjectStep1(driver);
  }

  public void openSpecialWatchListPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_WATCHLIST);
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
    return new VisualEditModePageObject();
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
    wait.forElementClickable(sectionEditButton);
    sectionEditButton.click();
    PageObjectLogging.log("openVEModeWithSectionEditButton",
        "VE edit button clicked at section: " + section, true, driver);
    return new VisualEditorPageObject(driver);
  }

  public VisualEditModePageObject openCKModeWithSectionEditButton(int section) {
    WebElement sectionEditButton = sectionEditButtons.get(section);
    wait.forElementVisible(sectionEditButton);
    sectionEditButton.click();
    PageObjectLogging.log("openCKModeWithSectionEditButton",
        "RTE edit button clicked at section: " + section, true, driver);
    return new VisualEditModePageObject();
  }

  public SourceEditModePageObject openSrcModeWithSectionEditButton(int section) {
    WebElement sectionEditButton = sectionEditButtons.get(section);
    wait.forElementVisible(sectionEditButton);
    sectionEditButton.click();
    PageObjectLogging.log("openSrcModeWithSectionEditButton",
        "Src edit button clicked at section: " + section, true, driver);
    return new SourceEditModePageObject(driver);
  }

  public VisualEditModePageObject navigateToArticleEditPage() {
    getUrl(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_EDIT));
    return new VisualEditModePageObject();
  }

  public VisualEditModePageObject navigateToArticleEditPage(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + article,
        URLsContent.ACTION_EDIT));
    return new VisualEditModePageObject();
  }

  public SourceEditModePageObject navigateToArticleEditPageSrc(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + article,
        URLsContent.ACTION_EDIT));
    return new SourceEditModePageObject(driver);
  }

  public VisualEditModePageObject goToArticleDefaultContentEditPage(String wikiURL,
      String article) {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder
        .appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + article, URLsContent.ACTION_EDIT),
        URLsContent.USE_DEFAULT_FORMAT));
    return new VisualEditModePageObject();
  }

  /**
   * method used to navigate to new visual editor
   */
  public VisualEditorPageObject openVEOnArticle(String wikiURL, String article) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + article,
        URLsContent.VEACTION_EDIT));
    return new VisualEditorPageObject(driver);
  }

  public void verifyUserLoggedIn(final String userName) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      if (driver.findElements(By.cssSelector("#PreviewFrame")).size() > 0) {
        driver.switchTo().frame("PreviewFrame");
      }
      // open nav if on mercury, required to see login data
      if (driver.findElements(MERCURY_SKIN).size() > 0) {
        wait.forElementClickable(MERCURY_NAV_ICON);
        driver.findElement(MERCURY_NAV_ICON).click();
        wait.forElementVisible(By.cssSelector(
            LOGGED_IN_USER_SELECTOR_MERCURY.replace("%userName%", userName.replace(" ", "_"))));
        // close nav on mercury
        wait.forElementClickable(MERCURY_NAV_ICON);
        driver.findElement(MERCURY_NAV_ICON).click();
      } else {
        WebElement avatar = wait.forElementVisible(By.cssSelector(LOGGED_IN_USER_SELECTOR_OASIS));
        String loggedInUserName = avatar.getAttribute("alt");
        if (!loggedInUserName.equals(userName)) {
          throw new IllegalArgumentException(
              "Invalid user, expected " + userName + ", but found: " + loggedInUserName);
        }
      }
    } finally {
      restoreDefaultImplicitWait();
      driver.switchTo().defaultContent();
    }
    PageObjectLogging.log("verifyUserLoggedIn", "user " + userName + " logged in", true);
  }

  public void verifyUserLoggedIn(User user) {
    this.verifyUserLoggedIn(user.getUserName());
  }

  public DeletePageObject deletePage() {
    String url =
        urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_DELETE);
    getUrl(url);
    PageObjectLogging.log("deletePage", "delete page opened", true);
    return new DeletePageObject(driver);
  }

  public String getBannerNotificationText() {
    return bannerNotification.getText();
  }

  public BlogPageObject openBlogByName(String wikiURL, String blogTitle, String userName) {
    getUrl(wikiURL + URLsContent.BLOG_NAMESPACE.replace("%userName%", userName) + blogTitle);
    return new BlogPageObject(driver);
  }

  public ArticlePageObject openMainPage(String wikiURL) {
    getUrl(wikiURL);
    return new ArticlePageObject();
  }

  public void verifyLoginRequiredMessage() {
    wait.forTextInElement(wikiFirstHeader, PageContent.LOGIN_REQUIRED);
    PageObjectLogging.log("LoginRequiredMessage", "Login required message in first header present",
        true, driver);
  }

  public void clickLoginOnSpecialPage() {
    wait.forElementVisible(specialUserLoginLink);
    PageObjectLogging.log("LoginLinkPresent", "Link to login special page present", true, driver);
    scrollAndClick(specialUserLoginLink);
    PageObjectLogging.log("LoginLinkClicked", "Link to login special page clicked", true, driver);
  }

  public void verifyNotLoggedInMessage() {
    wait.forTextInElement(wikiFirstHeader, PageContent.NOT_LOGGED_IN_MESSAGE);
    PageObjectLogging.log("NotLoggedInMessage", "Not logged in message present", true, driver);
  }

  public String getPasswordResetLink(String email, String password) {
    String passwordResetEmail = MailFunctions.getFirstEmailContent(email, password, "Reset your Fandom password");
    String resetLink = MailFunctions.getPasswordResetLinkFromEmailContent(passwordResetEmail);
    PageObjectLogging.log("Password reset link", "Password reset link received: " + resetLink,
        true);

    return resetLink;
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

  public String loginAs(String userName, String password, String wikiURL) {
    String token = Helios.getAccessToken(userName, password);

    driver.manage().addCookie(new Cookie("access_token", token,
        String.format(".%s", Configuration.getEnvType().getWikiaDomain()), null, null));

    if (driver.getCurrentUrl().contains("Logout")) {
      driver.get(wikiURL);
    } else {
      refreshPageAddingCacheBuster();
    }

    this.verifyUserLoggedIn(userName);
    PageObjectLogging.log("loginCookie",
        "user was logged in by by helios using access token: " + token, true);
    logMercuryUserId();

    return token;
  }

  public String loginAs(User user) {
    return loginAs(user.getUserName(), user.getPassword(), urlBuilder.getUrlForWiki());
  }

  private void logMercuryUserId() {
    Object scriptOut = driver.executeScript("return window.M && window.M.prop('userId')");

    if (scriptOut != null) {
      PageObjectLogging.logInfo("Mercury userID: " + scriptOut.toString());
    }
  }

  public void openWikiPage(String wikiURL) {
    getUrl(wikiURL);
    PageObjectLogging.log("openWikiPage", "Wiki page is opened", true);
  }

  public void verifyPageUnfollowed() {
    wait.forTextInElement(followButton, "Follow");
    PageObjectLogging.log("verifyPageUnfollowed", "page is not followed", true);
  }

  public void follow() {
    wait.forElementVisible(followButton);
    jsActions.click(followButton);
    wait.forTextInElement(followButton, "Following");
    PageObjectLogging.log("followArticle", "page is followed", true, driver);
  }

  public WatchPageObject unfollowCurrentUrl() {
    driver.get(
        urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), URLsContent.ACTION_UNFOLLOW));
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
    scrollAndClick(deleteDropdown);
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

  public String getNameForArticle() {
    return PageContent.ARTICLE_NAME_PREFIX + getTimeStamp();
  }

  public String getPseudoElementValue(WebElement element, String pseudoElement, String cssValue) {
    return driver
        .executeScript("return getComputedStyle(arguments[0], arguments[1])[arguments[2]];",
            element, pseudoElement, cssValue)
        .toString();
  }

  public VisualEditorPageObject openNewArticleEditModeVisual(String wikiURL) {
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR + getNameForArticle(),
        URLsContent.VEACTION_EDIT));
    return new VisualEditorPageObject(driver);
  }

  public void addVideoViaAjax(String videoURL) {
    String request = new String(
        "$.ajax('" + getWikiUrl() + "wikia.php?controller=Videos&method=addVideo&format=json', {"
            + "data: {url: '" + videoURL + "'}," + "type: 'POST' } );");
    jsActions.execute(request);
  }

  public void verifyVEPublishComplete() {
    waitForElementNotVisibleByElement(veMode);
    waitForElementNotVisibleByElement(focusMode);
    waitForElementNotVisibleByElement(veToolMenu);
    PageObjectLogging.log("verifyVEPublishComplete", "Publish is done", true, driver);
  }

  public WikiHistoryPageObject openArticleHistoryPage() {
    getUrl(urlBuilder.appendQueryStringToURL(getCurrentUrl(), URLsContent.ACTION_HISTORY));
    return new WikiHistoryPageObject();
  }

  private String getArticleName() {
    return (String) jsActions.execute(WikiaGlobalVariables.WG_PAGE_NAME);
  }

  public void verifyArticleNameInWgPageName(String targetText) {
    Assertion.assertStringContains(targetText, getArticleName());
    PageObjectLogging.log("verifyArticleNameInWgPageName",
        "The wgPageName variable contains article name" + targetText, true);
  }

  public void verifyNumberOfTop1kWikisInRange(Range expectedRange) {
    wait.forElementVisible(headerWhereIsMyExtensionPage);
    PageObjectLogging.log("verifyNumberOfTop1kWikisInRange", "Verification of top 1k wikis", true, driver);
    Pattern p = Pattern.compile("\\d+");
    Matcher m = p.matcher(headerWhereIsMyExtensionPage.getText());
    m.find();
    Assertion.assertTrue(
            expectedRange.contains(Integer.parseInt(m.group())),
            String.format("Number of Top 1k Wikis between %s and %s", expectedRange.getMinimum(), expectedRange.getMaximum())
    );
  }

  protected Boolean isNewGlobalNavPresent() {
    return isElementOnPage(newGlobalNavigation);
  }

  public Dimension getWindowSize() {
    return driver.manage().window().getSize();
  }

  public void scrollToFooter() {
    wait.forElementVisible(footer);
    jsActions.scrollToElement(footer);
    PageObjectLogging.log("scrollToFooter", "Scroll to the footer of the page", true);
  }

  public void scrollToMobileFooter() {
    wait.forElementVisible(mobileFooter);
    jsActions.scrollToElement(mobileFooter);
    PageObjectLogging.log("scrollToMobileFooter", "Scroll to the mobile footer of the page", true);
  }

  public void verifyGlobalNavigation() {
    wait.forElementVisible(globalNavigationBar);
    PageObjectLogging.log("verifyGlobalNavigation", "Verified global navigation", true);
  }


  public void verifyFBButtonVisible() {
    Assertion.assertTrue(isElementOnPage(formConnectWithFbButtonBasic));
  }

  public void verifyAvatarVisible() {
    wait.forElementVisible(globalNavigationAvatar);
    PageObjectLogging.log("verifyAvatarVisible", "desired avatar is visible on navbar", true);
  }

  /**
   * Refresh Wiki page, busting the cache( by adding cb=currentTimestamp )
   */
  public void refreshPageAddingCacheBuster() {
    driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(),
        "cb=" + DateTime.now().getMillis()));
  }

  public Boolean isWikiFirstHeaderVisible() {
    try {
      wait.forElementVisible(wikiFirstHeader);
      return true;
    } catch(TimeoutException e) {
      PageObjectLogging.logInfo("FirstPageHeader object not visible", e);
      return false;
    }
  }

  public enum PositionsVideo {
    LEFT, CENTER, RIGHT
  }

  public enum HubName {
    VIDEO_GAMES, ENTERTAINMENT, LIFESTYLE
  }
}
