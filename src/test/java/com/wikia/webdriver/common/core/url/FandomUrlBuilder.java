package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;

import org.apache.commons.lang.StringUtils;

public class FandomUrlBuilder {

  final static String FANDOM_URL = "fandom.wikia.com";
  final static String ARTICLE_PATH = "articles";
  public final static String ENV_PROD = "prod";
  public final static String ENV_ADENG = "adeng";
  public final static String ENV_QA = "qa";

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
}
