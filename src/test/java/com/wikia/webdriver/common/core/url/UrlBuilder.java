package com.wikia.webdriver.common.core.url;

import org.apache.commons.lang.StringUtils;

import com.wikia.webdriver.common.core.configuration.Configuration;

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

    String qs = Configuration.getQS();
    if (StringUtils.isNotBlank(qs)) {
      url = appendQueryStringToURL(url, qs);
    }

    return url;
  }

  public String getUrlForPath(String wikiPath) {
    return getUrlForPath(Configuration.getWikiName(), wikiPath);
  }

  public String getUrlForWiki() {
    return getUrlForWiki(Configuration.getWikiName());
  }

  public String getUrlForWiki(String wikiName) {
    String prefix = "wikia".equals(wikiName) ? "www." : "";
    String suffix;

    if (env.contains("dev") && !env.contains(SANDBOX_MERCURY_DEV)) {
      String devBoxOwner = env.split("-")[1];

      suffix = "." + devBoxOwner + "." + "wikia-dev.com";
    } else {
      suffix = wikiName.endsWith("wikia") ? ".com" : ".wikia.com";
    }

    return composeUrl(prefix, wikiName, suffix);
  }

  public String appendQueryStringToURL(String url, String qs) {
    String separator = url.contains("?") ? "&" : "?";

    String[] filteredUrl = url.split("#");
    if (filteredUrl.length > 1) {
      return filteredUrl[0] + separator + qs + "#" + filteredUrl[1];
    } else {
      return url + separator + qs;
    }
  }

  private String composeUrl(String prefix, String wikiName, String suffix) {
    String overwrittenWikiName = wikiName;
    String overwrittenPrefix = prefix;
    if (env != null) {
      if ((!env.contains("dev") || env.contains(SANDBOX_MERCURY_DEV)) && !"prod".equals(env)) {
        overwrittenPrefix = env + "." + prefix;
      }

      if (env.contains("dev") && !env.contains(SANDBOX_MERCURY_DEV) &&
          !"CHROMEMOBILEMERCURY".equalsIgnoreCase(browser) && wikiName.endsWith("wikia")) {
        overwrittenWikiName = "wikiaglobal";
      }
    }

    return "http://" + overwrittenPrefix + overwrittenWikiName + suffix + "/";
  }

  public static String getUrlForPageWithWWW(String pageName) {
    String environment = Configuration.getEnv();
    String wikiName = Configuration.getWikiName();

    environment = environment.equals("prod") ? "" : environment + ".";
    wikiName = wikiName.equals("wikia") ? "" : wikiName + ".";

    return environment + "www." + wikiName + "wikia.com" + pageName;
  }

  public static String getHostForWiki() {
    String environment = Configuration.getEnv();
    String wikiName = Configuration.getWikiName();

    environment = environment.equals("prod") ? "" : environment + ".";
    wikiName = wikiName.equals("wikia") ? "www." : wikiName + ".";

    return environment + wikiName + "wikia.com";
  }

  public static String getHostForWiki(String wikiName) {
    String environment = Configuration.getEnv();

    environment = environment.equals("prod") ? "" : environment + ".";
    wikiName = wikiName.equals("wikia") ? "www." : wikiName + ".";

    return environment + wikiName + "wikia.com";
  }

  public static String getUrlForPage(String pageName) {
    return getHostForWiki() + pageName;
  }

  public static String getUrl(String wikiName, String path) {
    String environment = Configuration.getEnv();

    environment = environment.equals("prod") ? "" : environment + ".";

    return "http://" + environment + wikiName + ".wikia.com" + path;
  }
}
