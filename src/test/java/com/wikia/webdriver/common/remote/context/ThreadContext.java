package com.wikia.webdriver.common.remote.context;

import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ThreadContext extends RemoteContext {

  private final String postId;

  @Builder
  public ThreadContext(String siteId, String postId) {
    super(siteId);
    this.postId = postId;
  }

  public static ThreadContext defaultContextUsing(final String siteId, final PostEntity.Data data) {
    Objects.requireNonNull(data.getId());

    return ThreadContext.builder()
        .siteId(siteId)
        .postId(data.getId())
        .build();
  }
}
