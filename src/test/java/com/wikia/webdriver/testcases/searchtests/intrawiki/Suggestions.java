package com.wikia.webdriver.testcases.searchtests.intrawiki;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.SearchContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;

/*
*  dropDownSuggestions: Type at least 3 chars and verify suggestions are displaying and contain given phrase
*  searchSuggestionsVisibility: Verify Image and text is shown next to every suggestion
*/

public class Suggestions extends BasicActions {

	  @Test(groups = {"dropDownSuggestions", "IntraWikiSearch", "Search"})
	  public void dropDownSuggestions() {
	    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
	    search.openWikiPage(testedWiki);
	    search.triggerSuggestions(SearchContent.SEARCH_SUGGESTION_PHRASE);
	    search.verifySuggestions(SearchContent.SEARCH_ARTICLE);
	    
	  }
	  
	  @Test(enabled = false, groups = {"searchSuggestionsVisibility", "IntraWikiSearch", "Search"})
	  public void searchSuggestionsVisibility() {
	    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
	    search.openWikiPage(searchSuggestionsWiki);
	    search.verifyNewSuggestionsTextAndImages(SearchContent.SEARCH_PHRASE_NEW_SUGGESTIONS);
	  }
	  
}
