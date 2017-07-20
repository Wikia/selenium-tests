package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;

public final class NotificationFactory {

  private NotificationFactory() {
    // no-op
  }

  public static Notification getPostReplyNotification(User user, PostEntity.Data post) {
    return getPostReplyNotfication(user.getUserName(), post.getTitle());
  }

  public static Notification getPostReplyConsolidatedNotification(User lastActor, int remainingActors, PostEntity.Data post) {
    String actor = String.format("%s and %d other users", lastActor.getUserName(), remainingActors);
    return getPostReplyNotfication(actor, post.getTitle());
  }

  private static Notification getPostReplyNotfication(String user, String postContent) {
    return Notification.builder()
      .actor(user)
      .contentObject(postContent)
      .type(NotificationType.POST_REPLY)
      .build();
  }

  public static Notification getPostUpvoteNotification(PostEntity.Data post) {
    return Notification.builder()
      .actor("1 user")
      .contentObject(post.getTitle())
      .type(NotificationType.POST_UPVOTE)
      .build();
  }

  public static Notification getReplyUpvoteNotification() {
    return Notification.builder()
      .actor("1 user")
      .type(NotificationType.REPLY_UPVOTE)
      .build();
  }


}
