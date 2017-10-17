package com.wikia.webdriver.testcases.discussions;


import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.ContributionEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.BaseReplyCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostEditorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.ReplyCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostEditorMobile;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.ReplyCreatorMobile;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Test(groups = "discussions-opengraph")
@Execute(asUser = User.USER_6, onWikia = MercuryWikis.DISCUSSIONS_2)
public class OpenGraphTests extends NewTestTemplate {

  private static final String URL = "http://fandom.wikia.com";

  /**
   * fixture methods
   */

  private PostEntity.Data setUp() {
    String siteId = Utils.excractSiteIdFromWikiName(MercuryWikis.DISCUSSIONS_2);
    return DiscussionsClient.using(User.USER_6, driver).createPostWithUniqueData(siteId);
  }

  /**
   * test methods
   */

  @Test
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validLinkGeneratesOpenGraphItemInNewPostOnDesktop() throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    PostEditorDesktop postsCreator = page.getPostsCreatorDesktop();
    verifyOpenGraphInNewPost(page, postsCreator);
  }

  @Test
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validLinkGeneratesOpenGraphItemInNewReplyOnDesktop() throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp().getId());
    ReplyCreatorDesktop replyCreator = page.getReplyCreatorDesktop();
    verifyOpenGraphInNewReply(page, replyCreator);
  }

  @Test
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void validLinkGeneratesOpenGraphItemInNewPostOnMobile() throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    PostEditorMobile postsCreator = page.getPostsCreatorMobile();
    verifyOpenGraphInNewPost(page, postsCreator);
  }

  @Test
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void validLinkGeneratesOpenGraphItemInNewReplyOnMobile() throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp().getId());
    ReplyCreatorMobile replyCreator = page.getReplyCreatorMobile();
    verifyOpenGraphInNewReply(page, replyCreator);
  }

  /**
   * helper methods
   */

  private void verifyOpenGraphInNewPost(PostsListPage page, ContributionEditor postsCreator)
    throws MalformedURLException {
    postsCreator.startPostCreationWithLink(new URL(URL));

    Assertion.assertTrue(postsCreator.hasOpenGraph());
    postsCreator.clickSubmitButton();
    page.waitForPageReload();
    page.getPost().waitForPostToAppearWith(URL);

    Assertion.assertTrue(page.getPost().findNewestPost().hasOpenGraph());
  }

  private void verifyOpenGraphInNewReply(PostDetailsPage page, BaseReplyCreator replyCreator)
    throws MalformedURLException {
    replyCreator.startReplyCreationWithLink(new URL(URL));

    Assertion.assertTrue(replyCreator.hasOpenGraph());
    replyCreator.clickSubmitButton();
    page.waitForPageReload();
    page.getReplies().waitForReplyToAppearWith(URL);

    Assertion.assertTrue(page.getReplies().getNewestReply().hasOpenGraph());
  }

}
