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
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class UploadingImageTests extends NewTestTemplate {

  private static final String URL = "http://fandom.wikia.com";

  private static final String DESKTOP = "discussions-uploading-image-desktop";
  private static final String MOBILE = "discussions-uploading-image-mobile";

  private static final String MOBILE_COMMUNITY = MercuryWikis.DISCUSSIONS_2;
  private static final String DESKTOP_COMMUNITY = MercuryWikis.DISCUSSIONS_3;

  private static final String UNSUPPORTED_IMAGE_MSG =
    "Invalid image type, please use jpeg, png or gif.";
  private static final String POST_IMAGE_VISIBLE = "Uploaded image should be visible in new post";
  private static final String POST_DELETED_IMAGE_NOT_VISIBLE =
    "Deleted image should not be visible in new post";
  private static final String POST_UNSUPPORTED_IMAGE_NOT_VISIBLE =
    "Unsupported image should not be visible in new post";
  private static final String POST_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE =
    "Opengraph image should not be visible in new post with uploaded image";
  private static final String REPLY_IMAGE_VISIBLE = "Uploaded image should be visible in new reply";
  private static final String REPLY_DELETED_IMAGE_NOT_VISIBLE =
    "Deleted image should not be visible in new reply";
  private static final String REPLY_UNSUPPORTED_IMAGE_NOT_VISIBLE =
    "Unsupported image should not be visible in new reply";
  private static final String REPLY_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE =
    "Opengraph image should not be visible in new reply with uploaded image";

  /**
   * fixture methods
   */

  private PostEntity.Data setUp(String wikiName) {
    String siteId = Utils.excractSiteIdFromWikiName(wikiName);
    return DiscussionsClient
      .using(User.USER_2, driver)
      .createPostWithUniqueData(siteId);
  }

  /**
   * test methods
   */

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  public void userCanUploadImageToTheirPostOnDesktop() {
    PostsListPage page = new PostsListPage().open();
    startPostCreationDesktop(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertTrue(page.getPost().firstPostHasImage(), POST_IMAGE_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  public void userCanUploadImageToTheirReplyOnDesktop() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(DESKTOP_COMMUNITY).getId());
    startReplyCreationDesktop(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertTrue(page.findNewestReply().hasImage(), REPLY_IMAGE_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  public void userCannotUploadUnsupportedImageToTheirPostOnDesktop() {
    PostsListPage page = new PostsListPage().open();
    addPostWithUnsupportedImage(startPostCreationDesktop(page));
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  public void userCannotUploadUnsupportedImageToTheirReplyOnDesktop() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(DESKTOP_COMMUNITY).getId());
    addReplyWithUnsupportedImage(startReplyCreationDesktop(page));
    page.waitForPageReload();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  public void userCanRemoveImagePreviewFromPostDraftOnDesktop() {
    PostsListPage page = new PostsListPage().open();
    startPostCreationDesktop(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  public void userCanRemoveImagePreviewFromReplyDraftOnDesktop() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(DESKTOP_COMMUNITY).getId());
    startReplyCreationDesktop(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanOverwriteOpenGraphImageInPostWithUploadedImageOnDesktop()
    throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    startPostCreationDesktopWithLink(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().firstPostHasOpenGraph(), POST_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanOverwriteOpenGraphImageInReplyWithUploadedImageOnDesktop()
    throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp(DESKTOP_COMMUNITY).getId());
    startReplyCreationDesktopWithLink(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.findNewestReply().hasOpenGraph(), REPLY_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE);
  }



  /**
   * mobile test methods
   */

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCanUploadImageToTheirPostOnMobile() {
    PostsListPage page = new PostsListPage().open();
    startPostCreationMobile(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertTrue(page.getPost().firstPostHasImage(), POST_IMAGE_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCanUploadImageTotheirReplyOnMobile() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(MOBILE_COMMUNITY).getId());
    startReplyCreationMobile(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertTrue(page.findNewestReply().hasImage(), REPLY_IMAGE_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCannotUploadUnsupportedImageToTheirPostOnMobile() {
    PostsListPage page = new PostsListPage().open();
    addPostWithUnsupportedImage(startPostCreationMobile(page));
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCannotUploadUnsupportedImageToTheirReplyOnMobile() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(MOBILE_COMMUNITY).getId());
    addReplyWithUnsupportedImage(startReplyCreationMobile(page));
    page.waitForPageReload();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCanRemoveImagePreviewFromPostDraftOnMobile() {
    PostsListPage page = new PostsListPage().open();
    startPostCreationMobile(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCanRemoveImagePreviewFromReplyDraftOnMobile() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(MOBILE_COMMUNITY).getId());
    startReplyCreationMobile(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCanOverwriteOpenGraphImageInExistingPostWithUploadedImageOnMobile()
    throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    startPostCreationMobileWithLink(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().firstPostHasOpenGraph());
  }



  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCanOverwriteOpenGraphImageInExistingReplyWithUploadedImageOnMobile()
    throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp(MOBILE_COMMUNITY).getId());
    startReplyCreationMobileWithLink(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.findNewestReply().hasOpenGraph(), REPLY_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE);
  }

  /**
   * helper methods
   */

  private ContributionEditor startPostCreationDesktop(PostsListPage page) {
    return page.getPostsCreatorDesktop().startPostCreation();
  }

  private ContributionEditor startPostCreationMobileWithLink(PostsListPage page)
    throws MalformedURLException {
    return page.getPostsCreatorMobile().startPostCreationWithLink(new URL(URL));
  }

  private ContributionEditor startPostCreationDesktopWithLink(PostsListPage page)
    throws MalformedURLException {
    return page.getPostsCreatorDesktop().startPostCreationWithLink(new URL(URL));
  }

  private BaseReplyCreator startReplyCreationDesktopWithLink(PostDetailsPage page)
    throws MalformedURLException {
    return page.getReplyCreatorDesktop().startReplyCreationWithLink(new URL(URL));
  }

  private BaseReplyCreator startReplyCreationMobileWithLink(PostDetailsPage page)
    throws MalformedURLException {
    return page.getReplyCreatorMobile().startReplyCreationWithLink(new URL(URL));
  }

  private ContributionEditor startPostCreationMobile(PostsListPage page) {
    return page.getPostsCreatorMobile().startPostCreation();
  }

  private BaseReplyCreator startReplyCreation(BaseReplyCreator replyCreator) {
    replyCreator.click()
      .closeGuidelinesMessage()
      .addTextWith(TextGenerator.defaultText());
    return replyCreator;
  }

  private BaseReplyCreator startReplyCreationDesktop(PostDetailsPage page) {
    return startReplyCreation(page.getReplyCreatorDesktop());
  }

  private BaseReplyCreator startReplyCreationMobile(PostDetailsPage page) {
    return startReplyCreation(page.getReplyCreatorMobile());
  }

  private void addPostWithUnsupportedImage(ContributionEditor postCreator) {
    String errorMsg = postCreator.uploadUnsupportedImage();
    Assertion.assertStringContains(errorMsg, UNSUPPORTED_IMAGE_MSG);
    postCreator.clickSubmitButton();
  }

  private void addReplyWithUnsupportedImage(BaseReplyCreator replyCreator) {
    String errorMsg = replyCreator.uploadUnsupportedImage();
    Assertion.assertStringContains(errorMsg, UNSUPPORTED_IMAGE_MSG);
    replyCreator.clickSubmitButton();
  }

}
