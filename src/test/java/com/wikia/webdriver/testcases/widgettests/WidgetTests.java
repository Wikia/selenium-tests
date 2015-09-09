package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
public class WidgetTests extends NewTestTemplate {

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void WidgetTest_001_Twitter() {
    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(MercurySubpages.TWITTER_VERBATIM);
    articleContent.push("<twitter>", MercurySubpages.TWITTER_VERBATIM);

    TwitterWidgetPageObject twitterWidgetPageObject = new TwitterWidgetPageObject(driver);
    twitterWidgetPageObject.openMercuryArticleByName(wikiURL, MercurySubpages.TWITTER_VERBATIM);
    twitterWidgetPageObject.isLoadedOnOasis();
  }
}
