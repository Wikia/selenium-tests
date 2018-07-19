package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class SoundMonitor extends BasePageObject {

  SoundMonitor() {
    throw new IllegalAccessError("Utility class");
  }

  public static Boolean wasSoundHeardOnPage(JavascriptActions jsActions) {
    new Wait(DriverProvider.getActiveDriver()).forScriptToExecute("soundMonitor.soundHeard");

    return (Boolean) jsActions.execute("soundMonitor.soundHeard");
  }
}
