package com.wikia.webdriver.TestCases.PhalanxTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DataProvider.PhalanxDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPhalanxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.BlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import java.util.HashMap;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class BlockPageContentTests extends TestTemplate {

    private String phalanxSpecialPage = URLsContent.specialPhalanx;
    private String specialCreatePage = URLsContent.specialCreatePage;

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageContent_001_Article", "PhalanxContent", "Phalanx"}
    )
    public void BlockPageContent_001_Article (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForContent(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        phalanx.openSpecialPage(specialCreatePage);
        SpecialCreatePagePageObject special = new SpecialCreatePagePageObject(driver, Global.DOMAIN);
        special.addPageWithDefaultTitleAndGivenContent(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();
    }

        @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageContent_002_BlogPost", "PhalanxContent", "Phalanx"}
    )
    public void BlockPageContent_002_BlogPost (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForContent(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, "");
        blog.openBlogPage(Properties.userName);
	SpecialCreateBlogPageObject createBlog = blog.clickOnCreateBlogPost();
	createBlog.createBlogPostWithDefaultTitle(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageContent_003_UserProfile", "PhalanxContent", "Phalanx"}
    )
    public void BlockPageContent_003_UserProfile (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForContent(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        UserProfilePageObject userProfile = login.openUserProfile(Properties.userName);
	userProfile.openUserIdentityModal();
	userProfile.populateBlockedContent(blockedContent);
	userProfile.submitEdition();
	userProfile.verifyContentBlocked(blockedContent);
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageContent_004_Comment", "PhalanxContent", "Phalanx"}
    )
    public void BlockPageContent_004_Comment (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForContent(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");
        CommonFunctions.logOut(driver);

        WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
	WikiArticlePageObject article = wiki.openRandomArticle();
	article.addComment(blockedContent);
	article.verifyCommentTextNotPresent(blockedContent);
    }
}
