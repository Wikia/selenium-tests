package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.*;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

public class PollsTests extends NewTestTemplate {

    /* TODO: Use fixtures to create Poll post */

    public static final int DEFAULT_ANSWERS_NUMBER = 2;
    public static final String ALREADY_VOTED_POST_ID = "3100000000000020087";

    /**
     * DESKTOP TESTS SECTION
     */

    @Test(groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_6, onWikia = MobileWikis.DISCUSSIONS_2)
    public void userCanCreatePostWithSimplePollOnDesktop() {
        PostsListPage page = new PostsListPage().open();
        BasePostsCreator postsCreator = page.getPostsCreatorDesktop();
        postsCreator.click().closeGuidelinesMessage().clickAddCategoryButton().selectFirstCategory();
        postsCreator.addTitleWith(TextGenerator.createUniqueText());
        Poll poll = postsCreator.addPoll();
//        Assertion.assertFalse(postsCreator.isPostButtonActive()); - this check commented out because of IRIS-5878

        poll.addTitle(TextGenerator.createUniqueText());
//        Assertion.assertFalse(postsCreator.isPostButtonActive()); - this check commented out because of IRIS-5878
        poll.addNthAnswer(TextGenerator.createUniqueText(), 0);
//        Assertion.assertFalse(postsCreator.isPostButtonActive()); - this check commented out because of IRIS-5878
        poll.addNthAnswer(TextGenerator.createUniqueText(), 1);
        postsCreator.clickSubmitButton();
        page.waitForLoadingSpinner();

        Assertion.assertTrue(new PostsListPage().getPost().firstPostHasPoll());
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnDesktop"}, groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.ANONYMOUS, onWikia = MobileWikis.DISCUSSIONS_2)
    public void anonUserCanNotVoteInPollOnDesktop() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        manageSignInModal(poll);
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnDesktop"}, groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_3, onWikia = MobileWikis.DISCUSSIONS_2)
    public void loggedInUserCanVoteOnceInPollOnDesktop() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        poll.clickNthAnswer(0);
        poll.clickVoteButton();

        Assertion.assertTrue(poll.isChosenResultBarDisplayed());
        Assertion.assertTrue(poll.getBarResultsList().size() > 0);
        Assertion.assertTrue(poll.isShareButtonDisplayed());
    }

    @Test(groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_6, onWikia = MobileWikis.DISCUSSIONS_2)
    public void userCanCreateComplexPollOnDesktop() {
        PostsListPage page = new PostsListPage().open();
        BasePostsCreator postsCreator = page.getPostsCreatorDesktop();
        postsCreator.click().closeGuidelinesMessage().clickAddCategoryButton().selectFirstCategory();
        postsCreator.addTitleWith(TextGenerator.createUniqueText());
        Poll poll = postsCreator.addPoll();
        Assertion.assertEquals(poll.getAnswersInputsList().size(), DEFAULT_ANSWERS_NUMBER);

        poll.addTitle(TextGenerator.createUniqueText());
        poll.addNthAnswer(TextGenerator.createUniqueText(), 0);
        poll.deletePoll();
        postsCreator.addPoll();
        Assertion.assertEquals(poll.getAnswersInputsList().size(), DEFAULT_ANSWERS_NUMBER);

        poll.addTitle(TextGenerator.createUniqueText());
        poll.addNthAnswer(TextGenerator.createUniqueText(), 0);
        poll.addNthAnswer(TextGenerator.createUniqueText(), 1);
        poll.clickAddAnswerButton();
        poll.addNthAnswer(TextGenerator.createUniqueText(), 2);
        poll.clickAddAnswerButton();
        poll.addNthAnswer(TextGenerator.createUniqueText(), 3);
        Assertion.assertEquals(poll.getAnswersInputsList().size(), DEFAULT_ANSWERS_NUMBER + 2);

        poll.deleteNthAnswer(0);
        Assertion.assertEquals(poll.getAnswersInputsList().size(), DEFAULT_ANSWERS_NUMBER + 1);

        postsCreator.clickSubmitButton();
        page.waitForLoadingSpinner();
        Assertion.assertTrue(new PostsListPage().getPost().firstPostHasPoll());
    }

