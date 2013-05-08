package com.wikia.webdriver.Common.ContentPatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.openqa.selenium.Dimension;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsContent {

    //HashMap with slots selector
    public HashMap slotsSelectors = new HashMap<String, String>();

    //Dimension with all possible correct sizes
    public Dimension slot1030X250 = new Dimension(1030, 250);
    public Dimension slot970x250 = new Dimension(970, 250);
    public Dimension slot728X90 = new Dimension(728, 90);
    public Dimension slot300X250 = new Dimension(300, 250);
    public Dimension acceptableSize = new Dimension(1,1);

    //SCIPTS
    public static String adsPushSlotScript =
	    "window.adslots2.push([\"%slot%\",null,\"AdEngine2\"]);";

    //ArrayLists of slots with its dimensions
    public ArrayList<Dimension> TOP_LEADERBOARD_SIZE = new ArrayList<Dimension>(
	    Arrays.asList(slot1030X250, slot728X90, slot970x250)
	    );

    //SLOTS NAMES
    public static String homeTopLeaderboard = "HOME_TOP_LEADERBOARD";
    public static String topLeaderboard = "TOP_LEADERBOARD";
    public static String homeMedrec = "HOME_TOP_RIGHT_BOXAD";
    public static String medrec = "TOP_RIGHT_BOXAD";
    public static String corpLeaderboard = "CORP_TOP_LEADERBOARD";
    public static String wikiaBAr = "WIKIA_BAR_BOXAD_1";
    public static String leftSkyscraper = "LEFT_SKYSCRAPER_2";
    public static String floatingMedrec = "INCONTENT_BOXAD_1";
    public static String leftSkyscraper2 = "LEFT_SKYSCRAPER_3";
    public static String prefooterLeft = "PREFOOTER_LEFT_BOXAD";
    public static String prefooterRight = "PREFOOTER_RIGHT_BOXAD";

	public AdsContent() {
		setSlotsSelectors();
	}

    private void setSlotsSelectors() {
		slotsSelectors.put("HOME_TOP_LEADERBOARD", "#HOME_TOP_LEADERBOARD");
		slotsSelectors.put("CORP_TOP_LEADERBOARD", "#CORP_TOP_LEADERBOARD");
		slotsSelectors.put("TOP_LEADERBOARD", "#TOP_LEADERBOARD");
		slotsSelectors.put("HOME_TOP_RIGHT_BOXAD", "#HOME_TOP_RIGHT_BOXAD");
		slotsSelectors.put("TOP_RIGHT_BOXAD", "#TOP_RIGHT_BOXAD");
//		slotsSelectors.put("WIKIA_BAR_BOXAD_1", "#WIKIA_BAR_BOXAD_1");
		slotsSelectors.put("LEFT_SKYSCRAPER_2", "#LEFT_SKYSCRAPER_2");
		slotsSelectors.put("LEFT_SKYSCRAPER_3", "#LEFT_SKYSCRAPER_3");
		slotsSelectors.put("INCONTENT_BOXAD_1", "#INCONTENT_BOXAD_1");
		slotsSelectors.put("PREFOOTER_LEFT_BOXAD", "#PREFOOTER_LEFT_BOXAD");
		slotsSelectors.put("PREFOOTER_RIGHT_BOXAD", "#PREFOOTER_RIGHT_BOXAD");
		slotsSelectors.put("AdsInContent", "#WikiaAdInContentPlaceHolder");
		slotsSelectors.put("Prefooters", "#WikiaArticleBottomAd");
    }
}

