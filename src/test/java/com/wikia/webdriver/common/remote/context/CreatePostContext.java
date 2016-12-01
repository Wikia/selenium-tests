package com.wikia.webdriver.common.remote.context;

import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CreatePostContext extends RemoteContext {

  private final String categoryId;

  private final String title;

  private final String description;

  @Builder
  private CreatePostContext(String siteId, String categoryId, String title, String description) {
    super(siteId);
    this.categoryId = categoryId;
    this.title = title;
    this.description = description;
  }

  public static CreatePostContext defaultContext(final String siteId) {
    Objects.requireNonNull(siteId);

    return CreatePostContext.builder()
        .siteId(siteId)
        .categoryId(siteId)
        .title(TextGenerator.defaultText())
        .description(TextGenerator.createUniqueText())
        .build();
  }
}
