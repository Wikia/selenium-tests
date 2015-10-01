package com.wikia.webdriver.common.core.networktrafficinterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.ProxyServer;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriverException;

/**
 * @author Bogna 'bognix' Knychala
 */
public class NetworkTrafficInterceptor extends ProxyServer {

  private static final int MAX = 8080;
  private static final int MIN = 7070;
  private final int portNumber;
  private Har har;

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
      throw new WebDriverException(ex);
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

  public void checkAssetsStatuses(String domain) {
    har = getHar();
    for (HarEntry entry : har.getLog().getEntries()) {
      if (entry.getRequest().getUrl().contains(domain)) {
        com.wikia.webdriver.common.logging.LOG.result("RESPONSE STATUS: "
            + entry.getResponse().getStatus(), entry.getRequest().getUrl(), entry.getResponse()
            .getStatus() < 400);
      }
    }
  }

  /**
   * Looks for correlator pattern in requests query strings to DFP domain, and logs if all the calls
   * have the same correlator ID. Any difference is ID is logged as failure.
   */
  public void logDFP(String skinCorrelator) {
    har = getHar();
    String expectedCorrelator = null;
    Pattern pt = Pattern.compile("(correlator=)\\d*");

    for (HarEntry entry : har.getLog().getEntries()) {
      if (entry.getRequest().getUrl().contains("pubads.g.doubleclick.net")
          && entry.getRequest().getQueryString().toString().contains(skinCorrelator)) {
        Matcher matcher = pt.matcher(entry.getRequest().getQueryString().toString());
        if (matcher.find()) {
          String correlatorID = matcher.group(0);

          if (expectedCorrelator == null) {
            expectedCorrelator = correlatorID;
          }

          com.wikia.webdriver.common.logging.LOG.result("CORRELATOR CHECK", "CORRELATOR ID: "
              + correlatorID, correlatorID.equals(expectedCorrelator));
        } else {
          throw new WebDriverException("Missing correlator param in query string");
        }
      }
    }
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
      throw new WebDriverException(ex);
    }
  }
}
