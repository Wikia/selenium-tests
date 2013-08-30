package com.wikia.webdriver.Common.ContentPatterns;

import java.util.HashMap;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsMobileContent {

	//Scripts
	public static String dartCallScript = "http://ad.mo.doubleclick.net/DARTProxy/mobile.handler";

	//Slot names
	public static String adInContent = "MOBILE_IN_CONTENT";
	public static String interstitial = "MOBILE_MODAL_INTERSTITIAL";

	public static String getSlotSelector(String slotName) {
		HashMap<String, String> selectorsRegistry = new HashMap();
		selectorsRegistry.put(adInContent, "#wkAdInContent");
		selectorsRegistry.put(interstitial, "#wkMdlImages .wkAdWrapper");
		return selectorsRegistry.get(slotName);
	}
}
