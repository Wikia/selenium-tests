package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;

public class PremiumAdLayout {
  private static final String INSTANT_GLOBAL_PREMIUM_LAYOUT = "wgAdDriverPremiumAdLayoutCountries";

  PremiumAdLayout() {
    throw new IllegalAccessError("Utility class");
  }

  public static String addTurnOnParams(UrlBuilder urlBuilder, Page page) {
    return urlBuilder.globallyEnableGeoInstantGlobalOnPage(page.getUrl(), INSTANT_GLOBAL_PREMIUM_LAYOUT);
  }
}
