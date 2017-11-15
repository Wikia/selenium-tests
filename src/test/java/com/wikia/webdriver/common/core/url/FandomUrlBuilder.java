package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;

public class FandomUrlBuilder extends UrlBuilder {

  private static final String FANDOM_URL = "fandom.wikia.com";
  private static final String ARTICLE_PATH = "articles";
  public static final String ENV_PROD = "prod";

  private String browser;
  private String env;

  public FandomUrlBuilder() {
    super();
    this.env = Configuration.getEnv();
    this.browser = Configuration.getBrowser();
  }

  public String getUrlForFandomArticlePage(String pageTitle) {
    String baseUrl = FANDOM_URL + "/" + ARTICLE_PATH + "/" + pageTitle;
    if (!env.equals(ENV_PROD)) {
      baseUrl = env + "." + baseUrl;
    }
    return "http://" + baseUrl;
  }

  public String getUrlForFandomTopic(String topic) {
    String baseUrl = FANDOM_URL + "/topics/" + topic;
    if (!env.equals(ENV_PROD)) {
      baseUrl = env + "." + baseUrl;
    }
    return "http://" + baseUrl;
  }
}
