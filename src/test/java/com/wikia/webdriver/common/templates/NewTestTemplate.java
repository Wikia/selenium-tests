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

  private void setPropertiesFromAnnotationsOnDeclaringClass(Class<?> declaringClass) {
    if (declaringClass.isAnnotationPresent(Execute.class)) {
      setTestProperty("wikiName", declaringClass.getAnnotation(Execute.class).onWikia());
      setTestProperty("disableFlash", declaringClass.getAnnotation(Execute.class).disableFlash());
      setTestProperty("browser", declaringClass.getAnnotation(Execute.class).browser());
      setTestProperty("browserSize", declaringClass.getAnnotation(Execute.class).browserSize());
      setTestProperty("onDevice", declaringClass.getAnnotation(Execute.class).onDevice());
    }
  }

  private void setPropertiesFromAnnotationsOnMethod(Method method) {
    if (method.isAnnotationPresent(Execute.class)) {
      setTestProperty("wikiName", method.getAnnotation(Execute.class).onWikia());
      setTestProperty("disableFlash", method.getAnnotation(Execute.class).disableFlash());
      setTestProperty("browser", method.getAnnotation(Execute.class).browser());
      setTestProperty("browserSize", method.getAnnotation(Execute.class).browserSize());
      setTestProperty("onDevice", method.getAnnotation(Execute.class).onDevice());
    }
  }

  /**
   * Return false if test is excluded from running on current test environment
   */
  private boolean isTestExcludedFromEnv(Method method){
    if (method.isAnnotationPresent(DontRun.class)) {
      String[] excludedEnvs = method.getAnnotation(DontRun.class).env();

      for (String excludedEnv : excludedEnvs) {
        if (Configuration.getEnv().contains(excludedEnv)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isNonAnonUserOnDeclaringClass(Class<?> declaringClass) {
    return declaringClass.isAnnotationPresent(Execute.class) &&
           declaringClass.getAnnotation(Execute.class).asUser() != User.ANONYMOUS;
  }

  private boolean isNonAnonUserOnMethod(Method method) {
    return method.isAnnotationPresent(Execute.class) &&
           method.getAnnotation(Execute.class).asUser() != User.ANONYMOUS;
  }

  @BeforeMethod(alwaysRun = true)
  public void start(Method method, Object[] data) {
    Configuration.clearCustomTestProperties();
    Class<?> declaringClass = method.getDeclaringClass();

    String browser = Configuration.getBrowser();
    setPropertiesFromAnnotationsOnDeclaringClass(declaringClass);
    setPropertiesFromAnnotationsOnMethod(method);
    String currentBrowser = Configuration.getBrowser();

    if (!browser.equals(currentBrowser)) {
      PageObjectLogging
          .logWarning("Parameter override", "Browser parameter changed by annotation"
                                            + ", old value: " + browser
                                            + ", new value: " + currentBrowser);
    }

    prepareURLs();

    if (isTestExcludedFromEnv(method)) {
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

    startBrowser();
    setWindowSize();

    if (!isNonAnonUserOnDeclaringClass(declaringClass) && !isNonAnonUserOnMethod(method)) {
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
