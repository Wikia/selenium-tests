package com.wikia.webdriver.common.templates.mobile;

import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.openqa.selenium.Dimension;

public class MobileTestTemplate extends NewTestTemplate {

  @Override
  public void setWindowSize() {
    driver.manage().window().setSize(new Dimension(360, 640));
  }
}
