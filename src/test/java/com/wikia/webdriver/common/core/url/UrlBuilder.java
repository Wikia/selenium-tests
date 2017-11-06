package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

import org.apache.commons.lang.StringUtils;

public class UrlBuilder {

  private static final String PROD_URL_FORMAT = "%s%s.%s";
  private static final String SANDBOX_URL_FORMAT = "%s.%s%s.%s";
  private static final String SANDBOX_NEW_URL_FORMAT = "%s%s.%s.%s";
  private static final String DEV_URL_FORMAT = "%s%s.%s.%s";

  private String env;
  private Boolean forceHttps;
  private Boolean newStagingUrlFormat;

  public UrlBuilder() {
    this.env = Configuration.getEnv();
    this.forceHttps = Configuration.getForceHttps();
    this.newStagingUrlFormat = Configuration.getNewStagingUrlFormat();
  }

  public UrlBuilder(String env, Boolean forceHttps, Boolean newStagingUrlFormat) {
    this.env = env;
    this.forceHttps = forceHttps;
    this.newStagingUrlFormat = newStagingUrlFormat;
  }

  public String normalizePageName(String pageName) {
    return pageName.replace(" ", "_");
  }

  public String getUrlForPageWithWWW(String pageName) {
    return getUrlForWiki(true) + pageName;
  }

  public String getUrlForPage(String pageName) {
    return getUrlForWiki() + pageName;
  }

  public String getUrlForPage(String wikiName, String pageName) {
    return getUrlForWiki(wikiName) + pageName;
  }

  public String getUrlForPage(Page page) {
    if (page.getWikiPath() == null) {
      return getUrlForWiki(page.getWikiName(), false);
    }
    return getUrlForPath(page.getWikiName(), page.getWikiPath());
  }

  public String getUrlForPage(Page page, String qs) {
    return appendQueryStringToURL(page.getUrl(), qs);
  }

  public String getUrlForPath(String wikiName, String wikiPath) {
    String url = "";
    if (!wikiPath.startsWith("/")) {
      url = String.format("%s/%s", getUrlForWiki(wikiName), wikiPath);
    } else {
      url = String.format("%s%s", getUrlForWiki(wikiName), wikiPath);
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

  public String getUrlForWiki(boolean addWWW) {
    return getUrlForWiki(Configuration.getWikiName(), addWWW);
  }


  public String getUrlForWiki(String wikiName, boolean addWWW) {
    final String wikiaName = getWikiaGlobalName(wikiName);
    EnvType envType = Configuration.getEnvType(this.env);

    String www = "";
    if (addWWW) {
      www = "www.";
    }

    switch (envType) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        return String.format(getUrlProtocol() + DEV_URL_FORMAT, www, wikiaName, devBoxOwner, envType.getWikiaDomain());
      }
      case PROD: {
        return String.format(getUrlProtocol() + PROD_URL_FORMAT, www, wikiaName, envType.getWikiaDomain());
      }
      case STAGING: {
        return String.format(getUrlProtocol() + PROD_URL_FORMAT, www, wikiaName, envType.getWikiaDomain());
      }
      case SANDBOX: {
        if (newStagingUrlFormat) {
          return String.format(getUrlProtocol() + SANDBOX_NEW_URL_FORMAT, www, wikiaName, this.env, envType.getWikiaDomain());
        } else {
          return String.format(getUrlProtocol() + SANDBOX_URL_FORMAT, this.env, www, wikiaName, envType.getWikiaDomain());
        }
      }
      default:
        return "";
    }
  }

  private String getUrlProtocol() {
    if (this.forceHttps) {
      return "https://";
    }
    return "http://";
  }

  public String getWikiGlobalURL(){
    EnvType env = Configuration.getEnvType(this.env);

    switch (env) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        return String.format(getUrlProtocol() + "%s.www.%s", devBoxOwner, env.getWikiaDomain());
      }
      case SANDBOX: {
        return String.format(getUrlProtocol() + "%s.www.%s", this.env, env.getWikiaDomain());
      }
      default:
        return String.format(getUrlProtocol() + "www.%s", env.getWikiaDomain());
    }
  }

  private String getWikiaGlobalName(String wikiName) {
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

  public String globallyEnableGeoInstantGlobalOnPage(String pageUrl, String instantGlobal) {
    return this.appendQueryStringToURL(pageUrl, String.format("InstantGlobals.%s=[XX]", instantGlobal));
  }

  public String globallyDisableGeoInstantGlobalOnPage(String pageUrl, String instantGlobal) {
    return this.appendQueryStringToURL(pageUrl, String.format("InstantGlobals.%s=[ZZ]", instantGlobal));
  }
}
