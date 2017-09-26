package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.BasePostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.BaseReplyCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

public class UploadingImageTests extends NewTestTemplate {

  private static final String DESKTOP = "uploading-image-desktop";
  private static final String MOBILE = "uploading-image-mobile";

  private static final String MOBILE_COMMUNITY = "sandbox-s3.dman";
  private static final String DESKTOP_COMMUNITY = "sandbox-s3.dman";

  private static final String UNSUPPORTED_IMAGE_MSG =
    "Invalid image type, please use jpeg, png or gif.";
  private static final String POST_IMAGE_VISIBLE = "Uploaded image should be visible in new post";
  private static final String POST_DELETED_IMAGE_NOT_VISIBLE =
    "Deleted image should not be visible in new post";
  private static final String POST_UNSUPPORTED_IMAGE_NOT_VISIBLE =
    "Unsupported image should not be visible in new post";
  private static final String REPLY_IMAGE_VISIBLE = "Uploaded image should be visible in new reply";
  private static final String REPLY_DELETED_IMAGE_NOT_VISIBLE =
    "Deleted image should not be visible in new reply";
  private static final String REPLY_UNSUPPORTED_IMAGE_NOT_VISIBLE =
    "Unsupported image should not be visible in new reply";

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
    PostDetailsPage page = new PostDetailsPage().open(setUp(MOBILE_COMMUNITY).getId());
    startReplyCreationDesktop(page).uploadImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertTrue(page.findNewestReply().hasImage(), REPLY_IMAGE_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  public void userCannotUploadUnsupportedImageToTheirPostOnDesktop() {
    PostsListPage page = new PostsListPage().open();
    BasePostsCreator postCreator = startPostCreationDesktop(page);
    String errorMsg = postCreator.uploadUnsupportedImage();
    Assertion.assertStringContains(errorMsg, UNSUPPORTED_IMAGE_MSG);
    postCreator.clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  public void userCannotUploadUnsupportedImageToTheirReplyOnDesktop() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(MOBILE_COMMUNITY).getId());
    BaseReplyCreator replyCreator = startReplyCreationDesktop(page);
    String errorMsg = replyCreator.uploadUnsupportedImage();
    Assertion.assertStringContains(errorMsg, UNSUPPORTED_IMAGE_MSG);
    replyCreator.clickSubmitButton();
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
    PostDetailsPage page = new PostDetailsPage().open(setUp(MOBILE_COMMUNITY).getId());
    startReplyCreationDesktop(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP, enabled = false)
  @RelatedIssue(issueID = "IRIS-4896", comment = "To be implemented")
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanOverwriteOpenGraphImageInExistingPostWithUploadedImageOnDesktop() {

  }

  @Test(groups = DESKTOP, enabled = false)
  @RelatedIssue(issueID = "IRIS-4896", comment = "To be implemented")
  @Execute(onWikia = DESKTOP_COMMUNITY, asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanOverwriteOpenGraphImageInExistingReplyWithUploadedImageOnDesktop() {

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
    BasePostsCreator postCreator = startPostCreationMobile(page);
    String errorMsg = postCreator.uploadUnsupportedImage();
    Assertion.assertStringContains(errorMsg, UNSUPPORTED_IMAGE_MSG);
    postCreator.clickSubmitButton();
    page.waitForPageReload();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCannotUploadUnsupportedImageToTheirReplyOnMobile() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(MOBILE_COMMUNITY).getId());
    BaseReplyCreator replyCreator = startReplyCreationMobile(page);
    String errorMsg = replyCreator.uploadUnsupportedImage();
    Assertion.assertStringContains(errorMsg, UNSUPPORTED_IMAGE_MSG);
    replyCreator.clickSubmitButton();
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

  @Test(groups = MOBILE, enabled = false)
  @RelatedIssue(issueID = "IRIS-4896", comment = "To be implemented")
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCanOverwriteOpenGraphImageInExistingPostWithUploadedImageOnMobile() {

  }

  @Test(groups = MOBILE, enabled = false)
  @RelatedIssue(issueID = "IRIS-4896", comment = "To be implemented")
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(onWikia = MOBILE_COMMUNITY, asUser = User.USER_2)
  public void userCanOverwriteOpenGraphImageInExistingReplyWithUploadedImageOnMobile() {

  }

  /**
   * helper methods
   */

  private BasePostsCreator startPostCreation(BasePostsCreator postCreator) {
    postCreator
      .click()
      .closeGuidelinesMessage()
      .addTitleWith(TextGenerator.defaultText())
      .addDescriptionWith(TextGenerator.defaultText())
      .clickAddCategoryButton()
      .selectFirstCategory();
    return postCreator;
  }

  private BasePostsCreator startPostCreationDesktop(PostsListPage page) {
    return startPostCreation(page.getPostsCreatorDesktop());
  }

  private BasePostsCreator startPostCreationMobile(PostsListPage page) {
    return startPostCreation(page.getPostsCreatorMobile());
  }

  private BaseReplyCreator startReplyCreation(BaseReplyCreator replyCreator) {
    replyCreator.click()
      .clickGuidelinesReadButton()
      .add(TextGenerator.defaultText());
    return replyCreator;
  }

  private BaseReplyCreator startReplyCreationDesktop(PostDetailsPage page) {
    return startReplyCreation(page.getReplyCreatorDesktop());
  }

  private BaseReplyCreator startReplyCreationMobile(PostDetailsPage page) {
    return startReplyCreation(page.getReplyCreatorMobile());
  }

}
