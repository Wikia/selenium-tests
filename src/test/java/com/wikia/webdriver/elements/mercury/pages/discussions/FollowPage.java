package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.NoFollowedPostsMessage;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import lombok.Getter;

public class FollowPage extends PageWithPosts {

  private static final String PATH = "/d/follow";

  @Getter(lazy = true)
  private final NoFollowedPostsMessage noFollowedPostsMessage = new NoFollowedPostsMessage();

  @Override
  public FollowPage open() {
    final FollowPage page = new FollowPage();
    page.getUrl(getUrlWithCacheBuster(page.urlBuilder.getUrlForWiki() + PATH));
    page.waitForEmberLoad();
    return page;
  }

  @Override
  public SignInToFollowModalDialog getSignInDialog() {
    throw new UnsupportedOperationException("FollowPage not reachable for unauthorized users");
  }
}
