package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-editing-post")
public class EditingPostTests extends NewTestTemplate {

  // User on mobile

  @Test(groups = "discussions-userMobileEditingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    data = DiscussionsOperations.using(User.STAFF, driver).updatePost(data);

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertTrue(post.hasEditedBySection(), "Post should have edited by section below post content.");
    Assertion.assertEquals(post.getEditedBySectionText(), "(edited by administrators)", "Post should have information that it was edited by administrators.");
  }

  @Test(groups = "discussions-userMobileEditingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeThatPostWasEditedByAdministratorOnPostsListPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    data = DiscussionsOperations.using(User.STAFF, driver).updatePost(data);

    final PostEntity post = new PostsListPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertFalse(post.hasEditedBySection(), "Post should not have information about who edited the post on posts list page.");
  }

  @Test(groups = "discussions-userMobileEditingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeThatPostWasEditedByAuthorOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.STAFF, driver).cratePostWithUniqueData();
    data = DiscussionsOperations.using(User.STAFF, driver).updatePost(data);

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertFalse(post.hasEditedBySection(), "If author and editor are the same person, edited by section should not be visible.");
  }

  // Discussions Administrator on mobile

  @Test(groups = "discussions-discussionsAdministratorMobileEditingPost")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    data = DiscussionsOperations.using(User.STAFF, driver).updatePost(data);

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertTrue(post.hasEditedBySection(), "Post should have edited by section below post content.");
    Assertion.assertStringNotContains(post.getEditedBySectionText(), "(edited by administrators)");
    Assertion.assertTrue(StringUtils.startsWith(post.getEditedBySectionText(), "(edited by "), "Post should have detailed information by who it was edited.");
  }
}
