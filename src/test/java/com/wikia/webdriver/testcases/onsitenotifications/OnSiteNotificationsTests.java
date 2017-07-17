package com.wikia.webdriver.testcases.onsitenotifications;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.NotificationFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = "on-site-notifications")
public class OnSiteNotificationsTests extends NewTestTemplate {

  private String siteId;
  private static final String wikiName = MercuryWikis.DISCUSSIONS_3;

  @BeforeClass
  public void setUp() {
    siteId = Utils.excractSiteIdFromWikiName(wikiName);
  }

  @Execute(asUser = User.USER)
  public void userOnDesktopReceivesPostReplyNotification() {
    // fixture
    PostEntity.Data post = createPostAs(User.USER);
    ReplyEntity.Data reply = createReplyToPostAs(post, User.USER_2);
    Notification noti = new NotificationFactory().getPostReplyNotification(User.USER_2, post);
    // when
    PostsListPage discussionPage = new PostsListPage().open(siteId);
    // then
    Assertion.assertNotNull(discussionPage.openNotificationsMenu().findNotification(noti));
  }

  private PostEntity.Data createPostAs(User user) {
    return DiscussionsOperations.using(user, driver).createPostWithUniqueData(siteId);
  }

  private ReplyEntity.Data createReplyToPostAs(PostEntity.Data post, User user) {
    return DiscussionsOperations.using(user, driver).createReplyToPost(siteId, post);
  }

}
