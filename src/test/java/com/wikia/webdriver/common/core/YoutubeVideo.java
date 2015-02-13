package com.wikia.webdriver.common.core;

/**
 * Created by Ludwik on 2015-02-12.
 */
public class YoutubeVideo implements Video {

  private String url;
  private String title;
  private String fileName;

  public YoutubeVideo(String title, String url) {
    this.url = url;
    this.title = title;
    this.fileName = transformTitleToFileName(title);
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public String getWikiFileName() {
    return this.fileName;
  }

  private String transformTitleToFileName(String title) {
    return title.replace(" ", "_");
  }
}
