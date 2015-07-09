package com.wikia.webdriver.testcases.flagtests;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.flag.AddFlagsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

import org.testng.annotations.Test;

/**
 * Created by Mariusz Czeszejko  2015-07-01
 * This test is adding flags with modal view and checking if added flags have the same ID
 * as in modal and check visibility added flags.
 * If some flag isn't visible test ends with fail.
 */
public class FlagTests extends NewTestTemplate {

    Credentials credentials = config.getCredentials();

    @Test(
            groups = {"FlagTests","Flag_Tests_001"})
    public void FlagTests_001_addFlag() {
        AddFlagsComponentObject flag = new AddFlagsComponentObject(driver);
        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
        String articleContent = PageContent.ARTICLE_TEXT;
        String articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
        VisualEditModePageObject
                visualEditMode =
                base.navigateToArticleEditPageCK(wikiURL, articleTitle);
        visualEditMode.addContent(articleContent);
        visualEditMode.submitArticle();
        flag.clickFlagButton();
        flag.selectFlagsInModal();
        flag.clickDoneButton();
        flag.checkFlagsEnabling();
    }
}
