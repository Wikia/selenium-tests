package com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.FandomPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MixedContentFooter extends WikiBasePageObject {

  @FindBy(css = "#mixed-content-footer")
  private WebElement mcFooter;

  @FindBy(css = ".mcf-header")
  private WebElement mcFooterHeader;

  @FindBy(css = ".mcf-card-wiki-articles")
  private WebElement moreOfWikiArticlesCard;

  @FindBy(css = ".mcf-card-related-wikis")
  private WebElement exploreWikisCard;

  @FindBy(css = ".mcf-card-related-wikis__item")
  private List<WebElement> exploreWikisList;

  @FindBy(css = ".mcf-card-article__link")
  private List<WebElement> articleLinks;

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

  public MixedContentFooter scrollToMCFooter() {
    wait.forElementVisible(mcFooter);
    jsActions.scrollToElement(mcFooter);
    wait.forElementVisible(mcFooterHeader);

    PageObjectLogging.log("scrollToFooter", "Scroll to the footer of the page", true);

    return this;
  }

  public boolean isMCFooterPresent() {
    //  MCF is lazy loaded when user scrolls to it, so to check if MCF is present we can check if its header is visible
    wait.forElementVisible(mcFooterHeader);
    PageObjectLogging.log("verifyMcfooterPresent", "MCFooter appeared", true);
    return mcFooter.isDisplayed();
  }

  public boolean isExploreWikisCardPresent() {
    wait.forElementVisible(exploreWikisCard);
    return exploreWikisCard.isDisplayed();
  }

  public boolean isMoreOfWikiArticlesCardPresent() {
    wait.forElementVisible(moreOfWikiArticlesCard);
    return moreOfWikiArticlesCard.isDisplayed();
  }

  public int countArticleCards() {
    //  MCF is lazy loaded when user scrolls to it, so to check if MCF is present we can check if its header is visible
    wait.forElementVisible(mcFooterHeader);
    return articleLinks.size();
  }

  public int countArticlesInExploreCard() {
    wait.forElementVisible(exploreWikisCard);
    return exploreWikisList.size();
  }

  public FandomPageObject clickFanomArticleCard() {
    wait.forElementClickable(fandomArticleCard);
    fandomArticleCard.click();

    return new FandomPageObject();
  }

  public ArticlePageObject clickWikiArticlecard() {
    wait.forElementClickable(wikiArticleCard);
    wikiArticleCard.click();

    return new ArticlePageObject();
  }

  public FandomPageObject clickFanomVideoCard() {
    wait.forElementClickable(fandomVideoCard);
    fandomVideoCard.click();

    return new FandomPageObject();
  }

  public ArticlePageObject clickWikiVideoCard() {
    wait.forElementClickable(wikiVideoCard);
    wikiVideoCard.click();

    return new ArticlePageObject();
  }

  public DiscussionCard getDiscussionsCard() {
    return new DiscussionCard();
  }
}
