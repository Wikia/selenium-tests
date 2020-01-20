package com.wikia.webdriver.common.core.url;

import static com.wikia.webdriver.common.core.configuration.Configuration.*;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

import okhttp3.HttpUrl;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriverException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UrlBuilder extends BaseUrlBuilder {

  public static final String WIKI_NAME_FANDOM_SUFFIX = "fandom";
  private static final Set<String>
      FANDOM_EXCLUDED_WIKIS =
      new HashSet<>(Arrays.asList(new String[]{"community", "community-search", "gameofthrones", "sydneybuses",
                                               "elderscrolls", "gta", "harrypotter", "sktest123", "pokemon", "starwars",
                                               "dnd4", "muppet", "pad", "nonenwikiwithemptydiscussions",
                                               "nonenwikiwithoutdiscussions", "app-on-ucp2"}));

  private final String wikiName;
  private Boolean forceHttps;
  private Boolean forceWikiaOrg;
  private String language;

  private UrlBuilder(String wiki, String env, Boolean forceHttps, String language) {
    super(env);
    //Add fandom suffix when fandom domain is enabled in configuration to run tests on fandom wikis except languagepath tests
    if (FANDOM_EXCLUDED_WIKIS.contains(wiki)) {
      this.wikiName = wiki;
    } else {
      this.wikiName = Configuration.getForceFandomDomain() ? wiki + WIKI_NAME_FANDOM_SUFFIX : wiki;
    }
    this.forceHttps = forceHttps;
    this.language = language;
    this.forceWikiaOrg = Configuration.getForceWikiOrg();
  }

  public static UrlBuilder createUrlBuilder() {
    return createUrlBuilderForWikiAndLang(getWikiName(), getWikiLanguage());
  }

  public static UrlBuilder createUrlBuilderForWiki(String wiki) {
    return createUrlBuilderForWikiAndLang(wiki, getWikiLanguage());
  }

  public static UrlBuilder createUrlBuilderForWikiAndLang(String wiki, String language) {
    return new UrlBuilder(wiki, getEnv(), getForceHttps(), language);
  }

  public static String stripUrlFromEnvSpecificPartAndDowngrade(String url) {
    return url.replaceAll("\\.(?:preview|stable|verify|sandbox[^.]*)\\.", ".")
        .replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX);
  }

  public static String stripUrlFromEnvSpecificPartAndDowngradeForAPI(String url) {
    if (url.contains("api.php") || url.contains("wikia.php")) {
      return url.replaceAll("\\.(?:preview|stable|verify|sandbox[^.]*)\\.", ".")
          .replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX);
    }
    return url.replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX);
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

  /**
   * Provides env-agnostic url used for calls to php API through border proxy
   *
   * @return wiki url
   */
  public String getUrlForApiCalls() {
    return stripUrlFromEnvSpecificPartAndDowngrade(
        getUrl().replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX));
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
    String host = getFormattedWikiHost(www, wikiaName, envType);

    if (!DEFAULT_LANGUAGE.equals(language)) {
      urlBuilder = urlBuilder.addEncodedPathSegments(language);
    }
    return urlBuilder.scheme(getUrlProtocol()).host(host).build().toString().replaceFirst("/$", "");
  }

  private String getFormattedWikiHost(String www, String wikiaName, EnvType envType) {
    switch (envType) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        return String.join(".", www + wikiaName, devBoxOwner, getDomain(envType));
      }
      case PROD: {
        return String.join(".", www + wikiaName, getDomain(envType));
      }
      case SANDBOX: {
        return String.join(".", www + wikiaName, this.env, getDomain(envType));
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

  private String getDomain(EnvType env) {
    return envType.getDomain();
  }
}
