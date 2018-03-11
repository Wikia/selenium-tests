package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.*;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "discussions-polls")
public class PollsTests extends NewTestTemplate {

    private String siteId;
    private String postId;

    private void setUp(String wikiName) {
        siteId = Utils.excractSiteIdFromWikiName(wikiName);
    }

    @Test
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    @Execute(asUser = User.USER_6, onWikia = MercuryWikis.DISCUSSIONS_2)
    public void userCanCreatePostWithSimplePollOnDesktop() {
        BasePostsCreator postsCreator = new PostsListPage().open().getPostsCreatorDesktop();
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
        Assert.assertFalse(postsCreator.isPostButtonActive()); //poll needs to have at least 2 answers options
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

}
