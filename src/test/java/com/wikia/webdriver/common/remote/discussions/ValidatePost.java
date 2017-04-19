package com.wikia.webdriver.common.remote.discussions;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.context.ModeratePostContext;

public class ValidatePost extends ModeratePost {

  private static final String REPORT_POST_URL_SUFFIX = "%s/posts/%s/report/valid";

  public ValidatePost(User user) {
    super(user);
  }

  protected String buildUrl(final ModeratePostContext context) {
    return DiscussionsOperations
      .service(String.format(REPORT_POST_URL_SUFFIX, context.getSiteId(), context.getPostId()));
  }
}
