package com.wikia.webdriver.Common.ContentPatterns;

import com.wikia.webdriver.Common.Core.Global;

public class URLsContent {

	//Image lightbox test page
	public static String lightboxImageTest = "LightboxTesting";
	public static String followingToolbarTest = "QAautoPage";

	//Preview url prefix
	public static String previewPrefix = "http://preview";

	// Common url component
	public static String wikiDir = "wiki/";

	public static String wikiFactoryLiveDomain = "http://community.wikia.com/wiki/Special:WikiFactory";

	// Hubs
	public static String VideoGamesHub = "http://www.wikia.com/Video_Games";
	public static String EntertainmentHub = "http://www.wikia.com/Entertainment";
	public static String LifestyleHub = "http://www.wikia.com/Lifestyle";

	//Special Urls - links to special pages
	public static final String specialUserLogin = "wiki/Special:UserLogin";
	public static final String specialUserSignup = "wiki/Special:UserSignup";
	public static final String specialAddBlogPost = "wiki/Special:CreateBlogPage";
	public static final String specialAddBlogListingPage = "wiki/Special:CreateBlogListingPage";
	public static final String specialNewFiles = "wiki/Special:NewFiles";
	public static final String specialNewVideo = "wiki/Special:Videos";
	public static final String specialUpload = "wiki/Special:Upload";
	public static final String specialMultipleUpload = "wiki/Special:MultipleUpload";
	public static final String specialWatchList = "wiki/Special:Watchlist";
	public static final String specialPhalanx = "wiki/Special:Phalanx";
	public static final String specialPhalanxTest = "wiki/Special:Phalanx/test";
	public static final String specialCreatePage = "wiki/Special:CreatePage";
	public static final String specialPreferences = "wiki/Special:Preferences";
	public static final String specialAdminDashboard = "wiki/Special:AdminDashboard";
	public static final String specialCss = "wiki/Special:Css";
	public static final String specialCSS = "Special:CSS";

	//Mediawiki Urls
	public static final String mediaWikiCss = "MediaWiki:Wikia.css";

	//Blog list page url
	public static final String blogList = "wiki/Blog:%listName%";

	// Extra switches - appear after ? in link
	public static final String noexternals = "?noexternals=1";
	public static final String actionEditParameter = "?action=edit";

    // WAM Scores page url
    public static final String wamPageUrl = "WAM";

	// Extra sites - useful for monobook
	// replace %user% with username
	public static final String userBlog = "User_blog:%user%";
	// replace %title% with new article name
	public static final String addArticle = "index.php?title=%title%&action=edit";
	// replace %user% with username
	public static final String userPrefix = "User:%user%";

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
	public static String fileNS = "File:";
	public static String fileName001 = "Grammy_Muppet_Critics";
	public static String fileName002 = "New_Batman_Year_One_Clip";
	public static String filePage = Global.DOMAIN + wikiDir + fileNS;

	// History Pages
	public static String historyAction = "action=history";

    // Article Pages
    public static String articleName001 = "TestVid001";
    public static String articleName002 = "TestVid002";
    public static String articleName003 = "TestVid003";
    public static String articleName004 = "TestVid004";
}
