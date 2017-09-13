package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_3)
@Test(groups = {"discussions-creating-replies"})
public class CreatingReplyTests extends NewTestTemplate {

  private static final String REPLY_ADDED_MESSAGE = "Reply should appear below post.";
  private static final String POST_FOLLOWED_BY_DEFAULT = "Post should be followed by default when reply was created for post.";

  private static final String MOBILE = "discussions-creating-replies-mobile";
  private static final String DESKTOP = "discussions-creating-replies-desktop";

  // Anonymous on mobile

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotWriteNewReply() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    userOnMobileMustBeLoggedInToUseReplyCreator(page.getReplyCreatorMobile());
  }

  // Anonymous on desktop

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotWriteNewReply() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    userOnDesktopMustBeLoggedInToUseReplyCreator(page.getReplyCreatorDesktop());
  }

  // User on mobile

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanCreateReplyOnPostDetailsPage() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    final ReplyCreator replyCreator = page.getReplyCreatorMobile();

    assertThatUserCanCreateReply(page, replyCreator);
  }

  // User on desktop

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanCreateReplyOnPostDetailsPage() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    final ReplyCreator replyCreator = page.getReplyCreatorDesktop();

    assertThatUserCanCreateReply(page, replyCreator);

  }

  // Testing methods

  private void userOnDesktopMustBeLoggedInToUseReplyCreator(final ReplyCreator replyCreator) {
    anonymousUserOnReplyEditorClickIsRedirectedTo(replyCreator, MercurySubpages.REGISTER_PAGE);
  }

  private void userOnMobileMustBeLoggedInToUseReplyCreator(final ReplyCreator replyCreator) {
    anonymousUserOnReplyEditorClickIsRedirectedTo(replyCreator, MercurySubpages.JOIN_PAGE);
  }

  private void anonymousUserOnReplyEditorClickIsRedirectedTo(final ReplyCreator replyCreator, final String urlFragment) {
    Assertion.assertTrue(replyCreator.click().isModalDialogVisible());
    replyCreator.clickOkButtonInSignInDialog();
    Assertion.assertTrue(replyCreator.click().isModalDialogVisible());
    replyCreator.clickSignInButtonInSignInDialog();
    Assertion.assertTrue(driver.getCurrentUrl().contains(urlFragment));
  }

  private PostEntity.Data createPostAsUserRemotely() {
    return DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
  }

  private void assertThatUserCanCreateReply(PostDetailsPage page, ReplyCreator replyCreator) {
    replyCreator.click().clickGuidelinesReadButton();
    final String text = TextGenerator.createUniqueText();
    replyCreator.add(text).clickSubmitButton();
    page.getReplies().waitForReplyToAppearWith(text).refreshPage();
    Assertion.assertFalse(page.getReplies().isEmpty(), REPLY_ADDED_MESSAGE);
    Assertion.assertTrue(page.isPostFollowed(), POST_FOLLOWED_BY_DEFAULT);
  }

}
