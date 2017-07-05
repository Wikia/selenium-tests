package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;

public class FandomUrlBuilder {

  private static final String FANDOM_URL = "fandom.wikia.com";
  private static final String ARTICLE_PATH = "articles";
  private static final String F2_ARTICLE_PATH = "f2/articles";
  private static final String F2_TOPIC_PATH = "topics";
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

  public String getF2Url(String pageName, String pageType) {
    String baseUrl = FANDOM_URL + "/" + F2_ARTICLE_PATH + "/" + pageName;

    if(AdsFandomTestTemplate.PAGE_TYPE_TOPIC.equals(pageType)) {
      baseUrl = FANDOM_URL + "/" + F2_TOPIC_PATH + "/" + pageName;
    }

    if(AdsFandomTestTemplate.PAGE_TYPE_HUB.equals(pageType)) {
      baseUrl = FANDOM_URL + "/" + F2_TOPIC_PATH + "/" + pageName + "-old";
    }

    if (!env.equals(ENV_PROD)) {
      baseUrl = env + "." + baseUrl;
    }

    return "http://" + baseUrl;
  }
}
