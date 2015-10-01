package com.wikia.webdriver.testcases.searchtests.intrawiki;

import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.search.IntraWiki;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

/*
 *  AnonFromSuggestion: Make sure clicking enter on suggestion takes you to destination page for anonymous
 *  UserFromSuggestion: Make sure clicking enter on suggestion takes you to destination page for logged in user 
 *  goSearchPreference: Make sure clicking search button after typing article name to the search field takes you to destination page when user has the preference enabled
 */
@Test(groups = {"IntraWikiSearchGoSearch"})
public class GoSearchFeature extends IntraWiki {

    @Test(groups = {"Search", "IntraWikiSearch", "goSearchFeature", "AnonFromSuggestion"})
    @RelatedIssue(issueID = "MAIN-5045", comment = "please Type 'GON' in search, arrow down to "
                                                   + "Gonzo article and enter. If you got to "
                                                   + "Gonzo article the test passed.")
    public void AnonFromSuggestion() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.openWikiPage(testedWiki);
        NavigationBar navigation = new NavigationBar(driver);
        navigation.triggerSuggestions(SEARCH_SUGGESTION_PHRASE);
        navigation.verifySuggestions(SEARCH_ARTICLE);
        ArticlePageObject article = navigation.ArrowDownAndEnterSuggestion(SEARCH_ARTICLE);
      article.verifyArticleNameInWgPageName(SEARCH_ARTICLE);
    }

    @Test(groups = {"Search", "IntraWikiSearch", "goSearchFeature", "UserFromSuggestion"})
    public void UserFromSuggestion() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.openWikiPage(testedWiki);
        base.loginAs(credentials.userName, credentials.password, wikiURL);
        NavigationBar navigation = new NavigationBar(driver);
        navigation.openWikiPage(testedWiki);
        navigation.triggerSuggestions(SEARCH_SUGGESTION_PHRASE);
        navigation.verifySuggestions(SEARCH_ARTICLE);
        ArticlePageObject article = navigation.ArrowDownAndEnterSuggestion(SEARCH_ARTICLE);
      article.verifyArticleNameInWgPageName(SEARCH_ARTICLE);
    }

    @Test(groups = {"Search", "IntraWikiSearch", "goSearchFeature", "goSearchPreference"})
    @Execute(asUser = User.USER_GO_SEARCH_PREFERRED, onWikia = "muppet")
    public void goSearchPreference() {
        new WikiBasePageObject(driver).openWikiPage(wikiURL);
        NavigationBar navigation = new NavigationBar(driver);
        ArticlePageObject article = navigation.goSearchFor(SearchContent.SEARCH_ARTICLE);
      article.verifyArticleNameInWgPageName(SearchContent.SEARCH_ARTICLE);
    }
}
