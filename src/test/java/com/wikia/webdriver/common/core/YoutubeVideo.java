package com.wikia.webdriver.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ludwik on 2015-02-12.
 */
public class YoutubeVideo implements Video {

  static final Map<String, String> TITLE_SPECIAL_CHARS_TO_REPLACE = new HashMap<String, String>() {
    {
      put("| ", "");
      put("|", "");
      put("{", " ");
      put("}", " ");
      put("[", " ");
      put("]", " ");
      put("/", " ");
    }
  };
  private String url;
  private String title;
  private String fileName;

  public YoutubeVideo(String title, String url) {
    this.url = url;
    this.title = escapeSpecialCharactersFromTitle(title);

    this.fileName = transformTitleToFileName(this.title);
  }

  private static final String escapeSpecialCharactersFromTitle(String title) {
    String titleAfterEscape = title;
    for (Map.Entry<String, String> entry : TITLE_SPECIAL_CHARS_TO_REPLACE.entrySet()) {
      titleAfterEscape = titleAfterEscape.replace(entry.getKey(), entry.getValue());
    }

    return titleAfterEscape;
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
