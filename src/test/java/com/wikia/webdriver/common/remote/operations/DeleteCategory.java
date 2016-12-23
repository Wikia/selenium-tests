package com.wikia.webdriver.common.remote.operations;


import com.google.common.collect.ImmutableMap;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.CategoryContext;
import com.wikia.webdriver.common.remote.operations.http.DeleteRemoteOperation;
import org.json.JSONObject;

public class DeleteCategory {

  private static final String DELETE_CATEGORY_URL_SUFFIX = "%s/forums/%s";

  private final DeleteRemoteOperation remoteOperation;

  DeleteCategory(User user) {
    this.remoteOperation = new DeleteRemoteOperation(user);
  }

  public void execute(final CategoryContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
        .put("moveChildrenTo", context.getSiteId())
        .build());

    remoteOperation.execute(buildUrl(context), jsonObject);
  }

  private String buildUrl(final CategoryContext context) {
    return Discussions.service(String.format(DELETE_CATEGORY_URL_SUFFIX, context.getSiteId(), context.getCategoryId()));
  }
}
