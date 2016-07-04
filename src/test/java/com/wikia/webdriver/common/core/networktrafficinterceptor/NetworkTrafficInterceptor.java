package com.wikia.webdriver.common.core.networktrafficinterceptor;

import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriverException;

import com.wikia.webdriver.common.logging.PageObjectLogging;

public class NetworkTrafficInterceptor extends BrowserMobProxyServer {

  private Har har;

  public Proxy startSeleniumProxyServer() {
    start();

    return ClientUtil.createSeleniumProxy(this);
  }

  public void startIntercepting(String networkTrafficDumpName) {
    this.har = newHar(networkTrafficDumpName);
  }

  public void startIntercepting() {
    this.har = newHar(RandomStringUtils.random(5));
  }

  public HarEntry getEntryByUrlPart(String needle) {
    har = getHar();
    for (HarEntry entry : har.getLog().getEntries()) {
      if (entry.getRequest().getUrl().contains(needle)) {
        return entry;
      }
    }
    return null;
  }

  public HarEntry getEntryByUrlPattern(String pattern) {
    har = getHar();
    for (HarEntry entry : har.getLog().getEntries()) {
      if (entry.getRequest().getUrl().matches(pattern)) {
        return entry;
      }
    }
    return null;
  }

  public void checkAssetsStatuses(String domain) {
    har = getHar();
    for (HarEntry entry : har.getLog().getEntries()) {
      if (entry.getRequest().getUrl().contains(domain)) {
        PageObjectLogging.log("RESPONSE STATUS: " + entry.getResponse().getStatus(),
            entry.getRequest().getUrl(), entry.getResponse().getStatus() < 400);
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

          PageObjectLogging.log("CORRELATOR CHECK", "CORRELATOR ID: " + correlatorID,
              correlatorID.equals(expectedCorrelator));
        } else {
          throw new WebDriverException("Missing correlator param in query string");
        }
      }
    }
  }

  public void setProxyServer(String ip) {
    setChainedProxy(new InetSocketAddress(ip.split(":")[0], 8888));
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
