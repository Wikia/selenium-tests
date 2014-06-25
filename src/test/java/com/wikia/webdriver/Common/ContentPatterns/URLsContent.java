package com.wikia.webdriver.Common.ContentPatterns;

import com.wikia.webdriver.Common.Core.Global;

public class URLsContent {

	//api
	public static String wikiaPhp = "wikia.php";

	public static String followingToolbarTest = "QAautoPage";

	// Common url component
	public static String wikiDir = "wiki/";
	public static String wikiaDir = "Wikia";

	// Hubs
	public static String VideoGamesHubUrl = "/Video_Games";
	public static String EntertainmentHubUrl = "/Entertainment";
	public static String LifestyleHubUrl = "/Lifestyle";

	//Special Urls - links to special pages
	public static final String specialUserLogin = "wiki/Special:UserLogin";
	public static final String specialUserSignup = "wiki/Special:UserSignup";
	public static final String specialAddBlogListingPage = "wiki/Special:CreateBlogListingPage";
	public static final String specialNewFiles = "wiki/Special:NewFiles";
	public static final String specialConnect = "wiki/Special:Connect";
	public static final String specialVideos = "wiki/Special:Videos";
	public static final String specialUpload = "wiki/Special:Upload";
	public static final String specialMultipleUpload = "wiki/Special:MultipleUpload";
	public static final String specialWatchList = "wiki/Special:Watchlist";
	public static final String specialPhalanx = "wiki/Special:Phalanx";
	public static final String specialPhalanxTest = "wiki/Special:Phalanx/test";
	public static final String specialPreferences = "wiki/Special:Preferences";
	public static final String specialPromote = "wiki/Special:Promote";
	public static final String specialCreatePage = "wiki/Special:CreatePage";
	public static final String specialCreateBlogPage = "wiki/Special:CreateBlogPage";
	public static final String specialAdminDashboard = "wiki/Special:AdminDashboard";
	public static final String specialCSS = "wiki/Special:CSS";
	public static final String specialRandom = "wiki/Special:Random";
	public static final String specialFollow = "wiki/Special:Following";
	public static final String specialForum = "wiki/Special:Forum";
	public static final String specialThemeDesigner = "wiki/Special:ThemeDesigner";
	public static final String specialWikiActivity = "wiki/Special:WikiActivity";
	public static final String specialEditAccount = "wiki/Special:EditAccount";
	public static final String userMessageWall = "wiki/Message_Wall:";
	public static final String specialMultiWikiFinderPage = "wiki/Special:Multiwikifinder";
	public static final String logout = "wiki/Special:UserLogout?noexternals=1";
	public static final String specialUndelete = "wiki/Special:Undelete";
	public static final String userProfile = "wiki/User:%userName%";
	public static final String specialCreateNewWiki = "Special:CreateNewWiki";
	public static final String specialWikiFactory = "wiki/Special:WikiFactory";
	public static final String specialContributions = "wiki/Special:Contributions";
	public static final String specialBlockList = "wiki/Special:BlockList";
	public static final String specialUnblock = "wiki/Special:Unblock";
	public static final String specialBlock = "wiki/Special:Block";
	public static final String specialChat = "wiki/Special:Chat";
	public static final String specialManageWikiaHome = "wiki/Special:ManageWikiaHome";
	public static final String specialUnusedFiles = "wiki/Special:UnusedFiles";
	public static final String specialUnusedVideos = "wiki/Special:UnusedVideos";
	public static final String specialUncategorizedFiles = "wiki/Special:UncategorizedFiles";
	public static final String specialMostLinkedFiles = "wiki/Special:MostLinkedFiles";
	public static final String specialLicensedVideoSwap = "wiki/Special:LicensedVideoSwap";
	public static final String specialEditHub = "wiki/Special:EditHub";
	public static final String specialInteractiveMaps = "wiki/Special:InteractiveMaps";

