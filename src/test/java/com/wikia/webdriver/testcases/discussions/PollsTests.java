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
import com.wikia.webdriver.elements.mercury.components.discussions.common.BasePostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Poll;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "discussions-polls")
@Execute(asUser = User.USER_6, onWikia = MercuryWikis.DISCUSSIONS_2)
public class PollsTests extends NewTestTemplate {

    private String siteId;

    private void setUp(String wikiName) {
        siteId = Utils.excractSiteIdFromWikiName(wikiName);
    }

    @Test
    @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
    public void userCanCreatePostWithSimplePollOnDesktop() {

    }

    @Test
    @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
    public void userCanCreatePostWithSimplePollOnMobile() {
        BasePostsCreator postsCreator = new PostsListPage().open().getPostsCreatorMobile();
        postsCreator.click().closeGuidelinesMessage();
        postsCreator.clickAddCategoryButton().selectFirstCategory();
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

}
