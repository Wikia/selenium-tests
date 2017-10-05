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
import com.wikia.webdriver.elements.mercury.components.discussions.common.MoreOptionsPopOver;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.*;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_5)
public class LockingPostTests extends NewTestTemplate {

  private static final String SHOULD_BE_LOCKED_MESSAGE = "Post should be locked.";
  private static final String SHOULD_NOT_LOCK_MESSAGE = "%s should not be able to lock a post.";
  private static final String SHOULD_LOCK_MESSAGE = "%s should be able to lock the post.";
  private static final String SHOULD_UNLOCK_MESSAGE =
    "%s should be able to unlock post locked by %s.";
  private static final String SHOULD_NOT_ADD_REPLY_MESSAGE =
    "%s should not be able to create reply under post locked by %s.";
  private static final String SHOULD_ADD_REPLY_MESSAGE =
    "%s should be able to add reply to post unlocked by %s.";

  private static final String MOBILE = "discussions-locking-posts-mobile";
  private static final String DESKTOP = "discussions-locking-posts-desktop";


  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnPostDetailsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostDetailsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnPostsListPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostsListPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotLockAPostOnUserPostsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnUserPostsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonymousUserOnDesktopCanNotLockAPostOnPostDetailsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostDetailsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonymousUserOnDesktopCanNotLockAPostOnPostsListPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostsListPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonymousUserOnDesktopCanNotLockAPostOnUserPostsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnUserPostsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.ANONYMOUS.name()));
  }


  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnPostDetailsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostDetailsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnPostsListPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostsListPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotLockAPostOnUserPostsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnUserPostsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotLockAPostOnPostDetailsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostDetailsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotLockAPostOnPostsListPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnPostsListPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotLockAPostOnUserPostsPage() {
    final MoreOptionsPopOver moreOptionsPopOver = findMoreOptionsOnUserPostsPage();

    Assertion.assertFalse(moreOptionsPopOver.hasLockPostOption(), String.format(SHOULD_NOT_LOCK_MESSAGE, User.USER_2.name()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostDetailsPage page = lockPostAsDiscussionsModeratorAndOpenPostDetailsPage();

    Assertion.assertTrue(page.getPost().findNewestPost().isLocked(), SHOULD_BE_LOCKED_MESSAGE);
    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), message);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    DiscussionsClient.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReplyOnMobile(page);
    boolean actual = isReplyNotPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(actual, message);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostDetailsPage page = lockPostAsDiscussionsModeratorAndOpenPostDetailsPage();

    Assertion.assertTrue(page.getPost().findNewestPost().isLocked(), SHOULD_BE_LOCKED_MESSAGE);
    final String message = String.format(SHOULD_NOT_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(page.getReplyCreatorDesktop().isPresent(), message);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanAddReplyUnderUnlockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    DiscussionsClient.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data)
        .unlockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final String text = addReplyOnDesktop(page);
    boolean actual = isReplyNotPresent(page, text);

    final String message = String.format(SHOULD_ADD_REPLY_MESSAGE, User.USER.name(), User.DISCUSSIONS_MODERATOR.name());
    Assertion.assertFalse(actual, message);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.DISCUSSIONS_ADMINISTRATOR.name()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanUnlockPostOnPostDetailsPage() {
    PostEntity postEntity = unlockPostLockedByDiscussionsAdministrator();

    final String name = User.DISCUSSIONS_ADMINISTRATOR.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsAdministratorOnDesktopCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.DISCUSSIONS_ADMINISTRATOR.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsAdministratorOnDesktopCanUnlockPostOnPostDetailsPage() {
    PostEntity postEntity = unlockPostLockedByDiscussionsAdministrator();

    final String name = User.DISCUSSIONS_ADMINISTRATOR.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.STAFF.name()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanUnlockPostOnPostDetailsPage() {
    PostEntity postEntity = unlockPostLockedByStaff();

    final String name = User.STAFF.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserOnDesktopCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.STAFF.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserOnDesktopCanUnlockPostOnPostDetailsPage() {
    PostEntity postEntity = unlockPostLockedByStaff();

    final String name = User.STAFF.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.DISCUSSIONS_ADMINISTRATOR.name()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanUnlockPostOnPostDetailsPage() {
    PostEntity postEntity = unlockPostLockedByDiscussionsModerator();

    final String name = User.DISCUSSIONS_MODERATOR.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsModeratorOnDesktopCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();

    PostEntity postEntity = lockPost(data);

    Assertion.assertTrue(postEntity.isLocked(), String.format(SHOULD_LOCK_MESSAGE, User.DISCUSSIONS_ADMINISTRATOR.name()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsModeratorOnDesktopCanUnlockPostOnPostDetailsPage() {
    PostEntity postEntity = unlockPostLockedByDiscussionsModerator();

    final String name = User.DISCUSSIONS_MODERATOR.name();
    Assertion.assertFalse(postEntity.isLocked(), String.format(SHOULD_UNLOCK_MESSAGE, name, name));
  }

  private MoreOptionsPopOver findMoreOptionsOnPostDetailsPage() {
    final PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    final PageWithPosts page = new PostDetailsPage().open(data.getId());

    return findMoreOptions(page);
  }

  private MoreOptionsPopOver findMoreOptions(final PageWithPosts page) {
    return page.getPost().findNewestPost().clickMoreOptions();
  }

  private MoreOptionsPopOver findMoreOptionsOnPostsListPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    final PageWithPosts page = new PostsListPage().open();

    return findMoreOptions(page, data);
  }

  private MoreOptionsPopOver findMoreOptions(final PageWithPosts page, final PostEntity.Data data) {
    return page.getPost().findPostById(data.getId()).clickMoreOptions();
  }

  private MoreOptionsPopOver findMoreOptionsOnUserPostsPage() {
    final PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    final PageWithPosts page = new UserPostsPage().open(data.getAuthorId());

    return findMoreOptions(page, data);
  }

  private PostDetailsPage lockPostAsDiscussionsModeratorAndOpenPostDetailsPage() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    DiscussionsClient.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data);

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

  private PostEntity unlockPostLockedByDiscussionsAdministrator() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    DiscussionsClient.using(User.DISCUSSIONS_ADMINISTRATOR, driver).lockPost(data);

    return unlockPost(data);
  }

  private PostEntity unlockPostLockedByStaff() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    DiscussionsClient.using(User.STAFF, driver).lockPost(data);

    return unlockPost(data);
  }

  private PostEntity unlockPostLockedByDiscussionsModerator() {
    PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
    DiscussionsClient.using(User.DISCUSSIONS_MODERATOR, driver).lockPost(data);

    return unlockPost(data);
  }

  private boolean isReplyNotPresent(PostDetailsPage page, String text) {
    return page.getReplies()
        .waitForReplyToAppearWith(text)
        .isEmpty();
  }
}
