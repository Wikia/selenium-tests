package com.wikia.webdriver.common.remote.discussions;


import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.JsonPath;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.context.UpvoteContext;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;
import com.wikia.webdriver.common.remote.operations.json.JsonToPostEntityMapper;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import org.json.JSONObject;

public class UpvotePost {
  public static final String UPVOTE_POST_SUFFIX = "%s/votes/post/%s";

  private final PostRemoteOperation remoteOperation;

  UpvotePost(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  public void execute(final UpvoteContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
      .put("siteId", context.getSiteId())
      .put("postId", context.getPostId())
      .build());

    remoteOperation.execute(buildUrl(context), jsonObject);
  }

  private String buildUrl(final UpvoteContext context) {
    return DiscussionsOperations
      .service(String.format(UPVOTE_POST_SUFFIX, context.getSiteId(), context.getPostId()));
  }
}
