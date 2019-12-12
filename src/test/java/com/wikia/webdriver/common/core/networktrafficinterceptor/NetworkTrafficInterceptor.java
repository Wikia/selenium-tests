package com.wikia.webdriver.common.core.networktrafficinterceptor;

import com.wikia.webdriver.common.logging.Log;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriverException;

import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkTrafficInterceptor extends BrowserMobProxyServer {

  private Har har;

  public Proxy startBrowserMobProxyServer() {
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
        Log.log(
            "RESPONSE STATUS: " + entry.getResponse().getStatus(),
            entry.getRequest().getUrl(),
            entry.getResponse().getStatus() < 400
        );
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
