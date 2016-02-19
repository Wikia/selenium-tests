package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.elements.mercury.components.RecentWikiActivity;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.joda.time.DateTime;

public class RecentWikiActivityPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final RecentWikiActivity recentWikiActivity = new RecentWikiActivity();

  public RecentWikiActivityPage() {
    super();
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
