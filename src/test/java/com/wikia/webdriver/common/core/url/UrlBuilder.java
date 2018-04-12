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

  public UrlBuilder() {
    this.env = Configuration.getEnv();
    this.envType = Configuration.getEnvType(this.env);
    this.forceHttps = Configuration.getForceHttps();
  }

  public UrlBuilder(String env, Boolean forceHttps) {
    this.env = env;
    this.envType = Configuration.getEnvType(this.env);
    this.forceHttps = forceHttps;
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
    return getUrlForPath(Configuration.getWikiName(), Configuration.getLanguage(), wikiPath);
  }

  protected String addPathToUrl(String url, String path) {
    HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
    urlBuilder.addEncodedPathSegments(path).build();

    String qs = Configuration.getQS();
    if (StringUtils.isNotBlank(qs)) {
      urlBuilder.encodedQuery(qs);
    }
    return urlBuilder.build().toString();
  }

  public String getUrlForWiki() {
    return getUrlForWiki(Configuration.getWikiName(), Configuration.getLanguage(), false);
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
    return getUrlForWiki(wikiName, DEFAULT_LANGUAGE, addWWW, envType);
  }

  public String getUrlForWiki(String wikiName, String language) {
    return getUrlForWiki(wikiName, language, false, envType);
  }

  public String getUrlForWiki(String wikiName, String language, boolean addWWW, EnvType envType) {
    final String wikiaName = getWikiaGlobalName(wikiName);

    String www = "";
    if (addWWW) {
      www = "www.";
    }

    String host;
    HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
    switch (envType) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        host = String.join(".", www + wikiaName, devBoxOwner, envType.getWikiaDomain());
        break;
      }
      case PROD: {
        host = String.join(".", www + wikiaName, envType.getWikiaDomain());
        break;
      }
      case STAGING: {
        host = String.join(".", www + wikiaName, envType.getWikiaDomain());
        break;
      }
      case SANDBOX: {
        host = String.join(".", www + wikiaName, this.env, envType.getWikiaDomain());
        break;
      }
      default:
        throw new WebDriverException("Unknown environment type");
    }
    urlBuilder.scheme(getUrlProtocol()).host(host);

    if (language != null && !("en").equals(language)) {
      urlBuilder.addPathSegments(language);
    }
    return urlBuilder.build().toString();
  }

  public String getUrlProtocol() {
    return this.forceHttps ? "https" : "http";
  }

  public String getWikiGlobalURL() {
    String host;
    HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
    EnvType env = Configuration.getEnvType(this.env);

    switch (env) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        host = String.join(".", "www", devBoxOwner, envType.getWikiaDomain());
        break;
      }
      case SANDBOX: {
        host = String.join(".", "www", this.env, envType.getWikiaDomain());
        break;
      }
      default:
        host = String.join(".", "www" , envType.getWikiaDomain());
    }

    return urlBuilder
            .scheme(getUrlProtocol())
            .host(host)
            .build()
            .toString();
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
    HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
    return urlBuilder.encodedQuery(qs).build().toString();
  }

  public String globallyEnableGeoInstantGlobalOnPage(String pageUrl, String instantGlobal) {
    return this.appendQueryStringToURL(pageUrl, String.format("InstantGlobals.%s=[XX]", instantGlobal));
  }

  public String globallyDisableGeoInstantGlobalOnPage(String pageUrl, String instantGlobal) {
    return this.appendQueryStringToURL(pageUrl, String.format("InstantGlobals.%s=[ZZ]", instantGlobal));
  }
}
