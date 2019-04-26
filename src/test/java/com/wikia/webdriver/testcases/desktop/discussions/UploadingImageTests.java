package com.wikia.webdriver.testcases.desktop.discussions;

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
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.*;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Execute(onWikia = "qadiscussions", language = "de")
public class UploadingImageTests extends NewTestTemplate {

  private static final String wikiName = "qadiscussions";
  private static final String language = "de";

  private static final String URL = "http://fandom.wikia.com";

  private static final String DESKTOP = "discussions-uploading-image-desktop";
  private static final String MOBILE = "discussions-uploading-image-mobile";

  private static final String
      UNSUPPORTED_IMAGE_MSG
      = "Ungültiger Bildtyp, verwende bitte jpeg, png oder gif.";
  private static final String POST_IMAGE_VISIBLE = "Uploaded image should be visible in new post";
  private static final String
      POST_DELETED_IMAGE_NOT_VISIBLE
      = "Deleted image should not be visible in new post";
  private static final String
      POST_UNSUPPORTED_IMAGE_NOT_VISIBLE
      = "Unsupported image should not be visible in new post";
  private static final String
      POST_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE
      = "Opengraph image should not be visible in new post with uploaded image";
  private static final String REPLY_IMAGE_VISIBLE = "Uploaded image should be visible in new reply";
  private static final String
      REPLY_DELETED_IMAGE_NOT_VISIBLE
      = "Deleted image should not be visible in new reply";
  private static final String
      REPLY_UNSUPPORTED_IMAGE_NOT_VISIBLE
      = "Unsupported image should not be visible in new reply";
  private static final String
      REPLY_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE
      = "Opengraph image should not be visible in new reply with uploaded image";

  /**
   * fixture methods
   */

  private PostEntity.Data setUp(String wikiName, String language) {
    String siteId = Utils.extractSiteIdFromWikiName(wikiName, language);
    return DiscussionsClient.using(User.USER_2, driver).createPostWithUniqueData(siteId);
  }

