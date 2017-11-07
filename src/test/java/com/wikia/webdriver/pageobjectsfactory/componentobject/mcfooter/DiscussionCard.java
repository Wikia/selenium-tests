package com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter;

import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
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

  @FindBy(css = ".wds-avatar")
  private WebElement avatarImage;

  @FindBy(css = ".mcf-card-discussions__content")
  private WebElement discussionsPost;

  @FindBy(css = ".mcf-card-discussions__zero-state-text")
  private WebElement discussionsZeroState;


  public boolean isDiscussionsCardPresent() {
    wait.forElementVisible(discussionsCard);

    return discussionsCard.isDisplayed();
  }

  public boolean isDiscussionsCardNotPresent() {
    wait.forElementNotVisible(discussionsCard);

    return true;
  }

  public DiscussionsPage clickOnViewAllLinkInDiscussions() {
    wait.forElementClickable(viewAllLink)
        .click();

    return new DiscussionsPage();
  }

  public String getUsername() {
    return avatarUsername.getText();
  }

  public UserProfilePage clickUserAvatar() {
    wait.forElementClickable(avatarImage);
    avatarImage.click();

    return new UserProfilePage();
  }

  public DiscussionsPage clickDiscussionsPost() {
    wait.forElementClickable(discussionsPost);
    discussionsPost.click();

    return new DiscussionsPage();
  }

  public boolean isZeroState() {
    wait.forElementVisible(discussionsZeroState);

    return discussionsZeroState.isDisplayed();
  }

}
