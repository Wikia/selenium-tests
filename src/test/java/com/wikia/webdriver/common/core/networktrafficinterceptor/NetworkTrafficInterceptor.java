package com.wikia.webdriver.common.core.networktrafficinterceptor;


import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.browsermob.core.har.Har;
import org.browsermob.core.har.HarEntry;
import org.browsermob.proxy.ProxyServer;
import org.browsermob.proxy.http.BrowserMobHttpRequest;
import org.browsermob.proxy.http.RequestInterceptor;
import org.openqa.selenium.Proxy;

import java.util.HashMap;
import java.util.Map;

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

  public void changeHeader(final String headerName, final String newValue) {
    addRequestInterceptor(new RequestInterceptor() {
      @Override
      public void process(BrowserMobHttpRequest request) {
        request.getMethod().removeHeaders(headerName);
        try {
          request.getMethod().addHeader(
              headerName,
              newValue
          );
        } catch (Exception e) {
          PageObjectLogging.log("changeHeader", e.getMessage(), false);
        }
      }
    });
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
