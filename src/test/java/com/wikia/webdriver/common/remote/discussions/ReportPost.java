package com.wikia.webdriver.common.remote.discussions;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.context.ModeratePostContext;

public class ReportPost extends ModeratePost {

  private static final String REPORT_POST_URL_SUFFIX = "%s/posts/%s/report";

  public ReportPost(User user) {
    super(user);
  }
  protected String buildUrl(final ModeratePostContext context) {
    return DiscussionsClient
      .service(String.format(REPORT_POST_URL_SUFFIX, context.getSiteId(), context.getPostId()));
  }
}
