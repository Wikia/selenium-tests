package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public class ReportedPostsAndRepliesPage extends WikiBasePageObject implements PageWithPosts {

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  @Getter(lazy = true)
  private final Post post = new Post();

  private static final String PATH = "/d/reported";

  public ReportedPostsAndRepliesPage open() {
    driver.get(urlBuilder.getUrlForWiki() + String.format(PATH));
    return this;
  }
}
