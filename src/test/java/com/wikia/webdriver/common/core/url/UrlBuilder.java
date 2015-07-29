package com.wikia.webdriver.common.core.url;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.common.core.configuration.Configuration;

/**
 * @author drets
 */
public class UrlBuilder {

  private String browser;
  private String env;

  public UrlBuilder() {
    env = Configuration.getEnv();
    browser = Configuration.getBrowser();
  }

  public UrlBuilder(String env) {
    this.env = env;
  }

  public UrlBuilder(String env, String browser) {
    this.env = env;
    this.browser = browser;
  }

  public String getUrlForPath(String wikiName, String wikiPath) {
    String url = getUrlForWiki(wikiName);
    String separator = wikiName.endsWith("wikia") || wikiName.equals("wowwiki") ? "" : "wiki/";
    url = url + separator + wikiPath;

    if ("CHROMEMOBILE".equalsIgnoreCase(browser)) {
      return appendQueryStringToURL(url, "useskin=wikiamobile");
    }

    return url;
  }

  public String getUrlForWiki(String wikiName) {
    String prefix = getUrlPrefix(wikiName);
    String suffix = getUrlSuffix(wikiName);

    return composeUrl(prefix, wikiName, suffix);
  }

  public String appendQueryStringToURL(String url, String qs) {
    String separator = url.contains("?") ? "&" : "?";
    return url + separator + qs;
  }

  private String getUrlPrefix(String wikiName) {
    return wikiName.equals("wikia") ? "www." : "";
  }

  private String getUrlSuffix(String wikiName) {
    if (env.contains("dev") && !env.contains("sandbox-mercurydev")) {
      String devBoxOwner = env.split("-")[1];
      return "." + devBoxOwner + "." + "wikia-dev.com";
    }

    return wikiName.endsWith("wikia") ? ".com" : ".wikia.com";
  }

  /**
   * Return url path i.e. from mlp.wikia.com/wiki/Main_Page returns /wiki/Main_Page
   * 
   * @param driver WebDriver
   * @return String
   */
  public String getUrlPath(WebDriver driver) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return js.executeScript("return location.pathname").toString();
  }

  /**
   * Return url parameters i.e. from mlp.wikia.com/wiki/Main_Page?noads=1 returns ?noads=1
   * 
   * @param driver WebDriver
   * @return String
   */
  public String getUrlParams(WebDriver driver) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return js.executeScript("return location.search").toString();
  }

  private Boolean isMercuryBrowser() {
    return browser != null && browser.equalsIgnoreCase("CHROMEMOBILEMERCURY");
  }

  private String composeUrl(String prefix, String wikiName, String suffix) {
    if (env != null) {
      if ((!env.contains("dev") || env.contains("sandbox-mercurydev")) && !env.equals("prod")) {
        prefix = env + "." + prefix;
      }

      if (env.contains("dev") && !env.contains("sandbox-mercurydev") && !isMercuryBrowser()
          && wikiName.endsWith("wikia")) {
        wikiName = "wikiaglobal";
      }
    }

    return "http://" + prefix + wikiName + suffix + "/";
  }
}
