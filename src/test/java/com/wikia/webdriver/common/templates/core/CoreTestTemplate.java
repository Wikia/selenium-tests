package com.wikia.webdriver.common.templates.core;

import com.wikia.webdriver.common.core.*;
import com.wikia.webdriver.common.core.annotations.*;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.testnglisteners.BrowserAndTestEventListener;

import org.openqa.selenium.Dimension;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

@Listeners({BrowserAndTestEventListener.class,
            com.wikia.webdriver.common.testnglisteners.InvokeMethodAdapter.class})
public abstract class CoreTestTemplate {

  protected WikiaWebDriver driver;
  protected NetworkTrafficInterceptor networkTrafficInterceptor;

  private void refreshDriver() {
    driver = DriverProvider.getActiveDriver();
  }

  @BeforeSuite(alwaysRun = true)
  public void beforeSuite() {
    prepareDirectories();
    Helios.updateTokenCache();
  }

  @BeforeMethod(alwaysRun = true)
  public void initTestContext(Method method) {
    TestContext.writeMethodName(method);
    Log.startTest(method);

    Configuration.clearCustomTestProperties();

    String browser = Configuration.getBrowser();
    setPropertiesFromAnnotationsOnDeclaringClass(method.getDeclaringClass());
    setPropertiesFromAnnotationsOnMethod(method);
    String currentBrowser = Configuration.getBrowser();

    if (!browser.equals(currentBrowser)) {
      Log.warning("Parameter override",
                  "Browser parameter changed by annotation" + ", old value: " + browser
                  + ", new value: " + currentBrowser
      );
    }

    prepareURLs();

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
      setTestProperty("language", declaringClass.getAnnotation(Execute.class).language());
      setTestProperty("disableFlash", declaringClass.getAnnotation(Execute.class).disableFlash());
      setTestProperty("mockAds", declaringClass.getAnnotation(Execute.class).mockAds());
      setTestProperty("disableCommunityPageSalesPitchDialog",
                      declaringClass.getAnnotation(Execute.class)
                          .disableCommunityPageSalesPitchDialog()
      );
    }

    if (declaringClass.isAnnotationPresent(InBrowser.class)) {
      setTestProperty("browser", declaringClass.getAnnotation(InBrowser.class).browser().getName());
      setTestProperty("browserSize", declaringClass.getAnnotation(InBrowser.class).browserSize());
    }
  }

  private void setPropertiesFromAnnotationsOnMethod(Method method) {
    if (method.isAnnotationPresent(Execute.class)) {
      setTestProperty("wikiName", method.getAnnotation(Execute.class).onWikia());
      setTestProperty("language", method.getAnnotation(Execute.class).language());
      setTestProperty("disableFlash", method.getAnnotation(Execute.class).disableFlash());
      setTestProperty("mockAds", method.getAnnotation(Execute.class).mockAds());
      setTestProperty("disableCommunityPageSalesPitchDialog",
                      method.getAnnotation(Execute.class).disableCommunityPageSalesPitchDialog());
    }

    if (method.isAnnotationPresent(InBrowser.class)) {
      setTestProperty("browser", method.getAnnotation(InBrowser.class).browser().getName());
      setTestProperty("browserSize", method.getAnnotation(InBrowser.class).browserSize());
    }

    if (method.isAnnotationPresent(UseUnstablePageLoadStrategy.class)) {
      setTestProperty("unstablePageLoadStrategy", "true");
    }

    if (method.isAnnotationPresent(NetworkTrafficDump.class)) {
      setTestProperty("dumpNetworkTraffic",
                      String.valueOf(method.getAnnotation(NetworkTrafficDump.class)
                                         .networkTrafficDump())
      );
      setTestProperty("useProxy", "true");
      setTestProperty("useMITM",
                      String.valueOf(method.getAnnotation(NetworkTrafficDump.class).useMITM())
      );
    }

    if (method.isAnnotationPresent(UnsafePageLoad.class) || method.getDeclaringClass()
        .isAnnotationPresent(UnsafePageLoad.class)) {
      setTestProperty("unsafePageLoad", "true");
    }
  }

  private void prepareDirectories() {
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
  @AfterClass(alwaysRun = true)
  public void stop() {
    DriverProvider.close();
  }

  protected void switchToWindow(int index) {
    DriverProvider.switchActiveWindow(index);
    refreshDriver();

    String driverName = DriverProvider.getActiveDriver().equals(driver) ? "primary window"
                                                                        : "secondary window";
    Log.log("switchToWindow", "================ " + driverName + " ================", true);
  }

  protected abstract void prepareURLs();

  protected abstract void loadFirstPage();
}
