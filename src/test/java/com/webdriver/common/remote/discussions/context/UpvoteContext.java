package com.webdriver.common.remote.discussions.context;

import com.webdriver.common.remote.context.RemoteContext;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

public class UpvoteContext extends RemoteContext {

  @NonNull
  @Getter
  private final String postId;

  @Builder
  private UpvoteContext(String siteId, String postId) {
    super(siteId);
    this.postId = postId;
  }

  public static UpvoteContext defaultContext(final String siteId, final String postId) {
    return UpvoteContext.builder()
      .siteId(siteId)
      .postId(postId)
      .build();
  }
}
