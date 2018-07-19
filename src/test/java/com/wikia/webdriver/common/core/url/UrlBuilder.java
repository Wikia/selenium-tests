package com.wikia.webdriver.common.core.url;

import static com.wikia.webdriver.common.core.configuration.Configuration.*;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

import okhttp3.HttpUrl;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriverException;

public class UrlBuilder extends BaseUrlBuilder {

  private final String wikiName;
  private Boolean forceHttps;
  private Boolean forceLanguageInPath;
  private String language;

  private UrlBuilder(
      String wiki, String env, Boolean forceHttps, Boolean forceLanguageInPath, String language
  ) {
    super(env);
    this.wikiName = wiki;
    this.forceHttps = forceHttps;
    this.forceLanguageInPath = forceLanguageInPath;
    this.language = language;
  }

  public static UrlBuilder createUrlBuilder() {
    return createUrlBuilderForWikiAndLang(getWikiName(), getWikiLanguage());
  }

  public static UrlBuilder createUrlBuilderForWiki(String wiki) {
    return createUrlBuilderForWikiAndLang(wiki, getWikiLanguage());
  }

  public static UrlBuilder createUrlBuilderForWikiAndLang(String wiki, String language) {
    return new UrlBuilder(wiki, getEnv(), getForceHttps(), getForceLanguageInPath(), language);
  }

  public String normalizePageName(String pageName) {
    if ("".equals(pageName)) {
      throw new WebDriverException("Page name is missing");
    }
    return pageName.replace(" ", "_");
  }

  public String getUrlForWikiPageWithWWW(String pageName) {
    if ("".equals(pageName)) {
      throw new WebDriverException("Page name is missing");
    }
    return getUrl(true) + URLsContent.WIKI_DIR + pageName;
  }

  public String getUrlForWikiPage(String pageName) {
    if ("".equals(pageName)) {
      throw new WebDriverException("Page name is missing");
    }

    String url = getUrl() + URLsContent.WIKI_DIR + pageName;

    if (StringUtils.isNotBlank(Configuration.getQS())) {
      return appendQueryStringToURL(url, Configuration.getQS());
    }
    return url;
  }

  public String getUrlForPath(String wikiPath) {
    return addPathToUrl(getUrl(), wikiPath);
  }

  public String getUrl() {
    return getUrl(this.language, false);
  }

  public String getUrl(String language, boolean addWWW) {
    return getUrl(language, addWWW, envType);
  }

  public String getUrl(boolean addWWW) {
    return getUrl(language, addWWW, envType);
  }

  public String getUrl(String language, boolean addWWW, EnvType envType) {
    final String wikiaName = getWikiaGlobalName(wikiName);

    if (language == null || wikiName == null) {
      throw new NullPointerException("Wikia name and language are required");
    }

    HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

    String www = addWWW ? "www." : "";
    String host = getFormattedWikiHost(www, wikiaName, envType, language);

    if (!DEFAULT_LANGUAGE.equals(language) && forceLanguageInPath) {
      urlBuilder.addEncodedPathSegments(language);
    }
    return urlBuilder.scheme(getUrlProtocol()).host(host).build().toString().replaceFirst("/$", "");
  }

  private String getFormattedWikiHost(
      String www, String wikiaName, EnvType envType, String language
  ) {

    if (!forceLanguageInPath && !(DEFAULT_LANGUAGE).equals(language)) {
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
        return String.join(".", "www", envType.getWikiaDomain());
    }
  }

  public String getUrlProtocol() {
    return this.forceHttps ? "https" : "http";
  }

  public String getWikiGlobalURL() {
    HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
    EnvType env = getEnvType(this.env);

    return urlBuilder.scheme(getUrlProtocol())
        .host(getFormattedHostForGlobalUrl(env))
        .build()
        .toString()
        .replaceFirst("/$", "");
  }

  private String getWikiaGlobalName(String wikiName) {
    if (wikiName.endsWith(".wikia")) {
      if (getEnvType(this.env) == EnvType.DEV) {
        return "wikiaglobal";
      } else {
        return wikiName.replace(".wikia", "");
      }
    } else {
      return wikiName;
    }
  }
}
