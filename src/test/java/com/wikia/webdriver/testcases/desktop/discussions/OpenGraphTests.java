package com.wikia.webdriver.testcases.desktop.discussions;


import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.BasePostsCreator;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.BaseReplyCreator;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.desktop.ReplyCreatorDesktop;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.mobile.PostsCreatorMobile;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.mobile.ReplyCreatorMobile;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Test(groups = "discussions-opengraph")
@Execute(asUser = User.USER_6, onWikia = "qadiscussions", language = "de")
public class OpenGraphTests extends NewTestTemplate {

  private static final String URL = "http://fandom.com";
  private static final String URL_NO_PROTOCOL = "fandom.com";

  /**
   * fixture methods
   */

  private PostEntity.Data setUp() {
    String siteId = Utils.extractSiteIdFromWikiName("qadiscussions", "de");
    return DiscussionsClient.using(User.USER_6, driver).createPostWithUniqueData(siteId);
  }

  /**
   * test methods
   */

  @Test
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validLinkGeneratesOpenGraphItemInNewPostOnDesktop() throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    PostsCreatorDesktop postsCreator = page.getPostsCreatorDesktop();
    verifyOpenGraphInNewPost(page, postsCreator);
  }

  @Test
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validOpenGraphItemCanBeSubmittedInNewPostOnDesktop() throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    PostsCreatorDesktop postsCreator = page.getPostsCreatorDesktop();
    verifyOpenGraphWithLinkRemovedInNewPost(page, postsCreator);
  }

  @Test
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validLinkGeneratesOpenGraphItemInNewReplyOnDesktop() throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp().getId());
    ReplyCreatorDesktop replyCreator = page.getReplyCreatorDesktop();
    verifyOpenGraphInNewReply(page, replyCreator);
  }

  @Test
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validOpenGraphItemCanBeSubmittedInNewReplyOnDesktop() throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp().getId());
    ReplyCreatorDesktop replyCreator = page.getReplyCreatorDesktop();
    verifyOpenGraphWithLinkRemovedInNewReply(page, replyCreator);
  }

  @Test
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void validLinkGeneratesOpenGraphItemInNewPostOnMobile() throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    PostsCreatorMobile postsCreator = page.getPostsCreatorMobile();
    verifyOpenGraphInNewPost(page, postsCreator);
  }

  @Test
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void validOpenGraphItemCanBeSubmittedInNewPostOnMobile() throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    PostsCreatorMobile postsCreator = page.getPostsCreatorMobile();
    verifyOpenGraphWithLinkRemovedInNewPost(page, postsCreator);
  }

  @Test
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void validLinkGeneratesOpenGraphItemInNewReplyOnMobile() throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp().getId());
    ReplyCreatorMobile replyCreator = page.getReplyCreatorMobile();
    verifyOpenGraphInNewReply(page, replyCreator);
  }

  @Test
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void validOpenGraphItemCanBeSubmittedInNewReplyOnMobile() throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp().getId());
    ReplyCreatorMobile replyCreator = page.getReplyCreatorMobile();
    verifyOpenGraphWithLinkRemovedInNewReply(page, replyCreator);
  }

  /**
   * helper methods
   */

  private void verifyOpenGraphInNewPost(PostsListPage page, BasePostsCreator postsCreator)
    throws MalformedURLException {
    postsCreator.startPostCreationWithLink(new URL(URL));

    Assertion.assertTrue(postsCreator.hasOpenGraph());
    postsCreator.clickSubmitButton();
    page.waitForLoadingSpinner();
    page.getPost().waitForPostToAppearWithText(URL_NO_PROTOCOL);

    Assertion.assertTrue(page.getPost().findNewestPost().hasOpenGraph());
  }

  private void verifyOpenGraphWithLinkRemovedInNewPost(PostsListPage page, BasePostsCreator
      postsCreator)
      throws MalformedURLException {
    postsCreator.startPostCreationWithLink(new URL(URL));
    postsCreator.clearDescription();
    Assertion.assertTrue(postsCreator.hasOpenGraph());

    postsCreator.clickSubmitButton();
    page.waitForLoadingSpinner();
    page.getPost().waitForPostToAppearWithOpenGraph(URL_NO_PROTOCOL);

    Assertion.assertTrue(page.getPost().findNewestPost().hasOpenGraph());
  }

  private void verifyOpenGraphInNewReply(PostDetailsPage page, BaseReplyCreator replyCreator)
    throws MalformedURLException {
    replyCreator.startReplyCreationWithLink(new URL(URL));

    Assertion.assertTrue(replyCreator.hasOpenGraph());
    replyCreator.clickSubmitButton();
    page.waitForLoadingSpinner();
    page.getReplies().waitForReplyToAppearWithText(URL_NO_PROTOCOL);

    Assertion.assertTrue(page.getReplies().getNewestReply().hasOpenGraph());
  }

  private void verifyOpenGraphWithLinkRemovedInNewReply(PostDetailsPage page, BaseReplyCreator
      replyCreator)
      throws MalformedURLException {
    replyCreator.startReplyCreationWithLink(new URL(URL));
    replyCreator.clearText();

    Assertion.assertTrue(replyCreator.hasOpenGraph());
    replyCreator.clickSubmitButton();
    page.waitForLoadingSpinner();
    page.getReplies().waitForReplyToAppearWithOpenGraph(URL_NO_PROTOCOL);

    Assertion.assertTrue(page.getReplies().getNewestReply().hasOpenGraph());
  }

}
