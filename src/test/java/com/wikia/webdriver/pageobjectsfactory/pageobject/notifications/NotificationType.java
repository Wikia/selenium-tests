package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

public enum NotificationType {

  POST_UPVOTE("%s upvoted your discussion %s"),
  POST_REPLY("%s replied to %s"),
  REPLY_UPVOTE("%s upvoted your reply");

  private final String format;

  NotificationType(String key) {
    format = key;
  }
}
