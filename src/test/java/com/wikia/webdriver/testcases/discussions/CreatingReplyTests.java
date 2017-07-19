package com.wikia.webdriver.testcases.discussions;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostActionsRow;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_3)
@Test(groups = {"discussions-creating-replies"})
public class CreatingReplyTests extends NewTestTemplate {

  private static final String SUBMIT_BUTTON_INACTIVE_MESSAGE = "Submit button should be inactive when no text was typed.";
  private static final String SUBMIT_BUTTON_ACTIVE_MESSAGE = "Submit button should active after typing text.";
  private static final String REPLY_ADDED_MESSAGE = "Reply should appear below post.";
  private static final String POST_FOLLOWED_BY_DEFAULT = "Post should be followed by default when reply was created for post.";
  private static final String SHOULD_UNFOLLOW_MESSAGE = "User should be able to unfollow post followed by default.";

  private static final String MOBILE = "discussions-creating-replies-mobile";
  private static final String DESKTOP = "discussions-creating-replies-desktop";

  // Anonymous on mobile

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void anonUserOnMobileCanNotWriteNewReply() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    userOnMobileMustBeLoggedInToUseReplyCreator(page.getReplyCreatorMobile());
  }

  // Anonymous on desktop

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotWriteNewReply() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    userOnDesktopMustBeLoggedInToUseReplyCreator(page.getReplyCreatorDesktop());
  }

  // User on mobile

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void userOnMobileCanCreateReplyOnPostDetailsPage() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    final ReplyCreator replyCreator = page.getReplyCreatorMobile();

    assertThatUserCanCreateReply(page, replyCreator);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void userOnMobileShouldFollowPostWhenAddedReplyOnPostDetailsPage() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    final ReplyCreator replyCreator = page.getReplyCreatorMobile();

    assertThatPostIsFollowedWhenReplyAdded(page, replyCreator);
    assertThatPostCanBeUnfollowed(page);
  }

  // User on desktop

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void replyEditorExpandsForUserOnDesktopOnPostDetailsPage() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    final ReplyCreator replyCreator = page.getReplyCreatorDesktop();

    final int originalEditorHeight = replyCreator.getEditorHeight();
    replyCreator.click().clickGuidelinesReadButton();
    int expandedEditorHeight = replyCreator.getEditorHeight();
    Assertion.assertTrue(expandedEditorHeight > originalEditorHeight, "Reply editor should expand.");
    Assertion.assertFalse(replyCreator.isSubmitButtonActive(), SUBMIT_BUTTON_INACTIVE_MESSAGE);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanCreateReplyOnPostDetailsPage() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    final ReplyCreator replyCreator = page.getReplyCreatorDesktop();

    assertThatUserCanCreateReply(page, replyCreator);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopShouldFollowPostWhenAddedReplyOnPostDetailsPage() {
    final PostDetailsPage page = new PostDetailsPage().open(createPostAsUserRemotely().getId());
    final ReplyCreator replyCreator = page.getReplyCreatorDesktop();

    assertThatPostIsFollowedWhenReplyAdded(page, replyCreator);
    assertThatPostCanBeUnfollowed(page);
  }

  // Testing methods

  private void userOnDesktopMustBeLoggedInToUseReplyCreator(final ReplyCreator replyCreator) {
    anonymousUserOnReplyEditorClickIsRedirectedTo(replyCreator, MercurySubpages.REGISTER_PAGE);
  }

  private void anonymousUserOnReplyEditorClickIsRedirectedTo(final ReplyCreator replyCreator, final String urlFragment) {
    Assertion.assertTrue(replyCreator.click().isModalDialogVisible());

    replyCreator.clickOkButtonInSignInDialog();
    Assertion.assertTrue(replyCreator.click().isModalDialogVisible());

    replyCreator.clickSignInButtonInSignInDialog();
    Assertion.assertTrue(driver.getCurrentUrl().contains(urlFragment));
  }

  private void userOnMobileMustBeLoggedInToUseReplyCreator(final ReplyCreator replyCreator) {
    anonymousUserOnReplyEditorClickIsRedirectedTo(replyCreator, MercurySubpages.JOIN_PAGE);
  }

  private PostEntity.Data createPostAsUserRemotely() {
    return DiscussionsOperations.using(User.USER, driver).createPostWithUniqueData();
  }

  private void assertThatUserCanCreateReply(PostDetailsPage page, ReplyCreator replyCreator) {
    replyCreator.click().clickGuidelinesReadButton();
    Assertion.assertFalse(replyCreator.isSubmitButtonActive(), SUBMIT_BUTTON_INACTIVE_MESSAGE);

    replyCreator.add(TextGenerator.createUniqueText());
    Assertion.assertTrue(replyCreator.isSubmitButtonActive(), SUBMIT_BUTTON_ACTIVE_MESSAGE);

    replyCreator.clearText();
    Assertion.assertFalse(replyCreator.isSubmitButtonActive(), SUBMIT_BUTTON_INACTIVE_MESSAGE);

    final String text = TextGenerator.createUniqueText();
    replyCreator.add(text).clickSubmitButton();
    page.getReplies().waitForReplyToAppearWith(text);
    Assertion.assertFalse(page.getReplies().isEmpty(), REPLY_ADDED_MESSAGE);
  }

  private void assertThatPostIsFollowedWhenReplyAdded(PostDetailsPage page, ReplyCreator replyCreator) {
    final String text = TextGenerator.createUniqueText();
    replyCreator.click()
        .clickGuidelinesReadButton()
        .add(text)
        .clickSubmitButton();
    page.getReplies().waitForReplyToAppearWith(text);

    final PostActionsRow postActions = page.getPost().findNewestPost().findPostActions();
    Assertion.assertTrue(postActions.isFollowed(), POST_FOLLOWED_BY_DEFAULT);
  }

  private void assertThatPostCanBeUnfollowed(PostDetailsPage page) {
    final PostActionsRow postActions = page.getPost().findNewestPost().findPostActions();
    postActions.clickFollow();
    Assertion.assertFalse(postActions.isFollowed(), SHOULD_UNFOLLOW_MESSAGE);
  }
}
