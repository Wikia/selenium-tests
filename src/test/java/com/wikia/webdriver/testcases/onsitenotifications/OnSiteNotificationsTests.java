package com.wikia.webdriver.testcases.onsitenotifications;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
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

  }

  private PostEntity.Data createPost() {
    return DiscussionsOperations.using(User.USER, driver).createPostWithUniqueData(siteId);
  }

}
