package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.NoFollowedPostsMessage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;

public class FollowPage extends WikiBasePageObject {

  private static final String PATH = "/d/follow";

  @Getter(lazy = true)
  private final NoFollowedPostsMessage noFollowedPostsMessage = new NoFollowedPostsMessage();

  public static FollowPage open() {
    final FollowPage page = new FollowPage();
    page.getUrl(page.urlBuilder.getUrlForWiki() + PATH);
    return page;
  }
}
