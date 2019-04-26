package com.wikia.webdriver.pageobjectsfactory.pageobject.notifications;

public enum NotificationType {

  POST_UPVOTE("%s deiner Diskussion %s"), POST_REPLY("%s auf %s"), REPLY_UPVOTE(
      "%s deiner Antwort");

  private final String format;

  NotificationType(String format) {
    this.format = format;
  }

  public String getFormat() {
    return format;
  }
}
