package com.wikia.webdriver.testcases.contentreview;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialJsPage;

import org.testng.annotations.Test;

@Test(groups = "ContentReview")
public class ContentReviewTests extends NewTestTemplate {

    @Test
    public void anonUserShouldntSeeReviewModule() {
        SpecialJsPage wikiaJs = new SpecialJsPage().open("wikia");

        Assertion.assertTrue(wikiaJs.getReviewModule().isModuleNotVisible());
    }

    @Test
    @Execute(asUser = User.STAFF)
    @RelatedIssue(issueID = "XW-4839")
    public void staffUserShouldSeeReviewModule() {
        SpecialJsPage wikiaJs = new SpecialJsPage().open("wikia");

        Assertion.assertTrue(wikiaJs.getReviewModule().isModuleVisible());
    }

    @Test
    @Execute(asUser = User.STAFF)
    @RelatedIssue(issueID = "XW-4839")
    public void editJS() {
        final String expectedContent = "console.log(\"content review test\");";

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
                .insertContent( "console.log(\"content review test 2\");");

        editPage.clickAutoApproveCheckbox().clickPublishButton();
        Assertion.assertEquals(specialJsPage.getScriptContent(),  "console.log(\"content review test 2\");");
        Assertion.assertTrue(specialJsPage.getReviewModule().isSubmitLinkNotVisible());
    }
}
