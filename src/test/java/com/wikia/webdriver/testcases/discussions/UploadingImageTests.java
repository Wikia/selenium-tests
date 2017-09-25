package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.ReplyCreatorDesktop;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@Execute(onWikia = "sandbox-s3.dman", asUser = User.USER_2)
public class UploadingImageTests extends NewTestTemplate {

  private static final String DESKTOP = "uploading-image-desktop";
  private static final String MOBILE = "uploading-image-mobile";

  private PostEntity.Data setUp(String wikiName) {
    String siteId = Utils.excractSiteIdFromWikiName(wikiName);
    return DiscussionsClient
      .using(User.USER_11, driver)
      .createPostWithUniqueData(siteId);
  }

  @Test(groups = DESKTOP)
  public void userCanUploadImageToTheirPost() {
    PostsListPage page = new PostsListPage();
    startPostCreation(page).uploadFile().clickSubmitButton();

    page.waitForPageReload();

    Assertion.assertTrue(page.getPost().findNewestPost().hasImage());
  }

  @Test
  public void userCanUploadImageToTheirReply() {
    PostEntity.Data post = setUp("dman");
    PostDetailsPage page = new PostDetailsPage().open(post.getId());
    startReplyCreation(page).uploadFile().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertTrue(page.findNewestReply().hasImage());

  }

  private PostsCreatorDesktop startPostCreation(PostsListPage page) {
    PostsCreatorDesktop postCreator = page
      .open()
      .getPostsCreatorDesktop();
    postCreator
      .click()
      .closeGuidelinesMessage()
      .addTitleWith(TextGenerator.defaultText())
      .addDescriptionWith(TextGenerator.defaultText())
      .clickAddCategoryButton()
      .findCategoryOn(0)
      .click();
    return postCreator;
  }

  private ReplyCreatorDesktop startReplyCreation(PostDetailsPage page) {
    ReplyCreatorDesktop replyCreator = page.getReplyCreatorDesktop();
      replyCreator.click()
      .clickGuidelinesReadButton()
      .add(TextGenerator.defaultText());
    return replyCreator;
  }

}
