package com.wikia.webdriver.common.contentpatterns;

import java.util.HashMap;
import java.util.Map;

public class AdsFandomContent {

  public static final Map<String, String> SLOT_SELECTORS;
  public static final Map<String, String> GPT_SLOT_SELECTORS;

  public static final String TOP_LEADERBOARD = "TOP_LEADERBOARD";
  public static final String BOTTOM_LEADERBOARD = "BOTTOM_LEADERBOARD";
  public static final String TOP_BOXAD = "TOP_BOXAD";
  public static final String INCONTENT_BOXAD = "INCONTENT_BOXAD";
  public static final String BOTTOM_BOXAD = "BOTTOM_BOXAD";
  public static final String FEED_BOXAD = "FEED_BOXAD";

  static {
    SLOT_SELECTORS = new HashMap<>();
    SLOT_SELECTORS.put(TOP_LEADERBOARD, "div[id$='TOP_LEADERBOARD_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_LEADERBOARD, "div[id$='BOTTOM_LEADERBOARD_0__container__']");
    SLOT_SELECTORS.put(TOP_BOXAD, "div[id$='TOP_BOXAD_0__container__']");
    SLOT_SELECTORS.put(INCONTENT_BOXAD, "div[id$='INCONTENT_BOXAD_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_BOXAD, "div[id$='BOTTOM_BOXAD_0__container__']");
    SLOT_SELECTORS.put(FEED_BOXAD, "div[id$='FEED_BOXAD_0__container__']");
  }

  static {
    GPT_SLOT_SELECTORS = new HashMap<>();
    // Select the slots by the ID prefix, so that all of the following match:
    //   #gpt-top-leaderboard          <- the most common case
    //   #gpt-top-leaderboard-desktop  <- when desktop and mobile slots are
    //   #gpt-top-leaderboard-mobile      put to different places in DOM
    GPT_SLOT_SELECTORS.put(TOP_LEADERBOARD, "[id^=\"gpt-top-leaderboard\"]");
    GPT_SLOT_SELECTORS.put(BOTTOM_LEADERBOARD, "[id^=\"gpt-bottom-leaderboard\"]");
    GPT_SLOT_SELECTORS.put(TOP_BOXAD, "[id^=\"gpt-top-boxad\"]");
    GPT_SLOT_SELECTORS.put(INCONTENT_BOXAD, "[id^=\"gpt-incontent-boxad\"]");
    GPT_SLOT_SELECTORS.put(BOTTOM_BOXAD, "[id^=\"gpt-bottom-boxad\"]");
    GPT_SLOT_SELECTORS.put(FEED_BOXAD, "[id^=\"gpt-feed-boxad\"]");
  }

  private AdsFandomContent() {
  }

  public static String getSlotSelector(String slotName) {
    return SLOT_SELECTORS.get(slotName);
  }

  public static String getGptSlotSelector(String slotName) {
    return GPT_SLOT_SELECTORS.get(slotName);
  }
}
