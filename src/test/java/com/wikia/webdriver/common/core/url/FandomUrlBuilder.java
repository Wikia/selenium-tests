package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

public class FandomUrlBuilder extends UrlBuilder {

  private static final String FANDOM_HOSTNAME = "fandom.wikia.com";
  private static final String ARTICLE_PATH = "articles";
  private static final String TOPICS_PATH = "topics";
  public static final String ENV_PROD = "prod";
  public final EnvType envType;

  private String browser;

  public FandomUrlBuilder() {
    super();
    this.browser = Configuration.getBrowser();
    envType = Configuration.getEnvType(this.env); //Why not to pass EnvType in UrlBuilder constructor
  }

  public String getFandomUrl() {
    return getFandomUrl(envType);
  }

  public String getFandomUrl(EnvType env) {
    String hostname = FANDOM_HOSTNAME;
    if (!env.equals(EnvType.PROD)) {
      hostname = env.getKey() + "." + hostname;
    }

    return HTTP_PREFIX + hostname + "/";
  }

  public String getFandomPageUrl(String path) {
    return addPathToUrl(getFandomUrl(), path);
  }

  public String getUrlForFandomArticlePage(String pageTitle) {
    return getFandomUrl() + ARTICLE_PATH + "/" + pageTitle;
  }

  public String getUrlForFandomTopic(String topic) {
    return getFandomUrl() + TOPICS_PATH + "/" + topic;
  }
}
