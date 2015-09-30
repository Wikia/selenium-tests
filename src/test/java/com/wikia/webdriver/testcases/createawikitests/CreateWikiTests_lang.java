package com.wikia.webdriver.testcases.createawikitests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.dataprovider.CreateNewWikiDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep3;

/**
 * @author Karol 'kkarolk' Kujawiak 1. Create wiki in different languages
 */
@Test(groups = {"CNW_lang"})
public class CreateWikiTests_lang extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(dataProviderClass = CreateNewWikiDataProvider.class, dataProvider = "getLangs", groups = {
      "CreateNewWiki_lang_001", "CNW_lang_first"})
  public void CreateNewWiki_lang_TC001(String lang) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName, credentials.password, wikiURL);
    CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
    cnw1.selectLanguage(lang);
    String wikiName = cnw1.getWikiName();
    cnw1.typeInWikiName(wikiName);
    cnw1.verifySuccessIcon();
    CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
    cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY);
    CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
    cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
    ArticlePageObject article = cnw3.submit();
    article.verifyWikiTitleOnCongratualtionsLightBox(wikiName);
    article.closeNewWikiCongratulationsLightBox();
    article.verifyWikiTitleHeader(wikiName);
    article.verifyUserLoggedIn(credentials.userName);
  }

  @Test(dataProviderClass = CreateNewWikiDataProvider.class, dataProvider = "getLangSecondHalf",
      groups = {"CreateNewWiki_lang_001", "CNW_lang_second"})
  public void langSecondHalf(String lang) {
    CreateNewWiki_lang_TC001(lang);
  }
}
