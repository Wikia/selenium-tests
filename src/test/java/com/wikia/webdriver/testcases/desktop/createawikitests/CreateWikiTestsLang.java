package com.wikia.webdriver.testcases.desktop.createawikitests;

import static com.wikia.webdriver.common.core.Assertion.assertEquals;
import static com.wikia.webdriver.common.core.Assertion.assertStringContains;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.WikiFactory;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.CreateNewWikiDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.pages.CreateNewWikiPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"createNewLanguageWiki", "CNW"})
@Execute(onWikia = "community")
public class CreateWikiTestsLang extends NewTestTemplate {

  private void createNewLanguageWiki(String lang) {
    CreateNewWikiPage cnw = new CreateNewWikiPage()
        .open()
        .selectLanguage(lang);

    String expectedDomainSufix = "";
    if (!"en".equals(lang)) {
      expectedDomainSufix = String.format("/%s", lang);
    }

    assertEquals(cnw.getDomainSufix(), "fandom.com" + expectedDomainSufix);
    String wikiName = cnw.getWikiName();

    ArticlePageObject article =
        cnw.typeInWikiName(wikiName)
            .clickNext()
            .selectCategory(CreateWikiMessages.WIKI_CATEGORY_ID)
            .createMyWiki()
            .selectThemeByName(CreateWikiMessages.WIKI_THEME)
            .logWikiTaskId()
            .showMeMyWiki();

    assertStringContains(article.getWikiTitleOnCongratulationsLightBox(), wikiName);

    article.closeNewWikiCongratulationsLightBox();

    assertStringContains(article.getWikiTitleHeader(), wikiName);

    new WikiFactory().setIsTestWiki(article.getWikiID(), true);

    article.verifyUserLoggedIn(User.USER_CNW.getUserName());
  }

  @Test(dataProviderClass = CreateNewWikiDataProvider.class, dataProvider = "getLangsDeEsFr", groups = {"CNW_Lang_DE_ES_FR"})
  @Execute(asUser = User.USER_CNW)
  public void createNewLanguageWiki_DE_ES_FR(String lang) {
    createNewLanguageWiki(lang);
  }

  @Test(dataProviderClass = CreateNewWikiDataProvider.class, dataProvider = "getLangsItJaZh", groups = {"CNW_Lang_IT_JA_ZH"})
  @Execute(asUser = User.USER_CNW)
  public void createNewLanguageWiki_IT_JA_ZH(String lang) {
    createNewLanguageWiki(lang);
  }

  @Test(dataProviderClass = CreateNewWikiDataProvider.class, dataProvider = "getLangsNlNoPl", groups = {"CNW_Lang_NL_NO_PL"})
  @Execute(asUser = User.USER_CNW)
  public void createNewLanguageWiki_NL_NO_PL(String lang) {
    createNewLanguageWiki(lang);
  }

  @Test(dataProviderClass = CreateNewWikiDataProvider.class, dataProvider = "getLangsPrPtBrRu", groups = {"CNW_Lang_PT_PTBR_RU"})
  @Execute(asUser = User.USER_CNW)
  public void createNewLanguageWiki_PT_PTBR_RU(String lang) {
    createNewLanguageWiki(lang);
  }
}
