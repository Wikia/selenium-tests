package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;

public class SoundMonitor {
  public static Boolean wasSoundHeardOnPage(JavascriptActions jsActions) {
    return (Boolean) jsActions.execute("soundMonitor.soundHeard");
  }
}
