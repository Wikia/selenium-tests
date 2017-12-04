package com.webdriver.common.remote.discussions.context;

import com.webdriver.common.remote.context.RemoteContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CreateReplyContext extends RemoteContext {

  private final String threadId;
  private final String body;

  @Builder
  private CreateReplyContext(String siteId, String threadId, String body) {
    super(siteId);
    this.threadId = threadId;
    this.body = body;
  }

  public static CreateReplyContext defaultContext(final String siteId, final String threadId) {
    Objects.requireNonNull(siteId);
    Objects.requireNonNull(threadId);

    return CreateReplyContext.builder()
      .siteId(siteId)
      .threadId(threadId)
      .body(TextGenerator.defaultText())
      .build();
  }
}
