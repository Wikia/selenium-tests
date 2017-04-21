package com.wikia.webdriver.common.remote.notifications;

import com.wikia.webdriver.common.remote.Utils;

public class NotificationsOperations {

  private final static String NOTIFICATIONS_SERVICE = "notifications/";

  public static String service(String url) {
    return Utils.buildServicesUrl() + NOTIFICATIONS_SERVICE + url;
  }

}
