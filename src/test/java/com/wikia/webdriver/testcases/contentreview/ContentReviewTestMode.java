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

        VisualEditModePageObject editPage = new VisualEditModePageObject(driver).open("mediawiki:common.js");
        editPage
                .getAceEditor()
                .clearContent();
        editPage.clickAutoApproveCheckbox().clickPublishButton();

        editPage = new VisualEditModePageObject(driver).open("mediawiki:common.js");
        editPage
                .getAceEditor()
                .insertContent(prependTestElement);
        editPage.clickPublishButton();

        SpecialJsPage specialJsPage = new SpecialJsPage(driver).open("common");
        Assertion.assertTrue(specialJsPage.getReviewModule().isEnableTestModeButtonVisible());
        specialJsPage.getReviewModule().clickEnableTestModeButton();

        Assertion.assertEquals(currentDatetime, specialJsPage.getTestElementContent());

        specialJsPage.refreshPage();
        Assertion.assertTrue(specialJsPage.isBannerNotificationLinkVisible());

        specialJsPage.getReviewModule().clickDisableTestModeButton();
        Assertion.assertTrue(specialJsPage.getReviewModule().isEnableTestModeButtonVisible());
        Assertion.assertFalse(specialJsPage.isBannerNotificationLinkVisible());
        Assertion.assertFalse(specialJsPage.isTestElementVisible());
    }
}
