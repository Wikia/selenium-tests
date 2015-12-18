package com.wikia.webdriver.testcases.seotests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public class TestSkipper {
  public static final int VARIANT_ALL = 0;
  public static final int VARIANT_ONLY_DESKTOP = 1;
  public static final int VARIANT_ONLY_MOBILE = 2;
  public static final int VARIANT_ONLY_ANONYMOUS = 3;
  public static final int VARIANT_ONLY_ANONYMOUS_MOBILE = 4;
  public static final int VARIANT_ONLY_LOGGED_IN = 5;
  public static final int VARIANT_ONLY_LOGGED_IN_DESKTOP = 6;
  public static final String LOG_NAME = "Test Skipper";

  private int variant;

  public TestSkipper(int variant) {
    this.variant = variant;
  }

  private boolean isMobile() {
    return Configuration.getBrowser().contains("MOBILE");
  }

  private boolean canRunForLoggedIn() {
    return !(variant == VARIANT_ONLY_ANONYMOUS || variant == VARIANT_ONLY_ANONYMOUS_MOBILE);
  }

  private boolean canRunForAnonymous() {
    return !(variant == VARIANT_ONLY_LOGGED_IN || variant == VARIANT_ONLY_LOGGED_IN_DESKTOP);
  }

  private boolean canRunOnMobile() {
    return !(variant == VARIANT_ONLY_DESKTOP || variant == VARIANT_ONLY_LOGGED_IN_DESKTOP);
  }

  private boolean canRunOnDesktop() {
    return !(variant == VARIANT_ONLY_MOBILE || variant == VARIANT_ONLY_ANONYMOUS_MOBILE);
  }

  public boolean shouldSkip(boolean loggedIn) {
    if (isMobile() && !canRunOnMobile()) {
      PageObjectLogging.log(LOG_NAME, "This testcase should not run on mobile", true);
      return true;
    }
    if (!isMobile() && !canRunOnDesktop()) {
      PageObjectLogging.log(LOG_NAME, "This testcase should not run on desktop", true);
      return true;
    }
    if (loggedIn && !canRunForLoggedIn()) {
      PageObjectLogging.log(LOG_NAME, "This testcase should not run for logged in users", true);
      return true;
    }
    if (!loggedIn && !canRunForAnonymous()) {
      PageObjectLogging.log(LOG_NAME, "This testcase should not run for anonymous users", true);
      return true;
    }
    return false;
  }
}
