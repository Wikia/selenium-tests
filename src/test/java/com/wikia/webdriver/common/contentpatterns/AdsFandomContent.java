package com.wikia.webdriver.common.contentpatterns;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class AdsFandomContent {

  public static final Map<String, String> IFRAME_SLOT_SELECTORS;
  public static final Map<String, String> SLOT_SELECTORS;

  public static final String TOP_LEADERBOARD = "top_leaderboard";
  public static final String BOTTOM_LEADERBOARD = "bottom_leaderboard";
  public static final String TOP_BOXAD = "top_boxad";
  public static final String INCONTENT_BOXAD = "incontent_boxad";
  public static final String BOTTOM_BOXAD = "bottom_boxad";
  public static final String FEED_BOXAD = "feed_boxad";

  static {
    SLOT_SELECTORS = new HashMap<>();
    SLOT_SELECTORS.put(TOP_LEADERBOARD, "div[id$='top_leaderboard_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_LEADERBOARD,
                       "div[id*='wka1b.PF/bottom_leaderboard'][id*='_0__container__']"
    );
    SLOT_SELECTORS.put(TOP_BOXAD,
                       "div[id*='wka1b.MR/top_boxad'][id*='_0__container__']"
    );
    SLOT_SELECTORS.put(INCONTENT_BOXAD, "div[id$='incontent_boxad_0__container__']");
    SLOT_SELECTORS.put(BOTTOM_BOXAD, "div[id$='bottom_boxad_0__container__']");
    SLOT_SELECTORS.put(FEED_BOXAD,
                       "div[id$='google_ads_iframe_/5441/wka1b.PF/feed_boxad/desktop/ns-article/_fandom-all_0__container__']");

    IFRAME_SLOT_SELECTORS = new HashMap<>();
    IFRAME_SLOT_SELECTORS.put(BOTTOM_LEADERBOARD,
                              "div[id*='wka1b.PF/bottom_leaderboard'][id*='_0__container__'] iframe"
    );
  }

  private AdsFandomContent() {
  }

  /**
   * Returns a querySelector() string to the element created by GPT
   *
   * @return String DOM id of element injected by GPT on a page
   */
  public static String getSlotSelectorString(AdSlot slot) {
    return slot.getId();
  }

  public static By getSlotSelector(AdSlot slot) {
    return By.id(slot.getName());
  }
}
