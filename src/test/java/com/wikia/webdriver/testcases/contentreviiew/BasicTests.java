package com.wikia.webdriver.testcases.contentreviiew;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

/**
 * Created by Ludwik on 2015-09-23.
 */
@Test(groups = "ContentReview")
public class BasicTests extends NewTestTemplate {

    @Test
    @Execute(onWikia = "openertest")
    public void anonUserShouldntSeeReviewModule(){
        ArticlePageObject wikiaJs = new ArticlePageObject(driver).open("mediawiki:wikia.js");

        Assertion.assertFalse(wikiaJs.getReviewModule().isModuleVisible());
    }

    @Test
    @Execute(asUser = User.STAFF, onWikia = "openertest")
    public void staffUserShouldSeeReviewModule(){
        ArticlePageObject wikiaJs = new ArticlePageObject(driver).open("mediawiki:wikia.js");

        Assertion.assertTrue(wikiaJs.getReviewModule().isModuleVisible());
    }
}
