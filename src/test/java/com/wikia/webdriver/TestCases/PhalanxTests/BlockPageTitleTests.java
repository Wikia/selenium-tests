package com.wikia.webdriver.TestCases.PhalanxTests;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.DataProvider.PhalanxDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows.CreateArticleModalComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPhalanxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class BlockPageTitleTests extends TestTemplate {

    private String phalanxSpecialPage = URLsContent.specialPhalanx;
    private String specialCreatePage = URLsContent.specialCreatePage;

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_001_SpecialPage", "Phalanx"}
    )
    public void BlockPageTitle_001_SpecialPage (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        phalanx.openSpecialPage(specialCreatePage);
        SpecialCreatePagePageObject special = new SpecialCreatePagePageObject(driver);
        special.addPageWithGivenTitleAndDefaultContent(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_002_actionSwitch" , "Phalanx"}
    )
        public void BlockPageTitle_002_actionSwitch (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        WikiArticleEditMode article = new WikiArticleEditMode(driver);
        article.editArticleByName(blockedContent);
        article.typeInContent(PageContent.articleText);
        article.clickOnPublish();

        phalanx.verifyMessageAboutBlockPresent();
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_003_contribute" , "Phalanx"}
    )
        public void BlockPageTitle_003_contribute (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        WikiBasePageObject base = new WikiBasePageObject(driver);
        base.openWikiPage();
        base.clickContributeNewPage();

        CreateArticleModalComponentObject modal = new CreateArticleModalComponentObject(driver);
        modal.createPageWithStandardLayout(blockedContent);
        modal.verifyMessageAboutBlockPresent();
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_004_rename" , "Phalanx"}
    )
        public void BlockPageTitle_004_rename (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        WikiArticlePageObject base = new WikiArticlePageObject(driver);
        base.openRandomArticle();
        base.renameRandomArticle(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_005_editBlock" , "Phalanx"}
    )
        public void BlockPageTitle_005_editBlock (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        phalanx.openSpecialPage(specialCreatePage);
        SpecialCreatePagePageObject special = new SpecialCreatePagePageObject(driver);
        special.addPageWithGivenTitleAndDefaultContent(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap modifiedBlock = phalanx.changeFilterContent(block);
        phalanx.testBlock(modifiedBlock);
        String modifiedBlockedContent = (String) modifiedBlock.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        phalanx.openSpecialPage(specialCreatePage);
        special.addPageWithGivenTitleAndDefaultContent(modifiedBlockedContent);

        phalanx.verifyMessageAboutBlockAbsent();
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_006_unblockBlock" , "Phalanx"}
    )
        public void BlockPageTitle_006_unblockBlock (String filterType) {
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        phalanx.openSpecialPage(specialCreatePage);
        SpecialCreatePagePageObject special = new SpecialCreatePagePageObject(driver);
        special.addPageWithGivenTitleAndDefaultContent(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        phalanx.openSpecialPage(phalanxSpecialPage);
        phalanx.unblockFilter(blockedContent);

        CommonFunctions.logOut(driver);
        login.loginAndVerify(Properties.userName, Properties.password);

        phalanx.openSpecialPage(specialCreatePage);
        special.addPageWithGivenTitleAndDefaultContent(blockedContent);

        phalanx.verifyMessageAboutBlockAbsent();
    }
}
