package com.wikia.webdriver.common.remote.discussions;

import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.JsonPath;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.context.UpdatePostContext;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;
import com.wikia.webdriver.common.remote.operations.json.JsonToPostEntityMapper;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import org.json.JSONObject;

public class UpdatePost {

  public static final String UPDATE_POST_URL_SUFFIX = "%s/threads/%s";

  private final PostRemoteOperation remoteOperation;

  UpdatePost(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  public PostEntity.Data execute(final UpdatePostContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
        .put("title", context.getTitle())
        .put("body", context.getDescription())
        .put("id", context.getFirstPostId())
        .put("threadId", context.getThreadId())
        .build());

    final String response = remoteOperation.execute(buildUrl(context), jsonObject);
    return new JsonToPostEntityMapper(JsonPath.parse(response)).toData();
  }

  private String buildUrl(final UpdatePostContext context) {
    return DiscussionsOperations
      .service(String.format(UPDATE_POST_URL_SUFFIX, context.getSiteId(), context.getThreadId()));
  }
}
