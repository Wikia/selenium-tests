package com.wikia.webdriver.common.remote.discussions.context;

import com.wikia.webdriver.common.remote.context.RemoteContext;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.TextGenerator;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdatePostContext extends RemoteContext {

  private final String firstPostId;
  private final String title;
  private final String description;
  private String threadId;

  @Builder
  private UpdatePostContext(
      String siteId, String threadId, String firstPostId, String title, String description
  ) {
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
