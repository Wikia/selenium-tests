package com.wikia.webdriver.common.templates.core;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.Dimension;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.logging.PageObjectLogging;

@Listeners({com.wikia.webdriver.common.logging.PageObjectLogging.class,
    com.wikia.webdriver.common.testnglisteners.InvokeMethodAdapter.class})
public abstract class CoreTestTemplate {

  protected WikiaWebDriver driver;
  protected NetworkTrafficInterceptor networkTrafficInterceptor;

  protected void refreshDriver() {
    driver = DriverProvider.getActiveDriver();
  }

  @BeforeSuite(alwaysRun = true)
  public void beforeSuite() {
    prepareDirectories();
  }

  @BeforeMethod(alwaysRun = true)
  public void initTestContext(Method method) {
    TestContext.writeMethodName(method);
    PageObjectLogging.start(method);

    Configuration.clearCustomTestProperties();

    String browser = Configuration.getBrowser();
    setPropertiesFromAnnotationsOnDeclaringClass(method.getDeclaringClass());
    setPropertiesFromAnnotationsOnMethod(method);
    String currentBrowser = Configuration.getBrowser();

    if (!browser.equals(currentBrowser)) {
      PageObjectLogging.logWarning("Parameter override", "Browser parameter changed by annotation"
          + ", old value: " + browser + ", new value: " + currentBrowser);
    }

    prepareURLs();

    if (isTestExcludedFromEnv(method)) {
      throw new SkipException("Test can't be run on " + Configuration.getEnv() + " environment");
    }

    if (method.isAnnotationPresent(GeoEdgeBrowserMobProxy.class)) {
      GeoEdgeBrowserMobProxy geoEdgeBrowserMobProxy = method.getAnnotation(GeoEdgeBrowserMobProxy.class);
      Configuration.setGeoEdgeCountry(geoEdgeBrowserMobProxy.country());
    }

    driver = DriverProvider.getActiveDriver();
    networkTrafficInterceptor = driver.getProxy();
    setWindowSize();

    loadFirstPage();
  }

  private void setTestProperty(String key, String value) {
    if (!"".equals(value)) {
      Configuration.setTestValue(key, value);
    }
  }

  private void setPropertiesFromAnnotationsOnDeclaringClass(Class<?> declaringClass) {
    if (declaringClass.isAnnotationPresent(Execute.class)) {
      setTestProperty("wikiName", declaringClass.getAnnotation(Execute.class).onWikia());
      setTestProperty("disableFlash", declaringClass.getAnnotation(Execute.class).disableFlash());
      setTestProperty("mockAds", declaringClass.getAnnotation(Execute.class).mockAds());
      setTestProperty("disableCommunityPageSalesPitchDialog",
          declaringClass.getAnnotation(Execute.class).disableCommunityPageSalesPitchDialog());
    }

    if (declaringClass.isAnnotationPresent(InBrowser.class)) {
      setTestProperty("browser", declaringClass.getAnnotation(InBrowser.class).browser().getName());
      setTestProperty("browserSize", declaringClass.getAnnotation(InBrowser.class).browserSize());
      setTestProperty("emulator", declaringClass.getAnnotation(InBrowser.class).emulator());
    }
  }

  private void setPropertiesFromAnnotationsOnMethod(Method method) {
    if (method.isAnnotationPresent(Execute.class)) {
      setTestProperty("wikiName", method.getAnnotation(Execute.class).onWikia());
      setTestProperty("disableFlash", method.getAnnotation(Execute.class).disableFlash());
      setTestProperty("mockAds", method.getAnnotation(Execute.class).mockAds());
      setTestProperty("disableCommunityPageSalesPitchDialog",
          method.getAnnotation(Execute.class).disableCommunityPageSalesPitchDialog());
    }

    if (method.isAnnotationPresent(InBrowser.class)) {
      setTestProperty("browser", method.getAnnotation(InBrowser.class).browser().getName());
      setTestProperty("browserSize", method.getAnnotation(InBrowser.class).browserSize());
      setTestProperty("emulator", method.getAnnotation(InBrowser.class).emulator());
    }

    if (method.isAnnotationPresent(UseUnstablePageLoadStrategy.class)) {
      setTestProperty("unstablePageLoadStrategy", "true");
    }

    if (method.isAnnotationPresent(GeoEdgeBrowserMobProxy.class)) {
      setTestProperty("countryCode", method.getAnnotation(GeoEdgeBrowserMobProxy.class).country());
    }

    if (method.isAnnotationPresent(NetworkTrafficDump.class)) {
      setTestProperty("dumpNetworkTraffic",
          String.valueOf(method.getAnnotation(NetworkTrafficDump.class).networkTrafficDump()));
      setTestProperty("useProxy", "true");
    }
  }

  /**
   * Return false if test is excluded from running on current test environment
   */
  protected boolean isTestExcludedFromEnv(Method method) {
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

  protected boolean isNonAnonUserOnDeclaringClass(Class<?> declaringClass) {
    return declaringClass.isAnnotationPresent(Execute.class)
        && declaringClass.getAnnotation(Execute.class).asUser() != User.ANONYMOUS;
  }

  protected boolean isNonAnonUserOnMethod(Method method) {
    return method.isAnnotationPresent(Execute.class)
        && method.getAnnotation(Execute.class).asUser() != User.ANONYMOUS;
  }

  protected void prepareDirectories() {
    CommonUtils.deleteDirectory("." + File.separator + "logs");
    CommonUtils.createDirectory("." + File.separator + "logs");
  }

  protected void setWindowSize() {
    Dimension browserSize = Configuration.getBrowserSize();

    if (!driver.isAndroid()) {
      if (browserSize != null) {
        driver.manage().window().setSize(browserSize);
      } else {
        driver.manage().window().maximize();
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void stop() {
    DriverProvider.close();
  }

  protected void switchToWindow(int index) {
    DriverProvider.switchActiveWindow(index);
    refreshDriver();

    String driverName =
        DriverProvider.getActiveDriver().equals(driver) ? "primary window" : "secondary window";
    PageObjectLogging.log("switchToWindow", "================ " + driverName + " ================",
        true);
  }

  protected abstract void prepareURLs();

  protected abstract void loadFirstPage();

}
