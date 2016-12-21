package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.ThreadContext;

public class LockPost {

  private static final String DELETE_POST_URL_SUFFIX = "%s/threads/%s/lock";

  private final PutRemoteOperation remoteOperation;

  public LockPost(User user) {
    this.remoteOperation = new PutRemoteOperation(user);
  }

  public void execute(final ThreadContext context) {
    final String url = buildUrl(context);
    remoteOperation.execute(url);
  }

  private String buildUrl(final ThreadContext context) {
    return Discussions.service(String.format(DELETE_POST_URL_SUFFIX, context.getSiteId(), context.getPostId()));
  }
}
