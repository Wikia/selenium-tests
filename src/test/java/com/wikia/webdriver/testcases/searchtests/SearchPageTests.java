package com.wikia.webdriver.testcases.searchtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.SearchPagePageObject;

import org.testng.annotations.Test;

public class SearchPageTests {

  @Test
public void extactMach() {
  SearchPagePageObject search = new SearchPagePageObject().open();
  search.typeInCommunityName().clickSearchButton();
  Assertion.assertTrue(search.isRelatedCommunityModulePresent());
}
}
