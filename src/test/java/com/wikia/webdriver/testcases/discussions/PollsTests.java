package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.*;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "discussions-polls")
public class PollsTests extends NewTestTemplate {

    public static final int DEFAULT_ANSWERS_NUMBER = 2;

    private void createInitialPostWithPoll() {
        final PostEntity.Data data = DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();

    }

    @Test
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
        Assert.assertFalse(postsCreator.isPostButtonActive()); //poll needs to have at least 2 answers options
        poll.addNthAnswer(TextGenerator.createUniqueText(), 1);
        postsCreator.clickSubmitButton();
        page.waitForLoadingSpinner();

        Assert.assertTrue(new PostsListPage().getPost().firstPostHasPoll());
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnDesktop"})
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void anonUserCanNotVoteInPollOnDesktop() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        poll.clickPollTitle();
        SignInToFollowModalDialog signInModal = new SignInToFollowModalDialog();
        Assert.assertTrue(signInModal.isVisible());

        signInModal.clickOkButton();
        poll.clickNthAnswer(0);
        Assert.assertTrue(signInModal.isVisible());
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnDesktop"})
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_3, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void loggedInUserCanVoteOnceInPollOnDesktop() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        poll.clickNthAnswer(0);
        poll.clickNthAnswer(1); //change answer before submitting a vote
        poll.clickVoteButton();

        Assert.assertTrue(poll.isChosenResultBarDisplayed());
        Assert.assertTrue(poll.getBarResultsList().size() > 0);
        Assert.assertTrue(poll.isAlreadyVotedMessageVisible());
    }

    @Test
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
        poll.deletePoll(); //delete poll being created
        postsCreator.addPoll(); //create new poll
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

    @Test
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

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnMobile"})
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void anonUserCanNotVoteInPollOnMobile() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        poll.clickPollTitle();
        SignInToFollowModalDialog signInModal = new SignInToFollowModalDialog();
        Assert.assertTrue(signInModal.isVisible());

        signInModal.clickOkButton();
        poll.clickNthAnswer(0);
        Assert.assertTrue(signInModal.isVisible());
    }

    @Test(dependsOnMethods = {"userCanCreatePostWithSimplePollOnMobile"})
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    @Execute(asUser = User.USER_3, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void loggedInUserCanVoteOnceInPollOnMobile() {
        Poll poll = new PostsListPage().open().getPost().clickNthPostWithPoll(0).getPoll();
        Assert.assertTrue(poll.getAnswersRadioButtonsList().size() > 0);
        poll.clickNthAnswer(0);
        poll.clickNthAnswer(1);
        poll.clickVoteButton();

        Assert.assertTrue(poll.isChosenResultBarDisplayed());
        Assert.assertTrue(poll.getBarResultsList().size() > 0);
        Assert.assertTrue(poll.isAlreadyVotedMessageVisible());
    }

}
