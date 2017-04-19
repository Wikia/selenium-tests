package com.wikia.webdriver.common.remote.discussions;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.context.CreatePostContext;
import com.wikia.webdriver.common.remote.discussions.context.ModeratePostContext;
import com.wikia.webdriver.common.remote.discussions.context.ThreadContext;
import com.wikia.webdriver.common.remote.discussions.context.UpdatePostContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor(staticName = "using")
public class DiscussionsOperations {

  private static final String DISCUSSIONS_SERVICE = "discussion/";
  private final User user;

  private final WebDriver driver;

  public static String service(String url) {
    return Utils.buildServicesUrl() + DISCUSSIONS_SERVICE + url;
  }

  /**
   * Callable only when on Special:Version
   */
  public PostEntity.Data createPostWithUniqueData() {
    return createPostWithUniqueData(extractSiteId());
  }

  /**
   * Callable from anywhere
   */
  public PostEntity.Data createPostWithUniqueData(String siteId) {
    return createPost(CreatePostContext.defaultContext(siteId));
  }

  /**
   * Callable only when on Special:Version
   */
  public PostEntity.Data createPostWithCategory(String categoryId) {
    return createPostWithCategory(categoryId, extractSiteId());
  }

  /**
   * Callable from anywhere
   */
  public PostEntity.Data createPostWithCategory(String categoryId, String siteId) {
    return createPost(CreatePostContext.categoryContext(siteId, categoryId));
  }

  public PostEntity.Data createCustomPost(String siteId, String title, String description) {
    return createPost(CreatePostContext.postContext(siteId, title, description));
  }

  private String extractSiteId() {
    return Utils.extractSiteIdFromMediaWikiUsing(driver);
  }

  public PostEntity.Data createPost(CreatePostContext context) {
    return new CreatePost(user).execute(context);
  }

  /**
   * Callable only when on Special:Version
   */
  public void deletePost(PostEntity.Data data) {
    deletePost(data, extractSiteId());
  }

  /**
   * Callable from anywhere
   */
  public void deletePost(PostEntity.Data data, String siteId) {
    deletePost(ThreadContext.defaultContextUsing(siteId, data));
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
