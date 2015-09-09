package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.verbatim.TwitterPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
// Uncoment after finish all verbatim tags
//@Test(groups = {"MercuryVerbatimTests", "Mercury"})
public class VerbatimTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

    // This login is temporary solution, use @Execute after QAART-669 is done
    new LoginPage(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
                                          Configuration.getCredentials().passwordStaff2);
  }

  @Test(groups = "MercuryVerbatimTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryVerbatimTest_001_Twitter() {
    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(MercurySubpages.TWITTER_VERBATIM);
    articleContent.push("<twitter>", MercurySubpages.TWITTER_VERBATIM);

    TwitterPageObject twitterPageObject = new TwitterPageObject(driver);
    twitterPageObject.openMercuryArticleByName(wikiURL, MercurySubpages.TWITTER_VERBATIM);
    twitterPageObject.isLoaded();
  }
}
