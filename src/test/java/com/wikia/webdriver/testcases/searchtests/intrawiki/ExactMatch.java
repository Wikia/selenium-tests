package com.wikia.webdriver.testcases.searchtests.intrawiki;

import com.wikia.webdriver.common.dataprovider.IntraWikiSearchProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.search.intrawikisearch.IntraWikiSearchPageObject;

import org.testng.annotations.Test;

/**
 * Created by Ludwik on 2015-01-23.
 */
public class ExactMatch extends NewTestTemplate {

  @Test(dataProviderClass = IntraWikiSearchProvider.class,
      dataProvider = "getArticleName",
      groups = {"IntraWikiSearch_001", "IntraWikiSearchExactMatch", "Search"}
  )
  public void IntraWikiSearch_001_exactMatch(String query) {
    IntraWikiSearchPageObject search = new IntraWikiSearchPageObject(driver);
    search.openWikiPage(wikiURL);
    search.searchFor(query);
    search.verifyFirstResult(query);
  }
}
