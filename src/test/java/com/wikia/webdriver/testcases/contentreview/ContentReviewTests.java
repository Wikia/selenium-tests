package com.wikia.webdriver.testcases.contentreview;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialJsPage;

@Test(groups = "ContentReview")
public class ContentReviewTests extends NewTestTemplate {

    @Test
    @Execute(onWikia = "openertest")
    public void anonUserShouldntSeeReviewModule() {
        SpecialJsPage wikiaJs = new SpecialJsPage().open("wikia");

        Assertion.assertTrue(wikiaJs.getReviewModule().isModuleNotVisible());
    }

    @Test
    @Execute(asUser = User.STAFF, onWikia = "openertest")
    public void staffUserShouldSeeReviewModule() {
        SpecialJsPage wikiaJs = new SpecialJsPage().open("wikia");

        Assertion.assertTrue(wikiaJs.getReviewModule().isModuleVisible());
    }

    @Test
    @Execute(asUser = User.CONTENT_REVIEWER, onWikia = "openertest")
    public void editJS() {
        final String expectedContent =
                String.format("My Awesome JS edit %d", DateTime.now().getMillis());

        VisualEditModePageObject editPage =
                new VisualEditModePageObject().open("mediawiki:wikia.js");

        editPage
                .getAceEditor()
                .clearContent()
                .insertContent(expectedContent);

        editPage
            .clickPublishButton();

        SpecialJsPage specialJsPage = new SpecialJsPage();
        Assertion.assertEquals(specialJsPage.getScriptContent(), expectedContent);
        Assertion.assertTrue(specialJsPage.getReviewModule().isSubmitLinkVisible());

        editPage.open("mediawiki:wikia.js")
                .getAceEditor()
                .clearContent()
                .insertContent("Adamk is awesome");

        editPage.clickAutoApproveCheckbox().clickPublishButton();
        Assertion.assertEquals(specialJsPage.getScriptContent(), "Adamk is awesome");
        Assertion.assertTrue(specialJsPage.getReviewModule().isSubmitLinkNotVisible());
    }
}
