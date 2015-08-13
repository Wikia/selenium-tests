package com.wikia.webdriver.common.core.networktrafficinterceptor;

import java.util.HashMap;
import java.util.Map;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.ProxyServer;

import org.openqa.selenium.Proxy;

/**
 * @author Bogna 'bognix' Knychala
 */
public class NetworkTrafficInterceptor extends ProxyServer {

  private Har har;
  private static final int MAX = 8080;
  private static final int MIN = 7070;
  private final int portNumber;

  public NetworkTrafficInterceptor() {
    super();
    portNumber = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
  }

  public Proxy startSeleniumProxyServer() {
    try {
      setPort(portNumber);
      start();
      return seleniumProxy();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public void startIntercepting(String networkTrafficDumpName) {
    this.har = newHar(networkTrafficDumpName);
  }

  public boolean searchRequestUrlInHar(String needle) {
    har = getHar();
    for (HarEntry entry : har.getLog().getEntries()) {
      if (entry.getRequest().getUrl().contains(needle)) {
        return true;
      }
    }
    return false;
  }

  public void setProxyServer(String ip) {
    Map<String, String> options = new HashMap<>();
    options.put("httpProxy", ip);
    setOptions(options);
  }

  @Override
  public void stop() {
    try {
      super.stop();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
