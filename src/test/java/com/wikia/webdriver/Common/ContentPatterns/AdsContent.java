package com.wikia.webdriver.Common.ContentPatterns;

import java.util.ArrayList;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsContent {

    //SLOTS NAMES
    public static String homeTopLeaderboard = "HOME_TOP_LEADERBOARD";
    public static String topLeaderboard = "TOP_LEADERBOARD";
    public static String homeMadrec = "HOME_TOP_RIGHT_BOXAD";
    public static String madrec = "TOP_RIGHT_BOXAD";

    //SCIPTS 
    public static String adsPushSlotScript = "window.adslots2.push([\"%slot%\",null,\"AdEngine2\"]);";

    public ArrayList<Integer> slot1030X250 = new ArrayList();
    public ArrayList<Integer> slot970X150 = new ArrayList();
    public ArrayList<Integer> slot728X90 = new ArrayList();
    public ArrayList<Integer> slot300X250 = new ArrayList();

    public ArrayList<ArrayList<Integer>> TOP_LEADERBOARD_SIZE = new ArrayList();

    public AdsContent() {
        slot1030X250.add(1030);
        slot1030X250.add(250);
        slot970X150.add(970);
        slot970X150.add(150);
        slot728X90.add(728);
        slot728X90.add(90);
    }

    //SLOTS SIZES
    public void setTopLeaderboardsSizes() {
        TOP_LEADERBOARD_SIZE.add(slot1030X250);
        TOP_LEADERBOARD_SIZE.add(slot970X150);
        TOP_LEADERBOARD_SIZE.add(slot728X90);
    }
}

