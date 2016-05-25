package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership Social Wikia
 */
public class Post extends WikiBasePageObject{

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;

  @FindBy(css = ".user-avatar")
  private WebElement avatarImage;

  @FindBy(css = ".avatar-username")
  private WebElement avatarUsername;

  @FindBy(css = ".icon.upvote")
  private List<WebElement> replyUpvoteButton;

  @FindBy(css = ".upvote-area")
  private List<WebElement> replyVoteCount;

  @FindBy(css = ".toggle-share")
  private List<WebElement> toggleShare;

  @FindBy(css = ".share-feature")
  private List<WebElement> shareFeature;

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
    ((JavascriptExecutor) driver)
        .executeScript("window.scrollTo(0, document.body.scrollHeight)");
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

  public void clickUpvoteButton(int postIndex) {
    WebElement button = replyUpvoteButton.get(postIndex);
    wait.forElementClickable(button);
    button.click();
  }

  public void waitForVoteCountToChange(int postIndex, String voteCount) {
    WebElement voteArea = replyVoteCount.get(postIndex);
    wait.forTextNotInElement(voteArea, voteCount);
  }

  /**
   * Wait for the noticeable time lag between vote and vote value change to pass
   */
  public void waitForVoteCountChangeTimeLagToPass() {
    try {
      //This sleep was introduced because of noticeable lag between vote and vote value change
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void clickShareIcon(int postIndex) {
    WebElement button = toggleShare.get(postIndex);
    wait.forElementClickable(button);
    button.click();
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
}
