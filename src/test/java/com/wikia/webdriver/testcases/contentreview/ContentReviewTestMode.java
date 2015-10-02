package com.wikia.webdriver.testcases.contentreview;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialJsPage;

import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by adamk@wikia-inc.com on 9/25/15.
 */
@Test(groups = "ContentReview")
public class ContentReviewTestMode extends NewTestTemplate {

    @Test
    @Execute(asUser = User.STAFF, onWikia = "openertest")
    public void canEnterAndExitTestMode() {
        String currentDatetime = new Date().toString();
        String prependTestElement = "$('#WikiaArticle').prepend('<div id=\"content-review-selenium-test-element\">" + currentDatetime + "</div>');";

        /**
         * Step 0 - Prepare a clean state with auto-approving a clearing revision.
         */
        VisualEditModePageObject editPage = new VisualEditModePageObject(driver).open("mediawiki:common.js");
        editPage
                .getAceEditor()
                .clearContent();
        editPage.clickAutoApproveCheckbox().clickPublishButton();

        /**
         * Step 1 - Create a revision that appends the test element with the current timestamp
         */
        editPage = new VisualEditModePageObject(driver).open("mediawiki:common.js");
        editPage
                .getAceEditor()
                .insertContent(prependTestElement);
        editPage.clickPublishButton();

        /**
         * Step 2 - Open the page as JsPage and check if the Enable test mode button is visible and click it.
         */
        SpecialJsPage specialJsPage = new SpecialJsPage(driver).open("common");
        Assertion.assertTrue(specialJsPage.getReviewModule().isEnableTestModeButtonVisible());
        specialJsPage.getReviewModule().clickEnableTestModeButton();

        /**
         * Step 3 - Assert that the content of the appended element equals the generated timestamp
         */
        Assertion.assertEquals(currentDatetime, specialJsPage.getTestElementContent());

        /**
         * Step 4 - Refresh the page to see if an information about the test mode being enabled
         * is still visible afterwards.
         */
        specialJsPage.refreshPage();
        Assertion.assertTrue(specialJsPage.isBannerNotificationLinkVisible());

        /**
         * Step 5 - Exit the test mode and make sure that the Enable test mode button is visible
         * and the BannerNotification and the test element are gone.
         */
        specialJsPage.getReviewModule().clickDisableTestModeButton();
        Assertion.assertTrue(specialJsPage.getReviewModule().isEnableTestModeButtonVisible());
        Assertion.assertFalse(specialJsPage.isBannerNotificationLinkVisible());
        Assertion.assertFalse(specialJsPage.isTestElementVisible());
    }
}
