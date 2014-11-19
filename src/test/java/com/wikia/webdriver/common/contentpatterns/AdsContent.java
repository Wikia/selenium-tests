package com.wikia.webdriver.common.contentpatterns;

import java.util.HashMap;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsContent {
	//HashMap with slots selector
	public static HashMap<String, String> slotsSelectors;
	public static HashMap<String, String> adsProviders;

	//SCRIPTS
	public static final String adsPushSlotScript =
		"window.adslots2.push([\"%slot%\",null,\"AdEngine2\"]);";
	public static final String adDriverForcedStatusSuccessScript =
		"top.window.adDriver2ForcedStatus['%s']='success';";

	public static final String slotTweakerHideSlotScript =
			"top.window.adDriver2ForcedStatus['%slot%']='success';"
			+ "varst=top.window.SlotTweaker(top.window.Wikia.log,top.document,top.window);"
			+ "st.hide('%slot%');";

    //SLOTS NAMES
	public static final String homeTopLB = "HOME_TOP_LEADERBOARD";
	public static final String hubLB = "HUB_TOP_LEADERBOARD";
	public static final String hubLB_gpt = "HUB_TOP_LEADERBOARD_gpt";
	public static final String corpTopLB = "CORP_TOP_LEADERBOARD";
	public static final String topLB = "TOP_LEADERBOARD";
	public static final String homeMedrec = "HOME_TOP_RIGHT_BOXAD";
	public static final String medrec = "TOP_RIGHT_BOXAD";
	public static final String floatingMedrec = "INCONTENT_BOXAD_1";
	public static final String leftSkyscraper2 = "LEFT_SKYSCRAPER_2";
	public static final String leftSkyscraper3 = "LEFT_SKYSCRAPER_3";
	public static final String prefooterLeft = "PREFOOTER_LEFT_BOXAD";
	public static final String prefooterRight = "PREFOOTER_RIGHT_BOXAD";
	public static final String wikiaBar = "WIKIA_BAR_BOXAD_1";
	public static final String topIncontentBoxad = "TOP_INCONTENT_BOXAD";
	public static final String mobileTopLB = "MOBILE_TOP_LEADERBOARD";
	public static final String mobileAdInContent = "MOBILE_IN_CONTENT";
	public static final String mobilePrefooter = "MOBILE_PREFOOTER";

	//CONTAINERS
	public static final String prefootersContainer = "Prefooters";
	public static final String adsInContentContainer = "AdsInContent";

	//SLOTS SELECTORS
	public static final String wikiaBarSelector = "#" + wikiaBar;


	public static String getSlotSelector(String slotName) {
		return slotsSelectors.get(slotName);
	}

	public static String getElementForProvider(String providerName) {
		setAdsProviders();
		return adsProviders.get(providerName);
	}

	private static void setAdsProviders() {
		adsProviders = new HashMap<>();
		adsProviders.put(
			"IDG",
			"script[src*='http://ad-emea.doubleclick.net/N7503/adj/DE-OW-netzwerk']"
		);
	}

	public static void setSlotsSelectors() {
		slotsSelectors = new HashMap<>();
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
		slotsSelectors.put(wikiaBar, "#WIKIA_BAR_BOXAD_1");
		slotsSelectors.put(adsInContentContainer, "#WikiaAdInContentPlaceHolder");
		slotsSelectors.put(prefootersContainer, "#WikiaArticleBottomAd");
		slotsSelectors.put(topIncontentBoxad, "#TOP_INCONTENT_BOXAD");
		slotsSelectors.put(mobileTopLB, "#MOBILE_TOP_LEADERBOARD");
		slotsSelectors.put(mobileAdInContent, "#MOBILE_IN_CONTENT");
		slotsSelectors.put(mobilePrefooter, "#MOBILE_PREFOOTER");

	}
}

