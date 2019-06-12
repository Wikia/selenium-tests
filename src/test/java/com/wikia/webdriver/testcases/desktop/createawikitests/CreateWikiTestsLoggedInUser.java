package com.wikia.webdriver.testcases.desktop.createawikitests;

import static com.wikia.webdriver.common.core.Assertion.assertStringContains;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.WikiFactory;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.helpers.WikiaProperties;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.pages.CreateNewWikiPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"CNW_User", "CNW"})
@Execute(onWikia = "community")
public class CreateWikiTestsLoggedInUser extends NewTestTemplate {

  @Execute(asUser = User.USER_CNW)
  public void createNewWikiCreateWiki() {
    CreateNewWikiPage cnw = new CreateNewWikiPage().open();
    String wikiName = cnw.getWikiName();
    ArticlePageObject article = cnw.typeInWikiName(wikiName)
        .clickNext()
        .selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID)
        .createMyWiki()
        .selectThemeByName(CreateWikiMessages.WIKI_THEME)
        .showMeMyWiki();

    assertStringContains(article.getWikiTitleOnCongratulationsLightBox(), wikiName);

    article.closeNewWikiCongratulationsLightBox();

    assertStringContains(article.getWikiTitleHeader(), wikiName);

    new WikiFactory().setIsTestWiki(article.getWikiID(), true);

    Assertion.assertTrue(article.isUserLoggedIn(User.USER_CNW.getUserName()));
  }

  @Execute(asUser = User.USER_CNW)
  public void createNewWikiCreateWikiForChildren() {
    CreateNewWikiPage cnw = new CreateNewWikiPage().open();
    String wikiName = cnw.getWikiName();
    ArticlePageObject article = cnw.typeInWikiName(wikiName)
        .clickNext()
        .selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID)
        .selectAllAgesCheckbox()
        .createMyWiki()
        .selectThemeByName(CreateWikiMessages.WIKI_THEME)
        .showMeMyWiki();

    article.closeNewWikiCongratulationsLightBox();

    new WikiFactory().setIsTestWiki(article.getWikiID(), true);

    Assertion.assertTrue(article.isUserLoggedIn(User.USER_CNW.getUserName()));

    Assertion.assertTrue(WikiaProperties.isWikiForChildren(driver), "Wiki is not for children");
  }

  @Execute(asUser = User.USER_CNW)
  public void createNewWikiCreateWikiChangedDomain() {
    CreateNewWikiPage cnw = new CreateNewWikiPage().open();
    String wikiName = cnw.getWikiName();
    String wikiDomain = String.format("%sDifferentDomain", wikiName);

    ArticlePageObject article = cnw.typeInWikiName(wikiName)
        .typeInWikiDomain(wikiDomain)
        .clickNext()
        .selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID)
        .createMyWiki()
        .selectThemeByName(CreateWikiMessages.WIKI_THEME)
        .showMeMyWiki();

    article.closeNewWikiCongratulationsLightBox();

    new WikiFactory().setIsTestWiki(article.getWikiID(), true);

    Assertion.assertTrue(article.isUserLoggedIn(User.USER_CNW.getUserName()));

    Assertion.assertTrue(article.isStringInURL(wikiDomain));
  }

  @Execute(asUser = User.USER_CNW)
  public void createNewWikiCreatWikiNameExists() {
    CreateNewWikiPage cnw = new CreateNewWikiPage().open();
    String wikiName = "muppets";
    cnw.typeInWikiName(wikiName);

    Assertion
        .assertStringContains(cnw.getDomainErrorMessage(), CreateWikiMessages.ADDRESS_OCCUPIED);
  }

  @Execute(asUser = User.USER)
  public void createNewWiki005CreateWikiPolicyViolation() {
    CreateNewWikiPage cnw = new CreateNewWikiPage().open();
    String wikiName = "1234";
    cnw.typeInWikiName(wikiName);

    Assertion
        .assertStringContains(cnw.getDomainErrorMessage(),
                              CreateWikiMessages.WIKINAME_VIOLATES_POLICY);
  }

  @Execute(asUser = User.USER_CNW)
  public void createNewWiki006CreateWikiNoCategory() {
    CreateNewWikiPage cnw = new CreateNewWikiPage().open();
    String wikiName = cnw.getWikiName();

    cnw.typeInWikiName(wikiName)
        .clickNext()
        .selectCategory(0);

    Assertion
        .assertStringContains(cnw.getCategoryErrorMessage(),
                              CreateWikiMessages.CATEGORY_ERROR_MESSAGE);
  }
}
