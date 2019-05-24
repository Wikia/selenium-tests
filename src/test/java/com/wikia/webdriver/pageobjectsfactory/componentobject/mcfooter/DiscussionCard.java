package com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter;

import com.wikia.webdriver.elements.communities.mobile.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiscussionCard extends WikiBasePageObject {

  @FindBy(css = ".mcf-card-discussions")
  private WebElement discussionsCard;

  @FindBy(css = ".mcf-card-discussions__link")
  private WebElement viewAllLink;

  @FindBy(css = ".mcf-card-discussions__user-subtitle")
  private WebElement avatarUsername;

  @FindBy(css = ".mcf-card-discussions__user-info .wds-avatar")
  private WebElement avatarImage;

  @FindBy(css = ".mcf-card-discussions__content")
  private WebElement discussionsPostTitle;

  @FindBy(css = ".mcf-card-discussions__zero-state-text")
  private WebElement discussionsZeroState;

  @FindBy(css = ".mcf-card-discussions__item")
  private WebElement discussionsPost;

  public boolean isDiscussionsCardPresent() {
    wait.forElementVisible(discussionsCard);

    return discussionsCard.isDisplayed();
  }

  public boolean isDiscussionsCardNotPresent() {
    wait.forElementNotVisible(discussionsCard);

    return true;
  }

  public DiscussionsPage clickOnViewAllLinkInDiscussions() {
    scrollTo(viewAllLink);
    wait.forElementClickable(viewAllLink).click();

    return new DiscussionsPage();
  }

  public String getUsername() {
    return avatarUsername.getText();
  }

  public UserProfilePage clickUserAvatar() {
    scrollTo(avatarImage);
    wait.forElementClickable(avatarImage);
    avatarImage.click();

    return new UserProfilePage();
  }

  public PostDetailsPage clickDiscussionsPost() {
    scrollTo(discussionsPost);
    wait.forElementClickable(discussionsPostTitle);
    discussionsPostTitle.click();

    return new PostDetailsPage();
  }

  public boolean isZeroState() {
    wait.forElementVisible(discussionsZeroState);

    return discussionsZeroState.isDisplayed();
  }

  public DiscussionCard scrollToDiscussions() {
    wait.forElementClickable(discussionsCard);
    jsActions.scrollToElement(discussionsCard);

    return this;
  }
}
