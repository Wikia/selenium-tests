package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.NoFollowedPostsMessage;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;

public class FollowPage extends WikiBasePageObject implements PageWithPosts {

  private static final String PATH = "/d/follow";

  @Getter(lazy = true)
  private final NoFollowedPostsMessage noFollowedPostsMessage = new NoFollowedPostsMessage();

  @Getter(lazy = true)
  private final Post post = new Post();

  public static FollowPage open() {
    final FollowPage page = new FollowPage();
    page.getUrl(page.urlBuilder.getUrlForWiki() + PATH);
    return page;
  }

  @Override
  public SignInToFollowModalDialog getSignInToFollowModalDialog() {
    throw new UnsupportedOperationException("FollowPage not reachable for unauthorized users");
  }
}
