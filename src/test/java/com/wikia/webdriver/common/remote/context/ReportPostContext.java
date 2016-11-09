package com.wikia.webdriver.common.remote.context;

import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ReportPostContext extends RemoteContext {

  private final String postId;

  @Builder
  public ReportPostContext(String siteId, String postId) {
    super(siteId);
    this.postId = postId;
  }

  public static ReportPostContext defaultContextUsing(PostEntity.Data data) {
    Objects.requireNonNull(data.getFirstPostId(), "First post id is required to report post. Post id is not used in this operation!");

    return ReportPostContext.builder()
        .siteId(Discussions.DAUTO_WIKIA_SITE_ID)
        // in order to report post (in front end) it has to be treated as reply (in front end, but post in back end)
        .postId(data.getFirstPostId())
        .build();
  }
}
