package com.wikia.webdriver.common.core.urlbuilder;

/**
 * @author Bogna 'bognix' Knychala
 */
public class UrlBuilder {

  private String env;
  private String browser;
  private Boolean isWikia;

  public UrlBuilder(String environment, String browser) {
    this.env = environment;
    this.browser = browser;
  }

  public UrlBuilder(String environment) {
    env = environment;
  }

  public UrlBuilder() {
    env = null;
  }


  public String getUrlForPath(String wikiName, String wikiPath) {
    String url = getUrlForWiki(wikiName);
    if (!(isWikia)) {
      url += "wiki/";
    }
    url += wikiPath;
    return updateUrl(url);
  }

  private String updateUrl(String url) {
    if ("CHROMEMOBILE".equalsIgnoreCase(browser)) {
      return appendQueryStringToURL(url, "useskin=wikiamobile");
    } else if ("CHROMEMOBILEMERCURY".equalsIgnoreCase(browser)) {
      return appendQueryStringToURL(url, "useskin=mercury");
    }
    return url;
  }

  public String getUrlForWiki(String inputWikiName) {
    String prefix;
    String suffix;
    String wikiName = inputWikiName;
    isWikia = wikiName.endsWith("wikia");
    String url = "http://";

    if (isWikia) {
      //If wikia.com than append www
      if ("wikia".equals(wikiName)) {
        prefix = "www.";
      } else {
        //for different languages, for example de.wikia, don't prepend www
        prefix = "";
      }
      suffix = ".com/";
    } else if (wikiName.endsWith(".de")) {
      prefix = "";
      suffix = "/";
    } else {
      prefix = "";
      suffix = ".wikia.com/";
    }

    try {
      if ("prod".equals(env)) {
        url += prefix + wikiName + suffix;
      } else if ("preview".equals(env) || env.contains("sandbox")) {
        url += env + "." + prefix + wikiName + suffix;
      } else if (env.contains("dev")) {
        String devBoxOwner = env.split("-")[1];
        if (isWikia) {
          wikiName = "wikiaglobal";
        }
        url += wikiName + "." + devBoxOwner + "." + "wikia-dev.com/";
      } else if (env != null) {
        url += env + "." + prefix + wikiName + suffix;
      }
    } catch (NullPointerException ex) {
      throw new RuntimeException("ENV property is not set!");
    }
    return url;
  }

  public String appendQueryStringToURL(String url, String qs) {
    String temp;
    if (url.contains("?")) {
      temp = url + "&" + qs;
      return temp;
    } else {
      temp = url + "?" + qs;
      return temp;
    }
  }

  public String removeQueryStringsFromURL(String url) {
    if (url.contains("?")) {
      return url.substring(0, url.indexOf("?"));
    }
    return url;
  }

  public String removeProtocolServerNameFromUrl(String url) {
    return url.substring(url.indexOf('.') + 1, url.length());
  }
}
