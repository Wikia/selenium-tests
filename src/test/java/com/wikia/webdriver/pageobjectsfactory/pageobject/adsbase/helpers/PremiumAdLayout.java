package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;

public class PremiumAdLayout {
  private static final String TURN_ON_PREMIUM_LAYOUT = "InstantGlobals.wgAdDriverPremiumAdLayoutCountries=[XX]";

  PremiumAdLayout() {
    throw new IllegalAccessError("Utility class");
  }

  public static String addTurnOnParams(UrlBuilder urlBuilder, Page page) {
    return urlBuilder.appendQueryStringToURL(page.getUrl(), TURN_ON_PREMIUM_LAYOUT);
  }
}
