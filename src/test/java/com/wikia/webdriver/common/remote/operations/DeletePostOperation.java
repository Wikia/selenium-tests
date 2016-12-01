package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.DeletePostContext;

public class DeletePostOperation {

  private static final String DELETE_POST_URL_SUFFIX = "%s/threads/%s/delete";

  private final PutRemoteOperation remoteOperation;

  public DeletePostOperation(User user) {
    this.remoteOperation = new PutRemoteOperation(user);
  }

  public void execute(final DeletePostContext context) {
    final String url = buildUrl(context);
    remoteOperation.execute(url);
  }

  private String buildUrl(final DeletePostContext context) {
    return Discussions.service(String.format(DELETE_POST_URL_SUFFIX, context.getSiteId(), context.getPostId()));
  }
}
