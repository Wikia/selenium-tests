package com.wikia.webdriver.testcases.articlecrudtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;

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
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    String articleName = article.getArticleName();
    DeletePageObject deletePage = article.deleteUsingDropdown();
    deletePage.submitDeletion();
    SpecialRestorePageObject restore = article.undeleteByFlashMessage();
    restore.verifyArticleName(articleName);
    restore.giveReason(article.getTimeStamp());
    restore.restorePage();
    article.verifyNotificationMessage();
    article.verifyArticleTitle(articleName);
  }

  @Test(groups = {"ArticleActionsAdmin_002"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.USER)
  public void moveArticle() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    String articleNewName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
    RenamePageObject renamePage = article.renameUsingDropdown();
    renamePage.rename(articleNewName, false);
    article.verifyNotificationMessage();
    article.verifyArticleTitle(articleNewName);
  }
}
