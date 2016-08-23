package com.wikia.webdriver.common.contentpatterns;

import java.util.HashMap;
import java.util.Map;

public class AdsFandomContent {

  public static final Map<String, String> SLOT_SELECTORS;

  public static final String TOP_LEADERBOARD = "TOP_LEADERBOARD";
  public static final String BOTTOM_LEADERBOARD = "BOTTOM_LEADERBOARD";
  public static final String TOP_BOXAD = "TOP_BOXAD";
  public static final String INCONTENT_BOXAD = "INCONTENT_BOXAD";
  public static final String BOTTOM_BOXAD = "BOTTOM_BOXAD";

  static {
    SLOT_SELECTORS = new HashMap<>();
    SLOT_SELECTORS.put(TOP_LEADERBOARD, "div[id$='TOP_LEADERBOARD_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_LEADERBOARD, "div[id$='BOTTOM_LEADERBOARD_0__container__']");
    SLOT_SELECTORS.put(TOP_BOXAD, "div[id$='TOP_BOXAD_0__container__']");
    SLOT_SELECTORS.put(INCONTENT_BOXAD, "div[id$='INCONTENT_BOXAD_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_BOXAD, "div[id$='BOTTOM_BOXAD_0__container__']");
  }

  private AdsFandomContent() {
  }

  public static String getSlotSelector(String slotName) {
    return SLOT_SELECTORS.get(slotName);
  }

}

