package com.wikia.webdriver.Common.ContentPatterns;

import com.wikia.webdriver.Common.Core.Global;

public class URLsContent {

	//Image lightbox test page
	public static String lightboxImageTest = "LightboxTesting";
	public static String followingToolbarTest = "QAautoPage";

	// Common url component
	public static String wikiDir = "wiki/";

	public static String wikiFactoryLiveDomain = "http://community.wikia.com/wiki/Special:WikiFactory";

	//Related videos
	public static String relatedVideosList = "MediaWiki:RelatedVideosGlobalList";

	// Hubs
	public static String VideoGamesHub = Global.LIVE_DOMAIN+"Video_Games";
	public static String EntertainmentHub = Global.LIVE_DOMAIN+"Entertainment";
	public static String LifestyleHub = Global.LIVE_DOMAIN+"Lifestyle";

	//Special Urls - links to special pages
	public static final String specialUserLogin = "wiki/Special:UserLogin";
	public static final String specialUserSignup = "wiki/Special:UserSignup";
	public static final String specialAddBlogListingPage = "wiki/Special:CreateBlogListingPage";
	public static final String specialNewFiles = "wiki/Special:NewFiles";
	public static final String specialNewVideo = "wiki/Special:Videos";
	public static final String specialUpload = "wiki/Special:Upload";
	public static final String specialMultipleUpload = "wiki/Special:MultipleUpload";
	public static final String specialWatchList = "wiki/Special:Watchlist";
	public static final String specialPhalanx = "wiki/Special:Phalanx";
	public static final String specialPhalanxTest = "wiki/Special:Phalanx/test";
	public static final String specialCreatePage = "wiki/Special:CreatePage";
	public static final String specialCreateBlogPage = "wiki/Special:CreateBlogPage";
	public static final String specialPreferences = "wiki/Special:Preferences";
	public static final String specialAdminDashboard = "wiki/Special:AdminDashboard";
	public static final String specialCSS = "wiki/Special:CSS";
	public static final String specialRandom = "wiki/Special:Random";
	public static final String specialFollow = "wiki/Special:Following";
	public static final String specialForum = "wiki/Special:Forum";
	public static final String specialThemeDesigner = "wiki/Special:ThemeDesigner";
	public static final String specialWikiActivity = "wiki/Special:WikiActivity";
	public static final String userMessageWall = "wiki/Message_Wall:";
	public static final String specialMultiWikiFinderPage = "wiki/Special:Multiwikifinder";
	public static final String logout = "wiki/Special:UserLogout?noexternals=1";

	//Urls for mobile
	public static final String articleSections = "wiki/Sections";
	public static final String articleModal = "wiki/Modal";
	public static final String articleComments = "wiki/Article_comments";
	public static final String categoryPmg = "wiki/Category:PMG";

	//Mediawiki Urls
	public static final String mediaWikiCss = "MediaWiki:Wikia.css";

	//Blog list page url
	public static final String blogList = "wiki/Blog:%listName%/";
	public static final String blogNameSpace = "wiki/User_blog:%userName%/";

	// Extra switches - appear after ? in link
	public static final String noexternals = "?noexternals=1";
	public static final String actionEditParameter = "action=edit";
	public static final String useDefaultFormat = "useFormat=1";
	public static final String wikiaTracker = "og_level=info";
	public static final String unfollowParameter = "action=unwatch";

	// WAM Scores page url
	public static final String wamPageUrl = "WAM";

	//Preview url for testing video suggestions
	public static final String videoSuggestionsUrl = "http://preview.callofduty.wikia.com/wiki/Frank_Woods";

	// replace %title% with new article name
	public static final String addArticle = "index.php?title=%title%&action=edit";

	public static final String apiUrl = Global.DOMAIN + "api.php";

	public static String buildUrl(String url, String parameter) {
		String temp;
		if (url.contains("?")) {
			temp = url + "&" + parameter;
			return temp;
		} else {
			temp = url + "?" + parameter;
			return temp;
		}
	}

	// File Pages
	public static String fileNameSpace = "File:";
	public static String fileName001 = "Grammy_Muppet_Critics";
	public static String fileName002 = "New_Batman_Year_One_Clip";
	public static String filePage = Global.DOMAIN + wikiDir + fileNameSpace;

	// History Pages
	public static String historyAction = "action=history";

    // Article Pages
    public static String articleName001 = "TestVid001";
    public static String articleName002 = "TestVid003";

	//External sites
	public static String facebookDomain = "facebook.com";
	public static String twitterDomain = "twitter.com";
	public static String googleDomain = "accounts.google.com";
	public static String redditDomain = "reddit.com";
	public static String stumpleUponDomain = "stumbleupon.com";
}
