package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class UrlBuilder {

  private static final String WOW_WIKI = "wowwiki";
  private static final String SANDBOX_MERCURY_DEV = "sandbox-mercurydev";
  private String browser;
  private String env;

  public UrlBuilder() {
    this.env = Configuration.getEnv();
    this.browser = Configuration.getBrowser();
  }

  public UrlBuilder(String env) {
    this.env = env;
  }

  public UrlBuilder(String env, String browser) {
    this.env = env;
    this.browser = browser;
  }

  public String getUrlForPage(Page page) {
    if (page.getWikiPath() == null) {
      return getUrlForWiki(page.getWikiName());
    }
    return getUrlForPath(page.getWikiName(), page.getWikiPath());
  }

  public String getUrlForPath(String wikiName, String wikiPath) {
    String url = getUrlForWiki(wikiName);
    String separator = wikiName.endsWith("wikia") || wikiName.equals(WOW_WIKI) ? "" : "wiki/";
    url = url + separator + wikiPath;

    return url;
  }

  public String getUrlForPathWithoutWiki(String wikiName, String wikiPath) {
    return getUrlForWiki(wikiName) + wikiPath;
  }

  /**
   * Returns URL with www (world wide web) in the domain.
   *
   * @param wikiName name of the wiki
   * @param wikiPath the part of url path that should appear after resource indicator /wiki/
   */
  public String getWebUrlForPath(String wikiName, String wikiPath) {
    String url = getWebUrlForWiki(wikiName);
    String separator = wikiName.endsWith("wikia") || wikiName.equals(WOW_WIKI) ? "" : "wiki/";
    url = url + separator + wikiPath;

    return url;
  }

  public String getUrlForWiki() {
    return getUrlForWiki(Configuration.getWikiName());
  }

  public String getUrlForWiki(String wikiName) {
    String prefix = getUrlPrefix(wikiName);
    String suffix = getUrlSuffix(wikiName);

    return composeUrl(prefix, wikiName, suffix);
  }

  /**
   * Returns URL with www (world wide web) in the domain, without the path part
   */
  public String getWebUrlForWiki(String wikiName) {
    String prefix = "www.";
    String suffix = getUrlSuffix(wikiName);

    return composeUrl(prefix, wikiName, suffix);
  }

  public String appendQueryStringToURL(String url, String qs) {
    String separator = url.contains("?") ? "&" : "?";
    return url + separator + qs;
  }

  private String getUrlPrefix(String wikiName) {
    return "wikia".equals(wikiName) ? "www." : "";
  }

  private String getUrlSuffix(String wikiName) {
    if (env.contains("dev") && !env.contains(SANDBOX_MERCURY_DEV)) {
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

  private Boolean isMercuryBrowser() {
    return browser != null && "CHROMEMOBILEMERCURY".equalsIgnoreCase(browser);
  }

  private String composeUrl(String prefix, String wikiName, String suffix) {
    String overwrittenWikiName = wikiName;
    String overwrittenPrefix = prefix;
    if (env != null) {
      if ((!env.contains("dev") || env.contains(SANDBOX_MERCURY_DEV)) && !"prod".equals(env)) {
        overwrittenPrefix = env + "." + prefix;
      }

      if (env.contains("dev") && !env.contains(SANDBOX_MERCURY_DEV) && !isMercuryBrowser()
          && wikiName.endsWith("wikia")) {
        overwrittenWikiName = "wikiaglobal";
      }
    }

    return "http://" + overwrittenPrefix + overwrittenWikiName + suffix + "/";
  }
}
