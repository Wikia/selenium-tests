package com.wikia.webdriver.common.templates;

import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;


public class NewTestTemplate_TwoDrivers extends NewTestTemplate {

  protected void switchToWindow(int index) {
    DriverProvider.switchActiveWindow(index);
    refreshDriver();

    String driverName = DriverProvider.getActiveDriver().equals(driver) ? "primary window" : "secondary window";
    PageObjectLogging
        .log("switchToWindow", "================ " + driverName + " ================", true);
  }
}
