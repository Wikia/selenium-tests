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

  private static final String DESKTOP_RESOLUTION = "1920x1080";

  private static final String SHOULD_NOT_LOCK_MESSAGE = "%s should not be able to lock a post.";

  private static final String SHOULD_LOCK_MESSAGE = "%s should be able to lock the post.";

  private static final String SHOULD_UNLOCK_MESSAGE = "%s should be able to unlock post locked by %s.";

  private static final String SHOULD_NOT_ADD_REPLY_MESSAGE = "%s should not be able to create reply under post locked by %s.";

  private static final String SHOULD_ADD_REPLY_MESSAGE = "%s should be able to add reply to post unlocked by %s.";

  // Anonymous user on mobile

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-anonymousUserMobileLocking"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnPostDetailsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostDetailsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-anonymousUserMobileLocking"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnPostsListPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostsListPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-anonymousUserMobileLocking"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnUserPostsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnUserPostsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  // Anonymous user on desktop

  @Test(groups = {"discussions-locking-posts-desktop", "discussions-anonymousUserDesktopLocking"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotLockAPostOnPostDetailsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostDetailsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = {"discussions-locking-posts-desktop", "discussions-anonymousUserDesktopLocking"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotLockAPostOnPostsListPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostsListPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = {"discussions-locking-posts-desktop", "discussions-anonymousUserDesktopLocking"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotLockAPostOnUserPostsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnUserPostsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  // Second User on mobile

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-userMobileLocking"})
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnPostDetailsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostDetailsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-userMobileLocking"})
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnPostsListPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostsListPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-userMobileLocking"})
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnUserPostsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnUserPostsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  // Second User on desktop

  @Test(groups = {"discussions-locking-posts-desktop", "discussions-userDesktopLocking"})
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotLockAPostOnPostDetailsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostDetailsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = {"discussions-locking-posts-desktop", "discussions-userDesktopLocking"})
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotLockAPostOnPostsListPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostsListPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = {"discussions-locking-posts-desktop", "discussions-userDesktopLocking"})
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotLockAPostOnUserPostsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnUserPostsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  // User on mobile

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-userMobileLocking"})
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostDetailsPage page = lockPostAsDiscussionsModeratorAndOpenPostDetailsPage();

    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), message);
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-userMobileLocking"})
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReplyOnMobile(page);
    boolean actual = isReplyNotPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(actual, message);
  }

  // User on desktop

  @Test(groups = {"discussions-locking-posts-desktop", "discussions-userDesktopLocking"})
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostDetailsPage page = lockPostAsDiscussionsModeratorAndOpenPostDetailsPage();

    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), message);
  }

  @Test(groups = {"discussions-locking-posts-desktop", "discussions-userDesktopLocking"})
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReplyOnDesktop(page);
    boolean actual = isReplyNotPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(actual, message);
  }

  // Discussions Administrator on mobile

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-discussionsAdministratorMobileLocking"})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.DISCUSSIONS_ADMINISTRATOR.name()));
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-discussionsAdministratorMobileLocking"})
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

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-staffUserMobileLocking"})
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.STAFF.name()));
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-staffUserMobileLocking"})
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanUnlockPostLockedByStaffOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.STAFF, driver).lockPost(data);

    PostEntity postEntity = unlockPost(data);

    final String name = User.STAFF.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }


  @Test(groups = {"discussions-locking-posts-mobile", "discussions-staffUserMobileLocking"})
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).lockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());

    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.STAFF.name(), User.DISCUSSIONS_ADMINISTRATOR.name());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), message);
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-staffUserMobileLocking"})
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReplyOnMobile(page);
    boolean actual = isReplyNotPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.STAFF.name(), User.DISCUSSIONS_ADMINISTRATOR.name());
    Assertion.assertFalse(actual, message);
  }

  // Discussions moderator on mobile

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-discussionsModeratorMobileLocking"})
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.DISCUSSIONS_ADMINISTRATOR.name()));
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-discussionsModeratorMobileLocking"})
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanUnlockPostLockedByDiscussionsModeratorOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data);

    PostEntity postEntity = unlockPost(data);

    final String name = User.DISCUSSIONS_MODERATOR.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  @Test(groups = {"discussions-locking-posts-mobile", "discussions-discussionsModeratorMobileLocking"})
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.STAFF, driver).lockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());

    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.DISCUSSIONS_MODERATOR.name(), User.STAFF.name());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), message);
  }


  @Test(groups = {"discussions-locking-posts-mobile", "discussions-discussionsModeratorMobileLocking"})
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.STAFF, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReplyOnMobile(page);
    boolean actual = isReplyNotPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.DISCUSSIONS_MODERATOR.name(), User.STAFF.name());
    Assertion.assertFalse(actual, message);
  }

  private MoreOptionsPopOver findMoreOptionsOnPostDetailsPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PageWithPosts page = new PostDetailsPage().open(data.getId());

    return findMoreOptions(page);
  }

  private MoreOptionsPopOver findMoreOptions(final PageWithPosts page) {
    return page.getPost().findNewestPost().clickMoreOptions();
  }

  private MoreOptionsPopOver findMoreOptionsOnPostsListPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PageWithPosts page = new PostsListPage().open();

    return findMoreOptions(page, data);
  }

  private MoreOptionsPopOver findMoreOptions(final PageWithPosts page, final PostEntity.Data data) {
    return page.getPost().findPostById(data.getId()).clickMoreOptions();
  }

  private MoreOptionsPopOver findMoreOptionsOnUserPostsPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    final PageWithPosts page = new UserPostsPage().open(data.getAuthorId());

    return findMoreOptions(page, data);
  }

  private PostDetailsPage lockPostAsDiscussionsModeratorAndOpenPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data);

    return new PostDetailsPage().open(data.getId());
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

  private String addReplyOnMobile(PostDetailsPage page) {
    final String text = TextGenerator.createUniqueText();

    page.getReplyCreatorMobile().click()
        .clickGuidelinesReadButton()
        .add(text)
        .clickSubmitButton();

    return text;
  }

  private String addReplyOnDesktop(PostDetailsPage page) {
    final String text = TextGenerator.createUniqueText();

    page.getReplyCreatorDesktop().click()
        .clickGuidelinesReadButton()
        .add(text)
        .clickSubmitButton();

    return text;
  }

  private boolean isReplyNotPresent(PostDetailsPage page, String text) {
    return page.getReplies()
        .waitForReplyToAppearWith(text)
        .isEmpty();
  }
}
