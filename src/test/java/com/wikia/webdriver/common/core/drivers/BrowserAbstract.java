package com.wikia.webdriver.common.core.drivers;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import net.lightbody.bmp.proxy.CaptureType;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public abstract class BrowserAbstract {

  protected DesiredCapabilities caps = new DesiredCapabilities();
  protected NetworkTrafficInterceptor server;

  /**
   * Get a ready to work instance for chosen browser
   * 
   * @return
   */
  public WikiaWebDriver getInstance() {
    setOptions();
    setProxy();
    setExtensions();
    setBrowserLogging(Level.SEVERE);
    WikiaWebDriver webdriver = create();
    setTimeputs(webdriver);
    setListeners(webdriver);

    return webdriver;
  }

  /**
   * Set Browser specific options, before creating a working instance
   */
  public abstract void setOptions();

  /**
   * Create a working instance of a Browser
   * 
   * @return
   */
  public abstract WikiaWebDriver create();

  protected void setBrowserLogging(Level logLevel) {
    LoggingPreferences loggingprefs = new LoggingPreferences();
    loggingprefs.enable(LogType.BROWSER, logLevel);
    caps.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
  }

  protected void setTimeputs(WebDriver webDriver) {
    webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  protected void setListeners(WikiaWebDriver webDriver) {
    webDriver.register(new PageObjectLogging());
  }

  /**
   * Add browser extensions
   * 
   * @param extensionName
   */
  public abstract void addExtension(String extensionName);

  protected void setExtensions() {
    for (String name : Configuration.getExtensions()) {
      addExtension(name);
    }
  }

  /**
   * Set Proxy instance for a Browser instance
   */
  protected void setProxy() {

    if (Configuration.useProxy()) {
      server = new NetworkTrafficInterceptor();
      String countryCode = Configuration.getCountryCode();
      server.setTrustAllServers(false);
      server.setMitmDisabled(!Boolean.parseBoolean(Configuration.useMITM()));
      server.setRequestTimeout(90, TimeUnit.SECONDS);
      server.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.REQUEST_COOKIES,
          CaptureType.RESPONSE_HEADERS, CaptureType.RESPONSE_COOKIES);
      if (StringUtils.isNotBlank(countryCode)) {
        server.setProxyServer(GeoEdgeProxy.getProxyAddress(countryCode));
      }

      caps.setCapability(CapabilityType.PROXY, server.startSeleniumProxyServer());
    }
  }
}
