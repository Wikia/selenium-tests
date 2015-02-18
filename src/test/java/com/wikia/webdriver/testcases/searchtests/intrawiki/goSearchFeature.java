package com.wikia.webdriver.testcases.searchtests.intrawiki;

import org.testng.annotations.Test;

import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;

/*
*  AnonFromSuggestion: Make sure clicking enter on suggestion takes you to destination page for anonymous
*  UserFromSuggestion: Make sure clicking enter on suggestion takes you to destination page for anonymous
*  goSearchLoggedIn: Make sure clicking search button after typing article name to the search field doesn't take you to destination page for anonymous   
*  goSearchLoggedIn: Make sure clicking search button after typing article name to the search field doesn't take you to destination page for logged In user  
*  goSearchUserPreference: Make sure clicking search button after typing article name to the search field takes you to destination page when user has the preference set
*/

public class goSearchFeature extends BasicActions {
	
	@Test(groups = {"dropDownSuggestions", "IntraWikiSearch", "Search"})
		public void goSearchAnon() {
	    	NavigationBar navigation = new NavigationBar (driver);
	    	navigation.openWikiPage(testedWiki);
	    	navigation.triggerSuggestions(SEARCH_SUGGESTION_PHRASE);
	    	navigation.verifySuggestions(SEARCH_ARTICLE);
	    	ArticlePageObject article = navigation.ArrowDownAndEnterSuggestion(SEARCH_ARTICLE);
	    	article.verifyArticleName(SEARCH_ARTICLE);
	}
}
