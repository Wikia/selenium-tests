package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;

import org.testng.annotations.Test;

@Test(groups = {"ArticleActionsAdmin"})
public class ArticleActionsAdminTests extends NewTestTemplate {

  @Test(groups = {"ArticleActionsAdmin_001"})
  @UseUnstablePageLoadStrategy
  @RelatedIssue(issueID = "WW-568")
  @Execute(asUser = User.STAFF)
  public void deleteUndeleteArticle() {
    String articleTitle = "DeleteUndeleArticle";
    new ArticleContent().push(PageContent.ARTICLE_TEXT, articleTitle);

    ArticlePageObject article = new ArticlePageObject().open(articleTitle);
    DeletePageObject deletePage = article.deleteUsingDropdown();
    deletePage.submitDeletion();
    SpecialRestorePageObject restore =
        article.getBannerNotifications().clickUndeleteLinkInBannerNotification();
    restore.verifyRestoredArticleName(articleTitle);
    restore.giveReason(article.getTimeStamp());
    restore.restorePage();

    Assertion.assertTrue(article.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification message is not visible");

    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleActionsAdmin_002"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.STAFF)
  public void moveArticle() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String articleNewName = TestContext.getCurrentMethodName() + article.getTimeStamp();
    RenamePageObject renamePage = article.renameUsingDropdown();
    renamePage.rename(articleNewName, false);

    Assertion.assertTrue(article.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification message is not visible");

    article.verifyArticleTitle(articleNewName);
  }
}
