package com.wikia.webdriver.testcases.discussions;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

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
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_5)
@Test(groups = "discussions-editing-post")
public class EditingPostTests extends NewTestTemplate {

  private static final String USER_MOBILE_TEST_GROUP = "discussions-userMobileEditingPost";

  private static final String USER_DESKTOP_TEST_GROUP = "discussions-userDesktopEditingPost";

  private static final String DISCUSSIONS_ADMINISTRATOR_MOBILE_TEST_GROUP = "discussions-discussionsAdministratorMobileEditingPost";

  private static final String DISCUSSIONS_ADMINISTRATOR_DESKTOP_TEST_GROUP = "discussions-discussionsAdministratorDesktopEditingPost";

  private static final String EDITED_BY_PREFIX = "(edited by ";

  private static final String EDITED_BY_ADMINISTRATORS = EDITED_BY_PREFIX + "administrators)";

  private static final String EDITED_BY_QA_STAFF = EDITED_BY_PREFIX + "QATestsStaff)";

  private static final String SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE = "Post should have edited by section below post content.";

  private static final String SHOULD_NOT_HAVE_EDITED_BY_SECTION_MESSAGE = "Post should not have information about who edited the post on posts list page.";

  private static final String EDITED_BY_ADMINISTRATORS_MESSAGE = "Post should have information that it was edited by administrators.";

  private static final String SAME_PERSON_MESSAGE = "If author and editor are the same person, edited by section should not be visible.";

  private static final String SHOULD_HAVE_DETAILED_INFORMATION_MESSAGE = "Post should have detailed information by who it was edited.";

  // User on mobile

  @Test(groups = USER_MOBILE_TEST_GROUP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void userOnMobileCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertTrue(post.hasEditedBySection(), SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE);
    Assertion.assertEquals(post.getEditedBySectionText(), EDITED_BY_ADMINISTRATORS, EDITED_BY_ADMINISTRATORS_MESSAGE);
  }

  @Test(groups = USER_MOBILE_TEST_GROUP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void userOnMobileCanNotSeeThatPostWasEditedByAdministratorOnPostsListPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostsListPage().open(data.getId()).getPost().findPostById(data.getId());
    Assertion.assertFalse(post.hasEditedBySection(), SHOULD_NOT_HAVE_EDITED_BY_SECTION_MESSAGE);
  }

  @Test(groups = USER_MOBILE_TEST_GROUP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void userOnMobileCanNotSeeThatPostWasEditedByAuthorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsStaffRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertFalse(post.hasEditedBySection(), SAME_PERSON_MESSAGE);
  }

  // User on desktop

  @Test(groups = USER_DESKTOP_TEST_GROUP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertTrue(post.hasEditedBySection(), SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE);
    Assertion.assertEquals(post.getEditedBySectionText(), EDITED_BY_ADMINISTRATORS, EDITED_BY_ADMINISTRATORS_MESSAGE);
  }

  @Test(groups = USER_DESKTOP_TEST_GROUP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotSeeThatPostWasEditedByAdministratorOnPostsListPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostsListPage().open(data.getId()).getPost().findPostById(data.getId());
    Assertion.assertFalse(post.hasEditedBySection(), SHOULD_NOT_HAVE_EDITED_BY_SECTION_MESSAGE);
  }

  @Test(groups = USER_DESKTOP_TEST_GROUP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotSeeThatPostWasEditedByAuthorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsStaffRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertFalse(post.hasEditedBySection(), SAME_PERSON_MESSAGE);
  }

  // Discussions Administrator on mobile

  @Test(groups = DISCUSSIONS_ADMINISTRATOR_MOBILE_TEST_GROUP)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void discussionsAdministratorOnMobileCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findPostById(data.getId());
    Assertion.assertTrue(post.hasEditedBySection(), SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE);
    Assertion.assertEquals(post.getEditedBySectionText(), EDITED_BY_QA_STAFF, SHOULD_HAVE_DETAILED_INFORMATION_MESSAGE);
  }


  // Discussions Administrator on desktop

  @Test(groups = DISCUSSIONS_ADMINISTRATOR_DESKTOP_TEST_GROUP)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findPostById(data.getId());
    Assertion.assertTrue(post.hasEditedBySection(), SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE);
    Assertion.assertEquals(post.getEditedBySectionText(), EDITED_BY_QA_STAFF, SHOULD_HAVE_DETAILED_INFORMATION_MESSAGE);
  }

  private PostEntity.Data createPostAsUserRemotely() {
    return DiscussionsOperations.using(User.USER, driver).createPostWithUniqueData();
  }

  private PostEntity.Data createPostAsStaffRemotely() {
    return DiscussionsOperations.using(User.STAFF, driver).createPostWithUniqueData();
  }

  private PostEntity.Data updatePostAsStaffRemotely(PostEntity.Data data) {
    return DiscussionsOperations.using(User.STAFF, driver).updatePost(data);
  }
}
