package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.elements.common.CommunityHeader;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.testcases.createawikitests.CreateWikiTests_lang;
import org.testng.annotations.Test;

@Test(groups = "discussions-creation")
public class DiscussionsCreation extends CreateWikiTests_lang {

  @Execute(asUser = User.USER_CNW)
  public void userCanSeeEmptyDiscussionsMessageOnNewCommunityDiscussionsPage() {
    CreateNewWiki_lang_TC001("en");
    PostsListPage page = navigateToDiscussions(driver.getCurrentUrl());
    Assertion.assertTrue(page.getErrorMessages().isEmptyPostsListMessageDisplayed());
    createNewPost(page);
    Assertion.assertFalse(page.getPost().isPostListEmpty());
  }

  private void createNewPost(PostsListPage page) {
    page.getPostsCreatorDesktop().startPostCreation().clickSubmitButton();
    page.waitForPageReload();
  }

  private PostsListPage navigateToDiscussions(String wikiUrl) {
    new ArticlePage().openWikiPage(wikiUrl);
    new CommunityHeader().clickDiscussLink();
    PostsListPage page = new PostsListPage();
    page.waitForPageReload();
    return page;
  }
}
