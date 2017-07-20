package com.wikia.webdriver.common.remote.notifications;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;
import org.json.JSONObject;

class MarkAsRead {

  private static final String MARK_AS_READ_SUFFIX = "mark-all-as-read";
  private final PostRemoteOperation remoteOperation;

  MarkAsRead(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  protected void execute() {
    remoteOperation.execute(buildUrl(), new JSONObject());
  }


  private String buildUrl() {
    return NotificationsOperations.service(MARK_AS_READ_SUFFIX);
  }


}
