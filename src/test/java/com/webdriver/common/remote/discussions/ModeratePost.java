package com.webdriver.common.remote.discussions;

import com.google.common.collect.ImmutableMap;
import com.webdriver.common.core.helpers.User;
import com.webdriver.common.remote.discussions.context.ModeratePostContext;
import com.webdriver.common.remote.operations.http.PutRemoteOperation;
import org.json.JSONObject;

abstract class ModeratePost {

  private final PutRemoteOperation remoteOperation;

  ModeratePost(User user) {
    this.remoteOperation = new PutRemoteOperation(user);
  }

  public void execute(final ModeratePostContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
        .put("value", 1)
        .build());

    final String url = buildUrl(context);
    remoteOperation.execute(url, jsonObject);
  }

  protected abstract String buildUrl(final ModeratePostContext context);
}
