package com.wikia.webdriver.testcases.createawikitests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.helpers.WikiaProperties;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep3;

@Test(groups = {"CNW_User"})
public class CreateWikiTestsLoggedInUser extends NewTestTemplate {

  private String wikiDomain;

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_001"})
  @Execute(asUser = User.USER_CNW)
  public void createNewWiki001CreateDeleteWiki() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    cnw1.selectLanguage("en");
    String wikiName = cnw1.getWikiName();
    cnw1.typeInWikiName(wikiName);
    cnw1.verifyNextButtonEnabled();
    CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
    cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID);
    CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
    cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
    ArticlePageObject article = cnw3.submit();
    article.verifyWikiTitleOnCongratualtionsLightBox(wikiName);
    article.closeNewWikiCongratulationsLightBox();
    article.verifyWikiTitleHeader(wikiName);
    DeletePageObject deletePage = article.deleteUsingDropdown();
    deletePage.submitDeletion();
    article.verifyUserLoggedIn(User.USER_CNW.getUserName());
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_002"})
  @Execute(asUser = User.USER_CNW)
  public void createNewWiki002CreateWikiForChildren() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = cnw1.getWikiName();
    cnw1.typeInWikiName(wikiName);
    cnw1.verifyNextButtonEnabled();
    CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
    cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID);
    cnw2.selectAllAgesCheckbox();
    CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
    cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
    ArticlePageObject article = cnw3.submit();
    article.closeNewWikiCongratulationsLightBox();
    article.verifyUserLoggedIn(User.USER_CNW.getUserName());

    Assertion.assertTrue(WikiaProperties.isWikiForChildren(driver), "Wiki is not for children");
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_003"})
  @Execute(asUser = User.USER_CNW)
  public void createNewWiki003CreateWikiChangedDomain() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = cnw1.getWikiName();
    wikiDomain = cnw1.getWikiName();
    cnw1.typeInWikiName(wikiName);
    cnw1.typeInWikiDomain(wikiDomain);
    cnw1.verifyNextButtonEnabled();
    CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
    cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID);
    CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
    cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
    ArticlePageObject article = cnw3.submit();
    article.closeNewWikiCongratulationsLightBox();
    article.verifyUserLoggedIn(User.USER_CNW.getUserName());
    article.isStringInURL(wikiDomain);
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_004"})
  @Execute(asUser = User.USER_CNW)
  public void createNewWiki004CreatWikiNameExists() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = "muppets";
    cnw1.typeInWikiName(wikiName);
    cnw1.verifyOccupiedWikiAddress(wikiName);
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_005"})
  @Execute(asUser = User.USER_CNW)
  public void createNewWiki005CreateWikiPolicyViolation() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = "1234";
    cnw1.typeInWikiName(wikiName);
    cnw1.verifyIncorrectWikiName();
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_006"})
  @Execute(asUser = User.USER_CNW)
  public void createNewWiki006CreateWikiNoCategory() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = cnw1.getWikiName();
    wikiDomain = cnw1.getWikiName();
    cnw1.typeInWikiName(wikiName);
    cnw1.typeInWikiDomain(wikiDomain);
    cnw1.verifyNextButtonEnabled();
    CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
    cnw2.selectCategory(-1);
    cnw2.verifyCategoryError();
  }
}
