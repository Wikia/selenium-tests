package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Replies;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Reply;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TopNoteModalDialog;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.ReplyCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.ReplyCreatorMobile;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;


public class PostDetailsPage extends WikiBasePageObject implements AvailablePage {

  @Getter(lazy = true)
  private final TopNoteModalDialog topNoteModalDialog = new TopNoteModalDialog();

  @Getter(lazy = true)
  private final Post post = new Post();

  @Getter(lazy = true)
  private final Reply reply = new Reply();

  @Getter(lazy = true)
  private final Replies replies = new Replies();

  @Getter(lazy = true)
  private final ReplyCreator replyCreatorDesktop = new ReplyCreatorDesktop();

  @Getter(lazy = true)
  private final ReplyCreator replyCreatorMobile = new ReplyCreatorMobile();

  @Getter(lazy = true)
  private final SignInToFollowModalDialog signInToFollowModalDialog = new SignInToFollowModalDialog();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  private static final String PATH = "/d/p/%s";

  // post with this ID does not exist on wiki discussions-empty
  private static final String EMPTY_POST_ID = "404";

  public PostDetailsPage open(String postId) {
    getUrl(urlBuilder.getUrlForWiki() + String.format(PATH, postId));
    waitForPageReload();
    return this;
  }

  public boolean isPostFollowed() {
    waitForPageLoad();
    return getPost().findNewestPost().isFollowed();
  }

  public PostDetailsPage openEmptyPost() {
    return open(EMPTY_POST_ID);
  }


  public boolean isDisplayed() {
    return getPost().isDisplayed();
  }

}
