package com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MixedContentFooter extends WikiBasePageObject{

  @FindBy(css = "#mixed-content-footer")
  private WebElement mcFooter;

  @FindBy(css = ".mcf-header")
  private WebElement mcFooterHeader;

  @FindBy(css = ".mcf-card-wiki-articles")
  private WebElement moreOfWikiArticlesCard;

  @FindBy(css = ".mcf-card-discussions")
  private WebElement discussionsCard;

  @FindBy(css = ".mcf-card-related-wikis")
  private WebElement exploreWikisCard;

  @FindBy (css = ".mcf-card-related-wikis__item")
  private List<WebElement> exploreWikisList;

  @FindBy(css = ".mcf-card-article__link")
  private List<WebElement> articleLinks;

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

  @FindBy(css = ".mcf-card-article[data-li-type='ns']")
  private WebElement fandomArticleCard;

  @FindBy(css = ".mcf-card-article[data-li-type='wiki']")
  private WebElement wikiArticleCard;

  @FindBy(css = ".mcf-card-article[data-li-type='ns'] .mcf-card-article__video-container")
  private WebElement fandomVideoCard;

  @FindBy(css = ".mcf-card-article[data-li-type='wiki'] .mcf-card-article__video-container")
  private WebElement wikiVideoCard;

  public MixedContentFooter openWikiMainPage() {
    getUrl(getWikiUrl() + URLsContent.WIKI_DIR);
    PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);
    return this;
  }

  public void scrollToMCFooter() {
    wait.forElementVisible(mcFooter);
    jsActions.scrollToElement(mcFooter);
    wait.forElementVisible(mcFooterHeader);

    PageObjectLogging.log("scrollToFooter", "Scroll to the footer of the page", true);
  }

  public boolean isMCFooterPresent() {
    //  MCF is lazy loaded when user scrolls to it, so to check if MCF is present we can check if its header is visible
    wait.forElementVisible(mcFooterHeader);
    PageObjectLogging.log("verifyMcfooterPresent", "MCFooter appeared", true);
    return mcFooter.isDisplayed();
  }

  public boolean isDiscussionsCardPresent() {
    wait.forElementVisible(discussionsCard);
    return discussionsCard.isDisplayed();
  }

  public boolean isDiscussionsCardNotPresent() {
    wait.forElementNotVisible(discussionsCard);
    return true;
  }

  public boolean isExploreWikisCardPresent() {
    wait.forElementVisible(exploreWikisCard);
    return exploreWikisCard.isDisplayed();
  }

  public boolean isMoreOfWikiArticlesCardPresent() {
    wait.forElementVisible(moreOfWikiArticlesCard);
    return moreOfWikiArticlesCard.isDisplayed();
  }

  public int countArticleCards () {
    //  MCF is lazy loaded when user scrolls to it, so to check if MCF is present we can check if its header is visible
    wait.forElementVisible(mcFooterHeader);
    return articleLinks.size();
  }

  public int countArticlesInExploreCard() {
    wait.forElementVisible(exploreWikisCard);
    return exploreWikisList.size();
  }

  public void clickOnViewAllLinkInDiscussions() {
    wait.forElementVisible(viewAllLink).click();
  }

  public boolean isDiscussions() {
    return discussionsBody.isDisplayed();
  }

  public String getUsername() {
    return avatarUsername.getText();
  }

  public MixedContentFooter clickUserAvatar() {
    avatarImage.click();
    return this;
  }

  public MixedContentFooter clickDiscussionsPost() {
    discussionsPost.click();
    return this;
  }

  public boolean isZeroState() {
    return discussionsZeroState.isDisplayed();
  }

  public void clickFanomArticleCard() {
    fandomArticleCard.click();
  }

  public void clickWikiArticlecard() {
    wikiArticleCard.click();
  }

  public void clickFanomVideoCard() {
    fandomVideoCard.click();
  }

  public void clickWikiVideoCard() { wikiVideoCard.click(); }
}
