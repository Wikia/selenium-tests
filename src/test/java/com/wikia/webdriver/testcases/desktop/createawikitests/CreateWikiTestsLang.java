package com.wikia.webdriver.testcases.desktop.createawikitests;

import static com.wikia.webdriver.common.core.Assertion.assertEquals;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.WikiFactory;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.CreateNewWikiDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep3;
import org.testng.annotations.Test;

@Test(groups = {"CNW_lang"})
public class CreateWikiTestsLang extends NewTestTemplate {

  @Test(dataProviderClass = CreateNewWikiDataProvider.class, dataProvider = "getLangs", groups = {
      "CreateNewWiki_lang_001", "CNW_lang_first"})
  @Execute(asUser = User.USER_CNW)
  public void createNewWikiLangTC001(String lang) {
    WikiBasePageObject base = new WikiBasePageObject();
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    cnw1.selectLanguage(lang);
    String expectedDomainPrefix = "en".equals(lang) ? "" : String.format("%s.", lang);
    assertEquals(cnw1.getDomainPrefix().getText(), expectedDomainPrefix);
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

    new WikiFactory().setIsTestWiki(article.getWikiID(), true);

    article.verifyUserLoggedIn(User.USER_CNW.getUserName());
  }

  @Test(dataProviderClass = CreateNewWikiDataProvider.class, dataProvider = "getLangSecondHalf", groups = {
      "CreateNewWiki_lang_001", "CNW_lang_second"})
  @Execute(asUser = User.USER_CNW)
  public void langSecondHalf(String lang) {
    createNewWikiLangTC001(lang);
  }
}
