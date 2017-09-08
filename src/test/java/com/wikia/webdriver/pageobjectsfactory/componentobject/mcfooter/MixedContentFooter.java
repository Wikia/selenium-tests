package com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MixedContentFooter extends WikiBasePageObject{

  @FindBy(css = "#mixed-content-footer")
  private WebElement mcFooter;

  @FindBy(css = ".mcf-row")
  private WebElement rowWithCards;

  @FindBy(css = ".mcf-card-wiki-articles")
  private WebElement moreOfWikiArticlesCard;

  @FindBy(css = ".mcf-card-discussions")
  private WebElement discussionsCard;

  @FindBy(css = ".mcf-card-related-wikis")
  private WebElement exploreWikisCard;

  public MixedContentFooter(WebDriver driver) {
    super();
  }

  public MixedContentFooter openWikiMainPage() {
    getUrl(getWikiUrl() + URLsContent.WIKI_DIR);
    PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);
    return this;
  }
  public boolean isMcfooterPresent() {
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



}
