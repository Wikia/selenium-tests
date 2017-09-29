package com.wikia.webdriver.testcases.discussions;


import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.ReplyCreatorDesktop;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class OpengraphTests extends NewTestTemplate {

  private static final String URL = "http://fandom.wikia.com";

  /**
   * fixture methods
   */

  private PostEntity.Data setUp(String wikiName, User user) {
    String siteId = Utils.excractSiteIdFromWikiName(wikiName);
    return DiscussionsClient.using(user, driver).createPostWithUniqueData(siteId);
  }

  /**
   * test methods
   */

  @Test
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validLinkGeneratesOpengraphItemInNewPostOnDesktop() throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    PostsCreatorDesktop postsCreator = page.getPostsCreatorDesktop();
    postsCreator.click().closeGuidelinesMessage();
    postsCreator.addTitleWith(TextGenerator.defaultText()).addDescriptionWithLink(new URL(URL));
    Assertion.assertTrue(postsCreator.hasOpenGraph());
    postsCreator.clickAddCategoryButton().selectFirstCategory();
    postsCreator.clickSubmitButton();
    page.waitForPageReload();
    page.getPost().waitForPostToAppearWith(URL);
    Assertion.assertTrue(page.getPost().findNewestPost().hasOpenGraph());
  }

  @Test
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void validLinkGeneratesOpengraphItemInNewReplyOnDesktop() throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage()
      .open(setUp(MercuryWikis.DISCUSSIONS_2, User.USER).getId());
    ReplyCreatorDesktop replyCreator = page.getReplyCreatorDesktop();
    replyCreator.click().clickGuidelinesReadButton().addWithLink(new URL(URL));
    Assertion.assertTrue(replyCreator.hasOpenGraph());
    replyCreator.clickSubmitButton();
    page.waitForPageReload();
    page.getReplies().waitForReplyToAppearWith(URL);
    Assertion.assertTrue(page.getReplies().getNewestReply().hasOpengraph());

  }
}
