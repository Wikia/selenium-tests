package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;

import org.testng.annotations.Test;

/**
 * @author: Bogna 'bognix' Knychała
 * @ownership: Content X-Wing
 */
@Test(groups = {"ArticleActionsAdmin"})
public class ArticleActionsAdminTests extends NewTestTemplate {

  @Test(groups = {"ArticleActionsAdmin_001"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.STAFF)
  public void deleteUndeleteArticle() {
    String articleTitle = "DeleteUndeleArticle";
    ArticleContent.push(PageContent.ARTICLE_TEXT, articleTitle);

    ArticlePageObject article = new ArticlePageObject(driver).open(articleTitle);
    DeletePageObject deletePage = article.deleteUsingDropdown();
    deletePage.submitDeletion();
    SpecialRestorePageObject restore = article.undeleteByFlashMessage();
    restore.verifyArticleName(articleTitle);
    restore.giveReason(article.getTimeStamp());
    restore.restorePage();
    article.verifyNotificationMessage();
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleActionsAdmin_002"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.STAFF)
  public void moveArticle() {
    ArticleContent.push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject(driver).open();
    String articleNewName = TestContext.getCurrentMethodName() + article.getTimeStamp();
    RenamePageObject renamePage = article.renameUsingDropdown();
    renamePage.rename(articleNewName, false);
    article.verifyNotificationMessage();
    article.verifyArticleTitle(articleNewName);
  }
}
