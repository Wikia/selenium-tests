package com.wikia.webdriver.common.core.drivers;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
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
    Proxy proxyServer;
    if (Configuration.useProxy()) {
      if ("true".equals(Configuration.useZap())) {
        proxyServer = new Proxy();
        String proxyAddress = String.format("%s:%s", XMLReader.getValue("zap_proxy.address"),
            Integer.parseInt(XMLReader.getValue("zap_proxy.port")));
        proxyServer.setHttpProxy(proxyAddress);
        proxyServer.setSslProxy(proxyAddress);
      } else {
        server = new NetworkTrafficInterceptor();
        server.setTrustAllServers(true);
        server.setMitmDisabled(!Boolean.parseBoolean(Configuration.useMITM()));
        server.setRequestTimeout(90, TimeUnit.SECONDS);
        server.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.REQUEST_COOKIES,
            CaptureType.RESPONSE_HEADERS, CaptureType.RESPONSE_COOKIES);
        proxyServer = server.startSeleniumProxyServer();
      }
      caps.setCapability(CapabilityType.PROXY, proxyServer);
    }
  }
}
