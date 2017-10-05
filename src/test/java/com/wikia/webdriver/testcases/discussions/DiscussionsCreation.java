package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.elements.common.CommunityHeader;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.testcases.createawikitests.CreateWikiTests_lang;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DiscussionsCreation extends CreateWikiTests_lang {

  private String wikiUrl;

  @BeforeTest
  public void setUp() {
    CreateNewWiki_lang_TC001("en");
    wikiUrl = driver.getCurrentUrl();
  }

  @Execute(asUser = User.USER_CNW)
  @Test(groups = "discussions-creation")
  public void userCanSeeEmptyDiscussionsMessageOnNewCommunityDiscussionsPage() {
    PostsListPage page = navigateToDiscussions(wikiUrl);
    ErrorMessages errorMessage = page.getErrorMessages();
    Assertion.assertTrue(errorMessage.isEmptyPostsListMessageDisplayed());
  }

  @Execute(asUser = User.USER_CNW)
  @Test(groups = "discussions-creation")
  public void userCanAddPostOnNewCommunityDiscussionsPage() {
    PostsListPage page = navigateToDiscussions(wikiUrl);
    page.getPostsCreatorDesktop().startPostCreation().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().isPostListEmpty());
  }

  private PostsListPage navigateToDiscussions(String wikiUrl) {
    new ArticlePage().openWikiPage(wikiUrl);
    new CommunityHeader().clickDiscussLink();
    PostsListPage page = new PostsListPage();
    page.waitForPageReload();
    return page;
  }
}
