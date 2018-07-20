package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;

public class Page {

  private String wikiName;
  private String wikiPath;
  private String wikiLanguage;
  private UrlBuilder urlBuilder;

  public Page(String wikiName, String language, String wikiPath) {
    this.wikiName = wikiName;
    this.wikiLanguage = language;
    this.wikiPath = wikiPath;
    this.urlBuilder = UrlBuilder.createUrlBuilderForWikiAndLang(this.wikiName, this.wikiLanguage);
  }

  public Page(String wikiName, String wikiPath) {
    this.wikiName = wikiName;
    this.wikiLanguage = Configuration.getWikiLanguage();
    this.wikiPath = wikiPath;
    this.urlBuilder = UrlBuilder.createUrlBuilderForWikiAndLang(this.wikiName, this.wikiLanguage);
  }

  public Page(String wikiName) {
    this.wikiName = wikiName;
    this.wikiLanguage = Configuration.getWikiLanguage();
    this.urlBuilder = UrlBuilder.createUrlBuilderForWikiAndLang(this.wikiName, this.wikiLanguage);
  }

  public String getWikiName() {
    return wikiName;
  }

  public String getUrl() {
    if (this.wikiPath == null) {
      return urlBuilder.getUrl(false);
    } else {
      return urlBuilder.getUrlForWikiPage(this.wikiPath);
    }
  }

  public String getUrl(String queryParam) {
    return urlBuilder.appendQueryStringToURL(getUrl(), queryParam);
  }

  public String getUrl(String[] queryParams) {
    String url = getUrl();

    for (String param : queryParams) {
      url = urlBuilder.appendQueryStringToURL(url, param);
    }

    return url;
  }
}
