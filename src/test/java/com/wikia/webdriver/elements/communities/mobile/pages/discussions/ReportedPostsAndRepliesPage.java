package com.wikia.webdriver.elements.communities.mobile.pages.discussions;

import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.SignInToFollowModalDialog;

import lombok.Getter;

public class ReportedPostsAndRepliesPage extends PageWithPosts {

  private static final String PATH = "/d/reported";
  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  @Override
  public ReportedPostsAndRepliesPage open() {
    driver.get(getUrlWithCacheBuster(urlBuilder.getUrl() + PATH));
    waitForEmberLoad();
    return this;
  }

  @Override
  public SignInToFollowModalDialog getSignInDialog() {
    throw new UnsupportedOperationException(
        "Reported posts page not reachable for unauthorized users");
  }
}
