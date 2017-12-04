package com.webdriver.common.remote.discussions;


import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.JsonPath;
import com.webdriver.common.core.helpers.User;
import com.webdriver.common.remote.discussions.context.CreateReplyContext;
import com.webdriver.common.remote.operations.http.PostRemoteOperation;
import com.webdriver.common.remote.operations.json.JsonToReplyPostEntityMapper;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyEntityData;
import org.json.JSONObject;

public class CreateReply {

  public static final String CREATE_REPLY_URL_SUFFIX = "%s/posts";

  private final PostRemoteOperation remoteOperation;

  CreateReply(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  public ReplyEntityData execute(final CreateReplyContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
      .put("siteId", context.getSiteId())
      .put("body", context.getBody())
      .put("title", "")
      .put("threadId", context.getThreadId())
      .put("creatorId", remoteOperation.getUser().getUserId())
      .build());

    final String response = remoteOperation.execute(buildUrl(context), jsonObject);
    return new JsonToReplyPostEntityMapper(JsonPath.parse(response)).toData();
  }

  private String buildUrl(final CreateReplyContext context) {
    return DiscussionsClient
      .service(String.format(CREATE_REPLY_URL_SUFFIX, context.getSiteId()));
  }
}
