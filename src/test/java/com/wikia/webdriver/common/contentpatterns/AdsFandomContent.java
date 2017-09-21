package com.wikia.webdriver.common.contentpatterns;

import java.util.HashMap;
import java.util.Map;

public class AdsFandomContent {

  public static final Map<String, String> SLOT_SELECTORS;
  public static final Map<String, String> GPT_SLOT_SELECTORS;

  public static final String TOP_LEADERBOARD = "TOP_LEADERBOARD";
  public static final String BOTTOM_LEADERBOARD = "BOTTOM_LEADERBOARD";
  public static final String BOTTOM_LEADERBOARD_DESKTOP = "BOTTOM_LEADERBOARD_DESKTOP";
  public static final String BOTTOM_LEADERBOARD_MOBILE = "BOTTOM_LEADERBOARD_MOBILE";
  public static final String TOP_BOXAD = "TOP_BOXAD";
  public static final String TOP_BOXAD_DESKTOP = "TOP_BOXAD_DESKTOP";
  public static final String TOP_BOXAD_MOBILE = "TOP_BOXAD_MOBILE";
  public static final String INCONTENT_BOXAD = "INCONTENT_BOXAD";
  public static final String INCONTENT_BOXAD_DESKTOP = "INCONTENT_BOXAD_DESKTOP";
  public static final String INCONTENT_BOXAD_MOBILE = "INCONTENT_BOXAD_MOBILE";
  public static final String BOTTOM_BOXAD = "BOTTOM_BOXAD";
  public static final String FEED_BOXAD = "FEED_BOXAD";

  static {
    SLOT_SELECTORS = new HashMap<>();
    SLOT_SELECTORS.put(TOP_LEADERBOARD, "div[id$='TOP_LEADERBOARD_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_LEADERBOARD, "div[id$='BOTTOM_LEADERBOARD_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_LEADERBOARD_DESKTOP, "div[id$='BOTTOM_LEADERBOARD_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_LEADERBOARD_MOBILE, "div[id$='BOTTOM_LEADERBOARD_0__container__']");
    SLOT_SELECTORS.put(TOP_BOXAD, "div[id$='TOP_BOXAD_0__container__']");
    SLOT_SELECTORS.put(TOP_BOXAD_MOBILE, "div[id$='TOP_BOXAD_0__container__']");
    SLOT_SELECTORS.put(TOP_BOXAD_DESKTOP, "div[id$='TOP_BOXAD_0__container__']");
    SLOT_SELECTORS.put(INCONTENT_BOXAD, "div[id$='INCONTENT_BOXAD_0__container__']");
    SLOT_SELECTORS.put(INCONTENT_BOXAD_MOBILE, "div[id$='INCONTENT_BOXAD_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_BOXAD, "div[id$='BOTTOM_BOXAD_0__container__']");
    SLOT_SELECTORS.put(FEED_BOXAD, "div[id$='FEED_BOXAD_0__container__']");
  }

  static {
    GPT_SLOT_SELECTORS = new HashMap<>();
    GPT_SLOT_SELECTORS.put(TOP_LEADERBOARD, "#gpt-top-leaderboard");
    GPT_SLOT_SELECTORS.put(BOTTOM_LEADERBOARD, "#gpt-bottom-leaderboard");
    GPT_SLOT_SELECTORS.put(BOTTOM_LEADERBOARD_DESKTOP, "#gpt-bottom-leaderboard-desktop");
    GPT_SLOT_SELECTORS.put(BOTTOM_LEADERBOARD_MOBILE, "#gpt-bottom-leaderboard-mobile");
    GPT_SLOT_SELECTORS.put(TOP_BOXAD, "[id^=\"gpt-top-boxad\"]");
    GPT_SLOT_SELECTORS.put(TOP_BOXAD_DESKTOP, "#gpt-top-boxad-desktop");
    GPT_SLOT_SELECTORS.put(TOP_BOXAD_MOBILE, "#gpt-top-boxad-mobile");
    GPT_SLOT_SELECTORS.put(INCONTENT_BOXAD, "#gpt-incontent-boxad");
    GPT_SLOT_SELECTORS.put(INCONTENT_BOXAD_DESKTOP, "#gpt-incontent-boxad-desktop");
    GPT_SLOT_SELECTORS.put(INCONTENT_BOXAD_MOBILE, "#gpt-incontent-boxad-mobile");
    GPT_SLOT_SELECTORS.put(BOTTOM_BOXAD, "#gpt-bottom-boxad");
    GPT_SLOT_SELECTORS.put(FEED_BOXAD, "#gpt-feed-boxad");
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
