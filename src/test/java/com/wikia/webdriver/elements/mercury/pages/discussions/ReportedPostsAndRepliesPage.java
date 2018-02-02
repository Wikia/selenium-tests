package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import lombok.Getter;

public class ReportedPostsAndRepliesPage extends PageWithPosts {

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  private static final String PATH = "/d/reported";

  @Override
  public ReportedPostsAndRepliesPage open() {
    driver.get(urlBuilder.getUrlForWiki() + PATH);
    waitForEmberLoad();
    return this;
  }

  @Override
  public SignInToFollowModalDialog getSignInDialog() {
    throw new UnsupportedOperationException("Reported posts page not reachable for unauthorized users");
  }
}
