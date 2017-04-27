package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;

public class NotificationFactory {

  public Notification getPostReplyNotification(User user, PostEntity.Data post) {
    return Notification.builder()
      .actor(user.getUserName())
      .contentObject(post.getTitle())
      .type(NotificationType.POST_REPLY)
      .build();
  }
}