	//Urls for mobile
	public static final String mobileTestMainPage = "wiki/Mobileregressiontesting_Wiki";
	public static final String articleSections = "wiki/Sections";
	public static final String articleModal = "wiki/Modal";
	public static final String articleComments = "wiki/Article_comments";
	public static final String categoryPmg = "wiki/Category:PMG";
	public static final String articleTopbar = "wiki/Topbar";
	public static final String gameGuidesControllerQS = "controller=GameGuides";
	public static final String renderFullQS = "method=renderFullPage";
	public static final String pageName = "page=";

	//Mediawiki Urls
	public static final String mediaWikiCss = "MediaWiki:Wikia.css";

	//Blog list page url
	public static final String blogList = "wiki/Blog:%listName%/";
	public static final String blogNameSpace = "wiki/User_blog:%userName%/";

	// Mediawiki template url
	public static final String templateNs = "Template";
	public static final String templateUrl = "wiki/" + templateNs + ":%name%";

	// Extra switches - appear after ? in link
	public static final String noexternals = "?noexternals=1";
	public static final String actionEditParameter = "action=edit";
	public static final String actionVisualEditParameter = "veaction=edit";
	public static final String useDefaultFormat = "useFormat=1";
	public static final String wikiaTracker = "og_level=info";
	public static final String unfollowParameter = "action=unwatch";
	public static final String deleteParameter = "action=delete";
	public static final String disableCaptchaParameter = "nocaptchatest=1";
	public static final String sourceMode = "useeditor=source";
	public static final String redLink = "redlink=1";
	public static final String mostRecent = "?sort=recent";

	// WAM Scores page url
	public static final String wamPageUrl = "wiki/WAM";

	//Preview url for testing video suggestions
	public static final String videoSuggestionsUrl = "http://preview.callofduty.wikia.com/wiki/Frank_Woods";

	// replace %title% with new article name
	public static final String addArticle = "index.php?title=%title%&action=edit";

	public static final String apiUrl = Global.DOMAIN + "api.php";

	// File Pages
	public static String fileNameSpace = "File:";
	public static String fileName001 = "Grammy_Muppet_Critics";
	public static String fileName002 = "New_Batman_Year_One_Clip";

	// History Pages
	public static String historyAction = "action=history";

	// Article Pages
	public static String articleName001 = "TestVid001";
	public static String articleName002 = "TestVid002";
	public static String articleName003 = "TestVid003";
	public static String articleName004 = "TestVid004";

	public static String intraWikiSearchPage = "wiki/Special:Search";

	//languages
	public static String translatableLanguage = "uselang=qqx";

	//External sites
	public static String facebookDomain = "facebook.com";
	public static String twitterDomain = "twitter.com";
	public static String googleDomain = "accounts.google.com";
	public static String redditDomain = "reddit.com";
	public static String stumpleUponDomain = "stumbleupon.com";

	//Facebook
	public static String facebookMainPage = "http://www.facebook.com/";
	public static String facebookSettingsPage = "http://www.facebook.com/settings";
	public static String facebookSettingsAppTab = "tab=applications";
	public static String facebookWikiaAppID = "112328095453510";
	public static String facebookWikiaDevAppID = "116800565037587";

	//avatars
	public static String avatarGeneric = "150px-Avatar.jpg";

	//Urls for VE editor
	public static final String veEnabledTestMainPage = "vetest";
	public static final String veDisabledTestMainPage = "vedisabledtest";
	public static final String rteDisabledTestMainPage = "ckdisabledtest";
	public static final String veAndrteDisabledTestMainPage = "veandckdisabledtest";
	public static final String testingPage = "Testingpage";

	//Urls for different namespace pages
	public static final String wikiMainPage = "Main_Page";
	public static final String categoryPage = "Category:General_wiki_templates";
	public static final String templatePage = "Template:Infobox";
	public static final String listPage = "List:Listing";
	public static final String mediaWiki = "mediawiki119";

	//Other wikis to test on
	public static final String videoTestWiki = "sktest123";
}
