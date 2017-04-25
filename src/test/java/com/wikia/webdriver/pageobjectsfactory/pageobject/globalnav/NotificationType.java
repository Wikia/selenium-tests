package com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav;

public enum NotificationType {

  POST_UPVOTE("discussion-post-upvote");

  private final String key;

  NotificationType(String key) {
    this.key = key;
  }
}
