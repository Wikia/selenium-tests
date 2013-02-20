package com.wikia.webdriver.TestCases.LoginTests;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DataProvider.LoginDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ForcedLoginTests extends TestTemplate{

    private String newFiles = URLsContent.specialNewFiles;
    private String newVideo = URLsContent.specialNewVideo;
    private String upload = URLsContent.specialUpload;
    private String watchList = URLsContent.specialWatchList;

    @Test(groups = {"ForcedLogin_001_newFile", "Login", "ForcedLogin"})
    public void ForcedLogin_001_newFile () {
        CommonFunctions.logOut(driver);

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        base.openSpecialPage(newFiles);
        SpecialNewFilesPageObject specialPage = new SpecialNewFilesPageObject(driver);
        specialPage.verifySpecialPage();
        specialPage.addPhoto();
        specialPage.verifyLogInModalForAnonsVisibility();
        specialPage.logInViaModal(Properties.userName, Properties.password);
        specialPage.closeAddPhotoModal();
        specialPage.verifyUserLoggedIn(Properties.userName);

        CommonFunctions.logOut(driver);
    }

    @Test(groups = {"ForcedLogin_002_video", "Login", "ForcedLogin"})
    public void ForcedLogin_002_video () {
        CommonFunctions.logOut(driver);

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        base.openSpecialPage(newVideo);
        SpecialVideosPageObject specialPage = new SpecialVideosPageObject(driver, Global.DOMAIN);
        specialPage.clickAddAVideo();
        specialPage.verifyLogInModalForAnonsVisibility();
        specialPage.logInViaModal(Properties.userName, Properties.password);
        specialPage.verifyUserLoggedIn(Properties.userName);

        CommonFunctions.logOut(driver);
    }

    @Test(groups = {"ForcedLogin_003_loginRequired", "Login", "ForcedLogin"})
    public void ForcedLogin_003_loginRequired () {
        CommonFunctions.logOut(driver);

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        base.openSpecialPage(upload);
        base.verifyLoginReguiredMessage();
        base.clickLoginOnSpecialPage();
        SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
        special.login(Properties.userName, Properties.password);
        special.verifyUserLoggedIn(Properties.userName);
        special.verifySpecialPageRedirection(upload);

        CommonFunctions.logOut(driver);
    }

    @Test(groups = {"ForcedLogin_004_notLoggedIn", "Login", "ForcedLogin"})
    public void ForcedLogin_004_notLoggedIn () {
        CommonFunctions.logOut(driver);

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        base.openSpecialPage(watchList);
        base.verifyNotLoggedInMessage();
        base.clickLoginOnSpecialPage();
        SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
        special.login(Properties.userName, Properties.password);
        special.verifyUserLoggedIn(Properties.userName);
        special.verifySpecialPageRedirection(watchList);

        CommonFunctions.logOut(driver);
    }

    @Test(groups = {"ForcedLogin_005_addMedia", "Login", "ForcedLogin"})
    public void ForcedLogin_005_addMedia () {
        CommonFunctions.logOut(driver);

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        WikiArticlePageObject article = base.openRandomArticle();
        article.edit();
        MiniEditorComponentObject miniEditor = new MiniEditorComponentObject(driver, Global.DOMAIN);
        miniEditor.clickAddImage();
        base.logInViaModal(Properties.userName, Properties.password);
        miniEditor.waitForEditorReady();
        base.verifyUserLoggedIn(Properties.userName);

        CommonFunctions.logOut(driver);
    }

    @Test(groups = {"ForcedLogin_006_rail", "Login", "ForcedLogin"})
    public void ForcedLogin_006_rail () {
        CommonFunctions.logOut(driver);

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        WikiArticlePageObject article = base.openRandomArticle();
        article.clickAddVideoFromRail();
        article.logInViaModal(Properties.userName, Properties.password);
        base.verifyUserLoggedIn(Properties.userName);

        CommonFunctions.logOut(driver);
    }
}
