package com.wikia.webdriver.common.templates;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.UserAgent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class NewTestTemplate extends NewTestTemplateCore {

  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
    prepareURLs();
  }

  @BeforeMethod(alwaysRun = true)
  public void start(Method method, Object[] data) {
    if (method.isAnnotationPresent(DontRun.class)) {
      String[] excludedEnv = method.getAnnotation(DontRun.class).env();
      for (int i = 0; i < excludedEnv.length; i++) {
        if (Configuration.getEnv().contains(excludedEnv[i])) {
          throw new SkipException("Test can't be run on " + Configuration.getEnv() + " environment");
        }
      }
    }

    runProxyServerIfNeeded(method);
    if (method.isAnnotationPresent(UserAgent.class)) {
      setBrowserUserAgent(
          method.getAnnotation(UserAgent.class).userAgent()
      );
    }

    if (method.isAnnotationPresent(UseUnstablePageLoadStrategy.class)) {
      NewDriverProvider.setUnstablePageLoadStrategy(true);
    }

    startBrowser();
    //Reset unstable page load strategy to default 'false' value
    NewDriverProvider.setUnstablePageLoadStrategy(false);
    logOut();
  }

  @AfterMethod(alwaysRun = true)
  public void stop() {
    if (isProxyServerRunning) {
      networkTrafficInterceptor.stop();
    }
    stopBrowser();
  }
}
