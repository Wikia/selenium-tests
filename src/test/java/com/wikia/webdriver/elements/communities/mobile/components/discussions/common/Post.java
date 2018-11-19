package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import javax.annotation.CheckForNull;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Post extends BasePage {

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;

  @FindBy(css = ".post-detail .wds-avatar")
  private WebElement avatarImage;

  @FindBy(css = ".user-avatar__username")
  private WebElement avatarUsername;

  @FindBy(css = "li.post-actions__upvote")
  private List<WebElement> postUpvoteButton;

  @FindBy(css = ".post-counters .upvote-count")
  private List<WebElement> postVoteCount;

  @FindBy(css = "li.post-actions__upvote")
  private WebElement upvoteArea;

  @FindBy(css = ".post-actions__upvote")
  private WebElement upvoteButton;

  @FindBy(css = ".load-more-button")
  private WebElement loadMoreButton;

  @FindBy(css = ".post-card__body .post-content")
  private WebElement postDetails;

  @FindBy(css = ".post-detail > .post-card__body > a")
  private WebElement postOpenGraph;

  @FindBy(css = ".post-detail .poll-wrapper")
  private List<WebElement> postsWithPollList;

  public boolean isPostListEmpty() {
    return postList.isEmpty();
  }

  public Post clickUserAvatar() {
    avatarImage.click();
    return this;
  }

  public Post clickUsernameLink() {
    avatarUsername.click();
    return this;
  }

  public String getUsername() {
    return avatarUsername.getText();
  }

  public Post scrollToLoadMoreButton() {
    scrollTo(loadMoreButton);
    return this;
  }

  public Post clickLoadMore() {
    scrollAndClick(loadMoreButton);
    return this;
  }

  public Post clickNthPostWithPoll(int number) {
    try {
      wait.forElementClickable(postsWithPollList.get(number));
      postsWithPollList.get(number).click();
    } catch (IndexOutOfBoundsException e) {
      Log.log("Could not find and click nth post with a poll", e, false);
    }

    return this;
  }

  public Poll getPoll() {
    return new Poll();
  }

  public int getPostsListLength() {
    return postList.size();
  }

  @CheckForNull
  public PostEntity findNewestPost() {
    return postList.isEmpty() ? null : new PostEntity(postList.get(0));
  }

  @CheckForNull
  public PostEntity findPostById(final String id) {
    return Iterables.tryFind(getPosts(), input -> input.findId().equals(id)).orNull();
  }

  public List<PostEntity> getReportedPosts() {
    return Lists.newArrayList(getPosts().stream()
                                  .filter(PostEntity::isReported)
                                  .collect(Collectors.toList()));
  }

  public List<PostEntity> getPosts() {
    return postList.stream().map(PostEntity::new).collect(toList());
  }

  public boolean isUpvoteButtonVisible(int index) {
    WebElement button = postUpvoteButton.get(index);
    wait.forElementVisible(button);
    return button.isDisplayed();
  }

  public String getVoteCount(int index) {
    WebElement voteCountArea = postVoteCount.get(index);
    wait.forElementVisible(voteCountArea);
    return voteCountArea.getText();
  }

  public Post clickUpvoteButton(int postIndex) {
    WebElement button = postUpvoteButton.get(postIndex);
    wait.forElementClickable(button);
    button.click();

    return this;
  }

  public Post waitForVoteCountToChange(int postIndex, String voteCount) {
    WebElement voteArea = postVoteCount.get(postIndex);
    wait.forTextNotInElement(voteArea, voteCount);

    return this;
  }

  /**
   * Wait for the noticeable time lag between vote and vote value change to pass
   */
  public Post waitForVoteCountChangeTimeLagToPass() {
    try {
      //This sleep was introduced because of noticeable lag between vote and vote value change
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Log.logError("waitForVoteCountChangeTimeLagToPass", e);
    }

    return this;
  }

  public Post waitForPostToAppearWithText(final String text) {
    wait.forTextInElement(By.cssSelector(".discussion.forum.forum-wrapper"), text);
    return this;
  }

  public Post waitForPostToAppearWithOpenGraph(final String url) {
    new FluentWait<>(driver).withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
        .until((Function<WikiaWebDriver, Boolean>) input -> wait.forElementVisible(postOpenGraph)
            .getAttribute("href")
            .contains(url));
    return this;
  }

  public String getPostDetailsVoteCount() {
    wait.forElementVisible(upvoteArea);
    return upvoteArea.getText();
  }

  public Post clickPostDetailsUpvoteButton() {
    wait.forElementClickable(upvoteButton);
    upvoteButton.click();

    return this;
  }

  public boolean isUpvoteButtonVisible() {
    wait.forElementVisible(upvoteButton);
    return upvoteButton.isDisplayed();
  }

  public String getPostDetailText() {
    return wait.forElementVisible(postDetails).getText();
  }

  public boolean isDisplayed() {
    return postDetails.isDisplayed();
  }

  public boolean firstPostHasImage() {
    setShortImplicitWait();
    boolean hasImage = findNewestPost().hasImage();
    restoreDefaultImplicitWait();
    return hasImage;
  }

  public boolean firstPostHasPoll() {
    setShortImplicitWait();
    boolean hasPoll = findNewestPost().hasPoll();
    restoreDefaultImplicitWait();
    return hasPoll;
  }

  public boolean firstPostHasOpenGraph() {
    setShortImplicitWait();
    boolean hasOpenGraph = findNewestPost().hasOpenGraph();
    restoreDefaultImplicitWait();
    return hasOpenGraph;
  }
}