  /**
   * test methods
   */

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(asUser = User.USER_3)
  public void userCanUploadImageToTheirPostOnDesktop() {
    PostsListPage page = new PostsListPage().open();
    startEmptyPostCreationDesktop(page).uploadImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertTrue(page.getPost().firstPostHasImage(), POST_IMAGE_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(asUser = User.USER_3)
  public void userCanUploadImageToTheirReplyOnDesktop() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(wikiName, language).getId());
    startEmptyReplyCreationDesktop(page).uploadImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertTrue(page.findNewestReply().hasImage(), REPLY_IMAGE_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(asUser = User.USER_3)
  public void userCannotUploadUnsupportedImageToTheirPostOnDesktop() {
    PostsListPage page = new PostsListPage().open();
    addPostWithUnsupportedImage(startPostCreationDesktop(page));
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP, enabled = false)
  @RelatedIssue(issueID = "IRIS-6198")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(asUser = User.USER_3)
  public void userCannotUploadUnsupportedImageToTheirReplyOnDesktop() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(wikiName, language).getId());
    addReplyWithUnsupportedImage(startReplyCreationDesktop(page));
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(asUser = User.USER_3)
  public void userCanRemoveImagePreviewFromPostDraftOnDesktop() {
    PostsListPage page = new PostsListPage().open();
    startPostCreationDesktop(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP, enabled = false)
  @RelatedIssue(issueID = "IRIS-6198")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  @Execute(asUser = User.USER_3)
  public void userCanRemoveImagePreviewFromReplyDraftOnDesktop() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(wikiName, language).getId());
    startReplyCreationDesktop(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanOverwriteOpenGraphImageInPostWithUploadedImageOnDesktop()
      throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    startPostCreationDesktopWithLink(page).uploadImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.getPost().firstPostHasOpenGraph(),
                          POST_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE
    );
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userCanOverwriteOpenGraphImageInReplyWithUploadedImageOnDesktop()
      throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp(wikiName, language).getId());
    startReplyCreationDesktopWithLink(page).uploadImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.findNewestReply().hasOpenGraph(),
                          REPLY_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE
    );
  }

  /**
   * mobile test methods
   */

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.USER_2)
  public void userCanUploadImageToTheirPostOnMobile() {
    PostsListPage page = new PostsListPage().open();
    startEmptyPostCreationMobile(page).uploadImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertTrue(page.getPost().firstPostHasImage(), POST_IMAGE_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.USER_2)
  public void userCanUploadImageTotheirReplyOnMobile() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(wikiName, language).getId());
    startEmptyReplyCreationMobile(page).uploadImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertTrue(page.findNewestReply().hasImage(), REPLY_IMAGE_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.USER_2)
  public void userCannotUploadUnsupportedImageToTheirPostOnMobile() {
    PostsListPage page = new PostsListPage().open();
    addPostWithUnsupportedImage(startPostCreationMobile(page));
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.USER_2)
  public void userCannotUploadUnsupportedImageToTheirReplyOnMobile() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(wikiName, language).getId());
    addReplyWithUnsupportedImage(startReplyCreationMobile(page));
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_UNSUPPORTED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.USER_2)
  public void userCanRemoveImagePreviewFromPostDraftOnMobile() {
    PostsListPage page = new PostsListPage().open();
    startPostCreationMobile(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.getPost().firstPostHasImage(), POST_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.USER_2)
  public void userCanRemoveImagePreviewFromReplyDraftOnMobile() {
    PostDetailsPage page = new PostDetailsPage().open(setUp(wikiName, language).getId());
    startReplyCreationMobile(page).uploadImage().removeImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.findNewestReply().hasImage(), REPLY_DELETED_IMAGE_NOT_VISIBLE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.USER_2)
  public void userCanOverwriteOpenGraphImageInExistingPostWithUploadedImageOnMobile()
      throws MalformedURLException {
    PostsListPage page = new PostsListPage().open();
    startPostCreationMobileWithLink(page).uploadImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.getPost().firstPostHasOpenGraph());
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.USER_2)
  public void userCanOverwriteOpenGraphImageInExistingReplyWithUploadedImageOnMobile()
      throws MalformedURLException {
    PostDetailsPage page = new PostDetailsPage().open(setUp(wikiName, language).getId());
    startReplyCreationMobileWithLink(page).uploadImage().clickSubmitButton();
    page.waitForLoadingSpinner();
    Assertion.assertFalse(page.findNewestReply().hasOpenGraph(),
                          REPLY_OVERWRITTEN_OPENGRAPH_NOT_VISIBLE
    );
  }

  /**
   * helper methods
   */

  private BasePostsCreator startEmptyPostCreationDesktop(PostsListPage page) {
    return page.getPostsCreatorDesktop().startPostCreationWithoutDescription();
  }

  private BasePostsCreator startPostCreationDesktop(PostsListPage page) {
    return page.getPostsCreatorDesktop().startPostCreation();
  }

  private BasePostsCreator startPostCreationMobileWithLink(PostsListPage page)
      throws MalformedURLException {
    return page.getPostsCreatorMobile().startPostCreationWithLink(new URL(URL));
  }

  private BasePostsCreator startPostCreationDesktopWithLink(PostsListPage page)
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

  private BasePostsCreator startPostCreationMobile(PostsListPage page) {
    return page.getPostsCreatorMobile().startPostCreation();
  }

  private BasePostsCreator startEmptyPostCreationMobile(PostsListPage page) {
    return page.getPostsCreatorMobile().startPostCreationWithoutDescription();
  }

  private BaseReplyCreator startReplyCreation(BaseReplyCreator replyCreator) {
    replyCreator.click().clickGuidelinesReadButton().add(TextGenerator.defaultText());
    return replyCreator;
  }

  private BaseReplyCreator startEmptyReplyCreation(BaseReplyCreator replyCreator) {
    replyCreator.click().clickGuidelinesReadButton();
    return replyCreator;
  }

  private BaseReplyCreator startReplyCreationDesktop(PostDetailsPage page) {
    return startReplyCreation(page.getReplyCreatorDesktop());
  }

  private BaseReplyCreator startEmptyReplyCreationDesktop(PostDetailsPage page) {
    return startEmptyReplyCreation(page.getReplyCreatorDesktop());
  }

  private BaseReplyCreator startReplyCreationMobile(PostDetailsPage page) {
    return startReplyCreation(page.getReplyCreatorMobile());
  }

  private BaseReplyCreator startEmptyReplyCreationMobile(PostDetailsPage page) {
    return startEmptyReplyCreation(page.getReplyCreatorMobile());
  }

  private void addPostWithUnsupportedImage(BasePostsCreator postCreator) {
    String errorMsg = postCreator.uploadUnsupportedImage();
    Assertion.assertStringContains(errorMsg, UNSUPPORTED_IMAGE_MSG);
    postCreator.waitForErrorMessageNotVisible().clickSubmitButton();
  }

  private void addReplyWithUnsupportedImage(BaseReplyCreator replyCreator) {
    String errorMsg = replyCreator.uploadUnsupportedImage();
    Assertion.assertStringContains(errorMsg, UNSUPPORTED_IMAGE_MSG);
    replyCreator.waitForErrorMessageNotVisible().clickSubmitButton();
  }
}
