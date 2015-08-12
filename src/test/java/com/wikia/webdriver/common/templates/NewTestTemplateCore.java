package com.wikia.webdriver.common.templates;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeBrowserMobProxy;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeUtils;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import net.lightbody.bmp.proxy.ProxyServer;

@Listeners({com.wikia.webdriver.common.logging.PageObjectLogging.class,
    com.wikia.webdriver.common.testnglisteners.InvokeMethodAdapter.class})
public class NewTestTemplateCore {

  protected WebDriver driver;
  protected UrlBuilder urlBuilder;
  protected String wikiURL;
  protected String wikiCorporateURL;
  protected String wikiCorpSetupURL;
  private DesiredCapabilities capabilities;
  protected NetworkTrafficInterceptor networkTrafficInterceptor;
  protected boolean isProxyServerRunning = false;

  @BeforeSuite(alwaysRun = true)
  public void beforeSuite() {
    prepareDirectories();
  }

  @BeforeMethod(alwaysRun = true)
  public void initTestContext(Method method) {
    TestContext.writeMethodName(method);
    PageObjectLogging.start(method);
  }

  protected void prepareDirectories() {
    CommonUtils.deleteDirectory("." + File.separator + "logs");
    CommonUtils.createDirectory("." + File.separator + "logs");
  }

  protected void prepareURLs() {
    urlBuilder = new UrlBuilder();
    wikiURL = urlBuilder.getUrlForWiki(Configuration.getWikiName());
    wikiCorporateURL = urlBuilder.getUrlForWiki("wikia");
    wikiCorpSetupURL = urlBuilder.getUrlForWiki("corp");
  }

  protected void startBrowser() {
    driver =
        registerDriverListener(NewDriverProvider.getDriverInstanceForBrowser(Configuration
            .getBrowser()));
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  protected WebDriver startCustomBrowser(String browserName) {
    driver = registerDriverListener(NewDriverProvider.getDriverInstanceForBrowser(browserName));
    return driver;
  }

  protected WebDriver registerDriverListener(WebDriver driver) {
    ((EventFiringWebDriver) driver).register(new PageObjectLogging());
    return driver;
  }

  protected void loadFirstPage() {
    driver.get(wikiURL + URLsContent.SPECIAL_VERSION);
  }

  protected void loadFirstPage(WebDriver driver) {
    driver.get(wikiURL + URLsContent.SPECIAL_VERSION);
  }


  protected void logOutCustomDriver(WebDriver customDriver) {
    customDriver.get(wikiURL + URLsContent.LOGOUT);
  }

  protected void stopBrowser() {
    /*
     * if (NewDriverProvider.getMobileDriver() != null &&
     * NewDriverProvider.getMobileDriver().getSessionId() != null) {
     * NewDriverProvider.getMobileDriver().quit(); }
     */
    if (driver != null) {
      try {
        driver.quit();
      } catch (Error e) {
      }
    }
  }

  protected void stopCustomBrowser(WebDriver customDriver) {
    if (customDriver != null) {
      try {
        customDriver.quit();
      } catch (Error e) {
      }
    }
  }

  protected DesiredCapabilities getCapsWithProxyServerSet(ProxyServer server) {
    capabilities = new DesiredCapabilities();
    try {
      capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return capabilities;
  }

  protected void setDriverCapabilities(DesiredCapabilities caps) {
    NewDriverProvider.setDriverCapabilities(caps);
  }

  protected void setWindowSize(int width, int height, WebDriver desiredDriver) {
    Dimension dimension = new Dimension(width, height);
    desiredDriver.manage().window().setSize(dimension);
  }

  protected void setBrowserUserAgent(String userAgent) {
    NewDriverProvider.setBrowserUserAgent(Configuration.getBrowser(), userAgent);
  }

  protected void runProxyServerIfNeeded(Method method) {
    boolean isGeoEdgeSet = false;
    boolean isNetworkTrafficDumpSet = false;
    String countryCode = null;

    if (method.isAnnotationPresent(GeoEdgeBrowserMobProxy.class)) {
      isGeoEdgeSet = true;
      countryCode = method.getAnnotation(GeoEdgeBrowserMobProxy.class).country();
    }

    if (method.isAnnotationPresent(NetworkTrafficDump.class)) {
      isNetworkTrafficDumpSet = true;
    }

    if (!isGeoEdgeSet && !isNetworkTrafficDumpSet) {
      return;
    }

    isProxyServerRunning = true;
    networkTrafficInterceptor = new NetworkTrafficInterceptor();
    networkTrafficInterceptor.startSeleniumProxyServer();
    if (isGeoEdgeSet && !countryCode.isEmpty()) {
      setGeoEdge(countryCode);
    }
    capabilities = getCapsWithProxyServerSet(networkTrafficInterceptor);
    setDriverCapabilities(capabilities);
  }

  public void setGeoEdge(String countryCode) {
    GeoEdgeUtils geoEdgeUtils = new GeoEdgeUtils();
    String ip = geoEdgeUtils.getIPForCountry(countryCode);
    networkTrafficInterceptor.setProxyServer(ip);
  }
}
