package com.wikia.webdriver.testcases.searchtests.intrawiki;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;

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
    	navigation.triggerSuggestions(SEARCH_SUGGESTION_PHRASE);
    	navigation.verifySuggestions(SEARCH_ARTICLE);
    	ArticlePageObject article = navigation.ArrowDownAndEnterSuggestion(SEARCH_ARTICLE);
    	article.verifyArticleName(SEARCH_ARTICLE);
	}
	
	@Test(groups = {"Search", "IntraWikiSearch", "goSearchFeature", "UserFromSuggestion"}, invocationCount=25)
	public void UserFromSuggestion() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage(testedWiki);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NavigationBar navigation = new NavigationBar(driver);
    	navigation.openWikiPage(testedWiki);
    	navigation.triggerSuggestions(SEARCH_SUGGESTION_PHRASE);
    	navigation.verifySuggestions(SEARCH_ARTICLE);
    	ArticlePageObject article = navigation.ArrowDownAndEnterSuggestion(SEARCH_ARTICLE);
    	article.verifyArticleName(SEARCH_ARTICLE);
	}
	
   @Test(groups = {"Search", "IntraWikiSearch", "goSearchFeature", "goSearchPreference"})
   public void goSearchPreference() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.openWikiPage(testedWiki);
        base.logInCookie(credentials.userNameGoSearchPreferred, credentials.passwordGoSearchPreferred, wikiURL);
        NavigationBar navigation = new NavigationBar(driver);
        ArticlePageObject article = navigation.goSearchFor(SEARCH_ARTICLE);
        article.verifyArticleName(SEARCH_ARTICLE);
    }

	
}
