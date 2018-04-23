package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.core.configuration.Configuration;

public class Page {
  private String wikiName;
  private String wikiPath;
  private String wikiLanguage;
  private UrlBuilder urlBuilder = new UrlBuilder();

  public Page(String wikiName, String language, String wikiPath) {
    this.wikiName = wikiName;
    this.wikiLanguage = language;
    this.wikiPath = wikiPath;
  }

  public Page(String wikiName, String wikiPath) {
    this.wikiName = wikiName;
    this.wikiLanguage = Configuration.DEFAULT_LANGUAGE;
    this.wikiPath = wikiPath;
  }

  public Page(String wikiName) {
    this.wikiName = wikiName;
    this.wikiLanguage = Configuration.DEFAULT_LANGUAGE;
  }

  public String getWikiName() {
    return wikiName;
  }

  public String getWikiLanguage() {
    return wikiLanguage;
  }

  public String getWikiPath() {
    return wikiPath;
  }

  public String getUrl() {
    if (this.wikiPath == null){
      return urlBuilder.getUrlForWiki(this.wikiName, this.wikiLanguage, false);
    } else {
      return urlBuilder.getUrlForPath(this.wikiName, this.wikiLanguage, this.wikiPath);
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
