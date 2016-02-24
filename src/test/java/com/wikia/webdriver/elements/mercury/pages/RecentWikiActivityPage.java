package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.RecentWikiActivity;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.joda.time.DateTime;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecentWikiActivityPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final RecentWikiActivity recentWikiActivity = new RecentWikiActivity();
  @FindBy(css = ".side-nav-toggle-2016")
  private WebElement topBar;

  private Navigation navigation;

  public RecentWikiActivityPage() {
    super();

    this.navigation = new Navigation(driver);
  }

  public RecentWikiActivityPage openFromMenu() {
    getUrl(String.format("%s", urlBuilder.getUrlForWiki()));
    topBar.click();
    navigation.openSubMenu(1);
    navigation.openSubMenu(0);
    navigation.openPageLink(0);

    Assertion.assertTrue(driver.getCurrentUrl().contains(URLsContent.RECENT_WIKI_ACTIVITY),
                         "You were not redirected to the recent wiki activity");

    return this;
  }

  private void editArticle(String articleName) {
    String text =  "Additional line";
    ArticleContent articleContent = new ArticleContent();

    articleContent.clear(articleName);

    articleContent.push(text, articleName);
  }

  public RecentWikiActivityPage open(){
    editArticle("RWA" + DateTime.now().getMillis());
    getUrl(String.format("%s%s", urlBuilder.getUrlForWiki(), URLsContent.RECENT_WIKI_ACTIVITY));

    return this;
  }
}
