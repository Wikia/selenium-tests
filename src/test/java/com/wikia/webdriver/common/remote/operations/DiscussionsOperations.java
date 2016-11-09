package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.context.CreatePostContext;
import com.wikia.webdriver.common.remote.context.ReportPostContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "using")
public class DiscussionsOperations {

  private final User user;

  public PostEntity.Data cratePostWithUniqueData() {
    return createPost(CreatePostContext.defaultContext());
  }

  public PostEntity.Data createPost(CreatePostContext context) {
    return new CreatePost(user).execute(context);
  }

  public void reportPost(PostEntity.Data data) {
    reportPost(ReportPostContext.defaultContextUsing(data));
  }

  public void reportPost(ReportPostContext context) {
    new ReportPost(user).execute(context);
  }
}
