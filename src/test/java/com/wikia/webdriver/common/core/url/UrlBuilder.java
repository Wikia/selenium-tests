package com.wikia.webdriver.common.core.url;

import org.apache.commons.lang.StringUtils;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

public class UrlBuilder {

  private static final String PROD_URL_FORMAT = "http://%s%s.%s";
  private static final String DEV_URL_FORMAT = "http://%s%s.%s.%s";
  private String env;

  public UrlBuilder() {
    this.env = Configuration.getEnv();
  }

  public UrlBuilder(String env) {
    this.env = env;
  }

  public static String getUrlForPageWithWWW(String pageName) {
    String environment = Configuration.getEnv();
    String wikiName = Configuration.getWikiName();

    environment = environment.equals("prod") ? "" : environment + ".";
    wikiName = wikiName.equals("wikia") ? "" : wikiName + ".";

    return environment + "www." + wikiName + "wikia.com" + pageName;
  }

  public static String getHostForWiki() {
    return getHostForWiki(Configuration.getWikiName());
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

  public String getUrlForPage(Page page) {
    if (page.getWikiPath() == null) {
      return getUrlForWiki(page.getWikiName(), false);
    }
    return getUrlForPath(page.getWikiName(), page.getWikiPath());
  }

  public String getUrlForPath(String wikiName, String wikiPath) {
    String url = "";
    if(wikiName.endsWith("wikia")){
      url = String.format("/%s/%s", getUrlForWiki(wikiName), wikiPath);
    }else {
      url = String.format("/%s/wiki/%s", getUrlForWiki(wikiName), wikiPath);
    }

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
    return getUrlForWiki(Configuration.getWikiName(), false);
  }

  public String getUrlForWiki(String wikiName) {
    return getUrlForWiki(wikiName, false);
  }

  public String getUrlForWiki(String wikiName, boolean addWWW) {
    EnvType env = Configuration.getEnvType(this.env);
    final String wikiaName = isGlobal(wikiName);

    String www = "";
    if (addWWW) {
      www = "www.";
    }

    switch (env) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        return String.format(DEV_URL_FORMAT, www, wikiaName, devBoxOwner, env.getWikiaDomain());
      }
      case PROD: {
        return String.format(PROD_URL_FORMAT, www, wikiaName, env.getWikiaDomain());
      }
      case STAGING: {
        return String.format(PROD_URL_FORMAT, www, wikiaName, env.getWikiaDomain());
      }
      case SANDBOX: {
        return String.format(DEV_URL_FORMAT, www, this.env, wikiaName, env.getWikiaDomain());
      }
      default:
        return "";
    }
  }

  private String isGlobal(String wikiName) {
    if (wikiName.endsWith(".wikia")) {
      if (Configuration.getEnvType(this.env) == EnvType.DEV) {
        return "wikiaglobal";
      } else {
        return wikiName.replace(".wikia", "");
      }
    } else {
      return wikiName;
    }
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
}
