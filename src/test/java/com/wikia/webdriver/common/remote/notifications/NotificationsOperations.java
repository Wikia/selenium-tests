package com.wikia.webdriver.common.remote.notifications;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "using")
public class NotificationsOperations {

  private static final String NOTIFICATIONS_SERVICE = "notifications/";
  private final User user;

  public static String service(String url) {
    return Configuration.getServicesUrl() + NOTIFICATIONS_SERVICE + url;
  }

  public void markAllAsRead() {
    new MarkAsRead(user).execute();
  }
}
