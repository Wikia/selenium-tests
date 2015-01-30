package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Editor;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.EditorPref;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;


/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 */

public class VisualEditorEntryTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_001", "categoryEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "categoryEntryPoints"
  )
  public void VisualEditorEntryTest_001_Category(
      boolean isRTEext, boolean isVEext, VisualEditorDataProvider.EditorPref editorPref,
      VisualEditorDataProvider.Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.openArticleByName(wikiURL, URLsContent.CATEGORY_PAGE);
    article.verifyMainEditEditor(expectedEditor);
  }

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_002", "createAPageEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "createAPageEntryPoints"
  )
  public void VisualEditorEntryTest_002_CreateAPage(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    String articleName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.openArticleByName(wikiURL, articleName);
    article.verifyCreateAPageEditor(expectedEditor, articleName);
  }

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_003", "listEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "listEntryPoints"
  )
  public void VisualEditorEntryTest_003_List(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.openArticleByName(wikiURL, URLsContent.LIST_PAGE);
    article.verifyMainEditEditor(expectedEditor);
  }

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_004", "articleEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "mainEditEntryPoints"
  )
  public void VisualEditorEntryTest_004_Article(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
    article.verifyMainEditEditor(expectedEditor);
  }

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_005", "redLinkEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "redLinkEntryPoints"
  )
  public void VisualEditorEntryTest_005_redLink(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
    article.verifyRedLinkEditor(expectedEditor);
  }

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_006", "sectionEditEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "sectionEditEntryPoints"
  )
  public void VisualEditorEntryTest_006_sectionEdit(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
    article.verifySectionEditEditor(expectedEditor);
  }

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_007", "templateEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "templateEntryPoints"
  )
  public void VisualEditorEntryTest_007_template(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.openArticleByName(wikiURL, URLsContent.TEMPLATE_PAGE);
    article.verifyMainEditEditor(expectedEditor);
  }

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_008", "urlActionEditEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "urlActionEditEntryPoints"
  )
  public void VisualEditorEntryTest_008_urlActionEdit(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.verifyURLActionEditEditor(expectedEditor, URLsContent.TESTINGPAGE, wikiURL);
  }

  @Test(
      groups = {"VisualEditorEntry", "VisualEditorEntryTest_009", "urlVEActionEditEntry"},
      dataProviderClass = VisualEditorDataProvider.class,
      dataProvider = "urlVEActionEditEntryPoints"
  )
  public void VisualEditorEntryTest_009_urlVEActionEdit(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
    ArticlePageObject article = new ArticlePageObject(driver);
    article.logInCookie(credentials.getUserBaseOnEditorPref(editorPref),
                        credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
    article.verifyURLVEActionEditEditor(expectedEditor, wikiURL);
  }
}
