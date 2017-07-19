package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;

public class NotificationFactory {

  public static Notification getPostReplyNotification(User user, PostEntity.Data post) {
    return Notification.builder()
      .actor(user.getUserName())
      .contentObject(post.getTitle())
      .type(NotificationType.POST_REPLY)
      .build();
  }

  public static Notification getPostUpvoteNotification(User user, PostEntity.Data post) {
    return Notification.builder()
      .actor(user.getUserName())
      .contentObject(post.getTitle())
      .type(NotificationType.POST_UPVOTE)
      .build();
  }

  public static Notification getReplyUpvoteNotification(User user) {
    return Notification.builder()
      .actor(user.getUserName())
      .type(NotificationType.REPLY_UPVOTE)
      .build();
  }
}
