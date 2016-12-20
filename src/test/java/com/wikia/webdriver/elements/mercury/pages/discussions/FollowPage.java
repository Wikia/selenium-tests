package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.NoFollowedPostsMessage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;

public class FollowPage extends WikiBasePageObject {

  private static final String PATH = "/d/follow";

  @Getter(lazy = true)
  private final NoFollowedPostsMessage noFollowedPostsMessage = new NoFollowedPostsMessage();

  public FollowPage open() {
    getUrl(urlBuilder.getUrlForWiki() + PATH);
    return this;
  }
}
