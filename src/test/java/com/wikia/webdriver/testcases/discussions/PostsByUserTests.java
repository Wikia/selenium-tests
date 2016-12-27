package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.MoreOptionsPopOver;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.junit.Test;

public class PostsByUserTests extends NewTestTemplate {

  @Test
  public void postsByUserAreReachableViaMoreMenu(final PageWithPosts page) {
    UserPostsPage posts = page
      .getPost()
      .findNewestPost()
      .clickMoreOptions()
      .clickViewAllPostsByOption();
    // should it still return MoreOptrionsPopOver? Or maybe a UsersPostsPage?
    Assertion.assertTrue(false);
  }
}
