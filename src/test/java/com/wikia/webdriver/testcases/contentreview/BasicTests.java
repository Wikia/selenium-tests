package com.wikia.webdriver.testcases.contentreview;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialJsPage;

@Test(groups = "ContentReview")
public class BasicTests extends NewTestTemplate {

    @Test
    @Execute(onWikia = "openertest")
    public void anonUserShouldntSeeReviewModule() {
        SpecialJsPage wikiaJs = new SpecialJsPage(driver).open("wikia");

        Assertion.assertFalse(wikiaJs.getReviewModule().isModuleVisible());
    }

    @Test
    @Execute(asUser = User.STAFF, onWikia = "openertest")
    public void staffUserShouldSeeReviewModule() {
        SpecialJsPage wikiaJs = new SpecialJsPage(driver).open("wikia");

        Assertion.assertTrue(wikiaJs.getReviewModule().isModuleVisible());
    }

    @Test
    @Execute(asUser = User.STAFF, onWikia = "openertest")
    public void editJS() {
        final String expectedContent =
                String.format("My Awesome JS edit %d", DateTime.now().getMillis());

        VisualEditModePageObject editPage =
                new VisualEditModePageObject(driver).open("mediawiki:wikia.js");

        editPage
                .getAceEditor()
                .clearContent()
                .insertContent(expectedContent);

        editPage.clickAutoApproveCheckbox().clickPublishButton();

        SpecialJsPage specialJsPage = new SpecialJsPage(driver);
        Assertion.assertEquals(specialJsPage.getScriptCoentent(), expectedContent);
        Assertion.assertFalse(specialJsPage.getReviewModule().isSubmitLinkVisible());

        editPage.open("mediawiki:wikia.js")
                .getAceEditor()
                .clearContent()
                .insertContent("Adamk is awesome");

        editPage.clickPublishButton();
        Assertion.assertEquals(specialJsPage.getScriptCoentent(), "Adamk is awesome");
        Assertion.assertTrue(specialJsPage.getReviewModule().isSubmitLinkVisible());
    }
}
