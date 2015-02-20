package com.wikia.webdriver.common.templates;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.UserAgent;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class NewTestTemplate extends NewTestTemplateCore {

  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
    prepareURLs();
  }

  @BeforeMethod(alwaysRun = true)
  public void start(Method method, Object[] data) {
    runProxyServerIfNeeded(method);
    if (method.isAnnotationPresent(UserAgent.class)) {
      setBrowserUserAgent(method.getAnnotation(UserAgent.class).userAgent());
    }

    if (method.isAnnotationPresent(UseUnstablePageLoadStrategy.class)) {
      NewDriverProvider.setUnstablePageLoadStrategy(true);
    }

    startBrowser();
    // Reset unstable page load strategy to default 'false' value
    NewDriverProvider.setUnstablePageLoadStrategy(false);
    logOut();

    if (method.isAnnotationPresent(ExecuteAs.class)) {
      new WikiBasePageObject(driver).logInCookie(method.getAnnotation(ExecuteAs.class).user()
          .getUserName(), method.getAnnotation(ExecuteAs.class).user().getPassword(), wikiURL);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void stop() {
    if (isProxyServerRunning) {
      networkTrafficIntereceptor.stop();
    }
    stopBrowser();
  }
}
