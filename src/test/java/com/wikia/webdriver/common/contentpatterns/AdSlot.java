package com.wikia.webdriver.common.contentpatterns;

import lombok.Getter;

public enum AdSlot {
  TOP_LEADERBOARD("top_leaderboard","div[id*='wka1b.LB/top_leaderboard'][id*='_0__container__']", "div[id*='wka1b.LB/top_leaderboard']"),
  BOTTOM_LEADERBOARD("bottom_leaderboard","div[id*='wka1b.PF/bottom_leaderboard'][id*='_0__container__']","div[id*='wka1b.PF/bottom_leaderboard']"),
  TOP_BOXAD("top_boxad", "div[id*='wka1b.MR/top_boxad'][id*='_0__container__']", "div[id*='wka1b.MR/top_boxad']"),
  INCONTENT_BOXAD("incontent_boxad", "div[id*='/incontent_boxad']", "div[id*='/incontent_boxad']"),
  BOTTOM_BOXAD("bottom_boxad", "div[id$='bottom_boxad_0__container__']", "#bottom_boxad"),
  FEED_BOXAD("feed_boxad", "div[id$='google_ads_iframe_/5441/wka1b.PF/feed_boxad/desktop/ns-article/_fandom-all_0__container__']", "#feed_boxad"),
  FEED_BOXAD_MOBILE("feed_boxad", "div[id$='google_ads_iframe_/5441/wka1b.PF/feed_boxad/smartphone/ns-article/_fandom-all_0__container__']", "#feed_boxad"),

  FEED_BOXAD_IFRAME("feed_boxad", "div[id$='google_ads_iframe_/5441/wka1b.PF/feed_boxad/desktop/ns-topic/_fandom-all_0__container__']", "#feed_boxad"),
  FEED_BOXAD_MOBILE_IFRAME("feed_boxad", "div[id$='google_ads_iframe_/5441/wka1b.PF/feed_boxad/smartphone/ns-topic/_fandom-all_0__container__']", "feed_boxad"),
  BOTTOM_LEADERBOARD_IFRAME("bottom_leaderboard", "div[id*='wka1b.PF/bottom_leaderboard'][id*='_0__container__'] iframe", "#bottom_leaderboard");

  @Getter
  private final String name;

  @Getter
  private final String id;

  @Getter
  private final String mainPath;


  AdSlot(String name, String id, String mainPath) {
    this.name = name;
    this.id = id;
    this.mainPath = mainPath;
  }
}
