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
import com.wikia.webdriver.elements.mercury.components.discussions.common.MoreOptionsPopOver;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-locking-posts")
public class LockingPostTests extends NewTestTemplate {

  public static final String SHOULD_NOT_LOCK_MESSAGE = "%s should not be able to lock a post.";

  public static final String SHOULD_LOCK_MESSAGE = "%s should be able to lock the post.";

  public static final String SHOULD_UNLOCK_MESSAGE = "%s should be able to unlock post locked by %s.";

  public static final String SHOULD_NOT_ADD_REPLY_MESSAGE = "%s should not be able to create reply under post locked by %s.";

  public static final String SHOULD_ADD_REPLY_MESSAGE = "%s should be able to add reply to post unlocked by %s.";

  // Anonymous user on mobile

  @Test(groups = "discussions-anonymousUserMobileLocking")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnPostDetailsPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PageWithPosts page = new PostDetailsPage().open(data.getId());

    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptions(page);

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = "discussions-anonymousUserMobileLocking")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnPostsListPage() {
    DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PageWithPosts page = new PostsListPage().open();

    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptions(page);

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = "discussions-anonymousUserMobileLocking")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnUserPostsPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PageWithPosts page = new UserPostsPage().open(data.getAuthorId());

    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptions(page);

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  // Second User on mobile

  @Test(groups = "discussions-userMobileLocking")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnPostDetailsPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PostDetailsPage page = new PostDetailsPage().open(data.getId());

    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptions(page);

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = "discussions-userMobileLocking")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnPostsListPage() {
    DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PageWithPosts page = new PostsListPage().open();

    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptions(page);

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = "discussions-userMobileLocking")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnUserPostsPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PageWithPosts page = new UserPostsPage().open(data.getAuthorId());

    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptions(page);

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  // User on mobile

  @Test(groups = "discussions-userMobileLocking")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());

    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), message);
  }

  @Test(groups = "discussions-userMobileLocking")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReply(page);
    boolean actual = isReplyPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(actual, message);
  }

  // Discussions Administrator on mobile

  @Test(groups = "discussions-discussionsAdministratorMobileLocking")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.DISCUSSIONS_ADMINISTRATOR.name()));
  }

  @Test(groups = "discussions-discussionsAdministratorMobileLocking")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanUnlockPostLockedByDiscussionsAdministratorOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).lockPost(data);

    PostEntity postEntity = unlockPost(data);

    final String name = User.DISCUSSIONS_ADMINISTRATOR.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  // Staff user on mobile

  @Test(groups = "discussions-staffUserMobileLocking")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.STAFF.name()));
  }

  @Test(groups = "discussions-staffUserMobileLocking")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanUnlockPostLockedByStaffOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.STAFF, driver).lockPost(data);

    PostEntity postEntity = unlockPost(data);

    final String name = User.STAFF.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }


  @Test(groups = "discussions-staffUserMobileLocking")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).lockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());

    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.STAFF.name(), User.DISCUSSIONS_ADMINISTRATOR.name());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), message);
  }

  @Test(groups = "discussions-staffUserMobileLocking")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReply(page);
    boolean actual = isReplyPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.STAFF.name(), User.DISCUSSIONS_ADMINISTRATOR.name());
    Assertion.assertFalse(actual, message);
  }

  // Discussions moderator on mobile

  @Test(groups = "discussions-discussionsModeratorMobileLocking")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.DISCUSSIONS_ADMINISTRATOR.name()));
  }

  @Test(groups = "discussions-discussionsModeratorMobileLocking")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanUnlockPostLockedByDiscussionsModeratorOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data);

    PostEntity postEntity = unlockPost(data);

    final String name = User.DISCUSSIONS_MODERATOR.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  @Test(groups = "discussions-discussionsModeratorMobileLocking")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.STAFF, driver).lockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());

    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.DISCUSSIONS_MODERATOR.name(), User.STAFF.name());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), message);
  }


  @Test(groups = "discussions-discussionsModeratorMobileLocking")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.STAFF, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReply(page);
    boolean actual = isReplyPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.DISCUSSIONS_MODERATOR.name(), User.STAFF.name());
    Assertion.assertFalse(actual, message);
  }

  private MoreOptionsPopOver findMoreOptions(final PageWithPosts page) {
    return page.getPost().findNewestPost().clickMoreOptions();
  }

  private PostEntity lockPost(final PostEntity.Data data) {
    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final PostEntity postEntity = page.getPost().findNewestPost();
    postEntity.clickMoreOptions().clickLockPostOption();
    return postEntity;
  }

  private PostEntity unlockPost(final PostEntity.Data data) {
    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final PostEntity postEntity = page.getPost().findNewestPost();
    postEntity.clickMoreOptions().clickUnlockPostOption();
    return postEntity;
  }

  private String addReply(PostDetailsPage page) {
    final String text = TextGenerator.createUniqueText();

    page.getReplyCreatorMobile().click()
        .clickGuidelinesReadButton()
        .add(text)
        .clickSubmitButton();

    return text;
  }

  private boolean isReplyPresent(PostDetailsPage page, String text) {
    return page.getReplies()
        .waitForReplyToAppearWith(text)
        .isEmpty();
  }
}
