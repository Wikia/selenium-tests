package com.wikia.webdriver.common.templates;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.annotations.UserAgent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class NewTestTemplate extends NewTestTemplateCore {

  private void setTestProperty(String key, String value) {
    if (!"".equals(value)) {
      Configuration.setTestValue(key, value);
    }
  }

  /**
   * Return false if test is excluded from running on current test environment
   * @param method
   * @return
   */
  private boolean isTestExcludedFromEnv(Method method){
    if (method.isAnnotationPresent(DontRun.class)) {
      String[] excludedEnv = method.getAnnotation(DontRun.class).env();
      for (int i = 0; i < excludedEnv.length; i++) {
        if (Configuration.getEnv().contains(excludedEnv[i])) {
          return true;
        }
      }
    }
    return false;
  }

  @BeforeMethod(alwaysRun = true)
  public void start(Method method, Object[] data) {
    Configuration.clearCustomTestProperties();
    Class<?> methodClass = method.getDeclaringClass();

    if (methodClass.isAnnotationPresent(Execute.class)) {
      setTestProperty("wikiName", methodClass.getAnnotation(Execute.class).onWikia());
      setTestProperty("disableFlash", methodClass.getAnnotation(Execute.class).disableFlash());
      setTestProperty("browser", methodClass.getAnnotation(Execute.class).browser());
      setTestProperty("browserSize", methodClass.getAnnotation(Execute.class).browserSize());
    }

    if (method.isAnnotationPresent(Execute.class)) {
      setTestProperty("wikiName", method.getAnnotation(Execute.class).onWikia());
      setTestProperty("disableFlash", method.getAnnotation(Execute.class).disableFlash());
      setTestProperty("browser", method.getAnnotation(Execute.class).browser());
      setTestProperty("browserSize", method.getAnnotation(Execute.class).browserSize());
    }

    prepareURLs();

    if(isTestExcludedFromEnv(method)){
      throw new SkipException(
          "Test can't be run on " + Configuration.getEnv() + " environment");
    }

    runProxyServerIfNeeded(method);
    if (method.isAnnotationPresent(UserAgent.class)) {
      setBrowserUserAgent(method.getAnnotation(UserAgent.class).userAgent());
    }

    if (method.isAnnotationPresent(UseUnstablePageLoadStrategy.class)) {
      NewDriverProvider.setUnstablePageLoadStrategy(true);
    }

    if (method.isAnnotationPresent(Execute.class)) {
      String onDriver = method.getAnnotation(Execute.class).allowedDriver();
      if (onDriver.length() > 0 & !onDriver.equalsIgnoreCase(Configuration.getBrowser())) {
        String errorMessage = "The test can not be run on driver " + Configuration.getBrowser()
                              + ". The test is restricted to driver " + onDriver +
                              " as instructed in the test annotation.";
        PageObjectLogging.log("allowedDriver annotation", errorMessage, false);
        throw new SkipException(errorMessage);
      }
    }

    startBrowser();
    setWindowSize();

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
