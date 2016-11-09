package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.context.CreatePostContext;
import com.wikia.webdriver.common.remote.context.DeletePostContext;
import com.wikia.webdriver.common.remote.context.ModeratePostContext;
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

  public void deletePost(PostEntity.Data data) {
    deletePost(DeletePostContext.defaultContextUsing(data));
  }

  public void deletePost(DeletePostContext context) {
    new DeletePostOperation(user).execute(context);
  }

  public void reportPost(PostEntity.Data data) {
    reportPost(ModeratePostContext.defaultContextUsing(data));
  }

  public void reportPost(ModeratePostContext context) {
    new ReportPost(user).execute(context);
  }

  public void validatePost(PostEntity.Data data) {
    validatePost(ModeratePostContext.defaultContextUsing(data));
  }

  public void validatePost(ModeratePostContext context) {
    new ValidatePost(user).execute(context);
  }
}
