package com.wikia.webdriver.common.core.video;

import com.wikia.webdriver.common.core.url.UrlBuilder;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class YoutubeVideo implements Video {

  private static final ImmutableMap<String, String> TITLE_SPECIAL_CHARS_TO_REPLACE_WITH_SPACE =
      new ImmutableMap.Builder<String, String>().put("/", " ").put("#", " ").put(":", " ").build();

  private final String url;
  private final String title;
  private final String fileName;
  private final String videoID;

  public YoutubeVideo(String title, String url, String videoId) {
    this.url = url;
    this.title = capitaliseFirstWord(escapeSpecialCharactersAndReduceSpacesFromTitle(title));

    this.fileName = (new UrlBuilder()).normalizePageName(this.title);
    this.videoID = videoId;
  }

  private static String escapeSpecialCharactersAndReduceSpacesFromTitle(String title) {
    String titleAfterEscape = title;

    for (Map.Entry<String, String> entry : TITLE_SPECIAL_CHARS_TO_REPLACE_WITH_SPACE.entrySet()) {
      titleAfterEscape = titleAfterEscape.replace(entry.getKey(), entry.getValue());
    }
    titleAfterEscape =
        titleAfterEscape
            .replaceAll(
                "[^ %!\"$&'()*,\\-./0-9:;=?@A-Z\\\\^_`a-z~\\x80-\\xFF+]|%[0-9A-Fa-f]{2}|&[A-Za-z0-9\\x80-\\xff]+;|&#[0-9]+;|&#x[0-9A-Fa-f]+;/S",
                "");

    titleAfterEscape =
        titleAfterEscape.replaceAll("^\\s+", "").replaceAll("$\\s+", "").replaceAll("\\s+", " ");

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
  public String getID() {
    return this.videoID;
  }

  @Override
  public String getFileName() {
    return this.fileName;
  }

  private String capitaliseFirstWord(String title) {
    return StringUtils.capitalize(title.substring(0, 1)) + title.substring(1);
  }
}
