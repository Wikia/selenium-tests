package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Search;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SearchTests extends NewTestTemplate {

  private static final String SEARCH_PHRASE = "Infobox";

  public void mercury_search_clearSearchPhrase(WikiBasePageObject page) {
    String currentPhrase = page
        .getTopBar()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickClearSearchButton()
        .getSearchPhrase();

    Assertion.assertTrue(currentPhrase.isEmpty());
  }

  public void mercury_search_verifySearchLayout(WikiBasePageObject page) {
    Search search = page
        .getTopBar()
        .openSearch();

    Assertion.assertTrue(search.isSearchInputFieldVisible());
    Assertion.assertTrue(search.isClearSearchButtonVisible());
    Assertion.assertTrue(search.isInputFieldSearchIconVisible());
  }
}
