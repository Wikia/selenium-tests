package com.wikia.webdriver.common.remote.discussions.context;

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

  /**
   * Builds CreatePostContext with unique title&description
   * @param siteId ID of wiki to create a post on
   * @param categoryId i.e. forumId
   * @return context to create a post
   */
  public static CreatePostContext categoryContext(final String siteId, final String categoryId) {
    Objects.requireNonNull(siteId);
    Objects.requireNonNull(categoryId);

    return CreatePostContext.builder()
      .siteId(siteId)
      .categoryId(categoryId)
      .title(TextGenerator.defaultText())
      .description(TextGenerator.createUniqueText())
      .build();
  }

  /**
   *
   * @param siteId is ID of wiki, but also "General" category ID in discussions on that Wiki by default
   * @return context with categoryId = siteId
   */
  public static CreatePostContext defaultContext(final String siteId) {
    return categoryContext(siteId, siteId);
  }

  public static CreatePostContext postContext(final String siteId, final String title,
    final String description) {
    return CreatePostContext.builder()
      .siteId(siteId)
      .categoryId(siteId)
      .title(title)
      .description(description)
      .build();
  }
}
