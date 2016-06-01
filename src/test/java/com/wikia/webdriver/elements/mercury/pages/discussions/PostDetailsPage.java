package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Reply;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PostDetailsPage extends WikiBasePageObject{

  @Getter(lazy = true)
  private final Post post = new Post();

  @Getter(lazy = true)
  private final Reply reply = new Reply();


  private static final String PATH = "d/p/%s";
  private static final String DEFAULT_POST_ID = "2741364719297234368";

  public PostDetailsPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki().replace("/wiki", "") + String.format(PATH, wikiID));
    return this;
  }

  public PostDetailsPage open() {
    return open(DEFAULT_POST_ID);
  }

}
