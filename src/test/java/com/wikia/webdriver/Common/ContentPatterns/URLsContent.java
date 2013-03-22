package com.wikia.webdriver.Common.ContentPatterns;

public class URLsContent {

	//Image lightbox test page
	public static String lightboxImageTest = "LightboxTesting";
	public static String followingToolbarTest = "QAautoPage";

        //Preview url prefix
	public static String previewPrefix = "http://preview";

	// Ads - pages with height better than 2400 px
	public static String wikiSearchMiddleLink1 = "http://preview.plantsvszombies.wikia.com/wiki/Special:Search?ns0=1&ns14=1&search=zombies&fulltext=Search&ns0=1&ns14=1&advanced=";
	public static String wikiSearchMiddleLink2 = "http://preview.arresteddevelopment.wikia.com/wiki/index.php?search=arrested&fulltext=Search";
	public static String wikiMainPageLink1 = "http://preview.arresteddevelopment.wikia.com/wiki/Main_Page";
	public static String wikiMainPageLink2 = "http://preview.easycrafts.wikia.com/wiki/Main_Page";
	public static String wikiNewForumLink1 = "http://preview.community.wikia.com/wiki/Thread:472861";
	public static String wikiNewForumLink2 = "http://preview.community.wikia.com/wiki/Thread:477388";
	public static String wikiCategoryLink1 = "http://preview.harrypotter.wikia.com/wiki/Category:Harry_Potter_Wiki";
	public static String wikiCategoryLink2 = "http://preview.fatalfrontier.wikia.com/wiki/Category:Article_stubs";
	public static String wikiArticleLink1 = "http://preview.plantsvszombies.wikia.com/wiki/Ladder_Zombie";
	public static String wikiArticleLink2 = "http://preview.arresteddevelopment.wikia.com/wiki/Pilot";

	// Ads - prefooter ads String. (adding this string to a URL enables/disables
	// prefooter ads)
	public static String prefooterAdsEnabled = "AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_ENABLED";
	public static String prefooterAdsDisabled = "AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED";

	// Hubs
	public static String VideoGamesHub = "http://www.wikia.com/Video_Games";
	public static String EntertainmentHub = "http://www.wikia.com/Entertainment";
	public static String LifestyleHub = "http://www.wikia.com/Lifestyle";

        //Special Urls - links to special pages
        public static final String specialUserLogin = "wiki/Special:UserLogin";
        public static final String specialUserSignup = "wiki/Special:UserSignup";
        public static final String specialAddBlogPost = "wiki/Special:CreateBlogPage";
        public static final String specialNewFiles = "wiki/Special:NewFiles";
        public static final String specialNewVideo = "wiki/Special:Videos";
        public static final String specialUpload = "wiki/Special:Upload";
        public static final String specialWatchList = "wiki/Special:Watchlist";
        public static final String specialPhalanx = "wiki/Special:Phalanx";
        public static final String specialPhalanxTest = "wiki/Special:Phalanx/test";
        public static final String specialCreatePage = "wiki/Special:CreatePage";

	// Extra switches - appear after ? in link
	public static final String noexternals = "?noexternals=1";

	// Extra sites - useful for monobook
	// replace %user% with username
	public static final String userBlog = "User_blog:%user%";
	// replace %title% with new article name
	public static final String addArticle = "index.php?title=%title%&action=edit";
	// replace %user% with username
	public static final String userPrefix = "User:%user%";

	public static String buildUrl(String url, String parameter) {
		String temp;
		if (url.contains("?")) {
			temp = url + "&" + parameter;
			System.out.println(temp);
			return temp;
		} else {
			temp = url + "?" + parameter;
			System.out.println(temp);
			return temp;
		}
	}

}
