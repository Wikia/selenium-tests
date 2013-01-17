package com.wikia.webdriver.Common.ContentPatterns;

public class URLsContent {
	
	//Ads - pages with height better than 2400 px
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

	//Ads - prefooter ads String. (adding this string to a URL enables/disables prefooter ads)
	public static String prefooterAdsEnabled = "AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_ENABLED";
	public static String prefooterAdsDisabled = "AbTest.PERFORMANCE_V_PREFOOTERS=PREFOOTERS_DISABLED";
	
	public static String buildUrl(String url, String parameter){
		String temp;
		if (url.contains("?"))
		{
			temp = url+"&"+parameter;
			System.out.println(temp);
			return temp;
		}
		else{
			temp = url+"?"+parameter;
			System.out.println(temp);
			return temp;
		}
	}
	
}
