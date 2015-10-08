package com.wikia.webdriver.testcases.social_buttons_tests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.social_buttons.SocialButtonsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SocialButtonsTests extends NewTestTemplate {

  private Credentials credentials = Configuration.getCredentials();

  /**
   * This tests executes for 11 users with different languages. Log in, open random article, compare
   * present social buttons to expected social buttons
   */
  @Test(groups = {"SocialButtons", "SocialButtons_001"}, dataProvider = "SocialButtonsDataProvider")
  public void SocialButtons_001_differentLanguages(String[] credentials,
      String[] expectedSocialNetworks) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials[0], credentials[1], wikiURL);
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    SocialButtonsComponentObject buttons = new SocialButtonsComponentObject(driver);
    buttons.verifyShareButtonsPresent();
    String[] currentSocialNetworks = buttons.getShareButtonTitles();
    for (int i = 0; i < expectedSocialNetworks.length; i++) {
      String currentSocialNetwork = currentSocialNetworks[i];
      String expectedSocialNetwork = expectedSocialNetworks[i];
      Assertion.assertEquals(currentSocialNetwork, expectedSocialNetwork,
          "Expected network not found on its position. "
              + "Note that the order of social buttons is also important, "
              + "as defined in requirements. Missing network:" + expectedSocialNetwork);
    }
  }

  /**
   * Requirements set in https://wikia-inc.atlassian.net/browse/CONCF-511
   *
   * { {userName, userPassword}, {expected buttons} }
   */
  @DataProvider(name = "SocialButtonsDataProvider")
  public final String[][][] SocialButtonsDataProvider() {
    return new String[][][] {
        { {credentials.userNameEnglish, credentials.passwordEnglish},
            {"Facebook", "Twitter", "Reddit", "Tumblr"}},
        { {credentials.userNameJapanese, credentials.passwordJapanese},
            {"Facebook", "Twitter", "Google+"}},
        { {credentials.userNameBrazilianPortuguese, credentials.passwordBrazilianPortuguese},
            {"Facebook", "Twitter", "Reddit", "Tumblr"}},
        { {credentials.userNameChinese, credentials.passwordChinese}, {"Facebook", "Sina Weibo"}},
        { {credentials.userNameGerman, credentials.passwordGerman},
            {"Facebook", "Twitter", "Tumblr"}},
        { {credentials.userNameFrench, credentials.passwordFrench}, {"Facebook", "Twitter"}},
        { {credentials.userNameSpanish, credentials.passwordSpanish},
            {"Facebook", "Twitter", "Menéame", "Tumblr"}},
        { {credentials.userNameRussian, credentials.passwordRussian},
            {"VK", "Facebook", "Odnoklassniki", "Twitter"}},
        { {credentials.userNamePolish, credentials.passwordPolish},
            {"Facebook", "Twitter", "NK", "Wykop"}},
        { {credentials.userNameItalian, credentials.passwordItalian},
            {"Facebook", "Twitter", "Reddit", "Tumblr"}}};
  }

}
