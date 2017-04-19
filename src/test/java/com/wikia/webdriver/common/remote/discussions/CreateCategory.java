package com.wikia.webdriver.common.remote.discussions;

import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.RemoteException;
import com.wikia.webdriver.common.remote.discussions.context.CreateCategoryContext;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import org.json.JSONObject;

public class CreateCategory {

  private class CategoryNotCreated extends RuntimeException {
    private CategoryNotCreated(String message, RemoteException cause) {
      super(message, cause);
    }
  }

  private static final int PARENT_ID = 1;
  private static final String CREATE_CATEGORY_URL_SUFFIX = "%s/forums";
  private final PostRemoteOperation remoteOperation;

  CreateCategory(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  public CategoryPill.Data execute(final CreateCategoryContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
        .put("name", context.getCategoryName())
        .put("parentId", PARENT_ID)
        .put("siteId", context.getSiteId())
        .build());

    String response;
    try {
      response = remoteOperation.execute(buildUrl(context), jsonObject);
    } catch(RemoteException e) {
      PageObjectLogging.logError("error: ", e);
      throw new CategoryNotCreated("Could not create a new category.", e);
    }

    DocumentContext json = JsonPath.parse(response);

    CategoryPill.Data data = new CategoryPill.Data(json.read("$.id"), json.read("$.name"));
    data.setDisplayOrder(json.read("$.displayOrder"));
    return data;
  }

  private String buildUrl(final CreateCategoryContext context) {
    return Discussions.service(String.format(CREATE_CATEGORY_URL_SUFFIX, context.getSiteId()));
  }
}
