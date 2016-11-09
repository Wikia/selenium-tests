package com.wikia.webdriver.common.remote.context;

import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class DeletePostContext extends RemoteContext {

  private final String postId;

  @Builder
  public DeletePostContext(String siteId, String postId) {
    super(siteId);
    this.postId = postId;
  }

  public static DeletePostContext defaultContextUsing(PostEntity.Data data) {
    Objects.requireNonNull(data.getId());

    return DeletePostContext.builder()
        .siteId(Discussions.DAUTO_WIKIA_SITE_ID)
        .postId(data.getId())
        .build();
  }
}
