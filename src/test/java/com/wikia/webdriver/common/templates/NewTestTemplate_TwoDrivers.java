package com.wikia.webdriver.common.templates;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;


public class NewTestTemplate_TwoDrivers extends NewTestTemplate {

  protected WebDriver driverOne;
  protected WebDriver driverTwo;

  @Override
  @BeforeMethod(alwaysRun = true)
  public void start(Method method, Object[] data) {
    Configuration.clearCustomTestProperties();
    if (method.isAnnotationPresent(Execute.class)) {
      if(!method.getAnnotation(Execute.class).onWikia().equals("")){
        Configuration.setTestValue("wikiName", method.getAnnotation(Execute.class).onWikia());
      }
    }
    prepareURLs();

    if (method.isAnnotationPresent(DontRun.class)) {
      String[] excludedEnv = method.getAnnotation(DontRun.class).env();
      for (int i = 0; i < excludedEnv.length; i++) {
        if (Configuration.getEnv().contains(excludedEnv[i])) {
          throw new SkipException("Test can't be run on " + Configuration.getEnv() + " environment");
        }
      }
    }

    driverOne = startCustomBrowser(Configuration.getBrowser());
    loadFirstPage(driverOne);
    driverTwo = startCustomBrowser(Configuration.getBrowser());
    loadFirstPage(driverTwo);
    this.driver = driverOne;
  }

  @Override
  @AfterMethod(alwaysRun = true)
  public void stop() {
    stopCustomBrowser(driverOne);
    stopCustomBrowser(driverTwo);
  }

  protected void switchToWindow(WebDriver maximized) {
    setWindowSize(10, 10, driverOne);
    setWindowSize(10, 10, driverTwo);
    maximized.manage().window().maximize();

    String driverName = maximized.equals(driverOne) ? "primary window" : "secondary window";
    PageObjectLogging
        .log("switchToWindow", "================ " + driverName + " ================", true);
  }

}
