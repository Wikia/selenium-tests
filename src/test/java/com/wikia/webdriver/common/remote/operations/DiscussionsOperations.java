package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.CreatePostContext;
import com.wikia.webdriver.common.remote.context.ModeratePostContext;
import com.wikia.webdriver.common.remote.context.ThreadContext;
import com.wikia.webdriver.common.remote.context.UpdatePostContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor(staticName = "using")
public class DiscussionsOperations {

  private final User user;

  private final WebDriver driver;

  public PostEntity.Data createPostWithUniqueData() {
    return createPostWithUniqueData(extractSiteId());
  }

  public PostEntity.Data createPostWithUniqueData(String siteId) {
    return createPost(CreatePostContext.defaultContext(siteId));
  }

  private String extractSiteId() {
    return Discussions.extractSiteIdFromMediaWikiUsing(driver);
  }

  public PostEntity.Data createPost(CreatePostContext context) {
    return new CreatePost(user).execute(context);
  }

  public void deletePost(PostEntity.Data data) {
    deletePost(ThreadContext.defaultContextUsing(extractSiteId(), data));
  }

  public void deletePost(ThreadContext context) {
    new DeletePost(user).execute(context);
  }

  public DiscussionsOperations lockPost(PostEntity.Data data) {
    return lockPost(ThreadContext.defaultContextUsing(extractSiteId(), data));
  }

  public DiscussionsOperations lockPost(ThreadContext context) {
    new LockPost(user).execute(context);
    return this;
  }

  public void reportPost(PostEntity.Data data) {
    reportPost(ModeratePostContext.defaultContextUsing(extractSiteId(), data));
  }

  public void reportPost(ModeratePostContext context) {
    new ReportPost(user).execute(context);
  }

  public void unlockPost(PostEntity.Data data) {
    unlockPost(ThreadContext.defaultContextUsing(extractSiteId(), data));
  }

  public void unlockPost(ThreadContext context) {
    new UnlockPost(user).execute(context);
  }

  public PostEntity.Data updatePost(PostEntity.Data data) {
    return updatePost(UpdatePostContext.defaultContext(extractSiteId(), data));
  }

  public PostEntity.Data updatePost(UpdatePostContext context) {
    return new UpdatePost(user).execute(context);
  }

  public void validatePost(PostEntity.Data data) {
    validatePost(ModeratePostContext.defaultContextUsing(extractSiteId(), data));
  }

  public void validatePost(ModeratePostContext context) {
    new ValidatePost(user).execute(context);
  }
}
