package com.wikia.webdriver.common.templates.mobile;

import com.wikia.webdriver.common.driverprovider.mobileprovider.MobileDriverProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class MobileTestTemplate extends NewTestTemplate {

  @Override
  public WebDriver startBrowser() {
    driver = registerDriverListener(new MobileDriverProvider().getDriverInstance());

    return driver;
  }

  @Override
  public void setWindowSize() {
    driver.manage().window().setSize(new Dimension(360, 640));
  }
}
