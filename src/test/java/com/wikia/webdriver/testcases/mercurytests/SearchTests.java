package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Search;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SearchTests extends NewTestTemplate {

  private static final String SEARCH_PHRASE = "Infobox";

  void mercury_search_clearSearchPhrase(WikiBasePageObject page) {
    String currentPhrase = page
        .getTopBar()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickClearSearchButton()
        .getSearchPhrase();

    Assertion.assertTrue(currentPhrase.isEmpty());
  }

  void mercury_search_verifySearchLayout(WikiBasePageObject page) {
    Search search = page
        .getTopBar()
        .openSearch();

    Assertion.assertTrue(search.isSearchInputFieldVisible());
    Assertion.assertTrue(search.isClearSearchButtonVisible());
    Assertion.assertTrue(search.isInputFieldSearchIconVisible());
  }
}
