package com.wikia.webdriver.common.remote.operations;

import com.google.common.collect.ImmutableMap;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.ReportPostContext;
import org.json.JSONObject;

public class ReportPost {

  private final static String REPORT_POST_URL_SUFFIX = "%s/posts/%s/report";

  private final PutRemoteOperation remoteOperation;

  public ReportPost(User user) {
    this.remoteOperation = new PutRemoteOperation(user);
  }

  public void execute(final ReportPostContext context) {
    JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
        .put("value", 1)
        .build());

    final String url = buildUrl(context);
    remoteOperation.execute(url, jsonObject);
  }

  private String buildUrl(final ReportPostContext context) {
    return Discussions.service(String.format(REPORT_POST_URL_SUFFIX, context.getSiteId(), context.getPostId()));
  }
}
