package com.wikia.webdriver.common.core.url;

public class Page {
  private String wikiName;
  private String wikiPath;
  private UrlBuilder urlBuilder = new UrlBuilder();

  public Page(String wikiName, String wikiPath) {
    this.wikiName = wikiName;
    this.wikiPath = wikiPath;
  }

  public Page(String wikiName) {
    this.wikiName = wikiName;
  }

  public String getWikiName() {
    return wikiName;
  }

  public String getWikiPath() {
    return wikiPath;
  }

  public String getUrl() {
    return urlBuilder.getUrlForPath(this.wikiName, this.wikiPath);
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
