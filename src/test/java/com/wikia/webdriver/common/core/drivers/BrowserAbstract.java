package com.wikia.webdriver.common.core.drivers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.testnglisteners.BrowserAndTestEventListener;

import net.lightbody.bmp.mitm.TrustSource;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public abstract class BrowserAbstract {

  protected DesiredCapabilities caps = new DesiredCapabilities();
  protected NetworkTrafficInterceptor server;

  /**
   * Get a ready to work instance for chosen browser
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
    webDriver.register(new BrowserAndTestEventListener());
  }

  /**
   * Add browser extensions
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
      Proxy proxyServer = new Proxy();
      if ("true".equals(Configuration.useZap())) {
        String zapProxyAddress = String.format(
            "%s:%s",
            XMLReader.getValue("zap_proxy.address"),
            Integer.parseInt(XMLReader.getValue("zap_proxy.port"))
        );
        proxyServer.setHttpProxy(zapProxyAddress);
        proxyServer.setSslProxy(zapProxyAddress);
      } else {
        server = new NetworkTrafficInterceptor();
        server.setTrustAllServers(true);
        server.setConnectTimeout(90, TimeUnit.SECONDS);
        server.setMitmDisabled(!Boolean.parseBoolean(Configuration.useMITM()));
        server.setRequestTimeout(90, TimeUnit.SECONDS);
        server.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_HEADERS);
        server.setUseEcc(true);
        proxyServer = server.startBrowserMobProxyServer();
      }
      caps.setCapability(CapabilityType.PROXY, proxyServer);
    }
  }
}
