package com.wikia.webdriver.elements.communities.mobile.pages.discussions;

import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.*;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.desktop.ReplyCreatorDesktop;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.mobile.ReplyCreatorMobile;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostDetailsPage extends PageWithPosts {

  private static final String PATH = "/d/p/%s";
  // post with this ID does not exist on wiki discussions-empty
  private static final String EMPTY_POST_ID = "404";
  @Getter(lazy = true)
  private final TopNoteModalDialog topNoteModalDialog = new TopNoteModalDialog();
  @Getter(lazy = true)
  private final Reply reply = new Reply();
  @Getter(lazy = true)
  private final Replies replies = new Replies();
  @Getter(lazy = true)
  private final ReplyCreatorDesktop replyCreatorDesktop = new ReplyCreatorDesktop();
  @Getter(lazy = true)
  private final ReplyCreatorMobile replyCreatorMobile = new ReplyCreatorMobile();
  @Getter(lazy = true)
  private final SignInToFollowModalDialog signInDialog = new SignInToFollowModalDialog();
  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();
  @FindBy(css = ".discussion-tooltip")
  private WebElement followingTooltip;

  public PostDetailsPage open(String postId) {
    getUrl(getUrlWithCacheBuster(urlBuilder.getUrl() + String.format(PATH, postId)));
    waitForEmberLoad();
    return this;
  }

  public boolean isPostFollowed() {
    waitForEmberLoad();
    return getPost().findNewestPost().isFollowed();
  }

  public PostDetailsPage clickFollowingTooltip() {
    wait.forElementClickable(followingTooltip);
    followingTooltip.click();

    return this;
  }

  public PostDetailsPage openEmptyPost() {
    return open(EMPTY_POST_ID);
  }

  public boolean isDisplayed() {
    return getPost().isDisplayed();
  }

  public Reply findNewestReply() {
    return getReplies().getNewestReply();
  }

  @Override
  public PostDetailsPage open() {
    return openEmptyPost();
  }

  public Poll getPoll() { return new Poll(); }
}
