package com.wikia.webdriver.testcases.verbatimtests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.verbatim.widgets.TwitterPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
public class VerbatimTests extends NewTestTemplate {

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void VerbatimTest_001_Twitter() {
    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(MercurySubpages.TWITTER_VERBATIM);
    articleContent.push("<twitter>", MercurySubpages.TWITTER_VERBATIM);

    TwitterPageObject twitterPageObject = new TwitterPageObject(driver);
    twitterPageObject.openMercuryArticleByName(wikiURL, MercurySubpages.TWITTER_VERBATIM);
    twitterPageObject.isLoadedOnOasis();
  }
}
