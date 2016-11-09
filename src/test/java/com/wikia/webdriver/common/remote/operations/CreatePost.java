package com.wikia.webdriver.common.remote.operations;

import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.CreatePostContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import org.json.JSONObject;

public class CreatePost {

  public static final String CREATE_POST_URL_SUFFIX = "%s/forums/%s/threads";

  private final PostRemoteOperation remoteOperation;

  CreatePost(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  public PostEntity.Data execute(final CreatePostContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
        .put("siteId", context.getSiteId())
        .put("title", context.getTitle())
        .put("body", context.getDescription())
        .put("creatorId", remoteOperation.getUser().getUserId())
        .build());

    final String response = remoteOperation.execute(buildUrl(context), jsonObject);

    DocumentContext json = JsonPath.parse(response);

    return PostEntity.Data.builder()
        .id(json.read("$.id"))
        .category("$.forumName")
        .title(json.read("$.title"))
        .description(json.read("$.rawContent"))
        .authorId(json.read("$.createdBy.id"))
        .firstPostId(json.read("$.firstPostId"))
        .build();
  }

  private String buildUrl(final CreatePostContext context) {
    return Discussions.service(String.format(CREATE_POST_URL_SUFFIX, context.getSiteId(), context.getCategoryId()));
  }
}
