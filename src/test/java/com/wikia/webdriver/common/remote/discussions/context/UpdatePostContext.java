package com.wikia.webdriver.common.remote.discussions.context;

import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdatePostContext extends RemoteContext {

  private String threadId;

  private final String firstPostId;

  private final String title;

  private final String description;

  @Builder
  private UpdatePostContext(String siteId, String threadId, String firstPostId, String title, String description) {
    super(siteId);
    this.threadId = threadId;
    this.firstPostId = firstPostId;
    this.title = title;
    this.description = description;
  }

  public static UpdatePostContext defaultContext(final String siteId, final PostEntity.Data data) {
    return UpdatePostContext.builder()
        .siteId(siteId)
        .threadId(data.getId())
        .firstPostId(data.getFirstPostId())
        .title(TextGenerator.defaultText())
        .description(TextGenerator.createUniqueText())
        .build();
  }
}
