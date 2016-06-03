package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;


public class Post extends BasePageObject {

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;

  @FindBy(css = ".user-avatar")
  private WebElement avatarImage;

  @FindBy(css = ".avatar-username")
  private WebElement avatarUsername;

  @FindBy(css = ".icon.upvote")
  private List<WebElement> replyUpvoteButton;

  @FindBy(css = ".post-counters svg.upvote")
  private List<WebElement> replyVoteCount;

  @FindBy(css = ".toggle-share")
  private List<WebElement> toggleShare;

  @FindBy(css = ".share-feature")
  private List<WebElement> shareFeature;

  @FindBy(css = "li.upvote-area")
  private WebElement upvoteArea;

  @FindBy(css = "svg.upvote")
  private WebElement upvoteButton;

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

  public Post scrollToBottom(WebDriver driver) {
    jsActions.scrollToBottom(driver);
    return this;
  }

  public int getPostsListLength() {
    return postList.size();
  }

  public boolean isUpvoteButtonVisible(int index) {
    WebElement button = replyUpvoteButton.get(index);
    wait.forElementVisible(button);
    return button.isDisplayed();
  }

  public String getVoteCount(int index) {
    WebElement voteCountArea = replyVoteCount.get(index);
    wait.forElementVisible(voteCountArea);
    return voteCountArea.getText();
  }

  public Post clickUpvoteButton(int postIndex) {
    WebElement button = replyUpvoteButton.get(postIndex);
    wait.forElementClickable(button);
    button.click();

    return this;
  }

  public Post waitForVoteCountToChange(int postIndex, String voteCount) {
    WebElement voteArea = replyVoteCount.get(postIndex);
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
      e.printStackTrace();
    }

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
