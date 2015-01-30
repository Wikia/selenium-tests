package com.wikia.webdriver.common.templates.mobile;

import com.wikia.webdriver.common.driverprovider.mobileprovider.MobileDriverProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileTestTemplate extends NewTestTemplate {

  @Override
  public void startBrowser() {
    driver = new MobileDriverProvider(config).getDriverInstance();
  }
}
