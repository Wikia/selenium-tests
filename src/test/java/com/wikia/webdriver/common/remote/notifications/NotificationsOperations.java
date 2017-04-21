package com.wikia.webdriver.common.remote.notifications;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "using")
public class NotificationsOperations {

  private final static String NOTIFICATIONS_SERVICE = "notifications/";
  private final User user;

  public static String service(String url) {
    return Utils.buildServicesUrl() + NOTIFICATIONS_SERVICE + url;
  }

  public void markAllAsRead() {
    new MarkAsRead(user).execute();
  }

}
