package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

import static com.wikia.webdriver.common.core.configuration.EnvType.PROD;

public class FandomUrlBuilder extends UrlBuilder {

  private static final String FANDOM_HOSTNAME = "fandom.wikia.com";
  private static final String ARTICLE_PATH = "articles";
  private static final String TOPICS_PATH = "topics";

  private String browser;

  public FandomUrlBuilder() {
    super();
    this.browser = Configuration.getBrowser();
  }

  public String getFandomUrl() {
    return getFandomUrl(envType);
  }

  public String getFandomUrl(EnvType envType) {
    String hostname = FANDOM_HOSTNAME;
    if (!envType.equals(PROD)) {
      hostname = env + "." + hostname;
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
