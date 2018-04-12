package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.BasePostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Poll;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PollsTests extends NewTestTemplate {

    /* TODO: Use fixtures to create Poll post */

    public static final int DEFAULT_ANSWERS_NUMBER = 2;

    /**
     * DESKTOP TESTS SECTION
     */

    @Test(groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_6, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void userCanCreatePostWithSimplePollOnDesktop() {
        PostsListPage page = new PostsListPage().open();
        BasePostsCreator postsCreator = page.getPostsCreatorDesktop();
        postsCreator.click().closeGuidelinesMessage().clickAddCategoryButton().selectFirstCategory();
        postsCreator.addTitleWith(TextGenerator.createUniqueText());
        Poll poll = postsCreator.addPoll();
        Assert.assertFalse(postsCreator.isPostButtonActive());

        poll.addTitle(TextGenerator.createUniqueText());
        Assert.assertFalse(postsCreator.isPostButtonActive());
        poll.addNthAnswer(TextGenerator.createUniqueText(), 0);
        Assert.assertFalse(postsCreator.isPostButtonActive());
        poll.addNthAnswer(TextGenerator.createUniqueText(), 1);
        postsCreator.clickSubmitButton();
        page.waitForLoadingSpinner();

        Assert.assertTrue(new PostsListPage().getPost().firstPostHasPoll());
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnDesktop"}, groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void anonUserCanNotVoteInPollOnDesktop() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        poll.clickPollTitle();
        manageSignInModal(poll);
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnDesktop"}, groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_3, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void loggedInUserCanVoteOnceInPollOnDesktop() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        poll.clickNthAnswer(0);
        poll.clickVoteButton();

        Assert.assertTrue(poll.isChosenResultBarDisplayed());
        Assert.assertTrue(poll.getBarResultsList().size() > 0);
        Assert.assertTrue(poll.isAlreadyVotedMessageVisible());
    }

    @Test(groups = "discussions-polls")
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_6, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void userCanCreateComplexPollOnDesktop() {
        PostsListPage page = new PostsListPage().open();
        BasePostsCreator postsCreator = page.getPostsCreatorDesktop();
        postsCreator.click().closeGuidelinesMessage().clickAddCategoryButton().selectFirstCategory();
        postsCreator.addTitleWith(TextGenerator.createUniqueText());
        Poll poll = postsCreator.addPoll();
        Assert.assertEquals(poll.getAnswersInputsList().size(), DEFAULT_ANSWERS_NUMBER);

        poll.addTitle(TextGenerator.createUniqueText());
        poll.addNthAnswer(TextGenerator.createUniqueText(), 0);
        poll.deletePoll();
        postsCreator.addPoll();
        Assert.assertEquals(poll.getAnswersInputsList().size(), DEFAULT_ANSWERS_NUMBER);

        poll.addTitle(TextGenerator.createUniqueText());
        poll.addNthAnswer(TextGenerator.createUniqueText(), 0);
        poll.addNthAnswer(TextGenerator.createUniqueText(), 1);
        poll.clickAddAnswerButton();
        poll.addNthAnswer(TextGenerator.createUniqueText(), 2);
        poll.clickAddAnswerButton();
        poll.addNthAnswer(TextGenerator.createUniqueText(), 3);
        Assert.assertEquals(poll.getAnswersInputsList().size(), DEFAULT_ANSWERS_NUMBER + 2);

        poll.deleteNthAnswer(0);
        Assert.assertEquals(poll.getAnswersInputsList().size(), DEFAULT_ANSWERS_NUMBER + 1);

        postsCreator.clickSubmitButton();
        page.waitForLoadingSpinner();
        Assert.assertTrue(new PostsListPage().getPost().firstPostHasPoll());
    }

    /**
     * MOBILE TESTS SECTION
     */

    @Test(groups = "discussions-polls")
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.USER_6, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void userCanCreatePostWithSimplePollOnMobile() {
        BasePostsCreator postsCreator = new PostsListPage().open().getPostsCreatorMobile();
        postsCreator.click().closeGuidelinesMessage().clickAddCategoryButton().selectFirstCategory();
        postsCreator.addTitleWith(TextGenerator.createUniqueText());
        Poll poll = postsCreator.addPoll();
        Assertion.assertFalse(postsCreator.isPostButtonActive());

        poll.addTitle(TextGenerator.createUniqueText());
        Assertion.assertFalse(postsCreator.isPostButtonActive());
        poll.addNthAnswer(TextGenerator.createUniqueText(), 0);
        Assert.assertFalse(postsCreator.isPostButtonActive());
        poll.addNthAnswer(TextGenerator.createUniqueText(), 1);
        postsCreator.clickSubmitButton();

        Assert.assertTrue(new PostsListPage().getPost().firstPostHasPoll());
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnMobile"}, groups = "discussions-polls")
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void anonUserCanNotVoteInPollOnMobile() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        poll.clickPollTitle();
        manageSignInModal(poll);
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnMobile"}, groups = "discussions-polls")
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.USER_3, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void loggedInUserCanVoteOnceInPollOnMobile() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        Assert.assertTrue(poll.getAnswersRadioButtonsList().size() > 0);
        poll.clickNthAnswer(0);
        poll.clickVoteButton();

        Assert.assertTrue(poll.isChosenResultBarDisplayed());
        Assert.assertTrue(poll.getBarResultsList().size() > 0);
        Assert.assertTrue(poll.isAlreadyVotedMessageVisible());
    }

    /**
     * TESTING METHODS SECTION
     */

    public void manageSignInModal(Poll p) {
        SignInToFollowModalDialog signInModal = new SignInToFollowModalDialog();
        Assert.assertTrue(signInModal.isVisible());
        signInModal.clickOkButton();
        p.clickNthAnswer(0);
        Assert.assertTrue(signInModal.isVisible());
    }

}
