package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.ThreadContext;

public class UnlockPost {

  private final DeleteRemoteOperation remoteOperation;

  public UnlockPost(User user) {
    this.remoteOperation = new DeleteRemoteOperation(user);
  }

  public void execute(final ThreadContext context) {
    final String url = buildUrl(context);
    remoteOperation.execute(url);
  }

  private String buildUrl(final ThreadContext context) {
    return Discussions.service(String.format(LockPost.LOCK_POST_URL_SUFFIX, context.getSiteId(), context.getPostId()));
  }
}
