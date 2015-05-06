package com.wikia.webdriver.testcases.social_buttons_tests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.social_buttons.SocialButtonsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author: Michal 'justnpT' Nowierski
 */
public class SocialButtonsForUserLanguage extends NewTestTemplate {

    private Credentials credentials = config.getCredentials();


  @Test(
      groups = {"SocialButtons", "SocialButtons_001"},
      dataProvider = "SocialButtonsDataProvider"
  )
    /**
    * This tests executes for 11 users with different languages.
    * Log in, open random article, compare present social buttons to expected social buttons
    */
    public void ArticleCRUDUser_001_specialPage(String[] credentials, String[] expectedSocialNetworks) {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.logInCookie(credentials[0], credentials[1], wikiURL);
        ArticlePageObject article = base.openRandomArticle(wikiURL);
        SocialButtonsComponentObject buttons = new SocialButtonsComponentObject(driver);
        buttons.verifyShareButtonsPresent();
        String[] currentSocialNetworks = buttons.getShareButtonTitles();
        for(int i = 0; i < expectedSocialNetworks.length; i++) {
            String currentSocialNetwork = currentSocialNetworks[i];
            String expectedSocialNetwork = expectedSocialNetworks[i];
            Assertion.assertEquals(currentSocialNetwork, expectedSocialNetwork,
                      "Expected network not found on its position. " +
                      "Note that the order of social buttons is also important, " +
                      "as defined in requirements. Missing network:" + expectedSocialNetwork);
        }
  }

    /**
     * Requirements set in https://wikia-inc.atlassian.net/browse/CONCF-511
     *
     * {
     *     {userName, userPassword},
     *     {expected buttons}
     * }
     */
    @DataProvider(name = "SocialButtonsDataProvider")
    public final String[][][] SocialButtonsDataProvider() {
        return new String[][][]{
                {
                        {credentials.userNameEnglish, credentials.passwordEnglish},
                        {"Facebook", "Twitter", "Reddit", "Tumblr"}
                },
                {
                        {credentials.userNameJapanese, credentials.passwordJapanese},
                        {"Facebook", "Twitter", "GooglePlus"}
                },
                {
                        {credentials.userNameBrazilianPortuguese, credentials.passwordBrazilianPortuguese},
                        {"Facebook", "Twitter", "Reddit", "Pinterest", "Tumblr"}
                },
                {
                        {credentials.userNameChinese, credentials.passwordChinese},
                        {"Facebook", "Weibo", "WeChat"}
                },
                {
                        {credentials.userNameGerman, credentials.passwordGerman},
                        {"Facebook", "Twitter", "Pinterest", "Tumblr"}
                },
                {
                        {credentials.userNameFrench, credentials.passwordFrench},
                        {"Facebook", "Twitter"}
                },
                {
                        {credentials.userNameSpanish, credentials.passwordSpanish},
                        {"Facebook", "Twitter", "Meneame", "Tumblr"}
                },
                {
                        {credentials.userNameRussian, credentials.passwordRussian},
                        {"VK", "Facebook", "OK", "Twitter"}
                },
                {
                        {credentials.userNamePolish, credentials.passwordPolish},
                        {"Facebook", "Twitter", "NK"}
                },
                {
                        {credentials.userNameItalian, credentials.passwordItalian},
                        {"Facebook", "Twitter", "Reddit", "Tumblr"}
                }
        };
    }

}