    @Test(groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_6, onWikia = MobileWikis.DISCUSSIONS_2)
    public void userWhoVotedCanSeeVotersListOnDesktop() {
        PostDetailsPage postDetails = new PostDetailsPage();
        postDetails.open(ALREADY_VOTED_POST_ID);
        Poll poll = postDetails.getPoll();
        postDetails.clickFollowingTooltip();

        clickNthAnswersVotersModal(poll, postDetails,0);
        clickNthAnswersVotersModal(poll, postDetails,1);
        verifyAllVotersModal(poll, postDetails);
    }

    /**
     * MOBILE TESTS SECTION
     */

    @Test(groups = "discussions-polls")
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.USER_6, onWikia = MobileWikis.DISCUSSIONS_2)
    public void userCanCreatePostWithSimplePollOnMobile() {
        PostsListPage page = new PostsListPage().open();
        BasePostsCreator postsCreator = page.open().getPostsCreatorMobile();
        postsCreator.click().closeGuidelinesMessage().clickAddCategoryButton().selectFirstCategory();
        postsCreator.addTitleWith(TextGenerator.createUniqueText());
        Poll poll = postsCreator.addPoll();
//        Assertion.assertFalse(postsCreator.isPostButtonActive()); - this check commented out because of IRIS-5878

        poll.addTitle(TextGenerator.createUniqueText());
//        Assertion.assertFalse(postsCreator.isPostButtonActive()); - this check commented out because of IRIS-5878
        poll.addNthAnswer(TextGenerator.createUniqueText(), 0);
//        Assertion.assertFalse(postsCreator.isPostButtonActive()); - this check commented out because of IRIS-5878
        poll.addNthAnswer(TextGenerator.createUniqueText(), 1);
        postsCreator.clickSubmitButton();
        page.waitForLoadingSpinner();

        Assertion.assertTrue(new PostsListPage().getPost().firstPostHasPoll());
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnMobile"}, groups = "discussions-polls")
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.ANONYMOUS, onWikia = MobileWikis.DISCUSSIONS_2)
    public void anonUserCanNotVoteInPollOnMobile() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        manageSignInModal(poll);
    }

    @Test()//dependsOnMethods = {"userCanCreatePostWithSimplePollOnMobile"}, groups = "discussions-polls")
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.USER_3, onWikia = MobileWikis.DISCUSSIONS_2)
    public void loggedInUserCanVoteOnceInPollOnMobile() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        poll.clickVoteButton();

        Assertion.assertTrue(poll.isChosenResultBarDisplayed());
        Assertion.assertTrue(poll.getBarResultsList().size() > 0);
        Assertion.assertTrue(poll.isShareButtonDisplayed());
    }

    @Test(groups = "discussions-polls")
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.USER_6, onWikia = MobileWikis.DISCUSSIONS_2)
    public void userWhoVotedCanSeeVotersListOnMobile() {
        PostDetailsPage postDetails = new PostDetailsPage();
        postDetails.open(ALREADY_VOTED_POST_ID);
        Poll poll = postDetails.getPoll();
        postDetails.clickFollowingTooltip();

        clickNthAnswersVotersModal(poll, postDetails,0);
        clickNthAnswersVotersModal(poll, postDetails,1);
        verifyAllVotersModal(poll, postDetails);
    }

    /**
     * TESTING METHODS SECTION
     */

    public void manageSignInModal(Poll p) {
        SignInToFollowModalDialog signInModal = new SignInToFollowModalDialog();
        Assertion.assertTrue(signInModal.isVisible());
        signInModal.clickOkButton();
        p.clickNthAnswer(0);
        Assertion.assertTrue(signInModal.isVisible());
    }

    public void clickNthAnswersVotersModal(Poll poll, PostDetailsPage postDetails, int n) {
        poll.clickOnNthAnswerResult(n);
        postDetails.waitForLoadingSpinner();
        Assertion.assertTrue(poll.isVotersListDisplayed());
        Assertion.assertTrue(poll.getVotersNumber() > 0);
        poll.closeVotersListModal();
    }

    public void verifyAllVotersModal(Poll poll, PostDetailsPage postDetails) {
        poll.clickOnPollVotesIcon();
        postDetails.waitForLoadingSpinner();
        Assertion.assertTrue(poll.isVotersListDisplayed());
        Assertion.assertTrue(poll.getVotersNumber() > 0);
    }

}
