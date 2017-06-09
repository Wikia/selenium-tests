package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = {"ArticleCRUDUser"})
public class
ArticleCRUDUserTests extends NewTestTemplate {

  @Test(groups = {"ArticleCRUDUser_001"})
  @Execute(asUser = User.USER)
  public void ArticleCRUDUser_001_specialPage() {
    SpecialCreatePage specialCreatePage = new SpecialCreatePage().open();
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleCRUDUser_002"})
  @Execute(asUser = User.USER)
  public void ArticleCRUDUser_002_addByURL() {
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new VisualEditModePageObject().open(articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleCRUDUser_003", "Smoke1"})
  @Execute(asUser = User.USER)
  public void ArticleCRUDUser_003_addEditButton() {
    new ArticleContent().clear();

    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject article = new ArticlePageObject().open(articleTitle + "?AbTest.ADD_NEW_PAGE=CONTROL1");
    VisualEditModePageObject visualEditMode = article.openCKModeWithMainEditButton();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(dataProviderClass = ArticleDataProvider.class, dataProvider = "articleTitles",
      groups = {"ArticleCRUDUser_004"})
  @Execute(asUser = User.USER)
  public void ArticleCRUDUser_004_differentTitles(String articleTitle) {
    String articleContent = PageContent.ARTICLE_TEXT;
    String randomArticleTitle = articleTitle + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new VisualEditModePageObject().open(randomArticleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(randomArticleTitle);
  }

  @Test(groups = {"ArticleCRUDUser_005"})
  @Execute(asUser = User.USER)
  public void ArticleCRUDUser_005_editByURL() {
    new ArticleContent().clear();

    String articleContent = PageContent.ARTICLE_TEXT;
    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
  }

  @Test(groups = {"ArticleCRUDUser_006"})
  @Execute(asUser = User.USER)
  public void ArticleCRUDUser_006_editEditDropdown() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    String articleContent = PageContent.ARTICLE_TEXT;
    ArticlePageObject article = new ArticlePageObject().open();
    VisualEditModePageObject visualEditMode = article.openCKModeWithMainEditButtonDropdown();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
    article.verifyContent(articleContent);
  }
}
