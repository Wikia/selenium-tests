package com.webdriver.common.contentpatterns;

import java.util.HashMap;
import java.util.Map;

public class AdsContent {
  //HashMap with slots selector
  private static final Map<String, String> SLOTS_SELECTORS;
  private static final Map<String, String> SPECIAL_PAGE_SLOTS_SELECTORS;
  private static final Map<String, String> FILE_PAGE_SLOTS_SELECTORS;
  private static final Map<String, String> MOBILE_SLOTS_SELECTORS;
  private static final Map<String, String> SLOTS_TRIGGERS;

  public static final String ENV_DESKTOP = "desktop";
  public static final String ENV_MOBILE = "mobile";

  //SCRIPTS
  public static final String ADS_PUSHSLOT_SCRIPT =
      "window.adslots2.push([\"%slot%\"]);";
  public static final String AD_DRIVER_FORCED_STATUS_SUCCESS_SCRIPT =
      "top.window.adDriver2ForcedStatus['%s']='success';";

  //SLOTS NAMES
  public static final String BOTTOM_LB = "BOTTOM_LEADERBOARD";
  public static final String MOBILE_BOTTOM_LB = "MOBILE_BOTTOM_LEADERBOARD";
  public static final String TOP_LB = "TOP_LEADERBOARD";
  public static final String MEDREC = "TOP_RIGHT_BOXAD";
  public static final String FLOATING_MEDREC = "INCONTENT_BOXAD_1";
  public static final String WIKIA_BAR = "WIKIA_BAR_BOXAD_1";
  public static final String MOBILE_TOP_LB = "MOBILE_TOP_LEADERBOARD";
  public static final String MOBILE_AD_IN_CONTENT = "MOBILE_IN_CONTENT";
  public static final String MOBILE_PREFOOTER = "MOBILE_PREFOOTER";
  public static final String INCONTENT_PLAYER = "INCONTENT_PLAYER";
  public static final String INVISIBLE_SKIN = "INVISIBLE_SKIN";
  public static final String INVISIBLE_HIGH_IMPACT_2 = "INVISIBLE_HIGH_IMPACT_2";
  public static final String TOP_BUTTON_WIDE = "TOP_BUTTON_WIDE";

  private AdsContent() {}

  public static Map<String, String> getSlotsSelectorsMap(String env) {
    if(env.equals(ENV_MOBILE)) {
      return MOBILE_SLOTS_SELECTORS;
    }

    return SLOTS_SELECTORS;
  }

  public static Map<String, String> getSpecialPageSlotsSelectorsMap() {
    return SPECIAL_PAGE_SLOTS_SELECTORS;
  }

  public static Map<String, String> getFilePageSlotsSelectors() {
    return FILE_PAGE_SLOTS_SELECTORS;
  }

  public static Map<String, String> getCategoryPageSlotsSelectors() {
    return FILE_PAGE_SLOTS_SELECTORS;
  }

  public static String getSlotSelector(String slotName) {
    return SLOTS_SELECTORS.get(slotName);
  }

  public static String getSlotTrigger(String slotName) {
    return SLOTS_TRIGGERS.get(slotName);
  }

  static {
    SPECIAL_PAGE_SLOTS_SELECTORS = new HashMap<>();
    SPECIAL_PAGE_SLOTS_SELECTORS.put(TOP_LB, "#TOP_LEADERBOARD");

    FILE_PAGE_SLOTS_SELECTORS = new HashMap<>();
    FILE_PAGE_SLOTS_SELECTORS.put(TOP_LB, "#TOP_LEADERBOARD");
    FILE_PAGE_SLOTS_SELECTORS.put(MEDREC, "#TOP_RIGHT_BOXAD");
    FILE_PAGE_SLOTS_SELECTORS.put(BOTTOM_LB, "#BOTTOM_LEADERBOARD");

    SLOTS_SELECTORS = new HashMap<>();
    SLOTS_SELECTORS.put(BOTTOM_LB, "#BOTTOM_LEADERBOARD");
    SLOTS_SELECTORS.put(TOP_LB, "#TOP_LEADERBOARD");
    SLOTS_SELECTORS.put(MEDREC, "#TOP_RIGHT_BOXAD");
    SLOTS_SELECTORS.put(FLOATING_MEDREC, "#INCONTENT_BOXAD_1");
    SLOTS_SELECTORS.put(WIKIA_BAR, "#WIKIA_BAR_BOXAD_1");
    SLOTS_SELECTORS.put(INCONTENT_PLAYER, "#INCONTENT_PLAYER");
    SLOTS_SELECTORS.put(INVISIBLE_HIGH_IMPACT_2, "#INVISIBLE_HIGH_IMPACT_2");
    SLOTS_SELECTORS.put(INVISIBLE_SKIN, "#INVISIBLE_SKIN");
    SLOTS_SELECTORS.put(TOP_BUTTON_WIDE, "#TOP_BUTTON_WIDE");
    SLOTS_SELECTORS.put(MOBILE_TOP_LB, "#MOBILE_TOP_LEADERBOARD");
    SLOTS_SELECTORS.put(MOBILE_AD_IN_CONTENT, "[id^=MOBILE_IN_CONTENT]");
    SLOTS_SELECTORS.put(MOBILE_PREFOOTER, "#MOBILE_PREFOOTER");
    SLOTS_SELECTORS.put(MOBILE_BOTTOM_LB, "#MOBILE_BOTTOM_LEADERBOARD");

    MOBILE_SLOTS_SELECTORS = new HashMap<>();
    MOBILE_SLOTS_SELECTORS.put(MOBILE_TOP_LB, "#MOBILE_TOP_LEADERBOARD");
    MOBILE_SLOTS_SELECTORS.put(MOBILE_AD_IN_CONTENT, "#MOBILE_IN_CONTENT");
    MOBILE_SLOTS_SELECTORS.put(MOBILE_PREFOOTER, "#MOBILE_PREFOOTER");

    SLOTS_TRIGGERS = new HashMap<>();
    SLOTS_TRIGGERS.put(FLOATING_MEDREC, "(function(){ window.scroll(0, 5000); setTimeout(function () {window.scroll(0, 5001) }, 10500); })();");
    SLOTS_TRIGGERS.put(BOTTOM_LB,"(function(){function getHookElement(){var mixContentFooterSelector=\"#mixed-content-footer\",articleCategoriesSelector=\".article-categories\",isMixContentFooterOnPage=!!document.querySelectorAll(mixContentFooterSelector)[0],areArticleCategoriesOnPage=!!document.querySelectorAll(articleCategoriesSelector)[0];if(isMixContentFooterOnPage){return mixContentFooterSelector;}return articleCategoriesSelector;}var hookElement=getHookElement();document.querySelectorAll(hookElement)[0].scrollIntoView();setTimeout(function(){window.scrollBy(0, -10);},5000);})();");
    SLOTS_TRIGGERS.put(MOBILE_BOTTOM_LB, "(function(){ document.querySelectorAll('.recirculation-prefooter__item:last-child')[0].scrollIntoView(); setTimeout(function () {document.querySelectorAll('.recirculation-prefooter__item:last-child')[0].scrollIntoView()}, 5000); })();");
  }
}
