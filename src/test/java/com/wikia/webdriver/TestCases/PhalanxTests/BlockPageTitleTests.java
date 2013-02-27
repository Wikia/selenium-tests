package com.wikia.webdriver.TestCases.PhalanxTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DataProvider.PhalanxDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows.CreateArticleModalComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPhalanxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import java.util.HashMap;
import org.testng.annotations.Test;

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
        CommonFunctions.logOut(driver);
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        phalanx.openSpecialPage(specialCreatePage);
        SpecialCreatePagePageObject special = new SpecialCreatePagePageObject(driver, Global.DOMAIN);
        special.addPageWithGIvenTitleAndDefaultContent(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();

        CommonFunctions.logOut(driver);
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_002_actionSwitch" , "Phalanx"}
    )
        public void BlockPageTitle_002_actionSwitch (String filterType) {
        CommonFunctions.logOut(driver);
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        WikiArticleEditMode article = new WikiArticleEditMode(driver, Global.DOMAIN, "");
        article.editArticleByName(blockedContent);
        article.typeInContent(PageContent.articleText);
        article.clickOnPublish();

        phalanx.verifyMessageAboutBlockPresent();

        CommonFunctions.logOut(driver);
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_003_contribute" , "Phalanx"}
    )
        public void BlockPageTitle_003_contribute (String filterType) {
        CommonFunctions.logOut(driver);
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        base.clickContributeNewPage();

        CreateArticleModalComponentObject modal = new CreateArticleModalComponentObject(driver, Global.DOMAIN);
        modal.createPageWithStandardLayout(blockedContent);
        modal.verifyMessageAboutBlockPresent();

        CommonFunctions.logOut(driver);
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_004_rename" , "Phalanx"}
    )
        public void BlockPageTitle_004_rename (String filterType) {
        CommonFunctions.logOut(driver);
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        WikiArticlePageObject base = new WikiArticlePageObject(driver, Global.DOMAIN,"");
        base.openRandomArticle();
        base.renameRandomArticle(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();

        CommonFunctions.logOut(driver);
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_005_editBlock" , "Phalanx"}
    )
        public void BlockPageTitle_005_editBlock (String filterType) {
        CommonFunctions.logOut(driver);
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        phalanx.openSpecialPage(specialCreatePage);
        SpecialCreatePagePageObject special = new SpecialCreatePagePageObject(driver, Global.DOMAIN);
        special.addPageWithGIvenTitleAndDefaultContent(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();

        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap modifiedBlock = phalanx.changeFilterContent(block);
        phalanx.testBlock(modifiedBlock);
        String modifiedBlockedContent = (String) modifiedBlock.get("content");

        phalanx.openSpecialPage(specialCreatePage);
        special.addPageWithGIvenTitleAndDefaultContent(modifiedBlockedContent);

        phalanx.verifyMessageAboutBlockAbsent();

        CommonFunctions.logOut(driver);
    }

    @Test(
        dataProviderClass=PhalanxDataProvider.class,
        dataProvider="getFilterTypes",
        groups = {"BlockPageTitle_006_unblockBlock" , "Phalanx"}
    )
        public void BlockPageTitle_006_unblockBlock (String filterType) {
        CommonFunctions.logOut(driver);
        SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

        SpecialPhalanxPageObject phalanx = new SpecialPhalanxPageObject(driver, Global.DOMAIN);
        phalanx.openSpecialPage(phalanxSpecialPage);
        HashMap block = phalanx.addFilterForTitle(filterType);
        phalanx.testBlock(block);
        String blockedContent = (String) block.get("content");

        phalanx.openSpecialPage(specialCreatePage);
        SpecialCreatePagePageObject special = new SpecialCreatePagePageObject(driver, Global.DOMAIN);
        special.addPageWithGIvenTitleAndDefaultContent(blockedContent);

        phalanx.verifyMessageAboutBlockPresent();

        phalanx.openSpecialPage(phalanxSpecialPage);
        phalanx.unblockFilter(blockedContent);

        phalanx.openSpecialPage(specialCreatePage);
        special.addPageWithGIvenTitleAndDefaultContent(blockedContent);

        phalanx.verifyMessageAboutBlockAbsent();

        CommonFunctions.logOut(driver);
    }
}
