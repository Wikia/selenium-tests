package com.wikia.webdriver.common.contentpatterns;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsContent {

  //HashMap with slots selector
  public static Map<String, String> SLOTS_SELECTORS;

  //SCRIPTS
  public static final String ADS_PUSHSLOT_SCRIPT =
      "window.adslots2.push([\"%slot%\"]);";
  public static final String AD_DRIVER_FORCED_STATUS_SUCCESS_SCRIPT =
      "top.window.adDriver2ForcedStatus['%s']='success';";

  //SLOTS NAMES
  public static final String HOME_TOP_LB = "HOME_TOP_LEADERBOARD";
  public static final String HUB_LB = "HUB_TOP_LEADERBOARD";
  public static final String HUB_LB_GPT = "HUB_TOP_LEADERBOARD_gpt";
  public static final String CORP_TOP_LB = "CORP_TOP_LEADERBOARD";
  public static final String TOP_LB = "TOP_LEADERBOARD";
  public static final String HOME_MEDREC = "HOME_TOP_RIGHT_BOXAD";
  public static final String MEDREC = "TOP_RIGHT_BOXAD";
  public static final String FLOATING_MEDREC = "INCONTENT_BOXAD_1";
  public static final String LEFT_SKYSCRAPPER_2 = "LEFT_SKYSCRAPER_2";
  public static final String LEFT_SKYSCRAPPER_3 = "LEFT_SKYSCRAPER_3";
  public static final String PREFOOTER_LEFT = "PREFOOTER_LEFT_BOXAD";
  public static final String PREFOOTER_RIGHT = "PREFOOTER_RIGHT_BOXAD";
  public static final String WIKIA_BAR = "WIKIA_BAR_BOXAD_1";
  public static final String TOP_INCONTENT_BOXAD = "TOP_INCONTENT_BOXAD";
  public static final String MOBILETOP_LB = "MOBILE_TOP_LEADERBOARD";
  public static final String MOBILE_AD_IN_CONTENT = "MOBILE_IN_CONTENT";
  public static final String MOBILE_PREFOOTER = "MOBILE_PREFOOTER";
  public static final String INCONTENT_1 = "INCONTENT_1";
  public static final String INCONTENT_2 = "INCONTENT_2";
  public static final String INCONTENT_3 = "INCONTENT_3";
  public static final String INCONTENT_LB_1 = "INCONTENT_LEADERBOARD_1";
  public static final String INCONTENT_LB_2 = "INCONTENT_LEADERBOARD_2";
  public static final String INCONTENT_LB_3 = "INCONTENT_LEADERBOARD_3";
  public static final String INVISIBLE_SKIN = "INVISIBLE_SKIN";
  public static final String INVISIBLE_HIGH_IMPACT = "INVISIBLE_HIGH_IMPACT";


  //CONTAINERS
  public static final String PREFOOTERS_CONTAINER = "Prefooters";
  public static final String ADS_IN_CONTENT_CONTAINER = "AdsInContent";

  private AdsContent() {

  }

  public static String getSlotSelector(String slotName) {
    return SLOTS_SELECTORS.get(slotName);
  }

  static {
    SLOTS_SELECTORS = new HashMap<>();
    SLOTS_SELECTORS.put(HOME_TOP_LB, "#HOME_TOP_LEADERBOARD");
    SLOTS_SELECTORS.put(CORP_TOP_LB, "#CORP_TOP_LEADERBOARD");
    SLOTS_SELECTORS.put(HUB_LB, "#HUB_TOP_LEADERBOARD");
    SLOTS_SELECTORS.put(HUB_LB_GPT, "[id*='gpt/HUB_TOP_LEADERBOARD']");
    SLOTS_SELECTORS.put(TOP_LB, "#TOP_LEADERBOARD");
    SLOTS_SELECTORS.put(HOME_MEDREC, "#HOME_TOP_RIGHT_BOXAD");
    SLOTS_SELECTORS.put(MEDREC, "#TOP_RIGHT_BOXAD");
    SLOTS_SELECTORS.put(LEFT_SKYSCRAPPER_2, "#LEFT_SKYSCRAPER_2");
    SLOTS_SELECTORS.put(LEFT_SKYSCRAPPER_3, "#LEFT_SKYSCRAPER_3");
    SLOTS_SELECTORS.put(FLOATING_MEDREC, "#INCONTENT_BOXAD_1");
    SLOTS_SELECTORS.put(PREFOOTER_LEFT, "#PREFOOTER_LEFT_BOXAD");
    SLOTS_SELECTORS.put(PREFOOTER_RIGHT, "#PREFOOTER_RIGHT_BOXAD");
    SLOTS_SELECTORS.put(WIKIA_BAR, "#WIKIA_BAR_BOXAD_1");
    SLOTS_SELECTORS.put(ADS_IN_CONTENT_CONTAINER, "#WikiaAdInContentPlaceHolder");
    SLOTS_SELECTORS.put(PREFOOTERS_CONTAINER, "#WikiaArticleBottomAd, .bottom-ads");
    SLOTS_SELECTORS.put(TOP_INCONTENT_BOXAD, "#TOP_INCONTENT_BOXAD");
    SLOTS_SELECTORS.put(MOBILETOP_LB, "#MOBILE_TOP_LEADERBOARD");
    SLOTS_SELECTORS.put(MOBILE_AD_IN_CONTENT, "#MOBILE_IN_CONTENT");
    SLOTS_SELECTORS.put(MOBILE_PREFOOTER, "#MOBILE_PREFOOTER");
    SLOTS_SELECTORS.put(INCONTENT_1, "[id^=INCONTENT_1]");
    SLOTS_SELECTORS.put(INCONTENT_2, "[id^=INCONTENT_2]");
    SLOTS_SELECTORS.put(INCONTENT_3, "[id^=INCONTENT_3]");
    SLOTS_SELECTORS.put(INCONTENT_LB_1, "#INCONTENT_LEADERBOARD_1");
    SLOTS_SELECTORS.put(INCONTENT_LB_2, "#INCONTENT_LEADERBOARD_2");
    SLOTS_SELECTORS.put(INCONTENT_LB_3, "#INCONTENT_LEADERBOARD_3");
    SLOTS_SELECTORS.put(INVISIBLE_SKIN, "#INVISIBLE_SKIN");
    SLOTS_SELECTORS.put(INVISIBLE_HIGH_IMPACT, "#INVISIBLE_HIGH_IMPACT");

  }
}

