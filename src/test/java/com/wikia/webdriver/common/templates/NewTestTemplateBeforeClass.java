package com.wikia.webdriver.common.templates;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class NewTestTemplateBeforeClass extends NewTestTemplateCore {

  public NewTestTemplateBeforeClass() {
    super();
  }

  @BeforeClass(alwaysRun = true)
  public void start() {
    prepareURLs();
    startBrowser();
    logOut();
  }

  @AfterClass(alwaysRun = true)
  public void stop() {
    stopBrowser();
  }
}
