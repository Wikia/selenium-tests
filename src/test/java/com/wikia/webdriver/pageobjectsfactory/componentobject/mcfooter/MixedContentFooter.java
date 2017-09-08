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

  @FindBy(css = ".mcf-card-wiki-articles")
  private WebElement moreOfWikiArticlesCard;

  @FindBy(css = ".mcf-card-discussions")
  private WebElement discussionsCard;

  @FindBy(css = ".mcf-card-related-wikis")
  private WebElement exploreWikisCard;

  @FindBy(css = ".mcf-card-article__link")
  private List<WebElement> articleLinks;

    public MixedContentFooter openWikiMainPage() {
    getUrl(getWikiUrl() + URLsContent.WIKI_DIR);
    PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);
    return this;
  }

  public void scrollToMCFooter() {
    wait.forElementVisible(mcFooter);
    jsActions.scrollToElement(mcFooter);

    PageObjectLogging.log("scrollToFooter", "Scroll to the footer of the page", true);
  }

  public boolean isMCFooterPresent() {
    wait.forElementVisible(mcFooter);
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

  public int countArticleCards (){
    wait.forElementVisible(mcFooter);
    return articleLinks.size();
  }

}

