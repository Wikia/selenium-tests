package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public class UserPostsPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  private static final String PATH = "d/u/%s";
  private static final String NON_EXISTING_USER_ID = "4809883";

  public UserPostsPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki().replace("/wiki", "") + String.format(PATH, wikiID));
    return this;
  }

  public UserPostsPage open() {
    return open(NON_EXISTING_USER_ID);
  }
}
