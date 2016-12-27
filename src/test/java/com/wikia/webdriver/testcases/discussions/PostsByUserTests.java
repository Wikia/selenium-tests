package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.MoreOptionsPopOver;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.Test;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

public class PostsByUserTests extends NewTestTemplate {

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.STAFF, onWikia = MercuryWikis.DISCUSSIONS_AUTO)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void postsByUserAreReachableViaMoreMenu() {
    Assertion.assertTrue(
      navigateViaMoreOptionsMenu(new PostsListPage().open())
      .getDeleteAll()
      .isVisible());
  }

  private UserPostsPage navigateViaMoreOptionsMenu(final PageWithPosts page) {
    return page
      .getPost()
      .findNewestPost()
      .clickMoreOptions()
      .clickViewAllPostsByOption();
  }

}
