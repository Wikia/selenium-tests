package com.wikia.webdriver.testcases.searchtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.SearchPage;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "mercuryautomationtesting")
public class SearchPageTests extends NewTestTemplate {

  @Test(groups = "searchPageTests_exactMatchShowsRelatedCommunityModule")
  public void exactMatchShowsRelatedCommunityModule() {
    new SearchPage()
        .navigateToSearchPage()
        .typeCommunityNameInSearchInput()
        .clickSearchButton()
        .relatedCommunityModuleIsVisible();
  }
}
