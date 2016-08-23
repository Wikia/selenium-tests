package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;

public class FandomUrlBuilder {

  private static final String FANDOM_URL = "fandom.wikia.com";
  private static final String ARTICLE_PATH = "articles";
  public static final String ENV_PROD = "prod";
  public static final String ENV_ADENG = "adeng";
  public static final String ENV_QA = "qa";

  private String browser;
  private String env;

  public FandomUrlBuilder() {
    this.env = Configuration.getEnv();
    this.browser = Configuration.getBrowser();
  }

  public FandomUrlBuilder(String env) {
    this.env = env;
  }

  public FandomUrlBuilder(String env, String browser) {
    this.env = env;
    this.browser = browser;
  }

  public String getUrlForFandomPage(String pageTitle) {
    String baseUrl = FANDOM_URL + "/" + ARTICLE_PATH + "/" + pageTitle;
    if (!env.equals(ENV_PROD)) {
      baseUrl = env + "." + baseUrl;
    }
    return "http://" + baseUrl;
  }

  public String getUrlForFandomHub(String hub) {
    String baseUrl = FANDOM_URL + "/" + hub;
    if (!env.equals(ENV_PROD)) {
      baseUrl = env + "." + baseUrl;
    }
    return "http://" + baseUrl;
  }
}
