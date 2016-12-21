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
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-locking-posts")
public class LockingPostTests extends NewTestTemplate {

  // Staff user on mobile

  @Test(groups = "discussions-staffUserMobileLocking")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanLockPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    PostEntity postEntity = page.getPost().findNewestPost();
    postEntity.clickMoreOptions().clickLockPostOption();
    Assertion.assertTrue(postEntity.isLocked(), "Staff should be able to lock the post.");
  }

  @Test(groups = "discussions-staffUserMobileLocking")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileCanUnlockPostLockedByStaffOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.STAFF, driver).lockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    PostEntity postEntity = page.getPost().findNewestPost();
    postEntity.clickMoreOptions().clickUnlockPostOption();
    Assertion.assertFalse(postEntity.isLocked(), "Staff should be able to unlock post locked by staff.");
  }


  // Discussions moderator on mobile

  @Test(groups = "discussions-discussionsModeratorMobileLocking")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsModeratorOnMobileCanNotAddReplyUnderLockedPostOnPostDetailsPage() {
    PostEntity.Data data = DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.STAFF, driver).lockPost(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertFalse(page.getReplyCreatorMobile().isPresent(), "Discussions moderator should not be able to create reply under post locked by staff.");
  }
}
