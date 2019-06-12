package com.wikia.webdriver.testcases.desktop.createawikitests;

import static com.wikia.webdriver.common.core.Assertion.assertStringContains;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.api.WikiFactory;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.pages.CreateNewWikiPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.time.LocalDate;

@Test(groups = {"CNW_Anon", "CNW"})
public class CreateWikiTestsLoggedOutUser extends NewTestTemplate {

  public void loggedOutUserCanCreateNewWiki() {
    String tempLogin = "QACreateWiki" + DateTime.now().getMillis();
    String tempPass = Long.toString(DateTime.now().getMillis());

    SignUpUser newUser = new SignUpUser(
        tempLogin,
        "qaCreateWiki@fandom.com",
        tempPass,
        LocalDate.of(1990, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(newUser);

    CreateNewWikiPage cnw = new CreateNewWikiPage().open();
    String wikiName = cnw.getWikiName();
    DetachedRegisterPage authModal = cnw.typeInWikiName(wikiName)
        .clickNextToSignIn();

    authModal
        .navigateToSignIn()
        .login(newUser.getUsername(), newUser.getPassword());

    ArticlePageObject article = cnw
        .selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID)
        .createMyWiki()
        .selectThemeByName(CreateWikiMessages.WIKI_THEME)
        .showMeMyWiki();

    article.closeNewWikiCongratulationsLightBox();

    new WikiFactory().setIsTestWiki(article.getWikiID(), true);

    assertStringContains(article.getWikiTitleHeader(), wikiName);

    Assertion.assertTrue(article.isUserLoggedIn(newUser.getUsername()));
  }
}
