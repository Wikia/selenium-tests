package com.wikia.webdriver.common.core.video;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableMap;

/**
 * Created by Ludwik Kaźmierczak on 2015-02-12.
 */
public class YoutubeVideo implements Video {

  private static final ImmutableMap<String, String> TITLE_SPECIAL_CHARS_TO_REPLACE =
      new ImmutableMap.Builder<String, String>()
          .put("| ", "")
          .put("|", "")
          .put("{", " ")
          .put("}", " ")
          .put("[", " ")
          .put("]", " ")
          .put("/", " ")
          .put("_", " ")
          .put(":", "")
          .build();

  private String url;
  private String title;
  private String fileName;

  public YoutubeVideo(String title, String url) {
    this.url = url;
    this.title = capitaliseFirstWord(escapeSpecialCharactersFromTitle(title));

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

  private String capitaliseFirstWord(String sentence) {
    return StringUtils.capitalize(sentence.substring(0, 1)) + sentence.substring(1);
  }
}
