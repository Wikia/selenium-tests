package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import java.util.List;


public class Post extends BasePageObject {

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;

  @FindBy(css = ".post-detail figure.avatar")
  private WebElement avatarImage;

  @FindBy(css = ".avatar-username")
  private WebElement avatarUsername;

  @FindBy(css = "li.upvote")
  private List<WebElement> postUpvoteButton;

  @FindBy(css = ".post-counters .upvote-count")
  private List<WebElement> postVoteCount;

  @FindBy(css = ".toggle-share-area")
  private List<WebElement> toggleShare;

  @FindBy(css = ".share-feature")
  private List<WebElement> shareFeature;

  @FindBy(css = "li.upvote-area")
  private WebElement upvoteArea;

  @FindBy(css = "svg.upvote")
  private WebElement upvoteButton;

  @FindBy(css = ".load-more-button")
  private WebElement loadMoreButton;

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

  public Post scrollToLoadMoreButton() {
    scrollTo(loadMoreButton);
    return this;
  }

  public Post clickLoadMore() {
    scrollAndClick(loadMoreButton);
    return this;
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
    return Iterables.tryFind(getPostEntities(), new Predicate<PostEntity>() {
      @Override
      public boolean apply(@Nullable PostEntity input) {
        return input.findId().equals(id);
      }
    }).orNull();
  }

  public List<PostEntity> getReportedPosts() {
    return Lists.newArrayList(Iterables.filter(getPostEntities(), new Predicate<PostEntity>() {
      @Override
      public boolean apply(@Nullable PostEntity postEntity) {
        return postEntity.isReported();
      }
    }));
  }

  private List<PostEntity> getPostEntities() {
    return Lists.transform(postList, new Function<WebElement, PostEntity>() {
      @Nullable
      @Override
      public PostEntity apply(@Nullable WebElement webElemnt) {
        return new PostEntity(webElemnt);
      }
    });
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
      PageObjectLogging.logError("waitForVoteCountChangeTimeLagToPass", e);
    }

    return this;
  }

  public Post waitForPostToAppearWith(final String description) {
    wait.forTextInElement(By.cssSelector(".discussion.forum.forum-wrapper"), description);
    return this;
  }

  public Post clickShareIcon(int postIndex) {
    WebElement button = toggleShare.get(postIndex);
    wait.forElementClickable(button);
    button.click();

    return this;
  }

  public String getPostDetailsVoteCount() {
    wait.forElementVisible(upvoteArea);
    return upvoteArea.getText();
  }

  public Post waitForPostDetailsVoteCountToChange(String voteCount) {
    wait.forTextNotInElement(upvoteArea, voteCount);

    return this;
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

  public String[] getSocialNetworkIconClasses(int postIndex) {
    List<WebElement> icons = shareFeature.get(postIndex).findElements(By.cssSelector("a.icon"));
    int numberOfIcons = icons.size();
    String[] classes = new String[numberOfIcons];
    for (int i = 0; i < numberOfIcons; i++) {
      WebElement icon = icons.get(i);
      wait.forElementVisible(icon);
      classes[i] = icon.getAttribute("class").split(" ")[0];
    }
    return classes;
  }

  public String[] getSocialNetworkIconsClasses() {
    return getSocialNetworkIconClasses(0);
  }
}
