package com.wikia.webdriver.testcases.createawikitests;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AuthModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep3;
import junit.framework.Assert;
import org.testng.annotations.Test;

@Test(groups = {"CNW_Anon"})
public class CreateWikiTests_loggedOutUser extends NewTestTemplate {

    Credentials credentials = Configuration.getCredentials();

    @Test(groups = {"CNW", "CreateNewWikiLoggedOut_001"})
    @RelatedIssue(issueID = "QAART-771", comment = "Test manually")
    public void loggedOutUserCanCreateNewWiki() {
        WikiBasePageObject base = new WikiBasePageObject();
        CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
        String wikiName = cnw1.getWikiName();
        cnw1.typeInWikiName(wikiName);
        cnw1.verifyNextButtonEnabled();
        AuthModal authModal = cnw1.clickNextToSignIn();
        authModal.clickToSignInForm();

        Assert.assertTrue(authModal.isSignInOpened());

        authModal.login(credentials.userName10, credentials.password10);
        CreateNewWikiPageObjectStep2 cnw2 = new CreateNewWikiPageObjectStep2(driver);
        cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID);
        CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
        cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
        ArticlePageObject article = cnw3.submit();
        article.verifyWikiTitleOnCongratualtionsLightBox(wikiName);
        article.closeNewWikiCongratulationsLightBox();
        article.verifyWikiTitleHeader(wikiName);
        article.verifyUserLoggedIn(credentials.userName);
    }

}
