package com.wikia.webdriver.testcases.communityheadertests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.CommunityHeader;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import junit.framework.Assert;
import org.testng.annotations.Test;

@Test(groups = {"CommunityHeaderTests"})
public class CommunityHeaderTests extends NewTestTemplate {
  // TODO Test cases:
  // - buttons for anon links to proper places
  // - buttons for logged in links to proper places
  // - buttons for admin links to proper places
  // - links in explore links to proper places
  // - disscuss link logic
  //    - for wiki with discussions enabled
  //    - for wiki with forum and discussions enabled
  //    - for wiki with only forum enabled
  //    - for wiki without forum or discussions
  // - visibility of elements for bigger breakpoint
  // - visibility of elements for lower breakpoint


  public void wordmarkShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeader().clickWordmark();

    Assert.assertTrue(mainPage.isMainPage());
  }

  public void wikiNameShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeader().clickWikiName();

    Assert.assertTrue(mainPage.isMainPage());
  }

  @Execute(asUser = User.ANONYMOUS)
  public void anonCanAddPageViaAddPageButton() {
    new CommunityHeader().clickAddNewPage();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:CreatePage"));
  }
}
