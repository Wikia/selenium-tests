package com.wikia.webdriver.common.remote.operations;

import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.CreateCategoryContext;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import org.json.JSONObject;

public class CreateCategory {

  private static final int PARENT_ID = 1;

  public static final String CREATE_CATEGORY_URL_SUFFIX = "%s/forums";

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

    final String response = remoteOperation.execute(buildUrl(context), jsonObject);

    DocumentContext json = JsonPath.parse(response);

    CategoryPill.Data data = new CategoryPill.Data(json.read("$.id"), 0, json.read("$.name"));
    data.setDisplayOrder(json.read("$.displayOrder"));
    return data;
  }

  private String buildUrl(final CreateCategoryContext context) {
    return Discussions.service(String.format(CREATE_CATEGORY_URL_SUFFIX, context.getSiteId()));
  }
}
