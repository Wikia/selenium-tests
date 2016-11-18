package com.wikia.webdriver.testcases.createawikitests;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.helpers.WikiaProperties;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep3;
import org.testng.annotations.Test;

@Test(groups = {"CNW_User"})
public class CreateWikiTests_loggedInUser extends NewTestTemplate {

  String wikiDomain;
  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_001"})
  @Execute(asUser = User.USER)
  public void CreateNewWiki_001_createDeleteWiki() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
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
    article.verifyUserLoggedIn(credentials.userName);

  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_002"})
  @Execute(asUser = User.USER)
  public void CreateNewWiki_002_createWikiForChildren() {
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
    article.verifyUserLoggedIn(credentials.userName);

    Assertion.assertTrue(WikiaProperties.isWikiForChildren(driver), "Wiki is not for children");
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_003"})
  @Execute(asUser = User.USER)
  public void CreateNewWiki_003_createWikiChangedDomain() {
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
    article.verifyUserLoggedIn(credentials.userName);
    article.isStringInURL(wikiDomain);
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_004"})
  @Execute(asUser = User.USER)
  public void CreateNewWiki_004_creatWikiNameExists() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = "muppets";
    cnw1.typeInWikiName(wikiName);
    cnw1.verifyOccupiedWikiAddress(wikiName);
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_005"})
  @Execute(asUser = User.USER)
  public void CreateNewWiki_005_createWikiPolicyViolation() {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    String wikiName = "1234";
    cnw1.typeInWikiName(wikiName);
    cnw1.verifyIncorrectWikiName();
  }

  @Test(groups = {"CNW", "CreateNewWikiLoggedIn_006"})
  @Execute(asUser = User.USER)
  public void CreateNewWiki_006_createWikiNoCategory() {
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
