package com.wikia.webdriver.common.templates;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.annotations.UserAgent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class NewTestTemplate extends NewTestTemplateCore {

  @BeforeMethod(alwaysRun = true)
  public void start(Method method, Object[] data) {
    Configuration.clearCustomTestProperties();
    if (method.isAnnotationPresent(Execute.class)) {
      if (!method.getAnnotation(Execute.class).onWikia().equals("")) {
        Configuration.setTestValue("wikiName", method.getAnnotation(Execute.class).onWikia());
      }
      if (!method.getAnnotation(Execute.class).disableFlash().equals("")) {
        Configuration
            .setTestValue("disableFlash", method.getAnnotation(Execute.class).disableFlash());
      }
    }
    prepareURLs();

    if (method.isAnnotationPresent(DontRun.class)) {
      String[] excludedEnv = method.getAnnotation(DontRun.class).env();
      for (int i = 0; i < excludedEnv.length; i++) {
        if (Configuration.getEnv().contains(excludedEnv[i])) {
          throw new SkipException(
              "Test can't be run on " + Configuration.getEnv() + " environment");
        }
      }
    }

    runProxyServerIfNeeded(method);
    if (method.isAnnotationPresent(UserAgent.class)) {
      setBrowserUserAgent(method.getAnnotation(UserAgent.class).userAgent());
    }

    if (method.isAnnotationPresent(UseUnstablePageLoadStrategy.class)) {
      NewDriverProvider.setUnstablePageLoadStrategy(true);
    }

    String onDriver = method.getAnnotation(Execute.class).allowedDriver();
    if (onDriver.length() > 0 & !onDriver.equalsIgnoreCase(Configuration.getBrowser())) {
      throw new SkipException(
          "The test can not be run on driver " + Configuration
              .getBrowser() + ". The test is restricted to driver " + onDriver);
    } else {
      startBrowser();
    }

    if (method.isAnnotationPresent(Execute.class)) {
      if (method.getAnnotation(Execute.class).asUser() == User.ANONYMOUS) {
        loadFirstPage();
      }
    } else {
      loadFirstPage();
    }
    // Reset unstable page load strategy to default 'false' value
    NewDriverProvider.setUnstablePageLoadStrategy(false);
  }

  @AfterMethod(alwaysRun = true)
  public void stop() {
    if (isProxyServerRunning) {
      networkTrafficInterceptor.stop();
    }
    stopBrowser();
  }
}
