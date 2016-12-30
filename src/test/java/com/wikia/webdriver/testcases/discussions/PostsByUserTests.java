package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.DeleteAllButton;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

public class PostsByUserTests extends NewTestTemplate {

  private User UserWithPosts = User.USER_6;

  @DataProvider(name = "postCreator")
  private Object[][] createPostByUser() {
    DiscussionsOperations.using(UserWithPosts, driver).createPostWithUniqueData();
    return new Object[][] { new Object[] { UserWithPosts.getUserId() }};
  }

  @Test(groups = "discussions-deleteAllPostsByUser", dataProvider = "postCreator")
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_AUTO)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonDeleteAllOptionNotVisible(String userId) {
    Assertion.assertFalse(deleteAllOptionVisible(userId));
  }

  private boolean deleteAllOptionVisible(String userId) {
    UserPostsPage userPosts = new UserPostsPage().open(userId);
    return getDeleteAllButton(userPosts).isVisible();
  }

  private DeleteAllButton getDeleteAllButton(final PageWithPosts page) {
    return page
      .getPost()
      .findNewestPost()
      .clickMoreOptions()
      .clickViewAllPostsByOption()
      .getDeleteAll();
  }

}
