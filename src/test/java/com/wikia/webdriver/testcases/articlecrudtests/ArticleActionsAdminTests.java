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
import com.wikia.webdriver.elements.oasis.components.notifications.Notification;
import com.wikia.webdriver.elements.oasis.components.notifications.NotificationType;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import org.testng.annotations.Test;
import java.util.List;

import static com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject.PageMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS;


@Test(groups = {"ArticleActionsAdmin"})
public class ArticleActionsAdminTests extends NewTestTemplate {


  @Test(groups = {"ArticleActionsAdmin_001"})
  @UseUnstablePageLoadStrategy
  @RelatedIssue(issueID = "MAIN-9808", comment = "problems with banner notifications")
  @Execute(asUser = User.STAFF)
  public void deleteUndeleteArticle() {
    String articleTitle = "DeleteUndeleArticle";
    new ArticleContent().push(PageContent.ARTICLE_TEXT, articleTitle);

    ArticlePageObject article = new ArticlePageObject().open(articleTitle);
    DeletePageObject deletePage = article.deleteUsingDropdown();
    deletePage.submitDeletion();
    List<Notification> confirmNotifications = article.getNotifications(NotificationType.CONFIRM);
    Assertion.assertEquals(confirmNotifications.size(),1,
            SpecialRestorePageObject.PageMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS);
    SpecialRestorePageObject restore = article.getNotifications(NotificationType.CONFIRM)
            .stream().findFirst().get().clickUndeleteLinkInBannerNotification();

    restore.verifyRestoredArticleName(articleTitle);
    restore.giveReason(article.getTimeStamp());
    restore.restorePage();
    confirmNotifications = article.getNotifications(NotificationType.CONFIRM);
    Assertion.assertEquals(confirmNotifications.size(),1,
            SpecialRestorePageObject.PageMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS);
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible());

    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleActionsAdmin_002"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.STAFF)
  public void moveArticle() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String articleOldTitle = article.getArticleTitle();
    String articleNewName = TestContext.getCurrentMethodName() + article.getTimeStamp();
    RenamePageObject renamePage = article.renameUsingDropdown();
    renamePage.rename(articleNewName, false);

    List<Notification> confirmNotifications = article.getNotifications(NotificationType.CONFIRM);

    Assertion.assertEquals(confirmNotifications.size(),1,
            SpecialRestorePageObject.PageMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS);
    Assertion.assertEquals(confirmNotifications.stream().findFirst().get().getMessage(),
            "\"" + articleOldTitle + "\" has been renamed \"" + articleNewName + "\"",
            "Banner notification messsage is invalid");
    Assertion.assertEquals(article.getArticleName(), articleNewName,
            "New article title is invalid");
  }
}
