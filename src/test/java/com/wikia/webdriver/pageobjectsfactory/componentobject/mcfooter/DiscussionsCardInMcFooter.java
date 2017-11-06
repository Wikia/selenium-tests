package com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiscussionsCardInMcFooter extends WikiBasePageObject{

  @FindBy(css = ".mcf-card-discussions")
  private WebElement discussionsCard;

  @FindBy(css = ".mcf-card-discussions__link")
  private WebElement viewAllLink;

  @FindBy(css = ".site-body-discussion")
  private WebElement discussionsBody;

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

  public void clickOnViewAllLinkInDiscussions() {
    wait.forElementClickable(viewAllLink).click();
  }

  public boolean isDiscussions() {
    return discussionsBody.isDisplayed();
  }

  public String getUsername() {
    return avatarUsername.getText();
  }

  public DiscussionsCardInMcFooter clickUserAvatar() {
    wait.forElementClickable(avatarImage);
    avatarImage.click();
    return this;
  }

  public DiscussionsCardInMcFooter clickDiscussionsPost() {
    wait.forElementClickable(discussionsPost);
    discussionsPost.click();
    return this;
  }

  public boolean isZeroState() {
    wait.forElementVisible(discussionsZeroState);
    return discussionsZeroState.isDisplayed();
  }

}
