package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_5)
public class EditingPostTests extends NewTestTemplate {

  private static final String MOBILE = "discussions-editing-post-mobile";
  private static final String DESKTOP = "discussions-editing-post-desktop";

  private static final String EDITED_BY = "(edited by %s)";
  private static final String EDITED_BY_ADMINISTRATORS = String.format(EDITED_BY, "administrators");
  private static final String EDITED_BY_STAFF = String.format(EDITED_BY, User.STAFF.getUserName());

  private static final String SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE = "Post should have edited by section below post content.";
  private static final String SHOULD_NOT_HAVE_EDITED_BY_SECTION_MESSAGE = "Post should not have information about who edited the post on posts list page.";
  private static final String EDITED_BY_ADMINISTRATORS_MESSAGE = "Post should have information that it was edited by administrators.";
  private static final String SAME_PERSON_MESSAGE = "If author and editor are the same person, edited by section should not be visible.";
  private static final String SHOULD_HAVE_DETAILED_INFORMATION_MESSAGE = "Post should have detailed information by who it was edited.";

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertTrue(post.hasEditedBySection(), SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE);
    Assertion.assertEquals(post.getEditedBySectionText(), EDITED_BY_ADMINISTRATORS, EDITED_BY_ADMINISTRATORS_MESSAGE);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeThatPostWasEditedByAdministratorOnPostsListPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostsListPage().open().getPostById(data.getId());
    Assertion.assertFalse(post.hasEditedBySection(), SHOULD_NOT_HAVE_EDITED_BY_SECTION_MESSAGE);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeThatPostWasEditedByAuthorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsStaffRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertFalse(post.hasEditedBySection(), SAME_PERSON_MESSAGE);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPostById(data.getId());
    Assertion.assertTrue(post.hasEditedBySection(), SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE);
    Assertion.assertEquals(post.getEditedBySectionText(), EDITED_BY_STAFF, SHOULD_HAVE_DETAILED_INFORMATION_MESSAGE);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertTrue(post.hasEditedBySection(), SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE);
    Assertion.assertEquals(post.getEditedBySectionText(), EDITED_BY_ADMINISTRATORS, EDITED_BY_ADMINISTRATORS_MESSAGE);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotSeeThatPostWasEditedByAdministratorOnPostsListPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostsListPage().open().getPostById(data.getId());
    Assertion.assertFalse(post.hasEditedBySection(), SHOULD_NOT_HAVE_EDITED_BY_SECTION_MESSAGE);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotSeeThatPostWasEditedByAuthorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsStaffRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPost().findNewestPost();
    Assertion.assertFalse(post.hasEditedBySection(), SAME_PERSON_MESSAGE);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsAdministratorOnDesktopCanSeeThatPostWasEditedByAdministratorOnPostDetailsPage() {
    final PostEntity.Data data = updatePostAsStaffRemotely(createPostAsUserRemotely());

    final PostEntity post = new PostDetailsPage().open(data.getId()).getPostById(data.getId());
    Assertion.assertTrue(post.hasEditedBySection(), SHOULD_HAVE_EDITED_BY_SECTION_MESSAGE);
    Assertion.assertEquals(post.getEditedBySectionText(), EDITED_BY_STAFF, SHOULD_HAVE_DETAILED_INFORMATION_MESSAGE);
  }

  /**
   * HELPER METHODS
   */

  private PostEntity.Data createPostAsUserRemotely() {
    return DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
  }

  private PostEntity.Data createPostAsStaffRemotely() {
    return DiscussionsClient.using(User.STAFF, driver).createPostWithUniqueData();
  }

  private PostEntity.Data updatePostAsStaffRemotely(PostEntity.Data data) {
    return DiscussionsClient.using(User.STAFF, driver).updatePost(data);
  }
}
