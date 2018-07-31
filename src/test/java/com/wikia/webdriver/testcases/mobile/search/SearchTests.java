package com.wikia.webdriver.testcases.mobile.search;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.Search;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SearchTests extends NewTestTemplate {

  private static final String SEARCH_PHRASE = "Infobox";

  public void clearSearchPhrase(WikiBasePageObject page) {
    String currentPhrase = page.getGlobalNavigationMobile()
        .openSearch()
        .typeInSearch(SEARCH_PHRASE)
        .clickClearSearchButton()
        .getSearchPhrase();

    Assertion.assertTrue(currentPhrase.isEmpty());
  }

  public void verifySearchLayout(WikiBasePageObject page) {
    Search search = page.getGlobalNavigationMobile().openSearch().typeInSearch("qAga");

    Assertion.assertTrue(search.isSearchInputFieldVisible());
    Assertion.assertTrue(search.isClearSearchButtonVisible());
    Assertion.assertTrue(search.isInputFieldSearchIconVisible());
  }
}
