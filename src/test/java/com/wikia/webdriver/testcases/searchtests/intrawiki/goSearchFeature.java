package com.wikia.webdriver.testcases.searchtests.intrawiki;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

/*
*  AnonFromSuggestion: Make sure clicking enter on suggestion takes you to destination page for anonymous
*  UserFromSuggestion: Make sure clicking enter on suggestion takes you to destination page for logged in user 
*  goSearchPreference: Make sure clicking search button after typing article name to the search field takes you to destination page when user has the preference enabled
*/

public class goSearchFeature extends BasicActions {
	
	@Test(groups = {"Search", "IntraWikiSearch", "goSearchFeature", "AnonFromSuggestion"})
	public void AnonFromSuggestion() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(testedWiki);
		NavigationBar navigation = new NavigationBar(driver);
    	navigation.triggerSuggestions(SearchContent.SEARCH_SUGGESTION_PHRASE);
    	navigation.verifySuggestions(SearchContent.SEARCH_ARTICLE);
    	ArticlePageObject article = navigation.ArrowDownAndEnterSuggestion(SearchContent.SEARCH_ARTICLE);
    	article.verifyArticleName(SearchContent.SEARCH_ARTICLE);
	}
	
	@Test(groups = {"Search", "IntraWikiSearch", "goSearchFeature", "UserFromSuggestion"})
	public void UserFromSuggestion() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(testedWiki);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NavigationBar navigation = new NavigationBar(driver);
    	navigation.openWikiPage(testedWiki);
    	navigation.triggerSuggestions(SearchContent.SEARCH_SUGGESTION_PHRASE);
    	navigation.verifySuggestions(SearchContent.SEARCH_ARTICLE);
    	ArticlePageObject article = navigation.ArrowDownAndEnterSuggestion(SearchContent.SEARCH_ARTICLE);
    	article.verifyArticleName(SearchContent.SEARCH_ARTICLE);
	}
	
   @Test(groups = {"Search", "IntraWikiSearch", "goSearchFeature", "goSearchPreference"})
   public void goSearchPreference() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.openWikiPage(testedWiki);
        base.logInCookie(credentials.userNameGoSearchPreferred, credentials.passwordGoSearchPreferred, wikiURL);
        NavigationBar navigation = new NavigationBar(driver);
        ArticlePageObject article = navigation.goSearchFor(SearchContent.SEARCH_ARTICLE);
        article.verifyArticleName(SearchContent.SEARCH_ARTICLE);
    }

	
}
