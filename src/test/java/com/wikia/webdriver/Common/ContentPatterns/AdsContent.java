package com.wikia.webdriver.Common.ContentPatterns;

import java.util.HashMap;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsContent {

    //HashMap with slots selector
    public static HashMap<String, String> slotsSelectors;

    //SCIPTS
    public static String adsPushSlotScript =
	    "window.adslots2.push([\"%slot%\",null,\"AdEngine2\"]);";

    //SLOTS NAMES
	public static String homeTopLB = "HOME_TOP_LEADERBOARD";
	public static String hubLB = "HUB_TOP_LEADERBOARD";
	public static String hubLB_gpt = "HUB_TOP_LEADERBOARD_gpt";
	public static String corpTopLB = "CORP_TOP_LEADERBOARD";
	public static String topLB = "TOP_LEADERBOARD";
	public static String homeMedrec = "HOME_TOP_RIGHT_BOXAD";
	public static String medrec = "TOP_RIGHT_BOXAD";
	public static String floatingMedrec = "INCONTENT_BOXAD_1";
	public static String leftSkyscraper2 = "LEFT_SKYSCRAPER_2";
	public static String leftSkyscraper3 = "LEFT_SKYSCRAPER_3";
	public static String prefooterLeft = "PREFOOTER_LEFT_BOXAD";
	public static String prefooterRight = "PREFOOTER_RIGHT_BOXAD";
	public static final String wikiaBar = "WIKIA_BAR_BOXAD_1";
	public static final String wikiaBar_gpt = "WIKIA_BAR_BOXAD_1_gpt";

	//SLOTS SELECTORS
	public static final String wikiaBarSelector = "#" + wikiaBar;
	public static final String wikiaBar_gptSelector = "#" + wikiaBar_gpt;


	public static String getSlotSelector(String slotName) {
		return slotsSelectors.get(slotName);
	}

	public static void setSlotsSelectors() {
		slotsSelectors = new HashMap<String, String>();
		slotsSelectors.put(homeTopLB, "#HOME_TOP_LEADERBOARD");
		slotsSelectors.put(corpTopLB, "#CORP_TOP_LEADERBOARD");
		slotsSelectors.put(hubLB, "#HUB_TOP_LEADERBOARD");
		slotsSelectors.put(hubLB_gpt, "#HUB_TOP_LEADERBOARD_gpt");
		slotsSelectors.put(topLB, "#TOP_LEADERBOARD");
		slotsSelectors.put(homeMedrec, "#HOME_TOP_RIGHT_BOXAD");
		slotsSelectors.put(medrec, "#TOP_RIGHT_BOXAD");
		slotsSelectors.put(leftSkyscraper2, "#LEFT_SKYSCRAPER_2");
		slotsSelectors.put(leftSkyscraper3, "#LEFT_SKYSCRAPER_3");
		slotsSelectors.put(floatingMedrec, "#INCONTENT_BOXAD_1");
		slotsSelectors.put(prefooterLeft, "#PREFOOTER_LEFT_BOXAD");
		slotsSelectors.put(prefooterRight, "#PREFOOTER_RIGHT_BOXAD");
		slotsSelectors.put("AdsInContent", "#WikiaAdInContentPlaceHolder");
		slotsSelectors.put("Prefooters", "#WikiaArticleBottomAd");
	}
}

