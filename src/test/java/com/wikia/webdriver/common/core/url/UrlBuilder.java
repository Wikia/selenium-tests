package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;
import okhttp3.HttpUrl;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriverException;

public class UrlBuilder {


  public static final String DEFAULT_LANGUAGE = "en";
  protected String env;
  protected EnvType envType;
  private Boolean forceHttps;
  private Boolean forceLanguageInPath;
  protected static final String HTTP_PREFIX = "http://";

  public UrlBuilder() {
    this.env = Configuration.getEnv();
    this.envType = Configuration.getEnvType(this.env);
    this.forceHttps = Configuration.getForceHttps();
    this.forceLanguageInPath = Configuration.getForceLanguageInPath();
  }

  public UrlBuilder(String env, Boolean forceHttps) {
    this.env = env;
    this.envType = Configuration.getEnvType(this.env);
    this.forceHttps = forceHttps;
    this.forceLanguageInPath = Configuration.getForceLanguageInPath();
  }

  public UrlBuilder(String env, Boolean forceHttps, Boolean forceLanguageInPath) {
    this.env = env;
    this.envType = Configuration.getEnvType(this.env);
    this.forceHttps = forceHttps;
    this.forceLanguageInPath = forceLanguageInPath;
  }

  public String getLanguageForWiki() {
    return Configuration.getWikiLanguage() == null ? DEFAULT_LANGUAGE : Configuration.getWikiLanguage();
  }

  public String normalizePageName(String pageName) {
    return pageName.replace(" ", "_");
  }

  public String getUrlForPageWithWWW(String pageName) {
    return getUrlForWiki(Configuration.getWikiName(), true) + pageName;
  }

  public String getUrlForPage(String pageName) {
    return getUrlForWiki() + pageName;
  }

  public String getUrlForPage(String wikiName, String pageName) {
    return getUrlForWiki(wikiName) + pageName;
  }

  public String getUrlForPage(Page page) {
    if (page.getWikiPath() == null) {
      return getUrlForWiki(page.getWikiName(), page.getWikiLanguage(), false);
    }
    return getUrlForPath(page.getWikiName(), page.getWikiLanguage(), page.getWikiPath());
  }

  public String getUrlForPage(Page page, String qs) {
    return appendQueryStringToURL(page.getUrl(), qs);
  }

  public String getUrlForPath(String wikiName, String language, String wikiPath) {
    return addPathToUrl(getUrlForWiki(wikiName, language, false, envType), wikiPath);
  }

  public String getUrlForPath(String wikiName, String wikiPath) {
    return addPathToUrl(getUrlForWiki(wikiName), wikiPath);
  }

  public String getUrlForPath(String wikiPath) {
    return getUrlForPath(Configuration.getWikiName(), getLanguageForWiki(), wikiPath);
  }

  protected String addPathToUrl(String url, String path) {
    HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
    urlBuilder.addEncodedPathSegments(path.startsWith("/") ? path.replaceAll("^/", "") : path).build();

    String qs = Configuration.getQS();
    if (StringUtils.isNotBlank(qs)) {
      urlBuilder.encodedQuery(qs);
    }
    return urlBuilder.build().toString();
  }

  public String getUrlForWiki() {
    return getUrlForWiki(Configuration.getWikiName(), getLanguageForWiki(), false);
  }

  public String getUrlForWiki(String wikiName, String language, boolean addWWW) {
    return getUrlForWiki(wikiName, language, addWWW, envType);
  }

  public String getUrlForWiki(String wikiName) {
    return getUrlForWiki(wikiName, false);
  }

  public String getUrlForWiki(String wikiName, boolean addWWW) {
    return getUrlForWiki(wikiName, addWWW, envType);
  }


  public String getUrlForWiki(String wikiName, boolean addWWW, EnvType envType) {
    return getUrlForWiki(wikiName, getLanguageForWiki(), addWWW, envType);
  }

  public String getUrlForWiki(String wikiName, String language) {
    return getUrlForWiki(wikiName, language, false, envType);
  }

  public String getUrlForWiki(String wikiName, String language, boolean addWWW, EnvType envType) {
    final String wikiaName = getWikiaGlobalName(wikiName);

    if (language == null || wikiName == null)
      throw new NullPointerException("Wikia name and language are required");

    HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

    String www = addWWW ? "www." : "";
    String host = getFormattedWikiHost(www, wikiaName, envType, language);

    if (!("en").equals(language) && forceLanguageInPath) {
        urlBuilder.addEncodedPathSegments(language);
    }
    return urlBuilder.scheme(getUrlProtocol()).host(host).build().toString().replaceFirst("/$","");
  }

  private String getFormattedWikiHost(String www, String wikiaName, EnvType envType, String language) {


    if (!forceLanguageInPath && !("en").equals(language)) {
      return getFormattedWikiHost(www, String.join(".", language, wikiaName), envType);
    }
    return getFormattedWikiHost(www, wikiaName, envType);
  }

  private String getFormattedWikiHost(String www, String wikiaName, EnvType envType) {
    switch (envType) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        return String.join(".", www + wikiaName, devBoxOwner, envType.getWikiaDomain());
      }
      case PROD: {
        return String.join(".", www + wikiaName, envType.getWikiaDomain());
      }
      case STAGING: {
        return String.join(".", www + wikiaName, envType.getWikiaDomain());
      }
      case SANDBOX: {
        return String.join(".", www + wikiaName, this.env, envType.getWikiaDomain());
      }
      default:
        throw new WebDriverException("Unknown environment type");
    }
  }

  private String getFormattedHostForGlobalUrl(EnvType env) {
    switch (env) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        return String.join(".", "www", devBoxOwner, envType.getWikiaDomain());
      }
      case SANDBOX: {
        return String.join(".", "www", this.env, envType.getWikiaDomain());
      }
      default:
        return String.join(".", "www" , envType.getWikiaDomain());
    }
  }

  public String getUrlProtocol() {
    return this.forceHttps ? "https" : "http";
  }

  public String getWikiGlobalURL() {
    HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
    EnvType env = Configuration.getEnvType(this.env);

    return urlBuilder
            .scheme(getUrlProtocol())
            .host(getFormattedHostForGlobalUrl(env))
            .build()
            .toString().replaceFirst("/$","");
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
